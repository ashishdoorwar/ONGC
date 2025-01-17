 jQuery(document).ready(function() {
        
        //options( 1 - ON , 0 - OFF)
        var auto_slide = 0;
            var hover_pause = 0;
        var key_slide = 0;
		 //var timer = setInterval('slide("right")', auto_slide_seconds); 
        
        //speed of auto slide(
        var auto_slide_seconds = 2000;
        /* IMPORTANT: i know the variable is called ...seconds but it's in milliseconds ( multiplied with 1000) '*/
        /*move he last list item before the first item. The purpose of this is if the user clicks to slide left he will be able to see the last item.*/
        jQuery('#carousel_ulpop li:first').before(jQuery('#carousel_ulpop li:last')); 
        
		//check if auto sliding is enabled
        if(auto_slide == 1){
            /*set the interval (loop) to call function slide with option 'right' and set the interval time to the variable we declared previously */
            var timer = setInterval('slide("right")', auto_slide_seconds); 
			//alert(timer+" first");            
            /*and change the value of our hidden field that hold info about the interval, setting it to the number of milliseconds we declared previously*/
            //jQuery('#hidden_auto_slide_seconds').val(auto_slide_seconds);
        }
  
        //check if hover pause is enabled
        if(hover_pause == 1){
            //when hovered over the list 
            jQuery('#carousel_ulpop').hover(function(){
                //stop the interval
                clearInterval(timer)
            },function(){
                //and when mouseout start it again
                timer = setInterval('slide("right")', auto_slide_seconds); 
            });
  
        }

		jQuery("#stop").click(function(){
			
			jQuery("#carousel_ulpop ").stop();
			
		     clearInterval(timer);
			//alert(timer +' pause');
		});

		 jQuery("#go").click(function(){
			//var auto_slide_seconds=1000;
			clearInterval(timer);
			jQuery("#carousel_ulpop ").animate();
			timer = setInterval('slide("right")', auto_slide_seconds); 
			//alert(timer +' play');
			// clearInterval(timer)
			//setInterval(timer)
		 
		});
   
        //check if key sliding is enabled
        if(key_slide == 1){
            
            //binding keypress function
            jQuery(document).bind('keypress', function(e) {
                //keyCode for left arrow is 37 and for right it's 39 '
                if(e.keyCode==37){
                        //initialize the slide to left function
                        slide('left');
                }else if(e.keyCode==39){
                        //initialize the slide to right function
                        slide('right');
                }
            });
        }         
  });


//FUNCTIONS BELLOW
//slide function  
function slide(where){
                //get the item width
            var item_width = jQuery('#carousel_ulpop li').outerWidth()+10;     
			
            /* using a if statement and the where variable check 
            we will check where the user wants to slide (left or right)*/
            if(where == 'left'){
                //...calculating the new left indent of the unordered list (ul) for left sliding
                var left_indent = parseInt(jQuery('#carousel_ulpop').css('left')) + item_width;
            }else{
                //...calculating the new left indent of the unordered list (ul) for right sliding
                var left_indent = parseInt(jQuery('#carousel_ulpop').css('left')) - item_width;
            
            }
            
            
            //make the sliding effect using jQuery's animate function... '
            jQuery('#carousel_ulpop:not(:animated)').animate({'left' : left_indent},500,function(){    
                /* when the animation finishes use the if statement again, and make an ilussion
                of infinity by changing place of last or first item*/
                if(where == 'left'){
                    //...and if it slided to left we put the last item before the first item
                    jQuery('#carousel_ulpop li:first').before(jQuery('#carousel_ulpop li:last'));
                }else{
                    //...and if it slided to right we put the first item after the last item
                    jQuery('#carousel_ulpop li:last').after(jQuery('#carousel_ulpop li:first')); 
                }
                  //...and then just get back the default left indent
                jQuery('#carousel_ulpop').css({'left' : '0px'});
				 
            });
            
           
}
