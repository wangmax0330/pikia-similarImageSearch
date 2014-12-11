var imageEdit = imageEdit || {};
$(function() {

	$("#hat_img_uploadify").uploadify(
			{
				'swf' : contextPath + '/res/js/upload/uploadify.swf',
				'uploader' : contextPath + '/d/image/pic/upload',
				'buttonImage' : contextPath + "/res/images/20131101143957.png",
				'auto' : true,
				'width' : 71,
				'height' : 25,
				buttonClass : "uploadify-btn",
				'fileTypeExts' : '*.jpg;*.png',
				'fileSizeLimit' : "600KB",
				'formData' : {},
				'onUploadSuccess' : function(fileObj, response, data) {
					var json = eval("(" + response + ")");
					var $img = $("form.form-test-edit .test-detail-pic-img");
					$img.attr("src", json.msg);
					$img.closest("a").attr(
							"href",
							contextPath + "/d/page/view-image?size=M&name="
									+ json.msg);
					// $("form.form-test-edit input[name='pic']").val(json.msg);
					var json = eval("(" + response + ")");
					// var $img = $("form.form-shop-edit .shop-detail-pic-img");
					// $img.attr("src", imgContextPath + "/uploads/M" +
					// json.msg);
					// $img.closest("a").attr(
					// "href",
					// contextPath + "/d/page/view-image?size=M&name="
					// + json.msg);
					$("form.form-image-edit input[name='pic']").val(json.msg);

				},
				'onUploadError' : function(event, queueID, fileObj) {
					alert("文件:" + fileObj.name + "上传失败");
				}
			});

});