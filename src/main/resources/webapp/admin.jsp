<!DOCTYPE html>
<html>
<head>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/admin.css"/>">
    <title>Server Administration</title>
</head>
<body>
<div class="container-fluid">
    <div class="row" style="padding-top: 20px">
        <div class="col-lg-12">
            <div class="card">
                <div class="card-header">Donations</div>
                <div class="card-body" id="donations-card">
                    <div class="card-block">
                        <table class="table table-sm table-striped table-bordered">
                            <thead>
                            <tr>
                                <th scope="col">ID</th>
                                <th scope="col">ACCOUNT</th>
                                <th scope="col">PACKAGE</th>
                                <th scope="col">STATUS</th>
                                <th scope="col">DATE</th>
                                <th scope="col">PAYMENT METHOD</th>
                                <th scope="col">REFERENCE</th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:if test="${not empty donations}">
                                <c:forEach items="${donations}" var="donation">
                                    <tr>
                                        <td scope="row"><c:out value="${donation.id}"/></td>
                                        <td><c:out value="${donation.login}"/></td>
                                        <td><c:out value="${donation.packageName}"/></td>
                                        <td class="${donation.status == 'DELIVERED' ? "status-approved" : "status-in-progress"}">
                                            <c:out value="${donation.status}"/></td>
                                        <td><c:out value="${donation.date}"/></td>
                                        <td><c:out value="${donation.paymentMethod}"/></td>
                                        <td><c:out value="${donation.ref}"/></td>
                                    </tr>
                                </c:forEach>
                            </c:if>
                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
