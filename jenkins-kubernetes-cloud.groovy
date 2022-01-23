#!groovy

import org.csanchez.jenkins.plugins.kubernetes.*
import jenkins.model.*

def j = Jenkins.getInstance()

def k = new KubernetesCloud(
  'kubernetes',
  null,
  'https:
  '',
  'http:
  '10', 0, 0, 5
)
k.setSkipTlsVerify(true)

j.clouds.replace(k)
j.save()
