#!groovy

node("${params.BUILD_AGENT}") {
    
    stage('Dangling Containers') {
      sh 'docker ps -q -f status=exited | xargs --no-run-if-empty docker rm'
    }

    stage('Dangling Images') {
      sh 'docker images -q -f dangling=true | xargs --no-run-if-empty docker rmi'
    }
    
    stage('Dangling Volumes') {
      sh 'docker volume ls -qf dangling=true | xargs -r docker volume rm'
    }
}

