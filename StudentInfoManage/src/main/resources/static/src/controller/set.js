/**

 @Name：layuiAdmin 设置
 @Author：贤心
 @Site：http://www.layui.com/admin/
 @License: LPPL
    
 */
 
layui.define(['form', 'upload'], function(exports){
  var $ = layui.$
  ,layer = layui.layer
  ,laytpl = layui.laytpl
  ,setter = layui.setter
  ,view = layui.view
  ,admin = layui.admin
  ,form = layui.form
  ,upload = layui.upload;

  var $body = $('body');
  admin.req(
      {

        url:'/operator/getName',
        success:function(serverData){
          form.val("OperatorInfoForm",{
            "loginname":serverData.data.loginname,
              "role":serverData.data.roleGroupBeans.groupname
          });


        }

      }


  );

  form.render();
  
  //自定义验证
  form.verify({
    nickname: function(value, item){ //value：表单的值、item：表单的DOM对象
      if(!new RegExp("^[a-zA-Z0-9_\u4e00-\u9fa5\\s·]+$").test(value)){
        return '用户名不能有特殊字符';
      }
      if(/(^\_)|(\__)|(\_+$)/.test(value)){
        return '用户名首尾不能出现下划线\'_\'';
      }
      if(/^\d+\d+\d$/.test(value)){
        return '用户名不能全为数字';
      }
    }
    
    //我们既支持上述函数式的方式，也支持下述数组的形式
    //数组的两个值分别代表：[正则匹配、匹配不符时的提示文字]
    ,pass: [
      /^[\S]{6,12}$/
      ,'密码必须6到12位，且不能出现空格'
    ]
    
    //确认密码
    ,repass: function(value){
      if(value !== $('#LAY_password').val()){
        return '两次密码输入不一致';
      }
    }
  });
  
  //网站设置
  form.on('submit(set_website)', function(obj){
    layer.msg(JSON.stringify(obj.field));
    
    //提交修改
    /*
    admin.req({
      url: ''
      ,data: obj.field
      ,success: function(){
        
      }
    });
    */
    return false;
  });
  
  //邮件服务
  form.on('submit(set_system_email)', function(obj){
    layer.msg(JSON.stringify(obj.field));
    
    //提交修改
    /*
    admin.req({
      url: ''
      ,data: obj.field
      ,success: function(){
        
      }
    });
    */
    return false;
  });
  
  
  //设置我的资料
  form.on('submit(setmyinfo)', function(obj){

    
    //提交修改

    admin.req({
      url: '/operator/save'
      ,data: obj.field
      ,success: function(serverData){
        layer.msg("提交成功");
        console.log(serverData);
      }
    });

    return false;
  });

  //上传头像

//普通图片上传

  var uploadInst = upload.render({
    elem: '#LAY_avatarUpload'
    ,url: '/operator/upload' //改成您自己的上传接口
    ,before: function(obj){
      //预读本地文件示例，不支持ie8

      obj.preview(function(index, file, result){
        $('#LAY_avatarSrc').attr('value', result); //图片链接（base64）

      });



      this.data={'loginname':$('#LOGINNAME').val()};
    }
    ,done: function(res){
      //如果上传失败
      if(res.code ==500){

        return layer.msg('文件已存在');
      }
      //上传成功
      $('#img_url').attr('src',res.data.src);
      layer.msg("上传成功");
    }
    ,error: function(){
      //演示失败状态，并实现重传
      var demoText = $('#demoText');
      demoText.html('<span style="color: #FF5722;">上传失败</span> <a class="layui-btn layui-btn-xs demo-reload">重试</a>');
      demoText.find('.demo-reload').on('click', function(){
        uploadInst.upload();
      });
    }
  });



  //查看头像
  admin.events.avartatPreview = function(othis){
    var LAY_avatarSrc = $('#LAY_avatarSrc');
    var src=LAY_avatarSrc.val();

    layer.photos({
      photos: {
        "title": "查看头像" //相册标题
        ,"data": [{
          "src": src //原图地址
        }]
      }
      ,shade: 0.01
      ,closeBtn: 1
      ,anim: 5
    });
  };
  
  
  //设置密码
  form.on('submit(setmypass)', function(obj){
    layer.msg(JSON.stringify(obj.field));
    
    //提交修改
    /*
    admin.req({
      url: ''
      ,data: obj.field
      ,success: function(){
        
      }
    });
    */
    return false;
  });
  
  //对外暴露的接口
  exports('set', {});
});