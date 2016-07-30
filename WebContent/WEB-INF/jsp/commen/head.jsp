<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@taglib prefix="s" uri="/struts-tags"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" href="css/style.default.css" type="text/css" />
<script type="text/javascript" src="js/plugins/jquery-1.7.min.js"></script>
<script type="text/javascript"
	src="js/plugins/jquery-ui-1.8.16.custom.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery.cookie.js"></script>
<script type="text/javascript" src="js/plugins/jquery.uniform.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery.flot.min.js"></script>
<script type="text/javascript"
	src="js/plugins/jquery.flot.resize.min.js"></script>
<script type="text/javascript" src="js/plugins/jquery.slimscroll.js"></script>
<script type="text/javascript" src="js/custom/general.js"></script>
<script type="text/javascript" src="js/custom/dashboard.js"></script>

</head>
</head>
<body>
	<div class="bodywrapper">
		<div class="topheader">
			<div class="left">
				<h1 class="logo">
					岭师青年<span>投稿系统</span>
				</h1>

				<span class="slogan"> <s:if test="#session.admin!=null">
					后台管理
				</s:if>

				</span> <br clear="all" />
			</div>
			<!--left-->
			<div class="right">

				<div class="userinfo">
					<img src="images/thumbs/avatar.png" alt="" /> <span> <s:if
							test="#session.admin==null">
							${session.author.getA_name() }
						</s:if> <s:else>
							${session.admin.getAdmin_account() }
						</s:else>
					</span>
				</div>
				<!--userinfo-->

				<div class="userinfodrop">
					<div class="avatar">
						<a href=""><img src="images/thumbs/avatarbig.png" alt="" /></a>

					</div>
					<!--avatar-->
					<div class="userdata">
						<h4>
							<s:if test="#session.admin==null">
							${session.author.getA_name() }
						</s:if>
							<s:else>
							${session.admin.getAdmin_account() }
						</s:else>
						</h4>
						<br> <span class="email"> <s:if
								test="#session.admin==null">
							${session.author.getA_email() }
						</s:if> <s:else>
							${session.admin.getAdmin_account() }
						</s:else>
						</span>
						<!--<span class="email">${ admin_account } </span>-->
						<ul>
							<s:if test="#session.admin==null">
								<li><a href="author_updateInput.action?a_id=${session.author.getA_id() }">编辑资料</a></li>
								<li><a href="author_updatePasswordInput.action">修改密码</a></li>
							</s:if>
							<li><a href="./login_logout.action">退出</a></li>
						</ul>
					</div>
					<!--userdata-->
				</div>
				<!--userinfodrop-->
			</div>
			<!--right-->
		</div>
		<!--topheader-->

	</div>
</body>
</html>