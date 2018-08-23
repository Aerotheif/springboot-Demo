$(".navbar-toggle").click(function(){
	if($(".navbar-fixed-top").hasClass("top-nav-dark")){
		$(".navbar-fixed-top").removeClass("top-nav-dark")
	}else{
		$(".navbar-fixed-top").addClass("top-nav-dark")
	}
})
function getCookie(name) {
    var arr,reg=new RegExp("(^| )"+name+"=([^;]*)(;|$)"); //正则匹配
    if(arr=document.cookie.match(reg)){
        return unescape(arr[2]);
    }
    else{
        return null;
    }
}
function setCookie(name,value) {
    var Days = 30;
    var exp = new Date();
    exp.setTime(exp.getTime() + Days*24*60*60*1000);
    document.cookie = name + "="+ escape (value) + ";expires=" + exp.toGMTString();
}
function delCookie(name) {
    var exp = new Date();
    exp.setTime(exp.getTime() - 1);
    var cval=getCookie(name);
    if(cval!=null)
        document.cookie= name + "="+cval+";expires="+exp.toGMTString();
}
function checkLogin() {
    $.post({
        url:rootPath+"user/getUserInfo.do",
        success:function (data) {
            if(!data.success){
                window.location.href=rootPath+"index.html";
            }
        }
    });
}
function userLogOut(){
    $.post({
        url:rootPath+"user/logout.do",
        success:function (data) {
            if(data.success){
                alert("注销成功！");
                window.location.href=rootPath+"index.html";
            }else{
                alert("系统异常");
            }
        }
    });
}
$(document).ready(function(){
    if(window.location!=rootPath+"index.html"){
        checkLogin();
    }
});
