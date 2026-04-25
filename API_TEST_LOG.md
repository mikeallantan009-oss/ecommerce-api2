
# API Test Log

## 1. GET All Products
- Method: GET
- Endpoint: /api/v1/products
- Result: 200 OK
- Output: Returned list of all products successfully

---

## 2. GET Product by ID
- Method: GET
- Endpoint: /api/v1/products/1
- Result: 200 OK / 404 Not Found (depending on test)
- Output: Returned product details or not found message

---

## 3. CREATE Product
- Method: POST
- Endpoint: /api/v1/products
- Result: 201 Created
- Output: Product successfully created

---

## 4. UPDATE Product
- Method: PUT
- Endpoint: /api/v1/products/{id}
- Result: 200 OK / 404 Not Found
- Output: Product updated successfully

---

## 5. PATCH Product
- Method: PATCH
- Endpoint: /api/v1/products/{id}
- Result: 200 OK / 404 Not Found
- Output: Partial update successful

---

## 6. DELETE Product
- Method: DELETE
- Endpoint: /api/v1/products/{id}
- Result: 204 No Content / 404 Not Found
- Output: Product deleted successfully

---

## 7. VALIDATION TEST
- Method: POST
- Endpoint: /api/v1/products
- Result: 400 Bad Request
- Output: Validation errors triggered (invalid name, price, etc.)