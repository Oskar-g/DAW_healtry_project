const dietaVacia = () => ({
	id: null,
	idPlan: null,
	idNutricionista: document.querySelector('[name="idNutricionista"]').value,
	clientes: new Set(),
	fechaInicio: null,
	fechaFin: null
});

function dietaApp() {
	return {
		// datos
		dietas: [],
		planes: [],
		clientes: [],

		// estados UI
		dieta: { ...dietaVacia() },
		isEdicion: false,
		dietaSeleccionada: null,

		// filtros / seleccion
		filtroClientes: "",
		clientesSeleccionados: new Set(),

		// modales 
		modalCrearDieta: new bootstrap.Modal(document.getElementById("modalCrearDieta")),
		modalConfirmarEliminar: new bootstrap.Modal(document.getElementById("modalConfirmarEliminarDieta")),

		init() {
			this.cargarPlanes();
			this.cargarClientes();
			this.cargarDietas();
		},

		// -------------------
		// API loaders
		// -------------------
		cargarPlanes() {
			const idNutricionista = document.querySelector('[name="idNutricionista"]').value;
			apiSend(
				{ url: `${apiUrl}/planes-semanales/nutricionistas/${idNutricionista}` },
				async res => this.planes = await res.json(),
				err => mostrarToast(err.message, true)
			);
		},

		cargarClientes() {
			const idNutricionista = document.querySelector('[name="idNutricionista"]').value;
			apiSend(
				{ url: `${apiUrl}/clientes/nutricionistas/${idNutricionista}` },
				async res => this.clientes = await res.json(),
				err => mostrarToast(err.message, true)
			);
		},

		cargarDietas() {
			const idNutricionista = document.querySelector('[name="idNutricionista"]').value;
			apiSend(
				{ url: `${apiUrl}/dietas/nutricionistas/${idNutricionista}` },
				async res => {
					const arr = await res.json();
					// normalizar: transformamos clientes (ids) a preview de nombres para la tabla
					this.dietas = arr.map(d => ({
						...d,
						clientesPreview: (d.clientes || []).slice(0, 5)
							.map(id => {
								const c = this.clientes.find(x => x.id === id);
								return c ? `${c.nombre} ${c.apellidos}` : String(id);
							})
					}));
				},
				err => mostrarToast(err.message, true)
			);
		},

		// -------------------
		// Crear / editar dieta
		// -------------------
		abrirModalCrear() {
			this.isEdicion = false;
			this.dieta = { ...dietaVacia() };
			this.clientesSeleccionados = new Set();
			this.modalCrearDieta.show();
		},

		abrirEditarDieta(dto) {
			this.isEdicion = true;
			this.dieta = {
				id: dto.id,
				idPlan: dto.idPlan,
				idNutricionista: dto.idNutricionista,
				fechaInicio: dto.fechaInicio,
				fechaFin: dto.fechaFin
			};
			this.clientesSeleccionados = new Set(dto.clientes || []);
			this.modalCrearDieta.show();
		},

		guardarDieta() {
			// validaciones simples
			if (!this.dieta.idPlan) return mostrarToast("Selecciona un plan", true);
			if (!this.dieta.fechaInicio || !this.dieta.fechaFin) return mostrarToast("Selecciona fechas válidas", true);
			if (new Date(this.dieta.fechaFin) < new Date(this.dieta.fechaInicio)) return mostrarToast("La fecha fin debe ser >= fecha inicio", true);
			if (!this.clientesSeleccionados.size) return mostrarToast("Selecciona al menos un cliente", true);

			const payload = {
				idPlan: this.dieta.idPlan,
				idNutricionista: this.dieta.idNutricionista,
				clientes: Array.from(this.clientesSeleccionados),
				fechaInicio: this.dieta.fechaInicio,
				fechaFin: this.dieta.fechaFin
			};

			if (this.isEdicion) {
				apiSend(
					{
						url: `${apiUrl}/dietas/${this.dieta.id}`,
						method: "PUT",
						body: JSON.stringify(payload)
					},
					async res => {
						mostrarToast("Dieta actualizada");
						this.modalCrearDieta.hide();
						this.cargarDietas();
					},
					err => mostrarToast(err.message, true)
				);
			} else {
				apiSend(
					{
						url: `${apiUrl}/dietas`,
						method: "POST",
						body: JSON.stringify(payload)
					},
					async res => {
						const created = await res.json();
						mostrarToast("Dieta creada");
						this.modalCrearDieta.hide();
						this.cargarDietas();
					},
					err => mostrarToast(err.message, true)
				);
			}
		},

		// -------------------
		// Eliminar dieta
		// -------------------
		confirmarEliminarDieta(dto) {
			this.dietaSeleccionada = dto;
			this.modalConfirmarEliminar.show();
		},

		eliminarDietaConfirmada() {
			if (!this.dietaSeleccionada?.id) return;
			apiSend(
				{
					url: `${apiUrl}/dietas/${this.dietaSeleccionada.id}`,
					method: "DELETE"
				},
				async () => {
					mostrarToast("Dieta eliminada");
					this.modalConfirmarEliminar.hide();
					this.cargarDietas();
				},
				err => mostrarToast(err.message, true)
			);
		},

		// -------------------
		// Clientes selection helpers
		// -------------------
		clientesFiltrados() {
			const q = (this.filtroClientes || "").toLowerCase().trim();
			if (!q) return this.clientes;
			return this.clientes.filter(c =>
				`${c.nombre} ${c.apellidos}`.toLowerCase().includes(q) ||
				(c.correo || "").toLowerCase().includes(q)
			);
		},

		toggleClienteSeleccionado(id) {
			if (this.clientesSeleccionados.has(id)) this.clientesSeleccionados.delete(id);
			else this.clientesSeleccionados.add(id);
			// force reactivity
			this.clientesSeleccionados = new Set(this.clientesSeleccionados);
		},

		toggleSeleccionTodos(checked) {
			if (checked) this.clientesSeleccionados = new Set(this.clientes.map(c => c.id));
			else this.clientesSeleccionados = new Set();
		},
		
		showInfo(){
			return this.dietas.length==0;
		},

		// -------------------
		// util
		// -------------------
		formatFecha(d) {
			if (!d) return "";
			// d may be "2025-11-20" (string) or Date
			const date = (typeof d === "string") ? new Date(d) : d;
			return date.toLocaleDateString();
		},

		nombrePlan(dieta) {
			return this.planes.find(p => p.id == dieta.idPlan)?.alias || `plan: ${dieta.idPlan}`;
		},
	};
}
