<template>
  <footer class="w-full absolute bottom-0 text-center flex gap-3">
    <div class="flex-1">INTES - 2023/2024</div>
    <div class="flex-2">
      PartService <span class="status" :class="partServiceStatus ? 'up' : 'down'"></span>
    </div>
    <div class="flex-2">
      SupplierService <span class="status" :class="supplierServiceStatus ? 'up' : 'down'"></span>
    </div>
  </footer>
</template>

<script>
import supplierService from '@/services/supplierService.js'
import partService from '@/services/partService.js'

export default {
  name: 'FooterComponents',
  data: () => ({
    partServiceStatus: false,
    checkTimeout: undefined,
    supplierServiceStatus: false
  }),
  methods: {
    checkPartServiceStatus: function () {
      this.partServiceStatus = partService.getStatus()
    },
    checkSupplierServiceStatus: function () {
      this.supplierServiceStatus = supplierService.getStatus()
    },
    checkStatus: function () {
      this.checkPartServiceStatus()
      this.checkSupplierServiceStatus()
      this.checkTimeout = setTimeout(() => {
        this.checkStatus()
      }, 5000)
    }
  },
  created: function () {
    this.checkStatus()
  },
  unmounted: function () {
    this.checkTimeout = clearTimeout(this.checkTimeout)
  }
}
</script>

<style></style>
