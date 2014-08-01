<#include "header.ftl"  encoding="utf-8" />
<div class="container-fluid">
    <div class="row-fluid">
    <#include "left.ftl"  encoding="utf-8" />
        <!--/span-->
        <div class="span9">
            <ul class="breadcrumb">
                <li><a href="#">首页</a></li>
                <li><a href="#">Maven工具集</a></li>
                <li class="active"><a href="#">${pageName?if_exists}</a></li>
            </ul>
            <div class="alert alert-success">
                三方Jar包安装本地Maven仓库
            </div>
            <div class="input-group input-group-lg">
                <span class="input-group-addon" style="width: 130px">GroupId</span>
                <input type="text" id="grpInput"  style="width:500px"
                       class="form-control" placeholder="如:org.springframework">
            </div>
            <div class="input-group input-group-lg" style="margin-top: 10px">
                <span class="input-group-addon" style="width: 130px">ArtifactId</span>
                <input type="text" id="atInput"  style="width:500px"
                       class="form-control" placeholder="如：spring-core">
            </div>
            <div class="input-group input-group-lg" style="margin-top: 10px">
                <span class="input-group-addon" style="width: 130px">Version</span>
                <input type="text" id="vsInput"  style="width:500px"
                       class="form-control" placeholder="如：3.1.3.RELEASE">
            </div>
            <div class="input-group input-group-lg" style="margin-top: 10px;position:relative;">
                <span class="input-group-addon" style="width: 130px">jar路径</span>
                <input type="text" id="ptInput"   style="width:500px;padding: 10px"
                       class="form-control" placeholder="如：D:\download\junit.jar">
            </div>
            <div class="btn-group btn-group-lg" style="margin-top: 10px">
                <button type="button" class="btn btn-primary" id="installBtn" style="width: 100px">安 装</button>
            </div>
        </div>
        <!--/span-->
    </div>
    <!--/row-->
</div><!--/.fluid-container-->
<script src="<@spring.url relativeUrl='/statics/js/maven/impjar.js'/>" type="text/javascript"></script>
<#include "footer.ftl"  encoding="utf-8" />