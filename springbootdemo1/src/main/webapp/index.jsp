<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<body>
<h2>Hello World!</h2>
${sessionScope.list}

<form action="${pageContext.request.contextPath}/login" method="post">
    <input type="text" name="name" id=""/><br/>
    <input type="text" name="id" id=""/><br/>
    <input type="text" name="code" id=""/><img src="${pageContext.request.contextPath}/getImage" id="code" onclick="document.getElementById('code').src='${pageContext.request.contextPath}/getImage?time='+(new Date()).getTime();"/><br/>

    <input type="submit" value="submit"/>
</form>
<form action="${pageContext.request.contextPath}/singleFileUpload" method="post" enctype="multipart/form-data">
    <input type="file" name="file1" id=""/>

    <input type="submit" value="submit" id=""/>
    <%--<img src="${pageContext.request.contextPath}/tmp/1.jpg" alt="">--%>
</form>
</body>
</html>