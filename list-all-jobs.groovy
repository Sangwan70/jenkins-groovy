#!groovy

import hudson.model.*

hudson.model.Hudson.instance.items.findAll{job -> job}.each {
    job -> println("Job: " + job.name)
}
