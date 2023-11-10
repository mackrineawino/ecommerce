function deleteFromDb(event) {
    const productId = event.target.getAttribute("productId");
    console.log("Deleting product with ID:", productId);

    fetch(`/ecommerce/addProduct?productId=${productId}`, {
        method: 'DELETE',
    })
    
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            console.log('Successfully deleted:', data);
            location.reload();
        })
        .catch(error => {
            console.error('Error during DELETE request:', error);
        });
}
