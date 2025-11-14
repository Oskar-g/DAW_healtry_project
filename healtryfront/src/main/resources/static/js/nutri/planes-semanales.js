const planVacio = () => ({
	id: null,
	alias: null,
	dias: [],
	nutricionistaId: document.querySelector('[name="nutricionistaId"]').value
});
function planSemanalApp() {
	return {
		dias: ["Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"],
		todosLosTipos: ["Desayuno", "Almuerzo", "Comida", "Merienda", "Cena"],
		tiposComida: ["Desayuno", "Almuerzo", "Comida", "Merienda", "Cena"],
		plan: {}, //id, alias, dias, nutricionistaId
		comidas: [],
		diaSeleccionado: null,
		tipoSeleccionado: null,
		modalSeleccionar: new bootstrap.Modal(document.getElementById("modalSeleccionarComida")),
		modalConfigComidas: new bootstrap.Modal(document.getElementById("modalConfigComidas")),
		filtro: "",

		init() {
			this.inicializarPlan();
			this.cargarComidas();
		},

		// -----------------------------------------------
		// API
		// -----------------------------------------------

		cargarComidas() {
			apiSend(
				{ url: `${apiUrl}/comidas` },
				async (res) => (this.comidas = await res.json()),
				(err) => mostrarToast(err.message, true)
			);
		},

		guardarPlan() {
			const dias = this.dias.flatMap(dia =>
				this.tiposComida
					.filter(tipo => this.plan[dia][tipo])
					.map(tipo => ({
						dia,
						tipoComida: tipo,
						comida: this.plan[dia][tipo],
					}))
			);

			const payload = {
				alias: this.plan.alias,
				dias
			};

			apiSend(
				{
					url: `${apiUrl}/planes-semanales`,
					method: "POST",
					body: JSON.stringify(payload),
				},
				async () => mostrarToast("Plan semanal guardado correctamente"),
				(err) => mostrarToast(err.message, true)
			);
		},

		// -----------------------------------------------
		// Generación semana
		// -----------------------------------------------

		inicializarPlan() {
			this.plan = { ...planVacio() };
			this.dias.forEach(dia => {
				this.plan[dia] = {};
				this.tiposComida.forEach(tipo => this.plan[dia][tipo] = null);
			});
		},

		caloriasDia(dia) {
			const comidasDia = this.tiposComida.map(tipo => this.plan[dia][tipo]).filter(Boolean);
			const total = comidasDia.reduce((suma, comida) => suma + (comida.kcal || 0), 0);
			return total.toFixed(0);
		},

		// -----------------------------------------------
		// Configuración dinámica de tipos de comidas
		// -----------------------------------------------

		abrirConfigComidas() {
			this.modalConfigComidas.show();
		},

		actualizarTiposComida() {
			// Aseguramos que las comidas eliminadas se limpien del plan
			this.dias.forEach(dia => {
				Object.keys(this.plan[dia]).forEach(tipo => {
					if (!this.tiposComida.includes(tipo)) {
						this.plan[dia][tipo] = null;
					}
				});
			});
			mostrarToast("Tipos de comidas actualizados");
		},


		// -----------------------------------------------
		// Selector Comidas
		// -----------------------------------------------

		abrirSelectorComida(dia, tipo) {
			this.diaSeleccionado = dia;
			this.tipoSeleccionado = tipo;
			this.filtro = "";
			this.modalSeleccionar.show();
		},

		comidasFiltradas() {
			const q = this.filtro.toLowerCase().trim();
			return this.comidas.filter(c => c.nombre.toLowerCase().includes(q));
		},

		asignarComida(comida) {
			this.plan[this.diaSeleccionado][this.tipoSeleccionado] = comida;
			this.modalSeleccionar.hide();
			mostrarToast(`Comida asignada al ${this.tipoSeleccionado} del ${this.diaSeleccionado}`);
		},

		quitarComida(dia, tipo) {
			this.plan[dia][tipo] = null;
		},
	};
}
