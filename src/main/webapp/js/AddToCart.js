function addToCartClick(event) {
    event.preventDefault();

    const productName = event.target.getAttribute("productName");
    const category = event.target.getAttribute("category");
    const price = event.target.getAttribute("price");

    const data = {
        productName,
        category,
        price,
    };
    fetch('/ecommerce/addToCart', {
        headers: {
            'Content-Type': 'application/json'
        },
        method: 'post',
        body: JSON.stringify(data),
    })
    .then(response => response.json())
    .then(data => {
        console.log(data)
    })
    .catch(error => {
        // Handle any errors
    });
}