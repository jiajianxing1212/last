<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>分诊管理</title>
    <script type="text/javascript" src="js/jquery.min1.3.5.js"></script>
    <script type="text/javascript" src="js/jquery.easyui.min1.3.5.js"></script>

    <link href="themes1.3.5/icon.css" rel="stylesheet" type="text/css">
    <link href="themes1.3.5/default/easyui.css" rel="stylesheet" type="text/css">
    <script type="text/javascript">
        $(function () {
            $("#departmentTable").datagrid({
                url: "triage/getByPage",
                pagination: true,
                columns: [[
                    {title: "编号", field: "id", hidden: true},
                    {title: "科室名称", field: "name"},
                    {title: "办公室电话", field: "telephone"},
                    {title: "所在区域", field: "area"},
                    {title: "待诊断人数", field: "count"},
                    {
                        title: "操作", field: "1412ad", formatter: function (value, rowData, index) {
                            return "<a href='triageList.jsp?did="+rowData.id+"'>开诊</a>"
                        }
                    }

                ]]
            })
        })

    </script>
</head>
<body>
<table id="departmentTable"></table>
</body>
</html>