import { createApp } from 'vue'
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
import App from './App.vue'
import VueFab from 'vue-float-action-button'
import axios from 'axios'


// 默认请求的基础url（如果axios请求的地址不带域名，自动添加baseURL）（默认请求域名，/当前域名）
axios.defaults.baseURL = "http://220.179.227.205:6020/"
// 设置post请求头的content-Type值
axios.defaults.headers.post['Content-Type'] = 'application/x-www-form-urlencoded';
// 请求超时5000毫秒
axios.defaults.timeout = 5000;



const app=createApp(App)


app.config.globalProperties.$axios = axios
app.use(VueFab,   {iconType: 'MaterialDesign'}/* {
  ----------------------
  // opitons 可选iconfont图标或MaterialIcons
  iconType: 'MaterialDesign'
  // iconType: 'iconfont'
} */)
app.use(ElementPlus)

app.mount('#app')



