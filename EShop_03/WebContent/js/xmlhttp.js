/**
 * 封装AJAX
 * 
 * @param callback
 *            回调函数
 * @param urladdress
 *            请求路径
 * @param isReturnData
 *            是否返回数据
 */
function send_request(callback, urladdress, isReturnData) {
	var xmlhttp = getXMLHttpRequest();
	xmlhttp.onreadystatechange = function() 
	{
		if (xmlhttp.readyState == 4) 
		{// readystate 为4即数据传输结束
			try 
			{
				if (xmlhttp.status == 200) 
				{
					if (isReturnData && isReturnData == true) 
					{
						callback(xmlhttp.responseText);
					}
				} else 
				{
					callback("抱歉，没找到此页面:" + urladdress + "");
				}
			} catch (e) 
			{
				callback("抱歉，发送请求失败，请重试 " + e);
			}
		}
	}
	xmlhttp.open("GET", urladdress, true);
	xmlhttp.send(null);
}

function getXMLHttpRequest() {
	  var xmlHttp=null;
	  try
	    {
	    // Firefox, Opera 8.0+, Safari
	    xmlHttp=new XMLHttpRequest();
	    }
	  catch (e)
	    {
	    // Internet Explorer
	    try
	      {
	      xmlHttp=new ActiveXObject("Msxml2.XMLHTTP");
	      }
	    catch (e)
	      {
	      xmlHttp=new ActiveXObject("Microsoft.XMLHTTP");
	      }
	    }
	return xmlHttp;
}
