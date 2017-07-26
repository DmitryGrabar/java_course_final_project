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
        <link rel="stylesheet" href="../css/profile.css" type="text/css"/>
    </head>

    <body>

    <div class="navbar navbar-inverse ">
        <div class="container">
            <div class="navbar-collapse collapse">
                <ul class="nav navbar-nav pull-left">
                    <li><ctg:info-date language="${language}"></ctg:info-date></li>
                </ul>
                <ul class="nav navbar-nav pull-right">
                    <li><a href="controller?command=singlegame"><fmt:message key="single_game"/></a></li>
                    <li><a href="controller?command=logout"><fmt:message key="logout"/></a></li>
                </ul>
            </div>
        </div>
    </div>

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
            <div class="tabbable">
                <ul class="nav nav-tabs">

                    <li class="active"><a href="#1" data-toggle="tab"><fmt:message key="profile"/></a></li>
                    <li><a href="#2" data-toggle="tab"><fmt:message key="money"/></a></li>
                    <li><a href="#3" data-toggle="tab"><fmt:message key="game_history"/></a></li>
                    <li><a href="#4" data-toggle="tab" id="rulesBtn"><fmt:message key="game_rules"/></a></li>
                </ul>
                <div class="tab-content">
                    <div class="tab-pane active" id="1">
                        <div class="col-md-6">
                            <img src="../${user.imgPath}" width="40%" id="img">
                            <div class="img" id="img_change">
                                <form action="upload" method="post" enctype="multipart/form-data">
                                    <div class="fileform">
                                        <input type="file" name="file" accept="image/*"/>
                                    </div>
                                    <button class="btn btn-default"><fmt:message key="submit"/></button>
                                </form>
                            </div>
                        </div>
                        <div class="col-md-6">
                            <div class="user_info">
                                <label><fmt:message key="username"/>:</label>
                                <label>${user.login}</label>
                                <br>
                                <label><fmt:message key="rating"/>: ${user.rating}</label>
                                <br>
                                <label><fmt:message key="money"/>: ${user.money}</label>
                            </div>
                            <br>
                            <label><fmt:message key="mess_to_adm"/>: </label>
                            <br>
                            <form action="${pageContext.request.contextPath}/controller" method="get">
                                <input type="hidden" name="command" value="sendMessage">
                                <textarea name="text"></textarea>
                                <button class="btn btn-default"><fmt:message key="submit"/></button>
                            </form>
                        </div>
                    </div>
                    <div class="tab-pane" id="2">
                        <div class="tabbable tabs-below" id="tab_below">
                            <div class="tab-content">
                                <div class="tab-pane active" id="tab1">
                                    <form>
                                        <input type="hidden" name="command" value="addbalance">
                                        <div class="pay form-group">
                                            <div class="input-group">
                                                <span class="input-group-addon"><fmt:message key="card_num"/></span>
                                                <input type="text" class="form-control" name="msg"
                                                       placeholder="1111 1111 1111 1111"
                                                       maxlength="19" pattern="[0-9]{4}\s[0-9]{4}\s[0-9]{4}\s[0-9]{4}"
                                                       required="required">
                                            </div>
                                            <br>
                                            <div class="input-group">
                                                <span class="input-group-addon"><fmt:message key="enter_money"/></span>
                                                <input type="text" class="form-control" name="money"
                                                       pattern="[1-9][0-9]{1,3}" required="required" placeholder="">
                                            </div>
                                            <br>
                                            <input type="submit" id="upBalance" class="btn btn-default"
                                                   value="<fmt:message key="pay"/>">
                                        </div>
                                    </form>
                                </div>
                                <div class="tab-pane" id="tab2">
                                    <form>
                                        <input type="hidden" name="command" value="reducebalance">
                                        <div class="pay form-group">
                                            <div class="input-group">
                                                <span class="input-group-addon"><fmt:message key="card_num"/></span>
                                                <input id="msg" type="text" class="form-control" name="msg"
                                                       placeholder="1111 1111 1111 1111"
                                                       maxlength="19" pattern="[0-9]{4}\s[0-9]{4}\s[0-9]{4}\s[0-9]{4}"
                                                       required="required">
                                            </div>
                                            <br>
                                            <div class="input-group">
                                                <span class="input-group-addon"><fmt:message key="enter_money"/></span>
                                                <input type="text" class="form-control" name="money"
                                                       pattern="[1-9][0-9]{1,3}" required="required" placeholder="">
                                            </div>
                                            <br>
                                            <input type="submit" id="downBalance" class="btn btn-default"
                                                   value="<fmt:message key="get_money"/>">
                                        </div>
                                    </form>
                                </div>
                            </div>
                            <ul class="nav nav-tabs" id="pay_add_money">
                                <li class="active"><a href="#tab1" data-toggle="tab"><fmt:message key="pay"/></a></li>
                                <li><a href="#tab2" data-toggle="tab"><fmt:message key="get_money"/></a></li>
                            </ul>
                        </div>
                    </div>
                    <div class="tab-pane" id="3">
                        <div class="games">
                            <label><fmt:message key="all_games"/>:${user.allGames}</label>
                            <br>
                            <label><fmt:message key="win_games"/>:${user.winGames}</label>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <label id="error" hidden>${errorMessage}</label>
        <div class="overlay"></div>
        <div class="popup">
            <div class="close_window">x</div>
        </div>
    </main>
    <script src="../script/lib/jquery-2.2.3.min.js"></script>
    <script src="../script/lib/bootstrap.min.js"></script>
    <script src="../script/profile_page_script.js"></script>
    </body>
    </html>
</fmt:bundle>