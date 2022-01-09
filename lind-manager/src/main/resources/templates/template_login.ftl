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
        <meta name="viewport" content="width=device-width,initial-scale=1.0"/>
        <script type="text/javascript" src="http://vuejs.org/js/vue.min.js"></script>
        <link rel="stylesheet" type="text/css" href="http://unpkg.com/iview/dist/styles/iview.css">
        <script type="text/javascript" src="http://unpkg.com/iview/dist/iview.min.js"></script>
        <script src="https://unpkg.com/axios/dist/axios.min.js"></script>
        <style>
            body {
                text-align: center;
            }

            .flex {
                /* 流式布局 */
                display: -webkit-flex;
                /* Safari */
                display: flex;
                flex-direction: column;
                /* 项目的排列方向 row | row-reverse | column | column-reverse; */
                flex-wrap: wrap;
                /* 换行 nowrap | wrap | wrap-reverse; */
                justify-content: center;
                /* 项目在主轴上的对齐方式 flex-start | flex-end | center | space-between | space-around; */
                align-items: center;
                /* 项目在交叉轴上如何对齐 flex-start | flex-end | center | baseline | stretch; */
                align-content: center;
                /* 多根轴线的对齐方式 flex-start | flex-end | center | space-between | space-around | stretch; */
                /* order\flex-grow\flex-shrink\flex-basis\flex\align-self */
            }
        </style>
    </head>
    <body>
    <div id="app">
        <#nested "form">
    </div>
    </body>
    </html>
</#macro>