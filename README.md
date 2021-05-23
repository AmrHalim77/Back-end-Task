You can run this project using maven command : mvn clean install then cd target directory then java -jar phone-management-0.0.1-SNAPSHOT.jar

or you can use docker : 

1- build the project using this command:  mvn clean install 
2- Generate Image using this command: docker build -t spring-boot-phone .  // ps: Dockerfile exist in spring-boot project.
3- docker run --name back-end  -d -it -p 8081:8080 spring-boot-phone




you can call APIs using this url: 

1- http://localhost:8081/api/add
2- http://localhost:8081/api/getAll

