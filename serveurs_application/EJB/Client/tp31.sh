#!/bin/zsh
CURRENT_DIR=`pwd`
TP_JAR="$CURRENT_DIR/../TP3-1/out/artifacts/TP31/TP31.jar"
OUT_PRODUCTION="./out/production/TP1Client"
CLEAN="rm -rf $OUT_PRODUCTION && mkdir ./out/production/TP1Client"
JAVAC="javac -classpath $TP_JAR ./src/TP3Client1.java -d $OUT_PRODUCTION"
CMD="cd $OUT_PRODUCTION && java -classpath $CLASSPATH:$TP_JAR TP3Client1"

eval $CLEAN
eval $JAVAC
eval $CMD
