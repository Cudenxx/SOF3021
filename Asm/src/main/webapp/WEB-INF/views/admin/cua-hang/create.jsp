<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<h1 class="text-center">Thêm Cửa Hàng</h1>
<div class="col-8 offset-2">
    <c:if test="${not empty sessionScope.mess_error}">
        <div class="alert alert-danger" role="alert">
                ${sessionScope.mess_error}
        </div>
        <% session.removeAttribute("mess_error"); %>
    </c:if>
    <%--@elvariable id="cuaHang" type=""--%>
    <form:form method="POST" action="/admin/cua-hang/add" modelAttribute="cuaHang">
        <div class="mt-3">
            <label>Mã</label>
            <form:input type="text" path="ma" class="form-control" readonly="true"/>
            <form:errors path="ma" cssClass="text-danger"/>
        </div>
        <div class="mt-3">
            <label>Tên</label>
            <form:input type="text" path="ten" class="form-control" required="true"/>
            <form:errors path="ten" cssClass="text-danger"/>
        </div>
        <div class="mt-3">
            <label>Địa chỉ</label>
            <form:input type="text" path="diaChi" class="form-control" required="true"/>
            <form:errors path="diaChi" cssClass="text-danger"/>
        </div>
        <div class="mt-3">
            <label>Quốc gia</label>
            <form:select path="quocGia" class="form-select">
                <form:option value="Việt Nam">Việt Nam</form:option>
                <form:option value="Mỹ">Mỹ</form:option>
            </form:select>
        </div>
        <div class="mt-3">
            <label>Thành phố</label>
            <form:select path="thanhPho" class="form-select">
                <form:option value="Hà Nội">Hà Nội</form:option>
                <form:option value="NewYork">NewYork</form:option>
            </form:select>
        </div>
        <div class="mt-3">
            <button class="btn btn-primary">Submit</button>
        </div>
    </form:form>
</div>
</body>
</html>
