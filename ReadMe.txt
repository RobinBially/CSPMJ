***************************
* CSPMJ BUILD WITH GRADLE *
***************************

To adapt or change the gradle-script, look at the file ‘build.gradle’. Note that the build-process of the parser depends on sablecc. Therefore, ensure that you are connected to internet because the sablecc generator is downloaded.

command for building the project and generating the jar-file for the CSP parser:
      gradle build
use —info for verbose build:
      gradle build —info

Additional info:
    The .jar file and all .class files are saved in a special folder ‘build’. The jar-file can be found in ‘build/libs’

To execute the parser you can use the produced .jar-file as follows:

java -jar cspmj.jar -parse Examples\some-file.csp
java -jar cspmj.jar -parse -show Examples\some-file.csp   (-show shows edited Tokenstream)

SableCC:
java -jar sablecc.jar some-file.scc
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