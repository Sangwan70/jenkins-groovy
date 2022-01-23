#!groovy

matcher = "Hello world v1.01" =~ /.* v(\S*)/
if (matcher.matches()) version = matcher[0][1]
assert version == "1.01"
version = ("Hello world v1.01" =~ /.* v(\S*)/).with { matches() ? it[0][1] : null }
assert version == "1.01"
