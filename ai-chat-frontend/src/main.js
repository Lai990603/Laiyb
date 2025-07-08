import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import axios from 'axios'

// 创建axios实例
const api = axios.create({
  baseURL: 'http://localhost:8080/api',
  timeout: 5000
})

const app = createApp(App)

// 全局注册axios
app.config.globalProperties.$api = api

app.use(store)
app.use(router)
app.use(ElementPlus)
app.mount('#app')