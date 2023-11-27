function checkout(totalPrice) {
    var stripe = Stripe('pk_test_51OGzlhGFDR8x86eFVSQr2mFCHNmLahJEQXjFNeNob8wzollbBjys7mQ8uBwbmaNjOWJrnWsLyo9bR2b6HOeWrxSM002WcnIaTj');
    var button = document.getElementById('checkoutButton');
    var spinner = document.getElementById('spinner');

    // Show spinner
    spinner.style.display = 'inline-block';
    button.disabled = true; // Disable the button to prevent multiple clicks

    // Call your server to create a Checkout session
    fetch('/ecommerce/checkout?totalPrice=' + totalPrice, {
        method: 'POST',
    })
    .then(response => response.json())
    .then(session => {
        // Redirect to Stripe Checkout
        stripe.redirectToCheckout({ sessionId: session.sessionId })
        .then(result => {
            if (result.error) {
                alert(result.error.message);
            }
        })
        .catch(error => {
            console.error('Error:', error);
        })
        .finally(() => {
            // Hide spinner and re-enable the button when the checkout process is complete
            spinner.style.display = 'none';
            button.disabled = false;
        });
    })
    .catch(error => {
        console.error('Error:', error);
        // Handle error if needed
        // Hide spinner and re-enable the button in case of an error
        spinner.style.display = 'none';
        button.disabled = false;
    });
}
