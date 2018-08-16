$(window).scroll(function () {
            if ($(".navbar").offset().top > 50) {$(".navbar-fixed-top").addClass("top-nav");
            }else {$(".navbar-fixed-top").removeClass("top-nav");}
 });
 $(".navbar-toggle").click(function(){
	if($(".navbar-fixed-top").hasClass("top-nav-dark")){
		$(".navbar-fixed-top").removeClass("top-nav-dark")
	}else{
		$(".navbar-fixed-top").addClass("top-nav-dark")
	}
})
