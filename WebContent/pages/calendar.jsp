<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>   

<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>Celendar jQuery</title>
		<link href='../css/fullcalendar.min.css' rel='stylesheet' />
		<link href='../css/fullcalendar.print.min.css' rel='stylesheet' media='print' />
		<script src='../js/moment.min.js'></script>
		<script src='../js/jquery.min.js'></script>
		<script src='../js/fullcalendar.min.js'></script>	
		
		<style>
			body {
				margin: 40px 10px;
				padding: 0;
				font-family: "Lucida Grande",Helvetica,Arial,Verdana,sans-serif;
				font-size: 14px;
			}
		
			#calendar {
				max-width: 900px;
				margin: 0 auto;
			}
		
		</style>	
	</head>
	
	<body>
		<a href="../index.jsp">Página Inicial</a><br/>
		<h3>Calendar</h3>	
		
		<div id='calendar'></div>
	</body>	
	
	<script>
		$(document).ready(function() {			
			$.get("buscarCalendarioDatas", function(response) {		
				var datas = JSON.parse(response);
				//datas = { title: 'All Day Event', start: '2017-02-01' };
				$('#calendar').fullCalendar({
					header: {
						left: 'prev,next today',
						center: 'title',
						right: 'month,basicWeek,basicDay'
					},
					defaultDate: '2017-02-12',
					navLinks: true, // can click day/week names to navigate views
					editable: true,
					eventLimit: true, // allow "more" link when too many events
					events: datas					
				});			
			});			
		});	
	</script>
	
</html>