import Vue from 'vue';
import ElementUI from 'element-ui';
import 'element-ui/lib/theme-chalk/index.css';

import axios from 'axios'
import VueAxios from 'vue-axios'
import VueRouter from 'vue-router'
import App from './App.vue';
import routes from './lib/routes';

Vue.use(ElementUI);
Vue.use(VueAxios, axios);
Vue.use(VueRouter);


// 3. 创建 router 实例，然后传 `routes` 配置
// 你还可以传别的配置参数, 不过先这么简单着吧。
const router = new VueRouter({
    routes // (缩写) 相当于 routes: routes
});

new Vue({
    el: '#app',
    router,
    render: h => h(App)
});