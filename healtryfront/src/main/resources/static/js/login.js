function abrirModalRecuperacion() {
	new bootstrap.Modal(document.getElementById("modalRecuperar")).show();
}

function enviarCodigo() {
	let email = document.getElementById("rec_email").value;

	fetch(`${localUrl}/renovar-codigo`, {
		method: "POST",
		headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
		body: "email=" + encodeURIComponent(email)
	})
		.then(t => alert("Código enviado"))
		.catch(e =>alert("Error al enviar el código"));
}

function cambiarPass() {
	let email = document.getElementById("rec_email").value;
	let codigo = document.getElementById("rec_codigo").value;
	let pass = document.getElementById("rec_pass").value;

	fetch("/renovar-pass", {
		method: "POST",
		headers: { 'Content-Type': 'application/x-www-form-urlencoded' },
		body:
			"email=" + encodeURIComponent(email) +
			"&codigo=" + encodeURIComponent(codigo) +
			"&nuevaPass=" + encodeURIComponent(pass)
	})
		.then(r => r.text())
		.then(t => {
			if (t === "OK") {
				alert("Contraseña cambiada correctamente");
				location.reload();
			} else {
				alert(t);
			}
		});
}
