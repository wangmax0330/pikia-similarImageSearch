<#macro calendarHeader contextPath active>
<script>
var projectID = '${projectID!''}';
var userID = '${Session._session_user_key.id!''}';
</script>
	<!--header Begin-->
	<div id="header">
	     <div>
	        <h1 class="site_title"><a href="${rc.contextPath}/d/calendar/index"></a></h1>
	        <h2 class="section_title">
	           <ul class="hd_info">
	            <li><a href="${rc.contextPath}/d/user/index" ><img style="width:35px;height:35px" src="<#if Session._session_user_key.photo?? && Session._session_user_key.photo!=""><@calendarMacro.imgPath/>/uploads/L${Session._session_user_key.photo!''}<#else>${rc.contextPath}/res/images/user_small.png</#if>"></a></li>
	            <li class='<#if active==0>c_color</#if>'><a href="${rc.contextPath}/d/user/index" class=""   >${Session._session_user_key.userName!''}</a>&nbsp;<p class="work_msg_num hidden" style="cursor: pointer"><span></span><label>0</label></p><#if active==0><i></i></#if>
					<div class='face_daliog width300 message work_msg_list hidden'>
						<div class='face_bg'>
						<div class='face_arrow face_arrow_t'></div>
							<div class='face_content'>
								
							</div>
						</div>
					</div>
	            </li>
	            <#if Session._session_user_key?? && (Session._session_user_key.admin ||Session._session_user_key.editor)>
	            <li class="menu_1 <#if active==1>c_color</#if>">
	            <a class="" onclick="$('.loading_wp div p').html('<span class=colorBlue>小提示</span>：可以对促销活动进行系统管理');$('.loading_wp').show();" href="${rc.contextPath}/d/coupon/index"> 活动管理</a><#if active==1><i></i></#if>
	            </li>
	            </#if>
	            
	            <#if Session._session_user_key?? && Session._session_user_key.admin>
	             <li class="menu_1-1 <#if active==8>c_color</#if>">
	            <a class="" onclick="$('.loading_wp div p').html('<span class=colorBlue>小提示</span>：可以对商品、订单进行系统管理');$('.loading_wp').show();" href="${rc.contextPath}/d/goods/index">商城管理</a><#if active==8><i></i></#if>
	            </li>
	            </#if>
	            
	            <#if Session._session_user_key?? && Session._session_user_key.admin>
	            <li class="menu_myProject menu_2 <#if active==2>c_color</#if>">
	            <a class=""  onclick="$('.loading_wp div p').html('<span class=colorBlue>小提示</span>：对品牌、商铺进行系统管理');$('.loading_wp').show();" href="${rc.contextPath}/d/shop/index" >商家管理</a><#if active==2><i></i></#if>
	            </li>
	            </#if>
	            
	            <#if Session._session_user_key?? && (Session._session_user_key.admin ||Session._session_user_key.approver)>
	            <li class="menu_myProject menu_3 <#if active==3>c_color</#if>">
	            <a class=""  onclick="$('.loading_wp div p').html('<span class=colorBlue>小提示</span>：对任务、用户上传的小票进行管理');$('.loading_wp').show();" href="${rc.contextPath}/d/task/index" >小票管理</a><#if active==3><i></i></#if>
	            </li>
	            </#if>
	            <#if Session._session_user_key?? && (Session._session_user_key.admin ||Session._session_user_key.role==5)>
	            <li class="menu_11 <#if active==11>c_color</#if>">
	            	<a class="" onclick="$('.loading_wp div p').html('<span class=colorBlue>小提示</span>：对奖励进行管理');$('.loading_wp').show();" href="${rc.contextPath}/d/encourageManage/index">奖励管理</a><#if active==11><i></i></#if>
	            </li>
	            </#if>
	            <#if Session._session_user_key?? && (Session._session_user_key.admin ||Session._session_user_key.role==6)>
	            <li class="menu_9 <#if active==9>c_color</#if>">
	            	<a class="" onclick="$('.loading_wp div p').html('<span class=colorBlue>小提示</span>：对返利进行统计');$('.loading_wp').show();" href="${rc.contextPath}/d/assignment/index">返利统计</a><#if active==9><i></i></#if>
	            </li>
	            </#if>
	            <#if Session._session_user_key?? && Session._session_user_key.admin>
	            <li class="menu_6 <#if active==6>c_color</#if>">
	            <a class=""  onclick="$('.loading_wp div p').html('<span class=colorBlue>小提示</span>：反欺诈规则设置');$('.loading_wp').show();" href="${rc.contextPath}/d/antifraud/index" >反欺诈</a><#if active==6><i></i></#if>
	            </li>
	            </#if>
	            <#if Session._session_user_key?? && Session._session_user_key.admin>
	            <li class="menu_myProject menu_4 <#if active==4>c_color</#if>">
	            <a class=""  onclick="$('.loading_wp div p').html('<span class=colorBlue>小提示</span>：对图片识别的一些基础配置');$('.loading_wp').show();" href="${rc.contextPath}/d/ocr/index" >识别管理</a><#if active==4><i></i></#if>
	            </li>
	            </#if>
	            <#if Session._session_user_key?? && Session._session_user_key.admin>
	            <li class="menu_5 <#if active==5>c_color</#if>">
	            	<a class="" onclick="$('.loading_wp div p').html('<span class=colorBlue>小提示</span>：公司管理员进行系统参数集中设置');$('.loading_wp').show();" href="${rc.contextPath}/d/sys/admin">系统管理</a><#if active==5><i></i></#if>
	            </li>
	            </#if>
	           
	             
	           
	            <!--li class="menu_6">
	            <a class="password-update">修改密码</a>
	            </li-->
	            <li class="menu_7">
	            <a href="${rc.contextPath}/d/d-front/logout">退出</a>
	            </li>
	          </ul>
	        </h2>
	  	</div>
	</div>
	<!--header End-->
	
	<script id="_tpl_message_top5_list_row" type="text/x-jquery-tmpl">
		<li class='{{if opType=="1"}}rw_ico{{else opType=="2"}}ico_folder{{else opType=="3"}}icn_new_article{{else opType=="4"}}icn_new_article{{else opType=="5"}}icn_categories{{/if}}'><p><a class='message_name' onclick="$('.work_msg_list').addClass('hidden');" href="${rc.contextPath}/d/user/index#msg-info.html!mid=@{id}" title="@{content}">@{title}</a></p>
			<p>@{sender}  &nbsp;( @{genTime})</p>
			<div class='mes_btn'>
			{{if  btns.length>0}}
			{{each(i,btn) btns}}
                <a msg-id="@{id}"  idx = "@{i}"  class='btn'>@{btn}</a>
            {{/each}}
            {{else}}
            	<a msg-id="@{id}" idx = "-1" class='btn'>已读</a>
            {{/if}}
            </div>
		</li>
	</script>
	
	
</#macro>
<#macro secondary_bar contextPath>
	<!--secondary_bar Begin-->
	<div id="secondary_bar">
	    <div class="user">
	   		 <p>${Session._session_user_key.userName!''}</p>
	    </div>
	   <div class="breadcrumbs_container">
	   		<div class='goBack' onclick="javascript:history.back(-1);"></div>
	    	<div class="breadcrumbs sync_icon_div" style="display:none"> <a href="#1"><img src="${rc.contextPath}/res/images/reflesh.gif"></a> </div>
	    	<span id="targetFixed" class="target_fixed"></span>
	    	<div class="welcome info"><img src="${rc.contextPath}/res/images/icn_alert_info.png"><span>欢迎使用work平台，我们将全心全意为您服务！</span></div>
	    	<div class="welcome errMsg"><img src="${rc.contextPath}/res/images/icn_alert_error.png"><span></span></div>
	    	<div class="welcome sucMsg"><img src="${rc.contextPath}/res/images/icn_alert_success.png"><span></span></div>
	   </div>
	   
	</div>
	<!--secondary_bar Ends-->
</#macro>
<#macro footer_left contextPath>
    <div class="footer_left">
   		<ul>
          <li><a href="#1"><img src="${rc.contextPath}/res/images/m1.png"></a></li>
          <li><a class="submenu" ><img src="${rc.contextPath}/res/images/m2.png"></a>
              <div class="drop_menu_div" style="display:none">
                <ul>
                    <li><a href="#1">新情境...</a></li>
                    <li><a href="#1">新项目...</a></li>
                    <li><a href="#1">新目标...</a></li>
                </ul>
                <i></i>
               </div>
          </li>
          <li ><a href="#1"><img src="${rc.contextPath}/res/images/m3.png"></a></li>
        </ul>
    </div>
</#macro>

<#macro load_day>
 <input type="hidden" value="" class="day_start" />
  <input type="hidden" value="" class="day_end" />
  <input type="hidden" value="" class="day_today" />
  <input type="hidden" value="" class="day_chose" />
</#macro>
<#macro dataSelector single param...>
    <div class='add_workgrounp <#if single>single</#if>' style="background:#fff;margin:0;" >
    	<div class='grounp_add'>
    		<input class="selectedInput" id="${param["inputId"]}" name='${param["inputId"]}' value="${param["initIds"]}" type="hidden">
    		<a class="add_idcon" func="${param["func"]!''}"><i></i><#if param["title"]??>${param["title"]}<#else>用户</#if></a>
    		<a><input class="dataSelectorInput" action="${param["action"]}" style="border: none;height: 22px;" type='text' value=''></input></a> 
    	</div>
    	<div class="cwidthdiv" style="display:none;"></div>
    </div>
    <script>
    	var initIds = '${param["initIds"]}'.split(",");
    	var initVals = '${param["initVals"]}'.split(",");
    	if('${param["initIds"]}'!=""){
	    	var $add_idconInit = $("#${param["inputId"]}").closest(".add_workgrounp").find(".add_idcon");
	    	$.each(initIds,function(i){
	    		$add_idconInit.before("<span class='z' did='"+initIds[i]+"'>"+initVals[i]+"<i class='del-dataSelector'></i></span>");
	    	});
    	}
    </script>
</#macro>
<#macro loadingPage contextPath>
<div class='loading_wp'>
	<div>
		<img src='${contextPath}/res/images/loading.gif' />
		<p><span class='colorBlue'>小提示</span>：拍拍返利,APP后台管理系统!</p>
	</div>
</div>
</#macro>

<#macro amountTypeSelect selName selIdx>
<select name="${selName}">
 	<option value="-1" <#if selIdx?? && selIdx==-1>selected</#if>>普通</option>
 	<option value="0" <#if selIdx?? && selIdx==0>selected</#if>>通用</option>
 	<option value="1" <#if selIdx?? && selIdx==1>selected</#if>>抽奖专用</option>
 	<option value="2" <#if selIdx?? && selIdx==2>selected</#if>>英雄联盟游戏币</option>
</select>
</#macro>

<#macro imgPath>http://img.p1pai.com</#macro>
