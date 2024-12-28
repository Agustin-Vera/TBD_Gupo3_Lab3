<script setup>
import Navbar from './components/NavBar.vue';
import { RouterView } from 'vue-router';
</script>

<template>
  <div>
    <Navbar />
    <div class="mt-14">
      <RouterView />
    </div>
  </div>
</template>