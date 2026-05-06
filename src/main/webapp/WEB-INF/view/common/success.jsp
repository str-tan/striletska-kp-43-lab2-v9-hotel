<%@ page contentType="text/html;charset=UTF-8" language="java" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="UTF-8">
    <title>Успіх | Star Rain Hotel</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light d-flex align-items-center" style="height: 100vh;">
    <div class="container text-center">
        <div class="card shadow p-5 mx-auto" style="max-width: 500px; border-top: 5px solid #198754;">
            <div class="mb-4">
                <span style="font-size: 4rem;">✅</span>
            </div>
            <h2 class="mb-3">Дія успішна!</h2>
            <p class="text-muted mb-4">${message}</p>
            <div class="d-grid gap-2">
                <c:choose>
                    <c:when test="${sessionScope.isAdmin}">
                        <a href="${pageContext.request.contextPath}/hotel/admin?password=kpi2026" class="btn btn-success">
                            Повернутися в адмін-панель
                        </a>
                    </c:when>
                    <c:otherwise>
                        <a href="${pageContext.request.contextPath}/hotel/" class="btn btn-primary">
                            На головну
                        </a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</body>
</html>