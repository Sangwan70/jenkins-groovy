#!groovy

import jenkins.model.*

def matchedJobs = Jenkins.instance.items.findAll { job ->
    job.name =~ /my_regex_here/
}
    
matchedJobs.each { job ->
    println job.name
    
}
