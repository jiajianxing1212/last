<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" type="text/css" href="./css/bootstrap.min.css" />
		<link rel="stylesheet" type="text/css" href="./css/style.css" />
		<!--font-awesome字体图标库-->
		<link rel="stylesheet" type="text/css" href="./css/font-awesome.min.css"/>
		<script src="./js/jquery-1.10.1.js" type="text/javascript" charset="utf-8"></script>
		<script src="./js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
		<title>医院分诊管理系统首页</title>
		<style type="text/css">
			th {
				text-align: center;
			}
			
			body {
				background-color: #ffff1;
			}
			
			html {
				margin: 1px;
			}
		</style>
	</head>

	<body>
		<iframe src="${pageContext.request.contextPath}/iframe/navIndex.jsp" width="100%" frameborder="0"></iframe>
		<div class="jumbotron">
			<div class="container">
				<h1>欢迎使用医院分诊管理系统</h1>
				<p>这里是系统简介</p>
			</div>
		</div>
		<div style="margin-bottom:30px" class="container-fluid">

			<!--统计信息-->
			<div class="row">
				<div class="col-md-1"></div>
				<div class="col-md-5">
					<div class="panel panel-info">
						<div class="panel-heading">
							近期值班表
						</div>
						<div class="panel-body">
							<table class="table table-default">
								<thead>
									<tr style="text-align: left;">
										<th>日期</th>
										<th>科室</th>
										<th>值班医生</th>
										<th>联系方式</th>
									</tr>
								</thead>
								<tbody style="text-align: center;">
									<tr>
										<td>2018年11月26日</td>
										<td>骨科</td>
										<td>张飞</td>
										<td>15533349988</td>
									</tr>
									<tr>
										<td>2018年11月26日</td>
										<td>眼课</td>
										<td>张飞</td>
										<td>15533349988</td>
									</tr>
									<tr>
										<td>2018年11月26日</td>
										<td>皮肤科</td>
										<td>张飞</td>
										<td>15533349988</td>
									</tr>
									<tr>
										<td>2018年11月26日</td>
										<td>脑科</td>
										<td>张飞</td>
										<td>15533349988</td>
									</tr>
									<tr>
										<td>2018年11月26日</td>
										<td>x科</td>
										<td>张飞</td>
										<td>15533349988</td>
									</tr>
								</tbody>
							</table>

						</div>
						<div class="panel-footer" style="text-align: right;">2018年11月26日</div>
					</div>

				</div>
				<div class="col-md-5">
					<!--
						panel
							head
							body
							footer
						
					-->
					<div class="panel panel-info">
						<div class="panel-heading">
							通知
						</div>
						<div class="panel-body">
							<table class="table table-default">
								<thead>
									<tr>
										<th>通知</th>
										<th>时间</th>
									</tr>
								</thead>
								<tbody style="text-align: center;">
									<tr>
										<td><a style="text-decoration: none;" href="#">哈哈哈哈哈哈...</a></td>
										<td>2018年11月26日21:21:42</td>
									</tr>
									<tr>
										<td><a style="text-decoration: none;" href="#">哈哈哈哈哈哈...</a></td>
										<td>2018年11月26日21:21:42</td>
									</tr>
									<tr>
										<td><a style="text-decoration: none;" href="#">哈哈哈哈哈...</a></td>
										<td>2018年11月26日21:21:42</td>
									</tr>
									<tr>
										<td><a style="text-decoration: none;" href="#">哈哈哈哈哈...</a></td>
										<td>2018年11月26日21:21:42</td>
									</tr>
									<tr>
										<td><a style="text-decoration: none;" href="#">哈哈哈哈哈哈...</a></td>
										<td>2018年11月26日21:21:42</td>
									</tr>
								</tbody>
							</table>
						</div>
						<div class="panel-footer" style="text-align: right;">
							2018年11月26日
						</div>
					</div>

				</div>
				<div class="col-md-1"></div>
			</div>
		</div>
		<!--底部-->
		<footer class="panel-footer navbar-fixed-bottom">
			<div class="container">
				<div class="row">
					<div class="col-md-2"></div>
					<div class="col-md-8" style="text-align: left;">
						&trade;百知教育 网址:
						<a href="http://www.baizhiedu.com">http://www.baizhiedu.com</a>
					</div>
					<div class="col-md-2"></div>
				</div>
			</div>

		</footer>
	</body>

</html>