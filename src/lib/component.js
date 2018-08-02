import Vue from 'vue';
import Files from '../component/Files';

export default {

    /**
     * 初始化组件
     */
    init () {
        Vue.component("file-tree", Files);
    }
}
