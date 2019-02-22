<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

    <title>文档管理</title>
    <script type="text/javascript" src="js/jquery.min1.3.5.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min1.3.5.js"></script>

    <link href="themes1.3.5/icon.css" rel="stylesheet" type="text/css">
    <link href="themes1.3.5/default/easyui.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
        $(function () {
            $("#fileTable").datagrid({
                url: "file/getByPage.do",
                pagination: true,
                toolbar: [{
                    iconCls: 'icon-add',
                    handler: function () {
                        $("#addFile").dialog("open");
                    }
                }],
                columns: [[
                    // {checkbox: true,},
                    {title: "编号", field: "id", hidden: true},
                    {title: "文件名", field: "fileName"},
                    {title: "文件大小", field: "fileSize"},
                    {
                        title: "上传时间", field: "upTime", formatter: function (value, rowData, index) {
                            return date(rowData.upTime);
                        }
                    },
                    {
                        title: "操作", field: "area", formatter: function (value, rowData, index) {
                            var data = JSON.stringify(rowData);
                            return "<a href='file/download.do?fileName="+rowData.fileName+"'>下载</a>"
                        }
                    },

                ]],
                onDblClickRow: function (rowIndex, rowData) {
                    toOpenUpdateDialog(rowIndex, rowData);
                }
            })
            //搜索
            $("#searchButton").click(function () {
                $("#fileTable").datagrid("load", {"fileName": $("#searchInput").val()})
            })
            //添加
            $("#addButton").linkbutton({});
            $("#addFile").dialog({
                width: 400,
                height: 200,
                resizable: true,
                closed: true
            });
            $("#addButton").click(function () {
                $("#addForm").form("submit", {
                    url: "file/upload.do",
                    success: function (data) {
                        alert("上传成功");
                        $("#addFile").dialog("close");
                        $("#fileTable").datagrid("reload");
                    }
                })
            })
        })

        //日期处理
        function date(value) {
            var date = new Date(value);//long转换成date
            var year = date.getFullYear().toString();
            var month = (date.getMonth() + 1);
            var day = date.getDate().toString();
            if (month < 10) {
                month = "0" + month;
            }
            if (day < 10) {
                day = "0" + day;
            }
            return year + "-" + month + "-" + day;
        }
        //文件下载
        /*function downloadFile(data) {
            // console.log(data.fileName);
            $.ajax({
                url:"file/download.do",
                data:{"fileName":data.fileName},
            })
        }*/

    </script>
</head>
<body>
<input type="text" id="searchInput">
<button id="searchButton">搜索</button>
<table id="fileTable"></table>
<div id="addFile">
    <form method="post" id="addForm" enctype="multipart/form-data">
        请选择文件：<input type="file" name="file"><br>
        <a href="javascript:void(0)" id="addButton">确认</a>
    </form>
</div>
</body>
</html>