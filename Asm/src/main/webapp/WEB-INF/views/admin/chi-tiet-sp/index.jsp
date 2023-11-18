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
<h1 class="text-center">Thông tin Chi Tiết Sản Phẩm</h1>
<a href="/admin/ctsp/create" class="btn btn-success mt-3">Add</a>
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
            <th>Năm bảo hành</th>
            <th>Số lượng tồn</th>
            <th>Giá nhập</th>
            <th>Giá bán</th>
            <th>Sản phẩm</th>
            <th>NSX</th>
            <th>Màu sắc</th>
            <th>Dòng Sản phẩm</th>
            <th>Mô tả</th>
            <th>Ảnh</th>
            <th class="col-2 text-center">Action</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="ctsp" items="${ list.content }" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${ ctsp.namBaoHanh }</td>
                <td>${ ctsp.soLuongTon }</td>
                <td>${ ctsp.giaNhap }</td>
                <td>${ ctsp.giaBan }</td>
                <td>${ ctsp.sanPham.ten }</td>
                <td>${ ctsp.nsx.ten }</td>
                <td>${ ctsp.mauSac.ten }</td>
                <td>${ ctsp.dongSp.ten }</td>
                <td>${ ctsp.moTa }</td>
                <td>
                    <img src="${ ctsp.sanPham.anh }" alt="ảnh sản phẩm" style="width: 100px; height: 100px;">
                </td>

                <td class="text-center">
                    <a href="/admin/ctsp/edit/${ ctsp.id }"
                       class="btn btn-primary">Update</a>
                    <a href="/admin/ctsp/delete/${ ctsp.id }"
                       class="btn btn-danger" onclick="return confirm('Bạn có chắc chắn muốn xoá?  ')">
                        Delete
                    </a>
                </td>
            </tr>
        </c:forEach>
        </tbody>
    </table>
    <nav aria-label="Page navigation example">
        <ul class="pagination justify-content-center">
            <li class="page-item">
                <a class="page-link" href="/admin/ctsp/index?page=${ list.number - 1 }" tabindex="-1"
                   aria-disabled="true">Previous</a>
            </li>
            <c:forEach begin="0" end="${list.totalPages - 1}" varStatus="loop">
                <li class="page-item">
                    <a class="page-link" href="/admin/ctsp/index?page=${loop.begin + loop.index}">${loop.begin + loop.index + 1}</a>
                </li>
            </c:forEach>
            <li class="page-item">
                <a class="page-link" href="/admin/ctsp/index?page=${ list.number + 1 }">Next</a>
            </li>
        </ul>
    </nav>
</c:if>

</body>
</html>
