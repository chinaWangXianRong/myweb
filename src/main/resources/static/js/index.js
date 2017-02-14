/**
 * Created by user on 2016/12/15.
 */
//退出
var logoutUrl="/user/quit.do";
jQuery(document).ready(function(){

    /*退出*/
    jQuery("#logout").click(function(){
        var param={}
        jQuery.post(logoutUrl,param,function(data){
            if(data==0){
                location.replace(location.href);
            }else{
                alert("退出失败，请联系管理员！");
            }
        });
    })
    /*联系管理员*/
    jQuery("#feedback").click(function(){
       alert("暂不完善，请发邮件至18910841832@qq.com，反馈您的意见！");
    })


});
