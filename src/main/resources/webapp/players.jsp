<!DOCTYPE html>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/players.css"/>">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="<c:url value="js/players.js"/>"></script>
    <title>Online Players</title>
</head>

<body>
<div class="container-fluid">
    <div class="row">
        <c:if test="${not empty players}">
            <c:forEach items="${players}" var="player">
                <div class="col-lg-4" style="margin-bottom: 25px">
                    <div class="card">
                        <div class="card-body">
                            <div class="row" style="padding-top: 0 !important;">
                                <div class="col-lg-12">
                                    <span class="badge badge-dark"><c:out value="${player.level}"/></span>
                                    <span class="player-name"><c:out value="${player.name}"/></span>
                                    <button class="btn btn-sm btn-danger kick_button" style="float: right;" id=<c:out
                                            value="kick_button_${player.name}"/>>Kick
                                    </button>
                                    <div class="points" style="margin-top: 15px">
                                        <span class="pspan" style="float: left">CP:</span>
                                        <div class="progress">
                                            <div class="progress-bar bg-warning" role="progressbar"
                                                 style="width: ${(player.cp * 100) / player.maxCP}%;"
                                                 aria-valuenow="${(player.cp * 100) / player.maxCP}"
                                                 aria-valuemin="0"
                                                 aria-valuemax="100"><c:out value="${player.cp}"/>/<c:out
                                                    value="${player.maxCP}"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="points">
                                        <span class="pspan" style="float: left">HP:</span>
                                        <div class="progress">
                                            <div class="progress-bar bg-danger" role="progressbar"
                                                 style="width: ${(player.hp * 100) / player.maxHP}%;"
                                                 aria-valuenow="${(player.hp * 100) / player.maxHP}"
                                                 aria-valuemin="0" aria-valuemax="100"><c:out
                                                    value="${player.hp}"/>/<c:out value="${player.maxHP}"/>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="points">
                                        <span class="pspan" style="float: left">MP:</span>
                                        <div class="progress">
                                            <div class="progress-bar bg-primary" role="progressbar"
                                                 style="width: ${(player.mp * 100) / player.maxMP}%;"
                                                 aria-valuenow="${(player.mp * 100) / player.maxMP}"
                                                 aria-valuemin="0"
                                                 aria-valuemax="100"><c:out value="${player.mp}"/>/<c:out
                                                    value="${player.maxMP}"/>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="row">
                                <div class="col-lg-4 col-md-4 col-sm-4 text-center">
                                    <span class="btn btn-danger">Karma <span class="badge badge-light"><c:out
                                            value="${player.karma}"/></span></span>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 text-center">
                                    <span class="btn btn-info">PK <span class="badge badge-light"><c:out
                                            value="${player.pk}"/></span></span>
                                </div>
                                <div class="col-lg-4 col-md-4 col-sm-4 text-center">
                                    <span class="btn btn-success">PVP <span class="badge badge-light"><c:out
                                            value="${player.pvp}"/></span></span>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </c:forEach>
        </c:if>
        <c:if test="${empty players}">
            <div class="no-players">No players Online.</div>
        </c:if>
    </div>
</div>
</body>
</html>