@echo off

call mvn clean
call mvn package
call mvn findbugs:check
call mvn site