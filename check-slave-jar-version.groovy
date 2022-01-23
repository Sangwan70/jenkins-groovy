#!groovy

import jenkins.model.*
import hudson.remoting.Launcher
import hudson.slaves.SlaveComputer

def expectedVersion = Launcher.VERSION
for (computer in Jenkins.instance.getComputers()) {
  if (! (computer instanceof SlaveComputer)) continue
  if (!computer.getChannel()) continue
	
  def version = computer.getSlaveVersion()
  if (!expectedVersion.equals(version)) {
    println "${computer.name} - expected ${expectedVersion} but got ${version}"
  }
}
