<!--add new folder dialog being-->

<style>
	.uploadify-button{background:none;border:none}
	.uploadify{display: inline-block;}
</style>
<div class="work_dialog doc-upload-dialog-item"  style="display:none">
    <div class='work_dialog_hd'>
    <div class="work_dialog_titile">文档<div><a onclick="if(doc_upload_is_done || confirm('确定要终止上传吗？')){$('#doc-uploadify').uploadify('cancel');$('.work_dialog').removeClass('dialog_move_postion');$('.work_dialog').hide();$('.file_name_list').html('');}" class="cannel btn">关闭</a></div></div>
    </div>
    <div class="work_dialog_ct">
    	<div class='imPadding upload_center'>
        <p>你每次可以选择一个或多个文件，然后开始上传。</p>
        <div class='upload_word file_name_list'>
        </div>
        <div id='file_queque_div'></div>
        <div class='upload_word'>
        	<img id="doc-uploadify" src="${rc.contextPath}/res/images/upload_img.png"><span>支持DOC, PDF, XLS, PPT, TXT, 压缩文件，不超过20MB</span>
        </div>
        <div class='mTop10 alignRight'><a onclick="$('#doc-uploadify').uploadify('cancel');$('.work_dialog').removeClass('dialog_move_postion');$('.work_dialog').hide();$('.file_name_list').html('');" class='btn btn-primary'>完成</a></div>
        </div>
    </div>
</div>
<!--add new folder dialog end-->