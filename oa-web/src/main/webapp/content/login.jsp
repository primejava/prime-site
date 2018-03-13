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
					<header class="panel-heading">
						<spring:message code="core.login.title" text="登录" />
					</header>

					<div class="panel-body">
						<form id="userForm" name="f" method="post"
							action="${tenantPrefix}/login/login" class="form-horizontal">
							<div class="form-group">
								<label class="col-md-2 control-label" for="username">账号</label>
								<div class="col-md-10">
									<input type='text' id="username" name='username'
										class="form-control" value=""
										aria-describedby="inputSuccess3Status"> <span
										id="usernameText"
										class="glyphicon glyphicon-remove form-control-feedback"
										aria-hidden="true"
										style="right: 15px; cursor: pointer; pointer-events: auto; display: none;"></span>
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-2 control-label" for="password">密码</label>
								<div class="col-md-10">
									<input type='password' id="password" name='password'
										class="form-control" value=''>
											<p style="font-size:1.2em; color:#F90;">	</p>
											<p style="font-size:1.2em; color:#F90;">用户名：admin  密码:000000</p>
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-2"></div>
								<div class="col-md-10">
									<input class="btn btn-default a-submit" name="submit" type="submit"
										text='登陆' />
								</div>
							</div>
						</form>
					</div>
				</article>

				<div class="m-spacer"></div>
				
			</section>

			<div class="col-md-4"></div>
			<a href="${tenantPrefix}/login/register">点击注册</a>
		</div>
	</div>

</body>
</html>
