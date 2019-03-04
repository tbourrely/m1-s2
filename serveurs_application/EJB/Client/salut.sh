#!/bin/zsh
CURRENT_DIR=`pwd`
TP1_JAR="$CURRENT_DIR/../TP1/out/artifacts/TP1/TP1.jar"
OUT_PRODUCTION="./out/production/TP1Client"
CLEAN="rm -rf $OUT_PRODUCTION && mkdir ./out/production/TP1Client"
JAVAC="javac -classpath $TP1_JAR ./src/Client.java -d $OUT_PRODUCTION"
CMD="cd $OUT_PRODUCTION && java -classpath $CLASSPATH:$TP1_JAR Client"

eval $CLEAN
eval $JAVAC
eval $CMD
