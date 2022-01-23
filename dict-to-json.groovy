#!groovy
import static groovy.json.JsonOutput.*
def config = ['name': 'denny']
println prettyPrint(toJson(config))
