<template>
  <div id="app" class="main">
    <ManagePeople msg="Gerenciador de pessoas"/>
    <People />
  </div>
</template>

<script>
import "bootstrap/dist/css/bootstrap.css";

import ManagePeople from './components/ManagePeople.vue'
import People from './components/People.vue'

export default {
  name: 'App',
  components: {
    ManagePeople,
    People
  }
}
</script>

<style>

.main {
  background-color: #f1f1f1;
  padding-top: 30px;
  padding-bottom: 30px;
  height: 100%;
  position: fixed;
}
</style>
