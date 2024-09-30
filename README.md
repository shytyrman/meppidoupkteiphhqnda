# Person CRUD

Some definitions should be here

## Table of Contents

- [Project Name](#person-crud)
- [Features](#features)
- [Installation](#installation)
- [Environment Variables](#environment-variables)
- [Usage](#usage)

## Features

#### PostgreSQL

- getting records by id/phoneNumber
- updating records by id/phoneNumber
- deleting records by id/phoneNumber
- getting all records by setting limit and offset

#### MongoDB

- getting records by id/phoneNumber
- updating records by id/phoneNumber
- deleting records by id/phoneNumber
- getting all records by setting limit and offset

## Installation

### Prerequisites

- [Java 17](https://bell-sw.com/pages/downloads/#jdk-17-lts) - Liberica JDK
- [Gradle](https://gradle.org/install/) - 8.10.1
- [PostgreSQL](https://www.postgresql.org/download/) - 16.2
- [MongoDB](https://www.mongodb.com/try/download/community) - 7.0.0

### Docker Installation Steps(Recommended)

1. Clone the repository:
   ```bash
   git clone https://github.com/shytyrman/meppidoupkteiphhqnda.git
2. Open the root folder:
    ```
   cd meppidoupkteiphhqnda
3. Run the Docker Engine(Docker Desktop)
4. Build and Run the project:
    ```
   docker-compose up --build
> **The application will be opened on port _8082_**

### Windows Installation Steps

- Database in PostgreSQL has to be created.
- MongoDB server has to be launched.
```bash
mongod
```

1. Clone the repository:
   ```bash
   git clone https://github.com/shytyrman/meppidoupkteiphhqnda.git
2. Open the root folder:
    ```
   cd meppidoupkteiphhqnda
3. Build the project:
    ```
   gradlew build -x test
   ```
   (Optional)Build the project with tests require *MongoDB* server run:
    ```
   mongod
   ```
    ```
   gradlew build
4. Go to the jar file folder:
    ```
   cd build/libs
5. Set environmental variables:
    ```
   set "SPRING_PROFILE=default" && set "POSTGRES_USER=postgres" && set "POSTGRES_PASSWORD=habargde" && set "PGHOST=localhost" && set "PGPORT=5432" && set "POSTGRES_DB=my_bpm" && set "PORT=8081" && set "MONGO_USER=mongo" && set "MONGO_PASSWORD=''" && set "MGHOST=localhost" && set "MGPORT=27017" && set "MONGO_DB=my_bpm"
   ```
   *I have used my own parameters, you have to change to yours and set them in this form. Full list of env variables are [here](#environment-variables).
6. Run the application:
    ```
   java -jar meppidoupkteiphhqnda-0.0.1-SNAPSHOT.jar
   ```
> **The application will be opened on port _8081_**

## Environment Variables

The following environment variables are used to configure the application:

| Variable            | Description                    | Default Value          |
|---------------------|--------------------------------|------------------------|
| `SPRING_PROFILE`     | Spring profile                 | `default`              |
| `POSTGRES_USER`      | PostgreSQL user                | `postgres`             |
| `POSTGRES_PASSWORD`  | PostgreSQL password            | `your_password`        |
| `PGHOST`             | PostgreSQL host                | `localhost`            |
| `PGPORT`             | PostgreSQL port                | `5432`                 |
| `POSTGRES_DB`        | PostgreSQL database name       | `your_database`        |
| `PORT`               | Application port               | `8081`                 |
| `MONGO_USER`         | MongoDB user                   | `mongo`                |
| `MONGO_PASSWORD`     | MongoDB password               |                        |
| `MGHOST`             | MongoDB host                   | `localhost`            |
| `MGPORT`             | MongoDB port                   | `27017`                |
| `MONGO_DB`           | MongoDB database name          | `your_mongo_database`  |

## Usage

### Requirements

- Postman 11.14.0

After running the application you can find "meppi.postman_collection.json".
Import it to your Postman. Then, inside you have 2 folders: docker and local 
for docker run and local run. Inside each of them 2 folders: mongo, postgres with
ready-to-use api calls that interacts with corresponding databases.

### API - Endpoints

PostgreSQL:
- /get (GET request)
- /update (POST request)
- /delete (DELETE request)
- /findAll (GET request)

MongoDB:
- /mongo/get (GET request)
- /mongo/update (POST request)
- /mongo/delete (DELETE request)
- /mongo/findAll (GET request)

All endpoints take parameters through RequestBody.

## */get* and */mongo/get*

Fetches the found record with id/phoneNumber.

PostgreSQL:
```json
   {
     "id": "<Long>",
     "phoneNumber": "<String>"
   }
```

MongoDb:
```json
{
  "id": "<String>", 
  "phoneNumber": "<String>"
}
```
First it will search for id in case there is no Entity with such id it will search by phoneNumber.

1) So, if you want search only with id the RequestBody has to be like this:
   1) PostgreSQL:
      ```json
      {
        "id": 13, 
        "phoneNumber": null 
      }
      ```

   2) MongoDb:
      ```json
      {
        "id": "66fa7ac325c39232777ac8e9", 
        "phoneNumber": null 
      }
      ```


2) Otherwise searching only with phoneNumber can be called: 
   1) PostgreSQL:
      ```json
      {
        "id": null, 
        "phoneNumber": "(435) 43 53"
      }
      ```

   2) MongoDb:
      ```json
      {
        "id": null, 
        "phoneNumber": "(253) 43 84"
      }
      ```
## */delete* and */mongo/delete*

Deletes the found record with id/phoneNumber.

PostgreSQL:
```json
   {
     "id": "<Long>",
     "phoneNumber": "<String>"
   }
```

MongoDb:
```json
{
  "id": "<String>", 
  "phoneNumber": "<String>"
}
```
First it will search for id in case there is no Entity with such id it will search by phoneNumber.

1) So, if you want search only with id the RequestBody has to be like this:
   1) PostgreSQL:
      ```json
      {
        "id": 13, 
        "phoneNumber": null 
      }
      ```

   2) MongoDb:
      ```json
      {
        "id": "66fa7ac325c39232777ac8e9", 
        "phoneNumber": null 
      }
      ```


2) Otherwise searching only with phoneNumber can be called:
   1) PostgreSQL:
      ```json
      {
        "id": null, 
        "phoneNumber": "(435) 43 53"
      }
      ```

   2) MongoDb:
      ```json
      {
        "id": null, 
        "phoneNumber": "(253) 43 84"
      }
      ```

## */update* and */mongo/update*

Updates the found record with id/phoneNumber. Can update all fields or only chosen ones.

PostgreSQL:
```json
   {
     "changeFullName": "<String>", //value to change name
     "changeBirthday": "<String>", //value to change birthday //yyyy-mm--dd
     "changePhoneNumber": "<String>", //value to change phone number
     "changePhoneNumberAdditional": "<String>", //value to change additional number
     "searchId": "<Long>", //id value to search record
     "searchPhoneNumber": "<String>" //phone number value to search record
   }
```

MongoDb:
```json
   {
      "changeFullName": "<String>", //value to change name
      "changeBirthday": "<String>", //value to change birthday //yyyy-mm--dd
      "changePhoneNumber": "<String>", //value to change phone number
      "changePhoneNumberAdditional": "<String>", //value to change additional number
      "searchId": "<String>", //id value to search record
      "searchPhoneNumber": "<String>" //phone number value to search record
   }
```
First it will search for id in case there is no Entity with such id it will search by phoneNumber.

1) So, if you want search only with id the RequestBody has to be like this:
   1) PostgreSQL:
      ```json
      {
         "changeFullName": "<String>", //value to change name
         "changeBirthday": "<String>", //value to change birthday //yyyy-mm--dd
         "changePhoneNumber": "<String>", //value to change phone number
         "changePhoneNumberAdditional": "<String>", //value to change additional number
         "searchId": 13, //id value to search record
         "searchPhoneNumber": null //phone number value to search record
      }
      ```

   2) MongoDb:
      ```json
      {
         "changeFullName": "<String>", //value to change name
         "changeBirthday": "<String>", //value to change birthday //yyyy-mm--dd
         "changePhoneNumber": "<String>", //value to change phone number
         "changePhoneNumberAdditional": "<String>", //value to change additional number
         "searchId": "66fa7ac325c39232777ac8e9", //id value to search record
         "searchPhoneNumber": null //phone number value to search record
      }
      ```


2) Otherwise searching only with phoneNumber can be called:
   1) PostgreSQL:
      ```json
      {
         "changeFullName": "<String>", //value to change name
         "changeBirthday": "<String>", //value to change birthday //yyyy-mm--dd
         "changePhoneNumber": "<String>", //value to change phone number
         "changePhoneNumberAdditional": "<String>", //value to change additional number
         "searchId": null, //id value to search record
         "searchPhoneNumber": "(123) 45 67"//phone number value to search record
      }
      ```

   2) MongoDb:
      ```json
      {
         "changeFullName": "<String>", //value to change name
         "changeBirthday": "<String>", //value to change birthday //yyyy-mm--dd
         "changePhoneNumber": "<String>", //value to change phone number
         "changePhoneNumberAdditional": "<String>", //value to change additional number
         "searchId": null, //id value to search record
         "searchPhoneNumber": "(123) 45 67"//phone number value to search record
      }
      ```
   If you want to change only specific fields and leave values in fields where you didn't specify you can use `null` as follow:

   ```json
   {
      "changeFullName": "<String>", //value to change name
      "changeBirthday": null, //save previous value of birthday //value to change birthday //yyyy-mm--dd
      "changePhoneNumber": null, //save previous value of phone number //value to change phone number
      "changePhoneNumberAdditional": "<String>", //value to change additional number
      "searchId": null, //id value to search record
      "searchPhoneNumber": "(123) 45 67"//phone number value to search record
   }
   ```
   This one will change only *FullName* and *PhoneNumberAdditional*.

## */findAll* and */mongo/findAll*

Fetches all records and sets limitation and offset.

PostgreSQL/MongoDB:
```json
   {
      "limit": "<Integer>", 
      "offset": "<Integer>" 
   }
```
*In MongoDB fields can not be `null`*