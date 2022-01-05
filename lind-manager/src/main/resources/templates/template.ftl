<#macro registrationLayout bodyClass="默认值，子页面没有设置会使用这个值，子页面设置后会覆盖它" mainCss="red">
<#--语法介绍：-->
<#--<#macro name param1 param2 ... paramN>--><!-- macro在模板里定义的变量，在子页面中可以重写变量的内容，它会反映的模板里-->
<#--<#nested loopvar1, loopvar2, ..., loopvarN>-->
<#--<p>${param1?cap_first}-->
    <!DOCTYPE html>
    <html lang="en">
    <head>
        <meta charset="UTF-8">
        <title><#nested "head"></title>
        <Style type="text/css">
            .red {
                color: red;
            }
        </Style>

        <meta name="renderer" content="webkit|ie-comp|ie-stand">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
        <meta http-equiv="Cache-Control" content="no-siteapp"/>
        <!-- 让IE8/9支持媒体查询，从而兼容栅格 -->
        <!--[if lt IE 9]>
        <script src="https://cdn.staticfile.org/html5shiv/r29/html5.min.js"></script>
        <script src="https://cdn.staticfile.org/respond.js/1.4.2/respond.min.js"></script>
        <![endif]-->

        <meta name="renderer" content="webkit|ie-comp|ie-stand">
        <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
        <meta name="viewport"
              content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
        <meta http-equiv="Cache-Control" content="no-siteapp"/>
        <!-- import stylesheet -->
        <link rel="stylesheet" href="https://cdn.staticfile.org/iview/3.1.5/styles/iview.css">
        <!-- import Vue.js -->
        <script src="https://cdn.staticfile.org/vue/2.5.22/vue.min.js"></script>
        <!-- import iView -->
        <script src="https://cdn.staticfile.org/iview/3.1.5/iview.min.js"></script>
    </head>
    <body>

        <p>
            ${bodyClass}
        </p>
        <h1 class="${mainCss}">FreeMaker模板页面</h1>
        <#nested "form">
    </body>
    </html>
</#macro>