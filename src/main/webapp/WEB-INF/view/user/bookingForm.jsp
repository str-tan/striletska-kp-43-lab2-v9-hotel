<%@ page contentType="text/html;charset=UTF-8" pageEncoding="UTF-8" language="java" %>
<html>
<head>
    <title>Реєстрація бронювання | Star Rain</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="container py-5">
    <div class="row justify-content-center">
        <div class="col-md-6 border p-4 bg-white shadow-sm rounded">
            <h2 class="mb-4">Бронювання номера №${room.number}</h2>
            <p class="text-muted">Ціна за ніч: <strong>${room.pricePerNight} $</strong></p>

            <form action="${pageContext.request.contextPath}/hotel/add-booking" method="POST">
                <input type="hidden" name="roomId" value="${room.id}">
                <input type="hidden" name="price" value="${room.pricePerNight}">

                <div class="mb-3">
                    <label class="form-label">Повне ім'я</label>
                    <input type="text" name="fullName" class="form-control" required placeholder="Прізвище Ім'я">
                </div>

                <div class="mb-3">
                    <label class="form-label">Email</label>
                    <input type="email" name="email" class="form-control" required placeholder="example@kpi.ua">
                </div>

                <div class="mb-3">
                    <label class="form-label">Кількість ночей</label>
                    <input type="number" name="nights" class="form-control" min="1" value="1" required>
                </div>

                <button type="submit" class="btn btn-primary w-100">Підтвердити бронювання</button>
                <a href="${pageContext.request.contextPath}/hotel/" class="btn btn-link w-100 mt-2">Скасувати</a>
            </form>
        </div>
    </div>
</body>
</html>