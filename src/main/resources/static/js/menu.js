            const toggleMenu = document.querySelector('.toggle')
            const showcase = document.querySelector('.showcase')

            toggleMenu.addEventListener('click', () => {
                toggleMenu.classList.toggle('active')
                showcase.classList.toggle('active')
            })