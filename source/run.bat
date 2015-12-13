SET "JAVA_PATH=%JAVA_HOME%"
SET "JAVA_JRE=%JAVA_HOME%/jre/bin/java.exe"
SET "FILE_PATH=D:\Software\mesagebox\Jp\kanji_job_unv.txt"
SET INTERVAL=3
start "" /min "%JAVA_JRE%" -cp bin/;libs/* -Dfile.encoding=UTF-8 org.jpstool.main.MainDriven "%FILE_PATH%" %INTERVAL% autoStart
