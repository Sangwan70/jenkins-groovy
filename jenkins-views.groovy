#!groovy

import jenkins.model.Jenkins
import hudson.model.ListView
import hudson.model.View
Jenkins jenkins = Jenkins.getInstance()

def viewName = ''

viewName = 'CIAutoMaintain'
jenkins.addView(new ListView(viewName))
myView = hudson.model.Hudson.instance.getView(viewName)
for(item in ['CleanOldArtifact', 'CreateTestEnv']) {
    myView.doAddJobToView(item)
}
jenkins.save()
View welcome_view = jenkins.getView('CIAutoMaintain')
View primary_view = jenkins.getPrimaryView()
jenkins.setPrimaryView(welcome_view)
