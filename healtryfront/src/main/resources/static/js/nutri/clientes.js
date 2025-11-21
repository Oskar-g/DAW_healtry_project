const clienteInfoVacio = () => ({
	fechaNacimiento: null,
	sexo: "H",
	alturaCm: null,
	pesoKg: null,
	idNutricionista: document.querySelector('[name="idNutricionista"]').value,
});
const clienteVacio = () => ({
	id: null,
	nombre: "",
	apellidos: "",
	correo: "",
	contrasena: "",
	rol: { id: null, nombre: "" },
	activo: true,
	clienteInfo: { ...clienteInfoVacio() }
});

function clientesApp() {
	return {
		erroresValidacion: {},

		roles: [],

		clientes: [],
		cliente: { ...clienteVacio() },
		modoEdicion: false,
		modalCliente: new bootstrap.Modal(document.getElementById("modalGuardarCliente")),
		modalEliminar: new bootstrap.Modal(document.getElementById("modalEliminarCliente")),

		init() {
			this.cargarRoles();
			this.cargarClientes();
		},

		// ---------------------------------------
		// REST
		// ---------------------------------------

		//ROLES
		cargarRoles() {
			apiSend(
				{ url: `${apiUrl}/roles` },
				//				async (res) => {
				//				    const roles = await res.json();
				//				    this.roles = roles.filter(e => e.nombre === 'CLIENTE');
				//				},
				async (res) => this.roles = [...await res.json()].filter(e => e.nombre === 'CLIENTE'),
				(err) => mostrarToast(err.message, true)
			);
		},

		//Clientes
		cargarClientes() {
			const idNutricionista = document.querySelector('[name="idNutricionista"]').value
			apiSend(
				{ url: `${apiUrl}/clientes/nutricionistas/${idNutricionista}` },
				async (res) => (this.clientes = await res.json()),
				(err) => mostrarToast(err.message, true)
			);
		},

		guardarCliente() {
			const errs = this.validarCliente();
			this.erroresValidacion = errs;

			if (Object.keys(errs).length > 0) return;

			const url = this.modoEdicion
				? `${apiUrl}/clientes/${this.cliente.id}`
				: `${apiUrl}/clientes`;

			const method = this.modoEdicion ? "PATCH" : "POST";
			const rol = this.roles[0];
			const cliente = { ...this.cliente, nutricionistaInfo: null, rol }

			apiSend(
				{
					url,
					method,
					body: JSON.stringify(cliente),
				},
				async () => {
					this.modalCliente.hide();
					mostrarToast(
						this.modoEdicion
							? "Cliente actualizado"
							: "Cliente creado"
					);
					this.cargarClientes();
				},
				(err) => mostrarToast(err.message, true)
			);
		},

		eliminarCliente() {
			apiSend(
				{
					url: `${apiUrl}/usuarios/${this.cliente.id}`,
					method: "DELETE",
				},
				async () => {
					this.modalEliminar.hide();
					mostrarToast("Cliente eliminado correctamente");
					this.cargarClientes();
				},
				err => mostrarToast(err.message, true)
			);
		},

		// ---------------------------------------
		// VALIDACIONES
		// ---------------------------------------

		validarCliente() {
			const errores = {};

			if (!this.cliente.nombre?.trim()) errores['nombre'] = "El nombre es obligatorio";
			if (!this.cliente.apellidos?.trim()) errores['apellidos'] = "Los apellidos son obligatorios";
			if (!this.cliente.correo?.trim()) errores['correo'] = "El correo es obligatorio";

			const info = this.cliente.clienteInfo;

			if (!info?.fechaNacimiento) errores['fechaNacimiento'] = "La fecha de nacimiento es obligatoria";

			if (!info?.sexo) errores['sexo'] = "Debe indicar el sexo";

			if (!info?.alturaCm || info.alturaCm <= 0) errores['alturaCm'] = "La altura debe ser mayor que 0";

			if (!info?.pesoKg || info.pesoKg <= 0) errores['pesoKg'] = "El peso debe ser mayor que 0";

			// Devuelvo lista de errores
			return errores;
		},

		// ---------------------------------------
		// MODALES
		// ---------------------------------------

		//USUARIOS
		abrirModalNuevo() {
			this.erroresValidacion = {};
			this.cliente = { ...clienteVacio() };
			this.modoEdicion = false;
			this.modalCliente.show();
		},

		abrirModalEditar(cliente) {
			this.erroresValidacion = {};

			this.cliente = JSON.parse(JSON.stringify(cliente));

			this.modoEdicion = true;
			this.modalCliente.show();
		},

		confirmarEliminar(cliente) {
			this.cliente = { ...cliente };
			this.modalEliminar.show();
		},

	};
}
