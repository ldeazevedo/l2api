<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <link rel="stylesheet" type="text/css" href="<c:url value="css/map.css"/>">
    <link rel="stylesheet" type="text/css" href="<c:url value="css/players.css"/>">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="<c:url value="js/players.js"/>"></script>
    <title>Online Players</title>
</head>
<script type="application/javascript">
    window.onload = function () {
        var myElement = document.getElementById('player-pos');
        var topPos = myElement.offsetTop;
        console.log("topPos: " + topPos);
        document.getElementById('player-info-card-body').scrollTop = topPos - 200;
    }
</script>
<body>
<c:choose>
    <c:when test="${empty player}">
        There's no player with that name
    </c:when>
    <c:otherwise>
        <div class="container-fluid">
            <div class="row" style="padding-top: 0 !important;">
                <div class="col-lg-12" style="padding-top: 20px !important;">
                    <div class="card">
                        <div class="card-header">${player.name}'s location</div>
                        <div class="card-body" id="player-info-card-body" style="overflow: auto; position: relative">
                            <img src="map/ct0_all.jpg" alt="none" id="map-img"/>
                            <div style="position: absolute; top: ${player.mapY}px; left: ${player.mapX - 30}px"
                                 id="player-pos">
                                <img src="data:image/png;base64,iVBORw0KGgoAAAANSUhEUgAAAAgAAAAICAYAAADED76LAAAAE0lEQVR42mP8z8AARLgB48hQAAAdEw/5itjcFgAAAABJRU5ErkJggg=="
                                     title="${player.name} &#013;X: ${player.x}&#013;Y: ${player.y}&#013;Z: ${player.z}"
                                     alt="None"/>
                            </div>
                            <div class="card-text"></div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="row" style="padding-top: 0 !important;">
                <div class="col-lg-12" style="padding-top: 20px !important;">
                    <div class="card">
                        <div class="card-header">Inventory</div>
                        <div class="card-body" id="player-inventory-card-body"
                             style="overflow: auto; position: relative">
                            <table class="table table-sm table-striped table-bordered">
                                <thead>
                                <tr>
                                    <th scope="col">ITEM ID</th>
                                    <th scope="col">NAME</th>
                                    <th scope="col">COUNT</th>
                                    <th scope="col">LOCATION</th>
                                </tr>
                                </thead>
                                <c:if test="${not empty player.items}">
                                    <c:forEach items="${player.items}" var="apiitem">
                                        <tr>
                                            <td><c:out value="${apiitem.itemId}"/></td>
                                            <td><c:out value="${apiitem.name}"/></td>
                                            <td><c:out value="${apiitem.count}"/></td>
                                            <td><c:out value="${apiitem.location}"/></td>
                                        </tr>
                                    </c:forEach>
                                </c:if>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </c:otherwise>
</c:choose>
</body>