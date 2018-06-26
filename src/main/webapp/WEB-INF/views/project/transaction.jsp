<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
</head>
<body>
トランザクションの検証
<form:form modelAttribute="projectFormCommit" action="registWithCommit">
<dl>
  <dt>Project名</dt>
  <dd>
    <form:input path="projectName" />
    <form:errors path="projectName"/><br/>
  </dd>
  <dt>Member名</dt>
  <dd>
    <form:input path="memberName" />
    <form:errors path="memberName"/><br/>
  </dd>
</dl>

<button type="submit">登録（2テーブルともコミット）</button>
</form:form>
<form:form modelAttribute="projectFormRollback" action="registWithRollback">
<dl>
  <dt>Project名</dt>
    <dd>
      <form:input path="projectName" />
      <form:errors path="projectName"/><br/>
    </dd>
    <dt>Member名</dt>
    <dd>
      <form:input path="memberName" />
      <form:errors path="memberName"/><br/>
    </dd>
</dl>
<button type="submit">登録時エラー発生（projectはコミット、memberはロールバック）</button>
</form:form>

<a href="../../">Back to Top.</a>
</body>
</html>