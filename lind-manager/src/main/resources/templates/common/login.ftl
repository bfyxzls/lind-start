<!doctype html>
<html lang="en" xmlns:th="http://www.thymeleaf.org">
<head th:include="/common/common :: head('登录')"></head>
<body class="login-bg">
    
    <div class="login">
        <div class="message">登录</div>
        <div id="darkbannerwrap"></div>
        
        <form method="post" class="layui-form"  action="/login" method="post" >
            <input name="username" type="text" lay-verType="tips" lay-verify="required" class="layui-input" placeholder="用户名" />
            <hr class="hr15">
            <input name="password" type="password" lay-verType="tips" lay-verify="required" class="layui-input" placeholder="密码" />
            <hr class="hr15">
            <input class="loginin" value="登录" lay-submit lay-filter="login" style="width:100%;" type="submit" />
            <hr class="hr20" >
            <div>
                ${message}
            </div>
        </form>
    </div>

    <div th:include="/common/common :: commonJS" th:remove="tag"></div>

</body>
</html>