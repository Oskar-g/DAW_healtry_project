const comidaVacio = () => ({
	id: null,
	nombre: "",
	alimentos: [] // { {alimento}, gramos }
});
const selectorAlimentoVacio = () => ({
	gramos: 100,
	alimento: {},
	query: "",
	filtrados: [],
	mostrando: false
});

function comidasApp() {
	return {
		alimentos: [],
		selectorAlimentos: [],
		comidas: [],
		comida: { ...comidaVacio() },
		filtro: "",
		modoEdicion: false,
		modalAlimento: new bootstrap.Modal(document.getElementById("modalGuardarComida")),
		modalEliminar: new bootstrap.Modal(document.getElementById("modalEliminarComida")),

		init() {
			this.modalAlimento.hide();
			this.modalEliminar.hide();
			this.cargarAlimentos();
			this.cargarComidas();
		},

		// -----------------------------------------------
		// API
		// -----------------------------------------------

		cargarAlimentos() {
			apiSend(
				{ url: `${apiUrl}/alimentos` },
				async (res) => (this.alimentos = await res.json()),
				(err) => mostrarToast(err.message, true)
			);
		},

		cargarComidas() {
			apiSend(
				{ url: `${apiUrl}/comidas` },
				async (res) => (this.comidas = await res.json()),
				(err) => mostrarToast(err.message, true)
			);
		},

		guardarComida() {
			this.comida.alimentos = this.selectorAlimentos.map(sa => ({ ...sa.alimento, gramos: sa.gramos }));
			apiSend(
				{
					url: `${apiUrl}/comidas`,
					method: this.modoEdicion ? "PUT" : "POST",
					body: JSON.stringify(this.comida),
				},
				async (res) => {
					this.init();
					mostrarToast(this.modoEdicion ? "Comida actualizada" : "Comida creada");
				},
				(err) => mostrarToast(err.message, true)
			);
		},

		eliminarComida() {
			console.log(this.id)
			apiSend(
				{ url: `${apiUrl}/comidas/${this.comida.id}`, method: "DELETE" },
				async (res) => {
					this.init();
					mostrarToast("Comida eliminada correctamente");
				},
				(err) => mostrarToast(err.message, true)
			);
		},

		// filtra por nombre de comida o por el nombre de alguno de sus alimentos
		comidasFiltradas() {
			if (!this.filtro.trim()) return this.comidas;

			const f = this.filtro.toLowerCase();

			return this.comidas.filter(c =>
				c.nombre.toLowerCase().includes(f)
				||
				(c.alimentos && c.alimentos.some(a =>
					a.nombre.toLowerCase().includes(f)
				))
			);
		},

		// -----------------------------------------------
		// Selector Alimentos
		// -----------------------------------------------

		agregarAlimento() {
			this.selectorAlimentos.push({ ...selectorAlimentoVacio() });
		},

		eliminarAlimento(index) {
			this.selectorAlimentos.splice(index, 1);
		},

		filtrarAlimentos(selectorAlimento) {
			const q = selectorAlimento.query.toLowerCase().trim();
			const alimentosSeleccionados = this.selectorAlimentos.map(sa => sa.alimento.id)

			selectorAlimento.filtrados = q.length === 0
				? []
				: this.alimentos
					.filter(a => a.nombre.toLowerCase().includes(q))
					.filter(a => !alimentosSeleccionados.includes(a.id));
		},

		verificarSelectorValido(selectorAlimento, index) {
			setTimeout(() => {
				selectorAlimento.mostrando = false;

				// Si no hay alimento seleccionado o el texto no coincide con ninguno válido quitar el selector
				if (!selectorAlimento.alimento.id ||
					selectorAlimento.query.trim() !== selectorAlimento.alimento.nombre) {
					this.selectorAlimentos.splice(index, 1);
				}
			}, 500); // pequeño retraso para permitir clic en las sugerencias
		},

		seleccionarAlimento(selectorAlimento, alimento) {
			selectorAlimento.query = alimento.nombre;
			selectorAlimento.alimento = alimento
			selectorAlimento.mostrando = false;
		},

		caloriasCalculadas(selectorAlimento) {
			const alimento = selectorAlimento.alimento
			return selectorAlimento.gramos && alimento.kcal
				? alimento.kcal * (selectorAlimento.gramos / 100)
				: 0;
		},

		caloriasCalculadasTotal() {
			return this.selectorAlimentos.map(sa => this.caloriasCalculadas(sa))
				.reduce((total, kcal) => total + kcal, 0);
		},

		// -----------------------------------------------
		// MODALES
		// -----------------------------------------------

		abrirModalNuevo() {
			this.comida = { ...comidaVacio() };
			this.selectorAlimentos = [];
			this.modoEdicion = false;
			this.modalAlimento.show();
		},

		abrirModalEditar(comida) {
			this.comida = { ...comida };
			this.selectorAlimentos = comida.alimentos.map(a => ({
				...selectorAlimentoVacio(),
				gramos: a.gramos,
				alimento: a,
				query: a.nombre,
			}))
			this.modoEdicion = true;
			this.modalAlimento.show();
		},

		confirmarEliminar(comida) {
			this.comida = { ...comida };
			this.modalEliminar.show();
		},

	};
}
