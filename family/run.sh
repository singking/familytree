export root=/home/zhane/javascript/d3js/tree/family
export CLASSPATH=$CLASSPATH:${root}/target/family-0.0.1-SNAPSHOT.jar 
export CLASSPATH=$CLASSPATH:/home/zhane/.m2/repository/com/google/code/findbugs/jsr305/2.0.1/jsr305-2.0.1.jar
export CLASSPATH=$CLASSPATH:/home/zhane/.m2/repository/com/google/collections/google-collections/1.0/google-collections-1.0.jar
export CLASSPATH=$CLASSPATH:/home/zhane/.m2/repository/com/google/guava/guava/18.0/guava-18.0.jar
export CLASSPATH=$CLASSPATH:/home/zhane/.m2/repository/org/codehaus/jackson/jackson-core-asl/1.9.13/jackson-core-asl-1.9.13.jar
export CLASSPATH=$CLASSPATH:/home/zhane/.m2/repository/org/codehaus/jackson/jackson-mapper-asl/1.9.13/jackson-mapper-asl-1.9.13.jar

java -cp ${CLASSPATH} family.CsvToJson >flarepretty.json
