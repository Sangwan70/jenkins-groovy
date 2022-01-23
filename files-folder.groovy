#!groovy
import groovy.io.FileType
def folderName = '/var/jenkins_home/jobs/backup'

def thinBackupDir = new File(folderName)
if (thinBackupDir.exists() == false) {
   
   thinBackupDir.mkdirs()
}

(1..3).each {
 new File("dir$it").mkdir()
}
(1..3).each {
 def file = new File("file$it")
 file << "Sample content for ${file.absolutePath}"
}

def file1 = new File('groovy1.txt')
def file2 = new File('groovy2.txt')
def file3 = new File('groovy3.txt')
 

file1.write 'Working with files the Groovy way is easy.\n'
 

file1 << 'See how easy it is to add text to a file.\n'
 

file2.text = '''We can even use the text property of
a file to set a complete block of text at once.'''
 

file3.withWriter('UTF-8') { writer ->
    writer.write('We can also use writers to add contents.')
}

def lines = file1.readLines()
assert 2 == lines.size()
assert 'Working with files the Groovy way is easy.' == lines[0]
assert 'We can also use writers to add contents.' == file3.text

     

files.each { new File(it).delete() }
/*

def mainDir = new File('test')
def subDir = new File(mainDir, 'app')
def file = new File(subDir, 'test.txt')
 
subDir.mkdirs()  
file << 'sample'  
 
assert mainDir.exists() && subDir.exists() && file.exists()
 
def result = mainDir.deleteDir()  
assert result
assert !mainDir.exists() && !subDir.exists() && !file.exists()
*/    
