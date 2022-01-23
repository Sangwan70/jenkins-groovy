#!groovy
node {
  try {
    def seconds = readFile("/tmp/test.txt")

      echo "seconds begin"
      echo seconds
      echo "seconds done"
      parallel firstBranch: {
      
      build job: 'SleepAnHour', parameters: [string(name: 'sleep_seconds', value: seconds)]
    }, secondBranch: {
      
      build job: 'SleepTwoHours', parameters: [string(name: 'sleep_seconds', value: seconds)]
        },
         failFast: true
      }
  finally {
    echo "finally"
      }
}
