<%@page import="java.util.Date"%>
<%@page import="kr.or.ddit.common.model.PageVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<title>Jsp</title>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<%@include file="/WEB-INF/views/common/common_lib.jsp"%>
<link href="${cp }/css/dashboard.css" rel="stylesheet">
<link href="${cp }/css/blog.css" rel="stylesheet">
<script>
// 문서 로딩이 완료되고 나서 실행되는 영역
$(function(){
	$('.user').on('click',function(){

		var userid = $(this).data('userid');
		$('#userid').val(userid);
		$('#frm').submit();
	})
	
	$('#insertBtn').on('click', function(){
		 location.href="${cp }/user/registUser"
	})
})
</script>
</head>

<body>
<%-- <%
	// 서블릿에서 만들어서 보낸 데이터를 보여준다.
	List<UserVo> userList = (List<UserVo>)request.getAttribute("userList");

%> --%>

	<form id="frm" action="${cp }/user/user" >
		<input type="hidden" id="userid" name="userid" value="" />
	</form>
	
	<%@ include file="/WEB-INF/views/common/header.jsp" %>

<div class="container-fluid">
		<div class="row">

			<div class="col-sm-3 col-md-2 sidebar">
				<%@ include file="/WEB-INF/views/common/left.jsp"%>
			</div>
			
			

			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">


				<div class="row">
					<div class="col-sm-8 blog-main">
						<h2 class="sub-header">사용자</h2>
						<div class="table-responsive">
							<table class="table table-striped">
								<tr>
									<th>사용자 아이디</th>
									<th>사용자 이름</th>
									<th>사용자 별명</th>
									<th>등록일시</th>
								</tr>
								
								<c:forEach items="${userList }" var="user">
								<tr class="user" data-userid="${user.userid}">
									<td>${user.userid }</td>
									<td>${user.usernm }</td>
									<td>${user.alias }</td>
									<td>fmt : <fmt:formatDate value="${user.reg_dt}" pattern="yyyy-MM-dd" /></td>
								</tr>
								</c:forEach>
							</table>
						</div>
						
						<a id="insertBtn" class="btn btn-default pull-right">사용자 등록</a>
					    <a id="insertBtn" class="btn btn-default pull-right" href="${cp }/user/excelDownload">사용자 엑셀다운로드</a>
					    
						<div class="text-center">
							<ul class="pagination">
								<li class="prev">
									<a href="${cp }/user/pagingUser?page=1&pageSize=${pageVo.getPageSize() }">«</a>
								</li>

								<c:forEach begin="1" end="${pagination }" var="i">
								 <c:choose>
									<c:when test="${pageVo.getPage() == i }">
										 <li class="active"><span>${i }</span></li>
									</c:when>
									<c:otherwise>
										<li><a href="${cp }/user/pagingUser?page=${i }&pageSize=${pageVo.getPageSize() }">${i }</a></li>
									</c:otherwise>
								</c:choose>	
								</c:forEach>
								
								<li class="next">
									<a href="${cp }/user/pagingUser?page=4&pageSize=${pageVo.getPageSize() }">»</a>
								</li>
							</ul>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
</body>

</html>