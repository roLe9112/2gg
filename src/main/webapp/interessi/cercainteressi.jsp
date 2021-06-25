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
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.carousel.min.css" integrity="sha512-tS3S5qG0BlhnQROyJXvNjeEM4UpMXHrQfTGmbQ1gKmelCxlSEBUaxhRBj/EFTzpbP4RVSrpEikbmdJobCvhE3g==" crossorigin="anonymous" referrerpolicy="no-referrer" />
		<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/assets/owl.theme.default.min.css" integrity="sha512-sMXtMNL1zRzolHYKEujM2AqCLUR9F2C4/05cdbxjjLSRvMQIciEPCQZo++nk7go3BtSuK9kfa/s+a4f4i5pLkw==" crossorigin="anonymous" referrerpolicy="no-referrer" />
		<script src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.6.0/jquery.min.js" integrity="sha512-894YE6QWD5I59HgZOGReFYm4dnWc1Qt5NtvYSaNcOP+u1T9qYdvdihz0PPSiiqn/+/3e7Jo4EaG7TubfWGUrMQ==" crossorigin="anonymous" referrerpolicy="no-referrer"></script>
		<script src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"
		integrity="sha512-bPs7Ae6pVvhOSiIcyUClR7/q2OAsRiovw4vAkX+zJbw3ShAeeqezq50RIIcIURq7Oa20rW2n2q+fyXBNcU9lrw==" 
		crossorigin="anonymous" referrerpolicy="no-referrer"></script>
	</head>

	<body>

		<%@include file="../profilo/menuIn.html"%>

		<!-- CONTENUTO PAGINA -->

		<div class="page-content">

			<!-- SEZIONE FISSA-->
			<div class="fixed-sidebar" id="fixed">

				<div id="schedaprofilo">
					<h2>Cerca!</h2>
					
					<form action="/interessi/risultato" method="get">
						<input type="text" size="30" name="nameint">
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
							<h1>Inizia la tua ricerca!</h1>
								
							
						
						</div> <!-- Fine nofixed-sidebar-content -->
				</div>
				<!-- Fine nofixed-sidebar -->
	
				</div>
		</div><!-- Fine Pagina -->
		
		<%@include file="/common/footer.html"%>
	</body>
	
	<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/OwlCarousel2/2.3.4/owl.carousel.min.js"></script>
	<!--DO NOT ENTER ANY EXTERNAL LINK IN BETWEEN-->
	<script type="text/javascript">
	$(document).ready(function() {
		$('.owl-carousel').owlCarousel({
		    stagePadding: 50,
		    loop:false,
		    margin:10,
		    nav:false,
		    dots:false,
		    responsive:{
		        0:{
		            items:1
		        },
		        600:{
		            items:3
		        },
		        1000:{
		            items:5
		        }
		    }
		})
	});
	</script>
	
</html>
