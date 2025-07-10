<script setup lang="ts">
import {RouterLink,RouterView} from 'vue-router'
</script>

<template>
  <div class="wrapper">
    <a href="https://vite.dev" target="_blank">
      <img src="/vite.svg" class="logo" alt="Vite logo" />
    </a>
    <a href="https://vuejs.org/" target="_blank">
      <img src="./assets/vue.svg" class="logo vue" alt="Vue logo" />
    </a>
  </div>
  <div>
  <header>
    <div class="wrapper">
      <h1>Vite + Vue</h1>
      <nav>
        <RouterLink to="/">Home</RouterLink>
        <RouterLink to="/about">About</RouterLink>
      </nav>
    </div>
  </header>
    <RouterView />
  </div>
</template>



<style scoped>
  #layout {
    font-family: Arial, Helvetica, sans-serif;
    -webkit-font-smothing:antialiased; 
    -moz-osx-font-smoothing: grayscale;
    text-align: center;
    color: #2c3e50;
  }

  nav {
    padding: 30px;
  }

  nav a {
    font-weight: bold;
    color: #2c3e50;
  }

  nav a.router-link-active {
    color: #42b983;
  }
</style>
