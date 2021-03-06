<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<meta charset="UTF-8">
<script src="http://code.jquery.com/jquery-1.11.2.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.min.js"></script>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

<div style="display: inline-block;">
    <form id="uploadForm4" enctype="multipart/form-data" method="POST" target="iframe1">
        <input type="file" name="file" value="파일선택" />
        <button type="submit" onclick="upload4()">파일업로드</button>
    </form>
</div> <br>
<!-- 이미지를 4개 단위로 끊어서 보여줌 -->
<span id="convertImg">
    <c:set var="i" value="0" />
    <c:set var="j" value="4" />
    <c:forEach items="${imgList}" var="imgList">
        <c:if test="${i%j==0}">
            <br>
            <img src="<spring:url value='/img/${imgList.imgName}'/>" width="200" height="200" />
        </c:if>
        <c:if test="${i%j!=0}">
            <img src="<spring:url value='/img/${imgList.imgName}'/>" width="200"
                height="200" />
        </c:if>
        <c:set var="i" value="${i+1}" />
    </c:forEach> <!-- 페이징 처리 -->
    <div style="display: block; text-align: left;">
        <c:if test="${paging.startPage != 1}">
            <a href="/sort4?nowPage=${paging.startPage-1}&cntPerPage=${paging.cntPerPage}">&lt;</a>
        </c:if>
        <c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="p">
            <c:choose>
                <c:when test="${p == paging.nowPage}">
                    <b>[${p}]</b>
                </c:when>
                <c:when test="${p != paging.nowPage}">
                    <a href="/sort4?nowPage=${p}&cntPerPage=${paging.cntPerPage}">[${p}]</a>
                </c:when>
            </c:choose>
        </c:forEach>
        <c:if test="${paging.endPage != paging.lastPage}">
            <a href="/sort4?nowPage=${paging.endPage+1}&cntPerPage=${paging.cntPerPage}">&gt;</a>
        </c:if>
    </div>
</span>