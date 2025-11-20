const apiUrl = 'http://localhost:8080';
const localUrl = 'http://localhost:8083';


function config() {
	return {
		configuraciones: [],

		init() {
			this.cargarConfiguraciones();
		},

		// ---------------------------------------
		// REST
		// ---------------------------------------

		cargarConfiguraciones() {
			apiSend(
				{
					url: `${apiUrl}/configuraciones`,
				},
				async res => this.configuraciones = await res.json(),
				err => mostrarToast(err.message, true),
			);
		},

		getTitle(){
			return this.configuraciones.find(c => c.clave=='TITULO')?.valor
		},
	}
}