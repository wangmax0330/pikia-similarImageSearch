
<#setting number_format="#">

<script id="_tpl_image_list_tbody" type="text/x-jquery-tmpl">
搜索结果
{{each rows}}
		<br />
		<br />
		<li class="draggable_log">
			<span>
				<div class="dataGridCell dataGridCellGood">
					<img style="width:156.0px;height:161.0px"src="/picSearch/upload/@{picUrl}"  alt="此图片丢失" />
					<br />
					
					<#if createTime??>${createTime?string("yyyy-MM-dd")}</#if>
					@{createTime}
				</div>	
			</span>
		</li>
{{/each}} 
</script>

<script id="_tpl_image_pagination" type="text/x-jquery-tmpl">
		<ul class="fr">
			<li {{if hasPrevious5Page}} onclick="imageList.imageListing(@{previousPage})"{{else}} class="disabled"{{/if}}>
			<a href="javascript:void(0);">&lt;&lt;</a></li>
			{{each(i,page) pages}}
				<li {{if page==currPage}} class="active" {{else}}onclick="imageList.imageListing(@{page})"{{/if}} >
				<a href="javascript:void(0);">@{page}</a></li>
			 {{/each}}
	    	<li {{if hasNext5Page}}onclick="goodList.goodListing(@{nextPage})"{{else}}class="disabled"{{/if}}><a href="javascript:void(0);">&gt;&gt;</a></li>
	   		<li class="disabled"><a href="#">总数：@{records}</a></li>
	   	</ul>
</script>

