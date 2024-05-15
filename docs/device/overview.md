# Get All

Retrieves a list of all devices.

**URL:** `/api/device`

**Method:** `GET`

## Response

Returns a list of `DeviceDto` objects.

### Example

Request:

```
GET /api/device
```

Response:

```json
[
    {
        "id": 1,
        "name": "device1"
    },
    {
        "id": 2,
        "name": "device2"
    },
    {
        "id": 3,
        "name": "device3"
    }
]
```
