#Cognizant Assignment
Rabobank Customer Statement Processor

#Description: This application will process Rabobank customer statements and returns the failed statement records. The supported file formats are CSV and XML.

#Tools used
Java 8
Spring Boot
Tomcat - Embedded Web Server

#Assumptions
Security is considered out of scope for this application

#How to run the app
1. Import the project as Maven project. All the necessary dependency jars will be downloaded automatically. Once it imports all the jars run "CustomerStatementProcessor.java" as "java application". Note:No need to do server setup as springboot internally uses tomcat server so we can run this application as standalone.

2. This Web service application has one active service to process Csv/Xml files. please find service url below,http://localhost:8888/processStatment

3. Upload input csv/xml file in the service using postman client.

4. The input file will be validated based on two condition mentioned in the problem statment.

5. We will get the invalid records in the response of the webservice along with status code.

Steps to test on POSTMAN
To upload file using postman: Select POST method. Provide "http://localhost:8080/processStatment" service link. Under Body select form-data and provide key as "file", "choose files" button will be enabled in value tab. Upload the file using this button. Please refer screenshot below.
 
Once the request is submitted you will receive a response with the failed transaction records and status code similar to the screen below.