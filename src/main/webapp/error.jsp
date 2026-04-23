<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>Помилка - Hotel System</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet">
</head>
<body class="bg-light">
    <div class="container vh-100 d-flex justify-content-center align-items-center">
        <div class="card shadow-sm border-danger" style="max-width: 500px; width: 100%;">
            <div class="card-header bg-danger text-white text-center">
                <h4 class="mb-0">Ой! Сталася помилка</h4>
            </div>
            <div class="card-body text-center py-4">
                <p class="card-text text-muted mb-4">
                    На жаль, ми не змогли опрацювати ваш запит.
                </p>

                <div class="alert alert-warning">
                    <strong>Деталі:</strong> ${errorMessage != null ? errorMessage : "Невідома помилка"}
                </div>

                <a href="${pageContext.request.contextPath}/index.jsp" class="btn btn-outline-primary mt-3">
                    Повернутися на головну
                </a>
            </div>
        </div>
    </div>
</body>
</html>