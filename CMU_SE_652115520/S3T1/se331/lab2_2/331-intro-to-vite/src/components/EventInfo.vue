<template>
  <div class="event-info">
    <div>{{ event.category }}</div>
    <div>{{ event.organizer }}</div>
  </div>
</template>

<script setup lang="ts">
interface Event {
  id: number;
  category: string;
  title: string;
  description: string;
  location: string;
  date: string;
  time: string;
  petsAllowed: boolean;
  organizer: string;
}

defineProps<{ event: Event }>()
</script>

<style scoped>
.event-info {
  text-align: right;
  font-size: 1rem;
}
.event-info > div {
  font-size: 1rem;
}
</style>
