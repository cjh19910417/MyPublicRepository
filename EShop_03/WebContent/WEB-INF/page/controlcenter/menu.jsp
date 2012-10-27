<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/page/share/taglib.jsp" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<base target="mainframe">

<style type="text/css"> 
<!-- 
*{margin:0;padding:0;border:0;} 
body { 
    font-family: arial, 微软雅黑, serif; 
    font-size:12px; 
} 
#nav { 
    width:180px; 
    line-height: 24px;  
    list-style-type: none; 
    text-align:left; 
    /*定义整个ul菜单的行高和背景色*/ 
} 

/*==================一级目录===================*/ 
#nav a { 
	color:highlight;
    width: 180px;  
    display: block; 
    padding-left:10px; 
    cursor: pointer;
    /*Width(一定要)，否则下面的Li会变形*/ 
} 

#nav li { 
    background:#CCC; /*一级目录的背景色*/ 
    border-bottom:#F1F1F1 3px solid; /*下面的一条白边*/ 
    float:left; 
    /*float：left,本不应该设置，但由于在Firefox不能正常显示 
    继承Nav的width,限制宽度，li自动向下延伸*/ 
} 

#nav li a:hover{ 
    background:#CC0000;    /*一级目录onMouseOver显示的背景色*/ 
} 

#nav a:link  { 
    color:#666; text-decoration:none; 
} 
#nav a:visited  { 
    color:#666;text-decoration:none; 
} 
#nav a:hover  { 
    color:#FFF;text-decoration:none;font-weight:bold; 
} 

/*==================二级目录===================*/ 
#nav li ul { 
    list-style:none; 
    text-align:left; 
} 
#nav li ul li{     
    background: #EBEBEB; /*二级目录的背景色*/ 
} 

#nav li ul a{ 
         padding-left:20px; 
         width:180px; 
    /* padding-left二级目录中文字向右移动，但Width必须重新设置=(总宽度-padding-left)*/ 
} 

/*下面是二级目录的链接样式*/ 

#nav li ul a:link  { 
    color:#666; text-decoration:none; 
} 
#nav li ul a:visited  { 
    color:#666;text-decoration:none; 
} 
#nav li ul a:hover { 
    color:#F3F3F3; 
    text-decoration:none; 
    font-weight:bold; 
    background:#CC0000; 
    /* 二级onmouseover的字体颜色、背景色*/ 
} 

/*==============================*/ 
#nav li:hover ul { 
    left: auto; 
} 
#nav li.sfhover ul { 
    left: auto; 
} 
#content { 
    clear: left;  
} 
#nav ul.collapsed { 
    display: none; 
} 
--> 

#PARENT{ 
    width:300px; 
    padding-left:0px; 
} 
</style> 
</head>
<body leftmargin="0" topmargin="0" bgcolor="#F1F1F1"><br>







<div id="PARENT"> 
<ul id="nav"> 
<!-------------------------网站产品管理START-----------------------------
<table border=0 width="98%" align="center" cellspacing="0" cellpadding="0">
  <tr> 
    <td colspan="3" language="JavaScript" onMouseUp="turnit('product')" style="CURSOR: hand"> 
      <img id="Imgproduct" src="/images/midclosedfolder.gif" align="absMiddle" border="0" width="16"><img name="Imgproduct_0" src="/images/clsfld.gif" align="absMiddle" border="0"> 
        <font face=宋体><b>产品管理</b></font> 
    </td>
  </tr>
</table>
<table id="menu_product" border=0 width="98%" align="center" cellspacing="0" cellpadding="0" style="display:none">

  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action="/control/product/list"/>">产品管理</a> </td>
  </tr>
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/product/type/list'/>">产品类别管理</a> </td>
  </tr>
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action="/control/product/brand/list"/>">产品品牌管理</a> </td>
  </tr>
  
</table>
<!-------------------------网站产品管理END---------------------------->
<li><a style=" margin-bottom:3px; font-weight: bold;font-size: 15px; padding-bottom: 5px;padding-top: 5px;" onclick="DoMenu('ChildMenu1')">产品管理</a> 
    <ul id="ChildMenu1" class="collapsed"> 
    <li><a href="<html:rewrite action="/control/product/list"/>">产品管理</a></li> 
    <li><a href="<html:rewrite action='/control/product/type/list'/>">产品类别管理</a> </li> 
    <li><a href="<html:rewrite action="/control/product/brand/list"/>">产品品牌管理</a>  </li> 
    </ul> 
</li> 
<!-------------------------网站文件管理START-----------------------------
<table border=0 width="98%" align="center" cellspacing="0" cellpadding="0">
  <tr> 
    <td colspan="3" language="JavaScript" onMouseUp="turnit('uploadfile')" style="CURSOR: hand"> 
      <img id="Imguploadfile" src="/images/midclosedfolder.gif" align="absMiddle" border="0" width="16"><img name="Imguploadfile_0" src="/images/clsfld.gif" align="absMiddle" border="0"> 
        <font face=宋体><b>文件管理</b></font> 
    </td>
  </tr>
</table>
<table id="menu_uploadfile" border=0 width="98%" align="center" cellspacing="0" cellpadding="0" style="display:none">
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/uploadfile/list'/>">上传文件管理</a> </td>
  </tr>
  
</table>
<!-------------------------网站文件管理END----------------------------->
<li><a style="font-weight: bold;font-size: 15px; padding-bottom: 5px;padding-top: 5px;" onclick="DoMenu('ChildMenu2')">文件管理</a> 
    <ul id="ChildMenu2" class="collapsed"> 
    <li><a href="<html:rewrite action='/control/uploadfile/list'/>">上传文件管理</a></li> 
    </ul> 
</li> 


<!-------------------------用户管理START------------------------------

<table border=0 width="98%" align="center" cellspacing="0" cellpadding="0">
  <tr> 
    <td colspan="3" language="JavaScript" onMouseUp="turnit('user')" style="CURSOR: hand"> 
      <img id="Imguser" src="/images/midclosedfolder.gif" align="absMiddle" border="0" width="16"><img name="Imguser_0" src="/images/clsfld.gif" align="absMiddle" border="0"> 
        <font face=宋体><b>用户管理</b></font> 
    </td>
  </tr>
</table>
<table id="menu_user" border=0 width="98%" align="center" cellspacing="0" cellpadding="0" style="display:none">
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/user/list'/>">网页用户管理</a> </td>
  </tr>
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/user/query'/>">网页用户查询</a> </td>
  </tr>
 </table>
<!-------------------------用户管理END------------------------------>

<li><a style="font-weight: bold;font-size: 15px; padding-bottom: 5px;padding-top: 5px;" onclick="DoMenu('ChildMenu3')">用户管理</a> 
    <ul id="ChildMenu3" class="collapsed"> 
    <li><a href="<html:rewrite action='/control/user/list'/>">网页用户管理</a></li> 
    <li> <a href="<html:rewrite action='/control/user/query'/>">网页用户查询</a></li> 
    </ul> 
</li> 


<!-------------------------订单管理START-----------------------------

<table border=0 width="98%" align="center" cellspacing="0" cellpadding="0">
  <tr> 
    <td colspan="3" language="JavaScript" onMouseUp="turnit('order')" style="CURSOR: hand"> 
      <img id="Imgorder" src="/images/midclosedfolder.gif" align="absMiddle" border="0" width="16"><img name="Imgorder_0" src="/images/clsfld.gif" align="absMiddle" border="0"> 
        <font face=宋体><b>订单管理</b></font> 
    </td>
  </tr>
</table>
<table id="menu_order" border=0 width="98%" align="center" cellspacing="0" cellpadding="0" style="display:none">
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/order/query'/>">订单查询</a> </td>
  </tr>
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/order/list'/>">待审核订单</a> </td>
  </tr>
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/order/list?state=WAITPAYMENT'/>">等待付款订单</a> </td>
  </tr>
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/order/list?state=ADMEASUREPRODUCT'/>">正在配货订单</a> </td>
  </tr>
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/order/list?state=WAITDELIVER'/>">等待发货订单</a> </td>
  </tr>
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/order/list?state=DELIVERED'/>">已发货订单</a> </td>
  </tr>
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/order/list?state=RECEIVED'/>">已收货订单</a> </td>
  </tr>
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/order/list?state=CANCEL'/>">已取消订单</a> </td>
  </tr>
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/lockedorder/list'/>">已加锁订单</a> </td>
  </tr>
  
 </table>
<!-------------------------订单管理END------------------------------>
<li><a style="font-weight: bold;font-size: 15px; padding-bottom: 5px;padding-top: 5px;" onclick="DoMenu('ChildMenu4')">订单管理</a> 
    <ul id="ChildMenu4" class="collapsed"> 
    <li><a href="<html:rewrite action='/control/order/query'/>">订单查询</a></li> 
    <li> <a href="<html:rewrite action='/control/order/list'/>">待审核订单</a></li> 
    <li>  <a href="<html:rewrite action='/control/order/list?state=WAITPAYMENT'/>">等待付款订单</a></li> 
    <li> <a href="<html:rewrite action='/control/order/list?state=ADMEASUREPRODUCT'/>">正在配货订单</a></li> 
    <li> <a href="<html:rewrite action='/control/order/list?state=WAITDELIVER'/>">等待发货订单</a></li> 
    <li> <a href="<html:rewrite action='/control/order/list?state=DELIVERED'/>">已发货订单</a></li> 
    <li> <a href="<html:rewrite action='/control/order/list?state=RECEIVED'/>">已收货订单</a></li> 
    <li> <a href="<html:rewrite action='/control/order/list?state=CANCEL'/>">已取消订单</a></li> 
    <li> <a href="<html:rewrite action='/control/lockedorder/list'/>">已加锁订单</a></li> 
    </ul> 
</li> 

<!-------------------------部门员工管理START-----------------------------

<table border=0 width="98%" align="center" cellspacing="0" cellpadding="0">
  <tr> 
    <td colspan="3" language="JavaScript" onMouseUp="turnit('privilege')" style="CURSOR: hand"> 
      <img id="Imgprivilege" src="/images/midclosedfolder.gif" align="absMiddle" border="0" width="16"><img name="Imgprivilege_0" src="/images/clsfld.gif" align="absMiddle" border="0"> 
        <font face=宋体><b>部门员工管理</b></font> 
    </td>
  </tr>
</table>
<table id="menu_privilege" border=0 width="98%" align="center" cellspacing="0" cellpadding="0" style="display:none">
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/employee/query'/>">员工查询</a> </td>
  </tr>
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/department/list'/>">部门管理</a> </td>
  </tr>
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/employee/list'/>">员工管理</a> </td>
  </tr>
  <tr> 
    <td width="50"><img src="/images/vertline.gif" border=0><img src="/images/lastnodeline.gif" align="absMiddle" border=0 width="16" height="22"><img src="/images/doctemp.gif" align="absMiddle" border="0" width="16" height="16"></td>
    <td width="123"> <a href="<html:rewrite action='/control/privilegegroup/list'/>">权限组管理</a> </td>
  </tr>
  
 </table>
<!-------------------------部门员工管理END-------------------------------> 
<li><a style="font-weight: bold;font-size: 15px; padding-bottom: 5px;padding-top: 5px;" onclick="DoMenu('ChildMenu5')">部门员工管理</a> 
    <ul id="ChildMenu5" class="collapsed"> 
    <li><a href="<html:rewrite action='/control/employee/query'/>">员工查询</a></li> 
    <li><a href="<html:rewrite action='/control/department/list'/>">部门管理</a></li> 
    <li><a href="<html:rewrite action='/control/employee/list'/>">员工管理</a></li> 
    <li><a href="<html:rewrite action='/control/privilegegroup/list'/>">权限组管理</a></li> 
    </ul> 
</li> 


</ul> 
</div> 

</body>
</html>

<script type=text/javascript>
<!-- 
var LastLeftID = ""; 

function menuFix() { 
    var obj = document.getElementById("nav").getElementsByTagName("li"); 
     
    for (var i=0; i<obj.length; i++) { 
        obj[i].onmouseover=function() { 
            this.className+=(this.className.length>0? " ": "") + "sfhover"; 
        } 
        obj[i].onMouseDown=function() { 
            this.className+=(this.className.length>0? " ": "") + "sfhover"; 
        } 
        obj[i].onMouseUp=function() { 
            this.className+=(this.className.length>0? " ": "") + "sfhover"; 
        } 
        obj[i].onmouseout=function() { 
            this.className=this.className.replace(new RegExp("( ?|^)sfhover\\b"), ""); 
        } 
    } 
} 

function DoMenu(emid) 
{ 
    var obj = document.getElementById(emid);     
    obj.className = (obj.className.toLowerCase() == "expanded"?"collapsed":"expanded"); 
    if((LastLeftID!="")&&(emid!=LastLeftID))    //关闭上一个Menu 
    { 
        document.getElementById(LastLeftID).className = "collapsed"; 
    } 
    LastLeftID = emid; 
} 

function GetMenuID() 
{ 

    var MenuID=""; 
    var _paramStr = new String(window.location.href); 

    var _sharpPos = _paramStr.indexOf("#"); 
     
    if (_sharpPos >= 0 && _sharpPos < _paramStr.length - 1) 
    { 
        _paramStr = _paramStr.substring(_sharpPos + 1, _paramStr.length); 
    } 
    else 
    { 
        _paramStr = ""; 
    } 
     
    if (_paramStr.length > 0) 
    { 
        var _paramArr = _paramStr.split("&"); 
        if (_paramArr.length>0) 
        { 
            var _paramKeyVal = _paramArr[0].split("="); 
            if (_paramKeyVal.length>0) 
            { 
                MenuID = _paramKeyVal[1]; 
            } 
        } 
        /* 
        if (_paramArr.length>0) 
        { 
            var _arr = new Array(_paramArr.length); 
        } 
         
        //取所有#后面的，菜单只需用到Menu 
        //for (var i = 0; i < _paramArr.length; i++) 
        { 
            var _paramKeyVal = _paramArr[i].split('='); 
             
            if (_paramKeyVal.length>0) 
            { 
                _arr[_paramKeyVal[0]] = _paramKeyVal[1]; 
            }         
        } 
        */ 
    } 
     
    if(MenuID!="") 
    { 
        DoMenu(MenuID) 
    } 
} 

GetMenuID();    //*这两个function的顺序要注意一下，不然在Firefox里GetMenuID()不起效果 
menuFix(); 
</script>
