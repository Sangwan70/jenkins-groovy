#!groovy

def hostname = 'google.com'
println InetAddress.getByName(hostname).address.collect { it & 0xFF }.join('.')
