async function fetchWrapper(url, options = {}) {

    const response = await fetch(url, {
        credentials: "include",
        ...options
    });

    if (response.status === 401) {

        window.location.href = "/login";
    }

    if (response.status === 403) {

        alert("Access Denied");
    }

    return response;
}