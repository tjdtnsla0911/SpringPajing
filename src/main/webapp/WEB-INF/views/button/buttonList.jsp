<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="utf-8">
<link rel="stylesheet"
	href="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/css/bootstrap.min.css">
<script
	src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.3/umd/popper.min.js"></script>
<script
	src="https://stackpath.bootstrapcdn.com/bootstrap/4.1.3/js/bootstrap.min.js"></script>
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Bootstrap 4</title>

</body>
</head>


<body>
	<div class="container">
		<div class="row">
			<div class="col">
				<button type="button" onclick="listss()" class="btn btn-primary">게시판리스트</button>
				<c:choose>
					<c:when test="${sessionScope.loginOK eq null}">

						<button type="button" onclick="login()" class="btn btn-primary">로그인하기</button>


					</c:when>
					<c:otherwise>
						<button type="button" onclick="logout()" class="btn btn-primary">로그아웃</button>
					</c:otherwise>
				</c:choose>

			</div>
		</div>
	</div>
</body>

<script>
	function listss() {
		location.href = "/list";
	}
	function login() {
		location.href = "/user/login";
	}
	function logout(){
		location.href="/user/logout";
	}
</script>
</html>