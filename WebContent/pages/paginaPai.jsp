<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Página PAI - Load jQuery</title>
		<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	</head>
	<body>
		<a href="../index.jsp">Página Inicial</a><br/>
		<h3>Página PAI - Load jQuery</h3>		
		
		<input type="button" value="Carregar página filha Ajax" onclick="carregar();"/>
		
		<div id="mostrarPaginaFilha"></div>
	</body>
	
	<script type="text/javascript">
		
		function carregar() {
			$("#mostrarPaginaFilha").load('paginaFilha.jsp')
		}
	
	</script>
</html>