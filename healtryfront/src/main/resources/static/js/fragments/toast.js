
function mostrarToast(mensaje, error = false) {
	const toastEl = document.querySelector(".toast");
	const toastHeaderTitle = toastEl.querySelector(
		".toast-header .me-auto"
	);
	const toastHeader = toastEl.querySelector(".toast-header");

	toastHeaderTitle.textContent = error ? "Error" : "Éxito";
	toastHeader.classList.remove("text-bg-success", "text-bg-danger");
	toastHeader.classList.add(
		error ? "text-bg-danger" : "text-bg-success"
	);

	const toastBody = toastEl.querySelector(".toast-body span");
	toastBody.textContent = mensaje;

	const toast = new bootstrap.Toast(toastEl, { delay: 3000 });
	toast.show();
}