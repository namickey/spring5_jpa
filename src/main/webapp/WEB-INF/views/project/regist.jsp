<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<form:form modelAttribute="projectForm" action="regist">
<dl>
  <dt>Project名</dt>
  <dd>
    <form:input path="name" />
    <form:errors path="*"/><br/>
  </dd>
</dl>
<button type="submit">登録</button>
</form:form>
<form:form modelAttribute="projectForm" action="registerror">
<dl>
  <dt>Project名</dt>
  <dd>
    <form:input path="name" />
    <form:errors path="*"/><br/>
  </dd>
</dl>
<button type="submit">登録エラー</button>
</form:form>
