<#import "../template.ftl" as layout>
<@layout.registrationLayout bodyClass="<span style='color:red'>修改模板里的变量</span>";section>
    <#if section = "head">
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