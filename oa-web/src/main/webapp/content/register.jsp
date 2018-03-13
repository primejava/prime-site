<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/common/taglibs.jsp"%>
<!doctype html>
<html lang="en">
<head>
<%@include file="/common/meta.jsp"%>
<title>登录</title>
<%@include file="/common/s.jsp"%>
</head>
<body>

	<div class="row" style="margin-top: 70px;">
		<div class="container-fluid">
			<div class="col-md-4"></div>
			<section class="col-md-4">
				<article class="panel panel-default">
					<header class="panel-heading">注册 </header>
					<div class="panel-body">
						<form id="registerForm" name="f" method="post"
							action="${tenantPrefix}/login/register" class="form-horizontal">
							<div class="form-group">
								<label class="col-md-2 control-label" for="name">昵称</label>
								<div class="col-md-10">
									<input type='text' id="name" name='name' class="form-control"
										value="">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" for="contactName">联系人姓名</label>
								<div class="col-md-10">
									<input type='text' id="contactName" name='contactName'
										class="form-control" value="">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" for="contactMobilePhone">联系电话</label>
								<div class="col-md-10">
									<input type='text' id="contactMobilePhone"
										name='contactMobilePhone' class="form-control" value="">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" for="contactEmail">联系邮箱</label>
								<div class="col-md-10">
									<input type='text' id="contactEmail" name='contactEmail'
										class="form-control" value="">
								</div>
							</div> 
							<div class="form-group">
								<label class="col-md-2 control-label" for="userName">账号</label>
								<div class="col-md-10">
									<input type='text' id="userName" name='userName'
										class="form-control" value="">
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" for="password">密码</label>
								<div class="col-md-10">
									<input type='password' id="password" name='password'
										class="form-control" value=''>
								</div>
							</div>

							<div class="form-group">
								<div class="col-md-2"></div>
								<div class="col-md-10">
									<input class="btn btn-default a-submit" name="submit"
										type="submit" text='申请' />
								</div>
							</div>
						</form>
					</div>
				</article>

				<div class="m-spacer"></div>
			</section>

			<div class="col-md-4"></div>
		</div>
	</div>

</body>
</html>
