
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<h1 class="text-center">Cập nhật Khách Hàng</h1>
<div class="col-8 offset-2">
    <c:if test="${not empty sessionScope.mess_error}">
        <div class="alert alert-danger" role="alert">
                ${sessionScope.mess_error}
        </div>
        <% session.removeAttribute("mess_error"); %>
    </c:if>
    <%--@elvariable id="khachHang" type=""--%>
    <form:form method="post" modelAttribute="khachHang" action="/admin/khach-hang/update/${khachHang.id}">
        <div class="form-group">
            <label for="ma">Mã</label>
            <form:input path="ma" value="${khachHang.ma}" id="ma" readonly="true" class="form-control"/>
            <form:errors path="ma" cssClass="text-danger"/>
        </div>
        <div class="form-group">
            <label for="ho">Họ</label>
            <form:input path="ho" value="${khachHang.ho}" id="ho" class="form-control"/>
            <form:errors path="ho" cssClass="text-danger"/>
        </div>
        <div class="form-group">
            <label for="tenDem">Tên Đệm</label>
            <form:input path="tenDem" value="${khachHang.tenDem}" id="tenDem" class="form-control"/>
            <form:errors path="tenDem" cssClass="text-danger"/>
        </div>
        <div class="form-group">
            <label for="ten">Tên</label>
            <form:input path="ten" value="${khachHang.ten}" id="ten" class="form-control"/>
            <form:errors path="ten" cssClass="text-danger"/>
        </div>
        <div class="form-group">
            <label for="ngaySinh">Ngày sinh</label>
            <form:input path="ngaySinh" type="date" value="${khachHang.ngaySinh}" id="ngaySinh" class="form-control"/>
            <form:errors path="ngaySinh" cssClass="text-danger"/>
        </div>
        <div class="form-group">
            <label for="diaChi">Địa chỉ</label>
            <form:input path="diaChi" value="${khachHang.diaChi}" id="diaChi" class="form-control" />
            <c:if test="${not empty error}">
                <p style="color: red">${error}</p>
            </c:if>
        </div>
        <div class="form-group">
            <label for="sdt">Số điện thoại</label>
            <form:input path="sdt" value="${khachHang.sdt}" id="sdt" class="form-control"/>
            <c:if test="${not empty error}">
                <p style="color: red">${error}</p>
            </c:if>
        </div>
        <div class="form-group">
            <label for="matKhau">Mật khẩu</label>
            <form:input path="matKhau" type="password" value="${khachHang.matKhau}" id="matKhau" class="form-control"/>
            <form:errors path="matKhau" cssClass="text-danger"/>
        </div>
        <div class="form-group">
            <label for="email">Email</label>
            <form:input path="email" value="${khachHang.email}" id="email" class="form-control" />
            <form:errors path="email" cssClass="text-danger"/>
        </div>
        <div class="form-group">
            <label for="thanhPho">Thành phố</label>
            <form:select path="thanhPho" class="form-select">
                <form:option value="Hà Nội" selected="${khachHang.thanhPho == 'Hà Nội'}">Hà Nội</form:option>
                <form:option value="NewYork" selected="${khachHang.thanhPho == 'NewYork'}">NewYork</form:option>
            </form:select>
        </div>
        <div class="form-group">
            <label for="quocGia">Quốc gia</label>
            <form:select path="quocGia" class="form-select">
                <form:option value="Việt Nam" selected="${khachHang.quocGia == 'Việt Nam'}">Việt Nam</form:option>
                <form:option value="Mỹ" selected="${khachHang.quocGia == 'Mỹ'}">Mỹ</form:option>
            </form:select>
        </div>
        <button type="submit" class="btn btn-primary mt-3">Submit</button>
    </form:form>
</div>
</body>
</html>
