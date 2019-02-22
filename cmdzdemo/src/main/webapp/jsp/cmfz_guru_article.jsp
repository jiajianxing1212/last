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
            // console.log(111);
            $("#getUsers").datagrid({
                title: "Message of Department",
                toolbar: "#myDiv",// 作为datagrid的属性，包在该组件内部
                pagination: true,// 分页
                rownumbers: true,// 显示行序号（索引）
                url: "${pageContext.request.contextPath}/article/getAll",
                // 双击一行数据时（datagrid双击事件），弹出该行的数据，并显示每列的原有内容
                onDblClickRow: function (rowIndex, rowData) {
                    $("#updateUser").dialog("open");

                    $("#updateUserId").val(rowData.articleId);
                    $("#updateImageUrl").val(rowData.articleImage);
                    $("#updatearticleName").val(rowData.articleName);
                    $("#updatearticleContent").val(rowData.articleContent);
                    $("#updateguruId").val(rowData.guruId);

                },
                pageSize: 5,// 设置首页初始显示条数
                pageList: [5, 10, 15, 20],// 可选择的每页显示的条数
                columns: [[
                    {checkbox: true},// 显示复选框
                    {title: "articleId", field: "articleId"},
                    {title: "articleImage", field: "articleImage",
                        formatter: function (value,row, index) {
                            return "<img style='width:35;height:20' src=${pageContext.request.contextPath}"+value+">"
                        }},
                    {title: "articleName", field: "articleName"},
                    {title: "articleContent", field: "articleContent"},
                    {title: "articleDate", field: "articleDate"},
                    {title: "guruid", field: "guruId"},
                    {title:"状态",field:"articleStatus",
                        formatter:function (value,row,index) {
                            // alert(111);
                            if(row.articleStatus==2){
                                return "<a href='' style=\"color: #449d44\">正常展示</a>"
                            }else if (row.articleStatus==1) {
                                return "<a href='' style=\"color: #CC2222\">文章下架</a>"
                            }else {
                                return "已删除";
                            }
                        }
                    }
                ]],
                /*修改状态 开始*/
                onClickCell:function (rowIndex, field, value) {
                    if (field == "articleStatus") {
                        var articleId = $(this).datagrid("getRows")[rowIndex].articleId;
                        var articleStatus = $(this).datagrid("getRows")[rowIndex].articleStatus;
                        //console.log(bannerId)
                        //console.log(bannerState)
                        if(articleStatus==2){
                            articleStatus = 1
                        }else {
                            articleStatus = 2
                        }
                        $.ajax({
                            url:"${pageContext.request.contextPath}/article/updateStatus",
                            type: "post",
                            data: {"articleId":articleId,"articleStatus":articleStatus},
                            success: function (data) {
                                //alert(data == true);
                                if (data) {
                                    $.messager.alert("提示框", "状态修改成功", "info");
                                    $("#getUsers").datagrid("reload");
                                } else {
                                    $.messager.alert("提示框", "状态修改失败", "info");
                                }
                            }
                        })
                    }
                },
                /*修改状态结束*/
            });

            $("#addUsers").dialog({
                title: 'add Dialog',
                width: 400,
                height: 200,
                closed: true,
                modal: true
            });

            $("#updateUser").dialog({
                title: 'update Dialog',
                width: 400,
                height: 200,
                closed: true,
                modal: true
            });
        });

        // 点击搜索按钮进行搜索,重新加载全部数据
        function search() {
            var name = $("#articleName").val();
            $("#getUsers").datagrid("load", {"articleName": name})
        }

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
                url: "${pageContext.request.contextPath}/article/add",
                success: function (data) {
                    // 进行判断
                    if (data == "true") {
                        // 该方法有四个参数，标题，内容，图标，函数
                        $.messager.alert("tips", "add success!", "info");
                        $("#addUsers").dialog("close");
                        $("#getUsers").datagrid("reload");

                    } else {
                        $.messager.alert("tips", "add failure!", "warning");
                    }
                }
            })
        }

        // 点击修改提交按钮，发送修改请求
        function updateUser() {
            $("#updateUserForm").form("submit", {
                // 请求update的controller方法
                url: "${pageContext.request.contextPath}/banner/update",
                success: function (data) {
                    // 进行判断
                    //alert(0000)
                    if (data == "true") {
                        $.messager.alert("tips", "update success", "info");
                        $("#updateUser").dialog("close");
                        $("#getUsers").datagrid("reload");
                    } else {
                        $.messager.alert("tips", "update failure", "warning");
                    }
                }
            })
        }

        // 删除
        function deleteUser() {
            var selectRows = $("#getUsers").datagrid("getSelections");
            if (selectRows.length == 0) {
                $.messager.alert("tips", "not found message!!!", "warning");
            } else {
                $.messager.confirm("tip", "Delete?Are you sure?", function (result) {
                    if (result) {
                        var selectIds = new Array();
                        for (var i = 0; i < selectRows.length; i++) {
                            selectIds[i] = selectRows[i].articleId;
                        }
                        // 发送ajax请求
                        $.ajax({
                            url: "${pageContext.request.contextPath}/article/multiRemove",
                            data: "ids=" + selectIds,
                            success: function (data) {
                                if (data) {
                                    $.messager.alert("tips", "delete success!", "info");
                                    $("#getUsers").datagrid("reload");
                                } else {
                                    $.messager.alert("tips", "delete failure!", "warning");
                                }
                            }
                        })
                    }
                })
            }
        }

    </script>
</head>
<body>
<%--展示全部的div--%>
<div id="getUsers">
</div>

<div id="myDiv">
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-add'" onclick="addButton()"></a>
    <%--删除数据--%>
    <a href="#" class="easyui-linkbutton" data-options="iconCls:'icon-remove'" onclick="deleteUser()"></a><br/>
    <input type="text" id="articleName" name="articleName">
    <input type="button" id="button" value="search" onclick="search()">
</div>


<%--添加数据--%>
<div id="addUsers">
    <form method="post" id="addUserForm" enctype="multipart/form-data">
        article_image:<input type="file" id="addImage" name="file1"><br/>
        article_name:<input type="text" id="" name="articleName"><br/>
        article_content:<input type="text" id="" name="articleContent"><br/>
        guru_id:<input type="text" id="" name="guruId"><br/>
        <%--article_date:<input type="text" id="" name="articleDate"><br/>--%>
        <%--article_status:<input type="text" id="" name="articleStatus"><br/>--%>
        <input type="button" value="button" id="addButton" onclick="addUser()">
    </form>
</div>

<%--数据修改--%>
<div id="updateUser">
    <form method="post" id="updateUserForm">
        <input type="hidden" id="updateUserId" name="articleId"><br/>
        <input type="hidden" id="updateImageUrl" name="articleImage"><br/>

        article_image:<input type="file" id="" name="file1"><br/>
        article_name:<input type="text" id="updatearticleName" name="articleName"><br/>
        article_content:<input type="text" id="updatearticleContent" name="articleContent"><br/>
        guru_id:<input type="text" id="updateguruId" name="guruId"><br/>
        <input type="button" value="button" id="updateButton" onclick="updateUser()">
    </form>
</div>
<%--条件查询--%>


</body>
</html>
