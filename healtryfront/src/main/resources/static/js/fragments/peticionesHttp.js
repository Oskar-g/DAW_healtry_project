
async function apiSend(info, onSuccess, onError) {
	try {
		const res = await fetch(
			info.url,
			{
				method: 'GET',
				headers: info.headers || { "Content-Type": "application/json" },
				...info
			}
		);

		onSuccess(res, info);

	} catch (err) {
		console.error(err);
		onError(err, info)
	}
}