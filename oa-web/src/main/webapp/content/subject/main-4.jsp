<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>
<%@include file="/common/s.jsp"%>
<link rel="stylesheet" href="${ctx}/resources/subject/main-skin.css"
	type="text/css" />
<style type="text/css">
div {
 	border: solid 1px red; 
}

html {
	height: 1000px
}
</style>
<script src="http://localhost:8080/rap.plugin.js?projectId=1"></script>
<div class="gas-station">
	<img src="${ctx}/upload/subject-top-bg.png" onError="defaultImg(this)"
		class="background">
	<div class="container">
		<div class="space"></div>
		<div class="space"></div>
		<div class="space"></div>
		<%-- 轮播图、动态 --%>
		<div class="fix-right-layout">
			<%-- 轮播图 --%>
			<div class="adaptive-left" unselectable="none"
				onselectstart="return false;">
				<div class="viwepager">
					<ul>
					</ul>
				</div>
			</div>
		</div>
		<div class="space"></div>

		<!-- 分隔符号*************************************************************** -->
		<div class="fix-right-layout" style="background-color: #fff;">
			<div class="adaptive-left attention" >
				<div class="title">
					<div class="more">
						<div class="fix-right more-right">
							<a target="_blank"
								href="subject-list.106100.402821f0515cbab601515cbce93f0002.html">更多&gt;&gt;</a>
						</div>
					</div>
					通知<span style="color: yellow; font-size: 22px;">公告</span>
				</div>
				<div class="content" style="border: solid 0px green;margin-top:15px;">
					<div class="attention-module" 
						style="border: solid 0px green; width:53%;height: 90%; float: left;">
						<div style="width: 100%; height: 60%">
							<img style="width: 100%; height: 100%"
								src="${ctx}/upload/1445505770748_6441.png">
						</div>
						<div style="height: 40%">
							<div class="overflow-omit"
								style="width: 100%; height: 30px; line-height: 35px; color: #000; font-size: 16px; font-weight: bold; cursor: pointer;">测试图片显示0324-1</div>
							<div class="list"
								style="width: 100%; height: 65%; font-size: 12px; font-weight:bold;line-height: 23px;color: #666; overflow: hidden;">
								两款设备有什么区别，看完之后你应该会有更深的了解。
								威锋网讯，“iPhone SE 就是将 iPhone 6s 的配置放进 4 威锋网讯，“iPhone SE 就是将 iPhone
								6s 的配置放进 4 威锋网讯 ，“iPhone SE 就是将 iPhone 6s 的配置放进 4 威锋网讯，“iPhone
								SE 就是将 iPhone 6s 的配置放进 4 “iPhone SE 就是将 iPhone 6s 的配置放进 4
								威锋网讯，“iPhone SE 就是将 iPhone 6s 的配置放进 4 “iPhone SE 就是将 iPhone 6s
								的配置放进 4 威锋网讯，“iPhone SE 就是将 iPhone 6s 的配置放进 4 威锋网讯，“iPhone SE
								就是将 iPhone 6s 的配置放进 4 威锋网讯，“iPhone SE 就是将 iPhone 6s 的配置放进 4
								威锋网讯，“iPhone SE 就是将 iPhone 6s 的配置放进 4 威锋网讯，“iPhone SE 就是将 iPhone
								6s 的配置放进 4</div>
						</div>
					</div>
					<div class="attention-module" 
						style="border: solid 0px green; width:40%;height: 90%; float: right; margin-right: 10px;">
						<ul class="list" style="">
						<li><a href="subjectNews-detail-402821ec536001af015360119cfb0009.html">关于参加2016年吹牛逼大赛的工作知道</a></li>
						<li><a href="subjectNews-detail-402821ec536001af015360119cfb0009.html">关于参加2016年吹牛逼大赛的工作知道</a></li>
							<li><a href="subjectNews-detail-402821ec536001af015360119cfb0009.html">关于参加2016年吹牛逼大赛的工作知道</a></li>
							<li><a href="subjectNews-detail-402821ec536001af015360119cfb0009.html">关于参加2016年吹牛逼大赛的工作知道</a></li>
							<li><a href="subjectNews-detail-402821ec536001af015360119cfb0009.html">关于参加2016年吹牛逼大赛的工作知道</a></li>
							<li><a href="subjectNews-detail-402821ec536001af015360119cfb0009.html">关于参加2016年吹牛逼大赛的工作知道</a></li>
							<li><a href="subjectNews-detail-402821ec536001af015360119cfb0009.html">关于参加2016年吹牛逼大赛的工作知道</a></li>
						
						</ul>
					</div>
				</div>
			</div>
			<!--  -->
			<div class="fix-right" style="height: 350px;width:300px; ">
				<div class="essence-title">
					创业<span style="color: yellow; font-size: 22px;">视频</span> <label
						class="a"
						onclick="window.open('subject-list.106103.402821f0515cbab601515cbce93f0002.html')">更多&gt;&gt;</label>
				</div>
				<div style="width: 100%; height: 70%;margin-top: 10px;">
						<img style="width: 100%; height: 100%"
							src="${ctx}/upload/1445505770748_6441.png">
				</div>
				<ul  class="myul" style="margin-top: 5px;">
					<li><a href="subjectNews-detail-402821ec536001af015360119cfb0009.html">关于参加2016年吹牛逼大赛的工作知道</a></li>
					<li><a href="subjectNews-detail-402821ec536001af015360119cfb0009.html">关于参加2016年吹牛逼大赛的工作知道</a></li>
				</ul>	
				</div>
			</div>
		</div>
		<div class="space"></div>
	</div>
	<jsp:include page="gas-station-bottom.jsp" />
	<script>
		function bulidSlider() {
			var slider = $('.viwepager').smallslider({
				time : 5000,
				switchTime : 1000,
				onImageStop : false,
				showButtons : true,
				buttonPosition : 'rightBottom',
				switchEffect : 'ease',
				buttonOffsetY : 10,
				switchEase : 'easeOutQuad',
				switchPath : 'left',
				switchMode : 'hover',
				showIntro : false,
				showText : false,
				beforeSlide : function(index) {
					switchShaft(index);
				}
			});
			$(".shaft li").on("click", function() {
				slider.slideTo($(this).index());
			});
		}
		var animating = false;
		function switchShaft(index) {
			if (animating)
				return;
			$(".shaft li:eq(" + index + ")").addClass("selected").siblings()
					.removeClass("selected");
			switchViwepager(index);
		}
		function switchViwepager(index) {
			var prevSelected = $(".brief li.selected");
			var currentSelected = $(".brief li:eq(" + index + ")");
			currentSelected.css("left", prevSelected.index() > currentSelected
					.index() ? "160px" : "-160px");
			currentSelected.addClass("selected");
			currentSelected.animate({
				left : 0
			}, 1000, function() {
				currentSelected.css("left", "0");
				animating = false;
			});
			prevSelected
					.animate(
							{
								left : prevSelected.index() > currentSelected
										.index() ? "-160px" : "160px"
							}, 1000, function() {
								prevSelected.removeClass("selected");
								animating = false;
							});
			animating = true;
		}
	<%-- 图片新闻 --%>
		$.post("subject-findSubjectNews.html", {
			'page.pageLength' : 4,
			'subject.id' : '${subject.id}',
			'subjectNews.type' : '106101'
		}, function(data) {
			//     	alert(data.results.length);
			if (!data.results.length)
				return;
			$.each(data.results, function(index, obj) {
				var selected = index == 0 ? " class='selected'" : "";
				$(".viwepager ul").append(
						"<li><img onclick='window.open(\"subjectNews-detail-"
								+ obj.id + ".html\")' src='${ctx}/"
								+ obj.imageUrl + "'></li>");
				$(".viwepager-brief ul.brief").append(
						"<li"+selected+"><div class='title' onclick='window.open(\"subjectNews-detail-"
								+ obj.id + ".html\")'>" + obj.title
								+ "</div><div class='content'>"
								+ formatContent(obj.contents.content, 120)
								+ "</div></li>");
				$(".viwepager-brief ul.shaft").append("<li"+selected+"></li>");
			});
			bulidSlider();
		}, 'json');
	</script>