<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<html>
<head>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>
<%@ include file="../button/buttonList.jsp" %>
<title>게시판</title>
</head>
<body>




<c:choose>
    <c:when test="${sessionScope.loginOK eq null}">
    
    	<h2>로그아웃 했습니다.</h2>
		
    </c:when>
    <c:otherwise>
	<h2>로그인했습니다</h2>
    </c:otherwise>
</c:choose>
	<div id="root">
		<header>
			<h1>게시판</h1>
		</header>
		<hr />

		<nav>홈 - 글 작성</nav>
		<hr />

		<input type="text" value="" id="serchs" name="serchs"
			placeholder="검색할내용을 검색하세요" />
		<button onclick="serch(serchs)">찾기</button>
		<section id="container">
			<form role="form" method="post" action="/board/write">
				<table>
					<tr>
						<th>번호</th>
						<th>제목</th>
						<th>작성자</th>
						<th>등록일</th>
					</tr>
					<tbody id="ajax">

						<c:forEach begin="1" end="3" items="${list}" var="list">
			
							<tr>
								<td><c:out value="${list.bno}" /></td>
								<td><a href="/board/readView?bno=${list.bno}"><c:out value="${list.title}" /></a></td>
								<td><c:out value="${list.writer}" /></td>
								<td>
					    	<c:set var="result" value="${list.bno}" scope="session"/>
					    	<c:if test="${sessionScope.loginOK.id ==result}">
								<button type="button" id="${list.bno}"
								onclick="delect(${list.bno})">삭제</button>
	
							</c:if>
								</td>
								<td><fmt:formatDate value="${list.regdate}"
										pattern="yyyy-MM-dd" /></td>
							</tr>
						</c:forEach>		

					</tbody>
						</table>
				<p id="ajax2">
					<c:forEach begin="1" end="${pajing.allpage}" var="i">
						<button type="button" name="${i}" onclick="pajing(${i})">${i}</button>
					</c:forEach>
				</p>

			</form>
		</section>
		<hr />
	</div>


</body>
<!-- Jqery 영역 -->
<script>

var sat;
var hiko =0;
//0일때 페이징 새로 렌더링할곳

function rendering(){
	var hiko2;
	console.log('');
	$.ajax({
	    url: '/board/pajingRendering',
	    type: 'get',
	    dataType: 'text'
	})
	.done(function (data) { 
		hiko2 = data;
		console.log('새로 렌더링하는곳 받은 데이터 = ',data);
	
		$("#ajax2").empty();
		for(var as=1;as <=data;as++){
			str = "<button type=\"button\" name=\""+as+"\" onclick=\"pajing("+as+")\">"+as+"</button>";
		$("#ajax2").append(str);
		}
	/* 	if(hiko<=0){
			console.log('hiko가 0에왔다');
			pajing(hiko2);
		}
		if(hiko <= hiko2 ){
			console.log('if문의 hiko = ',hiko);
			console.log('if문의 hiko2 = ',hiko2);
			hiko = hiko2;
		}else if(hiko <=0){
			console.log('히코 0에옴');
		pajing(hiko2);
			
		}else{
			console.log('틀리다');
			console.log('else문의 hiko = ',hiko);
			console.log('else문의 hiko2 = ',hiko2);
			pajing(hiko2);
		} */
		//pajing(data);
		
	}).fail(function (jqXHR, textStatus, errorThrown) {
		serrorFunction(); 
		});	
}
//검색
function serch(serchs){
	console.log('찾은내용 =',$("#serchs").val());
	var title = $("#serchs").val();
		console.log('받은 id=',title);
		$.ajax({
		    url: '/board/serch/'+title,
		    type: 'get',
		    data: "data",
		    dataType: 'json'
		})
		.done(function (data) { 
	//나중에할꺼임
		}).fail(function (jqXHR, textStatus, errorThrown) {
			serrorFunction(); 
			});
	}
//시작할떄 1받을놈
 window.onload = function (i) {
	console.log(i);
	i = 1;
	pajing(i);
	}  
	

function  pajing(i){
	sat = i;
	console.log('받아온 i=',i);
	var sk = i;
	$.ajax({
	    url: '/board/pajing/'+i,
	    type: 'get',
	    data: i,
	    dataType: 'json'
	})
		.done(function (data) { 
		console.log('data.length는 (여긴페이징)? =',data.length);
	
	

		$("#ajax").empty();
		for(data2 of data){
			var deleteNo = data2.bno;
			var sessionid = "${sessionScope.loginOK.id}";
			if(deleteNo != sessionid){
				console.log('다르다에옴');
		
			console.log('델레트넘  =',deleteNo);
			console.log('세션스코프 = ',sessionid);
			
			str="<tr>"+
			"<td>"+data2.bno+"</td>\r\n" + 
			"<td><a href=\"/board/readView?bno="+data2.bno+"\">"+data2.title+"</a></td>\r\n" + 
			"<td>"+data2.writer+"</td>\r\n" +
			"<td></td>\r\n" +
			"<td>"+data2.regdate+"<td>"+
			"</tr>";
			$("#ajax").append(str);
			}else{
				console.log('같다에옴');
				str="<tr>"+
				"<td>"+data2.bno+"</td>\r\n" + 
				"<td><a href=\"/board/readView?bno="+data2.bno+"\">"+data2.title+"</a></td>\r\n" + 
				"<td>"+data2.writer+"</td>\r\n" +
				"<td><button type=\"button\" id=\""+data2.bno+"\"\r\n" + 
				"onclick=\"delect("+data2.bno+")\">삭제</button></td>\r\n" +
				"<td>"+data2.regdate+"<td>"+
				"</tr>";
				$("#ajax").append(str);
				
			}
		}
			var aaa = data.length;
		//if(aaa<=0){
			rendering();
		//}
		
	}).fail(function (jqXHR, textStatus, errorThrown) {
		serrorFunction(); 
		});	
}
//삭제부분
	function delect(bno){
		console.log('sat = ',sat);
		console.log('받은 id=',bno);
		$.ajax({
		    url: '/board/delete/'+bno,
		    type: 'DELETE',
		    data: bno,
		    dataType: 'json'
		})
		.done(function (data) { 
			console.log(data.bno);
			console.log('data.length = ',data.length);
			var str;
			//$("#ajax").empty();//여기서지우고
			//for(var data2 of data){
			/* str="<tr>"+
			"<td>"+data2.bno+"</td>\r\n" + 
			"<td><a href=\"/board/readView?bno="+data2.bno+"\">"+data2.title+"</a></td>\r\n" + 
			"<td>"+data2.writer+"</td>\r\n" +
			"<td><button type=\"button\" id=\""+data2.bno+"\"\r\n" + 
			"onclick=\"delect("+data2.bno+")\">삭제</button></td>\r\n" + 
			"<td>"+data2.regdate+"<td>"+
			"</tr>"+
			$("#ajax").append(str);
			} */
			////// 생각해보니 새로그릴필요가없음
			pajing(sat);
			

			
		}).fail(function (jqXHR, textStatus, errorThrown) {
			serrorFunction(); 
			});
	
	}
	</script>
</html>