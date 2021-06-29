<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%--fmt 태그 : 문자열, 날짜 숫자의 모양 형식 변경 --%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>게시글</title>
<style>
	#board-area{ min-height: 700px;  margin-bottom: 100px;}
	#board-content{ padding-bottom:150px;}

	.boardImgArea{
		height: 300px;
	}

	.boardImg{
		width : 100%;
		height: 100%;
		border : 1px solid #ced4da;
		max-width : 300px;
		max-height: 300px;
		
	}
	
	.replyWrite > table{
		width: 90%;
		align: center;
	}
	
	#replyContentArea{ width: 90%; }
	
	#replyContentArea > textarea{
	    resize: none;
    	width: 100%;
	}
	
	#replyBtnArea{
	    width: 100px;
	    text-align: center;
	}
	
	.rWriter{ margin-right: 30px;}
	.rDate{
		font-size: 0.7em;
		color : gray;
	}
	
	#replyListArea{
		list-style-type: none;
	}
	
	.board-dateArea{
		font-size: 14px;
	}
	
	
	.boardImg {
		width: 200px;
		height: 200px;
	}
	.thubnail{
		width: 300px;
		height: 300px;
	}
	
	.boardImg > img{
		width : 100%;
	}
</style>
</head>
<body>
	<jsp:include page="../common/header.jsp"></jsp:include>
	<div class="container  my-5">

		<div>

			<div id="board-area">

				<!-- Category -->
				<h6 class="mt-4">카테고리 : [${board.categoryName } ]</h6>
				
				<!-- Title -->
				<h3 class="mt-4"></h3>
				${board.boardTitle }
				<!-- Writer -->
				<p class="lead">
					작성자 :${board.memberName }
				</p>

				<hr>

				<!-- Date -->
				<p>
					<span class="board-dateArea">
						작성일 :<fmt:formatDate value="${board.createDate}" pattern="yy년 MM월 dd일 HH:mm:ss"/> 
						
						<br>
						마지막 수정일 :<fmt:formatDate value="${board.modifyDate}" pattern="yy년 MM월 dd일 HH:mm:ss"/>
					</span>
			 		<span class="float-right">조회수 ${board.readCount }</span>
				</p>

				<hr>
				
					<!-- 이미지 출력 -->
					
					<c:forEach items="${board.atList}" var="at">
						<c:choose>
							<c:when test = "${at.fileLevel ==0 && !empty at.fileName }">
								<c:set var = "img0" value="${contextPath}/${at.filePath}${at.fileName }"/>
							</c:when>
							<c:when test = "${at.fileLevel ==01&& !empty at.fileName }">
								<c:set var = "img1" value="${contextPath}/${at.filePath}${at.fileName }"/>
							</c:when>
							<c:when test = "${at.fileLevel ==2 && !empty at.fileName }">
								<c:set var = "img2" value="${contextPath}/${at.filePath}${at.fileName }"/>
							</c:when>
							<c:when test = "${at.fileLevel ==3 && !empty at.fileName }">
								<c:set var = "img3" value="${contextPath}/${at.filePath}${at.fileName }"/>
							</c:when>
						</c:choose>
					</c:forEach>
					
					<div class="form-inline mb-2">
						<label class="input-group-addon mr-3 insert-label">썸네일</label>
						<div class="boardImg thubnail" id="titleImgArea">
								<c:if test="${!empty img0}"> 
								<img id="titleImg" src="${img0 }">
								</c:if> 
						</div>
					</div>
	
					<div class="form-inline mb-2">
						<label class="input-group-addon mr-3 insert-label">업로드<br>이미지</label>
						<div class="mr-2 boardImg" id="contentImgArea1">
								<c:if test="${!empty img1}">
								<img id="contentImg1" src="${img1 }">
								</c:if>
						</div>
	
						<div class="mr-2 boardImg" id="contentImgArea2">
								<c:if test="${!empty img2}">
								<img id="contentImg2" src="${img2 }">
								</c:if>
						</div>
	
						<div class="mr-2 boardImg" id="contentImgArea3">
								<c:if test="${!empty img3}">
								<img id="contentImg3" src="${img3 }">
								</c:if>
						</div>
					</div>
				
				
				
				<hr>
				<!-- Content -->
				<div id="board-content"></div>
				${board.boardContent}

				<hr>
				 
				
				<div>
					<%-- 로그인된 회원과 해당 글 작성자가 같은 경우에만 버튼 노출--%>
						<button id="deleteBtn" class="btn btn-primary float-right">삭제</button> 
						<button id="updateBtn" class="btn btn-primary float-right">수정</button> 
					
						<a href="#" class="btn btn-primary float-right">목록으로</a>
				</div>
				
				
				<%-- 댓글 영역 --%>
			</div>



		</div>
	</div>
	<jsp:include page="../common/footer.jsp"></jsp:include>
	
	
	<script>
		
		
	</script>
</body>
</html>
