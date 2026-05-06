<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Адміністрування | Star Rain</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<div class="bg-dark text-white py-3 mb-4">
    <div class="container d-flex justify-content-between align-items-center">
        <h2 class="m-0">Панель адміністратора</h2>
        <form action="${pageContext.request.contextPath}/hotel/logout" method="POST" class="m-0">
            <button class="btn btn-outline-danger btn-sm">Вийти з системи</button>
        </form>
    </div>
</div>

<div class="container">
    <c:if test="${not empty sessionScope.adminMessage}">
        <div class="alert alert-success alert-dismissible fade show" role="alert">
            <strong>Успіх!</strong> ${sessionScope.adminMessage}
            <button type="button" class="btn-close" data-bs-dismiss="alert" aria-label="Close"></button>
        </div>
        <% request.getSession().removeAttribute("adminMessage"); %>
    </c:if>

    <div class="row">
        <div class="col-md-4 mb-4">
            <div class="card p-4 shadow-sm border-0">
                <h4 class="mb-3">Додати номер</h4>
                <form action="${pageContext.request.contextPath}/hotel/admin/add-room" method="POST">
                    <div class="mb-3">
                        <label class="form-label text-muted small">Номер кімнати</label>
                        <input type="number" name="number" class="form-control" placeholder="№ кімнати" required>
                    </div>
                    <div class="mb-3">
                        <label class="form-label text-muted small">Ціна за ніч ($)</label>
                        <input type="number" step="0.01" name="price" class="form-control" placeholder="0.00" required>
                    </div>
                    <button class="btn btn-primary w-100">Зберегти номер</button>
                </form>
            </div>
        </div>

        <div class="col-md-8">
            <div class="card p-4 shadow-sm border-0">
                <h4 class="mb-3">Список номерів</h4>
                <div class="table-responsive">
                    <table class="table align-middle">
                        <thead class="table-light">
                            <tr>
                                <th style="width: 10%">№</th>
                                <th style="width: 35%">Ціна</th>
                                <th style="width: 20%">Статус</th>
                                <th style="width: 35%" class="text-center">Дії</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="room" items="${rooms}">
                                <tr>
                                    <td><strong>${room.number}</strong></td>
                                    <td>
                                        <form action="${pageContext.request.contextPath}/hotel/admin/update-price" method="POST" class="d-flex m-0">
                                            <input type="hidden" name="roomId" value="${room.id}">
                                            <input type="number" step="0.01" name="newPrice" value="${room.pricePerNight}"
                                                   class="form-control form-control-sm me-1" style="width: 90px;"
                                                   ${!room.available ? 'disabled' : ''}>
                                            <button type="submit" class="btn btn-sm btn-warning" ${!room.available ? 'disabled' : ''}>
                                                Оновити
                                            </button>
                                        </form>
                                    </td>
                                    <td>
                                        <span class="badge ${room.available ? 'bg-success' : 'bg-secondary'}">
                                            ${room.available ? 'Вільний' : 'Зайнятий'}
                                        </span>
                                    </td>
                                    <td>
                                        <div class="d-flex justify-content-center gap-2">
                                            <a href="${pageContext.request.contextPath}/hotel/admin/room-details?roomId=${room.id}"
                                               class="btn btn-sm btn-info text-white">Деталі</a>

                                            <form action="${pageContext.request.contextPath}/hotel/admin/delete-room" method="POST" class="m-0">
                                                <input type="hidden" name="roomId" value="${room.id}">
                                                <button class="btn btn-outline-danger btn-sm"
                                                        ${!room.available ? 'disabled' : ''}
                                                        onclick="return confirm('Видалити номер ${room.number}?')">
                                                    Видалити
                                                </button>
                                            </form>
                                        </div>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>
</body>
</html>