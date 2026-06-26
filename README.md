# Video Game Store API

## Description of the Project

The Video Game Store API is a Spring Boot REST application that simulates the backend of an online video game store. It
allows users to browse products and categories, manage shopping carts, authenticate with secure login, and maintain
their user profiles. Administrators can also manage product categories through protected endpoints.

The application was developed to demonstrate RESTful API design, layered architecture, database integration with Spring
Data JPA, authentication and authorization using Spring Security and JWT, and unit testing with JUnit and Mockito.

Interacts with front end integrated with backend.

---

## User Stories

### Bug Fixes

- Fixed Bug #1: Search results now display all matching products instead of only featured products.
- Fixed Bug #2: Product updates now correctly save inventory stock changes.

### Categories

- As a user, I want to view all categories so that I can browse available product categories.
- As an administrator, I want to create a category so that new product categories can be added to the store.
- As an administrator, I want to update category information so that category details remain accurate.
- As an administrator, I want to delete a category so that outdated categories can be removed.
- As a user, I want to retrieve a specific category by its ID so that I can view its details.

### Products

- As a user, I want to browse products by category so that I can find games and accessories that interest me.
- As a user, I want to search for products using filters so that I can quickly find what I'm looking for.

### Shopping Cart

- As a logged-in user, I want to view my shopping cart so that I can see the products I've added.
- As a logged-in user, I want to add products to my shopping cart so that I can purchase them later.
- As a logged-in user, I want to update product quantities in my shopping cart so that I can change the amount I wish to
  purchase.
- As a logged-in user, I want to empty my shopping cart so that I can start over.

### User Profile

- As a logged-in user, I want to view my profile information so that I can verify my personal details.
- As a logged-in user, I want to update my profile so that my contact information remains current.

---

## Prerequisites

- IntelliJ IDEA
- Java 17 Amazon Corretto  
- MySQL Server
- Maven

---

## Running the Application in IntelliJ

1. Clone or download the repository.
2. Open the project in IntelliJ IDEA.
3. Configure the MySQL database and execute the provided SQL setup script.
4. Update the database connection information in `application.properties` if necessary.
5. Allow Maven to download project dependencies.
6. Run the Spring Boot application. (ECommerceApplication)
7. Use Insomnia to test the REST endpoints, or launch the provided frontend.

---

## Technologies Used

- Java
- Spring Boot
- Spring Web
- Spring Data JPA
- Spring Security
- JWT Authentication
- MySQL
- Maven
- JUnit 6
- Mockito
- IntelliJ IDEA

---

## Demo

![GIF1](https://i.imgur.com/ezfT6E6.gif)
![GIF2](https://i.imgur.com/0KU6zoy.gif)
![GIF3](https://i.imgur.com/noePaot.gif)

---

## Future Work
- Improved search and sorting options
- Checkout and payment processing
- Further Testing

---

## Resources

- [Student Handbook 9: Building REST APIs with Spring
Boot](https://yearup.brightspace.com/d2l/le/enhancedSequenceViewer/11441?url=https%3A%2F%2Fa8aef0e2-4090-467b-bc7e-c872d64ba733.sequences.api.brightspace.com%2F11441%2Factivity%2F551039%3FfilterOnDatesAndDepth%3D1)


- [GPT Conversation @Transactional](https://chatgpt.com/s/t_6a3d8718b1188191ad31dd5216d6357b)


---

## Thanks

- Thank you, Raymond, for insight throughout every capstone.
- Thanks to all my classmates for the support and advice.