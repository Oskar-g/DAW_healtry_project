
async function apiSend(info, onSuccess, onError, finalmente) {
	try {
		const res = await fetch(
			info.url,
			{
				method: 'GET',
				headers: info.headers || { "Content-Type": "application/json" },
				...info
			}
		);
		if (!res.ok) {
			throw Error(`ERROR: ${res.status}`)
		}
		onSuccess(res, info);

	} catch (err) {
		console.error(err);
		if (onError) {
			onError(err, info)
		}
	}

	if (finalmente) {
		finalmente();
	}
}