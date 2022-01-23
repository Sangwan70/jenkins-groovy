#!groovy

import hudson.slaves.*

def javaHome = new EnvironmentVariablesNodeProperty(
    new EnvironmentVariablesNodeProperty.Entry('JAVA_HOME', '/usr/lib/jvm/java-7-oracle-amd64'))

hudson.model.Hudson.instance.slaves.each { it.nodeProperties.add(javaHome) }    
