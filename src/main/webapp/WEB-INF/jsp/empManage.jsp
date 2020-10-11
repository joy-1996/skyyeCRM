<%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2020/6/3
  Time: 9:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
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
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal2">添加企业合作专员</button>

<!--添加的模态-->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel2">请输入添加企业合作专员的信息</h4>
            </div>
            <div class="modal-body">
                <form id="aform">
                    <div class="form-group">
                        用户名称：<input type="text" class="form-control" name="username" id="username"/>
                        密码：<input type="password" class="form-control" name="password" id="password"/>
                        联系电话：<input type="text" class="form-control" name="tel" id="tel"/>
                        真实姓名：<input type="text" class="form-control" name="name" id="name"/>
                        电子邮箱：<input type="text" class="form-control" name="email" id="email"/>
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
                <h4 class="modal-title" id="myModalLabel">修改角色权限</h4>
            </div>
            <div class="modal-body">
                <form id="myform">
                    用户名称：<input type="text" class="form-control" name="empid" id="p_empid" readonly="readonly"/>
                    用户名称：<input type="text" class="form-control" name="username" id="p_username"/>
                    密码：<input type="password" class="form-control" name="password" id="p_password"/>
                    联系电话：<input type="text" class="form-control" name="tel" id="p_tel"/>
                    真实姓名：<input type="text" class="form-control" name="name" id="p_name"/>
                    电子邮箱：<input type="text" class="form-control" name="email" id="p_email"/>

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
                url: '${pageContext.request.contextPath}/searchAllEmployeesByPage',
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
                    field : 'empid',
                    title : '员工ID'
                }, {
                    field : 'username',
                    title : '用户名称'
                },
                    {
                    field : 'password',
                    title : '密码'
                },
                    {
                    field : 'tel',
                    title : '联系电话'
                }, {
                    field : 'name',
                    title : '姓名'
                }, {
                    field : 'email',
                    title : '电子邮箱'
                },{
                    field : 'empid',
                    title : '操作',
                    formatter:actionFormatter
                }]
            });
        };
        //得到查询的参数
        oTableinit.queryParams = function (params) {
            var temp = {
                limit : params.limit,//页大小
                offset :params.offset,//页码
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
            +"<span class='glyphicon glyphicon-remove'></span></a>";
    }

    function searchProduct(){
        $("#mytable").bootstrapTable('refresh');
    }

    function edit(id) {
        $.ajax({
            url : "${pageContext.request.contextPath}/preUpdateEmployee",
            data : {
                "empid" : id
            },
            async :false,
            dataType:"json",
            success : function(p) {
                $("input[id=p_empid]").val(p.empid);
                $("input[id=p_username]").val(p.username);
                $("input[id=p_password]").val(p.password);
                $("input[id=p_tel]").val(p.tel);
                $("input[id=p_name]").val(p.name);
                $("input[id=p_email]").val(p.email);
            }
        })
    }

    //确认修改
    function doEidt(){
        $.ajax({
            url : "${pageContext.request.contextPath}/updateEmployee",
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
            url : "${pageContext.request.contextPath}/addEmployee",
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
            url : "${pageContext.request.contextPath}/deleteEmployeeById",
            data : {
                "empid" : id
            },
            method : 'post',//请求方式
            success :function(msg) {
                if(msg=="success"){

                    $('#mytable').bootstrapTable('refresh');
                }else{
                    alert("该权限不能删除")
                }
            }
        })
    }
    /*选中角色和权限*/
    //选中供应商和目录
    function roleAndPermission(id,obj){
        var arr=obj.options;
        for(var i=0; i<arr.length;i++){
            if(arr[i].value == id){
                arr[i].selected=true;
            }
        }
    }

    function getBorth(){
        xmlhttp.open("post","${pageContext.request.contextPath}/selectRoleAndPermissionById",false);
        xmlhttp.onreadystatechange = function() {
            if(xmlhttp.readyState == 4 && xmlhttp.status == 200){
                var info = xmlhttp.responseText;
                var arr = JSON.parse(info);
                var roleList=arr[1];
                var perList=arr[2];
                var b_pro = document.getElementById("a_rid");
                var b_cate = document.getElementById("a_pid");
                for (var i = 0; i < roleList.length; i++) {
                    b_pro.add(new Option(roleList[i].rolename,roleList[i].rid));
                }
                for (var i = 0; i < perList.length; i++) {
                    b_cate.add(new Option(perList[i].permission_name, perList[i].pid));
                }
                //模态框中的数据
                b_pro = document.getElementById("rid");
                b_cate = document.getElementById("pid");
                for (var i = 0; i < roleList.length; i++) {
                    b_pro.add(new Option(roleList[i].rolename,roleList[i].rid));
                }
                for (var i = 0; i < perList.length; i++) {
                    b_cate.add(new Option(perList[i].permission_name,perList[i].pid));
                }
            }
        }
        xmlhttp.send();
    }
</script>
</body>
</html>