<%@page import="java.util.Date"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
</head>
<body>
	<form id="frm" action="${cp }/" >
		<input type="hidden" id="userid" name="userid" value="" />
	</form>
	
	<div class="container-fluid">

		<div class="row">
			<div class="col-sm-8 blog-main">
				<h2 class="sub-header">사용자(tiles)</h2>
				<div class="table-responsive">
					<table class="table table-striped">
						<tr>
							<th>사용자 아이디</th>
							<th>사용자 이름</th>
							<th>사용자 별명</th>
							<th>등록일시</th>
						</tr>

						<c:forEach items="${userList }" var="user">
							<tr>
								<td>${user.userid }</td>
								<td>${user.usernm }</td>
								<td>${user.alias }</td>
								<td>fmt : <fmt:formatDate value="${user.reg_dt}"
										pattern="yyyy-MM-dd" /></td>
							</tr>
						</c:forEach>
					</table>
				</div>

				<a class="btn btn-default pull-right">사용자 등록</a>

				<div class="text-center">
					<ul class="pagination">
						<li><a href="#">1</a></li>
						<li><a href="#">2</a></li>
						<li><a href="#">3</a></li>
						<li><a href="#">4</a></li>
						<li><a href="#">5</a></li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>
</html>