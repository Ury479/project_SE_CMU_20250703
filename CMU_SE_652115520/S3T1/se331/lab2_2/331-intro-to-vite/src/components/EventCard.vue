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

defineProps<{
  event: Event
}>()
</script>

<template>
  <div class="event-card">
    <h2>{{ event.title }}</h2>
    <span>@ {{ event.time }} on {{ event.date }}</span>
    <p>{{ event.description }}</p>
    <p><strong>Location:</strong> {{ event.location }}</p>
  </div>
</template>

<style scoped> 
  .event-card {
    padding: 20px;
    width: 250px;
    cursor: pointer;
    border: 1px solid #39495c;
    margin-bottom: 18px;
  }
  .event-card:hover {
    transform: scale(1.01);
    box-shadow: 0 3px 12px rgba(0, 0, 0, 0.2);
  }
</style>