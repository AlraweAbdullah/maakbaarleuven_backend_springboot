# Get User by ID

Retrieves a user from the system by their ID.

**URL:** `/api/user/{id}`

**Method:** `GET`

## Path Parameters

| Parameter | Type   | Required | Description               |
|-----------|--------|----------|---------------------------|
| `id`      | Long   | Yes      | The ID of the user to retrieve. |

## Response

Returns the `UserDto` object representing the user with the specified ID.

| Field     | Type   | Description        |
|-----------|--------|--------------------|
| `id`      | Long   | The unique identifier of the user. |
| `name`    | String | The user's name.   |
| `lastname`| String | The user's last name. |
| `email`   | String | The user's email address. |

### Example

Request:
```
GET api/user/1
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