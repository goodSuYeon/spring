<%@page import="java.util.Date"%>
<%@page import="kr.or.ddit.common.model.PageVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@include file="/WEB-INF/views/common/common_lib.jsp"%>
<script>
// 문서 로딩이 완료되고 나서 실행되는 영역
$(function(){
	$('.user').on('click',function(){

		var userid = $(this).data('userid');
		$('#userid').val(userid);
		$('#frm').submit();
	})
	
	$('#insertBtn').on('click', function(){
		 location.href="${cp }/user/registuserTiles"
	})
})
</script>

<body>
	<form id="frm" action="${cp }/user/user" >
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
							<tr class="user" data-userid="${user.userid}">
								<td>${user.userid }</td>
								<td>${user.usernm }</td>
								<td>${user.alias }</td>
								<td>fmt : <fmt:formatDate value="${user.reg_dt}"
										pattern="yyyy-MM-dd" /></td>
							</tr>
						</c:forEach>
					</table>
				</div>

				<a id="insertBtn" class="btn btn-default pull-right">사용자 등록</a> <a
					id="insertBtn" class="btn btn-default pull-right"
					href="${cp }/user/excelDownload">사용자 엑셀다운로드</a>

				<div class="text-center">
					<ul class="pagination">
						<li class="prev"><a
							href="${cp }/user/pagingUser?page=1&pageSize=${pageVo.getPageSize() }">«</a>
						</li>

						<c:forEach begin="1" end="${pagination }" var="i">
							<c:choose>
								<c:when test="${pageVo.getPage() == i }">
									<li class="active"><span>${i }</span></li>
								</c:when>
								<c:otherwise>
									<li><a
										href="${cp }/user/pagingUser?page=${i }&pageSize=${pageVo.getPageSize() }">${i }</a></li>
								</c:otherwise>
							</c:choose>
						</c:forEach>

						<li class="next"><a
							href="${cp }/user/pagingUser?page=4&pageSize=${pageVo.getPageSize() }">»</a>
						</li>
					</ul>
				</div>
			</div>
		</div>
	</div>
</body>