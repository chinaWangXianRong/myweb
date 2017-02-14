/**
 * Created by user on 2017/1/6.
 */

var isRegisterUser="/user/isRegisterUser.do";
var findPwd="/user/findPwd.do";

var myreg = /^([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+@([a-zA-Z0-9]+[_|\_|\.]?)*[a-zA-Z0-9]+\.[a-zA-Z]{2,3}$/;

jQuery(document).ready(function(){
    /*验证邮箱*/
    jQuery("#email").blur(function(){
        var email=jQuery("#email").val();
        if(email.length!=0){
            if(!myreg.test(email)){
                jQuery("#ad").text("邮箱地址错误！");
            }else{
                jQuery("#ad").text("");
            }
        }
    });

    /*确定查找*/
    jQuery("#sub").click(function(){
        var email=jQuery("#email").val();
        if(email.length==0){
            jQuery("#ad").text("邮箱不能为空");
        }else{
            jQuery.post(isRegisterUser,{email:email},function(data){
                if(data==0){
                    jQuery("#ad").text("邮箱未注册！");
                }else{
                    jQuery("#ad").text("");
                    jQuery.post(findPwd,{email:email},function(data){
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
        }
    });

});