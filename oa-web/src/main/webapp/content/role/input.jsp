<%@page contentType="text/html;charset=UTF-8"%>
 <%@include file="/taglibs.jsp"%><%--语法标签 --%>
<%
	pageContext.setAttribute("currentHeader", "role");
%>
<%
	pageContext.setAttribute("currentMenu", "role");
%>
<!doctype html>
<html>
<head>
<title>编辑流程组</title>
<%@include file="/common/s.jsp"%>
<script type="text/javascript">
$(document).ready(function() {
	var validator = $("#groupBaseForm").validate({
        errorClass: 'validate-error',
        rules: {
        	name: {
            	required: true
                }
        },
        messages: {
        	name: {
            	required: "请填写信息"
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
			  key: {
                  name: "name"
              },
			simpleData: {
				enable: true,
				idKey: "id"
			}
		},
		async: {
			enable: true,
			url: "${tenantPrefix}/admin/college/buildCollegeTree",
			autoParam:["id"]
		},
		callback: {
			onAsyncSuccess:function(event, treeId,treeNode){
				var tree=$.fn.zTree.getZTreeObj(treeId);
				tree.expandAll(true);
				$("#tree").children("input[type='hidden']").each(function(){
					var node=tree.getNodesByParam("id",$(this).val(),null);
					tree.checkNode(node[0]);
				});
			},
			onCheck:function(event, treeId, treeNode){
				var nodes=$.fn.zTree.getZTreeObj(treeId).getCheckedNodes(true);
				$("#tree").children("input[type='hidden']").remove();
				for(var i=0;i<nodes.length;i++){
					$("#tree").append($("<input type='hidden' name='departments' value="+nodes[i].id+">"));
				}
			}
		}
	};

	$(function(){
		$.fn.zTree.init($("#treeMenu"), setting);
	});
    </script>
</head>

<body>
	<%@include file="/header/role.jsp"%>

	<div class="row-fluid">
		<%@include file="/menu/role.jsp"%>

		<!-- start of main -->
		<section id="m-main" class="col-md-10" style="padding-top:65px;">

			<div class="panel panel-default">
					<!-- 头部开始 -->
	        <div class="panel-heading">
			  <i class="glyphicon glyphicon-list"></i>
			  编辑
			</div>
			<!-- 头部结束 -->
				<div class="panel-body">
					<form id="groupBaseForm" method="post" action="input.do" 
						class="form-horizontal">
						<c:if test="${model != null}">
							<input id="groupBase_id" type="hidden" name="id"
								value="${model.id}">
						</c:if>
						<div class="row" >	
					<div class="col-md-5" >
						<div class="form-group">
							<label class="control-label col-sm-5" for="groupBase_name">角色名称</label>
							<div class="col-sm-5">
								<input id="groupBase_name" type="text" name="name"
									value="${model.name}" size="40" class="text required"
									minlength="2" maxlength="50">
							</div>
						</div>
						<div class="form-group">
							<label class="control-label col-sm-5" for="groupBase_type">角色描述</label>
							<div class="col-sm-5">
							 <input id="groupBase_name" type="text" name="description"
									value="${model.description}" size="40" class="text required"
									minlength="2" maxlength="50">
							  </div>
						</div>
					</div>
					<div class="col-md-7" >
					<div id="tree">
						<ul id="treeMenu" class="ztree"></ul>
						<c:forEach items="${model.departments}" varStatus="st">
						<input  type="hidden" name="departments"  value="${model.departments[st.index]}"/>
						</c:forEach>
					</div>
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
			</div>
		</section>
		<!-- end of main -->
	</div>
</body>
</html>
