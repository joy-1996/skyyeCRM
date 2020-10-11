<%@ page import="com.whvcse.pojo.Employees" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2020/6/3
  Time: 11:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%
    Employees employees=(Employees) session.getAttribute("employees");
%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Insert title here</title>
    <link  href="${pageContext.request.contextPath}/css/bootstrap.min.css"  rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/js/jQuery-1.12.4.js"></script>
    <script src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
    <!--bootstrap分页所需的插件-->
    <link href="${pageContext.request.contextPath}/javascript/bootstrap-table/bootstrap-table.css"  rel="stylesheet"/>
    <script src="${pageContext.request.contextPath}/javascript/bootstrap-table/bootstrap-table.js"></script>
    <script src="${pageContext.request.contextPath}/javascript/bootstrap-table/locale/bootstrap-table-zh-CN.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/javascript/My97DatePicker/WdatePicker.js"></script>
</head>
<body>
<shiro:hasRole name="user">
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal2">添加企业客户</button>
</shiro:hasRole>
<!--添加的模态-->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel2">请输入添加的企业客户信息</h4>
            </div>
            <div class="modal-body">
                <form id="aform">
                    <div class="form-group">
                        客户名称：<input type="text" class="form-control" name="cusName" id="a_cusName"/>
                        地址：<input type="text" class="form-control" name="address" id="a_address"/>
                        联系人：<input type="text" class="form-control" name="contact" id="a_contact"/>
                        联系电话：<input type="text" class="form-control" name="tel" id="a_tel"/>
                        电子邮箱：<input type="text" class="form-control" name="email" id="a_email"/>
                        合作专员名称：<select class="form-control" id="a_empid" name="eid">
                                         <option value="<%=employees.getEmpid()%>"><%=employees.getUsername()%></option>
                                     </select>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭</button>
                <button type="button" class="btn btn-primary" onclick="add()" >确认添加</button>
            </div>
        </div>
    </div>
</div>


<table id="mytable">
</table>
<!--//修改产品的模态框-->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">$times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel">修改企业客户信息</h4>
            </div>
            <div class="modal-body">
                <form id="myform">
                    客户ID：<input type="text" class="form-control" name="cid" id="p_cid" readonly="readonly"/>
                    客户名称：<input type="text" class="form-control" name="cusName" id="p_cusName"/>
                    客户地址：<input type="text" class="form-control" name="address" id="p_address"/>
                    联系人：<input type="text" class="form-control" name="contact" id="p_contact"/>
                    联系电话：<input type="text" class="form-control" name="tel" id="p_tel"/>
                    电子邮箱：<input type="text" class="form-control" name="email" id="p_email" >
                </form>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭窗口</button>
              <button type="button" class="btn btn-primary" onclick="doEidt()">确认修改</button>
         </div>
     </div>
   </div>

</div>
<div class="modal fade" id="myModal3" tabindex="-1" role="dialog" aria-labelledby="myModalLabel3">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                    <span aria-hidden="true">$times;</span>
                </button>
                <h4 class="modal-title" id="myModalLabel3">修改企业客户信息</h4>
            </div>
            <div class="modal-body">
                <form id="umyform">
                                <input type="text" name="r_empid" id="r_empid" value="<%=employees.getEmpid()%>" hidden>
                              <input type="text" name="cid" id="u_cid" hidden/>
                    客户名称：<input type="text" class="form-control"  id="u_cusName" readonly="readonly"/>
                    合作专员名称：<select class="form-control" id="u_empid" name="empid">
                                     <option value="0">请输入合作专员名称</option>
                                  </select>
                </form>
            </div>
        </div>
        <div class="modal-footer">
            <button type="button" class="btn btn-default" data-dismiss="modal">关闭窗口</button>
            <button type="button" class="btn btn-primary" onclick="sure()">确认修改</button>
        </div>
    </div>
</div>
</div>
<script>
    var xmlhttp;
    if (window.XMLHttpRequest) {
        xmlhttp = new XMLHttpRequest();
    } else {
        xmlhttp = new ActiveXObject("Microsoft.XMLHTTP");
    }
    $(function() {
        var t = new Tableinit();
        t.init();
        getBorth();
    })
    var Tableinit = function() {
        var oTableinit=new Object();
        oTableinit.init = function() {
            $("#mytable").bootstrapTable({
                //请求后台的URL
                url: '${pageContext.request.contextPath}/searchAllCustomersByPageAndId  ',
                method : 'post',//请求方式
                contentType : "application/x-www-form-urlencoded; charset=UTF-8",
                //隔行变色
                striped : true,
                height: $(window).height() - 200,
                //工具按钮用那个容器
                //toolbar:'toolbar'
                cache : false,//是都使用缓存，默认为true，所有一般情况下需要设置一下这个属性
                pagination : true,//是否显示分页
                //传递参数
                queryParams :oTableinit.queryParams,
                sidePagination : "server",//分页方式
                /*  sortble:true,//是否启用排序
                sortOrder:"asc",//排序方式  */
                pageNumber : 1,//初始化加载第一页
                pageSize : 5,
                pageList : [ 10, 25, 50, 100 ],//选择分页的行数
                //search:true,是否显示表格搜索，
                showColumns : true,//是否显示所有的列
                showRefresh : true,//是否显示刷新按钮
                minimumCountColumns :2,//最少允许的列数
                clickToSelect : true,//是否启用点击选中行
                uniqueId : "ID",
                //showToggle:true,
                //carView:false,
                //detailView:false,
                columns : [{
                    checkbox : true
                }, {
                    field : 'cid',
                    title : '客户ID'
                }, {
                    field : 'cusName',
                    title : '客户名称'
                }, {
                    field : 'address',
                    title : '地址'
                }, {
                    field : 'contact',
                    title : '联系人'
                }, {
                    field : 'tel',
                    title : '联系电话'
                }, {
                    field : 'email',
                    title : '电子邮箱'
                }
            <shiro:hasRole name="user">
                , {
                    field : 'cid',
                    title : '操作',
                    formatter:actionFormatter
                }
            </shiro:hasRole>
                ]
            });
        };
        //得到查询的参数
        oTableinit.queryParams = function (params) {
            var temp = {
                limit : params.limit,//页大小
                offset :params.offset,//页码
                eid:${employees.empid}
            };
            return temp;
        };
        return oTableinit;
    };

    function actionFormatter(value,row,index){
        return "<a href='#' onclick='edit("+value+")' data-toggle='modal' data-target='#myModal'>"
            +"<span class='glyphicon glyphicon-pencil'></span>"+
            "</a>"
            +"&nbsp;&nbsp;&nbsp;&nbsp;"+
            "<a href='#' onclick='del("+value+")'>"
            +"<span class='glyphicon glyphicon-remove'></span></a>"
            +"&nbsp;&nbsp;&nbsp;&nbsp;"+
            "<a href='#' onclick='preTransferEmployee("+value+")' data-toggle='modal' data-target='#myModal3'>"
         +"<span class='glyphicon glyphicon-cog'></span></a>";
    }
    function preTransferEmployee(id) {
        $.ajax({
            url : "${pageContext.request.contextPath}/preUpdateCustomer",
            data : {
                "cid"    : id
            },
            async :false,
            dataType:"json",
            success : function(p) {
                $("input[id=u_cusName]").val(p.cusName);
                $("input[id=u_cid]").val(p.cid);
            }
        })
    }
    function searchProduct(){
        $("#mytable").bootstrapTable('refresh');
    }
    /*转移客户*/
    function  sure() {
        $.ajax({
            url : "${pageContext.request.contextPath}/transferEmployee",
            data : {
                "eid":$("#u_empid").val(),
                "cid":$("#u_cid").val(),
            },
            success : function(msg) {
                if(msg=="success"){
                    $('#myModal3').modal('hide');
                    $('#mytable').bootstrapTable('refresh');
                }
            }
        })
    }
    function edit(id) {
        $.ajax({
            url : "${pageContext.request.contextPath}/preUpdateCustomer",
            data : {
                "cid"    : id
            },
            async :false,
            dataType:"json",
            success : function(p) {
                $("input[id=p_cid]").val(p.cid);
                $("input[id=p_cusName]").val(p.cusName);
                $("input[id=p_address]").val(p.address);
                $("input[id=p_tel]").val(p.tel);
                $("input[id=p_contact]").val(p.contact);
                $("input[id=p_email]").val(p.email);
            }
        })
    }

    //确认修改
    function doEidt(){
        $.ajax({
            url : "${pageContext.request.contextPath}/updateCustomer",
            data : $("#myform").serialize(),
            success : function(msg) {
                if(msg=="success"){
                    $('#myModal').modal('hide');
                    $('#mytable').bootstrapTable('refresh');
                }
            }
        })
    }
    function add(){
        $.ajax({
            url : "${pageContext.request.contextPath}/addCustomer",
            data : $("#aform").serialize(),
            success : function(msg) {
                if(msg=="success"){
                    $('#myModal2').modal('hide');
                    $('#myModal2').bootstrapTable('refresh');
                    $('#mytable').bootstrapTable('refresh');
                }
            }
        })
    }

    function del(id) {
        $.ajax({
                url : "${pageContext.request.contextPath}/deleteCustomerById",
            data : {
                "cid" : id
            },
            method : 'post',//请求方式
            success :function(msg) {
                if(msg=="success"){
                    $('#mytable').bootstrapTable('refresh');
                }else{
                    alert("该客户删除失败")
                }
            }
        })
    }
   /* /!*选中角色和权限*!/
    //选中供应商和目录
    function roleAndPermission(id,obj){
        var arr=obj.options;
        for(var i=0; i<arr.length;i++){
            if(arr[i].value == id){
                arr[i].selected=true;
            }
        }
    }*/
    function getBorth(){
        xmlhttp.open("post","${pageContext.request.contextPath}/selectNotInEmp?empid=<%=employees.getEmpid()%>",false);
        xmlhttp.onreadystatechange = function() {
            if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
                var info = xmlhttp.responseText;
                var arr = JSON.parse(info);
                var emp = document.getElementById("u_empid");
                for (var i = 0; i < arr.length; i++) {
                    emp.add(new Option(arr[i].username,arr[i].empid));
                }
            }
        }
        xmlhttp.send();
    }
</script>
</body>
</html>
