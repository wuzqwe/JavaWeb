<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8"/>
    <title>注册</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0"/>
    <meta name="apple-mobile-web-app-capable" content="yes"/>
    <meta name="apple-mobile-web-app-status-bar-style" content="black"/>
    <meta name="format-detection" content="telephone=no"/>
    <link rel="apple-touch-icon-precomposed" href="static/images/screenLogo.png"/>
    <link rel="stylesheet" type="text/css" href="static/css/common.css"/>
    <style type="text/css">
        .login{margin:10px 10px 0}
        .loginbox{position:relative;margin-top:10px;overflow:hidden;border-right:solid 1px #ddd;}
        .loginbox i{position:absolute;left:10px;top:8px;color:#999;}
        .loginBtn input,.loginbox input{ outline-color: #FF6000;width:100%;height:40px;border:none;text-indent:35px;font-size:13px;border-radius: 3px;background-color: #F5F5F5}
        .loginbox input:focus{
            background-color: #ffffff;
        }
        .loginBtn input:focus{
            background-color: #ffffff;
        }
        .loginBtn {display: flex; margin-top: 10px}
        .loginBtn #txtCode{text-indent: 8px;font-size: 13px}
        .loginBtn button, .loginBtn #txtCode, .loginBtn #valideteCode {flex: 1;height: 40px;margin-right: 2px;}
        .loginBtn button {border: 0; border-radius: 3px}
        .loginBtn #btnLogin {background: #FF6000;color: #ffffff}
        .loginBtn #btnLogin:hover {background: #FF8C00;}
        .loginBtn #autoPwsLogin:hover {background: #778899;}
        .loginBtn #autoPwsLogin {  background: #628094; color: #ffffff }
        .login .zczh {display: flex; justify-content: flex-end}
        .login .zczh .zc_btn{font-size: 15px;color: #FF6000;}
        .login .checkbox { vertical-align:middle;}
        .login .lable { font-size: 12px}
        .msg {color:red;margin-left: 20px}

        .tubiao{display: flex;}
        .tubiao img{
            width: 100%;
            height: 150px;
        }
        body{
            background-color: #FFFFFF;
        }
        .ss{
            display: flex;
            justify-content: space-around;
            margin-top: -15px;
        }
        .ss a{
            font-size: 20px;
            padding: 30px 0;
            position:relative;color:#333;border-radius:14px;overflow:hidden
        }
        .ss2:after{position:absolute;content:"";bottom:20px;left:50%;width:20px;height:4px;margin-left:-10px;background:#FF6000;border-radius:1px}
        .gp{width:2px;height:16px;margin-top: 36px;
            float:left;width:1px;height:24px;font-size:0;background-color:#e8e8e8}

        .reg { display: flex;  flex-direction: column;  margin: 0 20px; }
        .reg button {  border-radius: 3px; border: 0; color: #fff; height: 40px; margin-right: 2px; background: #ff6600; }
        .reg button:hover {background: #FF8C00;}
        .reg input { outline-color: #FF6000;height: 40px; border: none; text-indent: 10px; font-size: 12px; margin-top: 10px;background-color: #F5F5F5 }
        .reg input:focus{background-color: #ffffff;}
        #btnReg { margin-top: 10px }

    </style>
</head>
<body>
<header id="header">
    <div class="tubiao">
        <img src="static/images/part-time3.png">
    </div>
    <div class="ss">
        <a class="ss1" href="login.jsp">账号密码登录</a>
        <span class="gp"></span>
        <a class="ss2" href="reg.jsp">用户注册</a>
    </div>
</header>
<div class="wrapper">
    <input id="uuid" name="uuid" type="hidden" />
    <form action="<%=request.getContextPath()%>/user/reg" method="post">
    <div class="reg">
        <input id="txtUser" name="nickname" type="text" placeholder="昵称" maxlength="20"/>
        <input id="txtMobile" name="username" type="text" onblur="verifyUsername(this)" placeholder="用户名/手机号" maxlength="11"/>
        <input id="txtPwd1" name="password" type="password" placeholder="密码（6~20位）" maxlength="20"/>
        <input id="txtPwd2" name="repassword" type="repwd" placeholder="重复密码（6~20位）" maxlength="20"/>
        <button type="submit" id="btnReg">提交</button>
    </div>
    </form>
</div>
<script type="text/javascript" src="static/js/zepto.min.js"></script>
<script type="text/javascript" src="static/js/layer.min.js"></script>
<script type="text/javascript" src="static/js/m_Common.js"></script>
<script type="text/javascript">
    function loadVerifyCode() {
        //获取图片验证码
        hnieApi.xmxGet({url:"/verifyImage",isNotToken:true}).then(res => {
            const ret  = res.data;
            $("#valideteCode").attr("src","data:image/png;base64," + ret.img);
            $("#uuid").val(ret.uuid);
        });
    }

    function verifyUsername(obj) {
        if(!obj.value) {
            return;
        }

        hnieApi.xmxGet({url:"/api/reg/checkName?username=" + obj.value}).then(res =>{
            if(res.code == 500) {
                hnieApi.alertMsg("【" + obj.value + "】" + res.msg);
                obj.value = '';
                obj.focus();
            }
        });
    }

    $(function () {
        loadVerifyCode(); //获取验证码

        $("#btnReg").click(function () {
            var user = $("#txtUser").val();
            var pwd1 = $("#txtPwd1").val();
            var pwd2 = $("#txtPwd2").val();
            var username = $("#txtMobile").val();
            var code = $("#txtCode").val();
            if (user.trim() == "") {
                layer.open({content: "请填写用户名", btn: ["好的"], shadeClose: false});
                $("#txtUser").focus();
                return;
            }
            if (username.trim() == "") {
                layer.open({content: "请填写手机号码", btn: ["好的"], shadeClose: false});
                $("#txtMobile").focus();
                return;
            }
            if (pwd1.trim() == "") {
                layer.open({content: "请填写密码", btn: ["好的"], shadeClose: false});
                $("#txtPwd1").focus();
                return;
            }
            if (pwd2.trim() == "") {
                layer.open({content: "请再次填写密码", btn: ["好的"], shadeClose: false});
                $("#txtPwd2").focus();
                return;
            }
            if (pwd1 != pwd2) {
                layer.open({content: "两次填写的密码不一致", btn: ["好的"], shadeClose: false});
                $("#txtPwd2").focus();
                return;
            }
            /* hnieApi.xmxPost({
                 url:"/api/register",
                 data:{nickname:user,username:username,password:pwd1,repassword:pwd2,code:code,uuid:$("#uuid").val()}
             }).then(res => {
                 console.log("regist info ",res);
             });*/
            //let json = JSON.stringify({nickname:user,username:username,password:pwd1,repassword:pwd2,code:code,uuid:$("#uuid").val()});
            //console.log("json", json);

            hnieApi.loading("提交中...");
            hnieApi.xmxPostJSON({
                url:"/api/registerJSON",
                data:{nickname:user,username:username,password:pwd1,repassword:pwd2,code:code,uuid:$("#uuid").val()}
            }).then(res => {
                hnieApi.closeAll();
                if(res.code == 200) {
                    hnieApi.alertMsg(res.msg);
                    document.location = "login.html";
                }else {
                    hnieApi.alertMsg(res.msg);
                }
            });
        });
    });</script>
</body>
</html>
