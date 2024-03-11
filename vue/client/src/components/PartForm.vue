<template>
  <form action="/client/part" method="post" class="w-full p-4 hidden">
    <label>Ajouter une pièce:</label>
    <br />
    <input name="id" type="number" placeholder="Id" />
    <input name="name" type="text" placeholder="Name" />
    <input name="description" type="text" placeholder="Description" />
    <button>Submit</button>
  </form>
</template>
