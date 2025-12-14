window.onload = function WindowLoad(event) {
    console.log("Page is loaded");

    loadCurrentTimestamp()
}

let timestampTimeout;
function loadCurrentTimestamp () {
    const container = document.getElementById('timestamp');
    container.textContent = new Date();
    clearTimeout(timestampTimeout);
    timestampTimeout = setTimeout(loadCurrentTimestamp, 1000);
}