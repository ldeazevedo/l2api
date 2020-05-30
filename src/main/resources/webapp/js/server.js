window.onload = function () {
    var restartServerButton = document.getElementById("restart-server");
    restartServerButton.onclick = function (event) {
        $.get("/server/restart?time=10", function (data, status) {
            if (status === "success") {

                restartServerButton.style.display = "none";

                var serverRestartSuccess = document.getElementById("server-restart-success");
                var serverRestartSuccessText = document.getElementById("server-restart-success-text");
                serverRestartSuccess.style.display = "block";

                var timeleft = 10;
                var downloadTimer = setInterval(function () {
                    serverRestartSuccess.value = 10 - --timeleft;
                    if (timeleft <= 0)
                        clearInterval(downloadTimer);
                }, 1000);
                setTimeout(function () {
                    serverRestartSuccess.style.display = "none";
                    serverRestartSuccessText.style.display = "block";
                    serverRestartSuccess.value = 0;
                }, 10000);
                setTimeout(function () {
                    serverRestartSuccessText.style.display = "none";
                    restartServerButton.style.display = "block";
                }, 15000);
            }
        });
    };

    var loginServerLogsButton = document.getElementById("login-server-logs");
    var gameServerLogsButton = document.getElementById("gameserver-server-logs");
    var logCard = document.getElementById("log-card");

    document.getElementById("refresh-chat-log").onclick = function () {
        loadChatLogs();
    };

    document.getElementById("close-log").onclick = function () {
        logCard.style.display = "none";
    };

    var logContent = document.getElementById("log-content");
    var chatContent = document.getElementById("chat-content");
    gameServerLogsButton.onclick = showGSLogs;
    loginServerLogsButton.onclick = showLSLogs;

    function showLSLogs() {
        showHideLogCard();
        if (logCard.style.display !== "none")
            loadLsGsLogs("lslogs");
        logContent.innerHTML = "Loading...";
    }

    function showGSLogs() {
        showHideLogCard();
        if (logCard.style.display !== "none")
            loadLsGsLogs("gslogs");
        logContent.innerHTML = "Loading...";
    }

    function loadChatLogs() {
        $.get("/server/chatlogs", function (data, status) {
            if (status === "success") {
                chatContent.innerHTML = data;
                chatContent.scrollTop = chatContent.scrollHeight;
            }
        });
    }

    function loadLsGsLogs(lsgs) {
        $.get("/server/" + lsgs, function (data, status) {
            if (status === "success") {
                logContent.innerHTML = data;
                logContent.scrollTop = logContent.scrollHeight;
            }
        });
    }

    function showHideLogCard() {
        if (logCard.style.display === "none" || logCard.style.display === "")
            logCard.style.display = "inline-block";
        /*else
            logCard.style.display = "none";*/
    }

    var annButton = document.getElementById("ann-button");
    var annText = document.getElementById("ann-text");
    annButton.onclick = function () {
        var annTextValue = annText.value;
        if (annTextValue === "")
            return;
        $.get("/server/announce?ann=" + annTextValue, function (data, status) {
            if (status === "success") {
                annText.value = "";
            }
        });
    };

    var playerName = document.getElementById("player-name-input").value;
    var findPlayerButton = document.getElementById("find-player-button");
    findPlayerButton.onclick = function () {
        $.get("/playerinfo?playerName=" + playerName, function (data, status) {
            if (status === "success") {
                console.log(data);
                showPlayerInfo(data);
            }
        });
    };

    function showPlayerInfo(playerData) {
        console.log("showPlayerInfo()");
    }

    loadChatLogs();
};