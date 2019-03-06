### Start the project
To start project you should execute next commands:
- `mvn clean install`
- `docker-compose up`
- get to localhost:8181

### Swagger2
http://localhost:8181/swagger-ui.html

---
### JWT security
Page `(POST, PUT, DELETE) http://localhost:8181/` is secured. To access this page, you need to do the following:

* **POST** request to `http://localhost:8181/api/signup` with body
```json
  username: "user",
  password: "12345"
```
* **POST** request to `http://localhost:8181/api/auth`, then take token from responce and use it in header to access secured page
* **POST, PUT** request to `http://localhost:8181/members/*` with header:
```json
  x-auth-token: <your token here>
```
