
# Delete Admin

Deletes a admin by its ID.

**URL:** `/api/admin/delete/{id}`

**Method:** `DELETE`

## Path Parameters

- `{id}`: The ID of the admin to delete.

## Response:

Response (HTTP Status 200 OK):
Returns the `AdminDto` object that has been deleted.

Response (HTTP Status 404 Not Found):
Returns a 404 Not Found when the admin is not found.


### Example

Request:

```
DELETE api/admin/delete/4
```

Response (HTTP Status 200 OK):

```json
{
  "id": 1,
  "email": "abdullah@gmail.com",
  "password": "$2a$10$nFkc777IenzmsPVF9VOJ/.CeMb3UdtNtHyXSXXwasXoOeIwmhSgCC"
}
```