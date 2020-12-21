layui.define(['table', 'form'], function(exports) {
    var $ = layui.$
        , admin = layui.admin
        , view = layui.view
        , table = layui.table
        , form = layui.form;


    table.render({
        id:"rolegroupTableContainer",
        elem: '#LAY-rolegroup-manage' //渲染id=myTable的表格
        ,url: '/roleGroup/list' //数据接口 //访问控制器，获得表格所需的数据
        ,where:{access_token: layui.data('layuiAdmin').access_token}
        ,height: 'full-230'
        ,page: true   //开启分页 （前端分页，后端分页）
        ,loading:true
        ,defaultToolbar: ['filter', 'print', 'exports']
        ,toolbar: '#toolbarDemo' //表示找到id=toolbarDemo的脚本代码，做为表格的头部工具按钮
        ,cols: [[ //表头
             {type:'checkbox'}//第一列为复选框列
            ,{field: 'groupid', title: 'ID', width:180, sort: true}
            ,{field: 'groupname', title: '权限组名称', width:180,sort:true,edit:'text',style:'color:green'}

            ,{fixed: 'right',title:'操作', width:180, align:'center', toolbar: '#rightbarDemo'} //这里的toolbar值是模板元素的选择器
        ]]
        ,done:function(){
            layer.msg("数据加载完毕");
        }
    });


    //监听查询按钮，把表单的查询条件 发送请求给服务端，
    //监听搜索
    form.on('submit(LAY-rolegroup-search)', function(data){
        var field = data.field;//表单元素值
        //执行重载
        table.reload('rolegroupTableContainer', {
            where: field//把查询表单的参数传递给后面
        });

    });

    //监听表格上方工具按钮的事件
    table.on('toolbar(LAY-rolegroup-manage)', function(obj){
        var checkStatus = table.checkStatus(obj.config.id);//获得选中行的数据

        console.log(checkStatus);


        console.log("lay-event的值为:"+obj.event)
        switch(obj.event){
            case 'add':



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
                    //  lay-filter="rolegroupFormFilter"
                    form.val("rolegroupFormFilter",rowData);  //rolegroupFormFilter 即 class="layui-form" 所在元素属性 lay-filter="" 对应的值
                    //给表单赋值元素赋值

                }

                break;
        }
    });





    //监听工具条
    table.on('tool(LAY-rolegroup-manage)', function(obj){ //注：tool 是工具条事件名，test 是 table 原始容器的属性 lay-filter="对应的值"
        var data = obj.data; //获得当前行数据
        var layEvent = obj.event; //获得 lay-event 对应的值（也可以是表头的 event 参数对应的值）
        if(layEvent === 'del'){ //删除
            layer.confirm('真的删除行么', function(index){
                obj.del(); //删除对应行（tr）的DOM结构，并更新缓存
                layer.close(index);
                //向服务端发送删除指令 待补充
            });
        }else  if(layEvent === 'setRole'){ //删除
            layer.msg("点击了设置权限按钮");
            admin.popup({
                title: '设置权限'
                ,area: ['500px', '550px']
                ,id: 'LAY-popup-rolegroup-add'
                ,success: function(layero, index){ //此处的index是弹出层的编号，可以做为layer.close(index)的参数关闭弹出层;
                    view(this.id).render('rolegroup/edit', null).done(function(){
                     console.log("当前行的数据为:");
                     console.log(data);

                        form.val("rolegroupFormFilter",data)
                        //监听保存按钮...
                        form.on("submit(setRoleFrom)",function(formdata){
                            //把当前行的权限组编号及，树节点中选中的节点编号传递给控制器。。。
                               console.log(formdata.field);
                            layer.msg("点击了保存，当前行的ID为:"+data.groupid);
                              admin.req({
                                  url:'/roleGroup/setRoleGroup',
                                  data:{groupid:data.groupid,menuids:formdata.field.menuids},
                                  success:function (serverData) {
                                      console.log("服务器返回的数据为:");
                                      console.log(serverData);
                                      if(serverData.code==0){
                                          layer.close(index);
                                      }

                                  }

                              });
                              return false;
                        })


                    });
                }
            });

        }
    });


    exports('rolegroup', {})
});