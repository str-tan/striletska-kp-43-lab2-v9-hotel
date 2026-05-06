<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Мої бронювання | Star Rain Hotel</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">

<nav class="navbar navbar-dark bg-dark mb-4">
    <div class="container">
        <a class="navbar-brand" href="${pageContext.request.contextPath}/hotel/">🌟 Star Rain</a>
        <a href="${pageContext.request.contextPath}/hotel/" class="btn btn-outline-light btn-sm">На головну</a>
    </div>
</nav>

<div class="container">
    <div class="p-5 mb-4 bg-white rounded-3 shadow-sm">
        <h2 class="mb-4">Список усіх бронювань</h2>

        <table class="table table-striped">
            <thead class="table-dark">
                <tr>
                    <th>Гість</th>
                    <th>Кімната</th>
                    <th>Ночей</th>
                    <th>Сума</th>
                    <th>Дія</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="booking" items="${bookings}">
                    <tr>
                        <td>${booking.visitor.fullName}</td>
                        <td><strong>${booking.room.number}</strong></td>
                        <td>${booking.nights}</td>
                        <td>${booking.totalAmount} $</td>
                        <td>
                            <form action="${pageContext.request.contextPath}/hotel/delete-booking" method="POST" style="display:inline;">
                                <input type="hidden" name="bookingId" value="${booking.id}">
                                <button type="submit" class="btn btn-outline-danger btn-sm"
                                        onclick="return confirm('Ви впевнені, що хочете скасувати бронювання?')">
                                    Скасувати
                                </button>
                            </form>
                        </td>
                    </tr>
                </c:forEach>
                <c:if test="${empty bookings}">
                    <tr>
                        <td colspan="6" class="text-center text-muted">Бронювань поки немає</td>
                    </tr>
                </c:if>
            </tbody>
        </table>
    </div>
</div>

</body>
</html>