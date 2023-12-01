document.addEventListener('DOMContentLoaded', function () {
    let currentSlide = 0;
    const slides = document.querySelectorAll('.slider-row');
    const totalSlides = slides.length;

    const nextButton = document.getElementById('nextButton');
    const prevButton = document.getElementById('prevButton');

    // Add event listeners for manual navigation
    nextButton.addEventListener('click', function () {
        showSlide(currentSlide + 1);
    });

    prevButton.addEventListener('click', function () {
        showSlide(currentSlide - 1);
    });

    // Function to show a specific slide
    function showSlide(index) {
        slides[currentSlide].style.display = 'none';
        currentSlide = (index + totalSlides) % totalSlides;
        slides[currentSlide].style.display = 'block';
    }

    // Function to automatically advance to the next slide
    function autoAdvance() {
        showSlide(currentSlide + 1);
    }

    // Set up automatic slideshow with a 5-second interval (adjust as needed)
    const intervalId = setInterval(autoAdvance, 5000);

    // Pause automatic slideshow when mouse hovers over the slider
    document.querySelector('.slider-container').addEventListener('mouseenter', function () {
        clearInterval(intervalId);
    });

    // Resume automatic slideshow when mouse leaves the slider
    document.querySelector('.slider-container').addEventListener('mouseleave', function () {
        intervalId = setInterval(autoAdvance, 5000);
    });
});
