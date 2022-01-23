#!groovy
import jenkins.model.*

import hudson.security.LDAPSecurityRealm
import hudson.security.HudsonPrivateSecurityRealm
import hudson.security.FullControlOnceLoggedInAuthorizationStrategy
import hudson.util.Secret
import jenkins.model.IdStrategy
import jenkins.security.plugins.ldap.LDAPConfiguration
import net.sf.json.JSONObject
if(!binding.hasVariable('ldap_settings')) {
   ldap_settings = [
      'server': 'ldaps:
      'rootDN': 'DC=mycompany,DC=com',
   ]
}
if(!(ldap_settings instanceof Map)) {
   throw new Exception('ldap_settings must be a Map.')
}

ldap_settings = ldap_settings as JSONObject

if(!(Jenkins.instance.securityRealm instanceof LDAPSecurityRealm)) {
   LDAPConfiguration conf = new LDAPConfiguration(
      ldap_settings.optString('server'),
      ldap_settings.optString('rootDN'),
      ldap_settings.optBoolean('inhibitInferRootDN'),
      ldap_settings.optString('managerDN'),
      Secret.fromString(ldap_settings.optString('managerPasswordSecret')))

   conf.userSearchBase = ldap_settings.optString('userSearchBase')
   conf.userSearch = ldap_settings.optString('userSearch', LDAPSecurityRealm.DescriptorImpl.DEFAULT_USER_SEARCH)
   conf.groupSearchBase = ldap_settings.optString('groupSearchBase')
   conf.groupSearchFilter = ldap_settings.optString('groupSearchFilter')
   conf.environmentProperties = (ldap_settings.opt('environmentProperties')?:[:]).collect { k, v ->
      new LDAPSecurityRealm.EnvironmentProperty(k.toString(), v.toString())
   } as LDAPSecurityRealm.EnvironmentProperty[]
   conf.displayNameAttributeName = ldap_settings.optString('displayNameAttributeName', LDAPSecurityRealm.DescriptorImpl.DEFAULT_DISPLAYNAME_ATTRIBUTE_NAME)
   conf.mailAddressAttributeName = ldap_settings.optString('mailAddressAttributeName', LDAPSecurityRealm.DescriptorImpl.DEFAULT_MAILADDRESS_ATTRIBUTE_NAME)

   List<LDAPConfiguration> configurations = [conf]
   Jenkins.instance.securityRealm = new LDAPSecurityRealm(
      configurations,
      ldap_settings.optBoolean('disableMailAddressResolver'),
      null,
      IdStrategy.CASE_INSENSITIVE,
      IdStrategy.CASE_INSENSITIVE)

   def strategy = new hudson.security.FullControlOnceLoggedInAuthorizationStrategy()
   strategy.setAllowAnonymousRead(true)
   Jenkins.instance.setAuthorizationStrategy(strategy)

   Jenkins.instance.save()
   println 'Security realm set to LDAP.'
}
else {
   println 'Nothing changed.  LDAP security realm already configured.'
}
