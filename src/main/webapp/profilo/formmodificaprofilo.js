$(document).ready(function () {
			
		const checkbox = document.querySelector("#amoree"); //get checkbox
		const div = document.querySelector("#orientamentoo"); //get div
		
		if(!checkbox.checked){  //if checkbox not checked
  			div.style.display = "none"; //hide div
			};

		checkbox.oninput = () => {
  		if(!checkbox.checked){  //if checkbox not checked
    		div.style.display = "none"; //hide div
  		} 
		else 
		{
    		div.style.display = "initial"; //show div
  		};
		}
            
        });


