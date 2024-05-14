
# Delete User

Deletes a user by its ID.

**URL:** `/api/user/delete/{id}`

**Method:** `DELETE`

## Path Parameters

- `{id}`: The ID of the user to delete.

### Example

Request:

```
DELETE api/user/delete/4
```

Response (HTTP Status 200 OK):

```json
{
  "id": 1,
  "name": "Abdullah",
  "lastname": "Alrawe",
  "password": "$2a$10$nFkc777IenzmsPVF9VOJ/.CeMb3UdtNtHyXSXXwasXoOeIwmhSgCC",
  "email": "abdullah@gmail.com"
}
```