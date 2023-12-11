function checkout(totalPrice) {
    var stripe = Stripe('pk_test_51OGzlhGFDR8x86eFVSQr2mFCHNmLahJEQXjFNeNob8wzollbBjys7mQ8uBwbmaNjOWJrnWsLyo9bR2b6HOeWrxSM002WcnIaTj');
    var button = document.getElementById('checkoutButton');
    var spinner = document.getElementById('spinner');

    spinner.style.display = 'inline-block';
    button.disabled = true;

    fetch('/ecommerce/checkout?totalPrice=' + totalPrice, {
        method: 'POST',
    })
    .then(response => response.json())  // Assuming the response is JSON
    .then(data => {
        // Check if the data contains a valid sessionId
        if (data && data.sessionId) {
            return stripe.redirectToCheckout({ sessionId: data.sessionId });
        } else {
            throw new Error('Invalid session data');
        }
    })
    .then(result => {
        if (result.error) {
            alert(result.error.message);
        }
    })
    .catch(error => {
        console.error('Error:', error);
    })
    .finally(() => {
        spinner.style.display = 'none';
        button.disabled = false;
    });
}
