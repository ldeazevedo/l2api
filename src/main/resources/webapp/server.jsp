<!DOCTYPE html>
<html>
<head>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <link rel="stylesheet" type="text/css" href="<c:url value="/css/server.css"/>">
    <script src="<c:url value="/js/server.js"/>"></script>
    <title>Server Management</title>
</head>

<body>
<div class="container-fluid">
    <div class="row">
        <div class="col-lg-4">
            <div class="row" style="padding-top: 0 !important;">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-header">Server Restart</div>
                        <div class="card-body">
                            <p class="card-text">Reinicia el servidor haciendo click en Restart!</p>
                            <div class="card-text">
                                <button type="button" class="btn btn-primary" id="restart-server" style="float: left">
                                    Restart!
                                </button>
                                <progress value="0" max="10" id="server-restart-success"></progress>
                                <span id="server-restart-success-text">Servidor reiniciado!</span>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-12" style="padding-top: 20px !important;">
                    <div class="card">
                        <div class="card-header">Server Info</div>
                        <div class="card-body">
                            <div class="card-text">
                                <a href="/players">Players Online: <c:out value="${playersonline}"></c:out></a>
                                <div class="input-group mb-3" id="ann">
                                    <input type="text" maxlength="99" class="form-control" id="ann-text"
                                           placeholder="Announcement" aria-label="Announcement"
                                           aria-describedby="basic-addon2">
                                    <div class="input-group-append">
                                        <button class="btn btn-primary" type="button" id="ann-button">Enviar!</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-12" style="padding-top: 20px !important;">
                    <div class="card">
                        <div class="card-header">Chat Logs</div>
                        <div class="card-body">
                            <div class="card-block">
                            </div>
                            <div class="card-block">
                                <div class="card" id="chat-card">
                                    <button class="btn btn-link" id="refresh-chat-log">Refresh</button>
                                    <div class="card-body" id="chat-content">Log content</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="col-lg-8">
            <div class="row" style="padding-top: 0 !important;                  ">
                <div class="col-lg-12">
                    <div class="card">
                        <div class="card-header">Server Logs</div>
                        <div class="card-body">
                            <div class="card-block">
                                <button type="button" class="btn btn-primary" data-toggle="button" aria-pressed="false"
                                        id="login-server-logs">
                                    LoginServer
                                </button>
                                <button type="button" class="btn btn-primary" id="gameserver-server-logs">
                                    GameServer
                                </button>
                            </div>
                            <div class="card-block">
                                <div class="card" id="log-card">
                                    <button class="btn btn-link" id="close-log">Cerrar</button>
                                    <div class="card-body" id="log-content">Log content</div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-12" style="padding-top: 20px !important;">
                    <div class="card">
                        <div class="card-header">Character's Ban</div>
                        <div class="card-body" id="players-ban-card-body">
                            <div class="card-block">
                                <table class="table table-sm table-striped table-bordered">
                                    <thead>
                                    <tr>
                                        <th scope="col">KEY</th>
                                        <th scope="col">TYPE</th>
                                        <th scope="col">PUNISHED BY</th>
                                        <th scope="col">REASON</th>
                                    </tr>
                                    </thead>
                                    <tbody>
                                    <c:if test="${not empty banned_players}">
                                        <c:forEach items="${banned_players}" var="player">
                                            <tr>
                                                <td scope="row"><c:out value="${player.charName}"/> (<c:out
                                                        value="${player.affect}"/>)
                                                </td>
                                                <td><c:out value="${player.type}"/></td>
                                                <td><c:out value="${player.punishedBy}"/></td>
                                                <td><c:out value="${player.reason}"/></td>
                                            </tr>
                                        </c:forEach>
                                    </c:if>
                                    </tbody>
                                </table>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="col-lg-12" style="padding-top: 20px !important;">
                    <div class="card">
                        <div class="card-header">Find character Info</div>
                        <div class="card-body" id="player-info-card-body">
                            <div class="card-text">
                                <div class="input-group mb-3" id="player-name">
                                    <input type="text" maxlength="99" class="form-control" id="player-name-input"
                                           placeholder="Player name" aria-label="Player name"
                                           aria-describedby="basic-addon2">
                                    <div class="input-group-append">
                                        <button class="btn btn-primary" type="button" id="find-player-button">Buscar</button>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
</div>
</body>
</html>