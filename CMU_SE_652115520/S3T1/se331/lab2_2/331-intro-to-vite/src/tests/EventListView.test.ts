import { describe, it, expect } from 'vitest'
import { mount } from '@vue/test-utils'
import EventListView from '../views/EventListView.vue'

describe('EventListView', () => {
  it('renders properly', () => {
    const wrapper = mount(EventListView)
    expect(wrapper.text()).toContain('Events For Good')
  })
})
