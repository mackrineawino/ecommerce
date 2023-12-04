document.addEventListener("DOMContentLoaded", function () {
    // Variables
    const slider = document.querySelector('.slider');
    const prevButton = document.getElementById('prevButton');
    const nextButton = document.getElementById('nextButton');
    let count = 0;
    const cardsPerSlide = 4;

    // Event listeners
    if (slider && prevButton && nextButton) {
        prevButton.addEventListener('click', function () {
            count -= cardsPerSlide;
            if (count < 0) {
                count = 0;
            }
            updateSlider();
        });

        nextButton.addEventListener('click', function () {
            count += cardsPerSlide;
            if (count >= imageUrls.length) {
                count = imageUrls.length - cardsPerSlide;
            }
            updateSlider();
        });
    }

    // Update slider position
    function updateSlider() {
        if (slider) {
            const translateValue = -count * 25 + '%'; // Adjust as per your slide width
            slider.style.transform = 'translateX(' + translateValue + ')';
        }
    }
});
