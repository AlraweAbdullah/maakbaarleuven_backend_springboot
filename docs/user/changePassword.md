# Change Password

Changes the password for the user associated with the provided email.

**URL:** `/api/user/changePassword`

**Method:** `POST`

## Request Body

The request body should be a JSON object representing the `email` and the `newPassword` of the user.

| Parameter   | Type   | Required | Description                     |
|-------------|--------|----------|---------------------------------|
| `email`     | String | Yes      | The email of the user.          |
| `newPassword`| String | Yes      | The new password for the user.  |

### Example
Request:
```
POST api/user/changePassword
```


Request Body:

```Json
{
"email": "john.doe@example.com",
"password": "updatedPassword"
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
