<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Capturando Exce��es com jQuery</title>
		<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	</head>
	<body>
		<a href="../index.jsp">P�gina Inicial</a><br/>
		<h3>Capturando Exce��es com jQuery</h3>
		<input type="text" value="valor informado" id="txtValor"/>
		<input type="button" onclick="testarExcecao();" value="Testar Exce��o"/>
	</body>
	
	<script type="text/javascript">
		function testarExcecao() {
			
			valorInformado = $('#txtValor').val();
			
			$.ajax({
				method: "POST",
				url: "capturarExcecao",
				data: { valorParam : valorInformado}
			}).done(function(response) {
				alert("Sucesso: " + response);
			}).fail(function(xhr, status, errorThrown) {
				alert("Error: " + xhr.responseText);
			});
		}
	</script>
</html>