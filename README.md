# Upload South Africa telephone numbers CSV file



## Tecnologies

HTML  
Bootstrap  
Thymeleaf  
Java  
SpringBoot  
OpenCSV  
Gradle  



## Databases

Change the properties in file:

    south-africa-nums/src/main/resources/application.properties

to use a either a preset PostgreSQL instance (where a sapdb DB was created):

    spring.datasource.url = jdbc:postgresql://localhost:5432/sapdb
    spring.datasource.platform = postgres
    spring.datasource.username = <user>
    spring.datasource.password = <password>

or H2 Database Engine (sapdb.mv.db file will be created in the base directory):

	spring.datasource.platform=h2
	spring.datasource.url=jdbc:h2:file:./sapdb
	spring.datasource.driverClassName=org.h2.Driver
	spring.datasource.username=sa
	spring.datasource.password=
	spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.H2Dialect

The tables will be automatically created and updated.  



## Build and run


### Windows environment

Set environment:

    set JAVA_HOME=C:\Program Files\Java\jdk-11.0.17
    set PATH=%JAVA_HOME%\bin;%PATH%


### Run automatic tests on Windows

    cd <path>\south-africa-nums
    .\gradlew test

Tests report:

    build\reports\tests\tes\index.html


### Run the application on Windows

Run the application:

    cd <path>\south-africa-nums
    .\gradlew bootRun


### Run executable jar on Windows

Build executable jar:

    cd <path>\south-africa-nums
    .\gradlew clean bootJar

The excutable jar is:

    build\libs\south-africa-nums-0.0.1-SNAPSHOT.jar


Run (previously compiled) executable jar:

    cd <path>\south-africa-nums
    java -jar build\libs\south-africa-nums-0.0.1-SNAPSHOT.jar


### Linux environment

    export JAVA_HOME=<path to java>
    export PATH=$JAVA_HOME/bin:$PATH


### Run executable jar on Linux

    cd <path to directory containing the executable jar>
    java -jar south-africa-nums-0.0.1-SNAPSHOT.jar



## South African Numbers Basic Application


### Basic frontend page

Once the Spring Boot application is started, open:

    http://localhost:8080/


Import Phone Numbers: before importing numbers, the content of the DB is erased  
Validate Phone Number: the number is just validated but not inserted in the DB  
List Phone Numbers: you can either filter by status or list all numbers  

The following CSV files are provided in the base directory:  

    Interlogica_Test Pre-selezione. South_African_Mobile_Numbers.csv
    Interlogica_Test Pre-selezione. South_African_Mobile_Numbers_Empty.csv



### Telephone numbers validation policy

The application validates numbers according to the following format:

    international prefix
     |
     |    7 digits telephone number 
     |       |
    27 83 1234567
        |
       2 digits area code

Accepted numbers must have a length of 11.  
Numbers with length of 9 are corrected adding the international prefix.  
No validation of 2 digits area code is provided.  
The other numbers are considered incorrect and the reason is given. 
  
   



