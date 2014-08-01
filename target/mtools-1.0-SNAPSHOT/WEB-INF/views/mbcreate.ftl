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
                分析表结构生成Mybatis配置文件，配置文件中包含基本的增删改查操作
            </div>
            <div style="margin-top: 10px">
                <label for="tbSel">选择库：</label>
                <select class="selectpicker" data-style="btn-default" id="dbSel" style="width: 400px%">
                </select>
            </div>
            <div style="margin-top: 10px">
                <label for="tbSel">选择表：</label>
                <select class="selectpicker" data-style="btn-default" id="tbSel" style="width: 400px">
                </select>
            </div>

            <div class="input-group input-group-lg">
                <span class="input-group-addon" style="width: 130px">生成路径</span>
                <input type="text" id="pathInput"
                       class="form-control" placeholder="默认：C:\" style="width:500px">
            </div>
            <div class="input-group input-group-lg" style="margin-top: 5px">
                <span class="input-group-addon" style="width: 130px">包路径</span>
                <input type="text" id="packInput"
                       class="form-control" style="width: 500px" placeholder="默认：com.yermoon.entity">
            </div>
            <div class="btn-group btn-group-lg" style="margin-top: 5px">
                <button type="button" class="btn btn-primary" id="ctBtn" style="width: 100px">生 成</button>
            </div>
            <button class="btn btn-primary btn-lg" data-toggle="modal" style="margin-top: 5px" data-target="#myModal">
                添加数据源
            </button>
            <div class="panel panel-primary" style="margin-top: 10px">
                <div class="panel-heading" id="res">数据源</div>
                <div class="panel-body">
                    <table class="table table-bordered">
                        <thead>
                        <tr>
                            <th>操作</th>
                            <th>编号</th>
                            <th>库类型</th>
                            <th>连接地址</th>
                            <th>用户名</th>
                            <th>密码</th>
                        </tr>
                        </thead>
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
<!-- Modal -->
<div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                <h4 class="modal-title" id="myModalLabel">添加数据源</h4>
            </div>
            <div class="modal-body">
                <div style="margin-top: 10px">
                    <label for="dbSel">数据库类型：</label>
                    <select class="selectpicker" data-style="btn-default" id="dbTypeSel">
                        <option value="1">Mysql</option>
                        <option value="2">Oracle</option>
                        <option value="3">SqlServer</option>
                        <option value="4">Db2</option>
                    </select>
                </div>
                <div class="input-group input-group-lg">
                    <span class="input-group-addon" style="width: 130px">JdbcUrl</span>
                    <input type="text" id="urlInput"
                           class="form-control" placeholder="如：jdbc:mysql://127.0.0.1:3306/qc?characterEncoding=UTF-8"
                           style="width:400px">
                </div>
                <div class="input-group input-group-lg">
                    <span class="input-group-addon" style="width: 130px">用户名</span>
                    <input type="text" id="userInput"
                           class="form-control" placeholder="如：root" style="width:400px">
                </div>
                <div class="input-group input-group-lg">
                    <span class="input-group-addon" style="width: 130px">密码</span>
                    <input type="text" id="passInput"
                           class="form-control" placeholder="如：root" style="width:400px">
                </div>
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default" data-dismiss="modal">取 消</button>
                <button type="button" class="btn btn-primary" id="saveBtn">保 存</button>
            </div>
        </div>
        <!-- /.modal-content -->
    </div>
    <!-- /.modal-dialog -->
</div><!-- /.modal -->
<script src="<@spring.url relativeUrl='/statics/js/mybatis/create.js'/>" type="text/javascript"></script>
<#include "footer.ftl"  encoding="utf-8" />