$(function(){
    // nav收缩展开
    $('#aat').on('click','.nav-item>a',function(){
        if (!$('.nav').hasClass('nav-mini')) {
            if ($(this).next().css('display') == "none") {
                //展开未展开
                $('.nav-item').children('ul').slideUp(300);
                $(this).next('ul').slideDown(300);
                $(this).parent('li').addClass('nav-show').siblings('li').removeClass('nav-show');
            }else{
                //收缩已展开
                $(this).next('ul').slideUp(300);
                $('.nav-item.nav-show').removeClass('nav-show');
            }
        }
    });
    // 标志点击样式
    $('#aat').on('click','.nav-item>ul>li',function(){
    	$('.nav-item>ul>li').css('background','none');
    	$(this).css('background','black');
    	var _link = $(this).children('a').attr('_link');
    	var menu2 = $(this).children('a').children('span').text();
    	var menu1 = $(this).parent().prev().children('i').next().text();
    	var men=menu1+">"+menu2;
    	localStorage.setItem("men",men);
    	$('#mainContent').attr('src',_link);
    })
    
  
});
