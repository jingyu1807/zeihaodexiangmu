$(function() {
	cssNavigation();//设置导航选中样式
	//showUserInfo();//头部显示用户信息
	iSortFun(".n-item-menu",".n-i-m-bottom .i-j-item",".n-item-right-op","curr");
    iSortFun(".line-item-menu",".line-i-m-bottom .line-j-item",".line-item-right-op","curr");
	headHight();//获取左侧菜单栏高度与图片相等
	mobileheaderlogo();//移动端底顶部logo切换

    var render = "canvas";
    try {
        //默认字符串转二维码的方法，仅支持html5
        document.createElement('canvas').getContext('2d');
    } catch (e) {
        //如果不支持html5报错后通过table生成二维码
        render = "table";
    }
    //生成二维码
    $('#app_qrcode').qrcode({
        text: baselocation + "/front/downapp",
        height: 100,
        width: 100,
        render: render,
        src: 'http://static.itmayiedu.com/favicon.ico'//这里配置Logo的地址即可。
    });
    $('#h5_qrcode').qrcode({
        text: window.location.href,
        height: 100,
        width: 100,
        render: render,
        src: 'http://static.itmayiedu.com/favicon.ico'//这里配置Logo的地址即可。
    });
});
//如果是首页移动端左上角则显示logo
function mobileheaderlogo(){
	if(checkIsMobile()){
		var url = window.document.location.pathname;
		var syWidth =$(".all-header").width()-95;
		var smWidth =$(".all-header").width()-140;
		if (url!="/"&&url!="/index2"&&url!="/index3") {
			$(".headerfanhui").show();
			//$(".headerlogo").hide();
			$("#logo").css({"width":"35px","margin":"0"});
            $("#logo a").css("margin","0");
			$(".mw-nav-seach").css("width",syWidth);
			$(".mw-nav-seach label input").css("width",smWidth);
		}else {
            $(".headerlogo").show();
		}
	}else {
        $(".headerlogo").show();
	}
}
//获取左侧菜单栏高度与图片相等
function headHight(){
	$(".n-i-m-bottom").css("height",$(".imgload").eq(0).height());
	$(".n-item-right").css("height",$(".imgload").eq(0).height()-2);
}
/**
 * 头部显示用户信息
 */
function showUserInfo() {
	var user = getLoginUser();
	if(user!=null && user.userId>0){
        $("#no-login").hide();
        $(".no-login").hide();
		queryUnReadNum();// 查询未读消息
		var showName = user.displayName;
		if (showName == null || $.trim(showName) == '') {
			showName = user.email;
		}
		// 头像
		var useImg = user.picImg;
		if (useImg == null || $.trim(useImg) == '') {
			useImg = baselocation + 'http://static.itmayiedu.com/avatar-boy.gif';
		} else {
			useImg = staticServer + useImg;
		}
		$("#userName").text(showName);
		$(".userImgPhoto").attr("src", useImg);
		$(".userImgPhoto").attr("alt", showName);
		$(".userNameClass").html(showName);
		$("#showName").text(showName);
		$("#showName").attr('title', showName);
		userBannerImage = user.bannerUrl;
		// 头部显示
		$("#userName").text(showName);
		$("#userName").attr('title', showName);
		$("#is-login-one,#is-login-two,#mobileExitDiv,.loginedLiShow").show();
		$(".picImg").attr("src", useImg);
		/*vip开通情况显示*/
		var vip = getVipInfo();
		if ($(vip).size()>0){
			$(".u-tit-vip").show();
		}else {
			$(".u-tit-vip-gq").show();
		}

	} else {
		$("#no-login").show();
		$(".no-login").show();
	}
}
/**
 * 设置导航选中样式
 */
function cssNavigation() {
	var url = window.document.location.pathname;
	if(url=='/index'){
        url = '/';
	}
	$("a[href='" + url + "']").parent().addClass("current");

	//根据请求地址 ，设置导航头部 搜索 交地址
	if(url.indexOf("/teacherlist") > 0||url.indexOf("/teacher/") > 0) {
		searchType('TEACHER');
		searchTypeH5('TEACHER');
	}else if (url.indexOf("/liveIndex")>0||url.indexOf("/showLivelist")>0||url.indexOf("/liveinfo/")>0){
		searchType('LIVES');
        searchTypeH5('LIVES');
	}else if (url.indexOf("/articlelist/article") > 0||url.indexOf("/articleinfo/") > 0) {
		searchType('ARTICLE');
        searchTypeH5('ARTICLE');
	}else if (url.indexOf("/articlelist/line") > 0) {
		searchType('LINEARTICLE');
        searchTypeH5('LINEARTICLE');
	}else if (url=="/questions/list") {
		searchType('QUESTIONS');
        searchTypeH5('QUESTIONS');
	}else if (url.indexOf("lineclass/index")>0||url.indexOf("lineclass/list")>0||url.indexOf("lineclass/infor/")>0) {
        searchType('LINECLASS');
        searchTypeH5('LINECLASS');
    }else if (url.indexOf("/exam")>0||url.indexOf("/examList")>0||url.indexOf("/examInfo/")>0||url.indexOf("/ucExam")>0) {
		searchType('EXAM');
		searchTypeH5('EXAM');
	}else if (url.indexOf("/classlist")>0||url.indexOf("/classinfo")>0) {
        searchType('CLASS');
        searchTypeH5('CLASS');
    }else  {
		searchType('COURSE');
        searchTypeH5('COURSE');
	}

	//顶部搜索下拉类型选择
	ssFun();
}

/**
 * 初始化选择搜索类型
 * @param type
 */
function searchType(type) {
	if(type == "TEACHER") {
		$("#headerformSearch").attr("action", "/front/teacherlist");
		$("#headerSearchName").attr("name", "http://static.itmayiedu.com/queryTeacher.name");
	}else if (type == "ARTICLE") {
		$("#headerformSearch").attr("action", "/front/articlelist/article");
		$("#headerSearchName").attr("name", "queryArticle.queryKey");
	}else if (type == "LINEARTICLE") {
		$("#headerformSearch").attr("action", "/front/articlelist/lineArticle");
		$("#headerSearchName").attr("name", "queryArticle.queryKey");
	}else if (type=="LIVES"){
		$("#headerformSearch").attr("action","/front/showLivelist");
		$("#headerSearchName").attr("name","queryCourse.courseName");
	}else if (type == "QUESTIONS") {
		$("#headerformSearch").attr("action", "/questions/list");
		$("#headerSearchName").attr("name", "questions.title");
	}else if (type == "LINECLASS") {
		$("#headerformSearch").attr("action", "/lineclass/list");
		$("#headerSearchName").attr("name", "queryCourse.courseName");
	}else if (type == "EXAM") {
		$("#headerformSearch").attr("action", "/exam/examList");
		$("#headerSearchName").attr("name", "http://static.itmayiedu.com/queryPaper.name");
	}else if (type == "CLASS") {
        $("#headerformSearch").attr("action", "/front/classlist");
        $("#headerSearchName").attr("name", "queryCourse.courseName");
    }else {
		$("#headerformSearch").attr("action", "/ajax/saveSearchWords");
		$("#headerSearchName").attr("name", "queryCourse.courseName");
	}
}
	//顶部消息下拉
function ssFun() {
	$(".h-r-login li, .h2-r-l-list li, .h3-r-l-list li").hover(function () {
        $(this).children(".new-dwon-warp-all").slideDown(50);
        $(this).siblings().children(".new-dwon-warp-all").slideUp(50);
	}, function () {
        $(this).children(".new-dwon-warp-all").slideUp(50);
	});
}
iSortFun(".line-item-menu",".line-i-m-bottom .line-j-item",".line-item-right-op","curr");

//课程分类展开
function iSortFun(obj,iTem,iCont,curr) {
	var _timer = null;
	$(obj).on({
		mouseover: function(e) {
			var _this = $(this),
				_index = _this.index();
			if($(iCont).children().eq(_index).length>0) {
				e.stopPropagation();
				_this.addClass(curr).siblings().removeClass(curr);
				_timer = setTimeout(function() {
					$(iCont).show().children().eq(_index).show().siblings().hide();
				}, 100);
				$(iCont).unbind().bind('mouseover', function(e) {
					e.stopPropagation();
					clearTimeout(_timer);
				});
				$(document).unbind().bind('mouseover', function(e) {
					e.stopPropagation();
					clearTimeout(_timer);
					_this.removeClass(curr);
					$(iCont).hide().children().eq(_index).hide();
				})
			}
		}
	}, iTem);
}

/**
 * 保存搜索关键词
 */
function toSearch() {
	var courseName = $("#courseName").val().trim();
	if(isNotEmpty(courseName)){
		$.ajax({
			type : "POST",
			dataType : "json",
			url:"/ajax/saveSearchWords",
			data:{"courseName":courseName},
			success : function(data) {
				if(success){
					alert("success");
				}else{
					alert("false");

				}
			}
		});
	}
	return true;
}
