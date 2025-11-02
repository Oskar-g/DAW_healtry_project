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
		modalParametro: null,
		modalEliminar: null,

		// ---------------------------------------
		// REST
		// ---------------------------------------

		cargarParametros() {
			apiSend(
				{
					url: `${baseUrl}/admin/configuraciones`,
				},
				async res => this.parametros = await res.json(),
				err => mostrarToast(err.message, true)
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
					this.cargarParametros();
					this.modalParametro.hide();
					mostrarToast(
						this.modoEdicion
							? "Parámetro actualizado correctamente"
							: "Parámetro creado con éxito"
					);
				},
				err => mostrarToast(err.message, true)
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
					this.modalParametro.hide();
					mostrarToast("Parámetro eliminado correctamente");
				},
				err => mostrarToast(err.message, true)
			)
		},

		// ---------------------------------------
		// MODALES
		// ---------------------------------------

		abrirModalNuevo() {
			this.parametro = parametroVacio;
			this.modoEdicion = false;
			this.modalParametro = new bootstrap.Modal(
				document.getElementById("modalParametro")
			);
			this.modalParametro.show();
		},

		abrirModalEditar(p) {
			this.parametro = { ...p };
			this.modoEdicion = true;
			this.modalParametro = new bootstrap.Modal(
				document.getElementById("modalParametro")
			);
			this.modalParametro.show();
		},

		confirmarEliminar(p) {
			this.parametro = { ...p };
			this.modalEliminar = new bootstrap.Modal(
				document.getElementById("modalEliminar")
			);
			this.modalEliminar.show();
		},
	}
}