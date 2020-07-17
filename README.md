# Car Factory - Test Developer
# The REST API
# Return All Cars
### Request

`GET /cars/`

For example:
	http://localhost:5000/cars

# Return Specific Car
### Request

`GET /cars/{id}`

http://localhost:5000/cars/{id}

For example using [cURL] you can list the details for a specific car with:

	$ curl http://localhost:5000/cars/2

This should produce output similar to this:

	{"id":2,"name":"auto 55 updated","variant":{"id":2,"name":"Familiar","price":245000.0},"equipments":[{"id":1,"name":"Techo Corredizo","price":12000.0},{"id":3,"name":"ABS","price":14000.0}],"price":271000.0,"_links":{"self":{"href":"http://localhost:5000/cars/2"},"cars":{"href":"http://localhost:5000/cars"}}}

# Delete a Car
### Request

`DELETE /cars/{id}`

    curl -X DELETE http://localhost:5000/cars/2

### Response

	HTTP/1.1 204 
	Date: Fri, 17 Jul 2020 19:18:52 GMT
	Keep-Alive: timeout=60
	Connection: keep-alive
