<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<html>
<head>
<title>Test for test entity input</title>
</head>
<body>
	<p>Текущее время ${currentTime}</p>
	<p>CRUD type: ${CrudType}</p>
	<table>
		<tr>
			<td>Введите значения</td>
			<td>Результат</td>
		</tr>
		<tr>
			<form method="post" action="/showtest">
				<label for="name1">Введите name1:</label>
				<!--
                name - указываем имя параметра, по которому получаем данные в контроллере
                type - типу инпута для ввода текста
                placeholder - чем заполнить поле, когда значение не введено
                id - просто идентификатор поля на странице для тега label
            -->
				<input type="text" id="name1" name="name1" placeholder="type name1" />
				<br /> <br /> <label for="name2">Введите name2:</label>
				<!--
                name - указываем имя параметра, по которому получаем данные в контроллере
                type - типу инпута для ввода текста
                placeholder - чем заполнить поле, когда значение не введено
                id - просто идентификатор поля на странице для тега label
            -->
				<input type="text" id="name2" name="name2" placeholder="type name2" />
				<br /> <br />
				<!--
                value - надпись на кнопке
                type - типу инпута кнопка для отправки формы
            -->
				<input type="submit" value="send" />
			</form>
		</tr>
		<tr>"${resobject}"
		</tr>
	</table>

</body>
</html>
