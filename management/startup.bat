:: Turns off command echoing, preventing the script from displaying the commands as they execute.
echo off

:: Starts a local scope for environment variables, meaning any variables set inside the script will not affect the system environment after the script ends.
setlocal

:: Sets the CONFIG_DIR variable to the config directory located in the same folder as the script
:: %~dp0 refers to the directory of the script file.
set CONFIG_DIR=%~dp0config

:: Sets the JAR_FILE variable to the management.jar file located in the same folder as the script.
:: %~dp0 ensures it finds management.jar in the script’s directory.
set JAR_FILE=%~dp0management.jar

:: tells Spring Boot to load its configuration from the config directory.
java -Dspring.config.location=file:///%CONFIG_DIR%\ -jar %JAR_FILE%

:: Ends the local environment variable scope, reverting any changes to the system variables.
endlocal