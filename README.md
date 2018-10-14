# URL SHORTENER
This is HTTP Service for creating short url @author Dino Spreco @version 1.0

## In App Help Page Uri (With JavaDoc): /help

## RUNNING THE API
### How to run API
 - Open Command Prompt and navigate to urlshortener-1.0.jar
 - Run command: java -jar urlshortener-1.0.jar
 - Wait until finishes
 
### What do I need to Run
 - You need Java SE Runtime Environment 8 or grater.
 
## USAGE
### Do I need account
 - YES!
### How can I create account
 - Send POST request to the rootUrl/account (http://<span></span>localhost:8080/account) with JSON 
 
    `{ "AccountId":"myAccountId" }`
 - In Response you will get JSON object: 
 
    `{"success": true, "description": "Your account is opened", "password": "JmtFDnuQ"}`
 - In case that account already exists you will get: 
 
    `{"success": false, "description": "Accoutn already exists", "password": null}`
 - In case of a bad request (E.g. mistype AcountId) you will get: 
 
    `{"success": false, "description": "Bad Request", "password": null}`
    
## SHORTENING URL
### How do i get my short url
 - Set Authorization header: Basic Auth and enter your credentials
 - Send POST request to the rootUrl/register (http://<span></span>localhost:8080/register) with JSON data 
 
    `{ "url":"reallyLongUrl" , "redirectType":301 }` 
    redirectType is optional, default is 301
 - In response you will get JSON object: 
 
    `{ "shortUrl": "http://localhost:8080/4l72U87RGn" }`
    
## STATISTICS
### What are statistics
 - API track how many times you visited your short url
### How to get statistics
 - Set Authorization header: Basic Auth and enter your credentials
 - Send GET request to the rootUrl/statistic/myAccountId (http://<span></span>localhost:8080/statistic/myAccountId)
 - In response you will get JSON object, map with key:value
   - key = URL
   - value = Number of visits
   
   `{ "www.someverylongurl.url/moreoftheurl" : 10, "www.someotherverylongurl.url/moreoftheotherurl: 3 }`
   
## JSON Object
### How do I send JSON data
 - Using Postman [Download](https://www.getpostman.com/)
 - [Other API testing tools](https://www.joecolantonio.com/12-open-source-api-testing-tools-rest-soap-services/)
