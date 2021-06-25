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
<% int esitoAmore = 2; %>
<% List<String> interessi = (List<String>) request.getAttribute("listint"); %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Profilo Utente</title>
		<script src="../js/jquery.min.js"></script>
		<script src="../js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="../stili/bootstrap.min.css">
		<link rel="stylesheet" href="../stili/Dashboard.css">
	</head>

	<body>
		
		<!-- BARRA DI NAVIGAZIONE -->
		<%@include file="menuIn.html"%>

		<!-- CONTENUTO PAGINA -->

		<div class="page-content">

			<!-- SEZIONE FISSA-->
			<div class="fixed-sidebar" id="fixed">

				<div class="fixed-sidebar-content" id="profilo">

					<div id="schedaprofilo">
						<%@include file="/common/profilobar.jsp"%>

					<!-- BOTTONE MODIFICA PROFILO -->
					<a class="btn btn-warning" id="modificaprofilo"
						href="/profilo/formmodificaprofilo" role="button">Modifica
						profilo</a>

				</div><!-- Fine di schedaprofilo -->

				</div><!-- Fine fixed-sidebar-content -->
			</div><!-- Fine fixed-sidebar -->

			<!-- SEZIONE NON FISSA -->
			<div class="nofixed-sidebar" id="nofixed">
			
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
								<form action="/videogiochi/rimuoviora" method="get">
									
									    <div style="position:relative;">
									    <input type="hidden" name="idvgora" value="<%= ov.getId() %>">
									        <button type="submit" class="close AClass">
									           <span>&times;</span>
									        </button>
									       <%= (	ov.getImmagine() == null 	?
										"<img id='AoE' class='img-fluid' src='/foto/AoE.png'>"
																	:
										"<img id='AoE' class='img-fluid' src='"+ ov.getImmagine() + "'>") %>
										<p>
										<b><%= ov.getNome() %></b><br>
										Genere:
										<%= ov.getGenere() %></p>
									    </div>
									</form>

							</div>

						<% }%>
						<% if(oraVg.size() < 3)
							out.println("<a href='/videogiochi/cercavgora'><img id='nondisponibile' src='/foto/giocopiu.png'></a>"); %>

					</div>  <!-- fine lista-adesso -->




					<div>
					<h1>Giochi preferiti</h1>
					</div>
					</div>
					<div class="lista-preferiti">
					<!-- GIOCA ORA -->
						<%
						for(Videogioco vg : lVg)
						{ %>


							<div class="giochi-preferiti">
								<form action="/videogiochi/rimuovi" method="get">
								
									    <div style="position:relative;">
									    <input type="hidden" name="idvgpref" value="<%= vg.getId() %>">
									        <button type="submit" class="close AClass">
									           <span>&times;</span>
									        </button>
									       <%= (	vg.getImmagine() == null 	?
										"<img id='AoE' class='img-fluid' src='/foto/AoE.png'>"
																	:
										"<img id='AoE' class='img-fluid' src='"+ vg.getImmagine() + "'>") %>
										<p>
										<b><%= vg.getNome() %></b><br>
										Genere:
										<%= vg.getGenere() %></p>
									    </div>
									</form>

							</div>

						<% }%>
						<% if(lVg.size() < 5)
							out.println("<a href='/videogiochi/cercavg'><img id='nondisponibile' src='/foto/giocopiu.png'></a>"); %>


						</div>  <!-- fine lista-preferiti -->

			<!--  ------------------------------------------------------------------------------------------------------------- -->





							<!--  ------------------------------------------------------------------------------------------------------------- -->



					<!-- PIATTAFORMA -->
					<div>
						<h3>Le tue piattaforme:</h3>
					</div>
					<div class="lista-preferiti">
						<%
						for(Piattaforma pt : lPlat)
						{ %>
							<div class="giochi-preferiti">
								<form action="/piattaforma/rimuovi" method="get">
								 <div style="position:relative;">
									    <input type="hidden" name="idplat" value="<%= pt.getId() %>">
									        <button type="submit" class="close BClass">
									          <span>&times;</span>
									          </button>
									          	<img id='AoE' class='img-fluid' src='/foto/platf.jpg'>
									          <p>
											<b><%= pt.getPiattaforma() %></b><br>
											<br>
											Produttore:
											<%= pt.getProduttore() %>

									    </div>
									</form>

							</div>

						<% }%>
						<% if(lPlat.size() < 3)
						out.println("<a href='/piattaforma/cercapiatt'><img id='nondisponibile' src='/foto/giocopiu.png'></a>"); %>

						</div>			          <!-- fine lista-preferiti PIATTAFORMA -->
					
					<!-- INTERESSI -->
					<h5>I tuoi interessi:</h5>
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
					
				
			</div><!-- Fine nofixed-sidebar-content  -->
	
			
			</div><!-- Fine nofixed-sidebar -->


		</div><!-- Fine page-content -->
		
		<%@include file="/common/footer.html"%> <!-- FOOTER -->
	</body>
	
</html>
