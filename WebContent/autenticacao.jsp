<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Autenticar Usuário</title>
	</head>
	<body>
		<h3>Autenticar Usuário</h3>
		
		<form action="autenticacaoUsuario" method="post">
			<input type="hidden" readonly="readonly" id="url" name="url" value="<%= request.getParameter("url")%>"/>
			<table>
				<tr>
					<td>Login</td>
					<td><input type="text" id="login" name="login" /></td>
				</tr>
				<tr>
					<td>Senha</td>
					<td><input type="password" id="passwd" name="passwd" /></td>
				</tr>
				<tr>
					<td></td>
					<td><input type="submit" id="logar" name="logar" value="Logar"/></td>
				</tr>
			</table>
		
		</form>	
	</body>
</html>