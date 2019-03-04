#!/bin/zsh
CURRENT_DIR=`pwd`
TP_JAR="$CURRENT_DIRR/../TP3-2/out/artifacts/TP32/TP32.jar"
OUT_PRODUCTION="./out/production/TP1Client"
CLEAN="rm -rf $OUT_PRODUCTION && mkdir ./out/production/TP1Client"
JAVAC="javac -classpath $TP_JAR ./src/TP3Client2.java -d $OUT_PRODUCTION"
CMD="cd $OUT_PRODUCTION && java -classpath $CLASSPATH:$TP_JAR TP3Client2"

eval $CLEAN
eval $JAVAC
eval $CMD
