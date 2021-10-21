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

        <h1>${url!}</h1>
        <h2>${url!'非空'}</h2>
        <#if forgetPasswordAddress?has_content>
            忘记密码
        </#if>
        <form id="kc-form-login" onsubmit="login.disabled = true; return true;" action="/free/add"
              enctype="application/x-www-form-urlencoded" method="post">
            <div class="form-group">
                <label for="username" class="control-label">用户名 或 电子邮箱地址</label>

                <input tabindex="1" id="username" class="form-control" name="username" value="" type="text" autofocus=""
                       autocomplete="off">
            </div>

            <div class="form-group">
                <label for="password" class="control-label">密码</label>
                <input tabindex="2" id="password" class="form-control" name="password" type="password"
                       autocomplete="off">
            </div>

            <div class="form-group login-pf-settings">
                <div id="kc-form-options">
                </div>
                <div class="">
                </div>

            </div>

            <div id="kc-form-buttons" class="form-group">
                <input type="hidden" id="id-hidden-input" name="credentialId">
                <input tabindex="4" class="btn btn-primary btn-block btn-lg" name="login" id="kc-login" type="submit"
                       value="登录">
            </div>
        </form>
    </#if>
</@layout.registrationLayout>