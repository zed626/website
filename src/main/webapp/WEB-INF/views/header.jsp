<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<header id="home">
	<div class="navbar navbar-default navbar-static-top">
		<div class="container">
			<div class="navbar-header">
				<a class="navbar-brand" href="<%=request.getContextPath()%>/home"><img src="<%=request.getContextPath()%>/resources/images/homePageImages/logo/blue-logo.png" width="160" height="40" alt="logo"/></a>
			</div>
			<div class="navbar-collapse collapse" id="ftheme">
			<ul class="nav navbar-nav navbar-right">
				<li><a href="<%=request.getContextPath()%>/home"><h5>首页</h5></a></li>
				<li><a href="<%=request.getContextPath()%>/product"><h5>产品介绍</h5></a></li>
				<li><a href="<%=request.getContextPath()%>/news"><h5>新闻动态</h5></a></li>
				<li><a href="<%=request.getContextPath()%>/about"><h5>关于我们</h5></a></li>
				<li><a href="<%=request.getContextPath()%>/joinUs"><h5>加入我们</h5></a></li>
			</ul>
		</div>
		</div>
	</div>
</header>