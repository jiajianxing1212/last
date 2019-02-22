<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="../css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="../css/style.css"/>
	<script src="../js/jquery-1.10.1.js" type="text/javascript" charset="utf-8"></script>
	<script src="../js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
</head>
<body>
<!--导航条-->
			<nav class="navbar navbar-default ">
				<!--标题LOGO-->
				<div class="navbar-header">
					<a href="#" class="navbar-brand" style="font-size:30px;font-weight: bold;">分诊管理系统</a>
				</div>
				<!--导航组-->
				<div class="container-fluid">
					<ul class="nav navbar-nav">
						<!--菜单项-->
						<li><a href="#">首页</a></li>
					</ul>
					<!--退出登陆-->
					<ul class="nav nav-form navbar-nav navbar-right">
						<li><a href="../Regist.jsp" target="_top"><span class="glyphicon glyphicon-log-out"></span>注册</a></li>
					</ul>
					<!--导航输入框-->
					<ul class="navbar-form nav navbar-right">
						<li>
							<form action="${pageContext.request.contextPath}/user/login" target="_top" class="form-inline form-group-sm" method="post">
								<input type="text" placeholder="请输入用户名" name="username" id="username" class=" form-control" />
								<input type="text" placeholder="请输入密码" name="password" id="password" class="form-control" />
								<input type="submit" value="登陆" class="btn btn-success btn-sm" />
							</form>
						</li>
					</ul>
				</div>
			</nav>
</body>
</html>
