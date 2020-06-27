<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>   

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>DataTable jQuery</title>
		<link rel="stylesheet" href="http://cdn.datatables.net/1.10.20/css/jquery.dataTables.min.css">
		<script src="https://code.jquery.com/jquery-3.3.1.js"></script>
		<script src="http://cdn.datatables.net/1.10.20/js/jquery.dataTables.min.js"></script>		
	</head>
	
	<body>
		<a href="../index.jsp">Página Inicial</a><br/>
		<table id="mytable" class="display" style="width:100%">
	        <thead>
	            <tr>
	                <th>ID</th>
	                <th>Login</th>	               
	            </tr>
	        </thead>	       
    	</table>		
	</body>	
	
	<script type="text/javascript">
		$(document).ready(function() {
		    $('#mytable').DataTable( {
		        "processing": true,
		        "serverSide": true,
		        "ajax": "carregarDadosDataTable"
		    });
		});
	</script>
	
</html>