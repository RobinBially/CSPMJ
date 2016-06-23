**********
* CSPMJ	 *
**********

java -jar sablecc.jar CSPMparser.scc
java -jar sablecc.jar CTLparser.scc
java -jar sablecc.jar LTLparser.scc
javac CTLparser.java
javac LTLparser.java
javac CSPMparser.java
java CSPMparser -parse Examples\newdebug.csp
java CSPMparser -parse -show Examples\newdebug.csp
java CSPMparser -parseAll 
java CSPMparser -parseAll -show				(-show shows edited Tokenstream)


Triangle substitutions:
\u00AB = «	seq opening
\u00BB = »	seq closing
\u00A3 = £	greater
\u20AC = €  smaller

*********************
* BUILD WITH GRADLE *
*********************
To adapt or change the gradle-script, look at the file ‘build.gradle’. Note that the build-process of the parser depends on sablecc. Therefore, ensure that you are connected to internet because the sablecc generator is downloaded.

command for building the project:
      gradle build
use —info for verbose build:
      gradle build —info
to build the project and produce the corresponding .jar-file use the the following command:
      gradle uberjar
   - the .jar file and all .class files are saved in a special folde ‘build’. the jar-file can be found in ‘build/libs’

To execute the parser you can use the produced .jar-file as follows:

java -jar cspmj.jar -parse some-file.csp
_________________________________________________________________________________________

*********
* CSPMF *
*********

cspmf-windows.exe translate test.csp --prologOut=test.csp.pl

_________________________________________________________________________________________

***********
* GitHub  *
***********

git reset --hard <sha1-commit-id>
git push -f
git rm -r --cached .
git add .
git commit -m "fixed untracked files"
git branch -d the_local_branch
_________________________________________________________________________________________