<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<c:if test="${isAuth==false}">
    <%-- Модальные окна --%>
    <%-- Модальное окно ауторизации--%>
    <div class="modal fade" id="loginModal" tabindex="-1" role="dialog" aria-labelledby="loginModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title" id="loginModalLabel">Вход</h3>
                </div>
                <div class="modal-body">
                    <sf:form id="loginForm">
                        <div class="alert alert-danger hide">
                            <p>Invalid login or password </p>
                        </div>
                        <div class="form-group">
                            <label for="loginInputLogin">Логин</label>
                            <input type="text" class="form-control" name="login" id="loginInputLogin"
                                   placeholder="Логин">
                        </div>
                        <div class="form-group">
                            <label for="loginInputPassword">Пароль</label>
                            <input type="password" class="form-control" name="password" id="loginInputPassword"
                                   placeholder="Пароль">
                        </div>
                        <%--TODO добавить checkbox "remeber me" для Spring Secucurity--%>
                    </sf:form>
                </div>
                <div class="modal-footer">
                    <button type="button" id="loginBtn" class="btn btn-primary">Войти</button>
                        <%--button type="button" class="btn btn-primary">Save changes</button--%>
                </div>
            </div>
                <%-- /.modal-content --%>
        </div>
            <%-- /.modal-dialog --%>
    </div>
    <%-- /.modal --%>

    <%-- Модальное окно регистрации--%>
    <div class="modal fade" id="regModal" tabindex="-1" role="dialog" aria-labelledby="regModalLabel">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h3 class="modal-title" id="regModalLabel">Форма регистрации</h3>
                </div>
                <div class="modal-body">
                    <sf:form id="regForm">
                        <div class="form-group has-feedback">
                            <label for="regInputEmail">Email</label>
                            <input type="email" class="form-control" name="email" id="regInputEmail"
                                   placeholder="Email">
                            <span class="glyphicon glyphicon-ok hide form-control-feedback" aria-hidden="true"></span>
                        </div>
                        <div class="form-group has-feedback">
                            <label for="regInputPhone">Телефон</label>
                            <input type="tel" class="form-control" name="phone" id="regInputPhone"
                                   placeholder="Phone">
                            <span class="glyphicon glyphicon-ok hide form-control-feedback" aria-hidden="true"></span>
                        </div>
                        <div class="form-group has-feedback">
                            <label for="regInputLogin">Логин</label>
                            <input type="text" class="form-control" name="login" id="regInputLogin"
                                   placeholder="Login">
                            <span class="glyphicon glyphicon-ok hide form-control-feedback" aria-hidden="true"></span>
                        </div>
                        <div class="form-group has-feedback">
                            <label for="regInputFirstName">Имя</label>
                            <input type="text" class="form-control" name="firstName" id="regInputFirstName"
                                   placeholder="FirstName">
                            <span class="glyphicon glyphicon-ok hide form-control-feedback" aria-hidden="true"></span>
                        </div>
                        <div class="form-group has-feedback">
                            <label for="regInputPassword">Пароль</label>
                            <input type="password" class="form-control" name="password" id="regInputPassword"
                                   placeholder="Пароль">
                            <span class="glyphicon glyphicon-ok hide form-control-feedback" aria-hidden="true"></span>
                        </div>
                        <div class="form-group has-feedback">
                            <label for="regInputPassword1">Повторите пароль</label>
                            <input type="password" class="form-control" id="regInputPassword1" placeholder="Password">
                            <span class="glyphicon glyphicon-ok hide form-control-feedback" aria-hidden="true"></span>
                        </div>
                        <input type="text" name="enabled" id="regInputEnabled" value="true" style="display:none">
                    </sf:form>
                </div>
                <div class="modal-footer">
                    <button type="button" id="regBtn" class="btn btn-primary">Регистрация</button>
                        <%--button type="button" class="btn btn-primary">Save changes</button--%>
                </div>
            </div>
                <%-- /.modal-content --%>
        </div>
            <%-- /.modal-dialog --%>
    </div><%-- /.modal --%>
    <script src="js/modalRegAuth.js"></script>
</c:if>
