<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8" />
    <title>个人中心 - 首页</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <meta name="description" content="" />
    <meta name="Keywords" content="" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="location" content="province=;city=;coord=" />
    <meta name="format-detection" content="telephone=no" />

    <link rel="apple-touch-icon-precomposed" href="static/images/screenLogo.png" />
    <link rel="stylesheet" type="text/css" href="static/css/m2015.css" />
    <link rel="stylesheet" type="text/css" href="static/css/common.css" />
    <script src="static/js/zepto.min.js" type="text/javascript"></script>
    <script src="static/js/main.js" type="text/javascript"></script>
    <style type="text/css">
        .vip-club ul {display: flex;justify-content: center; align-items: center}
        .vip-club ul li{flex:1}
        .vip-club ul li a{display: inline-block; width: 100%;font-size: 14px; color: black; text-align: center; padding: 8px 0;}

        .me{
            margin-top: 15px;
            border-bottom: 1px solid #F3F3F3;
            padding-bottom: 15px;   text-indent: 20px;

        }
        .me a span{
            color: black;

        }
    </style>
</head>

<body style="background-color: #FAFAF9">
<header id="header" style="">
    <div class="topbar" style="background-color: #ff6600" >
        <a href="javascript:history.back();" class="back_btn"><i class="iconfont"></i></a>
        <h1 class="page_title">个人中心</h1>
        <a href="javascript:;" class="top_home"><i class="iconfont"></i></a>
    </div>
</header>
<div class="vip-header">
    <a href="javascript:;">
        <dl>
            <dt>
                <img src="static/images/person_index/user2.png" />
            </dt>
            <dd>
                <h4 style="color: #222222;font-weight: bold">XXX</h4>
                <p style="color:#222222"> 郴州 | 21岁</p>
            </dd>
        </dl>
    </a>
    <ul>
        <li>
            <a href="javascript:;"><span>1</span>
                <p>我的收藏</p>
            </a>
        </li>
        <li>
            <a href="javascript:;"><span>0</span>
                <p>申请记录</p>
            </a>
        </li>
        <li>
            <a href="javascript:;"><span>0</span>
                <p>谁看过我</p>
            </a>
        </li>
    </ul>
</div>
<div class="me">
    <a href="javascipr:;"><span>用户查询</span></a>
</div>
<div class="me">
    <a href="javascipr:;"><span>企业查询</span></a>
</div>
<div class="me">
    <a href="javascipr:;"><span>我的通知</span></a>
</div>
<div class="me">
    <a href="javascipr:;"><span>薪资查询</span></a>
</div>
<div class="me">
    <a href="javascipr:;"><span>建议反馈</span></a>
</div>
<div class="me">
    <a href="javascipr:;"><span>我的账号</span></a>
</div>

<div class="me">
    <a href="javascipr:;"><span>退出登录</span></a>
</div>


<div class="foot-nav border_top hide" style="display: flex; justify-content: space-around">
    <a href="index.jsp"><i class="home"></i><span>首页</span></a>
    <a href="student.jsp"><i class="cate"></i><span>管理中心</span></a>
    <a href="person_index.jsp"><i class="user"></i><span>我的</span></a>
</div>

<script type="text/javascript">
    $(".foot-nav").show();
    $(".foot-nav a").eq(2).addClass("cur");
</script>
</body>
</html>
