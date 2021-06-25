<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Importiamo tutte le entities -->
<%@ page import="java.util.List" %>
<%@ page import="com.generation.GG.entities.*" %>
<!-- Creiamo poi per prima cosa l'oggetto Videogioco leggendo dal Model -->


<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Cerca Videogiochi</title>
		<script src="../js/jquery.min.js"></script>
		<script src="../js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="../stili/bootstrap.min.css">
		<link rel="stylesheet" href="../stili/Dashboard.css">
	
	</head>

	<body>

		<%@include file="../profilo/menuIn.html"%>

		<!-- CONTENUTO PAGINA -->

		<div class="page-content" style="display:flex; flex-direction:column;">

			<!-- SEZIONE FISSA-->
			<div class="fixed-sidebar" id="fixed" style="align-items:center;">

				<div id="schedaprofilo">
					<h2>Ricerca videogiochi</h2>
					
					<form action="/videogiochi/risultato" method="get">
						<input type="text" size="30" name="namevg">
						<button type="submit" class="btn btn-success">Cerca</button>
					</form>	
	
				</div>


			</div><!-- Fine di schedaprofilo -->
					
				<!-- Fine fixed-sidebar-content -->
			</div>
			<!-- Fine fixed-sidebar -->

			<!-- SEZIONE NON FISSA -->
				<div class="nofixed-sidebar" id="nofixed">
					<div class="nofixed-sidebar-content">
						
						<div class="nofixed-sidebar-content">
							
								
							
						
						</div> <!-- Fine nofixed-sidebar-content -->
				</div>
				<!-- Fine nofixed-sidebar -->
	
				</div>
		</div><!-- Fine Pagina -->
		
		<%@include file="/common/footer.html"%>
	</body>
	

</html>
