
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
</head>
<body>
<h1 class="text-center">Thêm Chi Tiết Sản Phẩm</h1>
<div class="col-8 offset-2">
    <c:if test="${not empty sessionScope.mess_error}">
        <div class="alert alert-danger" role="alert">
                ${sessionScope.mess_error}
        </div>
        <% session.removeAttribute("mess_error"); %>
    </c:if>
    <%--@elvariable id="ctsp" type=""--%>
    <form:form method="POST" action="/admin/ctsp/add" modelAttribute="ctsp">
        <div class="row">
            <div class=" col-md-4 ">
                <label class="form-label">Năm bảo hành</label>
                <form:input path="namBaoHanh" type="text" class="form-control"/>
                <form:errors path="namBaoHanh" cssClass="text-danger"/>
            </div>

            <div class=" col-md-4 mb-3">
                <label  class="form-label">Số lượng tồn</label>
                <form:input path="soLuongTon" type="number" class="form-control"/>
                <form:errors path="soLuongTon" cssClass="text-danger"/>
            </div>
            <div class="col-md-4 mb-3">
                <label  class="form-label">Dòng Sản phẩm</label>
                <form:select path="dongSp" class="form-control">
                    <c:forEach var="dongSp" items="${dongSPList}">
                        <option value="${dongSp.id}">${dongSp.ten}</option>
                    </c:forEach>
                </form:select>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 mb-3">
                <label class="form-label">Giá nhập</label>
                <form:input path="giaNhap" type="number" class="form-control"/>
                <form:errors path="giaNhap" cssClass="text-danger"/>
            </div>
            <div class="col-md-4 mb-3">
                <label for="nsx" class="form-label">Nhà sản xuất</label>
                <form:select path="nsx" class="form-control" >
                    <c:forEach var="nsx" items="${nsxList}">
                        <option value="${nsx.id}">${nsx.ten}</option>
                    </c:forEach>
                </form:select>
            </div>
            <div class="col-md-4 mb-3">
                <label class="form-label">Sản phẩm</label>
                <form:select path="sanPham" class="form-control" >
                    <c:forEach var="sp" items="${sanPhamList}">
                        <option value="${sp.id}">${sp.ten}</option>
                    </c:forEach>
                </form:select>
            </div>
        </div>
        <div class="row">
            <div class="col-md-4 mb-3">
                <label  class="form-label">Giá bán</label>
                <form:input path="giaBan" type="number" class="form-control" value="${ctsp.giaBan}"/>
                <form:errors path="giaBan" cssClass="text-danger"/>
            </div>
            <div class="col-md-4 mb-3">
                <label  class="form-label">Màu sắc</label>
                <form:select path="mauSac" class="form-control" >
                    <c:forEach var="mauSac" items="${mauSacList}">
                        <option value="${mauSac.id}">${mauSac.ten}</option>
                    </c:forEach>
                </form:select>
            </div>
            <div class=" col-md-4 mb-3">
                <label  class="form-label">Mô tả</label>
                <form:input path="moTa" type="text" class="form-control" value="${ctsp.moTa}"/>
                <form:errors path="moTa" cssClass="text-danger"/>
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
