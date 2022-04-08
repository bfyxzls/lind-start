<#macro registrationLayout bodyClass="默认值，子页面没有设置会使用这个值，子页面设置后会覆盖它" mainCss="red">
<#--语法介绍：-->
<#--<#macro name param1 param2 ... paramN>--><!-- macro在模板里定义的变量，在子页面中可以重写变量的内容，它会反映的模板里-->
<#--<#nested loopvar1, loopvar2, ..., loopvarN>-->
<#--<p>${param1?cap_first}-->
    <!--
CDN上引用vueJS后，使用i开头的标签
Button	i-button
Col	i-col
Tabel	i-tabel
Input	i-input
Form	i-form
Menu	i-menu
Select	i-sekect
Option	i-option
Progress	i-progress
Switch	i-switch
-->
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title><#nested "head"></title>
        <Style type="text/css">
            .red {
                color: red;
            }


            .layout {
                border: 1px solid #d7dde4;
                background: #f5f7f9;
            }

            .layout-logo {
                width: 100px;
                height: 30px;
                background: #5b6270;
                border-radius: 3px;
                float: left;
                position: relative;
                top: 15px;
                left: 20px;
            }

            .layout-nav {
                width: 420px;
                margin: 0 auto;
            }

            .layout-assistant {
                width: 300px;
                margin: 0 auto;
                height: inherit;
            }

            .layout-breadcrumb {
                padding: 10px 15px 0;
            }

            .layout-content {
                min-height: 200px;
                margin: 15px;
                overflow: hidden;
                background: #fff;
                border-radius: 4px;
            }

            .layout-content-main {
                padding: 10px;
            }

            .layout-copy {
                text-align: center;
                padding: 10px 0 20px;
                color: #9ea7b4;
            }

        </style>

        <meta name="renderer" content="webkit|ie-comp|ie-stand">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta http-equiv="Cache-Control" content="no-siteapp"/>
        <link rel="stylesheet" href="/css/iview.css">
        <script src="/js/vue.min.js"></script>
        <script src="/js/iview.min.js"></script>
        <script src="/js/axios.min.js"></script>
        <script src="/js/vuejs-datepicker.js"></script>
        <script src="/component.js"></script>
        <#nested "head-js">
    </head>
    <body>
    <div id="app">
        <div class="layout">

            <i-menu mode="horizontal" theme="dark" active-name="1">
                <div class="layout-logo"></div>
                <div class="layout-nav">
                    <menu-item name="1">
                        <Icon type="ios-navigate"></Icon>
                        Item 1
                    </menu-item>
                    <menu-item name="2">
                        <Icon type="ios-keypad"></Icon>
                        Item 2
                    </menu-item>
                    <menu-item name="3">
                        <Icon type="ios-analytics"></Icon>
                        Item 3
                    </menu-item>
                    <menu-item name="4">
                        <Icon type="ios-paper"></Icon>
                        Item 4
                    </menu-item>
                </div>
            </i-menu>
            <div class="layout-content">
                <Row>
                    <i-col span="5">
                        <i-menu theme="light" width="auto">
                            <Submenu name="1">
                                <template slot="title">
                                    内容管理
                                </template>
                                <menu-item name="1-1">文章管理</menu-item>
                                <menu-item name="1-2">评论管理</menu-item>
                                <menu-item name="1-3">举报管理</menu-item>
                            </Submenu>
                            <Submenu name="2">
                                <template slot="title">
                                    用户管理
                                </template>
                                <menu-item name="2-1">新增用户</menu-item>
                                <menu-item name="2-2">活跃用户</menu-item>
                            </Submenu>
                            <Submenu name="3">
                                <template slot="title">
                                    统计分析
                                </template>
                                <menu-group title="使用">
                                    <menu-item name="3-1">新增和启动</menu-item>
                                    <menu-item name="3-2">活跃分析</menu-item>
                                    <menu-item name="3-3">时段分析</menu-item>
                                </menu-group>
                                <menu-group title="留存">
                                    <menu-item name="3-4">用户留存</menu-item>
                                    <menu-item name="3-5">流失用户</menu-item>
                                </menu-group>
                            </Submenu>
                        </i-menu>
                    </i-col>
                    <i-col span="19">
                        <div class="layout-header"></div>
                        <div class="layout-breadcrumb">
                            <Breadcrumb :style="{margin: '24px 0'}">
                                <Breadcrumb-item>首页</Breadcrumb-item>
                                <Breadcrumb-item>内容管理</Breadcrumb-Item>
                                <Breadcrumb-item>文章管理</Breadcrumb-item>
                            </Breadcrumb>
                        </div>
                        <div class="layout-content">
                            <p>
                                ${bodyClass}
                            </p>
                            <#nested "form">
                    </i-col>
                </Row>
            </div>
            <div class="layout-copy">
                2011-2016 &copy; TalkingData
            </div>
        </div>

    </div>
    </body>
    </html>
</#macro>