<template>
<h1>Events For Good</h1>
<div class="home">
    <div class="event-card" v-for="event in events" :key="event.id">
      <EventCard :event="event" />
      <EventInfo :event="event" />
    </div>
</div>
 
  <div>
    <h1>Event List</h1>
    <ul>
      <li v-for="event in events" :key="event.id">
        {{ event.title }} — {{ event.location }}
      </li>
    </ul>
  </div>
</template>

<script setup lang="ts">
import EventCard from '../components/EventCard.vue'
import EventInfo from '../components/EventInfo.vue'
import { ref } from 'vue'   



// Define the Event type inline if ../types doesn't exist
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

// Use relative path instead of alias until alias is properly configured
import data from '../data/db.json'
const events = ref<Event[]>(data.events)

</script>

<style scoped>
  .home {
    display: flex;
    flex-direction: column;
    align-items: center;
  }
  
  .event-card {
    display: flex;
    flex-direction: row;
    justify-content: space-between;
    width: 100%;
    max-width: 800px;
    margin-bottom: 20px;
    padding: 10px;
    border: 1px solid #ddd;
    border-radius: 4px;
  }
</style>
