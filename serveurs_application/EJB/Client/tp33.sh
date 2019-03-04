#!/bin/zsh
CURRENT_DIR=`pwd`
TP_JAR="$CURRENT_DIR/../TP3-3/out/artifacts/TP33/TP33.jar"
OUT_PRODUCTION="./out/production/TP1Client"
CLEAN="rm -rf $OUT_PRODUCTION && mkdir ./out/production/TP1Client"
JAVAC="javac -classpath $TP_JAR ./src/TP3Client3.java -d $OUT_PRODUCTION"
CMD="cd $OUT_PRODUCTION && java -classpath $CLASSPATH:$TP_JAR TP3Client3"

eval $CLEAN
eval $JAVAC
eval $CMD
