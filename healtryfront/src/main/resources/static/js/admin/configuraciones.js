const configuracionVacio = {
	clave: "",
	valor: "",
	tipo: "",
	descripcion: ""
};
function configuracionesApp() {
	return {
		configuraciones: [],
		configuracion: { ...configuracionVacio },
		modoEdicion: false,
		modalGuardar: new bootstrap.Modal(document.getElementById("modalGuardarConfig")),
		modalEliminar: new bootstrap.Modal(document.getElementById("modalEliminarConfig")),

		init() {
			this.cargarConfiguraciones();
		},

		// ---------------------------------------
		// REST
		// ---------------------------------------

		cargarConfiguraciones() {
			apiSend(
				{
					url: `${apiUrl}/configuraciones`,
				},
				async res => this.configuraciones = await res.json(),
				err => mostrarToast(err.message, true),
			);
		},

		guardarConfiguracion() {
			apiSend(
				{
					url: `${apiUrl}/configuraciones`,
					method: this.modoEdicion ? "PUT" : "POST",
					body: JSON.stringify(this.configuracion),
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
				() => this.cargarConfiguraciones(),
			);
		},

		eliminarConfiguracion() {
			apiSend(
				{
					url: `${apiUrl}/configuraciones/${this.configuracion.clave}`,
					method: "DELETE",
				},
				async res => {
					this.cargarConfiguraciones();
					this.modalEliminar.hide();
					mostrarToast("Parámetro eliminado correctamente");
				},
				err => mostrarToast(err.message, true),
				() => this.cargarConfiguraciones(),
			)
		},

		// ---------------------------------------
		// MODALES
		// ---------------------------------------

		abrirModalNuevo() {
			this.configuracion = configuracionVacio;
			this.modoEdicion = false;
			this.modalGuardar.show();
		},

		abrirModalEditar(configuracion) {
			this.configuracion = { ...configuracion };
			this.modoEdicion = true;
			this.modalGuardar.show();
		},

		confirmarEliminar(configuracion) {
			this.configuracion = { ...configuracion };
			this.modalEliminar.show();
		},
	}
}