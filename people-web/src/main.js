import Vue from 'vue'
import App from './App.vue'
import Vuex from "vuex"
import axios from '@/axios'
import { BootstrapVue, IconsPlugin } from 'bootstrap-vue'

import 'bootstrap/dist/css/bootstrap.css'
import 'bootstrap-vue/dist/bootstrap-vue.css'

import config from "@/config"

Vue.use(Vuex);
Vue.use(BootstrapVue)
Vue.use(IconsPlugin)

const { username, password } = config;

const auth = {
  username,
  password
};

const store = new Vuex.Store({
  state: {
    pessoas: [],
    erros: [],
    pessoa: {}
  },
  mutations:{
    addPessoa(state, payload){
      const { pessoa } = payload;
      state.pessoas.push(pessoa); 
    },
    setPessoas(state, payload){
      const { pessoas  } = payload;

      state.pessoas = pessoas;
    },
    setErros(state, payload){
      const { erros } = payload;

      state.erros = erros;
    },
    pessoaToEdit(state, payload){
      const { pessoa } = payload;

      state.pessoa = pessoa;
    },
    clearPessoa(state){
      state.pessoa = {};
    }
  },
  actions:{
    async addPessoa(context, payload){
      const pessoaToSend = payload;

      try{
        const response = await axios.post('/pessoa', pessoaToSend, { auth });

        const pessoa = response.data.pessoa;

        context.commit("addPessoa", { pessoa });
        context.commit("clearPessoa");
        context.dispatch("getPessoas");
      }catch(error){
        const erros = error.response.data.errors;
        context.commit("setErros", { erros });
      }

    },
    async getPessoas(context){
      const response = await axios.get('/pessoa', { auth });

      const pessoas = response.data;

      context.commit("setPessoas", { pessoas });
    },
    async deletePessoa(context, payload){
      const { id } = payload;

      await axios.delete('/pessoa/'+id, { auth});

      context.dispatch("getPessoas");
    },
    editarPessoa(context, payload){
      const pessoa = { ...payload };
      context.commit("pessoaToEdit", { pessoa });
    },
    async updatePessoa(context, payload){
      const pessoaToSend = payload;

      console.log("Update");
      
      try{
        const response = await axios.put('/pessoa/'+pessoaToSend.id, pessoaToSend, { auth });

        const pessoa = response.data.pessoa;

        context.commit("addPessoa", { pessoa });
        context.commit("clearPessoa");
        context.dispatch("getPessoas");
      }catch(error){
        const erros = error.response.data.errors;
        context.commit("setErros", { erros });
      }
    },
    novoRegistro(context){
      context.commit("clearPessoa");
    }

  }
})

const loadPessoas = () =>{
  store.dispatch("getPessoas");
}

loadPessoas();

new Vue({
  store,
  render: h => h(App),
}).$mount('#app')
