layui.define(['table', 'form'], function(exports) {
    var $ = layui.$
        , admin = layui.admin
        , view = layui.view
        , table = layui.table
        , form = layui.form;

    table.render({
        id:"usersTableContainer",
        elem: '#LAY-users-manage' //渲染id=myTable的表格
        ,url: '/user/list' //数据接口 //访问控制器，获得表格所需的数据
        ,where:{access_token: layui.data('layuiAdmin').access_token}
        ,height: 'full-230'
        ,page: true   //开启分页 （前端分页，后端分页）
        ,loading:true
        ,defaultToolbar: ['filter', 'print', 'exports']
        ,toolbar: '#toolbarDemo' //表示找到id=toolbarDemo的脚本代码，做为表格的头部工具按钮
        ,cols: [[ //表头
            {type:'checkbox'}//第一列为复选框列
            ,{field: 'loginid', title: 'ID', width:180, sort: true}
            ,{field: 'loginname', title: '登录名', width:180,sort:true,edit:'text',style:'color:green'}
            ,{ title: '所属权限组', width:180, sort: true,templet: '#groupNames'}//templet: '#groupNames' 表示找到页面中的id=groupNames的元素，在元素内部用layui的模板引擎把json数据取出来显示


            ,{fixed: 'right',title:'操作', width:50, align:'center', toolbar: '#rightbarDemo'} //这里的toolbar值是模板元素的选择器
        ]]
        ,done:function(){
            layer.msg("数据加载完毕");
        }
    });


    //监听查询按钮，把表单的查询条件 发送请求给服务端，
    //监听搜索
    form.on('submit(LAY-users-search)', function(data){
        var field = data.field;//表单元素值
        //执行重载
        table.reload('usersTableContainer', {
            where: field//把查询表单的参数传递给后面
        });

    });

    //监听表格上方工具按钮的事件
    table.on('toolbar(LAY-users-manage)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);//获得选中行的数据

        console.log(checkStatus);


        console.log("lay-event的值为:"+obj.event)
        switch(obj.event){
            case 'add':

                saveType ='add';
                //把operator/edit.html页面显示在后面管理的弹出层中
                admin.popup({
                    title: '添加用户'
                    ,area: ['500px', '450px']
                    ,id: 'LAY-popup-users-add'
                    ,success: function(layero, index){ //此处的index是弹出层的编号，可以做为layer.close(index)的参数关闭弹出层;
                        view(this.id).render('user/change', null).done(function(){

                            //获得roleGroup表中的数据，动态的的写入到selec下拉列表中，然后再更新渲染
                            admin.req(
                                {
                                    url: '/roleGroup/list',
                                    success:function(serverData){//服务 器处理成功的回调函数，服务端返回的数据放在serverData变量中
                                        console.log("服务端返回的数据为:"+serverData);
                                        console.log(serverData);
                                        //当服务 端返回的json的code值 不为0时，会自动取msg值做为错误提示信息显示给用户看
                                        if (serverData.code==0){
                                            //刷新表格数据
                                            ////{"code":0,"msg":"查询成功","count":2,"data":[{"groupid":"g1","groupname":"超级管理员","description":null},{"groupid":"g2","groupname":"辅导员组","description":null}]}
                                            //
                                            //获得 服务 端返回的data值，取出groupid  groupname,动态添加到所属权限组下拉列表中
                                            for(var i=0;i<serverData.data.length;i++){
                                                var roleGroupObj =  serverData.data[i];//{"groupid":"g1","groupname":"超级管理员","description":null}
                                                //找到select元素
                                                $("#roleGroupSelect").append("<option value='"+roleGroupObj.groupid+"'>"+roleGroupObj.groupname+"</option>")
                                            }

                                            form.render('select'); //刷新select选择框渲染
                                        }
                                    }
                                });



                            //监听保存按钮事件
                            form.on("submit(addUsersFrom)", function(data) {
                                console.log(data.elem) //被执行事件的元素DOM对象，一般为button对象
                                console.log(data.form) //被执行提交的form对象，一般在存在form标签时才会返回
                                console.log(data.field) //当前容器的全部表单字段，名值对形式：{name: value}
                                layer.msg("点击了保存按钮 ");
                                //之前是用$.get jquery的异步请求发送数据给服务
                                //在layui.admin管理框架中，用admin.req 方法实现 异步请求
                                admin.req(
                                    {
                                        url: '/user/add' ,
                                        data:data.field,
                                        success:function(serverData){//服务 器处理成功的回调函数，服务端返回的数据放在serverData变量中
                                            console.log("服务端返回的数据为:"+serverData);
                                            console.log(serverData);
                                            //当服务 端返回的json的code值 不为0时，会自动取msg值做为错误提示信息显示给用户看
                                            if (serverData.code==0){
                                                //刷新表格数据
                                                table.reload("usersTableContainer");
                                                layer.close(index);
                                            }
                                        }
                                    });

                                return false;//阻止页面跳转
                            })


                        });
                    }
                });

                break;
            case 'delete':
                layer.msg('删除');
                layer.confirm('真的删除行么', function(index){

                    layer.close(index);
                    //向服务端发送删除指令，删除后再重新加载一次表格实现刷新

                });
                break;
            case 'update':
                layer.msg('编辑');
                saveType = 'edit';
                if (checkStatus.data.length!=1){
                    layer.msg("请选择一行要编辑的数据")
                }else{
                    var rowData = checkStatus.data[0];//因只能选中一行数据，可以把data中的第一个元素值 做为当前选中行的值
                    console.log("-----------------------------");
                    console.log(rowData);
                    //  lay-filter="operatorFormFilter"
                    form.val("usersFormFilter",rowData);  //operatorFormFilter 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
                    //给表单赋值元素赋值

                }

                break;
        }
    });





    //监听工具条
    table.on('tool(LAY-operator-manage)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        if(layEvent === 'del'){ //删除
            layer.confirm('真的删除行么', function(index){
                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                layer.close(index);
                //向服务端发送删除指令 待补充
            });
        }
    });


    exports('users', {})
});