const nutricionistaInfoVacio = () => ({
	especialidad: "",
	experienciaAnios: 0,
});

const clienteInfoVacio = () => ({
	fechaNacimiento: null,
	sexo: "H",
	alturaCm: null,
	pesoKg: null,
});
const usuarioVacio = () => ({
	id: null,
	nombre: "",
	apellidos: "",
	correo: "",
	contrasena: "",
	rol: { id: null, nombre: "" },
	activo: true,
	nutricionistaInfo: { ...nutricionistaInfoVacio() },
	clienteInfo: { ...clienteInfoVacio() }
});

function usuariosApp() {
	return {
		erroresValidacion: {},

		roles: [],

		usuarios: [],
		usuario: { ...usuarioVacio() },
		modoEdicion: false,
		modalUsuario: new bootstrap.Modal(document.getElementById("modalGuardarUsuario")),
		modalEliminar: new bootstrap.Modal(document.getElementById("modalEliminarUsuario")),

		nutricionistas: [],
		nutricionistaSeleccionado: { ...usuarioVacio() },
		filtroNutricionistas: "",
		modalNutricionistas: new bootstrap.Modal(document.getElementById("modalSeleccionarNutricionista")),

		init() {
			this.cargarRoles();
			this.cargarNutricionistas();
			this.cargarUsuarios();
		},

		// ---------------------------------------
		// REST
		// ---------------------------------------

		//ROLES
		cargarRoles() {
			apiSend(
				{ url: `${apiUrl}/roles` },
				async (res) => (this.roles = await res.json()),
				(err) => mostrarToast(err.message, true)
			);
		},

		// NUTRICIONISTAS

		cargarNutricionistas() {
			apiSend(
				{ url: `${apiUrl}/nutricionistas` },
				async res => this.nutricionistas = await res.json(),
				err => mostrarToast(err.message, true)
			);
		},


		// USUARIOS
		cargarUsuarios() {
			apiSend(
				{ url: `${apiUrl}/usuarios` },
				async (res) => (this.usuarios = await res.json()),
				(err) => mostrarToast(err.message, true)
			);
		},

		guardarUsuario() {
			const errs = this.validarUsuario();
			this.erroresValidacion = errs;

			if (Object.keys(errs).length > 0) return;

			const rol = this.roles.find(r => r.id === this.usuario.rol.id)?.nombre;

			if (rol === "NUTRICIONISTA") {
				return this.guardarNutricionista();
			} 
			if (rol === "CLIENTE") {
				return this.guardarCliente();
			} 
			if (rol === "ADMINISTRACION") {
				return this.guardarAdmin();
			} 
			
			mostrarToast("Rol no soportado", true);
			
		},

		guardarAdmin() {
			const url = this.modoEdicion
				? `${apiUrl}/usuarios/${this.usuario.id}`
				: `${apiUrl}/usuarios`;

			const method = this.modoEdicion ? "PATCH" : "POST";
			const admin = { ...this.usuario, clienteInfo: null,  nutricionistaInfo: null }

			apiSend(
				{
					url,
					method,
					body: JSON.stringify(admin),
				},
				async () => {
					this.modalUsuario.hide();
					mostrarToast(
						this.modoEdicion
							? "Administrador actualizado"
							: "Administrador creado"
					);
					this.cargarUsuarios();
				},
				(err) => mostrarToast(err.message, true)
			);
		},
		
		guardarNutricionista() {
			const url = this.modoEdicion
				? `${apiUrl}/nutricionistas/${this.usuario.id}`
				: `${apiUrl}/nutricionistas`;

			const method = this.modoEdicion ? "PATCH" : "POST";
			const nutricionista = { ...this.usuario, clienteInfo: null }

			apiSend(
				{
					url,
					method,
					body: JSON.stringify(nutricionista),
				},
				async () => {
					this.modalUsuario.hide();
					mostrarToast(
						this.modoEdicion
							? "Nutricionista actualizado"
							: "Nutricionista creado"
					);
					this.cargarUsuarios();
				},
				(err) => mostrarToast(err.message, true)
			);
		},

		guardarCliente() {
			const url = this.modoEdicion
				? `${apiUrl}/clientes/${this.usuario.id}`
				: `${apiUrl}/clientes`;

			const method = this.modoEdicion ? "PATCH" : "POST";
			const cliente = { ...this.usuario, nutricionistaInfo: null }

			apiSend(
				{
					url,
					method,
					body: JSON.stringify(cliente),
				},
				async () => {
					this.modalUsuario.hide();
					mostrarToast(
						this.modoEdicion
							? "Cliente actualizado"
							: "Cliente creado"
					);
					this.cargarUsuarios();
				},
				(err) => mostrarToast(err.message, true)
			);
		},

		eliminarUsuario() {
			apiSend(
				{
					url: `${apiUrl}/usuarios/${this.usuario.id}`,
					method: "DELETE",
				},
				async () => {
					this.modalEliminar.hide();
					mostrarToast("Usuario eliminado correctamente");
					this.cargarUsuarios();
				},
				err => mostrarToast(err.message, true)
			);
		},

		toggleActivo(usuario) {
			const nuevoEstado = !usuario.activo;
			apiSend(
				{
					url: `${apiUrl}/usuarios/${usuario.id}`,
					method: "PATCH",
					body: JSON.stringify({ activo: nuevoEstado }),
				},
				async (res) => {
					usuario.activo = nuevoEstado;
					mostrarToast(
						nuevoEstado
							? "Usuario activado correctamente"
							: "Usuario desactivado correctamente"
					);
				},
				(err) => mostrarToast(err.message, true),
				this.cargarUsuarios,
			);
		},

		// ---------------------------------------
		// VALIDACIONES
		// ---------------------------------------

		validarUsuario() {
			const errores = {};

			if (!this.usuario.nombre?.trim()) errores['nombre'] = "El nombre es obligatorio";
			if (!this.usuario.apellidos?.trim()) errores['apellidos'] = "Los apellidos son obligatorios";
			if (!this.usuario.correo?.trim()) errores['correo'] = "El correo es obligatorio";
			if (!this.usuario.rol?.id) errores['rol'] = "Debe seleccionar un rol";

			const rol = this.roles.find(r => r.id === this.usuario.rol.id)?.nombre;

			// Validación Nutricionista
			if (rol === "NUTRICIONISTA") {
				const info = this.usuario.nutricionistaInfo;

				if (!info?.especialidad?.trim())
					errores['especialidad'] = "La especialidad es obligatoria";

				if (info?.experienciaAnios < 0)
					errores['experienciaAnios'] = "La experiencia no puede ser negativa";
			}

			// Validación Cliente
			if (rol === "CLIENTE") {
				const info = this.usuario.clienteInfo;

				if (!info?.fechaNacimiento)
					errores['fechaNacimiento'] = "La fecha de nacimiento es obligatoria";

				if (!info?.sexo)
					errores['sexo'] = "Debe indicar el sexo";

				if (!info?.alturaCm || info.alturaCm <= 0)
					errores['alturaCm'] = "La altura debe ser mayor que 0";

				if (!info?.pesoKg || info.pesoKg <= 0)
					errores['pesoKg'] = "El peso debe ser mayor que 0";
			}

			// Devuelvo lista de errores
			return errores;
		},

		// ---------------------------------------
		// MODALES
		// ---------------------------------------

		//USUARIOS
		abrirModalNuevo() {
			this.erroresValidacion = {};
			this.usuario = { ...usuarioVacio() };
			this.modoEdicion = false;
			this.modalUsuario.show();
		},

		abrirModalEditar(usuario) {
			this.erroresValidacion = {};

			this.usuario = JSON.parse(JSON.stringify(usuario)); 

			if (!this.usuario.clienteInfo) this.usuario.clienteInfo = { ...clienteInfoVacio() };
			if (!this.usuario.nutricionistaInfo) this.usuario.nutricionistaInfo = { ...nutricionistaInfoVacio() };

			this.seleccionarNutricionista(usuario.clienteInfo?.idNutricionista)

			this.modoEdicion = true;
			this.modalUsuario.show();
		},

		confirmarEliminar(usuario) {
			this.usuario = { ...usuario };
			this.modalEliminar.show();
		},

		//NUTRICIONISTAS
		nutricionistasFiltrados() {
			const q = this.filtroNutricionistas.toLowerCase().trim();
			return this.nutricionistas.filter(n =>
				`${n.nombre} ${n.apellidos}`.toLowerCase().includes(q)
			);
		},

		abrirModalSeleccionarNutricionista() {
			this.cargarNutricionistas();
			this.modalNutricionistas.show();
		},

		seleccionarNutricionista(id) {
			if (!id) return;

			const nutricionista = this.nutricionistas.find(n => n.id === id);
			this.nutricionistaSeleccionado = nutricionista;
			this.usuario.clienteInfo.idNutricionista = id;

			this.modalNutricionistas.hide();
		},

	};
}
