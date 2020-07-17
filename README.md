# Car Factory - Test Developer
# The REST API
# Return All Cars

http://localhost:5000/cars

# Return Specific Car

http://localhost:5000/cars/{id}

For example using [cURL][] you can list the details for a specific car with:

	$ curl -u http://localhost:5000/cars/2

This should produce output similar to this:

	{"id":2,"name":"auto 55 updated","variant":{"id":2,"name":"Familiar","price":245000.0},"equipments":[{"id":1,"name":"Techo Corredizo","price":12000.0},{"id":3,"name":"ABS","price":14000.0}],"price":271000.0,"_links":{"self":{"href":"http://carfactory.us-east-1.elasticbeanstalk.com/cars/2"},"cars":{"href":"http://carfactory.us-east-1.elasticbeanstalk.com/cars"}}}
