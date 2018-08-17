/**
 * @author 管赟
 * @e-mail twist.uau@gmail.com
 * @type {Location | string | any}
 */
var localObj = window.location;

var curWwwPath=window.document.location.href;

var urlSplit = curWwwPath.split("/")[4];

var contextPath = localObj.pathname.split("/")[1];

var basePath = localObj.protocol+"//"+localObj.host+"/"+contextPath;

var rootPath=localObj.protocol+"//"+localObj.host+"/";

var server_context=basePath;