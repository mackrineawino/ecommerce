function removeItem(event) {
    const id = event.target.getAttribute("id");
    console.log("Deleting product with ID:", id);

    fetch(`/ecommerce/addToCart?id=${id}`, {
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
        updateCartCounter(-1);
        console.log('Counter updated. Reloading page...');
        location.reload();
    })
    .catch(error => {
        console.error('Error during DELETE request:', error);
    });

    function updateCartCounter(change) {
        const currentCount = parseInt(localStorage.getItem('cartCount')) || 0;
        const cartCounter = document.getElementById('cart-counter');
        
        const newCount = Math.max(0, currentCount + change);
        console.log('Updating counter. New count:', newCount);
        
        cartCounter.innerText = newCount;
        
        if (newCount > 0) {
            cartCounter.style.display = 'inline-block';
        } else {
            cartCounter.style.display = 'none';
        }

        localStorage.setItem('cartCount', newCount);
        console.log('Counter updated in localStorage.');
    }

}
function logout() {
    
    localStorage.removeItem('cartCount');
}
