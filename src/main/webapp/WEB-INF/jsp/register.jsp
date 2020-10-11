<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2020/5/27
  Time: 16:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册页面</title>
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
        height: 600px;
        position: relative;
        top:100px;
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
<div class="main">
    <h3>注册账号</h3>
    <form action="${pageContext.request.contextPath}/insertEmployee" method="post" class="form-horizontal">
        <div class="form-group" >
            <label  class="col-lg-2 control-label">名称</label>
            <div class="col-lg-10">
                <input type="text" class="form-control" name="username" id="username" placeholder="请输入名称">
            </div>
        </div>

        <div class="form-group" style="margin-top:20px;">
            <label  class="col-lg-2 control-label">密码</label>
            <div class="col-lg-10">
                <input type="password" class="form-control" name="password" id="password" placeholder="请输入密码">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label  class="col-lg-2 control-label">电话号码</label>
            <div class="col-lg-10">
                <input type="text" class="form-control" name="tel" id="tel" placeholder="请输入电话号码">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label  class="col-lg-2 control-label">姓名</label>
            <div class="col-lg-10">
                <input type="text" class="form-control" name="name" id="name" placeholder="请输入真实姓名">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <label  class="col-lg-2 control-label">邮箱</label>
            <div class="col-lg-10">
                <input type="text" class="form-control" name="email" id="email" placeholder="请输入邮箱地址">
            </div>
        </div>
        <div class="form-group" style="margin-top:20px;">
            <div class="col-lg-offset-5  col-lg-8">
                <button type="submit" class="btn btn-success">确定提交</button>
            </div>
        </div>
       <%-- 请输入用户名称<input class="form-control" type="text" value="" name="username"/>
        <br/>
        请输入用户密码<input  class="form-control" type="password" value="" name="password"/>
        <br/>
        请输入联系电话<input class="form-control" type="text" value="" name="tel"/>
        <br/>
        请输入真实姓名<input class="form-control" type="text" value="" name="name"/>
        <br/>
        请输入密码<input class="form-control" type="text" value="" name="email"/>
        <br/>--%>
    </form>
</div>
    <script src="${pageContext.request.contextPath}/js/jQuery-1.12.4.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
</body>
</html>
