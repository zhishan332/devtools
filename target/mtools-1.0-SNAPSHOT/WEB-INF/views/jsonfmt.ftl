<#include "header.ftl"  encoding="utf-8" />
<div class="container-fluid">
    <div class="row-fluid">
    <#include "left.ftl"  encoding="utf-8" />
        <!--/span-->
        <div class="span9">
            <ul class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">字符串工具集</a></li>
                <li class="active"><a href="#">${pageName?if_exists}</a></li>
            </ul>
            <div class="alert alert-success">
                用于Json字符换的格式化,验证Json格式是否正确
            </div>
            <div class="btn-group btn-group-lg">
                <button type="button" class="btn btn-primary" id="formatBtn" style="width: 100px">格 式 化</button>
            </div>
            <span class="label label-success" id="okJson" style="display: none">有效JSON</span>
            <span class="label label-warning" id="noJson" style="display: none">JSON格式有误</span>
            <div class="panel panel-primary" style="margin-top: 10px">
                <div class="panel-heading" id="res">Json字符串</div>
                <div class="panel-body" style="padding: 1px">
                    <textarea id="jsonArea" rows="20" style="width: 100%;min-height: 100%;border: none"></textarea>
                </div>

            </div>

        </div>
        <!--/span-->
    </div>
    <!--/row-->
</div><!--/.fluid-container-->
<script src="<@spring.url relativeUrl='/statics/js/string/jsonfmt.js'/>" type="text/javascript"></script>
<#include "footer.ftl"  encoding="utf-8" />