<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>

<head>
	<title>武汉艾瓦客机器人有限公司</title>
	<%@include file="common-css-js.jsp" %>
	<link rel="shortcut icon" href="<%=request.getContextPath()%>/resources/images/homePageImages/logo/web-logo.ico" type="image/x-icon" />
</head>

<body id="top" data-spy="scroll">
	<!-- header -->
	<%@ include file="header.jsp" %>

	<!--slider-->
	<div id="slider" class="flexslider">
		<ul class="slides">
			<li>
				<img src="<%=request.getContextPath()%>/resources/images/homePageImages/slides/slider1.jpg" alt="slider">
				<!-- <div class="caption">
					<p>Lorem Ipsum is simply dummy text of the printing and typesetting industry.</p>
					<button class="btn">Read More</button>
				</div> -->
			</li>
			<li>
				<img src="<%=request.getContextPath()%>/resources/images/homePageImages/slides/slider2.jpg" alt="slider">
			</li>
			<li>
				<img src="<%=request.getContextPath()%>/resources/images/homePageImages/slides/slider3.jpg" alt="slider">
			</li>
		</ul>
	</div>
	<br>
	
	<div id="news">
		<div class="container">
			<h4>News</h4>
			<h2>新闻动态</h2>
			<div class="col-xs-4">
				<img src="<%=request.getContextPath()%>/resources/images/homePageImages/news/news0128.jpg" width="600" alt="news">
				<h4><strong>热烈祝贺我司陈教授荣获青桐年汇路演大赛冠军</strong></h4>
				<p>2018年1月26日，阿尔法文创基地举办的光谷·青桐年汇上，数十位北京、上海等地的嘉宾及投资人，省市发改委、省市科技部门、东湖高新区管委会领导、与上百家本地创服机构、1000创业者一起在大雪中约定“2018，一起创”。</p>
				<a href="<%=request.getContextPath()%>/news/news0126"><h4><strong>阅读更多></strong></h4></a>
			</div>
			<div class="col-xs-4">
				<img src="<%=request.getContextPath()%>/resources/images/homePageImages/news/news1223.jpg" width="600" alt="news">
				<h4><strong>美国南加州终身院士黄铠教授莅临我司指导考察</strong></h4>
				<p>2017年12月23日，黄铠教授受我司陈敏教授的邀请亲临我司进行交流和指导。</p>
				<a href="<%=request.getContextPath()%>/news/news1223"><h4><strong>阅读更多></strong></h4></a>
			</div>
			<div class="col-xs-4">
				<img src="<%=request.getContextPath()%>/resources/images/homePageImages/news/news1.jpg" width="600" alt="news">
				<h4><strong>"新三板转板热潮下的风险与机遇论坛暨第十四期路演会"盛大开幕</strong></h4>
				<p>"新三板转板热潮下的风险与机遇论坛暨第十四期新三板联合会项目投融资路演对接会"在武汉中铁科技大厦会议中心拉开序幕。</p>
				<a href="<%=request.getContextPath()%>/news/news1124"><h4><strong>阅读更多></strong></h4></a>
			</div>
		</div>
		<br>
		<div class="container">
			<div class="col-xs-4">
				<img src="<%=request.getContextPath()%>/resources/images/homePageImages/news/news2.jpg" width="600" alt="news">
				<h4><strong>中国.光谷国际人工智能产业峰会武汉留学生创业园专场</strong></h4>
				<p>"2017中国.光谷国际人工智能产业峰会"武汉留学生创业园专场活动在武汉东湖国际会议中心蕙兰厅举行。</p>
				<a href="<%=request.getContextPath()%>/news/news1119"><h4><strong>阅读更多></strong></h4></a>
			</div>
			<div class="col-xs-4">
				<img src="<%=request.getContextPath()%>/resources/images/homePageImages/news/news3.jpg" width="600" alt="news">
				<h4><strong>GAIS 2017全球天使投资峰会</strong></h4>
				<p>这是一场天使投资各方思想碰撞与智慧交融的盛会，汇聚全球风险投资行业的领军人物，集合顶尖风投机构，洞悉行业趋势、拓展行业人脉。</p>
				<a href="<%=request.getContextPath()%>/news/news1118"><h4><strong>阅读更多></strong></h4></a>
			</div>
			<div class="col-xs-4">
				<img src="<%=request.getContextPath()%>/resources/images/homePageImages/news/news4.jpg" width="600" alt="news">
				<h4><strong>武汉留学生创业园举办机器人项目投融资对接沙龙活动</strong></h4>
				<p>近年来机器人智能化程度不断提高，机器人既是先进制造业的关键支撑设备，也是改善人类生活方式的重要切入点。</p>
				<a href="<%=request.getContextPath()%>/news/news1018"><h4><strong>阅读更多></strong></h4></a>
			</div>
		</div>
		<div class="container">
			<h4>Industry News</h4>
			<h2>行业动态</h2>
			<div class="col-xs-12">
				<center><img src="<%=request.getContextPath()%>/resources/images/homePageImages/news/news6.jpg" alt="news"></center>
				<a href="<%=request.getContextPath()%>/news/news0129">
				<center><h3><strong>机器人有了“读心术”，艾瓦客用AI打造“新生命”</strong></h3></center>
				</a>				
			</div>
		</div>
	</div>
	
	<%@include file="footer.jsp" %>
</body>

</html>