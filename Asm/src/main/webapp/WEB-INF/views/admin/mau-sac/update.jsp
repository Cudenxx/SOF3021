
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<h1 class="text-center">Cập nhật Màu Sắc</h1>
<div class="col-8 offset-2">
    <c:if test="${not empty sessionScope.mess_error}">
        <div class="alert alert-danger" role="alert">
                ${sessionScope.mess_error}
        </div>
        <% session.removeAttribute("mess_error"); %>
    </c:if>
    <%--@elvariable id="mauSac" type=""--%>
    <form:form method="POST" action="/admin/mau-sac/update/${mauSac.id}" modelAttribute="mauSac">
        <div class="row mt-3">
            <div class="col-6">
                <label>Mã</label>
                <form:input path="ma" class="form-control" value="${mauSac.ma}" readonly="true"/>
                <form:errors path="ma" class="text-danger"/>
            </div>
            <div class="col-6">
                <label>Tên</label>
                <form:input path="ten" class="form-control" value="${mauSac.ten}"/>
                <form:errors path="ten" class="text-danger"/>
            </div>
        </div>
        <div class="row mt-3">
            <div class="col-6">
                <button class="btn btn-primary">Submit</button>
            </div>
            <div class="col-6"></div>
        </div>
    </form:form>
</div>
</body>
</html>
