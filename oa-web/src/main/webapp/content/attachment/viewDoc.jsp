<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglibs.jsp"%>
<%
	pageContext.setAttribute("currentHeader", "attachment");
%>
<%
	pageContext.setAttribute("currentMenu", "attachment");
%>
<!doctype html>
<html>
<head>
<title>在线预览</title>
<%@include file="/common/s.jsp"%>
<link rel="stylesheet" type="text/css" href="${ctx}/resources/flexpaper/css/flexpaper.css" /> 
<script type="text/javascript" src="${ctx}/resources/whaty_flexpaper/flexpaper.js"></script>
<script type="text/javascript" src="${ctx}/resources/whaty_flexpaper/flexpaper_handlers.js?1=1"></script>
</head>
<body>
	<%@include file="/header/attachment.jsp"%>
	<div class="container" style="padding-top: 65px;" id="top">
		<div class="row">
			<div class="col-md-12">
				<div class="alert-fixed-top" data-alerts="alerts" data-titles="{}"
					data-ids="myid" data-fade="1000"></div>
				<div class="row">
					<div class="col-md-12 text-center">
						<div>${attachment.name}</div>
						<div id="documentViewer" class="flexpaper_viewer" style="width: 900px; height: 760px; "></div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<script type="text/javascript">
	$('#documentViewer').FlexPaperViewer(
			{ config : {	//SWFFile：url名称不能为中文，中文解决方案需要修改FlexPaperViewer.swf源码
//		            SWFFile : 'docs/项目安全检查规范.pdf.swf',
	            SWFFile : '${ctx}${swfFile}',
//		            SWFFile : 'docs/Paper.pdf.swf',
	            jsDirectory:'${ctx}/resources/whaty_flexpaper',
	            Scale : 1,
	            ZoomTransition : 'easeOut',
	            Zoom:1,
	            ZoomTime : 0.5,
	            ZoomInterval : 0.1,
	            FitPageOnLoad : false,
	            FitWidthOnLoad : true,
	            FullScreenAsMaxWindow : false,
	            ProgressiveLoading : false,
	            MinZoomSize : 0.2,
	            MaxZoomSize : 5,
	            SearchMatchAll : false,
	            InitViewMode : 'Portrait',
	            RenderingOrder : 'flash',
	            StartAtPage : '',
	            ViewModeToolsVisible : false,
	            ZoomToolsVisible : false,
	            NavToolsVisible : false,
	            CursorToolsVisible : false,
	            SearchToolsVisible : false,
	            WMode : 'Window',	//参数取值：Window（默认） | Opaque | Transparent，见下面注释说明
            	localeDirectory: '${ctx}/resources/whaty_flexpaper/locale/',
	            localeChain: 'zh_CN'
//		            localeChain: 'en_US'
	        }}
		);
    </script>
</body>
</html>
