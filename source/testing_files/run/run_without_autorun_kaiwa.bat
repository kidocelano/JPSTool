SET "JAVA_PATH=%JAVA_HOME%"
SET "JAVA_JRE=%JAVA_PATH%/jre/bin/java.exe"
SET "FILE_PATH=D:\Project\mine\git\JPSTool\source\testing_files\kanji_kaiwa.txt"
SET INTERVAL=60
start "" /min "%JAVA_JRE%" -cp bin/;libs/* -Dfile.encoding=UTF-8 org.jpstool.main.MainDriven "%FILE_PATH%" %INTERVAL%
