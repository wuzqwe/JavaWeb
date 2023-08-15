<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>用户登录</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="format-detection" content="telephone=no" />
    <link rel="apple-touch-icon-precomposed" href="static/images/screenLogo.png" />
    <link rel="stylesheet" type="text/css" href="static/css/common.css" />
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
        .msg {color:red}

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
        .ss1:after{position:absolute;content:"";bottom:20px;left:50%;width:20px;height:4px;margin-left:-10px;background:#FF6000;border-radius:1px}
        .gp{width:2px;height:16px;margin-top: 36px;
            float:left;width:1px;height:24px;font-size:0;background-color:#e8e8e8}

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
        <a href="reg.jsp">用户注册</a>
    </div>
</header>
<div class="wrapper">
    <div class="login">
        <div class="msg">

        </div>
        <form action="<%=request.getContextPath()%>/user/login" method="post">
        <div class="loginbox">
            <i class="iconfont">Œ</i>
            <input id="username" name="username" type="text" placeholder="用户名/邮箱/手机号" value="" />
        </div>
        <div class="loginbox">
            <i class="iconfont">ƺ</i>
            <input id="pwd" name="password" type="password" placeholder="密码" />
        </div>
        <div id="msg" style="margin-top:10px;"></div>
        <p class="zczh">
            <a href="forget.jsp" class="zc_btn" onclick="">忘记密码？</a>
        </p>
        <div class="loginBtn" style="margin-top: 40px">
            <button id="btnLogin" type="submit">登录</button>
        </div>
        <div style="margin:10px 10px 10px ;">
            <input type="checkbox" id="chkIfSave" class="checkbox" />
            <label for="chkIfSave" class="lable"> 保存一个月</label>
        </div>
        </form>
    </div>
</div>
<script type="text/javascript" src="static/js/zepto.min.js"></script>
<script type="text/javascript" src="static/js/layer.min.js"></script>
<script type="text/javascript" src="static/js/m_Common.js"></script>
<script type="text/javascript">
    Zepto(function($){ //html页面加载完成后执行的事件
        loadVerifyCode();

        $("#valideteCode").click(function() {
            loadVerifyCode();
        });

        $("#btnLogin").click(function () { //登录按钮添加事件
            //表单数据验证
            let username = $("#username");
            if (!username.val()) {
                layer.open({content: '请输入用户名/邮箱/手机号！', btn: ["确定"], shadeClose: false});
                $("div.msg").html("请输入用户名/邮箱/手机号！");
                return;
            }

            let pwd = $("#pwd");
            if (!pwd.val()) {
                layer.open({content: '请输入密码！', btn: ["确定"], shadeClose: false});
                $("div.msg").html("请输入密码！");
                return;
            }
        })
    })

    function loadVerifyCode() {
        //获取图片验证码
        hnieApi.xmxGet({url:"/verifyImage",isNotToken:true}).then(res => {
            const ret  = res.data;
            $("#valideteCode").attr("src","data:image/png;base64," + ret.img);
            $("#valideteToken").val(ret.uuid);
        });
    }
</script>
</body>
</html>
