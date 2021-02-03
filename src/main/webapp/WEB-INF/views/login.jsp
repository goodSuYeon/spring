<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="description" content="">
<meta name="author" content="">
<link rel="icon" href="../../favicon.ico">
<title>Signin Template for Bootstrap</title>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<%@ include file="/WEB-INF/views/common/common_lib.jsp" %>
<script src="https://cdn.jsdelivr.net/npm/js-cookie@rc/dist/js.cookie.min.js"></script>

<!-- Bootstrap core CSS -->
<link href="${cp }/css/bootstrap.min.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${cp }/css/signin.css" rel="stylesheet">

<script>
	//html문서 로딩이 완료되고나서, 실행되는 코드
	$(function(){
		
		<c:if test="${msg != null }">    //msg-> "잘못된 사용자 정보입니다." 메세지가 비어있지 않다면
			alert("${msg}" + "ra");
		
		</c:if>
			
		//userid, rememberme 쿠키를 확인해 존재할 경우 값설정, 체크
		userid = Cookies.get("userid");
		rememberme = Cookies.get("rememberme");
		
		if(userid != undefined) {
				$("#userid").html(userid);
			}

	//signin 아이디를 select
	$("#signin").on("click", function(){
		//rememberme 체크박스가 체크 되어있는지 확인
		
		//(1상황) 체크되어 있을 경우
		if($("#rememberme").is(":checked") == true){
			
			// userid input에 있는 값을 userid쿠키로 저장
			Cookies.set("userid", $("#userid").val());
			// remember쿠키로 Y값을 저장
			Cookies.set("rememberme", "Y");
		}
		//(2상황)체크 해제 되어 있을 경우 - 더이상 사용하지 않는다는 의미로, userid와 rememberme 쿠키를 삭제
		else{
			Cookies.remove("userid");
			Cookies.remove("rememberme");
		}
			//form태그를 이용해 signin 요청
			$("#frm").submit();
		});
	});
</script>

</head>

<body>

    <div class="container">
		
      <form id="frm" class="form-signin" action="${cp }/login/process" method="post">
        <h2 class="form-signin-heading">Please sign in</h2>
        <label for="userid" class="sr-only">userId</label>
        <input type="text" value="userId" id="userid" name="userid" class="form-control" placeholder="Id" required autofocus>
        <label for="pass" class="sr-only">Password</label>
        <input type="password" value="brownpass" id="pass" name="pass" class="form-control" placeholder="Password" required>
        
        <div class="checkbox">
          <label>
            <input type="checkbox" id="rememberme" value="remember-me"> Remember me
          </label>
        </div>
        <button class="btn btn-lg btn-primary btn-block" id="signin" type="button">Sign in</button>
      </form>

    </div> <!-- /container -->

  </body>
</html>