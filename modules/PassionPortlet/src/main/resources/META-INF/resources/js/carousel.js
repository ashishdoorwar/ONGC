 
       
//FUNCTIONS BELLOW
//slide function  
function slideImg(where,ulNo){
	
                //get the item width
            var item_width = jQuery('#carousel_ul'+ulNo+' li').outerWidth();     
			
            /* using a if statement and the where variable check 
            we will check where the user wants to slide (left or right)*/
            if(where == 'left'){
                //...calculating the new left indent of the unordered list (ul) for left sliding
                var left_indent = parseInt(jQuery('#carousel_ul'+ulNo+'').css('left')) + item_width;
            }else{
                //...calculating the new left indent of the unordered list (ul) for right sliding
                var left_indent = parseInt(jQuery('#carousel_ul'+ulNo+'').css('left')) - item_width;
            
            }
            
            
            //make the sliding effect using jQuery's animate function... '
            jQuery('#carousel_ul'+ulNo+':not(:animated)').animate({'left' : left_indent},500,function(){    
                /* when the animation finishes use the if statement again, and make an ilussion
                of infinity by changing place of last or first item*/
                if(where == 'left'){
                    //...and if it slided to left we put the last item before the first item
                    jQuery('#carousel_ul'+ulNo+' li:first').before(jQuery('#carousel_ul'+ulNo+' li:last'));
                }else{
                    //...and if it slided to right we put the first item after the last item
                    jQuery('#carousel_ul'+ulNo+' li:last').after(jQuery('#carousel_ul'+ulNo+' li:first')); 
                }
                  //...and then just get back the default left indent
                jQuery('#carousel_ul'+ulNo+'').css({'left' : '0px'});
				 
            });
            
           
}
