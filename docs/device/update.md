
# Update Device

Updates an existing device with new information.

**URL:** `/api/device/update/{id}`

**Method:** `PUT`

## Path Parameters

- `{id}`: The ID of the device to update.

## Request Body

```json
{
    "name": "updated_device"
}
```

### Example

Request:

```
PUT /api/device/update/4

{
    "name": "updated_device"
}
```

Response:

```json
{
    "id": 4,
    "name": "updated_device"
}
```
