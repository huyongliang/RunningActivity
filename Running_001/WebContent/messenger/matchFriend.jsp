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
<base href="<%=basePath%>" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>杰普――跑步社区</title>
<link rel="stylesheet" type="text/css" id="css" href="style/main.css" />
<link rel="stylesheet" type="text/css" id="css" href="style/style1.css" />
<link rel="stylesheet" type="text/css" id="css" href="style/style.css" />

<style type="text/css">
<!--
table {
	border-spacing: 1px;
	border: 1px solid #A2C0DA;
}

td, th {
	padding: 2px 5px;
	border-collapse: collapse;
	text-align: left;
	font-weight: normal;
}

thead tr th {
	background: #B0D1FC;
	border: 1px solid white;
}

thead tr th.line1 {
	background: #D3E5FD;
}

thead tr th.line4 {
	background: #C6C6C6;
}

tbody tr td {
	height: 35px;
	background: #CBE2FB;
	border: 1px solid white;
	vertical-align: middle;
}

tbody tr td.line4 {
	background: #D5D6D8;
}

tbody tr th {
	height: 35px;
	background: #DFEDFF;
	border: 1px solid white;
	vertical-align: middle;
}

tfoot tr td {
	height: 35px;
	background: #F5F5F5;
	border: 1px solid white;
	vertical-align: middle;
	text-align: right
}
-->
</style>

<script src="js/common.js" type="text/javascript"></script>
<script type="text/javascript" src="js/common.js"></script>

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
				<jsp:include page="../member/member-header-menu.jsp"></jsp:include>
			</div>

			<jsp:include page="friend-header-menu.jsp" flush="true"></jsp:include>
			<br /> <br />

			<div id="content" align="center">
				<div id="center">
					<br /> <br />
					<table width="600" border="0" align="center" cellpadding="0"
						cellspacing="0">
						<thead>
							<tr>
								<td colspan="2" height="40">
									<h4>-&gt;好友速配</h4>
								</td>
							</tr>
						</thead>
						<tbody>
							<tr>
								<td width="100%">
									<form action="messenger/friend-match-random.action?cmd=match"
										method="post" name="matchFriendForm">
										<table width="100%" cellspacing="1" cellpadding="3"
											align="center">
											<tr>
												<td colspan="4"><b>找到您跑步世界的另一半，您可以 <a
														href="messenger/friend-match-random.action?cmd=match1"><FONT
															color=#ff0000>马上速配</FONT></a> 一位跑友
												</b></td>
											</tr>
											<tr>
												<td colspan="4"><b>更可以按要求检索</b></td>
											</tr>
											<tr>
												<td align="center">年龄范围： <select name="age">
														<option value="1">10-19岁</option>
														<option value="2">20-29岁</option>
														<option value="3">30-39岁</option>
														<option value="unlimited" selected="selected">不限</option>
												</select>
												</td>
												<td align="center">性别： <select name="gender">
														<option value="0">男</option>
														<option value="1">女</option>
														<option value="unlimited" selected="selected">不限</option>
												</select>
												</td>
												<td align="center">所在省/城市： <select name="provinceCity">
														<option value="1">上海</option>
														<option value="2">北京</option>
														<option value="3">天津</option>
														<option selected="selected" value="unlimited">不限</option>
												</select>
												</td>
												<td align="center"><input type="submit" name="Submit2"
													value="开始检索" /></td>
											</tr>
										</table>
									</form>
								</td>
							</tr>
						</tbody>

						<table width="100%" cellspacing="1" cellpadding="3" align="center">
							<tfoot>
								<s:if test="randomList==null||randomList.size()==0">
									<p>没有数据</p>
								</s:if>
								<s:iterator value="randomList" var="m">
									<tr height="25">
										<td width="15%" align="center">${m.nickName}</td>
										<td width="15%" align="center">${m.gender}</td>
										<td width="15%" align="center">${m.age}</td>
										<td width="15%" align="center">${m.provinceCity}</td>
										<td width="20%" align="center"><a
											href="messenger/add-friend.action?friendName=${m.nickName}" >添加好友</a></td>
										<td width="20%" align="center"><a href="#">发送短信</a></td>
									</tr>
								</s:iterator>


							</tfoot>
						</table>
					</table>
					<br /> <br />
					<jsp:include page="../member/member-footer-menu.jsp"></jsp:include>
					<script type="text/javascript">
						if ("${addFriendMsg}" != null
								&& "${addFriendMsg}" != "")
							alert("${addFriendMsg}");
					</script>
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
