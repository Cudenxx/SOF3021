<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<h1 class="text-center">Thông tin Nhân Viên</h1>
<a href="/admin/nhan-vien/create" class="btn btn-success mt-3">Add</a>
<c:if test="${ f:length(list.content) == 0 }">
    <h4 class="text-center">Không có dữ liệu</h4>
</c:if>

<c:if test="${ f:length(list.content) != 0 }">
    <c:if test="${not empty sessionScope.message}">
        <div class="alert alert-success" role="alert">
                ${sessionScope.message}
        </div>
        <% session.removeAttribute("message"); %>
    </c:if>
    <table class="table table-bordered mt-5">
        <thead>
        <tr>
            <th>STT</th>
            <th>Mã</th>
            <th>Họ và Tên</th>
            <th>Giới Tính</th>
            <th>Ngày Sinh</th>
            <th>SDT</th>
            <th>Địa Chỉ</th>
            <th>Cửa Hàng</th>
            <th>Chức Vụ</th>
            <th>Email</th>
            <th>Trạng Thái</th>
            <th class="col-2 text-center">Action</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="nhanVien" items="${ list.content }" varStatus="status">
        <tr>
            <td>${status.index + 1}</td>
            <td>${ nhanVien.ma }</td>
            <td>${ nhanVien.ho } ${nhanVien.tenDem} ${nhanVien.ten}</td>
            <td>${ nhanVien.gioiTinh }</td>
            <td>${ nhanVien.ngaySinh }</td>
            <td>${ nhanVien.sdt }</td>
            <td>${ nhanVien.diaChi }</td>
            <td>${ nhanVien.cuaHang.ten }</td>
            <td>${ nhanVien.chucVu.ten }</td>
            <td>${ nhanVien.email }</td>
            <td>${ nhanVien.trangThai == 1 ? "Đang Làm" : "Đã Nghỉ" }</td>
            <td>
                <a href="/admin/nhan-vien/edit/${nhanVien.id}" class="btn btn-primary">Update</a>
                <a href="/admin/nhan-vien/delete/${nhanVien.id}" class="btn btn-danger"
                   onclick="return confirm('Bạn có chắc chắn muốn xoá?  ')">
                    Delete
                </a>
            </td>
            </c:forEach>
    </table>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item">
                <a class="page-link" href="/admin/nhan-vien/index?page=${ list.number - 1 }" tabindex="-1"
                   aria-disabled="true">Previous</a>
            </li>
            <c:forEach begin="0" end="${list.totalPages - 1}" varStatus="loop">
                <li class="page-item">
                    <a class="page-link" href="/admin/nhan-vien/index?page=${loop.begin + loop.index}">${loop.begin + loop.index + 1}</a>
                </li>
            </c:forEach>
            <li class="page-item">
                <a class="page-link" href="/admin/nhan-vien/index?page=${ list.number + 1 }">Next</a>
            </li>
        </ul>
    </nav>
</c:if>

</body>
</html>
