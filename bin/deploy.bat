@echo off

call mvn clean
call mvn package
call mvn findbugs:check
call mvn site

set PORT=4567
java -jar target/TicTacToe-1.0-SNAPSHOT-jar-with-dependencies.jar
