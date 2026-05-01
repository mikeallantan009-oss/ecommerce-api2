/**
 * Fetch all products from backend API
 */
async function fetchProducts() {
    try {

        const response = await fetch("http://localhost:8080/api/v1/products");

        if (!response.ok) {

            if (response.status === 404) {
                throw new Error("Error 404: Products not found");
            }

            if (response.status === 500) {
                throw new Error("Error 500: Internal server error");
            }

            throw new Error("Unexpected error occurred while fetching products");
        }

        return await response.json();

    } catch (error) {

        console.error("FetchProducts Error:", error.message);

        return [];
    }
}


/**
 * Render products into the <main> container
 */
async function renderProducts() {

    const products = await fetchProducts();

    const container = document.getElementById("product-container");

    // Handle empty state
    if (products.length === 0) {
        container.innerHTML = "<p>No products available.</p>";
        return;
    }

    // Generate product grid HTML
    container.innerHTML = products.map(product => `
        <div class="product-card">
            <h2>${product.name}</h2>
            <p>${product.description ?? "No description available"}</p>
            <p><strong>Price:</strong> ₱${product.price}</p>
            <p><strong>Stock:</strong> ${product.stockQuantity}</p>
        </div>
    `).join("");

}


/**
 * Call renderProducts when page loads
 */
document.addEventListener("DOMContentLoaded", renderProducts);