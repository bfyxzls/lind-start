<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>userlist</title>
</head>
<body>
<table border = "1">
    <tr>
        <td>名称</td>
        <td>email</td>
        <td>操作</td>
    </tr>
    <#list userList as user>
        <tr>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>
                <a href = "">修改</a>
                <a href = "">删除</a>
            </td>
        </tr>
    </#list>
</table>

<a href = "addpage">新增</a>

</body>
</html>