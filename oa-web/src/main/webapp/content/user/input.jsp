<%@page contentType="text/html;charset=UTF-8"%>
 <%@include file="/taglibs.jsp"%><%--语法标签 --%>
<%
	pageContext.setAttribute("currentHeader", "user");
%>
<%
	pageContext.setAttribute("currentMenu", "user");
%>
<!doctype html>
<html>
<head>
<title>编辑用户</title>
<%@include file="/common/s.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	var validator = $("#userBaseForm").validate({
        errorClass: 'validate-error',
        rules: {
            username: {
                remote: {
                    url: 'checkUsername.do',
                    data: {
                        <c:if test="${model != null&&model.id!='0'}">
                        id: function() {
                            return $('#userBase_id').val();
                        }
                        </c:if>
                    }
                    }
                }
        },
        messages: {
            username: {
                remote: "存在重复账号"
            }
        }
    });
	$("#submitButton").on("click",function(){
		if(validator.form()){
			   form.submit();
		}
	});
});


var setting = {
		check: {
			enable: true
		},
		data: {
			simpleData: {
				enable: true,
				idKey: "id"
			}
		},
		async: {
			enable: true,
			url: "${tenantPrefix}/admin/role/buildRoleTree",
			autoParam:["id"]
		},
		callback: {
			onAsyncSuccess:function(event, treeId,treeNode){
				var tree=$.fn.zTree.getZTreeObj(treeId);
				tree.expandAll(true);
				$("#roles").children("input[type='hidden']").each(function(){
					var node=tree.getNodesByParam("id",$(this).val(),null);
					tree.checkNode(node[0]);
				});
			},
			onCheck:function(event, treeId, treeNode){
				var nodes=$.fn.zTree.getZTreeObj(treeId).getCheckedNodes(true);
				$("#roles").children("input[type='hidden']").remove();
				for(var i=0;i<nodes.length;i++){
					$("#roles").append($("<input type='hidden' name='roles' value="+nodes[i].id+">"));
				}
			}
		}
	};

	$(function(){
		$.fn.zTree.init($("#role-tree"), setting);
	});
    </script>
</head>

<body>
	<%@include file="/header/user.jsp"%>
	<div class="row-fluid">
	<%@include file="/menu/user.jsp"%>

	<section id="m-main" class="col-md-10" style="padding-top:65px;">
		 <div class="panel panel-default">
			<!-- 头部开始 -->
		 <div class="panel-heading">
		  <i class="glyphicon glyphicon-list"></i>
		  编辑
		</div>
			<!-- 头部结束 -->
			<!-- 输入区域开始 -->
			<div class="panel-body">
				<form id="userBaseForm" method="post" action="input.do" class="form-horizontal">
					<c:if test="${model != null}">
						<input id="userBase_id" type="hidden" name="id" value="${model.id}">
					</c:if>
					<div class="col-md-7" >
					<div class="form-group">
						<label class="control-label col-md-1" for="userBase_username">账号</label>
						<div class="col-sm-5">
							<input id="userBase_username" type="text" name="username"
								value="${model.username}" size="40" class="form-control required"
								minlength="2" maxlength="50">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-1" for="userBase_email">邮箱</label>
						<div class="col-sm-5">
							<input id="userBase_email" type="text" name="email"
								value="${model.email}" size="40" class="form-control required email" 
								minlength="2" maxlength="40">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-1"  for="userBase_password">密码</label>
						<div class="col-sm-5">
							<input id="userBase_password" type="text" name="password"
								value="${model.password}" size="40" class="form-control required"
								minlength="2" maxlength="10">
						</div>
					</div>
					
					<div class="form-group">
						<label class="control-label col-md-1" for="userBase_status">启用</label>
						<div class="col-sm-5">
							<input id="userBase_status" type="checkbox" name="status"
								value="1" ${model.status == 1 ? 'checked' : ''}>
						</div>
					</div>
					</div>
					<div class="col-md-5" >
					<div id="roles">
					<ul id="role-tree" class="ztree"></ul>
						<c:forEach items="${model.roles}" varStatus="st">
						<input  type="hidden" name="roles"  value="${model.roles[st.index]}"/>
						</c:forEach>
					</div>
					</div>
					
					<div class="form-group">
						<div class="col-md-offset-1 col-md-11">
							<button id="submitButton" class="btn btn-default a-submit">
								保存
							</button>
							<button  onclick="history.back();"
								type="button" class="btn a-cancel">
								返回
							</button>
						</div>
					</div>
				</form>
			</div>
			<!-- 输入区域结束 -->
		</div>
	</section>
	</div>
</body>
</html>
