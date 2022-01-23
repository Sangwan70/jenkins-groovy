#!groovy

def command = "date"
def proc = command.execute()
proc.waitFor()     

println "Process exit code: ${proc.exitValue()}"
println "Std Err: ${proc.err.text}"
println "Std Out: ${proc.in.text}" 
