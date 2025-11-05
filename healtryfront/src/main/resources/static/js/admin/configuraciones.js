const parametroVacio = {
	clave: "",
	valor: "",
	tipo: "",
	descripcion: ""
};
function parametrosApp() {
	return {
		parametros: [],
		parametro: parametroVacio,
		modoEdicion: false,
		modalGuardar: null,
		modalEliminar: null,

		// ---------------------------------------
		// REST
		// ---------------------------------------

		init() {
			this.cargarParametros();
		},

		cargarParametros() {
			apiSend(
				{
					url: `${baseUrl}/admin/configuraciones`,
				},
				async res => this.parametros = await res.json(),
				err => mostrarToast(err.message, true),
			);
		},

		guardarParametro() {
			apiSend(
				{
					url: `${baseUrl}/admin/configuraciones`,
					method: this.modoEdicion ? "PUT" : "POST",
					body: JSON.stringify(this.parametro),
				},
				async res => {
					this.modalGuardar.hide();
					mostrarToast(
						this.modoEdicion
							? "Parámetro actualizado correctamente"
							: "Parámetro creado con éxito"
					);
				},
				err => mostrarToast(err.message, true),
				() => this.cargarParametros(),
			);
		},

		eliminarParametro() {
			apiSend(
				{
					url: `${baseUrl}/admin/configuraciones/${this.parametro.clave}`,
					method: "DELETE",
				},
				async res => {
					this.cargarParametros();
					this.modalEliminar.hide();
					mostrarToast("Parámetro eliminado correctamente");
				},
				err => mostrarToast(err.message, true),
				() => this.cargarParametros(),
			)
		},

		// ---------------------------------------
		// MODALES
		// ---------------------------------------

		abrirModalNuevo() {
			this.parametro = parametroVacio;
			this.modoEdicion = false;
			this.modalGuardar = new bootstrap.Modal(
				document.getElementById("modalGuardarConfig")
			);
			this.modalGuardar.show();
		},

		abrirModalEditar(p) {
			this.parametro = { ...p };
			this.modoEdicion = true;
			this.modalGuardar = new bootstrap.Modal(
				document.getElementById("modalGuardarConfig")
			);
			this.modalGuardar.show();
		},

		confirmarEliminar(p) {
			this.parametro = { ...p };
			this.modalEliminar = new bootstrap.Modal(
				document.getElementById("modalEliminarConfig")
			);
			this.modalEliminar.show();
		},
	}
}