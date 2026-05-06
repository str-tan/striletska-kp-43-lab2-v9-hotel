<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<c:if test="${empty rooms}">
    <c:redirect url="/hotel/"/>
</c:if>

<html>
<head>
    <meta charset="UTF-8">
    <title>Star Rain Hotel | Головна</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<nav class="navbar navbar-expand-lg navbar-dark bg-dark mb-4">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/hotel/">🌟 Star Rain</a>
        <div class="d-flex align-items-center">
            <c:choose>
                <c:when test="${not empty sessionScope.isAdmin && sessionScope.isAdmin}">
                    <a href="${pageContext.request.contextPath}/hotel/admin" class="btn btn-warning btn-sm me-2">Панель керування</a>
                    <form action="${pageContext.request.contextPath}/hotel/logout" method="POST" class="m-0">
                        <button type="submit" class="btn btn-outline-danger btn-sm">Вийти</button>
                    </form>
                </c:when>

                <c:otherwise>
                    <a href="${pageContext.request.contextPath}/hotel/view-bookings" class="btn btn-outline-light btn-sm me-3">Мої бронювання</a>
                    <form action="${pageContext.request.contextPath}/hotel/admin" method="POST" class="d-flex m-0">
                        <input type="password" name="password" class="form-control form-control-sm me-2" placeholder="Пароль адміна" style="width: 130px;">
                        <button class="btn btn-warning btn-sm" type="submit">Адмін</button>
                    </form>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</nav>

<div class="container py-5">
    <header class="pb-3 mb-4 border-bottom">
        <span class="fs-4">
            <c:out value="${sessionScope.isAdmin ? 'Star Rain Hotel — Керування номерами' : 'Star Rain Hotel — Оберіть свій номер'}"/>
        </span>
    </header>

    <div class="p-5 mb-4 bg-white rounded-3 shadow-sm">

        <div class="d-flex justify-content-between align-items-center mb-4">
            <h1 class="display-5 fw-bold m-0">Список номерів</h1>

            <div class="dropdown">
                <button class="btn btn-outline-secondary dropdown-toggle" type="button" data-bs-toggle="dropdown" aria-expanded="false">
                    Сортувати:
                    <c:choose>
                        <c:when test="${currentSort == 'priceDesc'}">Ціна: від більшої до меншої</c:when>
                        <c:when test="${currentSort == 'priceAsc'}">Ціна: від меншої до більшої</c:when>
                        <c:otherwise>За номером</c:otherwise>
                    </c:choose>
                </button>
                <ul class="dropdown-menu">
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/hotel/?sort=default">За номером кімнати</a></li>
                    <li><hr class="dropdown-divider"></li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/hotel/?sort=priceAsc">Ціна: від меншої до більшої</a></li>
                    <li><a class="dropdown-item" href="${pageContext.request.contextPath}/hotel/?sort=priceDesc">Ціна: від більшої до меншої</a></li>
                </ul>
            </div>
        </div>

        <table class="table table-hover mt-4">
            <thead class="table-dark">
                <tr>
                    <th>№ Кімнати</th>
                    <th>Ціна за ніч</th>
                    <th>Статус</th>
                    <th>Дія</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="room" items="${rooms}">
                    <tr>
                        <td><strong>${room.number}</strong></td>
                        <td>${room.pricePerNight} $</td>
                        <td>
                            <span class="badge ${room.available ? 'bg-success' : 'bg-danger'}">
                                ${room.available ? 'Вільний' : 'Зайнятий'}
                            </span>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${sessionScope.isAdmin}">
                                    <form action="${pageContext.request.contextPath}/hotel/admin/delete-room" method="POST" style="display:inline;">
                                        <input type="hidden" name="roomId" value="${room.id}">
                                        <button type="submit" class="btn btn-outline-danger btn-sm" onclick="return confirm('Видалити цей номер?')">Видалити</button>
                                    </form>
                                </c:when>
                                <c:otherwise>
                                    <c:if test="${room.available}">
                                        <form action="${pageContext.request.contextPath}/hotel/booking-form" method="GET">
                                            <input type="hidden" name="roomId" value="${room.id}">
                                            <button type="submit" class="btn btn-outline-primary btn-sm">Забронювати</button>
                                        </form>
                                    </c:if>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
    </div>

    <footer class="pt-3 mt-4 text-muted border-top">
        KPI FPM | Tetiana Striletska © 2026
    </footer>
</div>

<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.bundle.min.js"></script>

</body>
</html>