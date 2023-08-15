<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta content="text/html; charset=utf-8" http-equiv="Content-Type" />
    <title>大学生兼职平台</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" />
    <meta name="description" content="" />
    <meta name="Keywords" content="xxxx商城app," />
    <!--<meta name="apple-itunes-app" content="app-id=477927812" />-->
    <meta name="apple-mobile-web-app-capable" content="yes" />
    <meta name="apple-mobile-web-app-status-bar-style" content="black" />
    <meta name="location" content="province=;city=;coord=" />
    <meta name="format-detection" content="telephone=no" />

    <link rel="apple-touch-icon-precomposed" href="static/images/screenLogo.png" />
    <link rel="stylesheet" type="text/css" href="static/css/m2015.css" />
    <link rel="stylesheet" type="text/css" href="static/css/swiper.min.css" />
    <script src="static/js/zepto.min.js" type="text/javascript"></script>
    <script src="static/js/main.js" type="text/javascript"></script>
    <script src="static/js/swiper.min.js" type="text/javascript"></script>
    <style>
        .hot-news-roller dt { width: 75px; }
        .hot-news-roller dd { margin-right: 0; }
        .hot-news-roller dd ul li i {
            display: inline-block;
            border: solid 1px #f44623;
            color: #f44623;
            height: 18px;
            line-height: 18px;
            padding: 0 5px;
            border-radius: 5px;
            margin-right: 5px;
            font-size: 12px;
        }
        body{
            background-color: #FFFFFF;
        }
        .s{
            margin-top: 15px;
            border-bottom: 1px solid #F3F3F3;
            padding-bottom: 15px;   text-indent: 20px;
        }
        .sd
        {
            width: 80%;
            margin-top: 20px;
            margin-left: 20px;
            margin-right: 20px;
            height: 80px;
            background-color: #FAFAF9;
            display: flex;
            flex-wrap: wrap;
        }
    </style>
</head>

<body>
<div class="index_wrapper">
    <header>
        <div class="index-top">
            <h1 style="color:black;font-size: 20px;text-align: center;padding-top: 10px">管理中心</h1>
        </div>
    </header>
</div>
<div class="sd">
    <span>学生编号：</span>1
    <span>学生姓名：</span>张三
    <span>学生电话：</span>15173522772
    <span>学生地址：</span>湖南郴州
    <span>学生兼职信息：</span>服务员
    <div>
        <input value="删除" type="button"style="margin-left: 20px">
    </div>
</div>
<div class="sd">
    <span>学生编号：</span>2
    <span>学生姓名：</span>李四
    <span>学生电话：</span>15173522772
    <span>学生地址：</span>湖南郴州
    <span>学生兼职信息：</span>服务员
    <div>
        <input value="删除" type="button"style="margin-left: 20px">
    </div>
</div>
<div class="sd">
    <span>学生编号：</span>3
    <span>学生姓名：</span>王五
    <span>学生电话：</span>15173522772
    <span>学生地址：</span>湖南郴州
    <span>学生兼职信息：</span>服务员
    <div>
        <input value="删除" type="button"style="margin-left: 20px">
    </div>
</div>
<div class="sd">
    <span>学生编号：</span>1
    <span>学生姓名：</span>张三
    <span>学生电话：</span>15173522772
    <span>学生地址：</span>湖南郴州
    <span>学生兼职信息：</span>服务员
    <div>
        <input value="删除" type="button"style="margin-left: 20px">
    </div>
</div>

<div class="sd">
    <button type="button">企业管理</button>
</div>
<div class="foot-nav border_top hide" style="display: flex; justify-content: space-around">
    <a href="index.jsp"><i class="home" style="color: #f44263;"></i><span>首页</span></a>
    <a href="student.jsp"><i class="cate"></i><span>管理中心</span></a>
    <a href="person_index.jsp"><i class="user"></i><span>我的</span></a>
</div>
</body>
<script type="text/javascript">
    $(".foot-nav").show();
    $(".foot-nav a").eq(1).addClass("cur");
</script>

</html>
