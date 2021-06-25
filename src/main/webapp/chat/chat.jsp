<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
 <%@ page import="com.generation.GG.entities.*"%>
 <%@ page import="com.generation.GG.dao.*"%>
<%@ page import="java.util.*" %>
<% List<Profilo> dp = (List<Profilo>) request.getAttribute("elencoPersone"); %>

    
<!DOCTYPE html>
<html>
	<head>
		<meta charset="ISO-8859-1">
		<title>2GG - Chat</title>
		<script src="../js/jquery.min.js"></script>
		<!-- <script src="js/Dashboard.js"></script> -->
		<script src="../js/bootstrap.min.js"></script>
		<script src="functionsjs.js"></script>
		<link rel="stylesheet" href="../stili/bootstrap.min.css">
		<link rel="stylesheet" href="../stili/Dashboard.css">
		<link rel="stylesheet" href="stile.css">	
	</head>
	
	<body>
		<!-- BARRA DI NAVIGAZIONE -->
		<%@include file="/profilo/menuIn.html"%>
	
	<!-- CONTENUTO PAGINA -->

	<div class="container">
	
		<div class="messaging">
		      <div class="inbox_msg">
		      <!-- LISTA CONTATTI !!!!-->
		        <div class="inbox_people">
		          <div class="headind_srch">
		            <div class="recent_heading">
		              <h4>Lista Match</h4><img id="chatta" src="/foto/chatta.png">
		              
		            </div><!-- fine recent_heading -->
		          </div><!-- fine headind_srch -->
		          
		          <div class="inbox_chat">
		            <div class="chat_list active_chat">
		              <div class="chat_people">
		                <div class="chat_ib">
		                  	
								  <ol class="list-group list-group-flush">
								   <% for(Profilo p : dp) { %>
									    <li class="list-group-item" id="elemento" > 
										   <form action="chatSingola" method="get">
									    		<button id="utente" onclick="location.href='msgTo'" value="<%= p.getId()%>" name="idDestinatario">
									    			<%= p.getNick() %>
									    		</button>
									    	</form>
									    	<a style="text-decoration: none;" href="/chat/profilo?profilo=<%= p.getId() %>"><button type="button" class="btn btn-info btn-sm">Visualizza profilo</button></a>
										    	
									    	
									    </li>
								     <% } %>
								  </ol>
							
		                </div><!-- fine chat_ib -->
		              </div><!-- fine chat_people -->
		            </div><!--  fine chat_list_active_chat -->
		          </div><!-- fine inbox_chat -->
		        </div><!-- fine inbox_people -->
		        
		        <!-- SEZIONE CHAT !!!!!-->
		        <div class="mesgs">

		          <!-- SEZIONE INVIA MESSAGGIO -->

					          
			 </div><!-- fine msgs -->

		   </div><!-- fine inbox_msg -->
		 </div><!-- fine messaging -->
	  </div><!-- fine container -->
	</body>
</html>