# Change Password

Changes the password for the admin associated with the provided email.

**URL:** `/api/admin/password`

**Method:** `POST`

## Request Body

The request body should be a JSON object representing the `email` and the `newPassword` of the admin.

| Parameter   | Type   | Required | Description                     |
|-------------|--------|----------|---------------------------------|
| `email`     | String | Yes      | The email of the admin.          |
| `newPassword`| String | Yes      | The new password for the admin.  |

## Response

Response (HTTP Status 200 OK):
Returns the `AdminDto` object that has been changed.

Response (HTTP Status 400 Bad Request):
Returns a 400 Bad Request when validation errors occur or the email is not found.

### Example
Request:
```
POST api/admin/password
```


Request Body:

```Json
{
  "email": "admin@example.com",
  "password": "newPlainTextPassword"
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
