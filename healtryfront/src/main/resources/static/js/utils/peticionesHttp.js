
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
			throw  res.message? Error(`[${res.status}] ERROR: ${res.message}`) : Error(`[${res.status}] ERROR`)
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