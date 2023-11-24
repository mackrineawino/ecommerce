function deleteFromDb(event) {
    const id = event.target.getAttribute("id");
    console.log("Deleting product with ID:", id);

    fetch(`/ecommerce/addProduct?id=${id}`, {
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
