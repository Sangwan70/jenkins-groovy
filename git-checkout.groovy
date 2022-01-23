#!groovy

#!/usr/bin/env groovy

node('php') {

    stage('Get code from SCM') {
        checkout(
                [$class: 'GitSCM', branches: [[name: '*/#your-dev-branch#']],
                 doGenerateSubmoduleConfigurations: false,
                 extensions: [],
                 submoduleCfg: [],
                 userRemoteConfigs: [[url: '#your-git-link#']]]
        )
    }

    stage('Composer Install') {
        sh 'composer install'
    }

    stage("PHPLint") {
        sh 'find app -name "*.php" -print0 | xargs -0 -n1 php -l'
    }
}
