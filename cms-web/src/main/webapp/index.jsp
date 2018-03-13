<%@page contentType="text/html;charset=UTF-8"%>
<%@include file="/taglibs.jsp"%>
<%String url = "/admin/article/articles.do";%>
<%response.sendRedirect(request.getContextPath() + url);%>
