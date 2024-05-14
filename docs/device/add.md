
# Add Device

Adds a new device to the system.

**URL:** `/api/device/add`

**Method:** `POST`

## Request Body

```json
{
    "serial": "new_device"
}
```

### Example

Request:

```
POST /api/device/add

{
    "serial": "new_device"
}
```

Response:

```json
{
    "id": 4,
    "serial": "new_device"
}
```