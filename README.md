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

# Save a new Car
### Request

`POST /cars`

    curl -X POST http://localhost:5000/cars -d '{"name": "auto 4", "variant": { "id": 1, "name": "Coupe", "price": 270000 }, "equipments": [ { "id": 1, "name": "Techo Corredizo", "price": 12000 }, { "id": 3, "name": "ABS", "price": 14000 } ] }'

### Response

	HTTP/1.1 200 
	Content-Type: application/hal+json
	Transfer-Encoding: chunked
	Date: Fri, 17 Jul 2020 19:25:51 GMT
	Keep-Alive: timeout=60
	Connection: keep-alive

	{"id":2,"name":"auto 4","variant":{"id":1,"name":"Coupe","price":270000.0},"equipments":[{"id":1,"name":"Techo Corredizo","price":12000.0},{"id":3,"name":"ABS","price":14000.0}],"price":296000.0,"_links":{"self":{"href":"http://localhost:5000/cars/2"},"cars":{"href":"http://localhost:5000/cars"}}}
	
# Update a Car
### Request

`PUT /cars`

    curl -X PU	T http://localhost:5000/cars/2 -d '{"id": 2, "name": "auto 2 updated", "variant": { "id": 1, "name": "Coupe", "price": 270000 }, "equipments": [ { "id": 1, "name": "Techo Corredizo", "price": 12000 }, { "id": 3, "name": "ABS", "price": 14000 } ] }'

### Response

	HTTP/1.1 201 
	Server: nginx/1.16.1
	Date: Fri, 17 Jul 2020 19:30:05 GMT
	Content-Type: application/hal+json
	Transfer-Encoding: chunked
	Connection: keep-alive
	Location: http://carfactory.us-east-1.elasticbeanstalk.com/cars/2

	{"id":2,"name":"auto 2 updated","variant":{"id":2,"name":"Familiar","price":245000.0},"equipments":[{"id":3,"name":"ABS","price":14000.0},{"id":1,"name":"Techo Corredizo","price":12000.0}],"price":271000.0,"_links":{"self":{"href":"http://carfactory.us-east-1.elasticbeanstalk.com/cars/2"},"cars":{"href":"http://carfactory.us-east-1.elasticbeanstalk.com/cars"}}}
