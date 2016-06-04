**********
* CSPMJ	 *
**********

java -jar sablecc.jar CSPMparser.scc
java -jar sablecc.jar CTLparser.scc
java -jar sablecc.jar LTLparser.scc
javac CTLparser.java
javac LTLparser.java
javac CSPMparser.java
java CSPMparser -parse Examples\debug.csp
java CSPMparser -parse -h Examples\debug.csp
java CSPMparser -parseAll 
java CSPMparser -parseAll -h 				(h shows edited Tokenstream)
_________________________________________________________________________________________

*********
* CSPMF *
*********

cspmf-windows.exe translate debug.csp --prologOut=debug.csp.pl

_________________________________________________________________________________________

***********
* GitHub  *
***********

git reset --hard <sha1-commit-id>
git push -f
_________________________________________________________________________________________