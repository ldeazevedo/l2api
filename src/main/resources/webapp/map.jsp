<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <link rel="stylesheet" type="text/css" href="<c:url value="css/map.css"/>">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="<c:url value="js/players.js"/>"></script>
    <title>Online Players</title>
</head>
<body>
<div style="overflow: auto; position: relative">
    <img src="map/ct0_all.jpg" alt="none" id="map-img" style="margin-left: 35px"/>
    <c:if test="${not empty onlinePlayers}">
        <c:forEach items="${onlinePlayers}" var="player">
            <div style="position: absolute; top: ${player.mapY}px; left: ${player.mapX}px">
                <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAgAAAAICAYAAADED76LAAAAE0lEQVR42mP8z8AARLgB48hQAAAdEw/5itjcFgAAAABJRU5ErkJggg=="
                     title="${player.name}" alt="None"/>
            </div>
        </c:forEach>
    </c:if>
</div>
</body>