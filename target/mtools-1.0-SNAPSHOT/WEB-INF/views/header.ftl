<!DOCTYPE html>
<html lang="en" xmlns="http://www.w3.org/1999/xhtml">
<#import "/spring.ftl" as spring />
<head>
    <meta content="text/html; charset=UTF-8" http-equiv="Content-Type">
    <title>${pageName?if_exists}</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Bootstrap -->
    <link href="<@spring.url relativeUrl='/statics/css/plugins/bootstrap.min.css' />" type="text/css" rel="stylesheet">
    <#--<link rel="stylesheet" href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap.min.css">-->
    <link href="<@spring.url relativeUrl='/statics/css/plugins/bootstrap-responsive.css' />" type="text/css"
          rel="stylesheet">
    <link href="<@spring.url relativeUrl='/statics/css/plugins/special.css'/>" type="text/css" rel="stylesheet">
    <link href="<@spring.url relativeUrl='/statics/css/plugins/bootstrap-theme.css'/>" type="text/css" rel="stylesheet">
    <link href="<@spring.url relativeUrl='/statics/css/plugins/bootstrap-select.css'/>" type="text/css" rel="stylesheet">
    <link href="<@spring.url relativeUrl='/statics/css/yermoon.css'/>" type="text/css" rel="stylesheet">
    <#--<link rel="stylesheet" href="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/css/bootstrap-theme.min.css">-->
    <!-- HTML5 shim and Respond.js IE8 support of HTML5 elements and media queries -->
    <script src="http://lib.sinaapp.com/js/jquery/1.8.3/jquery.min.js" type="text/javascript"></script>
    <script src="<@spring.url relativeUrl='/statics/js/plugins/bootstrap.js'/>" type="text/javascript"></script>
    <#--<script src="http://cdn.bootcss.com/twitter-bootstrap/3.0.3/js/bootstrap.min.js"></script>-->
    <script src="<@spring.url relativeUrl='/statics/js/plugins/mumayi_top.js'/>" type="text/javascript"></script>
    <script src="<@spring.url relativeUrl='/statics/js/plugins/jquery.blockUI.js'/>" type="text/javascript"></script>
    <script src="<@spring.url relativeUrl='/statics/js/plugins/bootstrap-select.js'/>" type="text/javascript"></script>
    <script src="<@spring.url relativeUrl='/statics/js/yermoon.js'/>" type="text/javascript"></script>
    <style type="text/css">
        body {
            padding: 60px 20px 10px;
        }

        .sidebar-nav {
            padding: 9px 0;
        }

        @media (max-width: 980px) {
            /* Enable use of floated navbar text */
            .navbar-text.pull-right {
                float: none;
                padding-left: 5px;
                padding-right: 5px;
            }
        }
    </style>
</head>
<body>
<div class="navbar navbar-inverse navbar-fixed-top" style="margin-bottom: 5px!important;">
    <!-- Brand and toggle get grouped for better mobile display -->
    <div class="navbar-header">
        <a class="navbar-brand" href="<@spring.url relativeUrl='/index'/>" style="font-size: 20px;color: #0079d2">MTools</a>
    </div>
    <div class="collapse navbar-collapse">
        <ul class="nav navbar-nav " id="menu">
            </li>
            <li class="active" role="navigation"><a
                    href="">京东</a></li>
            <li class="" role="navigation"><a
                    href="">谷歌</a></li>
            <li class="" role="navigation"><a href="">Fuck my life</a></li>
            <li class="" role="navigation">
                <a href="">火狐</a></li>
            <li class="" role="navigation">
                <a href="">StackOverFlow</a></li>
        </ul>
        <form class="navbar-form navbar-left" role="search" method="get"
              action="">
            <div class="form-group">
                <input type="text" name="key" id="search_field" class="form-control" placeholder="试试看">
            </div>
            <button type="submit" id="search_btn" class="btn btn-primary">搜 索</button>
        </form>
        <ul class="nav navbar-nav navbar-right">
            <li class="dropdown">
                <a href="#" class="dropdown-toggle" data-toggle="dropdown">操作历史 <b class="caret"></b></a>
                <ul class="dropdown-menu">
                </ul>
            </li>
        </ul>
    </div>
</div>