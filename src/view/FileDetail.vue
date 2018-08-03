<template>
    <div v-html="fileDetail"></div>

</template>
<style>
    @import "~highlight.js/styles/atelier-forest-dark.css";
</style>
<script>
    import axios from 'axios';
    import marked, { Renderer } from 'marked';
    import highlightjs from 'highlight.js';

    // Create your custom renderer.
    const renderer = new Renderer();
    renderer.code = (code, language) => {
        // Check whether the given language is valid for highlight.js.
        const validLang = !!(language && highlightjs.getLanguage(language));
        // Highlight only if the language is valid.
        const highlighted = validLang ? highlightjs.highlight(language, code).value : code;
        // Render the highlighted code with `hljs` class.
        return `<pre><code class="hljs ${language}">${highlighted}</code></pre>`;
    };

    // Set the renderer to marked.
    marked.setOptions({ renderer });

    export default {
        data() {
            return {
                fileDetail : ''
            }
        },
        watch: {
            // 如果路由有变化，会再次执行该方法
            '$route': 'routeChanged'
        },
        mounted: function () {
            this.routeChanged();
        },
        methods: {
            routeChanged: function () {
                //todo java显示
                let type = this.$route.params.type;
                //base64解码
                let path = atob(this.$route.params.path);
                let _this = this;
                //异步请求回来把数据展示在页面上
                axios.get("/dg/blob/" + path).then(function (response) {
                    //以markdown的方式更新数据
                    _this.fileDetail = marked('```' + type + " \n\n " +
                        atob(response.data.body) +
                            " \n "  +
                        "```");
                });
            }
        }
    }
</script>