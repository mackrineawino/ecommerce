document.addEventListener("DOMContentLoaded", function() {
    function updateItem(event) {
        console.log("Inside updateItem function");
        const productId = event.target.getAttribute("productId");
        console.log("productId: " + productId);
        // ... rest of your code
        const updateForm = document.getElementById("updateForm");
        const productNameInput = document.getElementById("updateProductName");
        const categoryInput = document.getElementById("updateCategory");
        const priceInput = document.getElementById("updatePrice");

        var xhr = new XMLHttpRequest();
        xhr.onreadystatechange = function() {
            if (xhr.readyState === 4) {
                if (xhr.status === 200) {
                    try {
                        var productData = JSON.parse(xhr.responseText);

                        if (updateForm && productNameInput && categoryInput && priceInput) {
                            productNameInput.value = productData.productName;
                            categoryInput.value = productData.category;
                            priceInput.value = productData.price;
                            // Set other input field values if needed

                            updateForm.style.display = "block";
                        }
                    } catch (error) {
                        console.error("Error parsing JSON response: " + error);
                    }
                } else {
                    console.error("Request failed with status: " + xhr.status);
                    // Handle the error as needed
                }
            }
        };

        xhr.open("GET", "productData?productId=" + productId, true);
        xhr.send();
    }

    // Attach the updateItem function to an event, e.g., a button click.
});
