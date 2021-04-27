<template>
  <div class="myform">
    <div class="row align-items-center">
      <div class="col-md-9">
        <h1>{{ msg }}</h1>
      </div>
      <div class="col-md-3">
         <b-button @click="getSource()">Call Git Source</b-button>
      </div>  
    </div>
    <div class="row">
      <form>
        <div class="form-row">
          <div class="form-group col-md-9">
            <label for="inputNome">Nome</label>
            <input type="text" class="form-control" id="inputNome" placeholder="Nome" v-model="pessoa.nome">
          </div>

          <div class="form-group col-md-3">
            <label for="inputSexo">Sexo</label>
            <input type="text" class="form-control" id="inputSexo" placeholder="Sexo" v-model="pessoa.sexo">
          </div>
        </div>
        <div class="form-row">
          <div class="form-group col-md-5">
            <label for="inputEmail4">Email</label>
            <input type="email" class="form-control" id="inputEmail4" placeholder="Email" v-model="pessoa.email">
          </div>
          <div class="form-group col-md-3">
            <label for="inputCPF">CPF</label>
            <input type="text" class="form-control" id="inputCPF" placeholder="999.999.999-99" v-model="pessoa.cpf">
          </div>
          <div class="form-group col-md-4">
            <label for="inputNascimento">Nascimento</label>
            <input type="text" class="form-control" id="inputNascimento" v-model="pessoa.nascimento" placeholder="DD/MM/YYYY" >
          </div>
        </div>
        
        <div class="form-row">
          <div class="form-group col-md-6">
            <label for="inputNaturalidade">Naturalidade</label>
            <input type="text" class="form-control" id="inputNaturalidade" v-model="pessoa.naturalidade">
          </div>
          <div class="form-group col-md-6">
            <label for="inputState">Nacionalidade</label>
            <select id="inputState" class="form-control" v-model="pessoa.nacionalidade">
              <option selected>Brasil</option>
              <option>Estrangeiro</option>
            </select>
          </div>
        </div>
        <div class="row">
          <div class="col-md-1" style="margin-right: 10px">
            <button class="btn btn-info" @click="novoRegistro($event)">Novo</button>
          </div>
          <div class="col-md-1"><button @click="savePerson($event)" class="btn btn-primary">Gravar</button></div>
        </div>
      </form>
    </div>
    <b-toast id="sourceToast" variant="warning" solid>
      <template #toast-title>
        <div class="d-flex flex-grow-1 align-items-baseline">
          <b-img blank blank-color="#ff5555" class="mr-2" width="12" height="12"></b-img>
          <strong class="mr-auto">Git Project</strong>
          <small class="text-muted mr-2">{{source.lastCall}}</small>
        </div>
      </template>
      <a v-bind:href="source.value" target="_blank">{{source.value}}</a>
    </b-toast>
  </div>

</template>

<script>
import axios from '@/axios';

export default {
  name: 'ManagePeople',
  props: {
    msg: String,
  },
  data: function(){
    return {
      source: {
        value: "",
        lastCall: null
      }
    }
  },
  methods:{
    savePerson(event){
      if('id' in this.$store.state.pessoa){
        this.$store.dispatch("updatePessoa", this.$store.state.pessoa);
      }else{
        this.$store.dispatch("addPessoa", this.$store.state.pessoa);
      }

      event.preventDefault();
    },
    async getSource(){
      const response = await axios.get('/source');

      this.source.value = response.data;
      this.source.lastCall = new Intl.DateTimeFormat('pt-BR', { dateStyle: 'short', timeStyle: 'medium' }).format(new Date());

      this.$bvToast.show('sourceToast');
    },
    novoRegistro(event){
      this.$store.dispatch('novoRegistro');
      event.preventDefault();
    }
  },
  computed:{
    errors(){
      return this.$store.state.erros;
    },
    pessoa(){
      return this.$store.state.pessoa;
    }
  },
  watch:{
    errors(newErrors){
      newErrors.forEach(e => {
        this.$bvToast.toast(e, {
          title: 'Erro',
          variant: 'danger',
          solid: true,
          noAutoHide: true,
        })
      });
    }
  }
}
</script>

<style scoped>
  .container{
    margin-top: 15px;
  }
  .myform{
    margin: 15px;
  }
</style>
