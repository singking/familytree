export root=.
export library=/home/zhane/.m2/repository

export CLASSPATH=$CLASSPATH:${root}/target/family-0.0.1-SNAPSHOT.jar 
export CLASSPATH=$CLASSPATH:${library}/com/google/code/findbugs/jsr305/2.0.1/jsr305-2.0.1.jar
export CLASSPATH=$CLASSPATH:${library}/com/google/collections/google-collections/1.0/google-collections-1.0.jar
export CLASSPATH=$CLASSPATH:${library}/com/google/guava/guava/18.0/guava-18.0.jar
export CLASSPATH=$CLASSPATH:${library}/org/codehaus/jackson/jackson-core-asl/1.9.13/jackson-core-asl-1.9.13.jar
export CLASSPATH=$CLASSPATH:${library}/org/codehaus/jackson/jackson-mapper-asl/1.9.13/jackson-mapper-asl-1.9.13.jar

java -cp ${CLASSPATH} family.CsvToJson >flarepretty.json
