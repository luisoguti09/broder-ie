$(document).ready(function(){

    $('.arriba').click(function(){
		$('body, html').animate({
			scrollTop: '0px'
		}, 1000);
	});

	$(window).scroll(function(){
		if( $(this).scrollTop() > 0 ){
			$('.arriba').slideDown(300);
		} else {
			$('.arriba').slideUp(300);
		}
	});



});