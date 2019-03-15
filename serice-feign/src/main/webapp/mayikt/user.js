/**
 * 修改用户信息
 * @param userId 用户ID
 */
function updateUserInfo(userId,img){

	var path = $("#photoPath").val();

    var userName = $("#updateForm input[id='userName']").val();
    if (userName==""){
        msgshow("请输入帐号！", "false");
        return;
    }
    var changeShowName = $("#changeShowName").val();
    if (changeShowName==""){
        msgshow("请输入一个昵称！", "false");
        return;
    }

    var realName = $("#realName").val();
    if(realName==null||realName=="") {
        msgshow("请输入真实姓名！", "false");
        return;
    }
    /*var idCardNo = $("#idCardNo").val();
    if(idCardNo==null||idCardNo=="") {
        msgshow("请输入身份证号！", "false");
        return;
    }
    var isIDCard=/(^[1-9]\d{5}(18|19|([23]\d))\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{3}[0-9Xx]$)|(^[1-9]\d{5}\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\d{2}$)/;
    if(!(isIDCard.test(idCardNo))) {
        msgshow("身份证号格式错误！", "false");
        return;
    }
    var cmbProvince = $("#cmbProvince").val();
    var cmbCity = $("#cmbCity").val();
    var cmbArea = $("#cmbArea").val();
    if(cmbProvince==0||cmbCity==0||cmbArea==0) {
        msgshow("请选择居住地！", "false");
        return;
    }*/
    var school = $("#school").val();
    if(school==null||school=="") {
        msgshow("请输入所在学校！", "false");
        return;
    }
    var subject = $("#subject").val();
    if(subject==null||subject=="") {
        msgshow("请输入所学专业！", "false");
        return;
    }

	if(oldMobile==null||oldMobile==""){
		var mobileVal=$("#u-mobile").val();
		if(mobileVal==""){//验证手机是否为空
            msgshow("请输入用户手机号！", "false");
			return;
		}
		if(mobileRegex.test(mobileVal)==false){//格式不正确
            msgshow("请输入正确的手机！", "false");
			return;
		}
	}
	if(oldEmail==null||oldEmail==""){
		var emailVal=$("#u-email").val();
		if(emailVal==""){//验证邮箱是否为空
            msgshow("请输入邮箱！", "false");
			return;
		}
		if(emailRegex.test(emailVal)==false){//格式不正确
            msgshow("请输入正确的邮箱！", "false");
			return;
		}
	}

	var oldPhotoPath=$("#oldPhotoPath").val();
	//新上传图片后，修改图片
	if(path!=oldPhotoPath){
		var params = "photoPath=" + path + "&txt_width=" + $("#picture_width").val()
			+ "&txt_height=" + $("#picture_height").val() + "&txt_top="
			+ $("#txt_top").val() + "&txt_left=" + $("#txt_left").val()
			+ "&txt_DropWidth=" + $("#txt_DropWidth").val()
			+ "&txt_DropHeight=" + $("#txt_DropHeight").val();
		$.getJSON(uploadServerUrl + "/image/saveface?callback=?" , params,function(json) {
				uploadImageCallback(json.src);
			}
		);
	}else {
		uploadImageCallback(oldPhotoPath);
	}

}

/**
 * 修改个人头像 切图后回调
 * @param photoUrl
 */
function uploadImageCallback(photoUrl){
	var params='';
	$("#updateForm input,#updateForm select").each(function(){
		params+=$(this).serialize()+"&";
	});

	params+="user.picImg="+photoUrl+"&";
	$.ajax({
		url:baselocation+'/uc/updateUser',
		type:'post',
		dataType:'json',
		data:params,
		success:function(result){
			if(result.success==true){
				showUserInfo();
				msgshow(result.message,"success");//弱提示
                setTimeout(function() {
                    window.location.href = baselocation + "/uc/initUpdateUser/0";
                },3000);
			}else{
				msgshow(result.message,"false");//弱提示
			}
		}
	});
}

/**
 * 选项控制显示
 */
function showTab(_in){
	$(".c-tab-title > a.clickAvailable").click(function(){
		$(".c-tab-title > a.clickAvailable").removeClass('current');
		$(this).addClass('current');
		var _index = $(this).index()-1;
		$("#p_tCont > div").hide();
		$($("#p_tCont > div")[_index]).show();
	});
	
	$(".c-tab-title > a.clickAvailable").removeClass('current');
	$($(".c-tab-title > a.clickAvailable")[_in]).addClass('current');
	$("#p_tCont > div").hide();
	$($("#p_tCont > div")[_in]).show();
}

/**
 * 修改密码
 */
function updatePwd(){
    var edge = IEVersion();
    var falg;
    if("Edge" == edge){
        falg=true;
	}else if("Edge" != edge){
        falg=false;
	}

	$(".u-a-error").html("");
	var oldPwd=$("input[name='nowPassword']").val();
	if(oldPwd.trim()==""){
		$("input[name='nowPassword']").next().html('<em class="u-a-cw2 icon16">&nbsp;</em>请输入原始密码');
		$("input[name='nowPassword']").parent().addClass("errorts");
		return;
	}else{
		$("input[name='nowPassword']").next().html('<em class="u-a-zq icon16">&nbsp;</em>');
		$("input[name='nowPassword']").parent().removeClass("errorts");
	}
	
	var newPassword=$("input[name='newPassword']").val();
	if(newPassword.trim()==""){
		$("input[name='newPassword']").next().html('<em class="u-a-cw2 icon16">&nbsp;</em>请输入新密码');
		return;
	}else{
		$("input[name='newPassword']").next().html('<em class="u-a-zq icon16">&nbsp;</em>');
	}

    if(newPassword==oldPwd){
        $("input[name='newPassword']").next().html('<em class="u-a-cw2 icon16">&nbsp;</em>不能与近期密码相同');
        return;
    }else{
        $("input[name='newPassword']").next().html('<em class="u-a-zq icon16">&nbsp;</em>');
    }
	
	var confirmPwd=$("input[name='confirmPwd']").val();
	if(confirmPwd.trim()==""){
		$("input[name='confirmPwd']").next().html('<em class="u-a-cw2 icon16">&nbsp;</em>请输入确认密码');
		return;
	}else{
		$("input[name='confirmPwd']").next().html('<em class="u-a-zq icon16">&nbsp;</em>');
	}

	var params ='';
	$("#pwdForm input").each(function(){

		params+=$(this).serialize()+"&";
    });
	
	$.ajax({
		url:baselocation+'/uc/updatePwd',
		type:'post',
		dataType:'json',
		data:params,
		success:function(result){
			if(result.success==true){
				msgshow(result.message,"true","500");
				$("input:password").val('');
                $("input:password").next().html('');
			}else{
				if(result.entity==1){
					$("input[name='nowPassword']").next().html('<em class="u-a-cw2 icon16">&nbsp;</em>');
				}else {
					$("input[name='confirmPwd']").next().html('<em class="u-a-cw2 icon16">&nbsp;</em>');
					$("input[name='newPassword']").next().html('<em class="u-a-cw2 icon16">&nbsp;</em>');
				}
				dialog('提示信息',result.message,1);
			}
		},
		error:function(error){
			dialog('提示信息','系统繁忙，请稍后再操作！',1);
		}
	});
}
/**
 * 修改用户图片
 * @param userId 用户ID
 */
function updateImg(userId) {
    var path = $("#photoPath").val();
	var oldPhotoPath = $("#oldPhotoPath").val();
	if (path==oldPhotoPath){
		document.location='http://www.mayikt.com/uc/initUpdateUser/0';
		return;
	}
    if (path==null || path=='') {
        $("#save_message").html('<em class="icon16 u-a-cw">&nbsp;</em><q class="c-orange vam">请上传您要修改的图片</q>');
        return;
    }
    var params = "photoPath=" + path + "&txt_width=" + $("#picture_width").val()
        + "&txt_height=" + $("#picture_height").val() + "&txt_top="
        + $("#txt_top").val() + "&txt_left=" + $("#txt_left").val()
        + "&txt_DropWidth=" + $("#txt_DropWidth").val()
        + "&txt_DropHeight=" + $("#txt_DropHeight").val() + "&cusid="
        + userId;
    $.getJSON(uploadServerUrl + "/image/saveface?callback=?",params,function(json) {
            var photoUrl = json.src;
            $.ajax({
                type : "post",
                url : baselocation+"/uc/updateImg",
                data : {'user.userId':userId,'user.picImg':photoUrl},
                success : function(result) {
                    if(result.success==true){
                        document.location='http://www.mayikt.com/uc/initUpdateUser/0'
                    }else{
                        dialog('提示信息',result.message,1);
                    }
                },
                error : function(ex) {
                    dialog('提示信息','系统繁忙，请稍后再操作！',1);
                }
            });
        }
    );
}

/**
 * 初始化上传头像
 * @param btnid 标签ID
 * @param fieldName
 */
function uploadImg(btnid,fieldName){
    //实例化一个plupload上传对象
    var uploader = new plupload.Uploader({
        runtimes : 'html5,flash,gears,browserplus,silverlight,html4',
        browse_button : btnid, //触发文件选择对话框的按钮，为那个元素id
        url : uploadSimpleUrl+'param=temp', //服务器端的上传页面地址
        chunk_size : '1mb',
        flash_swf_url : baselocation+'/static/common/plupload/js/Moxie.swf', //swf文件，当需要使用swf方式进行上传时需要配置该参数
        silverlight_xap_url : baselocation+'/static/common/plupload/js/Moxie.xap', //silverlight文件，当需要使用silverlight方式进行上传时需要配置该参数
        multi_selection:false
        /*, filters: {
            mime_types : [ //只允许上传图片和zip文件
                { title : "Image files", extensions : "jpg,png,jpeg" }
                ,{ title : "Zip files", extensions : "zip" }
            ],
            max_file_size : '10mb', //最大只能上传10mb的文件
            prevent_duplicates : true //不允许选取重复文件
        }*/
    });

    //在实例对象上调用init()方法进行初始化
    uploader.init();
    //文件选择后上传
    uploader.bind('FilesAdded', function(uploader, files) {
        //自动上传
        uploader.start();
    });

    //文件选择后上传
    uploader.bind('FileFiltered', function(up, file){
        //选择文件后触发
        var limitSize=10;
        if (file.size > limitSize * 1024 * 1024)
        {
            alert("您上传的图片大小是" + plupload.formatSize(file.size)  + ",只能上传小于"+limitSize+"mb图片");
            up.removeFile(file);
            return;
        }
        if(!/(jpg|png|jpeg)$/.test(file.type)){
            alert("图片类型必须是.jpg,png,jpeg中的一种");
            //layer.msg("您上传的图片格式是" + file.type + ",只能上传"+limitType+"图片", {icon: 5, shift: 6});
            up.removeFile(file);
            return;
        }
    });

    //上传完成填充数据
    uploader.bind('FileUploaded',
        function(uploader, file, responseObject) {
            $.each(file, function(idx, item){
                uploader.removeFile(item);
            });
            var result=responseObject.response;
            var data = $.parseJSON(result);
            if (data.error == 0) {
                //销毁图片插件
                $("#deleImage").click();
                $("#picture").attr("style","");
                $("#picture").attr("src",staticServer+data.url);
                $(".jcrop-preview").attr("src",staticServer+data.url);
                $(".pictureWrap").attr("src",staticServer+data.url);
                $("#photoPath").val(data.url);

                var img=new Image();
                img.src=staticServer+data.url;
                img.onload=function(){
                    var realHeight = img.height;
                    var realWidth = img.width;
                    if(realHeight>=realWidth&&realHeight>300){
                        var height=300;
                        var width = height*realWidth/realHeight;
                        $("#picture").attr("height",height);
                        $("#picture").attr("width",width);
                        $("#picture_width").val(Math.ceil(width));
                        $("#picture_height").val(height);
                    }else if(realHeight<realWidth&&realWidth>300){
                        var width=300;
                        var height = width*realHeight/realWidth;
                        $("#picture").attr("height",height);
                        $("#picture").attr("width",width);
                        $("#picture_width").val(width);
                        $("#picture_height").val(Math.ceil(height));
                    }else{
                        var height=realHeight;
                        var width = realWidth;
                        $("#picture").attr("height",height);
                        $("#picture").attr("width",width);
                        $("#picture_width").val(Math.ceil(width));
                        $("#picture_height").val(height);
                    }

                    editingPhotos();
                };
            }else{
                alert(data.message);
            }
        }
    );
}

/**
 * 编辑图片
 */
function editingPhotos(){
	jQuery(function($){
	    var jcrop_api,
	        boundx,
	        boundy,
	        $preview = $('.preview-pane'),
	        $pcnt2 = $('.preview-pane2 .preview-container'),
	        $pimg2 = $('.preview-pane2 .preview-container img'),
	        $pcnt = $('.preview-pane1 .preview-container'),
	        $pimg = $('.preview-pane1 .preview-container img'),
	        $pcnt3 = $('.preview-pane3 .preview-container'),
	        $pimg3 = $('.preview-pane3 .preview-container img'),
	        xsize = $pcnt.width(),
	        ysize = $pcnt.height();
		    xsize2 = $pcnt2.width(),
	        ysize2 = $pcnt2.height();
		    xsize3 = $pcnt3.width(),
	        ysize3 = $pcnt3.height();

	    $('#picture').Jcrop({
	      onChange: updatePreview,
	      onSelect: updatePreview,
	      allowSelect:false,//是否允许新选框
          minSize: [50,50],//选框最小尺寸
	      aspectRatio: xsize / ysize,
	      aspectRatio: xsize2 / ysize2,
	      aspectRatio: xsize3 / ysize3
	    },function(){
	      var bounds = this.getBounds();
	      boundx = bounds[0];
	      boundy = bounds[1];
	      jcrop_api = this;
	      jcrop_api.animateTo([80,50,80,20]);
	      $preview.appendTo(jcrop_api.ui.holder);
	    });

	    function updatePreview(c){
    	  $('#txt_left').val(c.x);
	      $('#txt_top').val(c.y);
	      $('#x2').val(c.x2);
	      $('#y2').val(c.y2);
	      $('#txt_DropWidth').val(c.w);
	      $('#txt_DropHeight').val(c.h);
	      if (parseInt(c.w) > 0){
	        var rx = xsize / c.w;
	        var ry = ysize / c.h;
	        $pimg.css({
	          width: Math.round(rx * boundx) + 'px',
	          height: Math.round(ry * boundy) + 'px',
	          marginLeft: '-' + Math.round(rx * c.x) + 'px',
	          marginTop: '-' + Math.round(ry * c.y) + 'px'
	        });
	        var rx2 = xsize2 / c.w;
	        var ry2 = ysize2 / c.h;
	        $pimg2.css({
		          width: Math.round(rx2 * boundx) + 'px',
		          height: Math.round(ry2 * boundy) + 'px',
		          marginLeft: '-' + Math.round(rx2 * c.x) + 'px',
		          marginTop: '-' + Math.round(ry2 * c.y) + 'px'
		        });
	        var rx3 = xsize3 / c.w;
	        var ry3 = ysize3 / c.h;
	        $pimg3.css({
		          width: Math.round(rx3 * boundx) + 'px',
		          height: Math.round(ry3 * boundy) + 'px',
		          marginLeft: '-' + Math.round(rx3 * c.x) + 'px',
		          marginTop: '-' + Math.round(ry3 * c.y) + 'px'
		        });
	      }
		}
		$('#deleImage').click(function(e) {
            jcrop_api.destroy();
            return false;
        });
    });
}