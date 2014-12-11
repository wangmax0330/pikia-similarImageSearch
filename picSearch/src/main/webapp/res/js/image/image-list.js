var imageList = imageList || {};
$(function() {
	/** 列表 start---------------------------- * */
	imageList.imageListing = function(pageIndex) {
		$.ajax({
			url : contextPath + "/a/image/list",
			dataType : "json",
			data : {
				pageSize : 10,
				pageIndex : pageIndex
			},
			type : "post"
		}).done(
				function(data) {
					if (data.isSuc == 1) {
						$("#image_list_tbody").html(
								$("#_tpl_image_list_tbody").tmpl(data));
						$(".image-list-pagination").html(
								$("#_tpl_image_pagination").tmpl(data.pagi));
					}
				});
	};

	imageList.imageListing(1);

});