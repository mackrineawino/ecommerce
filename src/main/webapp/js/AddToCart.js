function addToCartClick(event) {
    event.preventDefault();

    const addToCartButton = event.currentTarget; // Use currentTarget instead of target
const id = addToCartButton.getAttribute("id");
const imageUrl = addToCartButton.getAttribute("imageUrl");
const productName = addToCartButton.getAttribute("productName");
const category = addToCartButton.getAttribute("category");
const price = addToCartButton.getAttribute("price");

    
        const data = {
            id,
            imageUrl: imageUrl,
            productName: productName,
            category: category,
            price: price,
    };
console.log(data);
    fetch('/ecommerce/addToCart', {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'post',
        body: JSON.stringify(data),
    })
    .then(response => response.json())
    .then(data => {
        addToCartButton.textContent = 'ADDED';
        addToCartButton.innerHTML += ' &#10003;';
        addToCartButton.style.background = '#49A3C8';
        addToCartButton.disabled = true;

        updateCartCounter();
    })
    .catch(error => {
        console.error("Error:", error);
    });

    function updateCartCounter() {
        const currentCount = parseInt(localStorage.getItem('cartCount')) || 0;
    
        const cartCounter = document.getElementById('cart-counter');
        
        const newCount = currentCount + 1;
        cartCounter.innerText = newCount;
        cartCounter.style.display = 'inline-block';

        localStorage.setItem('cartCount', newCount);
    }
    
}