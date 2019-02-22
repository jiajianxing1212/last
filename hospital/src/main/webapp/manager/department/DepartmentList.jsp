<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1">
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/bootstrap.min.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/style.css"/>
	<!--font-awesome字体图标库-->
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/font-awesome.min.css"/>
	<script src="${pageContext.request.contextPath}/js/jquery-1.10.1.js" type="text/javascript" charset="utf-8"></script>
	<script src="${pageContext.request.contextPath}/js/bootstrap.min.js" type="text/javascript" charset="utf-8"></script>
<title>医院分诊管理系统首页</title>
<style type="text/css">
	th {
		text-align: center;
	}
</style>
</head>
<body>
	<div class="container-fluid">
		<div class="row">
			<iframe src="${pageContext.request.contextPath}/iframe/nav.html" width="100%" frameborder="0"></iframe>
		</div>
		<!--按钮工具栏-->
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-2">
				<!--按钮组-->
				<div class="btn-group">
					<a href="AddDepartment.html" class="btn btn-info btn-sm"><span class="glyphicon glyphicon-plus"></span> 添加科室</a>
					<a href="#" class="btn btn-danger btn-sm"><span class="glyphicon glyphicon-remove"></span> 删除选中</a>
				</div>
			</div>
			<div class="col-md-3">
				<form action="#" class="form form-inline input-group">
					<input type="text" class="form-control" placeholder="请输入科室名" name="name" id="name" />
					<span class="input-group-btn"><input type="submit" class="btn btn-success" value="搜索" /></span>	
				</form>
			</div>				
			<div class="col-md-5"></div>
		</div>
		<div class="row">
			<div class="col-md-12"></div>
		</div>
		<!--信息管理table-->
		<div class="row">
			<div class="col-md-2"></div>
			<div class="col-md-8">
				<table class="table table-bordered table-condensed table-hover">
					<thead>
						<tr class="bg-info text-capitalize">
							<th class="">
								<input type="checkbox" id="" />
							</th>
							<th>编号</th>
							<th>名称</th>
							<th>办公室电话</th>
							<th>所在区域</th>
							<th style="width: 20%;">操作</th>
						</tr>
					</thead>
					<tbody>
						<tr>
							<td style="text-align: center;"><input type="checkbox" name="ids" id="" /></td>
							<td>101</td>
							<td>骨科</td>
							<td>12345678</td>
							<td>10楼A区</td>
							<td>
								<a href="#" class="btn btn-xs btn-warning"><span class="glyphicon glyphicon-trash"></span> 删除</a>
								<a href="#" class="btn btn-xs btn-info"><span class="glyphicon glyphicon-edit"></span> 修改</a>
								
							</td>
						</tr>
						<tr>
							<td style="text-align: center;"><input type="checkbox" name="ids" id="" /></td>
							<td>101</td>
							<td>骨科</td>
							<td>12345678</td>
							<td>10楼A区</td>
							<td>
								<a href="#" class="btn btn-xs btn-warning"><span class="glyphicon glyphicon-trash"></span> 删除</a>
								<a href="#" class="btn btn-xs btn-info"><span class="glyphicon glyphicon-edit"></span> 修改</a>
								
							</td>
						</tr>
						<tr>
							<td style="text-align: center;"><input type="checkbox" name="ids" id="" /></td>
							<td>102</td>
							<td>骨科</td>
							<td>12345678</td>
							<td>10楼A区</td>
							<td>
								<a href="#" class="btn btn-xs btn-warning"><span class="glyphicon glyphicon-trash"></span> 删除</a>
								<a href="#" class="btn btn-xs btn-info"><span class="glyphicon glyphicon-edit"></span> 修改</a>
								
							</td>
						</tr>
						<tr>
							<td style="text-align: center;"><input type="checkbox" name="ids" id="" /></td>
							<td>103</td>
							<td>骨科</td>
							<td>12345678</td>
							<td>10楼A区</td>
							<td>
								<a href="#" class="btn btn-xs btn-warning"><span class="glyphicon glyphicon-trash"></span> 删除</a>
								<a href="#" class="btn btn-xs btn-info"><span class="glyphicon glyphicon-edit"></span> 修改</a>
								
							</td>
						</tr>
						<tr>
							<td style="text-align: center;"><input type="checkbox" name="ids" id="" /></td>
							<td>104</td>
							<td>骨科</td>
							<td>12345678</td>
							<td>10楼A区</td>
							<td>
								<a href="#" class="btn btn-xs btn-warning"><span class="glyphicon glyphicon-trash"></span> 删除</a>
								<a href="#" class="btn btn-xs btn-info"><span class="glyphicon glyphicon-edit"></span> 修改</a>
								
							</td>
						</tr>
					</tbody>
				</table>
			</div>
			<div class="col-md-2"></div>
		</div>
		<!--分页-->
		<div class="row">
			<div class="col-md-4"></div>
			
			<div class="col-md-4">
				<ul class="pager">
					<li class="previous"><a href="#" class="btn btn-default">上一页</a></li>
					<li><a href="#" class="btn btn-default active disabled">第2页</a></li>
					<li class="next"><a href="#" class="btn btn-default">下一页</a></li>
				</ul>
				
			</div>
			<div class="col-md-4"></div>
		</div>
		<div class="row">
			<div class="com-md-12">
				<!--底部-->
				<footer class="modal-footer navbar-fixed-bottom">
					<div class="row">
						<div class="col-md-2"></div>
						<div class="col-md-8" style="text-align: left;">
							&trade;百知教育 网址:<a href="http://www.baizhiedu.com">http://www.baizhiedu.com</a>
						</div>
						<div class="col-md-2"></div>
					</div>
				</footer>
			</div>
		</div>
	</div>
</body>
</html>
