<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2020/5/28
  Time: 11:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>登录首页</title>
    <link  href="${pageContext.request.contextPath}/css/bootstrap.min.css" rel="stylesheet"/>
    <style type="text/css">
        html{
            width: 100%;
            height:100%;
        }
        body{
            width: 100%;
            height:100%;
            background:url(${pageContext.request.contextPath}/img/background.jpg);
            background-repeat:no-repeat;
            background-size:100% 100%;
            position:absolute;
        }
        .main {
            margin:0 auto;
            width: 600px;
            height: 400px;
            position: relative;
            top:30%;
        }
        .form-group{
            width: 100%;
        }
        h3{
            text-align:center;
        }
    </style>
</head>
<body>
<h3>欢迎来到电商管理系统</h3>
<div class="main">
    <div class="col-lg-offset-5">
        <h1 style="color: red ;margin-right: 10px">${msg}</h1>
    </div>

    <form action="${pageContext.request.contextPath}/login" method="post" class="form-horizontal">
        <div class="form-group" >
            <label  class="col-lg-2 control-label">名称</label>
            <div class="col-lg-10">
                <input type="text" class="form-control" name="username" id="username" placeholder="请输入名称">
            </div>
        </div>

        <div class="form-group" style="margin-top:50px;">
            <label  class="col-lg-2 control-label">密码</label>
            <div class="col-lg-10">
                <input type="password" class="form-control" name="password" id="password" placeholder="请输入密码">
            </div>
        </div>
        <div class="form-group" style="margin-top:50px;">
            <div class="col-lg-offset-4  col-lg-8">
                <button type="submit" class="btn btn-success">登录</button>
                <button  type="reset" class="btn btn-success col-lg-offset-3" onclick="adminLogin()">注册</button>
            </div>
        </div>
    </form>
</div>
<script src="${pageContext.request.contextPath}/js/jQuery-1.12.4.js"></script>
<script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
<script>
    function adminLogin(){
        location.href="register"
    }
</script>
</body>
</html>