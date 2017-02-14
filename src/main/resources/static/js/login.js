/**
 * Created by user on 2016/12/15.
 */
var url="/user/login.do";
jQuery(document).ready(function(){
        jQuery("#sub").click(function () {
            var param ={
                email:jQuery("#email").val(),
                password:jQuery("#password").val()
            }
            jQuery.post(url,param,function(data){
                if(data==0){

                    window.location.href="/index.html";
                }else{
                    alert("你输入的账号或密码不正确！");
                }
            });
        })
}
);