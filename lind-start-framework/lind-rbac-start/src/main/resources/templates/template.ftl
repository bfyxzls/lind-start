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
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8">

        <title><#nested "head"></title>

        <meta name="renderer" content="webkit|ie-comp|ie-stand">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta http-equiv="Cache-Control" content="no-siteapp"/>
        <link rel="stylesheet" href="/css/iview.css">
        <link rel="stylesheet" href="/css/site.css">
        <script charset="UTF-8" src="/js/vue.min.js"></script>
        <script charset="UTF-8" src="/js/axios.min.js"></script>
        <script charset="UTF-8" src="/js/iview.min.js"></script>
        <script charset="UTF-8" src="/js/vuejs-datepicker.js"></script>
        <script charset="UTF-8" src="/component.js"></script>

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
                        <nav-menu></nav-menu>
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