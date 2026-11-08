@echo off
echo ====================================================
echo Starting Apache Tomcat for PROTHAM - MATH WHIZ
echo ====================================================
set "CATALINA_HOME=C:\Users\HP\apache-tomcat-10.1.61"
cd /d "C:\Users\HP\apache-tomcat-10.1.61\bin"
call startup.bat
echo Tomcat is starting in a separate window!
echo Access your app at: http://localhost:8080/protham-math-whiz/
pause
