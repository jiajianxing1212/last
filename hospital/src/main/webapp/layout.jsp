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
  </head>

  <script>
      $(function () {
// 必须先初始化选项卡
        $("#tt").tabs({
            fit: true
        })
      });
      function addBookTabs(node) {
          // 先判断该选项卡是否存在，
          var isExists = $("#tt").tabs("exists", node.text);
          if (isExists) {
              // 如果存在则选中
              $("#tt").tabs("select", node.text);
          } else {
              // 如果不存在，则添加（打开新的选项卡）
              $("#tt").tabs("add", {
                  title: node.text,// 选项卡的名字
                  closable: true,// 是否显示x符号
                  selected: true,// 添加后并选中新增的选项卡
                  //通过iframe标签，把页面内容引入进来
                  content: "<iframe src='book.jsp?categoryId="+node.id+"' width='100%' height='100%'></iframe>"
              });
          }
      }
// 设置科室选项卡
      function OpenDepartment() {
          // 先判断该选项卡是否存在，
          var isExists = $("#tt").tabs("exists","用户管理");
          if (isExists) {
              // 如果存在则选中
              $("#tt").tabs("select", "用户管理");
          } else {
              // 如果不存在，则添加（打开新的选项卡）
              $("#tt").tabs("add",{
                  title:"用户管理",// 选项卡的名字
                  closable:true,// 是否显示x符号
                  selected:true,// 添加后并选中新增的选项卡
                  //通过iframe标签，把页面内容引入进来
                  content:"<iframe src='${pageContext.request.contextPath}/department.jsp' width='100%' height='100%'></iframe>"
              });
          }
      }


      // 设置药品选项卡
      function OpenDrug() {
          // 先判断该选项卡是否存在，
          var isExists = $("#tt").tabs("exists","药品管理");
          if (isExists) {
              // 如果存在则选中
              $("#tt").tabs("select", "药品管理");
          } else {
              // 如果不存在，则添加（打开新的选项卡）
              $("#tt").tabs("add",{
                  title:"药品管理",// 选项卡的名字
                  closable:true,// 是否显示x符号
                  selected:true,// 添加后并选中新增的选项卡
                  //通过iframe标签，把页面内容引入进来
                  content:"<iframe src='${pageContext.request.contextPath}/drug.jsp' width='100%' height='100%'></iframe>"
              });
          }
      }

      // 设置药品审核选项卡
      function OpenExam() {
          // 先判断该选项卡是否存在，
          var isExists = $("#tt").tabs("exists","药品审核");
          if (isExists) {
              // 如果存在则选中
              $("#tt").tabs("select", "药品审核");
          } else {
              // 如果不存在，则添加（打开新的选项卡）
              $("#tt").tabs("add",{
                  title:"药品审核",// 选项卡的名字
                  closable:true,// 是否显示x符号
                  selected:true,// 添加后并选中新增的选项卡
                  //通过iframe标签，把页面内容引入进来
                  content:"<iframe src='${pageContext.request.contextPath}/exam.jsp' width='100%' height='100%'></iframe>"
              });
          }
      }
      // 设置日志记录
      function openRizhi() {
          // 先判断该选项卡是否存在，
          var isExists = $("#tt").tabs("exists","日志管理");
          if (isExists) {
              // 如果存在则选中
              $("#tt").tabs("select", "日志管理");
          } else {
              // 如果不存在，则添加（打开新的选项卡）
              $("#tt").tabs("add",{
                  title:"日志管理",// 选项卡的名字
                  closable:true,// 是否显示x符号
                  selected:true,// 添加后并选中新增的选项卡
                  //通过iframe标签，把页面内容引入进来
                  content:"<iframe src='${pageContext.request.contextPath}/log.jsp' width='100%' height='100%'></iframe>"
              });
          }
      }

      // 设置分诊记录
      function openFenzhen() {
          // 先判断该选项卡是否存在，
          var isExists = $("#tt").tabs("exists","分诊管理");
          if (isExists) {
              // 如果存在则选中
              $("#tt").tabs("select", "分诊管理");
          } else {
              // 如果不存在，则添加（打开新的选项卡）
              $("#tt").tabs("add",{
                  title:"分诊管理",// 选项卡的名字
                  closable:true,// 是否显示x符号
                  selected:true,// 添加后并选中新增的选项卡
                  //通过iframe标签，把页面内容引入进来
                  content:"<iframe src='${pageContext.request.contextPath}/departmentList2.jsp' width='100%' height='100%'></iframe>"
              });
          }
      }

      // 设置患者记录
      function openPatient() {
          // 先判断该选项卡是否存在，
          var isExists = $("#tt").tabs("exists","患者管理");
          if (isExists) {
              // 如果存在则选中
              $("#tt").tabs("select", "患者管理");
          } else {
              // 如果不存在，则添加（打开新的选项卡）
              $("#tt").tabs("add",{
                  title:"患者管理",// 选项卡的名字
                  closable:true,// 是否显示x符号
                  selected:true,// 添加后并选中新增的选项卡
                  //通过iframe标签，把页面内容引入进来
                  content:"<iframe src='${pageContext.request.contextPath}/patientList.jsp' width='100%' height='100%'></iframe>"
              });
          }
      }
        // 其他
      function openOther() {
          // 先判断该选项卡是否存在，
          var isExists = $("#tt").tabs("exists","其他管理");
          if (isExists) {
              // 如果存在则选中
              $("#tt").tabs("select", "其他管理");
          } else {
              // 如果不存在，则添加（打开新的选项卡）
              $("#tt").tabs("add",{
                  title:"其他管理",// 选项卡的名字
                  closable:true,// 是否显示x符号
                  selected:true,// 添加后并选中新增的选项卡
                  //通过iframe标签，把页面内容引入进来
                  content:"<iframe src='${pageContext.request.contextPath}/fileList.jsp' width='100%' height='100%'></iframe>"
              });
          }
      }

  </script>
  <body>

      <body class="easyui-layout">
      <div data-options="region:'north',title:'North Title',split:true" style="height:100px;"></div>
      <div data-options="region:'south',title:'South Title',split:true" style="height:100px;"></div>
      <div data-options="region:'east',iconCls:'icon-reload',title:'East',split:true" style="width:100px;"></div>
      <div data-options="region:'west',title:'West',split:true" style="width:200px;">
        <%--手风琴组件--%>
        <div id="aa" class="easyui-accordion" style="width:300px;height:400px;">

          <%--1.科室管理系统--%>
          <div title="科室管理系统" data-options="iconCls:'icon-save'" style="overflow:auto;padding:10px;">
            <input class="easyui-linkbutton" value="科室管理" type="button" onclick="OpenDepartment()"/>
          </div>

          <%--2.药品管理系统--%>
          <div title="药品管理系统" data-options="iconCls:'icon-save'" style="overflow:auto;padding:10px;">
              <input class="easyui-linkbutton" value="药品管理" type="button" onclick="OpenDrug()"/><br/>
              <input class="easyui-linkbutton" value="药品审核" type="button" onclick="OpenExam()"/>
          </div>

          <%--3.日志管理--%>
          <div title="日志管理" data-options="iconCls:'icon-reload',selected:true" style="padding:10px;">
              <input class="easyui-linkbutton" value="日志管理" type="button" onclick="openRizhi()"/>
          </div>
          <%--4.患者管理--%>
          <div title="患者管理" data-options="iconCls:'icon-reload',selected:true" style="padding:10px;">
              <input class="easyui-linkbutton" value="患者管理" type="button" onclick="openPatient()"/>
          </div>

              <%--5.分诊管理--%>
              <div title="分诊管理" data-options="iconCls:'icon-reload',selected:true" style="padding:10px;">
                  <input class="easyui-linkbutton" value="分诊管理" type="button" onclick="openFenzhen()"/>
              </div>

           <%--6.其他管理--%>
           <div title="其他管理" data-options="iconCls:'icon-reload',selected:true" style="padding:10px;">
              <input class="easyui-linkbutton" value="其他管理" type="button" onclick="openOther()"/>
           </div>

        </div>
      </div>
      <div data-options="region:'center',title:'center title'" style="padding:5px;background:#eee;">
        <div id="tt" >
          <div title="首页" data-options="closable:true">
            <h1>欢迎页面</h1>
          </div>

        </div>

      </div>
      </body>
    </div>
  </body>
</html>
