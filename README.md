# debugger-ui

[![Build Status](https://travis-ci.org/kawhii/debugger-ui.svg?branch=master)](https://travis-ci.org/kawhii/debugger-ui) [![GitHub license](https://img.shields.io/github/license/kawhii/debugger-ui.svg)](https://github.com/kawhii/debugger-ui/blob/master/LICENSE) [![codecov](https://codecov.io/gh/kawhii/debugger-ui/branch/master/graph/badge.svg)](https://codecov.io/gh/kawhii/debugger-ui)

# 介绍
欢迎大家使用**Debugger-UI**，常见的问题可以在这里提出，希望对大家有帮助。

## 1. Debugger-UI是什么？

`Debugger-UI`专注于在`spring boot`各环境不方便调试的情况下进行**debug**并且提供强大的监控和扩展功能。

## 界面信息

源码：
![源码](./doc/images/source_code.jpg)



debug界面：
![debug界面](./doc/images/execute_info.jpg)


# 项目规划

1. 通过在线debug完成对所有方法的执行与响应
2. 基于spring进行开发显示
3. 能够通过spring的集成即可提供在线debug（类似swagger)
4. 能够在线枚举出所有的类（方法，属性等）、文件在线查看
5. 能够修改变量，修改class文件
6. 查看服务器信息，java环境、服务器cpu、硬盘使用情况等
7. 记录所有的操作记录并且支持导出，修改建议
8. 支持restful api查看与修改内容
9. 支持`Enable`开启与关闭


# 技术选型

* 基于spring 4.x
* 基于java反射


# 问题解决
1. jar包枚举文件
2. 反射加载类的问题解决（spring、静态、new instance）
3. 方法缓存
4. 属性修改
