<form action="modificaavatar" method="post">

	
	<div class="form-row">

		<div class="form-group col-md-4">
			<div class="form-check">
				<img id="fotoprofilo-mini" src="https://i.imgur.com/6wPgk2x.png">
				<input class="form-check-input" type="radio" name="idiconaprofilo"
					id="exampleRadios1" value="1" <% if(p.getIdIconaProfilo() == 1){ out.println("checked");} %> >
			</div>
		</div>

		<div class="form-group col-md-4">
			<div class="form-check">
				<img id="fotoprofilo-mini" src="https://i.imgur.com/AA7ktNw.png">
				<input class="form-check-input" type="radio" name="idiconaprofilo"
					id="exampleRadios2" value="2" <% if(p.getIdIconaProfilo() == 2){ out.println("checked");} %>>
			</div>
		</div>

		<div class="form-group col-md-4">
			<div class="form-check">
				<img id="fotoprofilo-mini" src="https://i.imgur.com/EEnnXSK.png">
				<input class="form-check-input" type="radio" name="idiconaprofilo"
					id="exampleRadios1" value="3" <% if(p.getIdIconaProfilo() == 3){ out.println("checked");} %>>
			</div>
		</div>
	</div>

	<div class="form-row">

		<div class="form-group col-md-4">
			<div class="form-check">
				<img id="fotoprofilo-mini" src="https://i.imgur.com/6Y7XpoK.png">
				<input class="form-check-input" type="radio" name="idiconaprofilo"
					id="exampleRadios1" value="4" <% if(p.getIdIconaProfilo() == 4){ out.println("checked");} %>>
			</div>
		</div>

		<div class="form-group col-md-4">
			<div class="form-check">
				<img id="fotoprofilo-mini" src="https://i.imgur.com/krxUjUL.png">
				<input class="form-check-input" type="radio" name="idiconaprofilo"
					id="exampleRadios2" value="5" <% if(p.getIdIconaProfilo() == 5){ out.println("checked");} %>>
			</div>
		</div>

		<div class="form-group col-md-4">
			<div class="form-check">
				<img id="fotoprofilo-mini" src="https://i.imgur.com/aoXwoYv.png">
				<input class="form-check-input" type="radio" name="idiconaprofilo"
					id="exampleRadios1" value="6" <% if(p.getIdIconaProfilo() == 6){ out.println("checked");} %>>
			</div>
		</div>
	</div>

	<div class="form-row">

		<div class="form-group col-md-4">
			<div class="form-check">
				<img id="fotoprofilo-mini" src="https://i.imgur.com/zzUkzOd.png">
				<input class="form-check-input" type="radio" name="idiconaprofilo"
					id="exampleRadios1" value="7" <% if(p.getIdIconaProfilo() == 7){ out.println("checked");} %>>
			</div>
		</div>

		<div class="form-group col-md-4">
			<div class="form-check">
				<img id="fotoprofilo-mini" src="https://i.imgur.com/zY1Ni7J.png">
				<input class="form-check-input" type="radio" name="idiconaprofilo"
					id="exampleRadios2" value="8" <% if(p.getIdIconaProfilo() == 8){ out.println("checked");} %>>
			</div>
		</div>

		<div class="form-group col-md-4">
			<div class="form-check">
				<img id="fotoprofilo-mini" src="https://i.imgur.com/EdfJR5f.png">
				<input class="form-check-input" type="radio" name="idiconaprofilo"
					id="exampleRadios1" value="9" <% if(p.getIdIconaProfilo() == 9){ out.println("checked");} %>>
			</div>
		</div>
	</div>
	
	
		
	<button class="btn btn-info" type="submit">Cambia avatar</button>
</form>