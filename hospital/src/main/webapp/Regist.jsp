<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1">
		<link rel="stylesheet" type="text/css" href="css/bootstrap.min.css"/>
		<link rel="stylesheet" type="text/css" href="css/font-awesome.min.css"/>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
		<title>用户注册</title>
		<style type="text/css">
		</style>
	</head>
	<body>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-4" style="height: 150px;"></div>
				<div class="col-md-4"></div>
				<div class="col-md-4"></div>
			</div>
			<div class="row">
				<div class="col-md-2"></div>
				<div class="col-md-8" align="center" style="text-align: center;padding-left: 5%;">
					<form class="form-inline" action="${pageContext.request.contextPath}/user/register" method="post">
						<table class="table  table-bordered" style="width:90%;">
							<tr class="bg-primary">
								<td style="text-align: center;" colspan="2"><span style="font-size: 20px;">用户注册</span></td>
							</tr>
							<tr class="active">
								<td style="width: 120px;"><span class="czs-user-l" style="font-size: 30px;"></span></td>
								<td><input type="text" style="width: 100%;" placeholder="请输入用户名" class="form-control" name="username" id=""></td>
							</tr>
							<tr class="active">
								<td><span class="czs-lock-l" style="font-size: 30px;"></span></td>
								<td><input type="password" style="width: 100%;" placeholder="请输入密码" class="form-control" name="password" id=""></td>
							</tr>
							<tr class="active">
								<td><span class="czs-user" style="font-size: 30px;"></span></td>
								<td><input type="text" style="width: 100%;" placeholder="真实姓名" class="form-control" name="realName" id=""></td>
							</tr>
							<tr class="active">
								<td><span class="czs-setting" style="font-size: 30px;"></span></td>
								<td>
									<input type="radio" class="form-control" name="sex" value="1">男
									<input type="radio" class="form-control" name="sex" value="0">女
								</td>
							</tr>
							<tr class="active">
								<td><span class="czs-calendar-l" style="font-size: 30px;"></span></td>
								<td><input type="date" value="1999-9-9" style="width: 100%;" class="form-control" name="birth" id=""></td>
							</tr>
							
							<tr class="active">
								<td><span class="czs-mobile-l" style="font-size: 30px;"></span></td>
								<td><input type="text" style="width: 100%;" placeholder="手机号" class="form-control" name="mobile" id=""></td>
							</tr>
							<tr class="active">
								<td><span class="czs-mobile-l" style="font-size: 30px;"></span></td>
								<td><input type="email" style="width: 100%;" placeholder="邮箱" class="form-control" name="email" id=""></td>
							</tr>
							<tr class="active">
								<td><span class="czs-user-framework" style="font-size: 30px;"></span></td>
								<td><input type="file" style="width: 100%;" class="form-control" name="headPic" id=""></td>
							</tr>
							<tr class="active">
								<td><i class="fa fa-2x fa-home"></i></td>
								<td>
									<select type="text" name="deptId" class="form-control" style="width:100%" placeholder="请选择科室" >
										<option value="101">骨科</option>
										<option value="102">脑科</option>
										<option value="103">精神科</option>										
									</select>
								</td>
							</tr>
							
							<tr>
								<td colspan="2"><input type="submit" style="width: 200px;" class="btn btn-success" value="注册"></td>
							</tr>
						</table>
					</form>
				</div>
				<div class="col-md-2"></div>
			</div>
			<div class="row">
				<div class="col-md-4"></div>
				<div class="col-md-4"></div>
				<div class="col-md-4"></div>
			</div>
		</div>

	</body>
</html>
