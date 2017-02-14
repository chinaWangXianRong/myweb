// JavaScript Document
/*****************数据改变  ul 不变*************/
//根据数据写入 li
var reg1 = /index\/\d+/;
var reg2 = /\d+/;
clipInit=function (){
	pageCon=this.getPageCount();
	var curPage=this.getCurPage();
	 liTab=7;
	 medCur=Math.ceil(liTab/2);
	var str="";
	var start=1;
	str+="<ul>";
	str+="<li class='disbled' id='firstPage' onclick='FirstPage()'>首页</li>";
	str+="<li class='disbled' id='lastPage' onclick='LastPage()'>上一页</li>";
	str+="<div id='pageU' class='fl'>";
	if(pageCon<=liTab){
		for(var i=start;i<=pageCon;i++){
			str+="<li id='clip"+i+"' onclick='openUrl("+i+") '>"+i+"</li>";
		}
	}else{
		if(curPage<medCur){

		}else {
			if(curPage>=(pageCon-medCur)){
				start=pageCon-liTab+1;
			}else{
				start=curPage-(liTab-medCur);
			}
		}
		for(var i=start;i<start+liTab;i++){
			str+="<li id='clip"+i+"' onclick='openUrl("+i+") '>"+i+"</li>";
		}
	}



	str+="<li class='clear'></li>";
	str+="</div>";
	str+="<li class='BORDER' id='nextPage' onclick='NextPage()'>下一页</li>";
	str+="<li class='BORDER' id='endPage' onclick='EndPage()' style='border-right:1px solid #ccc'>尾页</li>";
	str+="<li class='clear'></li>";
	str+="</ul>";

	$("#clipDIV").html(str);
	pageInt('clip'+curPage,pageCon,medCur);
}
//设置当点击的值小于预设固定值
//单击事件  选择页数
clipPage=function (cur,page){
	var str="";
	for(var i=1;i<=page;i++){
		var liId="clip"+i;
		if(cur==i){
			$("#"+liId).attr("class","curPage");
		}else{
			$("#"+liId).attr("class","BORDER");
		}
		$("#"+liId).text(i);
	}
	pageControl(cur);

}
//设置的中转站，根据获取的值更改操作
pageInt=function (obj,page,medCur){
	var value=parseInt($("#"+obj).text());
	if(value < medCur){
		clipPage(value,page);
	}else if(value >= medCur){
		clipPageMax(value,page,medCur);
	}


}
//设置当获取的值大于预设固定值
clipPageMax=function (cur,page,medCur){
	var str="";
	var startNum=cur-medCur+1;
	var maxPage=startNum+parseInt(page)-1;
	if(maxPage<pageCon){
		for(var i=1;i<=page;i++){
			var liId="clip"+i;
			if(medCur==i){
				$("#"+liId).attr("class","curPage");
			}else{
				$("#"+liId).attr("class","BORDER");
			}
			$("#clip"+i).text(startNum);
			startNum++;
		}
	}else{
		var end = new RegExp(/\d+$/);
		var page=parseInt(end.exec(page));
		var curT=cur-pageCon+page;
		var maxP=pageCon;
		for(var i=page;i>=1;i--){
			var liId="clip"+i;
			if(curT==i){
				$("#"+liId).attr("class","curPage");
			}else{
				$("#"+liId).attr("class","BORDER");
			}
			$("#"+liId).text(maxP);
			maxP--;
		}
		
	}
	pageControl(cur);
}
//首页，尾页，上一页，下一页 的样式
pageControl=function (cur){
	if(cur==1){
		$("#firstPage").attr("class","disbled");
		$("#lastPage").attr("class","disbled");
		$("#nextPage").attr("class","BORDER");
		$("#endPage").attr("class","BORDER");
	}else if(cur==pageCon){
		$("#firstPage").attr("class","BORDER");
		$("#lastPage").attr("class","BORDER");
		$("#nextPage").attr("class","disbled");
		$("#endPage").attr("class","disbled");
	}else{
		$("#firstPage").attr("class","BORDER");
		$("#lastPage").attr("class","BORDER");
		$("#nextPage").attr("class","BORDER");
		$("#endPage").attr("class","BORDER");
	}
}
//第一页 显示
FirstPage=function (){
	window.location.href='/index.html';return false;
}
//尾页 显示
EndPage=function (){
	var page=this.getPageCount();
	window.location.href='/index/'+page+'.html';return false;
}
//上一页 显示
LastPage=function (){
	var page=this.getCurPage();
	page=Number(page)-1;
	this.openUrl(page);
}
//下一页 显示
NextPage=function (){
	var page=this.getCurPage();
	page=Number(page)+1;
	this.openUrl(page);
}

openUrl=function(page){
	window.location.href='/index/'+page+'.html';return false;
}

getCurPage=function(){
	var curPage=1;
	var url = window.location+"";
	var s=url.match(reg1);
	if(s!=null){
		 curPage= s.toString().match(reg2);
	}
	return curPage;
}
getPageCount=function(){
	var count=Number(jQuery("#count").val());
	var pageCon=Math.ceil(count/20);
	return pageCon;
}