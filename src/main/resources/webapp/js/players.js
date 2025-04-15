window.onload = function () {
    let kickButtons = document.getElementsByClassName("kick_button");
    for (let i = 0; i < kickButtons.length; i++) {
        kickButtons[i].onclick = function () {
            let objId = event.target.id;
            console.log("KickButtonID: " + objId);
            let playerName = objId.substring(objId.lastIndexOf("_") + 1, objId.length);
            console.log("PlayerName: " + playerName);

            let url = "/players?player_name=" + playerName + "&action=KICK";
            $.post(url, function (data) {
                if (data.status === "OK") {
                    window.location.reload();
                }
            });
        }
    }

    document.getElementById("find-player-button").onclick = function () {
        //TODO: add functionality here
        console.log("findPlayer");
    };
};