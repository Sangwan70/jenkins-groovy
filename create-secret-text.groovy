#!groovy

import jenkins.model.*
import hudson.util.Secret
import com.cloudbees.plugins.credentials.*
import com.cloudbees.plugins.credentials.domains.*
import org.jenkinsci.plugins.plaincredentials.impl.*

domain = Domain.global()
store = Jenkins.instance.getExtensionList('com.cloudbees.plugins.credentials.SystemCredentialsProvider')[0].getStore()

secretText = new StringCredentialsImpl(
CredentialsScope.GLOBAL,
"secret-text",
"Secret Text Description",
Secret.fromString("some secret text goes here"))

store.addCredentials(domain, secretText)
