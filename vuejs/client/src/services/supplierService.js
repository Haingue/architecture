const API_SUPPLIER_SERVICE = 'http://localhost:8095'

export default {
  getStatus: async function () {
    const health = await fetch(`${API_SUPPLIER_SERVICE}/metrics`)
    return health.status === 200
  },
  loadAllSupplier: function () {
    return fetch(`${API_SUPPLIER_SERVICE}/service/suppliers`)
  },

  createSupplier: function (supplier) {
    return fetch(`${API_SUPPLIER_SERVICE}/service/suppliers`, {
      method: 'POST',
      body: supplier
    })
  }
}
