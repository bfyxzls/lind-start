<#import "../template.ftl" as layout>
<@layout.registrationLayout section>
    <#if section = "header">
        列表
    <#elseif section = "form">
        <table border="1">
            <tr>
                <td>编号</td>
                <td>名称</td>
                <td>年龄</td>
                <td>操作</td>
            </tr>
        </table>
    </#if>
</@layout.registrationLayout>