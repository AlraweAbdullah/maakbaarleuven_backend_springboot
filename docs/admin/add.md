# Add Admin

Adds a new admin to the system.

**URL:** `/api/admin/add`

**Method:** `POST`

## Request Body

The request body should be a JSON object representing the `AdminDto` of the admin to be added.

| Field      | Type   | Required | Description               |
|------------|--------|----------|---------------------------|
| `email`    | String | Yes      | The admin's email address. |
| `password` | String | Yes      | The admin's password.      |

### Response

Response (HTTP Status 201 Created):
Returns the `AdminDto` object that has been created.

Response (HTTP Status 400 Bad Request):
Returns a 400 Bad Request when validation errors occur.

### Example

Request:
```
POST api/admin/add
```

Request Body:
```json
{
  "email": "john.doe@example.com",
  "password": "secretpassword"
}
```
Response (HTTP Status 200 OK):

```json
{
  "id": 2,
  "name": "Admin2",
  "email": "admin2@example.com",
  "password": "encryptedPassword"
}
```

