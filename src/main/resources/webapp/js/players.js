window.onload = function () {
    var kickButtons = document.getElementsByClassName("kick_button");
    for (var i = 0; i < kickButtons.length; i++) {
        kickButtons[i].onclick = function () {
            var objId = event.target.id;
            console.log("KickButtonID: " + objId);
            var playerName = objId.substring(objId.lastIndexOf("_") + 1, objId.length);
            console.log("PlayerName: " + playerName);

            var url = "/players?player_name=" + playerName + "&action=KICK";
            $.post(url, function (data, status) {
                if (data.status === "OK") {
                    window.location.reload(true);
                }
            });
        }
    }

    var findPlayerButton = document.getElementById("find-player-button");
    findPlayerButton.onclick = function () {
        console.log("findPlayer");
    };
};