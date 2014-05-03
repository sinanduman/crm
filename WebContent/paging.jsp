<%@ page language="java" contentType="text/html; charset=utf-8" pageEncoding="utf-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:if test="${noofpages > 1}">
<table id="pagination">
	<tr>
		<%--For displaying Previous link --%>
		<c:if test="${currentpage != 1}">
			<td class="link_diger"><a href="${pagename}?page=1${param.param1}${param.param2}${param.param3}${param.param4}${param.param5}${param.param6}">İlk</a></td>
			<td class="link_diger"><a href="${pagename}?page=${currentpage - 1}${param.param1}${param.param2}${param.param3}${param.param4}${param.param5}${param.param6}">&lt;</a></td>
		</c:if>
		<%--For displaying Previous link --%>
		<%--For displaying more pages in dots --%>
		<c:set var="before" value="false"/>
		<c:set var="after" value="false"/>
		<%--For displaying more pages in dots --%>
		<c:forEach begin="1" end="${noofpages}" var="i">
			<c:choose>
				<c:when test="${currentpage eq i}">
					<td class="link_aktif">${i}</td>
				</c:when>
				
				<c:when test="${ (noofpages gt 5) and (i lt (currentpage-3)) }">
					<c:if test="${before eq 'false'}">
						<c:set var="before" value="true"/>
						<td class="link_diger">. . .</td>
					</c:if>
				</c:when>
				
				<c:when test="${ (i ge (currentpage-3)) and (i le (currentpage + 3)) }">
					<td class="link_diger"><a href="${pagename}?page=${i}${param.param1}${param.param2}${param.param3}${param.param4}${param.param5}${param.param6}">${i}</a></td>
				</c:when>
				
				<c:when test="${ (noofpages gt 5) and (i gt (currentpage + 3)) }">
					<c:if test="${after eq 'false'}">
						<c:set var="after" value="true"/>
						<td class="link_diger">. . .</td>
					</c:if>
				</c:when>
			</c:choose>
		</c:forEach>
		<%--For displaying Next link --%>
		<c:if test="${currentpage lt noofpages}">
			<td class="link_diger"><a href="${pagename}?page=${currentpage + 1}${param.param1}${param.param2}${param.param3}${param.param4}${param.param5}${param.param6}">&gt;</a></td>
			<td class="link_diger"><a href="${pagename}?page=${noofpages}${param.param1}${param.param2}${param.param3}${param.param4}${param.param5}${param.param6}">Son</a></td>
		</c:if>
		<%--For displaying Next link --%>
	</tr>
</table>
</c:if>