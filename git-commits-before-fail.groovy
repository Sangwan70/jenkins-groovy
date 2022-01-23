#!groovy

node( 'some_node' ) {
  stage( "Phase 1" ) {
    sshagent( credentials: [ 'some_creds' ] ) {
      checkout scm
      def lastSuccessfulCommit = getLastSuccessfulCommit()
      def currentCommit = commitHashForBuild( currentBuild.rawBuild )
      if (lastSuccessfulCommit) {
        commits = sh(
          script: "git rev-list $currentCommit \"^$lastSuccessfulCommit\"",
          returnStdout: true
        ).split('\n')
        println "Commits are: $commits"
      }
    }
  }
}

def getLastSuccessfulCommit() {
  def lastSuccessfulHash = null
  def lastSuccessfulBuild = currentBuild.rawBuild.getPreviousSuccessfulBuild()
  if ( lastSuccessfulBuild ) {
    lastSuccessfulHash = commitHashForBuild( lastSuccessfulBuild )
  }
  return lastSuccessfulHash
}

/**
 * Gets the commit hash from a Jenkins build object, if any
 */
@NonCPS
def commitHashForBuild( build ) {
  def scmAction = build?.actions.find { action -> action instanceof jenkins.scm.api.SCMRevisionAction }
  return scmAction?.revision?.hash
}
