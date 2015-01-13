<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<base href="<%=basePath%>"></base>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>杰普――跑步社区</title>
<link rel="stylesheet" type="text/css" id="css" href="style/main.css" />
<link rel="stylesheet" type="text/css" id="css" href="style/style1.css" />
<script src="js/main.js" type="text/javascript"></script>
</head>
<body>
	<div id="btm">
		<div id="main">

			<div id="header">
				<div id="top"></div>
				<div id="logo">
					<h1>跑步社区</h1>
				</div>
				<div id="logout">
					<a href="login.html">注 销</a> | 收 藏
				</div>
				<jsp:include page="member-header-menu.jsp" flush="true"></jsp:include>
			</div>

			<div id="content" align="center">
				<div id="center">
					<table style="margin-top: 50px; border: 1px solid #cccccc;"
						width="500" align="center">
						<tr>
							<td class='titlehead'><img src="images/icon01.gif"
								width="14" height="14" /> &nbsp;&nbsp; <b>系统提示</b></td>
						</tr>
						<tr>
							<td>
								<table width="100%" height="150">
									<tr>
										<td width="140" align="center"><img
											src="images/error.gif" width="80" height="72" /></td>
										<td>
											<div style="padding-bottom: 15px;" class="fontblue">
												<font color="red">对不起，您还没有创建个性化空间！</font>
											</div>
											<div style="padding-bottom: 15px;">
												<li><a href="member/createSpace.jsp" class="textlink02"><font
														color="red">点击这里</font></a>创建个性化空间！</li>
											</div>
										</td>
									</tr>
								</table>
							</td>
						</tr>
						<tr>
							<td colspan="3" height="30"></td>
						</tr>
					</table>
					<br /> <br />
					<jsp:include page="member-footer-menu.jsp" flush="true"></jsp:include>

				</div>
				<div id="right">
					<h2>&nbsp;</h2>
					<ul>
						<li></li>
					</ul>
				</div>
				<div class="clear"></div>


			</div>

			<div id="footer">
				<div id="copyright">
					<div id="copy">
						<p>CopyRight&copy;2013</p>
						<p>跑步社区(www.irun.com)</p>
					</div>
				</div>
				<div id="bgbottom"></div>
			</div>

		</div>
	</div>
</body>
</html>
