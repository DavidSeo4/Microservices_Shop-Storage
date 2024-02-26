# SpringBoot Microservices with SpringCloud

This version of this project is designed to serve as the basis for a microservices project with the necessary basic elements and a couple of extra services (shop and storage) as an example of how it works.
It is more focused on the functionality of the technologies used than on the business logic or the complexity of the code.

This microservices project based on a shop-storage situation contains: 
- A Spring Cloud Config Server service to store common configurations on a GitHub repository.
- A Spring Cloud Gateway service to centralize calls to services, distribute them and add security
- An authentication and authorization service to secure the entire project.
- A service that represents a store and a service that represents the warehouse that supplies that store.
- A service Spring Cloud Netflix Eureka Naming Server that connects all the services of the project.

Only the warehouse table has stored products because of an import.sql file, so it is advisable to start the experience by creating an order so that the shop table also has products. 
The authentication data needed in order to create a JWT to access the endpoints is:

- For the EMPLOYEE role: {"username": "davide", "password": "Password123456"}
- For the EMPLOYER role: {"username": "alexei", "password": "Password123456"}

Both are added to the database when the Authentication service starts through an import.sql file.

### Endpoints

#### All endpoints are accessible with the EMPLOYEE or EMPLOYER role except adding products to the warehouse and viewing all orders placed from the store. Also, every request goes through the Api Gateway port.

- http://localhost:8080/auth/authenticate (POST):

Endpoint to authenticate users and generate a JWT token. Requires user credentials provided in the import.sql file.

- http://localhost:8080/orders/create (POST):

Access point to create an order for products from the store to the warehouse. Requests a list of products in the body of the request.

- http://localhost:8080/orders/getAll (GET):

Access point to all the orders created and their information.

- http://localhost:8080/storage/getProducts (GET):

Access point to all the products storage in the warehouse.

- http://localhost:8080/storage/extractProducts (POST):

Access point to get products saved in the warehouse in order to send them to the shop. Requests a list of products in the body of the request, and it is accessed automatically from the orders/create Endpoint through a Feign client.

- http://localhost:8080/storage/addProducts (POST):

Access point to add products to the storage in the warehouse. Requests a list of products in the body of the request.

- http://localhost:8080/shop/getProducts (GET):

Access point to all the products storage in the shop.

- http://localhost:8080/shop/getProductsByType (GET):

Access point to all the products by type saved in the shop. Requests a String in the body with the type of product requested.



### Technologies Used
- Spring Boot, Spring DataJpa, Validation
- Spring Security, JSON Web Token (JWT)
- MySQL
- Spring Cloud Config Server, Spring Cloud Gateway, Spring Cloud Netflix Eureka Naming Server
- OpenFeign, RestTemplate

### Future improvements: 

- Implement an appropriate exception system.
- Expand services with a service for store customers.
- Improve validations.
- Improve and clean the code.

### Contributors:
- David Seoane (https://github.com/DavidSeo4)
