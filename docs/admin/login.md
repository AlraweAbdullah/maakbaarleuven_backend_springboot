# Login

Logs in a admin with the provided email and password.

**URL:** `/api/admin/login`

**Method:** `POST`

## Request Body

The request body should be a JSON object representing the `email` and the `password` of the admin.

| Parameter | Type   | Required | Description               |
|-----------|--------|----------|---------------------------|
| `email`   | String | Yes      | The email of the admin.    |
| `password`| String | Yes      | The password of the admin. |

## Response

Response (HTTP Status 200 OK):
Returns the `AdminDto` object that has been logged in.

Response (HTTP Status 400 Bad Request):
Returnsa 400 Bad Request when login credentials are incorrect.

### Example
Request:
```
POST api/admin/login
```


Request Body:

```Json
{
"email": "john.doe@example.com",
"password": "password123"
}
```


Response (HTTP Status 200 OK):

```json
{
  "id": 1,
  "email": "admin@example.com",
  "password": "encryptedPassword"
}
```
