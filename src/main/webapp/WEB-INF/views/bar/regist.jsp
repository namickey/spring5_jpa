<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form modelAttribute="registBarForm" action="regist">
<dl>
  <dt>Bar名</dt>
  <dd>
    <form:input path="barName" />
    <form:errors path="*"/><br/>
  </dd>
</dl>
<button type="submit">登録</button>
</form:form>
