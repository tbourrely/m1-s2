#!/bin/zsh
VERSION='Q10'
OUT_PRODUCTION="./out/production/$VERSION/"
OUT_ARTIFACTS="./out/artifacts/$VERSION"
RM_1="rm -rf $OUT_PRODUCTION"
RM_2="rm -rf $OUT_ARTIFACTS"
MKDIR="mkdir $OUT_PRODUCTION $OUT_ARTIFACTS $OUT_PRODUCTION"
eval $RM_1
eval $RM_2
eval $MKDIR
CP_META_INF="cp -r src/WEB-INF/ $OUT_PRODUCTION/WEB-INF && cp src/index.html $OUT_PRODUCTION/"
FILES_LIST=`find . -name "*.java"|xargs echo`
JAVAC_COMMAND="javac -d $OUT_PRODUCTION/WEB-INF/classes $FILES_LIST"
WAR_COMMAND="cd $OUT_PRODUCTION && jar cvf ./../../../$OUT_ARTIFACTS/$VERSION.war ./*"
CHECK_COMMAND="cd ./../../../ && jar tvf $OUT_ARTIFACTS/$VERSION.war"
DEPLOY_COMMAND="asadmin deploy $OUT_ARTIFACTS/$VERSION.war"
eval $CP_META_INF
eval $JAVAC_COMMAND
eval $WAR_COMMAND
eval $CHECK_COMMAND
eval $DEPLOY_COMMAND
