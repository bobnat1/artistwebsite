            const toggleMenu = document.querySelector('.toggle')
            const showcase = document.querySelector('.showcase')
            const showcaseTwo = document.querySelector('.showcase-two')

            toggleMenu.addEventListener('click', () => {
                toggleMenu.classList.toggle('active')
                showcase.classList.toggle('active')
                showcaseTwo.classList.toggle('active')
            })