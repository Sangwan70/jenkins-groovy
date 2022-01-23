#!groovy
import jenkins.model.Jenkins
import org.jenkinsci.plugins.workflow.job.WorkflowJob
import org.jenkinsci.plugins.workflow.cps.CpsScmFlowDefinition
import org.jenkinsci.plugins.workflow.flow.FlowDefinition
import hudson.plugins.git.GitSCM
import hudson.plugins.git.UserRemoteConfig
import com.cloudbees.hudson.plugins.folder.*
String folderName = "$folderName"
String jobName = "$jobName"
String jobScript = "$jobScript"
String gitRepo = "$gitRepo"
String gitRepoName = "$gitRepoName"
String credentialsId = "$credentialsId"

Jenkins jenkins = Jenkins.instance 
def folder = jenkins.getItem(folderName)

if (folder == null) {
  folder = jenkins.createProject(Folder.class, folderName)
}
UserRemoteConfig userRemoteConfig = new UserRemoteConfig(gitRepo, gitRepoName, null, credentialsId)

branches = null
doGenerateSubmoduleConfigurations = false
submoduleCfg = null
browser = null
gitTool = null
extensions = []
GitSCM scm = new GitSCM([userRemoteConfig], branches, doGenerateSubmoduleConfigurations, submoduleCfg, browser, gitTool, extensions)
FlowDefinition flowDefinition = (FlowDefinition) new CpsScmFlowDefinition(scm, jobScript)
Object job = null
job = folder.getItem(jobName)
if (job == null) {
  oldJob = jenkins.getItem(jobName)
  if (oldJob.getClass() == WorkflowJob.class) {
    
    Items.move(oldJob, folder)
  } else {
    
    job = folder.createProject(WorkflowJob, jobName)
  }
}

job.setDefinition(flowDefinition)
job.save()
