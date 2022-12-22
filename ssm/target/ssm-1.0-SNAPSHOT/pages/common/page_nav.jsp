<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2020/2/8
  Time: 14:44
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div id="page_nav">
    <c:if test="${requestScope.page.hasPreviousPage}">
        <a href="${ requestScope.url }&pageNo=1">首页</a>
        <a href="${ requestScope.url }&pageNo=${requestScope.page.prePage}">上一页</a>
    </c:if>
    <c:forEach items="${requestScope.page.navigatepageNums}" var="num">
        <c:if test="${num == requestScope.page.pageNum}">
            【${num}】
        </c:if>
        <c:if test="${num != requestScope.page.pageNum}">
            <a href="${ requestScope.url }&pageNo=${num}">${num}</a>
        </c:if>
    </c:forEach>
    <c:if test="${requestScope.page.hasNextPage}">
        <a href="${ requestScope.url }&pageNo=${requestScope.page.nextPage}">下一页</a>
        <a href="${ requestScope.url }&pageNo=1">末页</a>
    </c:if>

<%--    共${ requestScope.page.}页，${ requestScope.page.}条记录--%>
    到第<input value="${param.pageNo}" name="pn" id="pn_input"/>页
    <input id="searchPageBtn" type="button" value="确定">
    <script type="text/javascript">

        $(function () {
            // 跳到指定的页码
            $("#searchPageBtn").click(function () {

                var pageNo = $("#pn_input").val();

                <%--var pageTotal = ${requestScope.page.pageTotal};--%>
                <%--alert(pageTotal);--%>

                // javaScript语言中提供了一个location地址栏对象
                // 它有一个属性叫href.它可以获取浏览器地址栏中的地址
                // href属性可读，可写
                location.href = "${pageScope.basePath}${ requestScope.url }&pageNo=" + pageNo;

            });
        });

    </script>
</div>



