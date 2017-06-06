<%@ page contentType="text/html;charset=UTF-8" language="java"
	trimDirectiveWhitespaces="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<%-- HEAD --%>
<jsp:include page="head.jsp" />
</head>
<body>
	<div class="container">
		<div class="row header">
			<div class="col-xs-12">
				<%--Главное меню сайта--%>
				<jsp:include page="mainMenu.jsp" />
			</div>
		</div>
		<div class="row main-content">
			<div class="col-xs-12">
				<%--TODO Вставить другой текст--%>
				<h3>Самый современный CarWashingService чисто и без очередей</h3>
				<p>
				<blockquote>
					<p>" Вы Автомобилист, не хотите стоять в очередях на автомойки
						Ø хотите планировать свое время Ø хотите минимизировать свои
						затраты на автомойку Вы Владельцы автомоек хотите увеличит число
						клиентов Ø хотитет удержать имеющихся клиентов Ø хотите
						оптимизировать загрузку моек Ø хотите иметь оперативную информацию
						о загрузке моек и cash-flow "
				</blockquote>
				</p>


			</div>
		</div>
		<%--TODO вынести в отдельную стр--%>
		<div class="row footer vertical-align">
			<div class="col-xs-12">О команде, контакты, ©</div>
		</div>
	</div>

	<%--Модальные окна ауторизации и регистрации--%>
	<jsp:include page="modalRegAuth.jsp" />

</body>
</html>
