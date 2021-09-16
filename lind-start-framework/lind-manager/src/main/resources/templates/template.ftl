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