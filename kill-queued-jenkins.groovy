#!groovy

import hudson.model.*

def q = Jenkins.instance.queue

q.items.each {
  if (it =~ /deploy-to/) {
    q.cancel(it.task)
  }
}

