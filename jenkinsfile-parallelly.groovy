#!groovy
stage('stage1') {
    echo 'Hello World'
}

stage('stage2') {
    parallel firstBranch: {
        
         build 'dennyjob1'
    }, secondBranch: {
        
        build job: 'dennyjob2', parameters: [string(name: 'env1', value: 'value3')]
    },
    failFast: true
}

stage('stage3') {
   build 'dennyjob3'
}
