
# Add Device

Adds a new device to the system.

**URL:** `/api/device/add`

**Method:** `POST`

## Request Body

```json
{
    "name": "new_device"
}
```

### Example

Request:

```
POST /api/device/add

{
    "name": "new_device"
}
```

Response:

```json
{
    "id": 4,
    "name": "new_device"
}
```