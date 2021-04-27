<template>
    <div class="container">
        <table class="table table-dark table-hover">
  <thead>
    <tr>
      <th scope="col">Nome</th>
      <th scope="col">Data Nascimento</th>
      <th scope="col">CPF</th>
    </tr>
  </thead>
  <tbody>
    <tr v-for="(pessoa, key) in pessoas" v-bind:key="key" class="align-items-center">
      <th>{{pessoa.nome}}</th>
      <td>{{pessoa.nascimento}}</td>
      <td>{{pessoa.cpf}}</td>
      <td><button class="btn btn-light btn-sm" @click="editarPessoa(pessoa)">Editar</button></td>
      <td><button class="btn btn-light btn-sm" @click="deletePessoa(pessoa)">Remover</button></td>
    </tr>
  </tbody>
</table>
    </div>
</template>

<script>
export default {
    name: 'People',
    data: function(){
      return {}
    },
    methods:{
      getPessoas(){
        this.$store.dispatch("getPessoas");
      },
      deletePessoa(pessoa){
        this.$store.dispatch("deletePessoa", pessoa);
      },
      editarPessoa(pessoa){
        this.$store.dispatch("editarPessoa", pessoa);
      }
    },
    computed:{
      pessoas(){
        return this.$store.state.pessoas;
      }
    }
}
</script>

<style>
  .container{
    margin-top: 15px;
  }
</style>
