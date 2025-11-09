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
		usuario: { ...usuarioVacio },
		modoEdicion: false,
		modalUsuario: new bootstrap.Modal(document.getElementById("modalGuardarUsuario")),
		modalEliminar: new bootstrap.Modal(document.getElementById("modalEliminarUsuario")),

		init() {
			this.cargarRoles();
			this.cargarUsuarios();
		},

		// ---------------------------------------
		// REST
		// ---------------------------------------

		cargarUsuarios() {
			apiSend(
				{ url: `${apiUrl}/usuarios` },
				async (res) => (this.usuarios = await res.json()),
				(err) => mostrarToast(err.message, true)
			);
		},

		cargarRoles() {
			apiSend(
				{ url: `${apiUrl}/roles` },
				async (res) => (this.roles = await res.json()),
				(err) => mostrarToast(err.message, true)
			);
		},

		guardarUsuario() {
			this.usuario.rol = this.roles.find(r => r.id === this.usuario.rol.id);
			apiSend(
				{
					url: `${apiUrl}/usuarios`,
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
				(err) => mostrarToast(err.message, true),
				() => this.cargarUsuarios(),
			);
		},

		eliminarUsuario() {
			apiSend(
				{
					url: `${apiUrl}/usuarios/${this.usuario.id}`,
					method: "DELETE",
				},
				async (res) => {
					this.modalEliminar.hide();
					mostrarToast("Usuario eliminado correctamente");
				},
				(err) => mostrarToast(err.message, true),
				() => this.cargarUsuarios(),
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
		// MODALES
		// ---------------------------------------

		abrirModalNuevo() {
			this.usuario = { ...usuarioVacio };
			this.modoEdicion = false;
			this.modalUsuario.show();
		},

		abrirModalEditar(usuario) {
			this.usuario = { ...usuario };
			this.modoEdicion = true;
			this.modalUsuario.show();
		},

		confirmarEliminar(usuario) {
			this.usuario = { ...usuario };
			this.modalEliminar.show();
		},
	};
}
