### Dropwizard integrate with Guice extension.
  1. How to use Dropwizard extension of Guice (https://github.com/HubSpot/dropwizard-guice).  
  2. How to integrate H2 embedded database with dropwizard application.
  3. How to implement client using dropwizard-client.
  
### PreRequisites.
  1. JDK 1.7 or above.
  2. Gradle 2.3 or above.
  
### How to import.?
  1. Eclipse:
     Run the below gradle task, it creates eclipse project specific files .eclipse and .project, so you are good to import.   
     
     ```>gradle eclipse```  
     
  2. Intellij IDEA.  
     Directly clone from github and import gradle settings.  
     
### How to run?
  1. Dropwizard application has boot strapping class called Application.  
     ```TweetService.java```  
     in my case. Open and run as java main class and set the below program argument.  
     ```check/server <tweet.yml file location>```  
     check : verifies the yml file format.  
     server : to start the server with provided configuration.  
     And use Chrome post man or any other rest client to test it.  
     Request URL: http://<HostName>:<Port>/tweet
     Request Body:  
       ```{  
            "message": "Hello dropwizard..",  
            "date": "2015-09-11 19:58:00"  
          }```  
     
### How to run test cases?
   1. You can directly run below gradle task to run the tests.  
      ```>gradle test```  
   2. Open ```TweetE2ETest.java``` in your IDE and run as JUnit test by highlighting the particular method or all.
   
   