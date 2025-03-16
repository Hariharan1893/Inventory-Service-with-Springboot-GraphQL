# GraphQL Product API

## Overview
This API provides GraphQL-based CRUD operations for managing products, including support for **queries, mutations, and pagination**.

## Features
- **Get all products** with pagination support (offset-based pagination)
- **Get a product by ID**
- **Create a new product**
- **Update an existing product**
- **Delete a product**

---

## Installation & Setup

### **1️⃣ Clone the Repository**
```sh
 git clone https://github.com/your-repo/graphql-product-api.git
 cd graphql-product-api
```

### **2️⃣ Build & Run**
```sh
mvn clean install
mvn spring-boot:run
```

The API will be accessible at:
```
http://localhost:8080/graphql
```

---

## GraphQL Schema

```graphql
type Product {
    id: Int
    name: String
    category: String
    price: Int
    stock: Int
}

type Query {
    getAllProducts(limit: Int, offset: Int): [Product]
    getProductById(id: Int): Product
}

type Mutation {
    createProduct(name: String, category: String, price: Int, stock: Int): Product
    updateProduct(id: Int, name: String, category: String, price: Int, stock: Int): Product
    deleteProduct(id: Int): Boolean
}
```

---

## API Endpoints

### **1️⃣ Get All Products (With Pagination)**
#### **GraphQL Query**
```graphql
query {
    getAllProducts(limit: 5, offset: 0) {
        id
        name
        category
        price
        stock
    }
}
```
#### **Response**
```json
{
    "data": {
        "getAllProducts": [
            {
                "id": 1,
                "name": "Smartwatch",
                "category": "Wearable",
                "price": 8999,
                "stock": 25
            },
            ...
        ]
    }
}
```

### **2️⃣ Get Product by ID**
#### **GraphQL Query**
```graphql
query {
    getProductById(id: 1) {
        id
        name
        category
        price
        stock
    }
}
```

### **3️⃣ Create a Product**
#### **GraphQL Mutation**
```graphql
mutation {
    createProduct(name: "VR Headset", category: "Gaming", price: 12999, stock: 10) {
        id
        name
    }
}
```

### **4️⃣ Update a Product**
#### **GraphQL Mutation**
```graphql
mutation {
    updateProduct(id: 1, name: "Smartwatch Pro", category: "Wearable", price: 9999, stock: 30) {
        id
        name
    }
}
```

### **5️⃣ Delete a Product**
#### **GraphQL Mutation**
```graphql
mutation {
    deleteProduct(id: 1)
}
```

---

## Backend Implementation

### **1️⃣ Repository Layer (`ProductRepository.java`)**
```java
@Repository
public interface ProductRepository extends JpaRepository<Product, Integer> {
    @Query(value = "SELECT * FROM product LIMIT :limit OFFSET :offset", nativeQuery = true)
    List<Product> findProductsWithPagination(@Param("limit") int limit, @Param("offset") int offset);
}
```

### **2️⃣ Service Layer (`ProductService.java`)**
```java
@Service
public class ProductService {
    @Autowired
    private ProductRepository productRepository;

    public List<Product> getAllProducts(int limit, int offset) {
        return productRepository.findProductsWithPagination(limit, offset);
    }
}
```

### **3️⃣ Controller Layer (`ProductController.java`)**
```java
@GraphQLController
public class ProductController {
    @Autowired
    private ProductService productService;

    @QueryMapping
    public List<Product> getAllProducts(@Argument int limit, @Argument int offset) {
        return productService.getAllProducts(limit, offset);
    }
}
```

---

## Pagination Approaches

### **1️⃣ Offset-Based Pagination**
- Uses `LIMIT` and `OFFSET` to fetch a subset of records.
- Example:
```sql
SELECT * FROM product LIMIT 10 OFFSET 20;
```

### **2️⃣ Cursor-Based Pagination (Future Enhancement)**
- Uses a **cursor** (e.g., `id` or timestamp) to fetch records after a given point.
- More efficient than offset-based pagination for large datasets.
- Example:
```graphql
query {
    getAllProducts(afterCursor: "12345", limit: 10) {
        id
        name
    }
}
```

---

## Database Setup
```sql
CREATE TABLE Product (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(255) NOT NULL,
    category VARCHAR(100) NOT NULL,
    price INT NOT NULL,
    stock INT NOT NULL
);
```

---

## 📌 Notes
- **Offset-based pagination** is used for fetching paginated product data.
- **GraphQL Schema** follows best practices with queries and mutations.
- **Spring Boot + GraphQL** is used for seamless API management.

---

## 📌 Future Enhancements
- Implement **cursor-based pagination** for better performance.
- Add **search and filtering** features.
- Introduce **sorting options**.

---

## 📞 Contact
For queries or support, feel free to reach out!

🚀 Happy Coding! 🎯