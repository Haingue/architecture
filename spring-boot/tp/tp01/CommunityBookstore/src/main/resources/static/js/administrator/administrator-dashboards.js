document.addEventListener("DOMContentLoaded", (event) => {
    console.log("DOM fully loaded and parsed");
});

function removeBook (bookIsbn) {
    console.debug(`Remove a book [bookIsbn=${bookIsbn}]`)
    const formData = new FormData()
    formData.append('isbn', bookIsbn)
    formData.append('title', null)
    formData.append('author', null)
    formData.append('available', false)
    fetch ('/ui/administrator/books/remove', {
        method: 'POST',
        body: formData
    })
    .then(response => {
        if (response.ok) {
            window.location.reload()
            return
        }
        return response.json()
    })
    .then(problem => {
        alert(problem.detail)
    })
    .catch(error => {
        console.error('Error to remove a book: ', error)
    })
}


function forceReturnBook (uuid) {
    console.debug(`Reset borrowed book [uuid=${uuid}]`)
    const formData = new FormData()
    formData.append('uuid', uuid)
    fetch ('/ui/administrator/books/return', {
        method: 'POST',
        body: formData
    })
        .then(response => {
            if (response.ok) {
                window.location.reload()
                return
            }
            return response.json()
        })
        .then(problem => {
            alert(problem.detail)
        })
        .catch(error => {
            console.error('Error to force-return a book: ', error)
        })
}