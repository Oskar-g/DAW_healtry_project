const comidaVacio = () => ({
	id: null,
	nombre: "",
	alimentos: [] // { {alimento}, gramos }
});
function dietaClienteApp() {
	return {

		dias: ["Lunes", "Martes", "Miércoles", "Jueves", "Viernes", "Sábado", "Domingo"],
		tiposComida: ["Desayuno", "Almuerzo", "Comida", "Merienda", "Cena"],

		// tabla: dieta[dia][tipo] = { nombre, kcal, alimentos: [{id,nombre,gramos,kcal}, ...] }
		dieta: {},

		// modales / estado modal comida
		modalComida: new bootstrap.Modal(document.getElementById("modalComida")),
		modalComidaTitulo: "",
		comidaActual: null,

		// modal lista compra
		modalListaCompra: new bootstrap.Modal(document.getElementById("modalListaCompra")),
		listaCompra: [],
		mostrarTodos: false,

		init() {

			// cargar dieta y después generar la lista (importante: carga asíncrona)
			this.cargarDietaActual();
		},

		// ----------------------------------------------------
		// API
		// ----------------------------------------------------
		async cargarDietaActual() {
			const idCliente = document.querySelector('[name="idCliente"]').value;

			apiSend(
				{ url: `${apiUrl}/planes-semanales/actual/clientes/${idCliente}` },
				async res => {
					try {
						const data = await res.json();
						// convertir a formato tabla defensivamente
						this.dieta = this.convertirAFormatoTabla(data || []);
						// generar lista de compra una vez tengamos la dieta
						this.generarListaCompra();
						// cargar marcas desde localStorage
						this.cargarComprados();
					} catch (e) {
						mostrarToast("Error parseando dieta", true);
						this.inicializarTablaVacia();
					}
				},
				err => {
					mostrarToast(err.message, true);
					this.inicializarTablaVacia();
				}
			);
		},
		
		existeDieta(dia, tipo){
			return this.dieta[dia]?.[tipo]?.alimentos?.length;
		},

		inicializarTablaVacia() {
			// crea estructura vacía para evitar errores si no hay dieta
			const tabla = {};
			this.dias.forEach(d => {
				tabla[d] = {};
				this.tiposComida.forEach(t => tabla[d][t] = { ...comidaVacio() });
			});
			this.dieta = tabla;
		},

		convertirAFormatoTabla(data) {

			this.inicializarTablaVacia();

			const tabla = { ...this.dieta };

			if (!Array.isArray(data.dias)) return tabla;

			data.dias.forEach(entry => {
				const dia = entry.dia;
				const tipo = entry.tipoComida;
				if (!dia || !tipo) return;
				if (!tabla[dia]) return;
				tabla[dia][tipo] = entry.comida || { ...comidaVacio() };
			});

			return this.dieta;
		},

		// ----------------------------------------------------
		// Abrir/visualizar comida
		// ----------------------------------------------------
		abrirComida(dia, tipo) {

			const comida = this.dieta[dia]?.[tipo]?.alimentos?.length
				? this.dieta[dia][tipo]
				: null;
			this.comidaActual = comida;
			this.modalComidaTitulo = `${tipo} — ${dia}`;
			this.modalComida.show();
		},

		abrirComidaActual() {
			const ahora = new Date();
			const dayIdx = (ahora.getDay() + 6) % 7; // mapea 0(Sun)->6, 1(Mon)->0, ...
			const diaSemana = this.dias[dayIdx];

			const hora = ahora.getHours();

			// reglas de ejemplo (ajusta si quieres otro mapeo)
			let tipo = "Desayuno";
			if (hora >= 11 && hora < 14) tipo = "Almuerzo";
			else if (hora >= 14 && hora < 17) tipo = "Comida";
			else if (hora >= 17 && hora < 20) tipo = "Merienda";
			else if (hora >= 20 || hora < 6) tipo = "Cena";

			this.abrirComida(diaSemana, tipo);
		},

		// ----------------------------------------------------
		// Resaltar celda actual
		// ----------------------------------------------------
		resaltarCelda(dia, tipo) {
			try {
				const ahora = new Date();
				const dayIdx = (ahora.getDay() + 6) % 7;
				const diaSemana = this.dias[dayIdx];
				if (dia !== diaSemana) return "";

				const hora = ahora.getHours();
				let tipoActual = "Desayuno";
				if (hora >= 11 && hora < 14) tipoActual = "Almuerzo";
				else if (hora >= 14 && hora < 17) tipoActual = "Comida";
				else if (hora >= 17 && hora < 20) tipoActual = "Merienda";
				else if (hora >= 20 || hora < 6) tipoActual = "Cena";

				if (tipo === tipoActual) return "table-warning shadow";
				return "";
			} catch (e) {
				return "";
			}
		},

		// ----------------------------------------------------
		// LISTA DE LA COMPRA (modal)
		// ----------------------------------------------------
		abrirListaCompra() {
			// regeneramos la lista por si la dieta ha cambiado
			this.generarListaCompra();
			// sincronizamos marcas guardadas
			this.cargarComprados();
			// aseguramos que se muestra acorde a la preferencia
			// mostrarTodos queda tal cual (el checkbox en el modal controla)
			this.modalListaCompra.show();
		},

		generarListaCompra() {
			// defensivo: si dieta no está inicializada la función sale
			if (!this.dieta || typeof this.dieta !== "object") {
				this.listaCompra = [];
				return;
			}

			const items = this.dias
				.flatMap(dia => this.tiposComida.flatMap(tipo => {
					const comida = (this.dieta[dia] || {})[tipo];
					return comida && Array.isArray(comida.alimentos) ? comida.alimentos : [];
				}))
				.reduce((acc, a) => {
					if (!a || !a.nombre) return acc;
					const key = a.nombre.trim();
					acc[key] = acc[key]
						? { ...acc[key], gramos: acc[key].gramos + (a.gramos || 0) }
						: { nombre: key, marcado: false, gramos: a.gramos || 0 };
					return acc;
				}, {});


			this.listaCompra = Object.values(items).sort((x, y) => x.nombre.localeCompare(y.nombre));
		},

		filtrarListaCompra() {
			return this.listaCompra.filter(i => this.mostrarTodos || !i.marcado);
		},

		toggleComprado(nombre) {
			const item = this.listaCompra.find(i => i.nombre === nombre);
			if (!item) return;

			item.marcado = !item.marcado;
			this.guardarComprados()

		},

		reestablecerCompra() {
			this.listaCompra.forEach(item => { item.marcado = false; });
			this.guardarComprados();
		},

		// ----------------------------------------------------
		// Persistencia local (localStorage)
		// ----------------------------------------------------
		cargarComprados() {
			try {
				const raw = localStorage.getItem("listaCompraMarcados");
				if (!raw) return;
				const marcados = JSON.parse(raw);
				if (!Array.isArray(marcados)) return;
				this.listaCompra.forEach(i => {
					i.marcado = marcados.includes(i.nombre);
				});
			} catch (e) {
				console.warn("No se pudieron cargar marcas lista compra", e);
			}
		},

		guardarComprados() {
			try {
				const marcados = this.listaCompra.filter(i => i.marcado).map(i => i.nombre);
				localStorage.setItem("listaCompraMarcados", JSON.stringify(marcados));
			} catch (e) {
				console.warn("No se pudieron guardar marcas lista compra", e);
			}
		}

	};
}
