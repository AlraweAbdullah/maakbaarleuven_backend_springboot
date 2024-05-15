# Get Device
Retrieves a specific device by its ID.

**URL:** `/api/device/{id}`

**Method:** `GET`

## Path Parameters

- `{id}`: The ID of the device.

### Example

Request:

```
GET /api/device/1
```

Response:

```json
{
    "id": 1,
    "name": "device1"
}
```