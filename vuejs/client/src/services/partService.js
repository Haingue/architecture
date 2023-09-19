const API_PART_SERVICE = 'http://localhost:8090'

export default {
  getStatus: async function () {
    const health = await fetch(`${API_PART_SERVICE}/actuator/health`)
    return health.status === 200
  },
  loadAllBySupplierCode: function (supplierCode) {
    return fetch(`${API_PART_SERVICE}/service/part?supplierCode=${supplierCode}`)
  },
  loadAllPart: function () {
    return fetch(`${API_PART_SERVICE}/service/part`)
  },
  createPart: function (part) {
    return fetch(`${API_PART_SERVICE}/service/part`, {
      method: 'POST',
      body: part
    })
  }
}
