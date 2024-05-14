## Overview

Retrieves a list of all users in the system.

**URL:** `/api/user`

**Method:** `GET`

### Example

Request:
```
GET api/user
```

Response (HTTP Status 200 OK):  

```json
[
  {
    "id": 1,
    "name": "John",
    "lastname": "Doe",
    "email": "john.doe@example.com"
  },
  {
    "id": 2,
    "name": "Alice",
    "lastname": "Smith",
    "email": "alice.smith@example.com"
  }
]
```

