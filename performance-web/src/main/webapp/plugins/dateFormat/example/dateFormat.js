

// 字符串  月份转 当前时间  然后通过bootstrap-datimepicker 生成月份视图
var show_date = "201806";
var yea = show_date.substr(0, 4);
var mon = show_date.substr(4, 2);
var day = '01';
var date = yea + "-" + mon + "-" + day;
var show_date_common = new Date(date);

console.log(show_date_common);



