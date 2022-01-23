#!groovy

import jenkins.model.*
import hudson.security.*

def instance = Jenkins.getInstance()
def strategy = new hudson.security.FullControlOnceLoggedInAuthorizationStrategy()
strategy.setAllowAnonymousRead(false)
instance.setAuthorizationStrategy(strategy)

instance.save()
