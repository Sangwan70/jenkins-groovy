#!groovy

def tc_list = testsuites.tokenize(',')
  tc_list.each {
  try {
    stage("${it}") {
      println "============ Test ${it}"
      sh "mvn com.smartbear.soapui:soapui-maven-plugin:5.1.2:test -Dmdm.test.suite=\"${it}\""
    }
  } catch (e) {
    result = "FAIL"
  }
}

try {
  stage('end-to-end-tests') {
    node {
      def e2e = build job:'end-to-end-tests', propagate: false
        result = e2e.result
        if (result.equals("SUCCESS")) {
        } else {
          sh "exit 1" 
        }
    }
  }
 } catch (e) {
  result = "FAIL" 
    }

stage('deploy') {
  if (result.equals("SUCCESS")) {
    build 'deploy'
    } else {
       echo "Cannot deploy without successful build" 
    }
}
