
# Delete Device

Deletes a device by its ID.

**URL:** `/api/device/delete/{id}`

**Method:** `DELETE`

## Path Parameters

- `{id}`: The ID of the device to delete.

### Example

Request:

```
DELETE /api/device/delete/4
```

Response:

```json
{
    "id": 4,
    "name": "new_device"
}
```