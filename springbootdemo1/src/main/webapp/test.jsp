<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2018/12/6 0006
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min1.3.5.js"></script>
    <script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min1.3.5.js"></script>
    <link href="${pageContext.request.contextPath}/themes1.3.5/icon.css" rel="stylesheet" type="text/css">
    <link href="${pageContext.request.contextPath}/themes1.3.5/default/easyui.css" rel="stylesheet" type="text/css">

    <script>
        $(function () {
            $("#getAllStudent").datagrid({
                url:'${pageContext.request.contextPath}/getAllStudent',
                pagination:true,
                onDblClickRow: function (rowIndex, rowData) {
                    $("#updateUser").dialog("open");
                    $("#updateId").val(rowData.id);
                    $("#updateName").val(rowData.name);
                    $("#updateAge").val(rowData.age);
                    $("#updateGender").val(rowData.gender);
                    console.log($("#updateGender").val())
                    if (rowData.gender==1){
                        $("#nan").prop("checked", "checked");
                    } else {
                        $("#nv").prop("checked", "checked");
                    }
                },
                columns:[[
                    {checkbox:true},
                    {field:'id',title:'编号'},
                    {field:'name',title:'姓名'},
                    {field:'age',title:'年龄'},
                    {field:'gender',title:'性别',formatter: function(value,row,index){
                            // console.log(row.gender);
                            if (row.gender==1){
                                return "男";
                            } else {
                                return "女";
                            }
                            }
                    }
                ]]
            });
// 开始时不显示
            $("#updateUser").dialog({
                title: 'update Dialog',
                width: 400,
                height: 200,
                closed: true,
                modal: true
            });

            $("#addUsers").dialog({
                title: 'add Dialog',
                width: 400,
                height: 200,
                closed: true,
                modal: true
            });

        });


        // 点击添加按钮，弹出dialog对话框
        function addButton() {
            $("#addUsers").dialog({
                closed: false
            });
        }

        // 点击添加提交按钮，发送添加请求
        function addUser() {
            $("#addUserForm").form("submit", {
                // 请求添加的controller方法
                url: "${pageContext.request.contextPath}/add",

                success: function (data) {
                    // 进行判断
                    if (data == "true") {
                        // 该方法有四个参数，标题，内容，图标，函数
                        $.messager.alert("tips", "add success!", "info");
                        $("#addUsers").dialog("close");
                        $("#getAllStudent").datagrid("reload");
                    } else {
                        $.messager.alert("tips", "add failure!", "warning");
                    }
                }
            })
        }


        // 点击搜索重新加载全部
        function search() {
            var name = $("#name").val();
            console.log(name);
            $("#getAllStudent").datagrid("load", {"name": name})
        }
        // 点击修改提交
        function update() {
            $("#updateUserForm").form("submit", {
                // 请求update方法
                url: "${pageContext.request.contextPath}/update",
                success: function (data) {
                    // 进行判断
                    if (data == "true") {
                        $.messager.alert("提示", "修改成功", "info");
                        $("#updateUser").dialog("close");
                        $("#getAllStudent").datagrid("reload");
                    } else {
                        $.messager.alert("提示", "修改失败", "warning");
                    }
                }
            })
        }
        // 假删除
        function deleteUser() {
            var selectAll = $("#getAllStudent").datagrid("getSelections");
            if (selectAll.length == 0) {
                $.messager.alert("提示","请选中要删除的内容","warning");
            }else {
                $.messager.confirm("提示","确定删除?",function (result) {
                    if (result) {
                        var selectIds = new Array();
                        for (var i = 0; i < selectAll.length; i++) {
                            selectIds[i]=selectAll[i].id;
                        }
                        // 发送ajax请求
                        $.ajax({
                            url: "${pageContext.request.contextPath}/mutilRemove",
                            data: "ids=" + selectIds,
                            success:function (data) {
                                if (data) {
                                    $.messager.alert("tips", "delete success!", "info");
                                    $("#getAllStudent").datagrid("reload");
                                }else {
                                    $.messager.alert("tips", "delete failure!", "warning");
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
    <%--展示全部的div--%>
    <div id="getAllStudent">
    </div>

        <div id="myDiv">
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addButton()"></a>
            <%--删除数据--%>
            <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="deleteUser()"></a><br/>
            <%--条件查询--%>
            <input type="text" id="name" name="name">
            <input type="button" id="button" value="search" onclick="search()">
        </div>

    <%--添加数据--%>
    <div id="addUsers">
        <form method="post" id="addUserForm">
            name:<input type="text" id="addUsername" name="name"><br/>
            age:<input type="text" id="addPassword" name="age"><br/>
            gender:
            <input type="radio" name="gender" value="1" >男
            <input type="radio" name="gender" value="0" >女<br/>

            <input type="button" value="button" id="addButton" onclick="addUser()">
        </form>
    </div>

<%--数据修改--%>
<div id="updateUser">
    <form method="post" id="updateUserForm">
        <input type="hidden" id="updateId" name="id"><br/>
        name:<input type="text" id="updateName" name="name"><br/>
        age:<input type="text" id="updateAge" name="age"><br/>
        gender:
        <span id="updateGender">
        <input type="radio" name="gender" value="1" id="nan">男
        <input type="radio" name="gender" value="0" id="nv">女<br/>
        </span>
        <input type="button" value="button" id="updateButton" onclick="update()">
    </form>
</div>



</body>
</html>
