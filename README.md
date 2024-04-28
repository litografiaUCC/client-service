# Client Service

This repository contains a microservice that offers a REST API for managing clients in a lithography company. Developed with Spring Boot, it follows best practices for microservices and RESTful API design. It provides CRUD operations to handle client information efficiently and securely.

## Execute

### Prerequisites

- Java Development Kit (JDK) 8 or higher installed on your system.

### Steps to Run the Service

1. Clone the Repository

    `git clone https://github.com/litografiaUCC/clients-service.git`

2. Navigate to the Project Directory

    `cd client-service`

3. Run the Spring Boot Application

    `./mvnw spring-boot:run`
   
   > **Note:** Before running the service, ensure you have a MySQL database set up. You can create a new database using your preferred MySQL management tool or by running appropriate SQL commands. Don't forget to edit the application properties with the datasource configuration according to your MySQL setup.

5. Access the API

    Once the service is up and running, you can access the API via <http://localhost:8080/>. To see the complete documentation with swagger, you can access via <http://localhost:8080/swagger-ui/index.html>

## Endpoints

This section provides an overview of the available endpoints and their respective functionalities within the API.

<!-- Get All Clients -->
<details>
<summary><h3><span style="color:green">GET</span> Get all clients</h3></summary>

### Path: `host/api/v1/clients/`

This endpoints retrieves data of all clients registered.

### Response

- **Status:** Response will have one of this status:
  |Code|Name|Description|
  |----|----|-----------|
  |**200**|OK|The request succeeded|
  |**204**|No Content|There is no content to send for this request, but the headers may be useful|
  |**400**|Bad Request|The server cannot or will not process the request due to something that is perceived to be a client error|

- **Content-Type:** application/json

### Response Body

The response body will contain the following fields:

|Name|Description|
|----|-----------|
|**status**|The status of the request|
|**message**|A message related to the request|
|**data**|Additional data related to the request|

### Example Response

```json
{
    "status": 200,
    "message": "ok",
    "data": [
        {
            "id": 0,
            "name": "",
            "lastName": "",
            "typePerson": "",
            "email": "",
            "password": "",
            "phone": null,
            "photo": "",
            "isActive": true,
            "numberDocument": "",
            "typeDocument": null
        }
    ]
}
```

</details>

<!-- Get Client By ID -->
<details>
<summary><h3><span style="color:green">GET</span> Get Client By Id</h3></summary>

### Path: `host/api/v1/clients/{id}`

This endpoint retrieves data for a specific client.

### Response

- **Status:** Response will have one of this status:
  |Code|Name|Description|
  |----|----|-----------|
  |**200**|OK|The request succeeded|
  |**400**|Bad Request|The server cannot or will not process the request due to something that is perceived to be a client error|
  |**404**|Not Found|The server cannot find the requested resource|

- **Content-Type:** application/json

### Response Body

The response body will contain the following fields:

|Name|Description|
|----|-----------|
|**status**|The status of the request|
|**message**|A message related to the request|
|**data**|Additional data related to the request|

### Example Response

```json
{
    "status": 0,
    "message": "",
    "data": [
        {
            "id": 0,
            "name": "",
            "lastName": "",
            "typePerson": "",
            "email": "",
            "password": "",
            "phone": null,
            "photo": "",
            "isActive": true,
            "numberDocument": "",
            "typeDocument": null
        }
    ]
}
```

</details>

<!-- Sign Up New Client -->
<details>
<summary><h3><span style="color:yellow">POST</span> Sign Up New Client</h3></summary>

### Path: `host/api/v1/clients/signup`

This endpoint allows you to create a new client account.

### Request Body Info

|Attribute|Description|Type|Required|
|---|---|---|---|
|**name**|The first name of the client|string|✔|
|**lastName**|The last name of the client if is a person|string|❌|
|**typePerson**|The type of person (e.g., individual, organization)|string|❌|
|**email**|The email address of the client|string|✔|
|**password**|The first name of the client|string|✔|
|**photo**|The path of profile photo of the client|string|❌|
|**phone**|The phone of the client|string|❌|
|**numberDocument**|The document number of the client|string|✔|
|**typeDocument**| The type of document with its ID|TypeDocument|✔|

#### Type Document Entity

Stucture of the type TypeDocument

|Attribute|Description|Type|Required|
|---|---|---|---|
|**id**|The id of the type document|int|✔|

### Body Example

```json
    {
        "name": "String",
        "lastName": null,
        "typePerson": "String",
        "email": "example@mail.com",
        "password": "password",
        "phone": "String", 
        "photo": null,
        "numberDocument": "String",
        "typeDocument": {
            "id": 1
        }
    }
```

### Response

- **`Status`** Response will have one of this status:
  |Code|Name|Description|
  |----|----|-----------|
  |**200**|OK|The request succeeded|
  |**400**|Bad Request|The server cannot or will not process the request due to something that is perceived to be a client error|
  |**409**|Conflict|This response is sent when a request conflicts with the current state of the server|

- **Content-Type:** application/json

### Response Body

The response body will contain the following fields:

|Name|Description|
|----|-----------|
|**status**|The status of the request|
|**message**|A message related to the request|
|**data**|Additional data related to the request|

### Example Response

```json
{
    "status": 200,
    "message": "",
    "data": null
}
```

</details>

<!-- Deactivate Client -->
<details>
<summary><h3><span style="color:purple">PATCH</span> DeactivateClient</h3></summary>

### Path: `host/api/v1/clients/{id}/desactivate`

This HTTP PATCH request is used to deactivate a specific item by its ID. The request should be made to the endpoint formed by concatenating the base URL, API version, service, and the ID of the item to be deactivated.

### Response

- **Status:** Response will have one of this status:
  |Code|Name|Description|
  |----|----|-----------|
  |**200**|OK|The request succeeded|
  |**400**|Bad Request|The server cannot or will not process the request due to something that is perceived to be a client error|
  |**404**|Not Found|The server cannot find the requested resource|
  |**409**|Conflict|This response is sent when a request conflicts with the current state of the server|

- **Content-Type:** application/json

### Response Body

The response body will contain the following fields:

|Name|Description|
|----|-----------|
|**status**|The status of the request|
|**message**|A message related to the request|
|**data**|Additional data related to the request|

### Example Response

```json
{
    "status": 0,
    "message": "",
    "data": null
}
```

</details>

<!-- Sign Up New Client -->
<details>
<summary><h3><span style="color:blue">PUT</span> Update Data Client</h3></summary>

### Path: `host/api/v1/clients/update`

This endpoint allows the client to update a specific client.

### Request Body Info

|Attribute|Description|Type|Required|
|---|---|---|---|
|**id**|The id of the client|int|✔|
|**name**|The first name of the client|string|❌|
|**lastName**|The last name of the client if is a person|string|❌|
|**typePerson**|The type of person (e.g., individual, organization)|string|❌|
|**email**|The email address of the client|string|❌|
|**password**|The first name of the client|string|❌|
|**photo**|The path of profile photo of the client|string|❌|
|**phone**|The phone of the client|string|❌|
|**numberDocument**|The document number of the client|string|❌|
|**typeDocument**| The type of document with its ID|TypeDocument|❌|

#### Type Document Entity

Stucture of the type TypeDocument

|Attribute|Description|Type|Required|
|---|---|---|---|
|**id**|The id of the type document|int|✔|

### Body Example

    ```json
    {
        "id": 1,
        "name": "String",
        "lastName": null,
        "typePerson": "String",
        "email": "example@mail.com",
        "password": "password",
        "phone": "String", 
        "photo": null,
        "numberDocument": "String",
        "typeDocument": {
            "id": 1
        }
    }
    ```

### Response

- **Status:** Response will have one of this status:
  |Code|Name|Description|
  |----|----|-----------|
  |**200**|OK|The request succeeded|
  |**400**|Bad Request|The server cannot or will not process the request due to something that is perceived to be a client error|
  |**404**|Not Found|The server cannot find the requested resource|
  |**409**|Conflict|This response is sent when a request conflicts with the current state of the server|

- **Content-Type:** application/json

### Response Body

The response body will contain the following fields:

|Name|Description|
|----|-----------|
|**status**|The status of the request|
|**message**|A message related to the request|
|**data**|Additional data related to the request|

### Example Response

```json
{
    "status": 200,
    "message": "",
    "data": null
}
```

</details>
