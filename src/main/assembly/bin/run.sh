#!/bin/bash
# 项目名称
SERVER_NAME="${project.artifactId}"
echo $SERVER_NAME
JAR_NAME="${project.build.finalName}.jar"
echo $JAR_NAME

PACKAGE_PATH="$( dirname $( cd "$( dirname "$0"  )" && pwd  ) )"
if [[ -z $JAVA_OPTS ]];then
    JAVA_OPTS="-Xms128m -Xmx256m -XX:+TieredCompilation -XX:+UseG1GC -XX:MaxGCPauseMillis=200 \
      -Xlog:gc:${PACKAGE_PATH}/logs/gc.log \
      -XX:HeapDumpPath=${PACKAGE_PATH}/heapdump.hprof \
      -XX:+HeapDumpOnOutOfMemoryError -XX:+DisableExplicitGC"
fi

CLASSPATH=${CLASSPATH}:"${PACKAGE_PATH}/conf":"${PACKAGE_PATH}/lib/*"

if [ ! -d "${PACKAGE_PATH}/logs" ];then
    mkdir -p ${PACKAGE_PATH}/logs
fi

echo "Using JAVA_HOME   ${JAVA_HOME}"
echo "Using CLASSPATH   ${CLASSPATH}"

exec "${JAVA_HOME}/bin/java" -Duser.dir=${PACKAGE_PATH} ${JAVA_OPTS} ${APP_OPTS} -cp ${CLASSPATH} -jar ${PACKAGE_PATH}/lib/${JAR_NAME}