<div class="main_list">
	 	<form class="form-image-edit" action="${rc.contextPath}/d/image/search" method="post">
	 		<input type="hidden" name="id" value="<#if test??&&test.id??>${test.id}<#else>0</#if>"/>
	 			<tr>
	 				<td>图片：</td>
	 				<td class='user_style addproject' colspan='2'>
	 					<a href="javascript:void(0)" target="_blank">
	 					<img class="detail-pic-img" style="max-width:100px;max-height:100px;vertical-align:middle;" src=""/>
	 					</a>
	 					<input type="file" name="Filedata" id="hat_img_uploadify" style="width:60px;height:30px;" value="上传" class="btn">
	 					<input type="hidden" name="pic"  value="">
	 					<label class="error">请上传图片!</label>	
        			</td>
	 			</tr>	
	 			<tr>
	 				<td>&nbsp;</td>
	 				<td colspan='2'>
	 					<input type="submit" class="btn btn-primary bt-submit-dis" createFlag = "<#if create?? && create>1<#else>0</#if>" value="<#if create?? && create>再来一次<#else>搜索</#if>" style="width:<#if create?? && create>160<#else>75</#if>px;height:30px;"/>
		 	   			<span class='loading'><img src="${rc.contextPath}/res/images/reflesh.gif"/>正在搜索...</span>
		 	   		</td>
	 			</tr>
	 		</table>
	    </form>          
</div>