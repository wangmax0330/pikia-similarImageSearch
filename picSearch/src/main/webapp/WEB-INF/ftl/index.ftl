<#include "/res/base.ftl">
<#setting number_format="#">
<link href="${rc.contextPath}/res/js/upload/css/default.css" rel="stylesheet" type="text/css" />
<link href="${rc.contextPath}/res/js/upload/css/uploadify.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="${rc.contextPath}/res/js/upload/swfobject.js"></script>
<script type="text/javascript" src="${rc.contextPath}/res/js/upload/jquery.uploadify-3.1.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/res/js/jquery/jquery.ba-hashchange.min.js"></script>
<script type="text/javascript" src="${rc.contextPath}/res/js/image/index.js"></script>


<style>
	.error{padding:3px 0 3px 20px; width:210px;display:none;color:red}
	.loading {color: #999999; display: none;font-size: 12px;margin-left: 10px;}
	
	.montage_list {height:470px ;weight:100%; margin-top:15px;}
	.result_list {clear:both;padding-top:15px }
</style>

<script type="text/javascript">
	var contextPath = '${rc.contextPath}';
	var imgContextPath = 'http://img.p1pai.com';
</script>
	 
		<div class='active_menu'>
	 		<div><span>上传</span></div>
		</div>	 
	    <div class="upload_list">
		 	<#include "index_componet_upload.ftl">
		</div>	

   
