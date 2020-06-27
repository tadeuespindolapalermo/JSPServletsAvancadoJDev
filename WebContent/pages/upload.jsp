<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>

	<head>
		<meta charset="ISO-8859-1">
		<title>Upload</title>	
		<script type="text/javascript" src="https://code.jquery.com/jquery-3.1.1.min.js"></script>
	</head>
	
	<body>
		<a href="../index.jsp">Página Inicial</a><br/>	
		<h3>Upload</h3>			
		
		<form enctype="application/x-www-form-urlencoded">
			<input type="file" id="file" name="file" onchange="uploadFile();" />
			<img alt="Imagem" src="" id="target" width="200" height="200">
		</form>
		
		<br /><br />
		<a href="fileUpload?acao=carregar">Carregar imagens</a>
		<br /><br />
		
		<table>
			<c:forEach items="${imagens}" var="img">
				<tr>					
					<td>${img.id}</td>					
					<td><a target="_blank" href="fileUpload?acao=download&idImagem=${img.id}">Download</a></td>
				</tr>
			</c:forEach>
		</table>
		
	</body>
	
	<script type="text/javascript">
		function uploadFile() {
			var target = document.querySelector("img");
			var file = document.querySelector("input[type=file]").files[0];
			
			var reader = new FileReader();
			
			reader.onloadend = function () {
				
				target.src = reader.result;
				
				// Upload Ajax
				$.ajax({
					method: "POST",
					url: "fileUpload",
					data: { 
						fileUpload: reader.result
					}
				})
				.done(function(response) {
					alert("Sucesso: " + response);
				})
				.fail(function(xhr, status, errorThrown) {
					alert("Error: " + xhr.responseText);
				});
			};
			
			if (file) {						
				reader.readAsDataURL(file);				
			} else {
				target.src = "";
			}
		}
	
	</script>
</html>