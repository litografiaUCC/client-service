# Client Service

This repository contains a microservice that offers a REST API for managing clients in a lithography company. Developed with Spring Boot, it follows best practices for microservices and RESTful API design. It provides CRUD operations to handle client information efficiently and securely.

## Endpoints

This section provides an overview of the available endpoints and their respective functionalities within the API.

<!-- Get All Clients -->
<details>
<summary><h3><span style="color:green">GET</span> Get all clients</h3></summary>

### Path: `host/api/v1/clients/`

This endpoints retrieves data of all clients registered.

### Response

- **`Status:`** 200, 204, 400

- **`Content-Type:`** application/json

### Response Body

The response body will contain the following fields:

- **`status:`** The status of the request.
- **`message:`** A message related to the request.
- **`data:`** Additional data related to the request.

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

- **`Status:`** 200, 400, 404

- **`Content-Type:`** application/json

### Response Body

The response body will contain the following fields:

- **`status:`** The status of the request.
- **`message:`** A message related to the request.
- **`data:`** Additional data related to the request.

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

- **`name:`** The first name of the client.

    ```json
        "type": "string",
        "required": true
    ```

- **`lastName:`** The last name of the client if is a person.

    ```json
        "type": "string",
        "required": false
    ```

- **`typePerson:`** The type of person (e.g., individual, organization).

    ```json
        "type": "string",
        "required": false
    ```

- **`email:`** The email address of the client.

    ```json
        "type": "string",
        "required": true
    ```

- **`password:`** The password for the client account.

    ```json
        "type": "string",
        "required": true
    ```

- **`photo`:** The profile photo of the client.

    ```json
        "type": "string",
        "required": false
    ```

- **`numberDocument:`** The document number of the client.

    ```json
        "type": "string",
        "required": true
    ```

- `typeDocument` (object, required): The type of document with its ID.

    ```json
        "required": true,
        "typeDocument": {
            "id": {
                "type": "integer",
                "required": true
            }
        }
    ```

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

- **`Status:`** 200, 400, 404, 409

- **`Content-Type:`** application/json

### Response Body

The response body will contain the following fields:

- **`status:`** The status of the request.
- **`message:`** A message related to the request.
- **`data:`** Additional data related to the request.

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

- **`Status:`** 200, 400, 404, 409

- **`Content-Type:`** application/json

### Response Body

The response body will contain the following fields:

- **`status:`** The status of the request.
- **`message:`** A message related to the request.
- **`data:`** Additional data related to the request.

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

This endpoint allows you to create a new client account.

### Request Body Info

- **`name:`** The id of the client.

    ```json
        "type": "int",
        "required": true
    ```

- **`name:`** The first name of the client.

    ```json
        "type": "string",
        "required": false
    ```

- **`lastName:`** The last name of the client if is a person.

    ```json
        "type": "string",
        "required": false
    ```

- **`typePerson:`** The type of person (e.g., individual, organization).

    ```json
        "type": "string",
        "required": false
    ```

- **`email:`** The email address of the client.

    ```json
        "type": "string",
        "required": false
    ```

- **`password:`** The password for the client account.

    ```json
        "type": "string",
        "required": false
    ```

- **`photo`:** The profile photo of the client.

    ```json
        "type": "string",
        "required": false
    ```

- **`numberDocument:`** The document number of the client.

    ```json
        "type": "string",
        "required": false
    ```

- `typeDocument` (object, required): The type of document with its ID.

    ```json
        "required": false,
        "typeDocument": {
            "id": {
                "type": "integer",
                "required": true
            }
        }
    ```

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

- **`Status:`** 200, 400, 404, 409

- **`Content-Type:`** application/json

### Response Body

The response body will contain the following fields:

- **`status:`** The status of the request.
- **`message:`** A message related to the request.
- **`data:`** Additional data related to the request.

### Example Response

```json
{
    "status": 200,
    "message": "",
    "data": null
}
```

</details>