## Overview / Get All

Retrieves a list of all admins in the system.

**URL:** `/api/admin`

**Method:** `GET`

### Example

Request:
```
GET api/admin
```

Response (HTTP Status 200 OK):  

```json
[
  {
    "id": 1,
    "email": "admin1@example.com",
    "password": "encryptedPassword"
  },
  ...
]
```
