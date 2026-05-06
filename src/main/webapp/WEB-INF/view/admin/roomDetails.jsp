<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Деталі номера №${room.number} | Star Rain</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
<div class="container py-5">
    <div class="d-flex justify-content-between align-items-center mb-4">
        <a href="${pageContext.request.contextPath}/hotel/admin" class="btn btn-outline-secondary">← До списку номерів</a>
        <h2 class="m-0">Картка номера №${room.number}</h2>
    </div>

    <div class="row">
        <div class="col-md-4">
            <div class="card shadow-sm border-0 mb-4">
                <div class="card-header bg-dark text-white">Технічні дані</div>
                <div class="card-body">
                    <p><strong>Database ID:</strong> ${room.id}</p>
                    <p><strong>Ціна за ніч:</strong> ${room.pricePerNight} $</p>
                    <p><strong>Статус:</strong>
                        <span class="badge ${room.available ? 'bg-success' : 'bg-danger'}">
                            ${room.available ? 'Вільний' : 'Зайнятий'}
                        </span>
                    </p>
                </div>
            </div>
        </div>

        <div class="col-md-8">
            <div class="card shadow-sm border-0">
                <div class="card-header bg-primary text-white">Історія бронювань</div>
                <div class="table-responsive bg-white">
                    <table class="table table-hover m-0">
                        <thead>
                            <tr>
                                <th>ID</th>
                                <th>Клієнт</th>
                                <th>Email</th>
                                <th>Ночі</th>
                                <th>Сума</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="booking" items="${bookings}">
                                <tr>
                                    <td>${booking.id}</td>
                                    <td><strong>${booking.visitor.fullName}</strong></td>
                                    <td>${booking.visitor.email}</td>
                                    <td>${booking.nights}</td>
                                    <td><span class="fw-bold text-primary">${booking.totalAmount} $</span></td>
                                </tr>
                            </c:forEach>
                            <c:if test="${empty bookings}">
                                <tr>
                                    <td colspan="5" class="text-center py-4 text-muted">Для цієї кімнати ще немає жодного запису про бронювання.</td>
                                </tr>
                            </c:if>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>