<%@page import="kr.or.ddit.user.model.UserVo"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사용자 신규등록</title>
<%@include file="/WEB-INF/views/common/common_lib.jsp"%>
<script src="http://dmaps.daum.net/map_js_init/postcode.v2.js"></script>
<link href="${cp }/css/dashboard.css" rel="stylesheet">
<link href="${cp }/css/blog.css" rel="stylesheet">
<script>
	$(function() {
		$('#addrBtn').on('click', function() {
			new daum.Postcode({
				oncomplete : function(data) {
					$('#addr1').val(data.roadAddress); //도로주소
					$('#zipcode').val(data.zonecode); //우편번호
					//사용자 편의성을 위해 상세주소 입력 input 태그로 focus설정
					$('#addr2').focus();
					}
			}).open();

		});
	});
</script>

</head>

<body>
	<%@include file="/WEB-INF/views/common/header.jsp"%>

	<div class="container-fluid">
		<div class="row">

			<div class="col-sm-3 col-md-2 sidebar">
				<%@include file="/WEB-INF/views/common/left.jsp"%>
			</div>
			<div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
			
				spring message: <spring:message code="LANG" />
			
				<form class="form-horizontal" role="form"
					action="${cp }/user/registUser" method="POST"
					enctype="multipart/form-data">

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 아이디</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="userid" name="userid"
								placeholder="사용자 아이디" value="${userVo.userid}">

							<span style="color:red;"><form:errors path="userVo.userid" /></span>

							<input type="file" name="profile" />
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">사용자 이름</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="usernm" name="usernm"
								placeholder="사용자 이름" value="${userVo.usernm}">
						</div>
					</div>

					<div class="form-group">
						<label for="userNm" class="col-sm-2 control-label">별명</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="alias" name="alias"
								placeholder="별명" value="${userVo.alias}">
						</div>
					</div>

					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">비밀번호</label>
						<div class="col-sm-8">
							<input type="password" class="form-control" id="pass" name="pass"
								placeholder="비밀번호" value="${userVo.pass}">
						</div>
					</div>

					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">등록일자</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="reg_dt" name="reg_dt"
								placeholder="등록일자" value="${userVo.reg_dt}">
						</div>
					</div>

					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">도로주소</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="addr1" name="addr1"
								placeholder="도로주소" value="${userVo.addr1}" readonly>
						</div>
						<div class="col-sm-2">
							<button type="button" id="addrBtn" class="btn btn-default">주소검색</button>
						</div>
					</div>

					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">상세주소</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="addr2" name="addr2"
								placeholder="상세주소" value="${userVo.addr2}">
						</div>
					</div>
					<div class="form-group">
						<label for="pass" class="col-sm-2 control-label">우편번호</label>
						<div class="col-sm-8">
							<input type="text" class="form-control" id="zipcode"
								name="zipcode" placeholder="우편번호" value="${userVo.zipcode}"
								readonly>
						</div>
					</div>

					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-default">등록</button>
						</div>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>