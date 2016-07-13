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


java -jar cspmj.jar -parse file-name.csp 

or

java -jar cspmj.jar -parse file-name.csp --prologOut=output-file-name

or

java -jar cspmj.jar -parseAll