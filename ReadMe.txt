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