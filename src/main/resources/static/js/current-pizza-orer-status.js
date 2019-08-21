
const table = document.querySelector("table");

table.addEventListener("click", (e)=>{

  // if ((e.target.parentNode.tagName !== "A") 
  //   && (e.target.parentNode.innerHTML !== '<i class="far fa-edit" aria-hidden="true"></i>')) return;
  
  if (!e.target.classList.contains("fa-edit")) return;

  let node = e.target;
  
  while (node.tagName !== 'TR') {
	  node = node.parentNode;
  }
  
  if (node.querySelectorAll("td")[1].innerHTML === "ORDERED") return; 
  
  alert("Status is not ORDERD!");
  
  e.preventDefault();
});
