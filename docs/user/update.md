# Update User

Updates an existing user in the system.

**URL:** `/api/user/update/{id}`

**Method:** `PUT`

## Path Parameters

| Parameter | Type   | Required | Description               |
|-----------|--------|----------|---------------------------|
| `id`      | Long   | Yes      | The ID of the user to update. |

## Request Body

The request body should be a JSON object representing the updated `UserDto` of the user.

| Field     | Type   | Required | Description        |
|-----------|--------|----------|--------------------|
| `name`    | String | Yes      | The updated user's name.   |
| `lastname`| String | Yes      | The updated user's last name. |
| `email`   | String | Yes      | The updated user's email address. |
| `password`| String | Yes      | The updated user's password. |

### Example

Request:
```
PUT api/user/update/1
```
Request Body:

```json
{
  "name": "UpdatedName",
  "lastname": "UpdatedLastName",
  "email": "updated.email@example.com",
  "password": "newpassword123"
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