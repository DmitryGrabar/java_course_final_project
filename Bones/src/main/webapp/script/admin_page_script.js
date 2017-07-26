$(document).ready(function () {
    $('#img_change').hide();
});

$('#img').click(function () {
    $('#img_change').show();
});

// Get the modal
var modal = document.getElementById('myModal');

// Get the button that opens the modal
var btn = document.getElementById("rulesBtn");

// Get the <span> element that closes the modal
var span = document.getElementsByClassName("close")[0];

// When the user clicks the button, open the modal
btn.onclick = function() {
    modal.style.display = "block";
}

// When the user clicks on <span> (x), close the modal
span.onclick = function() {
    modal.style.display = "none";
}

// When the user clicks anywhere outside of the modal, close it
window.onclick = function(event) {
    if (event.target == modal) {
        modal.style.display = "none";
    }
}

function setBan(ban, email) {
    var data = function () {
        return {
            command: "setBan",
            ban: ban,
            email: email
        };
    };
    $.ajax({
        type: "POST",
        url: "ajaxController",
        data: JSON.stringify(data(ban, email)),
        dataType: "json",
        async: false,
        headers: {"Access-Control-Allow-Origin": "*"},
        contentType: "application/json; charset=utf-8",
        success: function (responseText) {
        },
        error: function (responseText) {
        }
    });
    setTimeout('window.location.reload(true)', 1000);
}
function mesDel(text, userId) {
    var data = function () {
        return {
            command: "delete_message",
            text: text,
            userId: userId
        };
    };
    $.ajax({
        type: "POST",
        url: "ajaxController",
        data: JSON.stringify(data()),
        dataType: "json",
        async: false,
        headers: {"Access-Control-Allow-Origin": "*"},
        contentType: "application/json; charset=utf-8",
        success: function (responseText) {
        },
        error: function (responseText) {
        }
    });
    setTimeout('window.location.reload(true)', 1000);
}