import Vue from 'vue'
import Vuetify from 'vuetify'
import './api/resources'
import App from 'pages/App.vue'
import { connect } from './util/ws.js'
import 'vuetify/dist/vuetify.min.css'

if (frontendData.profile) {
    connect()
}

Vue.use(Vuetify)

new Vue({
    vuetify : new Vuetify(),
    el: '#app',
    render: a => a(App)
})