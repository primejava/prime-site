<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@include file="/taglibs.jsp"%>
<%@include file="/common/s.jsp"%>
<link rel="stylesheet" href="${ctx}/resources/subject/main-skin.css"
	type="text/css" />
<style type="text/css">
div {
/* 	border: solid 1px red; */
}

html {
	height: 1000px
}

.three-wrapper{width: 1200px;height: 500px;position: relative;z-index: 5;background-color: #fff;left: -20px;}
.three-module{width: 310px;height: 100%;margin-left: 20px;float: left;}
.three-module .title{height: 50px;line-height: 50px;border-bottom: 1px solid #666666;font-size: 18px;color: #000;}
.three-module .module-title{width: 170px;height: 30px;line-height: 25px;color: #000;font-size: 14px;font-weight: bold;cursor: pointer;}
.three-module .first-content{width: 100%;height: 255px;}
.three-module .first-content img{border: 0;width: 100%;height: 140px;}

.myul.li{width: 100%;height: 30px;line-height: 30px;border-bottom: 1px dashed #c9ced3;margin-bottom: 2px;word-break:keep-all;white-space:nowrap;overflow:hidden;text-overflow:ellipsis;}
.myul li a{font-size: 12px;font-weight:bold;color: #666;vertical-align: middle;}
.myul li a:hover{text-decoration: underline;}

.one-attention{ width: 980px;height: 430px;border-right: 1px solid #e9e9e9;padding-right: 20px;}
.one-attention .title{height: 40px;width: 100%;background: url(../images/website/attention.png) no-repeat right bottom;line-height: 40px;font-size: 36px;color: #004c8f;position: relative;}
.one-attention .title .more{width: 550px;height: 22px;position: absolute;bottom: 0;right: 0;margin-right: 1px;}
.one-attention .title .more .more-left{height: 22px;width: 50%;line-height: 22px;color: #cbd0d4;font-size: 12px;}
.one-attention .title .more .more-right{height: 22px;width: 50%;line-height: 22px;text-align: right;}
.one-attention .title .more .more-right a{font-size: 12px;color: #929ba9;}
.one-attention .title .more .more-right a:hover{text-decoration: underline;}
.one-attention .content{width: 100%;height: 400px;}
.one-attention-module{width: 33.3%;height: 175px;padding-bottom: 5px;border-bottom: 1px solid #ccc;position: relative;margin-top: 10px;float: left;}
.one-attention-module .module-title{width:80%;height: 20%;line-height: 35px;color: #000;font-size: 16px;font-weight: bold;cursor: pointer;}
.one-attention-module .module-title:hover{text-decoration: underline;}
.one-attention-module .module-content{width:100%;height: 70%;font-size: 12px;line-height: 25px;overflow: hidden;}
.one-attention-module .calendar{width: 60px;height: 60px;background-color:yellow;}
.one-fix-left{width: 220px;position: absolute;top: 0;left: 0;}
.one-adaptive-right{margin-left: 70px;height: auto;}

</style>
<script src="http://localhost:8080/rap.plugin.js?projectId=1"></script>
<div class="gas-station">
	<img src="${ctx}/upload/subject-top-bg.png" onError="defaultImg(this)"
		class="background">
	<div class="container">
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
		<!-- *****************************第一栏开始********************************** -->
		<div class="one-attention">
			<div class="title">
				<div class="more">
					<div class="fix-right more-right">
						<a
							href="subject-list.106100.402821f0515cbab601515cbce93f0002.html"
							target="_blank">更多>></a>
					</div>
				</div>
				最新<span style="color: yellow; font-size: 22px;">动态</span>
			</div>
			<div class="space"></div>
			<div class="content">
				<div class="one-attention-module" style="padding-b0ttom:0px;">
					<div class="one-adaptive-right" style="width: 73%;height:100%;">
						<div class="module-title overflow-omit">电邮之父雷·汤姆林森离世：他让邮件有了@</div>
						<div class="module-content ">
						据《商业内幕》杂志（Business
							Insider）报道，当地时间3月5日，“电子邮件之父”雷·汤姆林森报道，当地时间3月5日，“电子邮件之父”雷·汤姆林森报道，当地时间3月5日，“电子邮件之父”雷·汤姆林森报道，当地时间3月5日，“电子邮件之父”雷·汤姆林森报道，当地时间3报道，当地时间3月5日，“电子邮件之父”雷·汤姆林森报道，当地时间3月5日，“电子邮件之父”雷·汤姆林森月5日，“电子邮件之父”雷·汤姆林森（Ray Tomlinson...
						</div>
					</div>
					<div class="one-fix-left calendar" >
						<div style="text-align: center; "><span style="font-size: 28;font-weight:bold;">24</span></div>
						<div style="text-align: center;"><span style="font-size: 11;font-weight:bold; ">2016.05</span></div>
					</div>
				</div>
				<div class="one-attention-module" style="padding-b0ttom:0px;">
					<div class="one-adaptive-right" style="width: 73%;height:100%;">
						<div class="module-title overflow-omit">电邮之父雷·汤姆林森离世：他让邮件有了@</div>
						<div class="module-content ">
						据《商业内幕》杂志（Business
							Insider）报道，当地时间3月5日，“电子邮件之父”雷·汤姆林森报道，当地时间3月5日，“电子邮件之父”雷·汤姆林森报道，当地时间3月5日，“电子邮件之父”雷·汤姆林森报道，当地时间3月5日，“电子邮件之父”雷·汤姆林森报道，当地时间3报道，当地时间3月5日，“电子邮件之父”雷·汤姆林森报道，当地时间3月5日，“电子邮件之父”雷·汤姆林森月5日，“电子邮件之父”雷·汤姆林森（Ray Tomlinson...</div>
					</div>
					<div class="one-fix-left calendar" >
						<div style="text-align: center; "><span style="font-size: 28;font-weight:bold;">24</span></div>
						<div style="text-align: center;"><span style="font-size: 11;font-weight:bold; ">2016.05</span></div>
					</div>
				</div>
				<div class="one-attention-module" style="padding-b0ttom:0px;">
					<div class="one-adaptive-right" style="width: 73%;height:100%;">
						<div class="module-title overflow-omit">电邮之父雷·汤姆林森离世：他让邮件有了@</div>
						<div class="module-content ">
						据《商业内幕》杂志（Business
							Insider）报道，当地时间3月5日，“电子邮件之父”雷·汤姆林森报道，当地时间3月5日，“电子邮件之父”雷·汤姆林森报道，当地时间3月5日，“电子邮件之父”雷·汤姆林森报道，当地时间3月5日，“电子邮件之父”雷·汤姆林森报道，当地时间3报道，当地时间3月5日，“电子邮件之父”雷·汤姆林森报道，当地时间3月5日，“电子邮件之父”雷·汤姆林森月5日，“电子邮件之父”雷·汤姆林森（Ray Tomlinson...</div>
					</div>
					<div class="one-fix-left calendar" >
						<div style="text-align: center; "><span style="font-size: 28;font-weight:bold;">24</span></div>
						<div style="text-align: center;"><span style="font-size: 11;font-weight:bold; ">2016.05</span></div>
					</div>
				</div>
			</div>
		</div>
		<!-- *****************************第二栏开始********************************** -->
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
				<ul class="myul" style="margin-top: 5px;">
					<li><a href="subjectNews-detail-402821ec536001af015360119cfb0009.html">关于参加2016年吹牛逼大赛的工作知道</a></li>
					<li><a href="subjectNews-detail-402821ec536001af015360119cfb0009.html">关于参加2016年吹牛逼大赛的工作知道</a></li>
				</ul>	
			</div>
		</div>
		<!-- *****************************第二栏结束********************************** -->
		<!-- *****************************第三栏开始********************************** -->
		<div class="three-wrapper" style="height: 400px;">
		<div class="three-module">
			<div class="title">
				校友信息 <label class="a"
					onclick="window.open('subject-list.106104.402821f0515cbab601515cbce93f0002.html')">更多&gt;&gt;</label>
			</div>
			<div class="first-content" style="margin-top: 20px;">
				<ul  class="myul" style="margin-top: 5px;">
					<li><a href="subjectNews-detail-402821ec536001af015360119cfb0009.html">关于参加2016年吹牛逼大赛的工作知道</a></li>
					<li><a href="subjectNews-detail-402821ec536001af015360119cfb0009.html">关于参加2016年吹牛逼大赛的工作知道</a></li>
					<li><a href="subjectNews-detail-402821ec536001af015360119cfb0009.html">关于参加2016年吹牛逼大赛的工作知道</a></li>
					<li><a href="subjectNews-detail-402821ec536001af015360119cfb0009.html">关于参加2016年吹牛逼大赛的工作知道</a></li>
					<li><a href="subjectNews-detail-402821ec536001af015360119cfb0009.html">关于参加2016年吹牛逼大赛的工作知道</a></li>
				</ul>
				<img src="${ctx}/upload/1445505770748_6441.png"/>
			</div>
		</div>
		<div class="three-module">
			<div class="title">
				创新创业 <label class="a"
					onclick="window.open('subject-list.106104.402821f0515cbab601515cbce93f0002.html')">更多&gt;&gt;</label>
			</div>
			<div class="first-content" >
				<img src="${ctx}/upload/1445505770748_6441.png">
					<ul  class="myul" style="margin-top: 5px;">
					<li><a href="subjectNews-detail-402821ec536001af015360119cfb0009.html">关于参加2016年吹牛逼大赛的工作知道</a></li>
					<li><a href="subjectNews-detail-402821ec536001af015360119cfb0009.html">关于参加2016年吹牛逼大赛的工作知道</a></li>
					<li><a href="subjectNews-detail-402821ec536001af015360119cfb0009.html">关于参加2016年吹牛逼大赛的工作知道</a></li>
					<li><a href="subjectNews-detail-402821ec536001af015360119cfb0009.html">关于参加2016年吹牛逼大赛的工作知道</a></li>
					<li><a href="subjectNews-detail-402821ec536001af015360119cfb0009.html">关于参加2016年吹牛逼大赛的工作知道</a></li>
				</ul>
			</div>
		</div>

		<div class="three-module" >
			<div class="title">
				推荐书籍 <label class="a"
					onclick="window.open('subject-list.106104.402821f0515cbab601515cbce93f0002.html')">更多&gt;&gt;</label>
			</div>
			<div class="first-content" style="margin-top: 20px;" >
				<div style="width: 100%;height:60%;">
					<div style="float: left;width: 40%;height:100%;">
					<img style="float: left;width: 100%;height:100%;" src="${ctx}/upload/1445505770748_6441.png">
					</div>
					<div style="float: right;width: 60%;height:100%;padding-left: 5px;">
						<div class="module-title overflow-omit">电邮之父雷·汤姆林森离世：他让邮件有了@</div>
						<div  style="overflow: hidden;height:85%;">
						据《商业内幕》杂志（Business
							Insider）报道，当地时间3月5日，“电子邮件之父”雷·汤姆林森报道，当地时间3月5日，“电子邮件之父”雷·汤姆林森报道，当地时间3月5日，“电子邮件之父”雷·汤姆林森报道，当地时间3月5日，“电子邮件之父”雷·汤姆林森报道，当地时间3报道，当地时间3月5日，“电子邮件之父”雷·汤姆林森报道，当地时间3月5日，“电子邮件之父”雷·汤姆林森月5日，“电子邮件之父”雷·汤姆林森（Ray Tomlinson...
						</div>
					</div>
				</div>
				<div style="width: 100%;height:40%;">
					<div style="float:left;width: 50%;" >
					<ul  class="myul" style="margin-top: 5px;">
						<li><a href="subjectNews-detail-402821ec536001af015360119cfb0009.html">关于参加2016年吹牛逼大赛</a></li>
						<li><a href="subjectNews-detail-402821ec536001af015360119cfb0009.html">关于参加2016年吹牛逼大赛</a></li>
						<li><a href="subjectNews-detail-402821ec536001af015360119cfb0009.html">关于参加2016年吹牛逼大赛</a></li>
					</ul>
					</div>
					<div style="float:right;width: 50%;padding-left:10px;">
						<ul  class="myul" style="margin-top: 5px;">
						<li><a href="subjectNews-detail-402821ec536001af015360119cfb0009.html">关于参加2016年吹</a></li>
						<li><a href="subjectNews-detail-402821ec536001af015360119cfb0009.html">关于参加2016年吹牛</a></li>
						<li><a href="subjectNews-detail-402821ec536001af015360119cfb0009.html">关于参加2016年吹牛</a></li>
					</ul>
					</div>
				</div>
			</div>
		</div>
		</div>
		<!-- *****************************第三栏结束********************************** -->
	</div>
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