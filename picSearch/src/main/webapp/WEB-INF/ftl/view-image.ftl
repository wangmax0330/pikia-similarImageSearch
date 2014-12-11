<#setting number_format="#">
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<#import "./res/macro.ftl" as calendarMacro>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>图片预览</title>
<script type="text/javascript" src="${rc.contextPath}/res/base/jquery/jquery-1.8.2.min.js"></script>
<script>
var size = "${size}".split(",");
var name = "${name}";
var imgPath = "<@calendarMacro.imgPath/>/uploads/";
$(function(){
	$.each(size,function(i,item){
		$(".container").append("<div><img alt='size:"+item+"' title='size:"+item+"' src='"+imgPath+item+name+"' /></div>");
	});
});
</script>
<style>
.container{background:#ddd;}
.container img{border:1px solid #aaa; margin-bottom:3px;}
</style>
</head>
<body>
	<div class="container" style="background:#ddd; width:502px;"> 
	</div>
</body>
</html>