# userfeedbackservice
Simple REST service for getting user feedback

This REST service will receive user feedback and provide list of previous entries. 

- **userfeedback/rest/feedback _GET_** List of entries 
- **userfeedback/rest/feedback/{id} _GET_** Feedback details 
- **userfeedback/rest/feedback _POST_** Feedback details, accepting parameters  _name_  and _content_ 

All in JSON format

Its java web app, you can deploy on Tomcat or any other container. 
There is also Main class in test folder. If you run it, it runt simple server for testing.

Build using maven
```
mvn clean install
```

Tests will run server on port 8080. Test might fail if port is in use.

To build without tests
```
mvn clean install -Dmaven.test.skip=true
```
