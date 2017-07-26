<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="ctg" uri="custom-tags" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:bundle basename="pagecontent" prefix="label.">
    <!DOCTYPE html>
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <link rel="stylesheet" href="../css/lib/bootstrap.css">
        <link rel="stylesheet" href="../css/lib/jPages.css" type="text/css"/>
        <link rel="stylesheet" href="../css/admin.css" type="text/css"/>
    </head>

    <body>
    <div id="myModal" class="modal">
        <div class="modal-content">
            <span class="close">&times;</span>
            <p>
                <fmt:message key="game_rules"/>
                <br>
                <fmt:message key="game_rules_context"/>
            </p>
        </div>
    </div>

    <main>
        <div class="container">

            <div class="right-float">
                <li><a href="controller?command=logout"><fmt:message key="logout"/></a></li>
            </div>
            <div class="tabbable">
                <ul class="nav nav-tabs">
                    <li class="active"><a data-toggle="tab" href="#1"><fmt:message key="profile"/></a></li>
                    <li><a data-toggle="tab" href="#2"><fmt:message key="users"/></a></li>
                    <li><a data-toggle="tab" href="#3"><fmt:message key="messages"/></a></li>
                    <li><a data-toggle="tab" id="rulesBtn" class="right-float"><fmt:message key="game_rules"/></a></li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane fade in active" id="1">
                        <div class="right-float">
                            <form class="set_startup_game" action="controller" method="post" id="set_startup_game">
                                <input type="hidden" name="command" value="gamestartupparam">
                                <label><fmt:message key="game_startup_param"/></label>
                                <br>
                                <label for="get_bet"><fmt:message key="min_bet"/></label>
                                <br>
                                <input id="get_bet" name="bet" type="number" min="0" pattern="[0-9]+"
                                       required="required">
                                <br>
                                <label for="get_points"><fmt:message key="min_game_points"/></label>
                                <br>
                                <input id="get_points" name="points" type="number" min="0" max="21" pattern="[0-9]+"
                                       required="required">
                                <br>
                                <button class="btn btn-default"><fmt:message key="enter_but"/></button>
                            </form>
                        </div>
                        <div class="user_and_message">
                            <div class="user_info">
                                <h3><fmt:message key="username"/>: ${user.login}</h3>
                            </div>
                            <div class="profile">
                                <img src="../${user.imgPath}" width="30%" id="img">
                                <div class="img" id="img_change">
                                    <form action="upload" method="post" enctype="multipart/form-data">
                                        <div class="fileform">
                                            <input type="file" name="file"/>
                                        </div>
                                        <button class="btn btn-default"><fmt:message key="submit"/></button>
                                    </form>
                                </div>
                            </div>
                            <br>
                        </div>
                    </div>
                    <div class="tab-pane fade" id="2">
                        <ul id="itemContainer">
                            <li>
                                <label id="email_label"><fmt:message key="email"/>: </label>
                                <label id="login_label"><fmt:message key="login_but"/>: </label>
                            </li>
                            <c:forEach var="user" items="${users}">
                                <li>
                                    <label id="email">${user.email}</label>
                                    <label id="login">${user.login}</label>
                                    <label id="button">
                                        <button id="ban" class='btn btn-default'
                                                onclick="setBan('${!user.ban}','${user.email}')">${user.ban?"unban":"ban"}</button>
                                    </label>
                                </li>
                            </c:forEach>
                        </ul>
                        <div class="holder"></div>
                        <br>
                        <div class="users">
                        </div>
                        <br>
                    </div>
                    <div class="tab-pane fade" id="3">
                        <ul id="messageContainer">
                            <li>
                                <label class="mes_button"></label>
                                <label class="userId" id="user_label">User id: </label>
                                <label class="text">Message: </label>
                            </li>
                            <c:forEach var="message" items="${messages}">
                                <li>
                                    <label class="mes_button">
                                        <img src="/images/delete.png" class="delete" height="40px" width="40px"
                                             onclick="mesDel('${message.text}','${message.userId}')">
                                    </label>
                                    <label class="userId">${message.userId}</label>
                                    <p class="text">${message.text}</p>
                                </li>
                            </c:forEach>
                        </ul>
                        <div class="mess_holder"></div>
                        <br>
                        <div class="messages">
                        </div>
                        <br>
                    </div>
                </div>
            </div>
        </div>
    </main>
    <footer class="modal-footer"><ctg:info-date language="${language}"></ctg:info-date></footer>
    <script src="../script/lib/jquery-2.2.3.min.js"></script>
    <script src="../script/lib/bootstrap.min.js"></script>
    <script src="../script/lib/jPages.js"></script>
    <script src="../script/admin_page_script.js"></script>
    <script>
        $(function () {
            $("div.holder").jPages({
                containerID: "itemContainer"
            });
        });
    </script>
    </body>
    </html>
</fmt:bundle>