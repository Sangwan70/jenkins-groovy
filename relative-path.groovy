#!groovy

def root= new File('/usr/share')
def full= new File('/usr/share/docs/rpm-4.4')

def relPath= new File( root.toURI().relativize( full.toURI() ).toString() )
