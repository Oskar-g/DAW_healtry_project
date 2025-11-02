const usuarioVacio = {
	id: null,
	nombre: "",
	correo: "",
	rol: { id: null, nombre: "" },
	activo: true,
};
function usuariosApp() {
	return {
		usuarios: [],
		roles: [],
		usuario: usuarioVacio,
		modoEdicion: false,
		modalUsuario: null,
		modalEliminar: null,

		// ---------------------------------------
		// REST
		// ---------------------------------------

		init() {
			this.cargarRoles();
			this.cargarUsuarios();
		},

		cargarUsuarios() {
			apiSend(
				{ url: `${baseUrl}/admin/usuarios` },
				async (res) => (this.usuarios = await res.json()),
				(err) => mostrarToast(err.message, true)
			);
		},

		cargarRoles() {
			apiSend(
				{ url: `${baseUrl}/admin/roles` },
				async (res) => (this.roles = await res.json()),
				(err) => mostrarToast(err.message, true)
			);
		},

		guardarUsuario() {
			this.usuario.rol = this.roles.find(r => r.id === this.usuario.rol.id);
			apiSend(
				{
					url: `${baseUrl}/admin/usuarios`,
					method: this.modoEdicion ? "PUT" : "POST",
					body: JSON.stringify(this.usuario),
				},
				async (res) => {
					this.cargarUsuarios();
					this.modalUsuario.hide();
					mostrarToast(
						this.modoEdicion
							? "Usuario actualizado correctamente"
							: "Usuario creado con éxito"
					);
				},
				(err) => mostrarToast(err.message, true)
			);
		},
		
		toggleActivo(u) {
			const nuevoEstado = !u.activo;
			apiSend(
				{
					url: `${baseUrl}/admin/usuarios/${u.id}`,
					method: "PATCH",
					body: JSON.stringify({ activo: nuevoEstado }),
				},
				async (res) => {
					u.activo = nuevoEstado;
					mostrarToast(
						nuevoEstado
							? "Usuario activado correctamente"
							: "Usuario desactivado correctamente"
					);
				},
				(err) => mostrarToast(err.message, true)
			);
		},

		eliminarUsuario() {
			apiSend(
				{
					url: `${baseUrl}/admin/usuarios/${this.usuario.id}`,
					method: "DELETE",
				},
				async (res) => {
					this.cargarUsuarios();
					this.modalEliminar.hide();
					mostrarToast("Usuario eliminado correctamente");
				},
				(err) => mostrarToast(err.message, true)
			);
		},

		// ---------------------------------------
		// MODALES
		// ---------------------------------------

		abrirModalNuevo() {
			this.usuario = JSON.parse(JSON.stringify(usuarioVacio));
			this.modoEdicion = false;
			this.modalUsuario = new bootstrap.Modal(
				document.getElementById("modalUsuario")
			);
			this.modalUsuario.show();
		},

		abrirModalEditar(u) {
			this.usuario = JSON.parse(JSON.stringify(u));
			this.modoEdicion = true;
			this.modalUsuario = new bootstrap.Modal(
				document.getElementById("modalUsuario")
			);
			this.modalUsuario.show();
		},

		confirmarEliminar(u) {
			this.usuario = JSON.parse(JSON.stringify(u));
			this.modalEliminar = new bootstrap.Modal(
				document.getElementById("modalEliminar")
			);
			this.modalEliminar.show();
		},
	};
}
