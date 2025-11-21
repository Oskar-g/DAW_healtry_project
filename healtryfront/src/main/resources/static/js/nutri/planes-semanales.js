const planVacio = () => ({
    id: null,
    alias: null,
    dias: [],
    idNutricionista: null,
});
function planSemanalApp() {
	return {
		dias: ["Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"],
		todosLosTipos: ["Desayuno", "Almuerzo", "Comida", "Merienda", "Cena"],
		tiposComida: ["Desayuno", "Almuerzo", "Comida", "Merienda", "Cena"],

		plan: {}, //id, alias, dias, idNutricionista
		comidas: [],
		diaSeleccionado: null,
		tipoComidaSeleccionada: null,
		modalSeleccionarComida: new bootstrap.Modal(document.getElementById("modalSeleccionarComida")),

		modalConfigComidas: new bootstrap.Modal(document.getElementById("modalConfigComidas")),
		filtroComidas: "",

		modalPlanes: new bootstrap.Modal(document.getElementById("modalPlanes")),
		planes: [],

		modalConfirmarEliminar: new bootstrap.Modal(document.getElementById("modalConfirmarEliminar")),
		planAEliminar: null,

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
				async res => this.comidas = await res.json(),
				(err) => mostrarToast(err.message, true)
			);
		},

		cargarPlanes() {
			const idNutricionista = document.querySelector('[name="idNutricionista"]').value;

			apiSend(
				{ url: `${apiUrl}/planes-semanales/nutricionistas/${idNutricionista}` },
				async res => this.planes = await res.json(),
				err => mostrarToast(err.message, true)
			);
		},

		guardarPlan() {
			console.log(this.plan);
			const id = this.plan.id;
			const idNutricionista = document.querySelector('[name="idNutricionista"]').value;
			const alias = this.plan.alias;
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
				id,
				idNutricionista,
				alias,
				dias
			};

			const isEdicion = this.plan.id !== null;
			const method = isEdicion ? "PUT" : "POST";
			const url = isEdicion
				? `${apiUrl}/planes-semanales/${this.plan.id}`
				: `${apiUrl}/planes-semanales`;

			apiSend(
				{
					url,
					method,
					body: JSON.stringify(payload),
				},
				async res => {
					mostrarToast("Plan semanal guardado correctamente");
					this.editarPlan(await res.json())
				},
				(err) => mostrarToast(err.message, true)
			);
		},


		eliminarPlanConfirmado() {
			apiSend(
				{
					url: `${apiUrl}/planes-semanales/${this.planAEliminar.id}`,
					method: "DELETE"
				},
				async () => {
					mostrarToast("Plan eliminado correctamente");
					this.modalConfirmarEliminar.hide();
					this.planes = this.planes.filter(p => p.id !== this.planAEliminar.id);
				},
				err => mostrarToast(err.message, true)
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
		// Botonera superior
		// -----------------------------------------------
		nuevoPlan() {
			this.inicializarPlan();
			mostrarToast("Plan reiniciado");
		},

		guardarComoCopia() {
			this.plan.id = null;

			this.guardarPlan();
			mostrarToast("Plan guardado como copia");
		},

		editarPlan(plan, toast = false) {
			console.log('antes plan', plan);
			console.log('antes this.plan', this.plan);
			this.inicializarPlan();
			this.plan.id = plan.id;
			this.plan.alias = plan.alias;

			// reconstruir dias
			plan.dias.forEach(d => {
				this.plan[d.dia][d.tipoComida] = d.comida;
			});

			this.modalPlanes.hide();
			if (toast) {
				mostrarToast("Plan cargado para edición");
			}
			console.log('despues plan', plan);
			console.log('despues this.plan', this.plan);

		},

		// -----------------------------------------------
		// Modales
		// -----------------------------------------------

		abrirModalPlanes() {
			this.cargarPlanes();
			this.modalPlanes.show();
		},

		solicitarEliminarPlan(plan) {
			this.planAEliminar = plan;
			this.modalConfirmarEliminar.show();
		},

		// -----------------------------------------------
		// Selector Comidas
		// -----------------------------------------------

		abrirSelectorComida(dia, tipo) {
			this.diaSeleccionado = dia;
			this.tipoComidaSeleccionada = tipo;
			this.filtroComidas = "";
			this.modalSeleccionarComida.show();
		},

		comidasFiltradas() {
			const q = this.filtroComidas.toLowerCase().trim();
			return this.comidas.filter(c => c.nombre.toLowerCase().includes(q));
		},

		asignarComida(comida) {
			this.plan[this.diaSeleccionado][this.tipoComidaSeleccionada] = comida;
			this.modalSeleccionarComida.hide();
			mostrarToast(`Comida asignada al ${this.tipoComidaSeleccionada} del ${this.diaSeleccionado}`);
		},

		quitarComida(dia, tipo) {
			this.plan[dia][tipo] = null;
		},

		// -----------------------------------------------
		// Validaciones
		// -----------------------------------------------

		validPlan() {
			if (!this.plan) return false;

			if (!this.plan.alias || this.plan.alias.trim().length === 0) return false;

			const tieneComidas = this.dias.some(dia =>
				this.tiposComida.some(tipo => this.plan[dia][tipo])
			);

			if (!tieneComidas) return false;

			return true;
		}

	};
}
