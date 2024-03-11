<template>
  <main>
    <div class="flex gap-4 m-4 p-2 bg-blue-400">
      <section class="flex-1">
        <label>Supplier</label>
        <select v-model="selectSupplier" @input="updateSupplier">
          <option :value="undefined">-</option>
          <option v-for="supplier in suppliers" :key="supplier.code" :value="supplier">
            {{ supplier.name }}
          </option>
        </select>
      </section>
      <section v-if="selectSupplier != undefined" class="flex-1">
        <h2>Description</h2>
        <p>{{ selectSupplier.code }}</p>
        <p>{{ selectSupplier.name }}</p>
        <p>{{ selectSupplier.address }}</p>
      </section>
    </div>
    <div v-if="parts.length > 0" class="my-4 p-4">
      <table class="w-full table-auto text-center">
        <thead>
          <tr>
            <th>Id</th>
            <th>Name</th>
            <th>Description</th>
            <th>Action</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="part in parts" :key="part.id">
            <td>{{ part.id }}</td>
            <td>{{ part.name }}</td>
            <td>{{ part.description }}</td>
            <td><button @click="deletePart(part.id)">Supprimer</button></td>
          </tr>
        </tbody>
      </table>
    </div>
    <PartForm />
  </main>
</template>

<script>
import PartForm from '@/components/PartForm.vue'
import supplierService from '@/services/supplierService.js'
import partService from '@/services/partService.js'
export default {
  name: 'SupplierView',
  components: {
    PartForm
  },
  data: () => ({
    suppliers: [],
    selectSupplier: undefined,
    updateSupplierTimeout: undefined,
    parts: []
  }),
  methods: {
    loadSupplier: function () {
      supplierService
        .loadAllSupplier()
        .then((response) => {
          if (response.status === 200) return response.json()
          throw response
        })
        .then((json) => {
          this.suppliers.splice(0, this.suppliers.length)
          this.suppliers.push(...json)
        })
        .catch((error) => {
          alert(error)
        })
    },
    updateSupplier: function () {
      this.updateSupplierTimeout = clearTimeout(this.updateSupplierTimeout)
      this.updateSupplierTimeout = setTimeout(this.loadPart, 400)
    },
    loadPart: function () {
      console.debug('Load parts:', this.selectSupplier.code)
      this.parts.splice(0, this.parts.length)
      if (this.selectSupplier === undefined) return
      partService
        .loadAllBySupplierCode(this.selectSupplier.code)
        .then((response) => {
          if (response.status === 200) return response.json()
          else if (response.status === 201) throw response
          throw response
        })
        .then((json) => {
          this.parts.push(...json)
        })
        .catch((error) => {
          alert(error)
        })
    },
    deletePart: function (partId) {
      console.debug('Delete part:', partId)
      if (confirm('Etes-vous sûr de vouloir supprimer cette part ?')) {
        partService.delete(partId).then(this.loadPart)
      }
    }
  },
  created: function () {
    this.loadSupplier()
  }
}
</script>
