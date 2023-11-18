<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="f" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="/../WEB-INF/css/bootstrap.min.css">
</head>
<body>
<h1 class="text-center">Thông tin Sản Phẩm</h1>
<a href="/admin/san-pham/create" class="btn btn-success mt-3">Add</a>
<br>
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

    <c:if test="${not empty sessionScope.mess_error}">
        <div class="alert alert-danger" role="alert">
                ${sessionScope.mess_error}
        </div>
        <% session.removeAttribute("mess_error"); %>
    </c:if>
    <span class="text-danger">${errorMessage}</span>
    <table class="table table-bordered mt-5">
        <thead>
        <tr>
            <th>STT</th>
            <th>Mã</th>
            <th>Tên</th>
            <th>Ảnh</th>
            <th class="col-2 text-center">Action</th>
        </tr>
        </thead>
        <tbody>

        <c:forEach var="sp" items="${ list.content }" varStatus="status">
            <tr>
                <td>${status.index + 1}</td>
                <td>${sp.ma}</td>
                <td>${sp.ten}</td>
                <td>
                    <img src="${sp.anh}" style="width: 150px; height: 150px;">
                </td>
                <td class="text-center">
                    <a href="/admin/san-pham/edit/${ sp.id }"
                       class="btn btn-primary">Update</a>
                    <a href="/admin/san-pham/delete/${ sp.id }"
                       class="btn btn-danger"
                       onclick="return confirm('Bạn có chắc chắn muốn xoá?  ')">
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
                <a class="page-link" href="/admin/san-pham/index?page=${ list.number - 1 }" tabindex="-1"
                   aria-disabled="true">Previous</a>
            </li>
            <c:forEach begin="0" end="${list.totalPages - 1}" varStatus="loop">
                <li class="page-item">
                    <a class="page-link" href="/admin/san-pham/index?page=${loop.begin + loop.index}">${loop.begin + loop.index + 1}</a>
                </li>
            </c:forEach>
            <li class="page-item">
                <a class="page-link" href="/admin/san-pham/index?page=${ list.number + 1 }">Next</a>
            </li>
        </ul>
    </nav>
</c:if>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script src="https://cdnjs.cloudflare.com/ajax/libs/toastr.js/latest/js/toastr.min.js"></script>
</body>
</html>
