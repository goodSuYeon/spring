<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<div class="col-sm-3 col-md-2 sidebar">
	<ul class="nav nav-sidebar">
		<li class="active"><a href="${cp }/main.jsp">Main <span class="sr-only">(current)</span></a></li>
		<li class="active"><a href="${cp }/user/allUser">현재 사용자</a></li>
		<li class="active"><a href="${cp }/user/allUserTiles">현재 사용자(타일즈)</a></li>
		
		<li class="active"><a href="${cp }/user/pagingUser">사용자 페이징 리스트</a></li>
		<li class="active"><a href="${cp }/user/pagingUserTiles">사용자 페이징 리스트(타일즈)</a></li>
		<%--
			/alluser 요청을 처리할 servlet(controller)
			kr.or.ddit.user.controller.AllUser
				doGet(){
					(1). service객체를 통해서 전체 사용자 정보를 조회
					(2). request객체에 userList라는 속성명으로 (1)번에서 조회한 데이터를 설정
					(3). webapp/user/allUser.jsp로 응답을 생성하도록 forward
					    - allUser.jsp는 -> user.html을 참고하여 생성
						- header.jsp , left.jsp는 했던 거 재사용해 생성
						
						- user.html의 사용자 접오를 표현하는 테이블 태그의 tr부분을 
						  request에 저장된 userList속성 값으로 동적 생성해 화면에 출력한다.
				}
		 --%>
	</ul>
</div>