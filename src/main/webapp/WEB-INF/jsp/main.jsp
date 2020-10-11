<%@ page import="com.whvcse.pojo.Employees" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2020/5/28
  Time: 11:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link  rel="stylesheet" href="${pageContext.request.contextPath}/css/bootstrap.min.css">
    <script src="${pageContext.request.contextPath}/js/jQuery-1.12.4.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <!--bootstrap分页所需的插件-->
    <style type="text/css">
        html,body {
            width: 100%;
            height: 100%;
        }
        #left {
            height: 100%;
        }
        h3{
            margin-top:8px;
            text-align:center;
        }
        h4{
            text-align:center;
        }
        a:hover {
            color: #23527c;
            text-decoration: none;
        }
        a{
            text-decoration:none;
        }
    </style>
</head>
<body>
<%
    Employees employees=(Employees)session.getAttribute("employees");
%>
<div class="container-fluid" style="height: 100%;">
    <div class="row">
        <div class="col-lg-12">
            <nav class="navbar navbar-default">
                <div class="navbar-header">
                    <div class="navbar-brand">
                        <h3>电商管理系统</h3>
                    </div>
                </div>
                <div class="col-md-2 col-md-offset-10" style="margin-top: -42px">
                    <button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal2">修改密码</button>
                    <button type="button" class="btn btn-primary">${employees.username}</button>
                    <a href="loginout">退出</a>
                </div>
            </nav>
        </div>
    </div>
    <div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title" id="myModalLabel2">请输入修改信息</h4>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="form-group">
                            <input class="form-control" type="password" name="password" id="password" placeholder="请输入将要的密码"/>
                                <br/>
                            <input class="form-control" type="password"   placeholder="请再次确认密码"/>
                        </div>
                        <div class="modal-footer">
                            <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                            <button  class="btn btn-primary" onclick="doEidt()">确认修改</button>
                        </div>
                    </form>
                </div>

            </div>
        </div>
    </div>
    <div class="row" style="height:90%;">
        <div class="col-lg-2" id="left">
            <div id="accordion" class="panel-group" role="tablist" aria-multiselectable="true">
                <!-- //合作专员信息管理 -->
             <shiro:hasRole name="admin">
                <div class="panel-default">
                    <div class="panel-heading" role="tab" id="headingOne">
                        <h4 class="panel-title">
                            <a role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseOne" aria-expanded="true"
                               aria-controls="collapseOne">合作专员信息管理</a>
                        </h4>
                    </div>
                    <div class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingOne" id="collapseOne">
                        <h4 class="panel-body">
                            <div class="col-lg-12">
                                <a class="btn btn-block btn-info " href="empManage" target="showAll">合作专员信息管理</a>
                            </div>
                        </h4>
                    </div>
                </div>
             </shiro:hasRole>
                <!-- //供应商操作 -->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingTwo">
                        <h4 class="panel-title">
                            <a  class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseTwo" aria-expanded="false"
                                aria-controls="collapseTwo">企业客户信息管理</a>
                        </h4>
                    </div>
                    <div  id="collapseTwo" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingTwo">
                        <div class="panel-body">

                        <shiro:hasRole name="admin">
                            <h4 class="col-lg-12">
                                <a class="btn btn-block btn-info " href="customerByAdmin" target="showAll">企业客户信息管理</a>
                            </h4>
                        </shiro:hasRole>
                        <shiro:hasRole name="user">
                                <h4 class="col-lg-12">
                                    <a class="btn btn-block btn-info " href="customer" target="showAll">企业客户信息管理</a>
                                </h4>
                        </shiro:hasRole>
                        </div>
                    </div>
                </div>
                <!-- //目录 -->
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingThree">
                        <h4 class="panel-title">
                            <a  class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseThree" aria-expanded="false"
                                aria-controls="collapseThree">客户拜访记录管理</a>
                        </h4>
                    </div>
                    <div  id="collapseThree" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                        <div class="panel-body">
                                <shiro:hasRole name="admin">
                                <h4 class="col-lg-12">
                                    <a class="btn btn-block btn-info " href="searchCustomerVisit" target="showAll">客户拜访记录管理</a>
                                </h4>
                            </shiro:hasRole>
                            <shiro:hasRole name="user">
                                <h4 class="col-lg-12">
                                    <a class="btn btn-block btn-info " href="customerVisit" target="showAll">客户拜访记录管理</a>
                                </h4>
                            </shiro:hasRole>
                            <%--</shiro:hasPermission>--%>
                            <!-- <h4 class="col-lg-12" style="margin-top: 10px;">
                                <a class="btn btn-info" href="" target="showAll">增加目录</a>
                            </h4> -->
                        </div>
                    </div>
                </div>
                <!-- //订单管理 -->
                <%--<div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingFive">
                        <h4 class="panel-title">
                            <a  class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseFive" aria-expanded="false"
                                aria-controls="collapseFive">合作专员客户管理</a>
                        </h4>
                    </div>
                    <div  id="collapseFive" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingFive">
                        <div class="panel-body">
                            <h4 class="col-lg-12" style="margin-top: 10px;">
                                <a class="btn btn-block btn-info" href="customerAndEmployee" target="showAll">合作专员客户管理</a>
                            </h4>
                        </div>
                    </div>
                </div>--%>
                <!-- //权限管理 -->
            <shiro:hasRole name="supperAdmin">
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingSix">
                        <h4 class="panel-title">
                            <a   class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSix " aria-expanded="false"
                                 aria-controls="collapseSix">权限管理</a>
                        </h4>
                    </div>
                    <div  id="collapseSix" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingSix">
                        <div class="panel-body">
                            <h4 class="col-lg-12">
                                <a class="btn btn-block btn-info " href="permission" target="showAll">添加权限</a>
                            </h4>
                        </div>
                    </div>
                </div>
            </shiro:hasRole>
                <!-- //角色 -->
             <shiro:hasRole name="supperAdmin">
                <div class="panel panel-default">
                    <div class="panel-heading" role="tab" id="headingSeven">
                        <h4 class="panel-title">
                            <a  class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseSeven" aria-expanded="false"
                                aria-controls="collapseThree">角色权限管理</a>
                        </h4>
                    </div>
                    <div  id="collapseSeven" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingThree">
                        <div class="panel-body">

                            <h4 class="col-lg-12">
                                <a class="btn btn-block btn-info " href="role" target="showAll">角色管理</a>
                            </h4>

                        </div>
                    </div>
                </div>
             </shiro:hasRole>
                <shiro:hasRole name="supperAdmin">
                    <div class="panel panel-default">
                        <div class="panel-heading" role="tab" id="headingeight">
                            <h4 class="panel-title">
                                <a  class="collapsed" role="button" data-toggle="collapse" data-parent="#accordion" href="#collapseEight" aria-expanded="false"
                                    aria-controls="collapseThree">用户角色管理</a>
                            </h4>
                        </div>
                        <div  id="collapseEight" class="panel-collapse collapse" role="tabpanel" aria-labelledby="headingeight">
                            <div class="panel-body">
                                <h4 class="col-lg-12">
                                    <a class="btn btn-block btn-info " href="employeeAndRole" target="showAll">角色分配管理</a>
                                </h4>
                            </div>
                        </div>
                    </div>
                </shiro:hasRole>
            </div>
        </div>
        <div class="col-lg-10" id="right" style="height:100%">
            <iframe name="showAll" href="${pageContext.request.contextPath}/welcome" width="100%" height="100%" scrolling="no" frameBorder="0">
            </iframe>
        </div>
    </div>
</div>

<script>
    function doEidt(){
        $.ajax({
            url : "${pageContext.request.contextPath}/updatePassword",
            method : 'get',
            data : {
               "empid":<%=employees.getEmpid()%>,
                "password":$("#password").val(),
                "username":<%=employees.getUsername()%>
            },
            success : function(msg) {
                if(msg=="success"){
                    alert("修改成功")
                    $('#myModal2').modal('hide');
                }
            }
        })
    }
</script>
</body>
</html>
