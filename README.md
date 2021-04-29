# super-smart

Test task for Super Smart

## Getting Started

The project is located: https://github.com/BliznyukAlex/super-smart

Source code can be download as Zip file or by using Git
https://github.com/BliznyukAlex/super-smart.git

# Running the application

There are several ways to run a Spring Boot application on your local machine. 
One way is to execute the main method in the com.testtask.contactbook.ContactBookApplication.java class from your IDE.

Another way is to go to project directory and run from command line
 
    mvn spring-boot:run
    
## Endpoints
    
    POST request to /api/v1/validation/items --> to validate customer's transaction  

## Request examples
### Validate transaction

    POST /api/v1/validation/items
    Accept: application/json
    Content-Type: application/json
    [
        {              
            "id":1,
            "barcode":1202020,
            "weight":0.99,
            "type":"WEIGHTED"
        },
        {              
            "id":2,
            "barcode":1202021,
            "weight":39.99,
            "type":"UNIT"
        },
        {              
            "id":3,
            "barcode":2902022,
            "weight":39.99,
            "type":"GREEN"
        }
    ]
    RESPONSE: HTTP 200 (OK)
