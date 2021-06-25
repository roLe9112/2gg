<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%@ page import="com.generation.GG.entities.Profilo"%>


<% Profilo p = (Profilo) request.getAttribute("schedaprofilo"); %>

<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Modifica il tuo profilo</title>
		<script src="../js/jquery.min.js"></script>
		<script src="formmodificaprofilo.js"></script>
		<script src="../js/bootstrap.min.js"></script>
		<link rel="stylesheet" href="../stili/bootstrap.min.css">
		<link rel="stylesheet" href="../stili/Dashboard.css">
	</head>

	<body>
	
		<%@include file="menuIn.html"%>
	
	
		<!-- CONTENUTO PAGINA -->
	
		<div class="page-content">
	
			<!-- SEZIONE FISSA-->
			<div class="fixed-sidebar" id="fixed">
	
				<div class="fixed-sidebar-content" id="profilo">
					<!-- Qui deve apparire la scheda profilo -->
					<h1>Modifica il tuo avatar</h1>
					<%@include file="avatar.jsp"%>
				</div>
				<!-- Fine fixed-sidebar-content -->
			</div>
			<!-- Fine fixed-sidebar -->
	
			<!-- SEZIONE NON FISSA -->
			<div class="nofixed-sidebar" id="nofixed">
				<div class="nofixed-sidebar-content">
					<h2>Qui puoi puoi modificare il tuo profilo</h2>
					<form action="modificaprofilo" method="post">
	
	
						<div class="form-row">
	
							<div class="form-group col-md-6">
								<label for="cambiaNick">Nickname</label> <input name="nick"
									type="text" class="form-control" id="changeNick"
									aria-describedby="nickHelp" value="<%= p.getNick() %>"
									onfocus="if(this.value=='<%= p.getNick() %>')this.value=''"
									onblur="if(this.value=='')this.value='<%= p.getNick() %>'" /> <small
									id="emailHelp" class="form-text text-muted">Puoi
									cambiare nick quanto e come vuoi</small>
							</div>
	
							<div class="form-group col-md-6">
								<label for="telNumber">Numero di telefono</label> <input
									type="text" class="form-control" id="telNumber"
									name="numerotelefono" 
									<% if(!(p.getNumeroTelefono() == null)) { %>
																			value="<%= p.getNumeroTelefono() %>"
									onfocus="if(this.value != null'<%= p.getNumeroTelefono() %>')this.value=''"
									onblur="if(this.value=='')this.value='<%= p.getNumeroTelefono() %>'" <% }  %>/>
										
										
								

	
							</div>
	
						</div>
						<!-- FINE RIGA 1 -->
	
						<div class="form-row">
							<div class="form-group col-md-6">
								<label for="citta">Città</label> <input type="text"
									class="form-control" id="telNumber" name="citta"
									<% if(!(p.getCitta() == null)) { %>
																			value="<%= p.getCitta() %>"
									onfocus="if(this.value != null'<%= p.getCitta() %>')this.value=''"
									onblur="if(this.value=='')this.value='<%= p.getCitta() %>'" <% }  %>/>
							</div>
	
							<div class="form-group col-md-6">
								<label for="citta">Provincia</label> <input type="text"
									class="form-control" id="telNumber" name="provincia"
									<% if(!(p.getProvincia() == null)) { %>
																			value="<%= p.getProvincia() %>"
									onfocus="if(this.value != null'<%= p.getProvincia() %>')this.value=''"
									onblur="if(this.value=='')this.value='<%= p.getProvincia() %>'" <% }  %>/>
							</div>
	
	
						</div>
						<!-- FINE RIGA 2 -->
	
	
						<div class="form-group">
							<label for="bio">BIO</label>
							<textarea class="form-control" id="biografia" name="bio" rows="6"><%=p.getBio()%></textarea>
						</div>
						<!--  FINE BIO -->
	
						<div class="form-row">
							<div class="form-group col-md-6">
								<div class="form-check">
	
									<input class="form-check-input" type="checkbox" value="1"
										id="amoree" name="amore" <% if (p.isAmore()) { %>
										checked="true" <% }  %>> <label
										class="form-check-label" for="amore"> Amore </label>
	
	
								</div>
							</div>
	
							<div class="form-group col-md-6">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="1"
										id="tipogamer" name="tipogamer" <% if (p.isTipoGamer()) { %>
										checked="true" <% }  %>> <label
										class="form-check-label" for="tipogamer"> Hardcore
										Gamer? </label>
	
								</div>
							</div>
						</div> <!-- FINE RIGA 3 -->
	
						<div class="form-row">
							<div class="form-group" id="orientamentoo">
								<label for="exampleFormControlSelect1">In cerca di?</label> <select
									class="form-control" name="idorientamento" id="idorientamento">
									<option selected="selected">
										<%
														switch(p.getIdOrientamento()) {
														case 1:
															out.println("Uomo");
															break;
														case 2:
															out.println("Donna");
															break;
														case 3:
															out.println("Altro");
															break;
														case 5:
															out.println("Entrambi");
															break;		
														}
													%>
	
	
									</option>
									<option
										<%
		    									   if(p.getIdOrientamento()== 1)  {
											    %>
										style="display: none">
										<%
											        } else {
											    %> value="1"
										<%
											        }
											    %>>Uomo
									</option>
	
	
									<option
										<%
		    									   if(p.getIdOrientamento()== 2)  {
											    %>
										style="display: none">
										<%
											        } else {
											    %> value="2"
										<%
											        }
											    %>>Donna
									</option>
	
	
									<option
										<%
		    									   if(p.getIdOrientamento()== 3)  {
											    %>
										style="display: none">
										<%
											        } else {
											    %> value="3"
										<%
											        }
											    %>>Altro
									</option>
	
									<option
										<%
		    									   if(p.getIdOrientamento()== 5)  {
											    %>
										style="display: none">
										<%
											        } else {
											    %> value="5"
										<%
											        }
											    %>>Entrambi
									</option>
	
								</select>
	
							</div>	
	
						</div>	<!-- FINE RIGA 4 ORIENTAMENTO -->
						
						<div class="form-row">
							<div class="form-group col-md-6">
							<h5>I tuoi interessi:</h5>
							</div>
						</div>
						
						<div class="form-row">
							<div class="form-group col-md-6">
								<div class="form-check">
									<input class="form-check-input" type="hidden" value="0" name="listinteressi"  >
									<input class="form-check-input" type="checkbox" value="1" name="listinteressi"  > 
									<label class="form-check-label" for="listinteressi">Cantare</label>
	
	
								</div>
							</div>
	
							<div class="form-group col-md-6">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="2" name="listinteressi" > 
									<label class="form-check-label" for="listinteressi">Cucinare</label>
	
								</div>
							</div>
						</div> <!-- FINE RIGA 5 -->
						
						<div class="form-row">
							<div class="form-group col-md-6">
								<div class="form-check">
	
									<input class="form-check-input" type="checkbox" value="3" name="listinteressi"  > 
									<label class="form-check-label" for="listinteressi">Danzare</label>	
	
	
								</div>
							</div>
	
							<div class="form-group col-md-6">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="4" name="listinteressi"  > 
										<label class="form-check-label" for="listinteressi">Dipingere</label>	
	
								</div>
							</div>
						</div> <!-- FINE RIGA 6 -->
						
						<div class="form-row">
							<div class="form-group col-md-6">
								<div class="form-check">
	
									<input class="form-check-input" type="checkbox" value="5"  name="listinteressi"  >
									<label class="form-check-label" for="listinteressi"> Guardare Film/Serie Tv/Anime </label>	
	
	
								</div>
							</div>
	
							<div class="form-group col-md-6">
								<div class="form-check">
									
									<input class="form-check-input" type="checkbox" value="6"  name="listinteressi"  > 
									<label class="form-check-label" for="listinteressi"> Fare Foto </label>		
	
								</div>
							</div>
						</div> <!-- FINE RIGA 7 -->
						
						<div class="form-row">
							<div class="form-group col-md-6">
								<div class="form-check">
	
									<input class="form-check-input" type="checkbox" value="7"  name="listinteressi"  > 
									<label class="form-check-label" for="listinteressi"> Leggere Manga/Fumetti </label>		
	
	
								</div>
							</div>
	
							<div class="form-group col-md-6">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="8"  name="listinteressi"  > 
									<label class="form-check-label" for="listinteressi"> Leggere Libri </label>	
	
								</div>
							</div>
						</div> <!-- FINE RIGA 8 -->
						
						
						<div class="form-row">
							<div class="form-group col-md-6">
								<div class="form-check">
	
									<input class="form-check-input" type="checkbox" value="9"  name="listinteressi"  > 
									<label class="form-check-label" for="listinteressi"> Visitare Musei </label>	
	
	
								</div>
							</div>
	
							<div class="form-group col-md-6">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="10"  name="listinteressi"  > 
									<label class="form-check-label" for="listinteressi"> Ascoltare Musica </label>	
	
								</div>
							</div>
						</div> <!-- FINE RIGA 9 -->
						
						<div class="form-row">
							<div class="form-group col-md-6">
								<div class="form-check">
	
									<input class="form-check-input" type="checkbox" value="11"  name="listinteressi"  > 
									<label class="form-check-label" for="listinteressi"> Pitturare  </label>
	
	
								</div>
							</div>
	
							<div class="form-group col-md-6">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="12"  name="listinteressi"  > 
									<label class="form-check-label" for="listinteressi"> Recitare </label>	
	
								</div>
							</div>
						</div> <!-- FINE RIGA 10 -->
						
						<div class="form-row">
							<div class="form-group col-md-6">
								<div class="form-check">
	
									<input class="form-check-input" type="checkbox" value="13"  name="listinteressi"  > 
									<label class="form-check-label" for="listinteressi"> Scolpire </label>	
	
	
								</div>
							</div>
	
							<div class="form-group col-md-6">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="14"  name="listinteressi"  > 
									<label class="form-check-label" for="listinteressi"> Scrivere </label>		
	
								</div>
							</div>
						</div> <!-- FINE RIGA 11 -->
						
						<div class="form-row">
							<div class="form-group col-md-6">
								<div class="form-check">
	
									<input class="form-check-input" type="checkbox" value="15"  name="listinteressi"  > 
									<label class="form-check-label" for="listinteressi"> Fare Sport  </label>
	
	
								</div>
							</div>
	
							<div class="form-group col-md-6">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="16"  name="listinteressi"  > 
									<label class="form-check-label" for="listinteressi"> Suonare </label>		
	
								</div>
							</div>
						</div> <!-- FINE RIGA 12 -->
						
						<div class="form-row">
							<div class="form-group col-md-6">
								<div class="form-check">
	
									<input class="form-check-input" type="checkbox" value="17"  name="listinteressi"  > 
									<label class="form-check-label" for="listinteressi"> Andare a Teatro  </label>	
	
	
								</div>
							</div>
	
							<div class="form-group col-md-6">
								<div class="form-check">
									<input class="form-check-input" type="checkbox" value="18"  name="listinteressi"  > 
									<label class="form-check-label" for="listinteressi"> Viaggiare </label>		
	
								</div>
							</div>
						</div> <!-- FINE RIGA 13 -->
						
						<button class="btn btn-info" type="submit">Modifica</button>
						<a href="/profilo/">
							<button type="button" class="btn btn-secondary">Indietro</button>
						</a>
					</form>
	
				</div>
				<!-- Fine nofixed-sidebar-content -->
			</div>
			<!-- Fine nofixed-sidebar -->
	
	
		</div>
		<!-- Fine Pagina -->
	
	</body>
</html>




