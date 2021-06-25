<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!-- Importiamo tutte le entities -->
<%@ page import="java.util.List" %>
<%@ page import="com.generation.GG.entities.*" %>
<!-- Creiamo poi per prima cosa l'oggetto Videogioco leggendo dal Model -->
<% List<Videogioco> vgs = (List<Videogioco>) request.getAttribute("listavg"); %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Risultato ricerca videogiochi</title>
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

				<div id="camporicerca">
					<h2>Aggiungi videogiochi</h2>
					
					<form action="/videogiochi/risultato" method="get">
						<input type="text" size="40" name="namevg" placeholder="Cerca qui il videogioco">
						<button type="submit" class="btn btn-success">Cerca</button>
					</form>	
	
				</div>
				
				<div>
				   <h2>Risultati:</h2>
				</div>
							
				<div class="lista-preferiti" style="margin-left:70px;">							
							
							<%	for(Videogioco v : vgs)
											{%>
								<div class="giochi-preferiti">
    											
									<form action="/videogiochi/aggiungi" method="get">
									
										<!-- Stampiamo tutti i dettagli del videogioco -->
										<%= (v.getImmagine() == null 	?
												"<img id='AoE' class='img-fluid' src='/foto/AoE.png'>" 
																		:								
												"<img id='AoE' class='img-fluid' src='"+ v.getImmagine() + "'>") %>
										<br>
										<p>
											<b><%= v.getNome() %></b><br>
											Genere:
											<%= v.getGenere() %>
										</p>
										<input type="hidden" name="idvgscelto" value="<%= v.getId() %>">
										<button type="submit" class="btn btn-info">Aggiungi</button>
									</form>
									
								</div>
							<% }%>		
				</div> <!-- Fine div lista-preferiti -->


			</div><!-- Fine di schedaprofilo -->
					
				<!-- Fine fixed-sidebar-content -->
			</div>
			<!-- Fine fixed-sidebar -->

			<!-- SEZIONE NON FISSA -->
				<div class="nofixed-sidebar" id="nofixed">
					<div class="nofixed-sidebar-content">
						
						<div class="nofixed-sidebar-content">
							
							
							
							
						</div> <!-- Fine nofixed-sidebar-content -->
				</div> <!-- Fine nofixed-sidebar -->	
		</div><!-- Fine Pagina -->
		
		<%@include file="/common/footer.html"%> <!-- FOOTER -->
	</body>
	
	
	
	
</html>
