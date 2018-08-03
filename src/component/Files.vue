<!--文件树-->
<template>
    <el-tree :props="defaultProps" :load="loadNode"
             @node-click="handleNodeClick"
             lazy>
    </el-tree>
</template>

<script>
    import axios from 'axios'

    export default {
        methods: {
            /**
             * 点击节点时触发
             */
            handleNodeClick(data) {
                //不是目录的时候可以加载数据
                if (!data.isDir) {
                    //跳转到对应的视图进行处理
                    let type = data.name.substring(data.name.lastIndexOf(".") + 1);
                    if (type === 'class') {
                        type = "java";
                    }
                    //对路径进行base64
                    let path = btoa(data.path);
                    //跳转目标路径
                    this.$router.replace({name: 'detail', params: {type: type, path: path}});
                }
            },
            /**
             * 加载节点
             * @param node
             * @param resolve
             */
            loadNode(node, resolve) {
                let next = "";

                if (node.level > 0) {
                    //以长的路径为最终路径
                    next = "/" + (node.data.name.length > node.data.path.length ? node.data.name : node.data.path);
                }
                //异步请求回来把数据展示在页面上
                axios.get("/dg/tree" + next).then(function (response) {
                    resolve(response.data);
                });
            }
        },
        data() {
            return {
                //数据与节点的映射
                defaultProps: {
                    label: function (data, node) {
                        //将class后缀的文件转回java文件
                        if (data.name.indexOf(".class") > 0) {
                            return data.name.substr(0, data.name.indexOf(".class")) + ".java";
                        } else {
                            return data.name;
                        }
                    },
                    //是否为叶子节点
                    isLeaf: function (data, node) {
                        return !data.isDir;
                    }
                }
            }
        }
    }
</script>
