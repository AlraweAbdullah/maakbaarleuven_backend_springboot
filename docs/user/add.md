# Add User

Adds a new user to the system.

**URL:** `/api/user/add`

**Method:** `POST`

## Request Body

The request body should be a JSON object representing the `UserDto` of the user to be added.

| Field      | Type   | Required | Description               |
|------------|--------|----------|---------------------------|
| `name`     | String | Yes      | The user's name.          |
| `lastname` | String | Yes      | The user's last name.     |
| `email`    | String | Yes      | The user's email address. |
| `password` | String | Yes      | The user's password.      |
| `houseNr`  | String | No       | The user's house nuber.   |


### Example

Request:
```
POST api/user/add
```

Request Body:
```json
{
  "name": "John",
  "lastname": "Doe",
  "email": "john.doe@example.com",
  "password": "secretpassword"
}
```
Response (HTTP Status 200 OK):

```json
{
  "id": 1,
  "name": "John",
  "lastname": "Doe",
  "email": "john.doe@example.com"
}
```

