@echo off

rem java
rem -Dserver.port=8888
rem -Dcsp.sentinel.dashboard.server=localhost:8888

rem -Dproject.name=sentinel-dashboard
rem -Dsentinel.dashboard.auth.username=sentinel
rem -Dsentinel.dashboard.auth.password=sentinel
rem -jar ../target/sentinel-dashboard-1.8.0.jar

if not exist "%JAVA_HOME%\bin\java.exe" echo Please set the JAVA_HOME variable in your environment, We need java(x64)! jdk8 or later is better! & EXIT /B 1
set "JAVA=%JAVA_HOME%\bin\java.exe"

set BASE_DIR=%~dp0
rem added double quotation marks to avoid the issue caused by the folder names containing spaces.
rem removed the last 5 chars(which means \bin\) to get the base DIR.
set BASE_DIR="%BASE_DIR:~0,-5%"

set DEFAULT_SEARCH_LOCATIONS="classpath:/,classpath:/config/,file:./,file:./config/"
set CUSTOM_SEARCH_LOCATIONS=%DEFAULT_SEARCH_LOCATIONS%,file:%BASE_DIR%/conf/

set PROJECT_NAME=sentinel-dashboard
set USERNAME=sentinel
set PASSWORD=sentinel
set SERVER=sentinel-dashboard-1.8.0

set "JAVA_OPT=%JAVA_OPT% -Dsentinel.home=%BASE_DIR%"
set "JAVA_OPT=%JAVA_OPT% -Dproject.name=%PROJECT_NAME%"
set "JAVA_OPT=%JAVA_OPT% -Dsentinel.dashboard.auth.username=%USERNAME%"
set "JAVA_OPT=%JAVA_OPT% -Dsentinel.dashboard.auth.password=%PASSWORD%"
set "JAVA_OPT=%JAVA_OPT% -jar %BASE_DIR%\target\%SERVER%.jar"
set "JAVA_OPT=%JAVA_OPT% --spring.config.location=%CUSTOM_SEARCH_LOCATIONS%"

call "%JAVA%" %JAVA_OPT% sentinel.sentinel %*

