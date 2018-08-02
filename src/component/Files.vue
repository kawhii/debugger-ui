<!--文件树-->
<template>
    <el-tree :data="data" :props="defaultProps" :load="loadNode"
             lazy>
    </el-tree>
</template>

<script>
    export default {
        methods: {
            loadNode(node, resolve) {
                resolve([
                    {
                        "name": "com/karl/debugger/ui",
                        "size": 288,
                        "lastModified": 1533024572000,
                        "path": "com",
                        "isDir": true
                    },
                    {
                        "name": "application.properties",
                        "size": 78,
                        "lastModified": 1533024571000,
                        "path": "application.properties",
                        "isDir": false
                    }
                ]);

            }
        },
        data() {
            return {
                data: [],
                defaultProps: {
                    label: function (data, node) {
                        //将class后缀的文件转回java文件
                        if (data.name.indexOf(".class") > 0) {
                            return data.name.substr(0, data.name.indexOf(".class")) + ".java";
                        } else {
                            return data.name;
                        }
                    },
                    isLeaf: function (data, node) {
                        return !data.isDir;
                    }
                }
            }
        }
    }
</script>
