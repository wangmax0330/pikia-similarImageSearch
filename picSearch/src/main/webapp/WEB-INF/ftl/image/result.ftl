 <#setting number_format="#">
 <#include "../res/base.ftl">
 
 <script type="text/javascript" src="${rc.contextPath}/res/js/image/image-list.js"></script>
 
 <div style="padding:10px">
 
 						<div class="daraGridList">
									<ul>
										<li>
											<ul id="image_list_tbody">
												
											</ul>
										</li>
										<li class="gd_footer">
											<div class="dataGridCell">
												<div class="gd_column" style="width: 10%"></div>
												<div class="gd_column" style="width: 20%"></div>
												<div class="gd_column_center" style="width: 50%"></div>
												<div class="gd_column_center" style="width: 20%"></div>
											</div>
										</li>
									</ul>
									
									  <!--- page -->
									    <div class="pagination mg-top image-list-pagination">
									
									    </div>
									  <!--- page end -->
									    
						</div>
						
</div>

<#include "./result-tmpl.ftl">