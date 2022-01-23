#!groovy

def gettags = ("git ls-remote -t -h ssh:

return gettags.text.readLines()
         .collect { it.split()[1].replaceAll('refs/heads/', '')  }
         .unique()
         .findAll { it.startsWith('<some more pattern>') }
