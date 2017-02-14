/**
 * Created by user on 2016/12/20.
 */

var publishUrl='/article/publish.do';
jQuery(document).ready(function(){
    var editor=new wangEditor('contextDiv');
    editor.config.uploadImgUrl='/upload.do';
    editor.config.uploadImgFileName = 'file';
    editor.config.hideLinkImg = true;
    editor.create();

    jQuery("#publishSub").click(function(){
        // 获取编辑器区域完整html代码
        var html = editor.$txt.html();

        // 获取编辑器纯文本内容
        var text = editor.$txt.text();

        // 获取格式化后的纯文本
        var formatText = editor.$txt.formatText();
        var  title=jQuery("#title").val();
        var  description=jQuery("#description").val();
        if(title.length==0){
            alert("请输入标题");
        }else if(description.length==0){
            alert("请输入描述");
        }
        else if(formatText.length==0){
            alert("请输入正文！");
        }else{
            var param={
                title:title,
                text:html,
                description:description

            }
            jQuery.post(publishUrl,param,function(data){
                alert(data);
                window.location.href="/index.html";

            });
        }
    });


});