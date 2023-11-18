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
<h1 class="text-center">Thông tin Khách Hàng</h1>
<a href="/admin/khach-hang/create" class="btn btn-success mt-3">Add</a>
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
    <table class="table mt-3">
        <thead>
        <th>Mã</th>
        <th>Họ tên</th>
        <th>Ngày Sinh</th>
        <th>Địa chỉ</th>
        <th>SDT </th>
        <th>Email</th>
        <th>Thành phố</th>
        <th>Quốc gia</th>
        <th>Action</th>
        </thead>
        <tbody>
        <c:forEach varStatus="index" items="${list.content}" var="kh">
            <tr>
                <td>${kh.ma}</td>
                <td>${kh.ho} ${kh.tenDem} ${kh.ten}</td>
                <td>${kh.ngaySinh}</td>
                <td>${kh.diaChi}</td>
                <td>${kh.sdt}</td>
                <td>${kh.email}</td>
                <td>${kh.thanhPho}</td>
                <td>${kh.quocGia}</td>
                <td>
                    <a href="/admin/khach-hang/edit/${kh.id}" class="btn btn-primary">Update</a>
                    <a href="/admin/khach-hang/delete/${kh.id}" class="btn btn-danger"
                       onclick="return confirm('Bạn có chắc chắn muốn xoá không?')"
                    >Xoá</a>
                </td>
            </tr>

        </c:forEach>
        </tbody>
    </table>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item">
                <a class="page-link" href="/admin/khach-hang/index?page=${ list.number - 1 }" tabindex="-1"
                   aria-disabled="true">Previous</a>
            </li>
            <c:forEach begin="0" end="${list.totalPages - 1}" varStatus="loop">
                <li class="page-item">
                    <a class="page-link" href="/admin/khach-hang/index?page=${loop.begin + loop.index}">${loop.begin + loop.index + 1}</a>
                </li>
            </c:forEach>
            <li class="page-item">
                <a class="page-link" href="/admin/khach-hang/index?page=${ list.number + 1 }">Next</a>
            </li>
        </ul>
    </nav>
</c:if>

</body>
</html>
