#!/bin/zsh
CURRENT_DIR=`pwd`
TP_JAR="$CURRENT_DIR/../TP2/out/artifacts/TP2/TP2.jar"
OUT_PRODUCTION="./out/production/TP1Client"
CLEAN="rm -rf $OUT_PRODUCTION && mkdir ./out/production/TP1Client"
JAVAC="javac -classpath $TP_JAR ./src/TP2Client.java -d $OUT_PRODUCTION"
CMD="cd $OUT_PRODUCTION && java -classpath $CLASSPATH:$TP_JAR TP2Client"

eval $CLEAN
eval $JAVAC
eval $CMD
