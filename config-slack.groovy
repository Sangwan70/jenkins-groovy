#!groovy
import jenkins.model.Jenkins
import jenkins.plugins.slack.SlackNotifier.*
def slack = Jenkins.instance.getExtensionList('jenkins.plugins.slack.SlackNotifier$DescriptorImpl')[0]

slack.tokenCredentialId = 'slack_token_passwd'
slack.baseUrl = 'https:

slack.save()
println 'Slack global settings configured.'
