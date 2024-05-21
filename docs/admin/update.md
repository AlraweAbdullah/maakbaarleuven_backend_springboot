# Update Admin

Updates an existing admin in the system.

**URL:** `/api/admin/update/{id}`

**Method:** `PUT`

## Path Parameters

| Parameter | Type   | Required | Description               |
|-----------|--------|----------|---------------------------|
| `id`      | Long   | Yes      | The ID of the admin to update. |

## Request Body

The request body should be a JSON object representing the updated `AdminDto` of the admin.

| Field     | Type   | Required | Description        |
|-----------|--------|----------|--------------------|
| `email`   | String | Yes      | The updated admin's email address. |
| `password`| String | Yes      | The updated admin's password. |

## Response

Response (HTTP Status 200 OK):
Returns the `AdminDto` object that has been changed.

Response (HTTP Status 400 Bad Request):
Returnsa 400 Bad Request when validation errors occur.

Response (HTTP Status 404 Not Found):
Returns a 404 Not Found when the admin with the specified ID is not found.

### Example

Request:
```
PUT api/admin/update/1
```
Request Body:

```json
{
  "email": "updated.email@example.com",
  "password": "newPassword123"
}
```


Response (HTTP Status 200 OK):

```json
{
  "id": 1,
  "email": "updated.email@example.com",
  "password": "encryptedPassword"
}
```