#!groovy

/*
 * This script configures the Jenkins base URL.
 */

import jenkins.model.JenkinsLocationConfiguration

JenkinsLocationConfiguration location = Jenkins.instance.getExtensionList('jenkins.model.JenkinsLocationConfiguration')[0]
location.url = 'https:
location.save()

import jenkins.model.*
import hudson.security.*
def env = System.getenv()

def jenkinsLocationConfiguration = JenkinsLocationConfiguration.get()
jenkinsLocationConfiguration.setAdminAddress(env.JENKINS_EMAIL)
jenkinsLocationConfiguration.setUrl(env.JENKINS_URL)
jenkinsLocationConfiguration.save()
