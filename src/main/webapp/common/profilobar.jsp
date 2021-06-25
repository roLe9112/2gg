<div class="soprafoto">

	<img id="diamante" src="/foto/diamante.png" title="Profilo"><strong> <%= p.getNick() %></strong>
	<img id="stella" src="/foto/stella.png" title="Eta'"><strong><%= p.getEta() %></strong>
	<%
									switch(p.getIdSesso()) {
									case 1:
										out.println("<img id='sessouomo' title='Sono uomo' src='/foto/sessouomo.png'>");
										break;
									case 2:
										out.println("<img id='sessodonna' title='Sono donna' src='/foto/sessodonna.png'>");
										break;
									case 3:
										out.println("<img id='sessoneutro' title='Sono altro' src='/foto/neutro.png'>");
										break;
									case 4:
										out.println("<img id='sessoneutro' title='Non specifico' src='/foto/neutro.png'>");
										break;	
									}
								%>
</div>
<!--  Fine soprafoto -->
													
<div class="foto">
	<a href="/profilo/formmodificaprofilo"><img id="fotoprofilo" src="<%=request.getAttribute("linkimmagine")%>"></a>
	

	<div class="cerco-amore">
		<p>
        <%
            if (esitoAmore != 2)
            {
                if (esitoAmore == 1)
                {
                    out.println("<img id='cuore' title='In cerca di relazione' src='/foto/cuore.png'>");
                }
                else
                {
                    out.println("<img id='joystick' title='In cerca di teammates' src='/foto/joystick.png'>");
                }
            }
            else
            {
                if (p.isAmore())
                {
                    out.println("<img id='cuore' title='In cerca di relazione' src='/foto/cuore.png'>");
                }
                else
                {
                    out.println("<img id='joystick' title='In cerca di teammates' src='/foto/joystick.png'>");
                }
            }
        %>
        </p>
	</div>

	<div class="orientamento">
        <p>
            <%
                if (esitoAmore != 2)
                {
                    if (esitoAmore == 1)
                    {
                        switch(p.getIdOrientamento()) {
                        case 1:
                            out.println("<img id='uomo' title='Cerco uomo' src='/foto/uomo.png'>");
                            break;
                        case 2:
                            out.println("<img id='donna' title='Cerco donna' src='/foto/donna.png'>");
                            break;
                        case 3:
                            out.println("<img id='genderless' title='Cerco altro' src='/foto/genderless.png'>");
                            break;
                        case 5:
                            out.println("<img id='bisex' title='Cerco entrambi' src='/foto/bisex.png'>");
                            break;
                        }
                    }
                }
                else
                {
                    if (p.isAmore())
                    {
                        switch(p.getIdOrientamento()) {
                        case 1:
                            out.println("<img id='uomo' title='Cerco uomo' src='/foto/uomo.png'>");
                            break;
                        case 2:
                            out.println("<img id='donna' title='Cerco donna' src='/foto/donna.png'>");
                            break;
                        case 3:
                            out.println("<img id='genderless' title='Cerco altro' src='/foto/genderless.png'>");
                            break;
                        case 5:
                            out.println("<img id='bisex' title='Cerco entrambi' src='/foto/bisex.png'>");
                            break;
                        }
                    }
                }
                                %>
        </p>

    </div>


</div>
<!--  Fine foto -->

<div class="sottofoto">

	<div class="sezione-immagini">

		<img id="gamer" src="/foto/gamer.png"> 
		<img id="bio" src="/foto/bio.png"> 
		<% if(!(p.getNumeroTelefono() == null || (p.getNumeroTelefono().equals("")))) { if (p.getId() == idSessione) { out.println("<img id='telefono' src='/foto/telefono.png'>");} }%> 
		<img id="citta" src="/foto/citta.png">
		<img id="provincia" src="/foto/provincia.png">

	</div>
	<!--  fine sezione-immagini -->

	<div class="sezione-testo">

		<p>
			Tipo gamer: <%= p.isTipoGamer() ? "Hardcore" : "Casual" %></p>
		<p>
			BIO:
			<%= (p.getBio() == null ? "<i>Aggiungila!</i>" : p.getBio()) + "" %></p>
			
		
		<% if(!(p.getNumeroTelefono() == null || (p.getNumeroTelefono().equals("")))) { if (p.getId() == idSessione) { out.println("<p>Tel:" + p.getNumeroTelefono() + "</p>");} }%>
		
				
		
		<p>
			Citta':
			<%= (p.getCitta() == null ? "<i>Aggiungila!</i>" : p.getCitta()) + "" %></p>
		<p>
			Provincia:
			<%= (p.getProvincia() == null ? "<i>Aggiungila!</i>" : p.getProvincia()) + "" %></p>
			
	</div>
	<!--  fine sezione-testo -->
</div>
<!--  Fine sottofoto -->