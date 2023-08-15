<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>找回密码</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <meta name="format-detection" content="telephone=no"/>
    <link rel="apple-touch-icon-precomposed" href="static/images/screenLogo.png"/>
    <link rel="stylesheet" type="text/css" href="static/css/common.css"/>
    <script src="static/js/zepto.min.js" type="text/javascript"></script>
  <!--  <script src="js/main.js" type="text/javascript"></script>-->
    <script type="text/javascript" src="static/js/layer.min.js"></script>
    <script type="text/javascript" src="static/js/m_Common.js"></script>
    <style type="text/css">
        .reg { display: flex; flex-direction: column; margin: 0 20px; }
        .reg input { height: 30px;  border: solid 1px #ddd; text-indent: 10px; font-size: 12px; margin-top: 10px;}
        .reg .btn { border-radius: 3px; border: 0; color: #fff; background: #FF6000; height: 40px; margin-top: 10px;}
        .reg .choose, .reg .operator { display: flex; flex-direction: column}
        .reg .operator .verifyCode {display: flex}
        .reg .operator .verifyCode button {background: #676b70;
            color: #FFFFFF;
            width: 100px;
            height: 40px;
            border: 0;
            border-radius: 3px;
            margin-top: 10px;
            font-size: 12px;
            margin-left: 3px;}
        .reg .operator .verifyCode input {flex: 1}

        .verifyCode btn-disabled {color:#3d474a}

        body{
            background-color: #FFFFFF;
        }

        .reg input{ outline-color: #FF6000;width:100%;height:40px;border:none;text-indent:10px;font-size:13px;border-radius: 3px;background-color: #F5F5F5}
        .reg input:focus{
            background-color: #ffffff;
        }
        .reg input:focus{
            background-color: #ffffff;
        }
    </style>
</head>
<body>
<header id="header">
    <div class="topbar">
        <a href="javascript:history.back();" class="back_btn"><i class="iconfont"></i></a>
        <h1 class="page_title">找回密码</h1>
<!--        <h1>找回密码</h1>-->
    </div>
</header>
<div class="wrapper">
    <div id="_item1" class="reg">
        <input id="txtUser" name="txtUser" type="text" placeholder="填写您的用户名" maxlength="20" value=""/>
        <div>
            <input id="txtCode" name="code" type="text" placeholder="填写验证码" style="width: 50%;" maxlength="10"/>
            <input id="valideteToken" name="uuid" type="hidden" />
            <img id="valideteCode" style="height: 40px; vertical-align: middle" onclick="loadVerifyCode()"/>
        </div>
        <button id="btnNext1" class="btn">下一步</button>
    </div>
    <div id="_item2" class="reg">
        <div class="choose" style="display: none">
            <p style="margin-top:10px;text-align:center;">请选择</p>
            <button id="btnToPhone" class="btn" style="margin:10px 0;">发送验证码到手机</button>
            <p style="text-align:center;">或者</p>
            <button id="btnToMail" class="btn" style="margin:10px 0;">发送验证码到邮箱</button>
        </div>
        <div class="operator" style="display: none">
            <input id="txtPhone" type="text" placeholder="填写您注册时使用的手机号码" maxlength="15" style="display:none;"/>
            <input id="txtMail" type="text" placeholder="填写您注册时使用的邮箱地址" maxlength="200" style="display:none;"/>
            <div class="verifyCode">
                <input id="txtCode2" type="text" placeholder="填写您接收到的验证码" maxlength="10" autocomplete="off"/>
                <input id="uuid" type="hidden" />
                <button onclick="sendVerifyCodeToEmail(this)">发送验证码(60)</button>
            </div>
            <button id="btnNext2" class="btn">下一步</button>
        </div>
    </div>
    <div id="_item3" class="reg" style="display: none">
        <input id="txtPwd" type="password" placeholder="设置新密码"/>
        <input id="txtRepwd" type="password" placeholder="设置确认密码"/>
        <button id="btnSubmit" class="btn">确定修改</button>
	</div>
</div>
    <script type="text/javascript">
        $(function () {
            loadVerifyCode();

            $("#btnNext1").click(function () {
                ValidUser();
            })

			$("#btnToPhone").click(function () {
				$("#_item2 .choose").hide();
				$("#_item2 .operator").show();
				$("#txtMail").hide();
				$("#txtPhone").show();
			});

			$("#btnToMail").click(function () {
				$("#_item2 .choose").hide();
				$("#txtPhone").hide();
				$("#_item2 .operator").show();
				$("#txtMail").show();
			})

			$("#btnNext2").click(function () {
			    let email = $("#txtMail");
			    let txtCode2 = $("#txtCode2");
			    if(!email.val()) {
			        hnieApi.alertMsg("请输入接受验证码的邮箱地址！");
			        email.focus();
			        return;
                }

                if(!txtCode2.val()) {
                    hnieApi.alertMsg("请输入收到的验证码！");
                    txtCode2.focus();
                    return;
                }

                hnieApi.xmxPostJSON({
                    url:"/api/forget/step2", isNotToken: true,
                    data:{username:email.val(),code:txtCode2.val(),uuid:$("#uuid").val()}
                }).then(res =>{
                    if(res.code == 200) {
                        $("#_item2 .operator").hide();
                        $("#_item3").show();
                    }else{
                        hnieApi.alertMsg(res.msg);
                    }
                })
			});

            $("#btnSubmit").click(function(){ //提交按钮绑定事件
                let pwd = $("#txtPwd");
                let repwd = $("#txtRepwd");

                if(!pwd.val()) {
                    hnieApi.alertMsg("请输入新密码！");
                    pwd.focus();
                    return;
                }

                if(!repwd.val()) {
                    hnieApi.alertMsg("请输入确认密码！");
                    repwd.focus();
                    return;
                }

                if(pwd.val() != repwd.val()) {
                    hnieApi.alertMsg("两次密码输入不一致！");
                    repwd.focus();
                    return;
                }

                hnieApi.xmxPostJSON({
                    url:"/api/forget/step3", isNotToken: true,
                    data:{username:$("#txtMail").val(),password:pwd.val(),repassword:repwd.val()}
                }).then(res =>{
                    if(res.code == 200) {
                         hnieApi.alertMsg("密码设置成功！");
                         setTimeout(function () {
                             sessionStorage.removeItem("user_token");
                             document.location = "login.html";
                         },1000);
                    }else{
                        hnieApi.alertMsg(res.msg);
                    }
                })
            });
        })

        function ValidUser() {
            var user = $("#txtUser").val();
            var code = $("#txtCode").val();
            if ($.trim(user) == "") {
                hnieApi.alertMsg("请填写用户名");
                $("#txtUser").focus();
                return;
            }
            if ($.trim(code) == "") {
                hnieApi.alertMsg("请填写验证码");
                $("#txtCode").focus();
                return;
            }

            hnieApi.loading();
            hnieApi.xmxPostJSON({
                url:"/api/forget/step1", isNotToken: true,
                data:{username:user,code:code,uuid:$("#valideteToken").val()}
            }).then( res => {
                hnieApi.closeAll();
                if(res.code == 200) {
                    $("#txtMail").val(user);
                    $("#_item2 .choose").hide();
                    $("#txtPhone").hide();
                    $("#_item2 .operator").show();
                    $("#txtMail").show();
                    $("#_item1").hide();
                }else {
                    hnieApi.alertMsg(res.msg);
                    loadVerifyCode();
                    $("#txtCode").val("");
                }
            });
        }

        function loadVerifyCode() {
            //获取图片验证码
            hnieApi.xmxGet({url:"/verifyImage",isNotToken:true}).then(res => {
                const ret  = res.data;
                $("#valideteCode").attr("src","data:image/png;base64," + ret.img);
                $("#valideteToken").val(ret.uuid);
            });
        }

        function sendVerifyCodeToEmail(btn) {
            $(btn).css("color","#676b7").attr("disabled","");

            hnieApi.xmxGet({
                url:"/api/sendMail?email=" + $("#txtMail").val(), isNotToken: true
            }).then(res => {
                if(res.code == 200) {
                    $("#uuid").val(res.uuid);
                }else {
                    hnieApi.alertMsg(res.msg);
                }
            });

            let second = 59;
            let key = setInterval(() => {
                second--;
                if(second <= 0) {
                    clearInterval(key);
                    $(btn).removeAttr("disabled").css("color","#fff").html("发送验证码(60)");
                    return;
                }
                $(btn).html("发送验证码(" + second + ")");
            }, 1000);
        }
    </script>
</body>
</html>
