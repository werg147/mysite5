<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<link href="${pageContext.request.contextPath }/assets/css/mysite.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/css/gallery.css" rel="stylesheet" type="text/css">
<link href="${pageContext.request.contextPath }/assets/bootstrap/css/bootstrap.css" rel="stylesheet" type="text/css">

<script type="text/javascript" src="${pageContext.request.contextPath }/assets/js/jquery/jquery-1.12.4.js"></script>
<script type="text/javascript" src="${pageContext.request.contextPath }/assets/bootstrap/js/bootstrap.js"></script>

</head>


<body>
	<div id="wrap">

		<c:import url="/WEB-INF/views/include/header.jsp"></c:import>
		<!-- //header -->
		<!-- //nav -->

		<c:import url="/WEB-INF/views/include/galleryAside.jsp"></c:import>
		<!-- //aside -->


		<div id="content">

			<div id="content-head">
				<h3>갤러리</h3>
				<div id="location">
					<ul>
						<li>홈</li>
						<li>갤러리</li>
						<li class="last">갤러리</li>
					</ul>
				</div>
				<div class="clear"></div>
			</div>
			<!-- //content-head -->


			<div id="gallery">
				<div id="list">
			
						<c:if test="${authUser != null}">
							<button id="btnImgUpload">이미지올리기</button>
							<div class="clear"></div>
						</c:if>

			
					<ul id="viewArea">
						
						<!-- 이미지반복영역 -->
						<c:forEach items="${galleryList}" var="galleryVo">
							<li id="areaDel" data-no="${galleryVo.no}" data-userNo="${galleryVo.userNo}">
								<div class="view" >
									<img class="imgItem" src="${pageContext.request.contextPath}/upload/${galleryVo.saveName}">
									<div class="imgWriter">작성자: <strong>${galleryVo.name}</strong></div>
								</div>
							</li>
						</c:forEach>
						<!-- 이미지반복영역 -->
						
						
					</ul>
				</div>
				<!-- //list -->
			</div>
			<!-- //board -->
		</div>
		<!-- //content  -->
		<div class="clear"></div>

		<c:import url="/WEB-INF/views/include/footer.jsp"></c:import>
		<!-- //footer -->

	</div>
	<!-- //wrap -->

	
		
	<!-- 이미지등록 팝업(모달)창 -->
	<div class="modal fade" id="addModal">
		<div class="modal-dialog">
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지등록</h4>
				</div>
				
				<!-- multipart post방식만 허용 -->
				<form method="post" action="${pageContext.request.contextPath}/gallery/upload" enctype="multipart/form-data">
					<div class="modal-body">
						<div class="form-group">
							<label class="form-text">글작성</label>
							<input id="addModalContent" type="text" name="content" value="" >
						</div>
						<div class="form-group">
							<label class="form-text">이미지선택</label>
							<input id="file" type="file" name="file" value="" >
						</div>
					</div>
					<div class="modal-footer">
						<button type="submit" class="btn" id="btnUpload">등록</button>
					</div>
				</form>
				
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->
	


	<!-- 이미지보기 팝업(모달)창 -->
	<div class="modal fade" id="viewModal">
		<div class="modal-dialog" >
			<div class="modal-content">
				<div class="modal-header">
					<button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
					<h4 class="modal-title">이미지보기</h4>
				</div>
				<div class="modal-body">
					
					<div class="formgroup" >
						<img id="viewModelImg" src =""> <!-- ajax로 처리 : 이미지출력 위치-->
					</div>
					
					<div class="formgroup">
						<p id="viewModelContent"></p>
					</div>
					
					<input id="modalNo" type="hidden" name="no" value="">
					<input id="userNo" type="text" name="userNo" value="">
					
				</div>
				
				<form method="post" action="${pageContext.request.contextPath}/gallery/remove">
				
					<div class="modal-footer">
						<button type="button" class="btn btn-default" data-dismiss="modal">닫기</button>
						<button type="button" class="btn btn-danger" id="btnDel">삭제</button>
						
						<input type="hidden" name="userNo" value="${galleryVo.userNo}">
					
					</div>
							
				</form>
				
			</div><!-- /.modal-content -->
		</div><!-- /.modal-dialog -->
	</div><!-- /.modal -->	


</body>

<script type="text/javascript">

	//등록버튼 모달창
	$("#btnImgUpload").on("click", function(){
		console.log("등록버튼 클릭")
		
		$("#addModal").modal();
	});

	
	//게시글 읽기 모달창
	$("#viewArea").on("click", "li", function(){
		console.log("읽기버튼 클릭");
		
		//모달창 no 수집
		var no = $(this).data("no");
		console.log(no);
		
		$("#modalNo").val(no);

		
		$.ajax({
			
			url : "${pageContext.request.contextPath}/gallery/read",		
			type : "post",
			//contentType : "application/json",
			data : {no: no},

			dataType : "json",
			success : function(galleryVo){
				/*성공시 처리해야될 코드 작성*/
				//console.log(galleryVo.content);
				
				//attr() 속성값 변경
				$("#viewModelImg").attr("src", "${pageContext.request.contextPath}/upload/" + galleryVo.saveName);
				$("#viewModelContent").text(galleryVo.content); //html
				
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});

		
		$("#viewModal").modal();
	});
	
	
	//삭제버튼 클릭  undefind.. userNo수집 어려움> 일단 no만으로 삭제
	$("#btnDel").on("click", function(){
		console.log("삭제버튼 클릭");
		
		var no = $("#modalNo").val();
		console.log(no);
		
		
        $.ajax({
			
			url : "${pageContext.request.contextPath}/gallery/remove",		
			type : "post",
			//contentType : "application/json",
			data : {no: no},

			dataType : "json",
			success : function(count){
				/*성공시 처리해야될 코드 작성*/

				if(count == 1){
					//모달창 닫기
					$("#viewModal").modal("hide");
					
					//목록에서 삭제  #viewArea로 하면 전체가 지워진것처럼 보임 > 삭제할 게시글만 선택
					$("#areaDel").remove();

				} else {
					$("#viewModal").modal("hide");
				}
			},
			error : function(XHR, status, error) {
				console.error(status + " : " + error);
			}
		});
		
		
	});

</script>




</html>

