window.onload = function () {
    let restartServerButton = document.getElementById("restart-server");
    restartServerButton.onclick = function (event) {
        $.get("/server/restart?time=60", function (data, status) {
            if (status === "success") {

                restartServerButton.style.display = "none";

                let serverRestartSuccess = document.getElementById("server-restart-success");
                let serverRestartSuccessText = document.getElementById("server-restart-success-text");
                serverRestartSuccess.style.display = "block";

                let timeleft = 10;
                let downloadTimer = setInterval(function () {
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

    let loginServerLogsButton = document.getElementById("login-server-logs");
    let gameServerLogsButton = document.getElementById("gameserver-server-logs");
    let buttonMap = document.getElementById("button-map");
    let searchPlayerButton = document.getElementById("find-player-button");
    let logCard = document.getElementById("log-card");

    document.getElementById("refresh-chat-log").onclick = function () {
        loadChatLogs();
    };

    document.getElementById("close-log").onclick = function () {
        logCard.style.display = "none";
    };

    let logContent = document.getElementById("log-content");
    let chatContent = document.getElementById("chat-content");
    gameServerLogsButton.onclick = showGSLogs;
    loginServerLogsButton.onclick = showLSLogs;
    buttonMap.onclick = goToMap;

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

    function goToMap() {
        window.location.href = "/map";
    }

    searchPlayerButton.onclick = function () {
        let playerName = document.getElementById("player-name-input");
        window.location.href = "/playerinfo?playerName=" + playerName.value;
    };

    let annButton = document.getElementById("ann-button");
    let annText = document.getElementById("ann-text");
    annButton.onclick = function () {
        let annTextValue = annText.value;
        if (annTextValue === "")
            return;
        $.get("/server/announce?ann=" + annTextValue, function (data, status) {
            if (status === "success") {
                annText.value = "";
            }
        });
    };

    loadChatLogs();
};