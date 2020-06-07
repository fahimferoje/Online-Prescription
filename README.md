# Online-Prescription

Online prescription system where user can login into the system, can perform CRUD operation on prescription. Able to track daily report on how many prescriptions crreated per day.

## Getting Started
 This app holds two part. one part is web and another part is restapi
 
### Prerequisites
1. maven installed in your computer
2. MySQL or MariaDB database up and running

### To run the application

1. create a schema named `online_prescription` in your database or you can put whatever you want but you have to change those value in `appilication.properties` file </br>
`spring.datasource.url = jdbc:mysql://localhost:3306/your_schema_name?autoreconnect=true` </br>
`flyway.schemas = your_schema_name`

2. replaced username and password with your database username and password in `appilication.properties` file </br>
```
spring.datasource.username = your_db_username
spring.datasource.password = your_db_password

flyway.user = your_db_username
flyway.password = your_db_password
```
3. From your cmd/terminal type
```
mvn clean
mvn spring-boot:run
```
## To test the webapp application

1. Open your browser and type http://localhost:8080/
2. Flyway migration will create a default user to log in. In login view set email `doctor@cmed.com` and password `doctor` to log in.

## To test the api-consume part
1. you can find api consumption example here http://localhost:8080/api-consume and it is not protected page. you should be able to acess it without login in. 

## To test the REST API part

1. generate an acess token
Use the following generic command to generate an access token:
```
curl --location --request POST 'http://localhost:8080/api/v1/token' \
--header 'Content-Type: application/json' \
--data-raw '{
	"email": "doctor@cmed.com",
	"password": "doctor"
}'
```
Response :
```
{
    "token": "eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkb2N0b3JAY21lZC5jb20iLCJleHAiOjE1OTE1MjQ0NDgsImlhdCI6MTU5MTUwNjQ0OH0.WlWpbRLjRF3-WnL5yizKXZDj2qg78HrGJk53_TLZBobHdIUKkVIXyCT3JArHYsmVvv_gv_Vl1O5zlFAjeOUoaw"
}
```
2. Use the token to access resources through your RESTful API
   Acess all available prescriptions
   ```
    curl --location --request GET 'http://localhost:8080/api/v1/prescription' \
    --header 'Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJkb2N0b3JAY21lZC5jb20iLCJleHAiOjE1OTE1MjgzOTksImlhdCI6MTU5MTUxMDM5OX0.QEOFqGe4x7aPIIgjKcY8XjHiFSzbcSjiZZB    vQVtz6MOkEkBNY77_N-VU5YNatw1HVlG8a1tmX9nK4woawQYp9g' \
    ```
    Response will be
    ```
    [
    {
        "id": 1,
        "diagnosis": "Normal Fever",
        "medicines": "Nape extra",
        "nextVisitDate": "2020-06-18",
        "createdAt": "2020-06-05",
        "user": {
            "username": "doctor@cmed.com"
        },
        "patient": {
            "id": 1,
            "name": "patient 1",
            "age": 30,
            "gender": "MALE"
        },
        "patientId": null,
        "nextVisitDateString": null,
        "userId": null
    },
    {
        "id": 2,
        "diagnosis": "Normal Fever",
        "medicines": "Nape extra",
        "nextVisitDate": "2020-06-18",
        "createdAt": "2020-06-05",
        "user": {
            "username": "doctor@cmed.com"
        },
        "patient": {
            "id": 2,
            "name": "patient 2",
            "age": 35,
            "gender": "FEMALE"
        },
        "patientId": null,
        "nextVisitDateString": null,
        "userId": null
    }
    ]
    ```

## Author

* **Fahim Feroje Al Jami**
