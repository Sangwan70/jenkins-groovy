#!groovy
import jenkins.model.Jenkins;
import hudson.model.FreeStyleProject;
import hudson.tasks.Shell;

job = Jenkins.instance.createProject(FreeStyleProject, 'job-name')

job.buildersList.add(new Shell('echo hello world'))

job.save()

build = job.scheduleBuild2(5, new hudson.model.Cause.UserIdCause())

build.get()
