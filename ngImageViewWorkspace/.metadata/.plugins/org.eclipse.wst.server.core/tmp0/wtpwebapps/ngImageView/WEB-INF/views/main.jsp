<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>

<head>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/angularjs/1.6.9/angular.min.js"></script>
<title>ImageUpload</title>
<script>
	//앵귤러 모듈 imageView
	var app = angular.module("imageView", []);
	//앵귤러 버튼 이벤트
	app.controller("butCon", function ($scope) {
		$scope.sort3 = function () {
			$.ajax({
				url : "/sort3",
				type : "POST",
				processData: false,
				contentType: false,
				success : function(result){
					$('#convertView').empty();
					$('#convertView').append(result);
				},
				error:function(request,status,error){
			        alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
			       }
			});
		}

		$scope.sort4 = function () {
			$.ajax({
				url : "/sort4",
				type : "POST",
				processData: false,
				contentType: false,
				success : function(result){
					$('#convertView').empty();
					$('#convertView').append(result);
				},
				error:function(request,status,error){
			        alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
			       }
			});
		}

		$scope.sort5 = function () {
			$.ajax({
				url : "/sort5",
				type : "POST",
				processData: false,
				contentType: false,
				success : function(result){
					$('#convertView').empty();
					$('#convertView').append(result);
				},
				error:function(request,status,error){
			        alert("code = "+ request.status + " message = " + request.responseText + " error = " + error); // 실패 시 처리
			       }
			});
		}
	})
</script>

<!-- sort3 파일 업로드 아작스 -->
<script>
	function upload3() {
		var form = new FormData(document.getElementById("uploadForm3"));
		$.ajax({
			url: "/sort3Upload",
			type: "POST",
			data: form,
			processData: false,
			contentType: false,
			success: function (result) {
				$('#convertImg').empty();
				$('#convertImg').append(result);
			}
		})
	}
</script>
<!-- sort4 파일 업로드 아작스 -->
<script>
	function upload4() {
		var form = new FormData(document.getElementById("uploadForm4"));
		$.ajax({
			url: "/sort4Upload",
			type: "POST",
			data: form,
			processData: false,
			contentType: false,
			success: function (result) {
				$('#convertImg').empty();
				$('#convertImg').append(result);
			}
		})
	}
</script> 
<!-- sort5 파일 업로드 아작스 -->
<script>
	function upload5() {
		var form = new FormData(document.getElementById("uploadForm5"));
		$.ajax({
			url: "/sort5Upload",
			type: "POST",
			data: form,
			processData: false,
			contentType: false,
			success: function (result) {
				$('#convertImg').empty();
				$('#convertImg').append(result);
			}
		})
	}
</script> 
</head>

<body>
	<div ng-app="imageView" ng-controller="butCon" name="form">
		<div style="display: inline-block">
			<input type="button" ng-click="sort3()" value="sort3"></input> <input type="button"
				ng-click="sort4()" value="sort4"></input> <input type="button" ng-click="sort5()"
				value="sort5"></input>
		</div>
		<!-- 전체 화면 전환시 교체되는 tag -->
		<span id="convertView">
			<div style="display: inline-block;">
				<form id="uploadForm3" enctype="multipart/form-data" method="POST" target="iframe1">
					<input type="file" name="file" value="파일선택" />
					<button type="submit" onclick="upload3()">파일업로드</button>
				</form>
			</div> <br>
			
			<!-- 아작스 통신 후 교체되는 태그 -->
			<!-- 이미지를 3개 단위로 끊어서 보여줌. 기본 페이지 --> 
			<span id="convertImg">
				<c:set var="i" value="0" />
				<c:set var="j" value="3" />
				<c:forEach items="${imgList}" var="imgList">
					<c:if test="${i%j==0}">
						<br>
						<img src="<spring:url value='/img/${imgList.imgName}'/>" width="300"
							height="300" />
					</c:if>
					<c:if test="${i%j!=0}">
						<img src="<spring:url value='/img/${imgList.imgName}'/>" width="300"
							height="300" />
					</c:if>
					<c:set var="i" value="${i+1}" />
				</c:forEach> <!-- 페이징 처리 -->
				<div style="display: block; text-align: left;">
					<c:if test="${paging.startPage != 1}">
						<a href="/sort3?nowPage=${paging.startPage-1}&cntPerPage=${paging.cntPerPage}">&lt;</a>
					</c:if>
					<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="p">
						<c:choose>
							<c:when test="${p == paging.nowPage}">
								<b>[${p}]</b>
							</c:when>
							<c:when test="${p != paging.nowPage}">
								<a href="/sort3?nowPage=${p}&cntPerPage=${paging.cntPerPage}">[${p}]</a>
							</c:when>
						</c:choose>
					</c:forEach>
					<c:if test="${paging.endPage != paging.lastPage}">
						<a href="/sort3?nowPage=${paging.endPage+1}&cntPerPage=${paging.cntPerPage}">&gt;</a>
					</c:if>
				</div>
			</span>
		</span>
	</div>
</body>
<iframe name="iframe1" style="display: none;"></iframe>
</html>