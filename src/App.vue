<template>
    <div id="app">
        <div>
            <el-row :gutter="20">
                <el-col :span="5">
                    <file-tree></file-tree>
                </el-col>
                <el-col :span="18">
                    <router-view></router-view>
                </el-col>
            </el-row>
        </div>
    </div>

</template>

<style>
    #app {
        font-family: Helvetica, sans-serif;
    }
</style>
