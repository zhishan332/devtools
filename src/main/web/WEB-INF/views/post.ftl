<#include "header.ftl"  encoding="utf-8" />
<div class="container-fluid">
    <div class="row-fluid">
    <#include "left.ftl"  encoding="utf-8" />
        <!--/span-->
        <div class="span9">
            <ul class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">Http工具集</a></li>
                <li class="active"><a href="#">${pageName?if_exists}</a></li>
            </ul>
            <div class="alert alert-success">
                用于模拟Http的请求，目前支持Get和Post请求,编码使用的是utf-8,不同编码格式下可能会产生乱码。使用场景如测试Http接口。
                请求参数格式：name=ken&age=20&sex=both
            </div>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">请求URL</span>
                <input type="text"  id="urlInput"
                       class="form-control" placeholder="如:http://192.168.0.1:8061/ApiService.do">
            </div>
            <div class="input-group input-group-lg" style="margin-top: 10px">
                <span class="input-group-addon">请求参数</span>
                <input type="text"  id="paramInput"
                       class="form-control" placeholder="如：name=ken&age=20&sex=both">
            </div>
            <div style="margin-top: 10px">
                <select class="selectpicker" data-style="btn-default" id="methodSel">
                    <option value="1">POST</option>
                    <option value="2">GET</option>
                </select>
            </div>
            <div class="btn-group btn-group-lg">
                <button type="button" class="btn btn-primary" id="sendBtn" style="width: 100px">发 送</button>
            </div>
            <div class="panel panel-primary" style="margin-top: 10px">
                <div class="panel-heading" id="res">解析结果</div>
                <div class="panel-body">
                    <ul class="list-group" id="resUl">
                    </ul>
                </div>
            </div>

        </div>
        <!--/span-->
    </div>
    <!--/row-->
</div><!--/.fluid-container-->
<script src="<@spring.url relativeUrl='/statics/js/http/post.js'/>" type="text/javascript"></script>
<#include "footer.ftl"  encoding="utf-8" />