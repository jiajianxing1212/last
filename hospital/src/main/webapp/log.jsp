<%--
  Created by IntelliJ IDEA.
  User: ysy
  Date: 2018/12/6
  Time: 13:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min1.3.5.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min1.3.5.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/easyui-lang-zh_CN1.3.5.js"></script>
    <link href="${pageContext.request.contextPath}/themes1.3.5/icon.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/themes1.3.5/default/easyui.css" rel="stylesheet" type="text/css">
    <script>
        $(function () {
            $("#myDatagrid").datagrid({
                title:"日志列表",
                url:"${pageContext.request.contextPath}/log/getAll",
                // 让datagrid做分页
                pagination:true,
                // 一页显示自定义条数，pageList是必须有其值
                pageSize:8,
                pageList:[8,15,30],
                // 行号列
                rownumbers:true,
                checkOnSelect:true,
                columns:[[
                    // 一个对象表示的是一列 一个{}一列
                    {checkbox:true},
                    {title:"日志编号",field:"id"},
                    {title:"日志类型",field:"type"},
                    {title:"操作人",field:"oprater"},
                    {title:"内容",field:"content"},
                    {title:"时间",field:"logDate"}
                ]],
                toolbar:"#myTools"
            });

        });
        function doSearch() {
            //获取到用户输入的用户名
            var name = $("#name").val();
            //调用datagrid的load方法，把用户输入的用户名传递到后台
            //带着参数名为username的参数，重新往后台发送请求，并且重新解析响应回来的数据
            $('#myDatagrid').datagrid('load',{
                "oprater": name
            });
        }
        // 批量删除
        function doMultiDelete() {
            // 1.获取到选中的内容
            // 2.判断是否有选中的内容
            // if（选中），提示是否删除，否则，提示选择删除内容

            // 通过getSelections方法获取选中的内容
            var selectedRows = $("#myDatagrid").datagrid("getSelections");
            if(selectedRows.length==0){
                // 没有选中内容，提示一下
                $.messager.alert("批量删除框","没有选中内容，请重新选择","info");
            }else {
                $.messager.confirm("确认删除框","确认删除吗？",function (result) {
                    if(result){
                        // 把选中的内容获取到的id放到一个数组中
                        var selectId = new Array(selectedRows.length);
                        for(var i=0;i<selectedRows.length;i++){
                            selectId[i]=selectedRows[i].id;
                        }
                        // 发送ajax请求，后台进行批量删除操作
                        $.ajax({
                            url:"multiDelete",
                            data:{"ids":selectId},//jQuery深度序列化
                            traditional:true,
                            success:function (data) {
                                if(data){
                                    $.messager.alert("提示框","删除成功","info");
                                    $("#myDatagrid").datagrid("reload");
                                }else {
                                    $.messager.alert("提示框","删除失败，请确认","warning");
                                }
                            }
                        })
                    }
                });
            }
        }
    </script>
</head>
<body>
操作人：<input id="name">
<input type="button" value="搜索" onclick="doSearch()"/>
<table id="myDatagrid"></table>

<div id="myTools">
    <a class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="doMultiDelete()">批量删除</a>
</div>

</body>
</html>

