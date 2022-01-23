#!groovy
import groovy.json.JsonSlurper
def jsonSlurper = new JsonSlurper()
def object = jsonSlurper.parseText('{ "name": "John Doe" } /* some comment */')
assert object instanceof Map
assert object.name == 'John Doe'
print object.name
