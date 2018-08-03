<template>
    <div>
        <div v-html="classInfo"></div>
        <!--方法列表-->
        <div>
            <el-table
                    :data="tableData"
                    stripe
                    style="width: 100%"
                    @expand-change="expandChange">
                <el-table-column type="expand">
                    <template slot-scope="scope">
                        方法调试：
                        <el-form ref="form" label-width="80px">
                            <el-form-item label="入参：">
                                <el-input type="textarea" :rows="4" v-model="scope.row.input"></el-input>
                            </el-form-item>
                            <el-form-item label="响应：">
                                <el-input type="textarea" :rows="4" readonly v-model="scope.row.response"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-button type="primary" @click.native.prevent="executeMethod(scope.row)">执行</el-button>
                            </el-form-item>
                        </el-form>
                    </template>
                </el-table-column>
                <!--width="650"-->
                <el-table-column
                        label="方法"

                >
                    <template slot-scope="scope">
                        <el-popover trigger="hover" placement="top">
                            详情:
                            <div v-if="scope.row.detail != undefined">
                                <p v-for="ann in scope.row.detail.annotations" style="color: burlywood">
                                    @{{ann}}()
                                </p>
                            </div>
                            <p>
                                <span v-if="scope.row.detail"
                                      style="color: chocolate">{{scope.row.detail.modifiersStr}} </span>
                                <span v-if="scope.row.detail && scope.row.detail.returnType === 'void'"
                                      style="color: chocolate">{{scope.row.detail.returnType}} </span>
                                <span v-if="scope.row.detail && scope.row.detail.returnType !== 'void'"
                                >{{scope.row.detail.returnType}} </span>
                                <!--方法名-->
                                <span v-if="scope.row.detail">{{scope.row.detail.methodName}}( </span>

                                <span v-if="scope.row.detail">
                                    <!--异常抛出-->
                                    <span v-if="scope.row.detail.args"
                                          v-for="(item, index) of scope.row.detail.args">
                                        <span>{{item.type}} {{item.argName}}</span>
                                        <span v-if="index != scope.row.detail.args.length - 1">, </span>
                                    </span>
                                </span>

                                <span>) </span>

                                <span v-if="scope.row.detail">
                                    <!--异常抛出-->
                                    <span v-if="scope.row.detail.throwsTypes"
                                          v-for="(item, index) of scope.row.detail.throwsTypes">
                                        <span v-if="index == 0" style="color: chocolate">throws </span>
                                        <span>{{item}}</span>
                                        <span v-if="index != scope.row.detail.throwsTypes.length - 1">, </span>
                                    </span>
                                </span>
                                <span>; </span>
                                <!--<div v-if="scope.row.detail">{{ scope.row.method }}</div>-->
                            </p>
                            <div slot="reference">
                                <el-tag size="medium">{{ scope.row.methodName }}</el-tag>
                            </div>
                        </el-popover>
                    </template>
                </el-table-column>
                <!--<el-table-column
                        prop="opt"
                        label="操作"
                        width="250">
                    <template slot-scope="scope">
                        <el-button
                                @click.native.prevent="openOpt(scope.row, scope.$index)"
                                type="text"
                                size="small">
                            展开
                        </el-button>
                    </template>
                </el-table-column>-->
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
            /**
             * 点击展开或关闭时触发
             */
            expandChange: function(row, expandedRows) {
                // console.info(row, expandedRows);
            },
            /**
             * 执行方法
             */
            executeMethod: function(row) {
                console.info(row);
            },
            /**
             * 路由改变时触发
             */
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
                });
            },
            //打开操作
            openOpt: function (row, index) {
                //todo 展开信息执行
                console.info("操作了", row, index, this.body);
            },
            /**
             * 渲染java信息
             * @param javaInfo
             * @returns {*|void}
             */
            renderJavaInfo: function (javaInfo) {
                let body = javaInfo.body;
                let type = '';

                if (!body['interface'] && !body['enum'] && !body['annotation']) {
                    type = "class";
                } else if (body['enum']) {
                    type = "enum";
                } else if (body['annotation']) {
                    type = "@interface";
                }

                //继承信息
                let extendsInfo = body.superClassName ? " extends " + body.superClassName : "";

                let info = "```java \n" +
                    "package " + body.packageName + "\n" +
                    body.modifiersStr + " " + (type ? type + " " : "") + body.className.substring(body.className.lastIndexOf(".") + 1) + extendsInfo + "\n" +
                    "```";

                return marked(info);
            },
            /**
             * 渲染方法信息
             * @param methods
             * @returns {Array}
             */
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
                            methodName: m.methodName + "(" + argInfo + ")",
                            input: "[]",
                            response : "",
                        });
                    });
                }
                return methodInfo;
            }
        }
    }
</script>