    <%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 2020/5/29
  Time: 8:31
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
<button type="button" class="btn btn-primary" data-toggle="modal" data-target="#myModal2">添加</button>

<!--添加的模态-->
<div class="modal fade" id="myModal2" tabindex="-1" role="dialog" aria-labelledby="myModalLabel2">
    <div class="modal-dialog" role="document">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
                <h4 class="modal-title" id="myModalLabel2">请输入添加角色的信息</h4>
            </div>
            <div class="modal-body">
                <form id="aform">
                    <div class="form-group">
                        权限名称：<input type="text" class="form-control" name="rolename"/>
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
                <h4 class="modal-title" id="myModalLabel">修改角色</h4>
            </div>
            <div class="modal-body">
                <form id="myform">
                    <div class="form-group">
                        角色ID：<input type="text" class="form-control" id="rid" name="rid" readonly/>
                        角色名称：<input type="text" class="form-control" name="rolename"/>
                    </div>
                </form>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">关闭窗口</button>
                <button type="button" class="btn btn-primary" onclick="doEidt()">确认修改</button>
            </div>
        </div>
    </div>
</div>
<script>
    $(function() {
        var t = new Tableinit();
        t.init();
        /*getBorth();*/
    })
    var Tableinit = function() {
        var oTableinit=new Object();
        oTableinit.init = function() {
            $("#mytable").bootstrapTable({
                //请求后台的URL
                url: '${pageContext.request.contextPath}/selectAllRoleByPage',
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
                    field : 'rid',
                    title : '角色id'
                }, {
                    field : 'rolename',
                    title : '角色名称'
                },{
                    field : 'rid',
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
            +"<span class='glyphicon glyphicon-remove'></span></a>"
             +"&nbsp;&nbsp;&nbsp;&nbsp;"+
             "<a href='addPermissionToRole?rid="+value+"' >"
            +"<span class='glyphicon glyphicon-pencil'></span></a>";
    }

    function searchProduct(){
        $("#mytable").bootstrapTable('refresh');
    }

    function edit(id) {
        $.ajax({
            url : "${pageContext.request.contextPath}/selectRoleById",
            data : {
                "rid"    : id
            },
            async :false,
            dataType:"json",
            success : function(p) {
                $("input[name=rid]").val(p.rid);
                $("input[name=rolename]").val(p.rolename);
            }
        })
    }

    //确认修改
    function doEidt(){
        $.ajax({
            url : "${pageContext.request.contextPath}/updateRole",
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
            url : "${pageContext.request.contextPath}/addRole",
            data : $("#aform").serialize(),
            success : function(msg) {
                if(msg=="success"){
                    $('#myModal2').modal('hide');
                    $('#mytable').bootstrapTable('refresh');
                }
            }
        })
    }
    function del(id) {
        $.ajax({
            url : "${pageContext.request.contextPath}/deleteRoleById",
            data : {
                "rid" : id
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
</script>
</body>
</html>
