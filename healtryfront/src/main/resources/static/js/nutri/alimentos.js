const alimentoVacio = {
	id: null,
	nombre: "",
	proteinas: 0,
	grasas: 0,
	carbohidratos: 0,
};

function alimentosApp() {
	return {
		alimentos: [],
		alimento: { ...alimentoVacio },
		filtro: "",
		modoEdicion: false,
		modalAlimento: new bootstrap.Modal(document.getElementById("modalGuardarAlimento")),
		modalEliminar: new bootstrap.Modal(document.getElementById("modalEliminarAlimento")),

		init() {
			this.cargarAlimentos();
		},

		// ---------------------------------------
		// REST
		// ---------------------------------------

		cargarAlimentos() {
			apiSend(
				{ url: `${apiUrl}/alimentos` },
				async (res) => (this.alimentos = await res.json()),
				(err) => mostrarToast(err.message, true)
			);
		},

		guardarAlimento() {
			apiSend(
				{
					url: `${apiUrl}/alimentos`,
					method: this.modoEdicion ? "PUT" : "POST",
					body: JSON.stringify(this.alimento),
				},
				async (res) => {
					this.cargarAlimentos();
					this.modalAlimento.hide();
					mostrarToast(
						this.modoEdicion
							? "Alimento actualizado correctamente"
							: "Alimento creado con éxito"
					);
				},
				(err) => mostrarToast(err.message, true)
			);
		},

		eliminarAlimento() {
			apiSend(
				{
					url: `${apiUrl}/alimentos/${this.alimento.id}`,
					method: "DELETE",
				},
				async (res) => {
					this.modalEliminar.hide();
					mostrarToast("Alimento eliminado correctamente");
					this.cargarAlimentos();
				},
				(err) => mostrarToast(err.message, true)
			);
		},

		alimentosFiltrados() {
			if (!this.filtro.trim()) return this.alimentos;
			return this.alimentos.filter(a =>
				a.nombre.toLowerCase().includes(this.filtro.toLowerCase())
			);
		},

		// ---------------------------------------
		// MODALES
		// ---------------------------------------

		abrirModalNuevo() {
			this.alimento = { ...alimentoVacio };
			this.modoEdicion = false;
			this.modalAlimento.show();
		},

		abrirModalEditar(alimento) {
			this.alimento = { ...alimento };
			this.modoEdicion = true;
			this.modalAlimento.show();
		},

		confirmarEliminar(alimento) {
			this.alimento = { ...alimento };
			this.modalEliminar.show();
		},
	};
}
