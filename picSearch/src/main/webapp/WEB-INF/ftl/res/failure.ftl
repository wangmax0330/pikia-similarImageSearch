<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head><title>Exception!</title></head>
<style type='text/css'>
	.exception{text-align: center;font-size:16px;font-family:microsoft yahei;line-height:30px;color:#666;margin-top:80px;letter-spacing:1px}
	.exception img{vertical-align: -2px;margin-right:5px}
	.exception p{padding:20px 0;margin:0}
	.exception a{color:#08c; text-decoration: none;}
	.exception a:hover{text-decoration: underline;color:#06c}
</style>
<body>
<div class='exception'><img src='${rc.contextPath}/res/images/lock.png' ><p><#if false &&exception??>${exception.message!'您还没法访问，请联系管理员！'}</#if>抱歉...您没有访问权限，请联系管理员！</p>
	<span><a href='javascript:history.go(-1);'>返回</a></span>
</div>
</body>
</html>