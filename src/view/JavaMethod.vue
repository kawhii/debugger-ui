<template>
    <div>
        <div v-html="classInfo"></div>
        <!--方法列表-->
        <div>
            <el-table
                    :data="tableData"
                    stripe
                    style="width: 100%">
                <el-table-column
                        label="方法"
                        width="650">
                    <template slot-scope="scope">
                        <el-popover trigger="hover" placement="top">
                            详情:
                            <div v-if="scope.row.detail != undefined">
                                <p v-for="ann in scope.row.detail.annotations">
                                    @{{ann}}()
                                </p>
                            </div>
                            <p>{{ scope.row.method }}</p>
                            <div slot="reference">
                                <el-tag size="medium">{{ scope.row.methodName }}</el-tag>
                            </div>
                        </el-popover>
                    </template>
                </el-table-column>
                <el-table-column
                        prop="opt"
                        label="操作"
                        width="250">
                </el-table-column>
            </el-table>
        </div>
    </div>
</template>
<style>
    @import "~highlight.js/styles/atelier-forest-dark.css";
</style>
<script>
    import axios from 'axios';

    import marked, {Renderer} from 'marked';
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
    marked.setOptions({renderer});

    export default {
        data() {
            return {
                tableData: [{
                    method: '123123',
                    opt: '1'
                }],
                /**
                 * class信息
                 */
                classInfo: '',
                body: ''
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
                //base64解码
                let path = atob(this.$route.params.path);
                let _this = this;
                //异步请求回来把数据展示在页面上
                axios.get("/dg/blob/" + path).then(function (response) {
                    //渲染方法
                    _this.tableData = _this.renderMethodInfo(response.data.body.methods);
                    _this.body = response.data.body;
                    _this.classInfo = _this.renderJavaInfo(response.data);
                    console.info(response.data);
                });
            },
            /**
             * 渲染java信息
             * @param javaInfo
             * @returns {*|void}
             */
            renderJavaInfo: function (javaInfo) {
                let body = javaInfo.body;
                let info = "```java \n" +
                    "package " + body.packageName + "\n" +
                    body.modifiersStr + " " + body.className + "\n" +
                    "```";

                return marked(info);
            },
            renderMethodInfo: function (methods) {
                let methodInfo = [];
                if (methods) {
                    methods.forEach(m => {
                        let method = m.modifiersStr + " " + m.returnType + " " + m.methodName + "(";
                        let argInfo = "";
                        //拼装参数
                        if (m.args) {
                            m.args.forEach(arg => {
                                argInfo += arg.type + " " + arg.argName;
                                argInfo += ", ";
                            });
                            argInfo = argInfo.substring(0, argInfo.length - 2);
                        }

                        method += ") ";

                        //异常抛出
                        let throws = "";
                        if (m.throwsTypes) {
                            throws += "throws ";
                            m.throwsTypes.forEach(t => {
                                throws += t;
                                throws += ", ";
                            });
                            throws = throws.substring(0, throws.length - 2);
                        }

                        method += throws;

                        //完成方法拼接

                        methodInfo.push({
                            detail: m,
                            method: method,
                            methodName: m.methodName + "(" + argInfo + ")"
                        });
                    });
                }
                return methodInfo;
            }
        }
    }
</script>