
/*var change;
// 导出总表（）
function listenEnd() {// 定时监听
  var loop = setInterval(function() {
      if (change == false) {
          clearInterval(loop);// 停止定时任务
          $.messager.progress('close');// 进度条关闭
      } 
  }, 1000);// 单位毫秒 注意：如果导出页面很慢时，建议循环时间段稍长一点
}*/

/**
 * js生成excel方法
 */
function exportExcel(JSONData, filename,title,title1) {// 整个表格拷贝到EXCEL中  
	NProgress.start();
	/*change=true;
		$.messager.progress({
	      title: '提示',
	      msg: '请稍候……',
	      text: '记录导出中……'
	  });*/
	  //listenEnd();
      tableToExcel(JSONData, filename,title,title1)
}


var tableToExcel =function JSONToExcelConvertor(JSONData, FileName,title,title1) {
  if(!JSONData)
      return;
  // 转化json为object
  var arrData = typeof JSONData != 'object' ? JSON.parse(JSONData) : JSONData;
  var excel = createTable(arrData, title,title1);// 生产table的html 字符串
  var excelFile = "<html xmlns:o='urn:schemas-microsoft-com:office:office' xmlns:x='urn:schemas-microsoft-com:office:excel' xmlns='http://www.w3.org/TR/REC-html40'>";
  excelFile += '<meta http-equiv="content-type" content="application/vnd.ms-excel; charset=UTF-8">';
  excelFile += '<meta http-equiv="content-type" content="application/vnd.ms-excel';
  excelFile += '; charset=UTF-8">';
  excelFile += "<head>";
  excelFile += "<!--[if gte mso 9]>";
  excelFile += "<xml>";
  excelFile += "<x:ExcelWorkbook>";
  excelFile += "<x:ExcelWorksheets>";
  excelFile += "<x:ExcelWorksheet>";
  excelFile += "<x:Name>";
  excelFile += "{worksheet}";
  excelFile += "</x:Name>";
  excelFile += "<x:WorksheetOptions>";
  excelFile += "<x:DisplayGridlines/>";
  excelFile += "</x:WorksheetOptions>";
  excelFile += "</x:ExcelWorksheet>";
  excelFile += "</x:ExcelWorksheets>";
  excelFile += "</x:ExcelWorkbook>";
  excelFile += "</xml>";
  excelFile += "<![endif]-->";
  excelFile += "</head>";
  excelFile += "<body>";
  excelFile += excel;
  excelFile += "</body>";
  excelFile += "</html>";
  var uri = 'data:application/vnd.ms-excel;charset=utf-8,' + encodeURIComponent(excelFile);
  var link = document.createElement("a");
//  change=false;
  link.href = uri;
  link.style = "visibility:hidden";
  link.download = FileName + ".xls";
  document.body.appendChild(link);
  link.click();
  document.body.removeChild(link);
}

function createTable(arrData,title,title1) {
  var excel = '<table border="1" cellspacing="1" cellpadding="1"  id="table" text-align="center">';
  // 设置表头
  var row = "<tr>";
  // 使用标题项
  for (var i in title) {
       row += "<th align='center'  width='100px'>" + title[i] + '</th>';    
  }
  excel += row + "</tr>";
  for (var i = 0; i < arrData.length; i++) {
  	// 一级
  	var row = "<tr>";
  	for (var j = 0; j <title1.length; j++) {
  		var value = arrData[i][title1[j]] == null ? "" : arrData[i][title1[j]];
  		row += '<td align="center" style="white-space: nowrap;">' + value + '</td>';
  	}
    excel += row + "</tr>";
      
   	}  
  excel += "</table>";
  NProgress.done();
  return  excel;
}
