
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<h1 class="text-center">Thêm Nhân Viên</h1>
<div class="col-8 offset-2">
    <c:if test="${not empty sessionScope.mess_error}">
        <div class="alert alert-danger" role="alert">
                ${sessionScope.mess_error}
        </div>
        <% session.removeAttribute("mess_error"); %>
    </c:if>
    <%--@elvariable id="nhanVien" type=""--%>
    <form:form method="POST" action="/admin/nhan-vien/add" modelAttribute="nhanVien">
        <div class="row">
            <div class="mt-3 col-7">
                <div class="form-group">
                    <label>Mã</label>
                    <form:input path="ma" class="form-control"/>
                    <form:errors path="ma" cssClass="text-danger"/>
                    <c:if test="${not empty error}">
                        <p style="color: red">${error}</p>
                    </c:if>
                </div>
                <div class="form-group">
                    <label>Họ</label>
                    <form:input path="ho" class="form-control"/>
                    <form:errors path="ho" cssClass="text-danger"/>
                </div>
                <div class="form-group">
                    <label>Tên đệm</label>
                    <form:input path="tenDem" class="form-control"/>
                    <form:errors path="tenDem" cssClass="text-danger"/>
                </div>
                <div class="form-group">
                    <label>Tên</label>
                    <form:input path="ten" class="form-control"/>
                    <form:errors path="ten" cssClass="text-danger"/>
                </div>
                <div class="form-group">
                    <label>Ngày Sinh</label>
                    <form:input path="ngaySinh" type="date" class="form-control" pattern = "yyyy-MM-dd"/>
                    <form:errors path="ngaySinh" cssClass="text-danger"/>
                </div>
                <div class="form-group">
                    <label>SDT</label>
                    <form:input path="sdt" class="form-control"/>
                    <form:errors path="sdt" cssClass="text-danger"/>
                </div>
                <div class="form-group">
                    <label>Địa chỉ</label>
                    <form:input path="diaChi" class="form-control"/>
                    <form:errors path="diaChi" cssClass="text-danger"/>
                </div>
            </div>
            <div class="mt-3 col-5">
                <div class="form-group">
                    <label>Mật khẩu</label>
                    <form:input path="matKhau" type="password" class="form-control"/>
                    <form:errors path="matKhau" cssClass="text-danger"/>
                </div>
                <div class="form-group">
                    <label>Email</label>
                    <form:input path="email" type="email" class="form-control"/>
                    <form:errors path="email" cssClass="text-danger"/>
                </div>
                <div class="form-group">
                    <label>Giới tính</label>
                    <div class="form-check">
                        <input type="radio" name="gioiTinh" value="Nam" class="form-check-input" checked/>Nam
                    </div>
                    <div class="form-check">
                        <input type="radio" name="gioiTinh" value="Nữ" class="form-check-input"/>Nữ
                    </div>
                    <form:errors path="gioiTinh" cssClass="text-danger"/>
                </div>
                <div class="form-group">
                    <label>Trạng thái</label>
                    <div class="form-check">
                        <input type="radio" name="trangThai" value="1" class="form-check-input" checked/>Đang làm việc
                    </div>
                    <div class="form-check">
                        <input type="radio" name="trangThai" value="0" class="form-check-input"/>Đã nghỉ việc
                    </div>
                    <form:errors path="trangThai" cssClass="text-danger"/>
                </div>
                <div class="form-group">
                    <label>Chức vụ</label>
                    <form:select path="chucVu" class="form-control">
                        <c:forEach var="chucVu" items="${chucVuList}">
                            <option value="${chucVu.id}">${chucVu.ten}</option>
                        </c:forEach>
                    </form:select>
                </div>
                <div class="form-group">
                    <label>Cửa Hàng</label>
                    <form:select path="cuaHang" class="form-control">
                        <c:forEach var="cuaHang" items="${cuaHangList}">
                            <option value="${cuaHang.id}">${cuaHang.ten}</option>
                        </c:forEach>
                    </form:select>
                </div>
            </div>
        </div>
        <div class="mt-3">
            <button class="btn btn-primary" type="submit">Submit</button>
        </div>
    </form:form>
</div>
</body>
</html>
