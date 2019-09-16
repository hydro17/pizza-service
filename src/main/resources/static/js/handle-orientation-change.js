function sendTouchEvent(x, y, element, eventType) {
  const touchObj = new Touch({
    identifier: Date.now(),
    target: element,
    clientX: x,
    clientY: y,
    radiusX: 2.5,
    radiusY: 2.5,
    rotationAngle: 10,
    force: 0.5,
  });

  const touchEvent = new TouchEvent(eventType, {
    cancelable: true,
    composed: false,
    bubbles: true,
    touches: [touchObj],
    targetTouches: [],
    changedTouches: [touchObj],
    shiftKey: true,
  });

  element.dispatchEvent(touchEvent);
}

const myElement = document.querySelector("table");

// sendTouchEvent(220, 200, myElement, 'touchmove');
// sendTouchEvent(220, 200, myElement, 'touchend');

const wait = (ms) =>
  new Promise((res) => {
    setTimeout(() => {
      res();
    }, ms);
  });

window.addEventListener("orientationchange", async () => {
  sendTouchEvent(150, 150, myElement, "touchstart");
  await wait(50);
  sendTouchEvent(150, 150, myElement, "touchend");
  await wait(50);
  sendTouchEvent(150, 150, myElement, "touchstart");
  await wait(50);
  sendTouchEvent(150, 150, myElement, "touchend");
});

// window.addEventListener("orientationchange", () => {
//   const tblContainer = document.querySelector(".tbl-container-for-responsiveness");
//   const containerWidth = tblContainer.getBoundingClientRect().width;
//   const zoomFactor = window.screen.width / containerWidth;
//   console.log("window.innerWidth: " + window.innerWidth);
//   console.log("tblContainer.scrollWidth: " + tblContainer.scrollWidth);
//   console.log("containerWidth: " + containerWidth);
//   console.log("window.screen.width: " + window.screen.width);
//   console.log("zoomFactor: " + zoomFactor);
//   document.body.style.zoom = zoomFactor;
//   document.body.style.zoom = 1;
//   document.body.style.zoom = zoomFactor;
// });

window.addEventListener("touchstart", (e) => {
  console.log("touchstart");
  console.log(e);
});

window.addEventListener("touchend", (e) => {
  console.log("touchend");
  console.log(e);
});

window.addEventListener("touchcancel", (e) => {
  console.log("touchcancel");
  console.log(e);
});

// window.addEventListener("scroll", () => {
//   document.body.style.zoom = 2;
//   document.body.style.backgroundColor = "red";
//   setTimeout(() => {
//     document.body.style.zoom = 1;
//     document.body.style.backgroundColor = null;
//   }, 2000);
// });
