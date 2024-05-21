# Get Admin by ID

Retrieves a admin from the system by their ID.

**URL:** `/api/admin/{id}`

**Method:** `GET`

## Path Parameters

| Parameter | Type   | Required | Description               |
|-----------|--------|----------|---------------------------|
| `id`      | Long   | Yes      | The ID of the admin to retrieve. |

## Response

Response (HTTP Status 200 OK):
Returns the `AdminDto` object representing the admin with the specified ID.

Response (HTTP Status 404 Not Found):
Returns a 404 Not Found when the admin with the specified ID is not found.

| Field     | Type   | Description        |
|-----------|--------|--------------------|
| `id`      | Long   | The unique identifier of the admin. |
| `email`   | String | The admin's email address. |
| `password`| String | The admin's encrypted password. |

### Example

Request:
```
GET api/admin/1
```

Response (HTTP Status 200 OK):

```json
{
  "id": 1,
  "email": "admin1@example.com",
  "password": "encryptedPassword"
}
```