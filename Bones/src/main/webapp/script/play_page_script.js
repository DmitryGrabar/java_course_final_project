// Ф-ция пораждающая бъекты, представляющие собой "кости"
// (Это сделано для того что бы связать в одном месте представление объекта,
//  его значение, и добавить ему кое-какие методы - поведение )
$(document).ready(function () {
    $("#wrong_input").hide();
});

function Bone(imageElement) {
    return ({
        // представление кости
        face: imageElement,
        // Значение кости
        faceVal: 0,
        // Грани кости
        faces: [
            {img: "images/1.png", val: 1},
            {img: "images/2.png", val: 2},
            {img: "images/3.png", val: 3},
            {img: "images/4.png", val: 4},
            {img: "images/5.png", val: 5},
            {img: "images/6.png", val: 6}
        ],
        // Инициализация случ. значением:
        init: function () {
            // Получаем случ. чило от 0 до 5 (кол-во граней -1)
            var faceIndex = Math.round(Math.random() * (this.faces.length - 1));
            // Устанавливаем состояние вида ...
            this.face.src = this.faces[faceIndex].img;
            // ... и значения:
            this.faceVal = this.faces[faceIndex].val;
            // Возвернём себя самого :)
            return this;
        },
        // Кинуть :) кости
        roll: function (result) {
            // Промежуток между итерациями:
            var time = Math.round(Math.random() * 200) + 100,
                // Кол-во итераций:
                count = Math.round(Math.random() * 7) + 3,
                // Сохраняем контекст (ну, не люблю я всякие apply() и call() )
                ctx = this,
                // Этот интервал отвечает за псевдо анимацию костей
                interval = window.setInterval(
                    function () {
                        if ((count--) > 0) {
                            ctx.init();
                        } else {
                            window.clearInterval(interval);
                            // Увеличиваем счёт результата:
                            result.value += ctx.faceVal;
                            Bone.intervals--;
                            // пока есть хоть один интервал
                            // нельзя показывать результат
                            if (!Bone.intervals) {
                                result.show();
                            }
                            return;
                        }
                    }, time
                );
            Bone.intervals++;
        }
    }.init());
}
// Статич. элемент - счётчик интервалов
// (пока есть хоть один интервал нельзя показывать результат)
Bone.intervals = [];

// Косточки (их может быть и больше, но тогда
// нужно добавить картинок их представляющих)
// Их логично держать в одном месте :)
var bones = [
    Bone(document.getElementById("first")),
    Bone(document.getElementById("second"))
];
var bones1 = [
    Bone(document.getElementById("third")),
    Bone(document.getElementById("fourth"))
];
// Этот объект представляет результат броска
// (Сделано так, что бы связать HTMLElement и значения +
// добавлены сервисные методы)
var result = {
    // представление
    html: document.getElementById("result"),
    // значение
    value: 0,
    // отобразить результат:
    show: function () {
        if (this.value > min_points) {
            $('#stop').attr('hidden', false);
        }
        if (this.value > 21) {
            sendResult("You lose", 'false');
        }
        this.html.innerHTML = "Результат: " + this.value;
    },
    // очистить результат:
    clear: function () {
        this.value = 0;
        this.show();
    }
};

var bot_result = {
    // представление
    html: document.getElementById("bot_result"),
    // значение
    value: 0,
    // отобразить результат:
    show: function () {
        if (this.value > 21) {
            sendResult("You win", 'true');
        }
        this.html.innerHTML = "Результат: " + this.value;
    },
    // очистить результат:
    clear: function () {
        this.value = 0;
        this.show();
    }
};
var bot;
var player;
var min_points = $('#min_points').text();
$('#stop').click(function () {
    bot = bot_result.value;
    player = result.value;
    if (bot <= player) {
        bot_GO();
        setTimeout(logic, 6000);
    } else {
        logic();
    }

});

function logic() {
    if (bot < 22 && bot > player) {
        sendResult("You lose", 'false');
    } else if (bot < 22 && bot < player) {
        sendResult("You win", 'true');
    } else if (bot > 21 && player <= 21) {
        sendResult("You win", 'true');
    } else if (player > 21) {
        sendResult("You lose", 'false');
    }
}

/** Ф-ция, представляющая бросок костей:
 * @param array of Bone: bones - массив объектов Bone
 * @param result - объект представляющий результат.
 */
function attempt(bones, result) {
    var len = bones.length;
    // Кидаем все кости
    for (var i = 0; i < len; i += 1) {
        bones[i].roll(result);
    }
}
// Это запускающая ф-ция: =================================
function GO() {
    document.getElementById('throw').disabled = true;
    document.getElementById('stop').disabled = true;
    attempt(bones, result);
    setTimeout(bot_GO, 4000);
    setTimeout(function () {
        document.getElementById('throw').disabled = false;
        document.getElementById('stop').disabled = false;
    }, 6000);
}

function bot_GO() {
    attempt(bones1, bot_result);
}

function sentData(bet, win) {
    var data = function (bet, win) {
        return {
            command: "saveGame",
            bet: bet,
            win: win
        };
    };
    $.ajax({
        type: "POST",
        url: "ajaxController",
        data: JSON.stringify(data(bet, win)),
        dataType: "json",
        async: false,
        headers: {"Access-Control-Allow-Origin": "*"},
        contentType: "application/json; charset=utf-8",
        success: function (responseText) {
            //create(responseText.users);
        },
        error: function (responseText) {

        }
    });
}

var user_bet;
$("#make_bet").click(function () {
    var min_bet = parseInt($("#min_bet").html());
    var user_money = parseInt($("#user_money").text());
    user_bet = $("#get_bet").val();
    if (user_bet < min_bet) {
        $("#wrong_input").show();
    } else if (user_bet > user_money) {
        $("#wrong_input").show();
    } else {
        $('#modal_form')
            .animate({opacity: 0, top: '45%'}, 200, // плaвнo меняем прoзрaчнoсть нa 0 и oднoвременнo двигaем oкнo вверх
                function () { // пoсле aнимaции
                    $(this).css('display', 'none'); // делaем ему display: none;
                    $('#overlay').fadeOut(400); // скрывaем пoдлoжку
                }
            );
    }
});

$('#modal_close').click(function () {
    $(location).attr('href', 'controller?command=forward&forward=toProfile');
});

$('#play').click(function () { // лoвим клик пo крестику или пoдлoжке
    if ($('#get_bet').val() > 0) {
        user_bet = $('#get_bet').val();
        $('#modal_form')
            .animate({opacity: 0, top: '45%'}, 200, // плaвнo меняем прoзрaчнoсть нa 0 и oднoвременнo двигaем oкнo вверх
                function () { // пoсле aнимaции
                    $(this).css('display', 'none'); // делaем ему display: none;
                    $('#overlay').fadeOut(400); // скрывaем пoдлoжку
                }
            );
    }
    else {
        $('#message').style.display = "block";
    }
});

function sendResult(text, win) {
    alert(text);
    sentData(user_bet, win);
    $(location).attr('href', 'controller?command=forward&forward=toProfile');
}