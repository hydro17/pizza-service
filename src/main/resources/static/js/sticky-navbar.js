const navbar = document.querySelector("header");
const distanceBetweenNavbarAndPageTop = navbar.offsetTop;

window.addEventListener("scroll", () => {
  if (window.pageYOffset >= distanceBetweenNavbarAndPageTop) {
    navbar.classList.add("sticky");
    document.body.classList.add("due-to-sticky-navbar");
  } else {
    navbar.classList.remove("sticky");
    document.body.classList.remove("due-to-sticky-navbar");
  }
});
