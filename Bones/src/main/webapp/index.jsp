<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<fmt:setLocale value="${language}" scope="session"/>
<fmt:bundle basename="pagecontent" prefix="label.">
    <!DOCTYPE html>
    <html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
        <link rel="stylesheet" href="css/lib/bootstrap.css">
        <link rel="stylesheet" href="css/login.css" type="text/css"/>
    </head>

    <body>

    <div class="navbar navbar-inverse navbar-fixed-top headroom">
        <ul>
            <li class="left-float">
                <div id="rulesBtn">
                    <a><fmt:message key="rules_but"/></a>
                </div>
            </li>
            <li class="right-float">
                <div class="dropdown">
                    <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown">Language
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu">
                        <form action="${pageContext.request.contextPath}controller" method="post">
                            <input type="hidden" name="command" value="language">
                            <input type="hidden" name="language" value="ru_RU">
                            <input class="language" id="ru" type="submit" name="rus" value="ru">
                        </form>
                        <form action="${pageContext.request.contextPath}controller" method="post">
                            <input type="hidden" name="command" value="language">
                            <input type="hidden" name="language" value="en_EN">
                            <input class="language" id="eng" type="submit" name="eng" value="en">
                        </form>
                    </ul>
                </div>
            </li>
        </ul>
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

    <table class="tableBut">
        <td>
            <button id="loginButton"><fmt:message key="login_but"/></button>
        </td>
        <td>
            <button class="signupButton" id="signupButton"><fmt:message key="signup_but"/></button>
        </td>
    </table>

    <form class="sign-up-form" action="controller" method="post" id="sign-up-form">
        <input type="hidden" name="command" value="login">
        <p class="clearfix">
            <label for="email"><fmt:message key="email"/><span class="star"> *</span></label>
            <input type="text" name="email" id="email" placeholder="<fmt:message key="email"/>"
                   pattern="[A-z0-9_\.-]+@[a-z]+\.[a-z]{2,4}" title="An example of an correct email: wtf@gmail.com"
                   required="required">
        </p>
        <p class="clearfix">
            <label for="password"><fmt:message key="pass"/><span class="star"> *</span></label>
            <input type="password" name="password" id="password" placeholder="<fmt:message key="pass"/>"
                   pattern="[A-z0-9_-]{6,18}" title="The password must contain 6-18 characters or digits."
                   required="required">
        </p>
        <p class="clearfix">
            <input type="submit" name="submit" value=<fmt:message key="enter_but"/>>
        </p>
    </form>

    <form class="sign-up-form hidden" action="controller" method="post" id="registration-form">
        <input type="hidden" name="command" value="registration">
        <p class="clearfix">
            <label for="sign_login"><fmt:message key="login_but"/><span class="star"> *</span></label>
            <input type="text" name="sign_login" id="sign_login" placeholder="<fmt:message key="login_but"/>"
                   pattern="[A-z0-9_-]{3,16}" title="The login must contain 3-16 characters or digits"
                   required="required">
        </p>
        <p class="clearfix">
            <label for="email"><fmt:message key="email"/><span class="star"> *</span></label>
            <input type="text" name="sign_email" id="sign_email" placeholder="<fmt:message key="email"/>"
                   pattern="[A-z0-9_\.-]+@[a-z]+\.[a-z]{2,4}" title="An example of an correct email: wtf@gmail.com"
                   required="required">
        </p>
        <p class="clearfix">
            <label for="sign_pass"><fmt:message key="pass"/><span class="star"> *</span></label>
            <input type="password" name="sign_pass" id="sign_pass" placeholder="<fmt:message key="pass"/>"
                   pattern="[A-z0-9_-]{3,16}" title="The password must contain 6-18 characters or digits."
                   required="required">
        </p>
        <p class="clearfix">
            <label for="confirm_password"><fmt:message key="conf_pass"/><span class="star">*</span></label>
            <input type="password" name="confirm_password" id="confirm_password"
                   placeholder="<fmt:message key="conf_pass"/>"
                   pattern="[A-z0-9_-]{3,16}" title="The password must contain 6-18 characters or digits."
                   required="required">
        </p>
        <p class="clearfix">
            <input type="submit" name="submit_form2" value=<fmt:message key="registr_but"/>>
        </p>
    </form>
    <label id="error" hidden>${errorMessage}</label>
    <div class="overlay"></div>
    <div class="popup">
        <div class="close_window">x</div>
    </div>

    <script src="script/lib/jquery-2.2.3.min.js"></script>
    <script src="script/lib/bootstrap.min.js"></script>
    <script src="script/index_page_script.js"></script>

    </body>

    <c:if test="${not empty user}">
        <c:redirect url="controller?command=forward&forward=toProfile"/>
    </c:if>

    </html>
</fmt:bundle>