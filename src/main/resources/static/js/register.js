/**
 * Created by user on 2016/12/6.
 */
var emailUrl="/user/sendMail.do";
var registerUrl="/user/register.do";
var isRegisterUser="/user/isRegisterUser.do";
var isGetEmailCode="/user/getEmailCode.do";
var isSub=true;
var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;
jQuery(document).ready(
    function(){
        /*发送邮件按钮*/
        jQuery("#sendMail").click(function () {
            var email=jQuery("#email").val();
            var nickName=jQuery("#nickName").val();
            if(email.length==0 || !myreg.test(email) ){
                jQuery("#ad").text('邮箱不能为空或者不正确！');
            }else if(nickName.length==0){
                jQuery("#ad").text('昵称不能为空');
            }else{
                var param={
                    email:email,
                    nickName:nickName
                }
                jQuery.get(emailUrl,param,function(data){
                   if(data==0){
                       alert("发送成功！");
                   }else if(data==1){
                       alert("发送失败！");
                   }else{
                       alert("系统异常，请联系管理员！");
                   }
                });
            }
        });

        /*注册确定按钮*/
        jQuery("#sub").click(function(){
            var nickName=jQuery("#nickName").val();
            var password=jQuery("#password").val();
            var email=jQuery("#email").val();
            var code=jQuery("#code").val();
            var param={
                nickName:nickName,
                password:password,
                email:email
            }
            if(nickName.length==0){
               /* alert("昵称不能为空");*/
                jQuery("#ad").text('昵称不能为空');
            }else if(password.length==0){
                jQuery("#ad").text('密码不能为空');
            }else if(email.length==0){
                jQuery("#ad").text('邮箱不能为空');
            }else if(code.length==0){
                jQuery("#ad").text('验证码不能为空');
            } else if(isSub==true){

                    jQuery.post(isGetEmailCode,{email:email},function(data){
                        if(data!=code.toUpperCase()){
                            jQuery("#ad").text('验证码错误');
                            isSub=false;
                        }else{
                            jQuery.post(registerUrl,param,function(data){
                                if(data==0){
                                    alert("注册成功！");
                                    window.location.href="/index.html";
                                }else if(data==1){
                                    jQuery("#ad").text('注册的邮箱已经存在！');
                                }else{
                                    alert("系统出现错误，请联系管理员");
                                }
                            });
                        }

                    });
            }
        });


        /*限时名称长度*/
        jQuery("#nickName").blur(function(){
            var len=jQuery("#nickName").val().length;
            if(len>10){
                jQuery("#ad").text('昵称不能大于10个字符！');
                isSub=false;
            }else{
                isSub=true;
                jQuery("#ad").text('');
            }
        });
        /*密码和确认密码*/
        /*验证邮箱*/
        jQuery("#email").blur(function(){

            var temp=jQuery("#email").val();
            if(temp.length!=0){
                if(!myreg.test(temp)){
                    jQuery("#ad").text('邮箱格式不正确，请输入正确邮箱！');
                    isSub=false;
                }else{
                    jQuery.post(isRegisterUser,{email:temp},function(data){
                        if(data==1){
                            jQuery("#ad").text('邮箱已经被注册，换个邮箱或找回密码');
                            isSub=false;
                        }else{
                            isSub=true;
                            jQuery("#ad").text('');
                        }
                    });
                }
            }
        });

        /*验证码*/
        jQuery("#code").blur(function(){
            var email=jQuery("#email").val();
            var code=jQuery("#code").val();
            if(code.length!=0){
                jQuery.post(isGetEmailCode,{email:email},function(data){
                    if(data!=code.toUpperCase()){
                        jQuery("#ad").text('验证码错误');
                        isSub=false;
                    }else{
                        jQuery("#ad").text('验证码正确');
                        isSub=true;
                    }

                });
            }
        });



    }
);