            const toggleMenu = document.querySelector('.toggle')
            const showcase = document.querySelector('.showcase')
            const showcaseTwo = document.querySelector('.showcase-two')
            const menuInside = document.querySelector('.menu')

            toggleMenu.addEventListener('click', () => {
                toggleMenu.classList.toggle('active')
                showcase.classList.toggle('active')
                showcaseTwo.classList.toggle('active')
                menuInside.classList.toggle('active')
            })