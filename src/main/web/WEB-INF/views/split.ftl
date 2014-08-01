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
              用于长字符串拆分，防止看瞎眼。回车，粘贴，退格都会触发拆分
            </div>
            <div class="input-group input-group-lg">
                <span class="input-group-addon">字符串</span>
                <input type="text" oninput="split()" id="strInput" class="form-control" placeholder="字符串">
            </div>
            <div class="panel panel-primary" style="margin-top: 10px">
                <div class="panel-heading" id="res">解析结果</div>
                <div class="panel-body">
                    <table class="table table-bordered">
                        <tbody id="tbData">
                        </tbody>
                    </table>
                </div>

            </div>

        </div>
        <!--/span-->
    </div>
    <!--/row-->
</div><!--/.fluid-container-->
<script src="<@spring.url relativeUrl='/statics/js/string/split.js'/>" type="text/javascript"></script>
<#include "footer.ftl"  encoding="utf-8" />