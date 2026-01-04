document.addEventListener("DOMContentLoaded", (event) => {
    console.log("DOM fully loaded and parsed");
});

function returnBook (customerUuid, bookIsbn) {
    console.debug(`Return a book [customerUuid=${customerUuid}, bookIsbn=${bookIsbn}]`)
    const formData = new FormData()
    formData.append('customerUuid', customerUuid)
    formData.append('bookIsbn', bookIsbn)
    fetch ('/ui/customer/books/return', {
        method: 'POST',
        body: formData
    })
    .then(response => {
        if (response.ok) {
            window.location.reload()
        }
        throw Error(response)
    })
    .catch(error => {
        console.error('Error to return a book: ', error)
    })
}