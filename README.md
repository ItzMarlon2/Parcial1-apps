# Restaurant Order Management System

## 📌 Project Description
This is a RESTful API built using **Java 21**, **Spring Boot**, and **JPA** for managing restaurant orders, customers, products, and categories. The application provides full **CRUD operations** and integrates **Docker Compose** for database management using **MySQL** and **Adminer**.

## 📞 Technologies Used
- **Java 21**
- **Spring Boot**
- **Spring Data JPA**
- **MySQL**
- **Docker & Docker Compose**
- **Adminer** (Database Management UI)
- **Postman** (For API testing)

---

## ⚙️ Project Setup

### 👥 Clone the Repository
```sh
git clone https://github.com/your-repo.git
cd restaurant-order-management
```

### 🧑‍💻 Run the Database with Docker
Make sure **Docker** is installed and running.

```sh
docker-compose up -d
```

This will start:
- **MySQL** database on `port 5500`
- **Adminer** on `http://localhost:8000`

### 🏗️ Build and Run the Spring Boot Application
Use **Maven** to install dependencies and run the project.

```sh
mvn clean install
mvn spring-boot:run
```

The application runs on **port 4500**.

---

## 👤 Project Structure

```
📂 src/main/java/com/restaurante/gestionpedidos
 └📂 controllers         # REST API Controllers
 └📂 models              # Entity Models
 └📂 repositories        # JPA Repositories
 └📂 services            # Business Logic Layer
 └📂 config              # Database and Application Configurations
```

---

## 🛠️ API Endpoints

### 📌 Customers
| Method | Endpoint | Description |
|--------|---------|-------------|
| `GET`  | `/customers` | Get all customers |
| `GET`  | `/customers/{id}?includeOrders=true` | Get customer by ID (optional orders) |
| `POST` | `/customers` | Create a new customer |
| `PUT`  | `/customers/{id}` | Update a customer |
| `DELETE` | `/customers/{id}` | Delete a customer |

### 📌 Orders
| Method | Endpoint | Description |
|--------|---------|-------------|
| `GET`  | `/orders` | Get all orders |
| `GET`  | `/orders/{id}` | Get order by ID |
| `POST` | `/orders` | Create a new order |
| `PUT`  | `/orders/{id}` | Update an order |
| `DELETE` | `/orders/{id}` | Delete an order |

### 📌 Order Items
| Method | Endpoint | Description |
|--------|---------|-------------|
| `GET`  | `/order-items` | Get all order items |
| `GET`  | `/order-items/{id}` | Get order item by ID |
| `POST` | `/order-items` | Create an order item |
| `PUT`  | `/order-items/{id}` | Update an order item |
| `DELETE` | `/order-items/{id}` | Delete an order item |

### 📌 Products
| Method | Endpoint | Description |
|--------|---------|-------------|
| `GET`  | `/products` | Get all products |
| `GET`  | `/products/{id}?includeCategory=true` | Get product by ID (optional category) |
| `POST` | `/products` | Create a new product |
| `PUT`  | `/products/{id}` | Update a product |
| `DELETE` | `/products/{id}` | Delete a product |

### 📌 Categories
| Method | Endpoint | Description |
|--------|---------|-------------|
| `GET`  | `/categories` | Get all categories |
| `GET`  | `/categories/{id}` | Get category by ID |
| `POST` | `/categories` | Create a new category |
| `PUT`  | `/categories/{id}` | Update a category |
| `DELETE` | `/categories/{id}` | Delete a category |

---

## 📝 Example API Requests (Postman)

### ✅ Create a Customer
#### `POST /customers`
```json
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "phone": "123456789"
}
```

### ✅ Create a Category
#### `POST /categories`
```json
{
  "name": "Pizzas"
}
```

### ✅ Create a Product
#### `POST /products`
```json
{
  "name": "Margherita Pizza",
  "price": 10.99,
  "description": "Classic pizza with tomato and cheese",
  "category": { "id": 1 }
}
```

### ✅ Create a Order
#### `POST /orders`
```json
{
  "customer": {
    "id": 2
  }
}
```

### ✅ Create a Order-Item
#### `POST /order-items`
```json
{
  "order": {
    "id": 6
  },
  "product": {
    "id": 1
  },
  "quantity": 4
}
```

---


## ❓ Troubleshooting

### 🔹 Database Connection Issues
1. Ensure Docker is running.
2. Restart the database:
   ```sh
   docker-compose down
   docker-compose up -d
   ```
3. Check if MySQL is listening on port `5500`.

### 🔹 Adminer Not Working
If Adminer doesn't open at `http://localhost:8000`, try:
```sh
docker-compose restart adminer
```

---

## 👥 Contributors
- **Marlon Andrés Campo Amórtegui** - 240220231004

---


