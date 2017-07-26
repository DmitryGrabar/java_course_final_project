<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="ctg" uri="custom-tags" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:bundle basename="pagecontent" prefix="label.">
    <html>
    <head>
        <meta charset="utf-8">
        <title><fmt:message key="cubes"/></title>
        <link rel="stylesheet" href="../css/play.css" type="text/css"/>
    </head>

    <body>

    <div id="modal_form">
        <span id="modal_close">X</span>
        <label><fmt:message key="your"/> <fmt:message key="money"/>: </label>
        <label id="user_money">${user.money}</label>
        <br>
        <label for="get_bet"><fmt:message key="make_bet"/>: </label>
        <br>
        <input id="get_bet" type="number" min="${game.min_bet}" pattern="[0-9]+" required="required"><br>
        <div id="message">
            <fmt:message key="bet_should_be_less"/>:
            <label id="min_bet"> ${game.min_bet}</label>
        </div>
        <div id="get_points">
            <fmt:message key="min_game_points"/>
            <label id="min_points"> ${game.min_points}</label>
        </div>
        <input type="submit" id="make_bet" value="<fmt:message key="start"/>">
        <br>
        <label id="wrong_input"><fmt:message key="wrong_bet"/></label>
    </div>

    <div class="middle">
        <div id="result"><fmt:message key="your"/> <fmt:message key="result"/>: 0</div>
        <img id="first" src="../images/1.png"/>
        <img id="second" src="../images/2.png"/>
        <br>
        <button id="throw" name="go" type="button" onClick="GO()"><fmt:message key="throw_bones"/></button>
        <br>
        <button hidden="true" id="stop" name="stop" type="button"><fmt:message key="stop_btn"/></button>
        <br>

        <div class="bot_result">
            <div id="bot_result"><fmt:message key="bot"/> <fmt:message key="result"/>: 0</div>
            <img id="third" src="../images/1.png"/>
            <img id="fourth" src="../images/2.png"/>
        </div>
    </div>
    <script type="text/javascript" src="../script/lib/jquery-2.2.3.min.js"></script>
    <script type="text/javascript" src="../script/play_page_script.js"></script>

    </body>
    </html>
</fmt:bundle>