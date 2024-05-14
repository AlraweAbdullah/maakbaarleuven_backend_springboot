# Login

Logs in a user with the provided email and password.

**URL:** `/api/user/login`

**Method:** `POST`

## Request Body

The request body should be a JSON object representing the `email` and the `password` of the user.

| Parameter | Type   | Required | Description               |
|-----------|--------|----------|---------------------------|
| `email`   | String | Yes      | The email of the user.    |
| `password`| String | Yes      | The password of the user. |

### Example
Request:
```
POST api/user/login
```


Request Body:

```Json
{
"email": "user@example.com",
"password": "password123"
}
```


Response (HTTP Status 200 OK):

```json
{
  "id": 1,
  "name": "UpdatedName",
  "lastname": "UpdatedLastName",
  "email": "updated.email@example.com",
}
```
