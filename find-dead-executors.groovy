#!groovy
def config = new HashMap()
def bindings = getBinding()
config.putAll(bindings.getVariables())
def out = config['out']

for (aSlave in hudson.model.Hudson.instance.slaves) {
  
  execList = aSlave.getComputer().getExecutors()      
  for( exec in execList ) {
    if (exec.getCauseOfDeath()) {
      println("\tSlave ${aSlave.name} has a dead executor.")
      println("Error:")
      exec.getCauseOfDeath().printStackTrace(out) 
      println('\n')
      println("\tRemoving Dead Executor.")
      exec.doYank()
    }
  } 
}
