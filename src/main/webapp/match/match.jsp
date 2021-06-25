<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="java.util.*"%>
<%@ page import="com.generation.GG.entities.*"%>

<% Profilo p = (Profilo) request.getAttribute("schedaprofilo"); %>
<% List<Piattaforma> lPlat = (List<Piattaforma>) request.getAttribute("lsplat"); %>
<% List<Videogioco> lVg = (List<Videogioco>) request.getAttribute("lsgiochipref"); %>
<% List<Videogioco> oraVg = (List<Videogioco>) request.getAttribute("lsgiocoora"); %>
<% String link = (String) request.getAttribute("linkicona"); %>
<% int idSessione = Integer.parseInt(session.getAttribute("idProfilo") + ""); %>
<% int esitoAmore = Integer.parseInt(session.getAttribute("esitoamore") + ""); %>
<% List<String> interessi = (List<String>) request.getAttribute("listint"); %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>2GG - Richieste di match </title>
		<script src="../js/jquery.min.js"></script>
		<script src="../js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="../stili/bootstrap.min.css">
		<link rel="stylesheet" href="../stili/Dashboard.css">
	</head>

<body>

	<%@include file="../profilo/menuIn.html"%>

	<!-- CONTENUTO PAGINA -->

	<div class="page-content">

		<!-- SEZIONE FISSA-->
		<div class="fixed-sidebar" id="fixed">
			<h4>Proposta di match con:</h4>
			<div id="schedaprofilo">

				<%@include file="/common/profilobar.jsp"%>

				<!-- BOTTONI -->
				<div class="form-row">
					<div class="form-group col-md-6">
						<a href="/match/accetta"><img id="accetta" title="Accetta"
							src="/foto/accetta.png"></a>
					</div>

					<div class="form-group col-md-6">
						<a href="/match/rifiuta"><img id="rifiuta" title="Rifiuta"
							src="/foto/rifiuta.png"></a>
					</div>

				</div>


			</div>
			<!-- Fine di schedaprofilo -->

			<!-- Fine fixed-sidebar-content -->
		</div>
		<!-- Fine fixed-sidebar -->

		<!-- SEZIONE NON FISSA -->
		<div class="nofixed-sidebar" id="nofixed">
			<div class="nofixed-sidebar-content">

				<div class="nofixed-sidebar-content">

					<div>
						<h1>Giocati adesso</h1>
					</div>

					<div class="lista-adesso">
						<!-- GIOCA ORA -->
						<%
					for(Videogioco ov : oraVg) 
					{ %>
						<div class="giochi-adesso">

							<%= (	ov.getImmagine() == null 	?
									"<img id='AoE' class='img-fluid' src='/foto/AoE.png'>" 
																:								
									"<img id='AoE' class='img-fluid' src='"+ ov.getImmagine() + "'>") %>
							<p>
								<b><%= ov.getNome() %></b><br> Genere:
								<%= ov.getGenere() %></p>

						</div>

						<% }%>
					</div>
					<!-- fine lista-adesso -->

					<div>
						<h1>Giochi preferiti</h1>
					</div>
					<div class="lista-preferiti">
						<!-- GIOCHI PREFERITI -->
						<%
				for(Videogioco vg : lVg) 
				{ %>
						<div class="giochi-preferiti">
							<%= (	vg.getImmagine() == null 	?
								"<img id='AoE' class='img-fluid' src='/foto/AoE.png'>" 
															:								
								"<img id='AoE' class='img-fluid' src='"+ vg.getImmagine() + "'>") %>
							<p>
								<b><%= vg.getNome() %></b><br> Genere:
								<%= vg.getGenere() %></p>

						</div>

						<% }%>
					</div>
					<!-- fine lista-preferiti -->

					<!-- PIATTAFORMA -->
					<div>
						<h3>Le sue piattaforme:</h3>
					</div>

					<div class="lista-preferiti">
						<%
					for(Piattaforma pt : lPlat) 	
					{ %>
						<div class="giochi-preferiti">

							<!-- Stampiamo tutti i dettagli della Videogioco -->
							<img id='AoE' class='img-fluid' src='/foto/platf.jpg'>
							<p>
								<b> <%= pt.getPiattaforma() %>
								</b>
							</p>
							<br> Produttore:
							<%= pt.getProduttore() %>

						</div>
						<% }%>
					</div>
					<!-- fine lista-preferiti (PIATTAFORMA) -->
					
					<!-- INTERESSI -->
					<h5>I suoi interessi:</h5>
					<div class="lista-preferiti">
						<%
						for(String i : interessi) 	
						{ %>
							<div class="int-preferiti">
								
									<!-- Stampiamo tutti i dettagli della Videogioco -->
									<img id='imminters' class='img-fluid' src='/foto/interesse.png'>
									<p>
										<b>
											<%= i %>
										</b>
									</p>
									<br> 
							</div>
						<% }%>
					
				</div> <!-- fine lista-preferiti INTERESSI -->

				</div>
				<!-- Fine nofixed-sidebar-content -->
			</div>
			<!-- Fine nofixed-sidebar -->

		</div>
	</div>
	<!-- Fine Pagina -->

	<%@include file="/common/footer.html"%>
</body>

</html>
