# Locality (backend service)

#### API for Locality project. Made with Java Spring Boot, uses Mysql as a database.

List of endpoints:

### Auth
- **Sign Up**: POST `/api/v1/auth/signup`
```
{
    "username": "admin",
    "firstName": "admin",
    "lastName": "admin",
    "email": "admin@gmail.com",
    "password": "1234",
    "roles": "USER"
}
```
- **Sign In**: POST `/api/v1/auth/signin`
```
{
    "username": "admin",
    "password": "1234"
}
```

### Issues
- **Add New Issue**: POST `/api/issues`
```
{
    "title": "New Issue Title",
    "description": "Toilet is broken",
    "status": "active",
    "imageUrl": "http://blah.com",
    "userId": 1,
    "localityId": 1
}
```

- **Get All Issues**: GET `api/issues`, query params: `localityId, issueId, orderBy, limit, page, status`
- **Patch Issue**: PATCH `api/issues`,
```
{
    "issueId": 13,
    "key": "status",
    "value": "RESOLVED"
}
```

### Announcements
- **Add New Announcement**: POST `/api/announcements/`
```
{
    "title": "Api example",
    "description": "description text",
    "imageUrl": "http://cloudinary/1.jpg",
    "isPinned": false,
    "status": "ACTIVE",
    "userId": 2,
    "localityId": 1
}
```
- **Patch Announcement**: PATCH `/api/announcements`
```
{
    "entityId": 8,
    "key": "isPinned",
    "value": false
}
```
- **Get All Announcements**: GET `/api/announcements`, query params: `localityId, announcementId, limit, page, status, isPinned, searchText`

### Dashboard
- GET-requests to `/api/dashboard/issues`, `/api/dashboard/announcements`, `/api/dashboard/users` to aggregate statistics. Each of them accepts `localityId` as query param
