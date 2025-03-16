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
 git clone https://github.com/Hariharan1893/Inventory-Service-with-Springboot-GraphQL.git
 cd Inventory-Service-with-Springboot-GraphQL
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
type Product{
	id: Int,
	name: String,
	category: String,
	price: Int,
	stock: Int
}

type Query{
	getAllProducts:[Product]
	getProductById(id:Int):Product
	getProductsWithPagination(limit: Int, offset: Int):[Product]
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

## 📞 Contact
For queries or support, feel free to reach out!

🚀 Happy Coding! 🎯