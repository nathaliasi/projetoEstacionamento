<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<link rel="stylesheet" type="text/css" href="style/loginadm.css">
<title>Insert title here</title>
</head>
<body>
<div class="container-login">
	<form class="form-login" method="post" action="LoginServlet">
    Nome de Usuário: <input type="text" name="username"><br>
    Senha: <input type="password" name="password"><br>
     <input class="btn" type="submit" value="Login">
</form>

</div>
</body>
