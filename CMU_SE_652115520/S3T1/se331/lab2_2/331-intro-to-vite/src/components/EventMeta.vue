<template>
    <div class="meta">
      <div>{{ category }}</div>
      <div>{{ organizer }}</div>
    </div>
  </template>
  
  <script setup lang="ts">
  defineProps<{
    category: string
    organizer: string
  }>()
  </script>
  
  <style scoped>
  .meta {
    text-align: right;
    font-size: 1rem;
  }
  </style>
  