function deleteFromDb(event) {
    const id = event.target.getAttribute("id");

    // Show the confirmation modal
    const confirmationModal = document.getElementById("confirmationModal");
    confirmationModal.style.display = "flex";

    // Set up event listeners for confirm and cancel buttons
    const confirmButton = document.getElementById("confirmDelete");
    const cancelButton = document.getElementById("cancelDelete");

    confirmButton.addEventListener("click", () => {
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

        confirmationModal.style.display = "none";
    });

    cancelButton.addEventListener("click", () => {
        confirmationModal.style.display = "none";
        console.log('Deletion canceled by the user');
    });
}
