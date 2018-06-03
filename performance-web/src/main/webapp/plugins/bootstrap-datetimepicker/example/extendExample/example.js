// 1，简单的定义一下时间变量，这里需要用到DateFormat.js时间格式化插件1，简单的定义一下时间变量，这里需要用到DateFormat.js时间格式化插件
var now = new Date();//当前时间
var hours = new Date().getHours();//小时
hours=hours>=10?hours:"0"+hours;
var yesterday= new Date(new Date(now.getTime()-3600*1000*24));//昨天时间
var month = new Date().getMonth()+1;//当前月份
month = month>=10?month:"0"+month;
var year = new Date().getFullYear();
var prevHour = new Date(new Date(now.getTime()-3600*1000));//前一个小时

var todayDay=$.format.date(now,'yyyy-MM-dd');//根据自己的时间展现需要来格式化成相关的格式
var Yesterday=$.format.date(yesterday,'yyyy-MM-dd');
var MonthTime=$.format.date(month,'yyyy-MM');//格式化月的事件格式
var YearDate=$.format.date(now,'yyyy');//格式化年的时间格式
var PrevHour=$.format.date(prevHour,'yyyy-MM-dd HH:mm');
var Hour= $.format.date(now,'yyyy-MM-dd HH:mm');
// （1）小时视图

    //小时的开始时间
    //这里可以改成自己需要的id
    $('#dayHour [data-time="start"]').datetimepicker({
        language:  'zh-CN',//选择语言类型
        weekStart: 1,//设置起始周
        todayBtn: true ,//打开底部今天按钮,false为关闭
        autoclose: true,//选中日期后自动关闭选择器
        todayHighlight: true,//高亮显示当前日期
        startView: 1,//设置为小时视图 ,1 hour 1 day 2 month 3 year 4 decade(十年)
        minView: 1,//设置最小视图为小时视图
        format: 'yyyy-mm-dd hh:mm',//设置时间展现格式
        forceParse: true//是否强制解析时间格式和类型
    }).val(PrevHour);//初始化前端input默认值

    //小时结束时间;
    $('#dayHour [data-time="end"]').datetimepicker({
        language:  'zh-CN',
        weekStart: 1,
        todayBtn: true ,
        autoclose: true,
        todayHighlight: true,
        startView: 1,
        minView: 1,
        format: 'yyyy-mm-dd hh:mm',
        forceParse: true
    }).val(Hour);

//（2），日视图

//日的开始时间
$('#Day [data-time="start"]').datetimepicker({
    language:  'zh-CN',
    weekStart: 1,
    todayBtn: true ,
    autoclose: true,
    todayHighlight: true,
    startView: 2,
    minView: 2,
    format: 'yyyy-mm-dd',//定义时间格式
    forceParse: true
}).val(yesterday);//初始化input默认值

//日结束时间;
$('#Day [data-time="end"]').datetimepicker({
    language:  'zh-CN',
    weekStart: 1,
    todayBtn: true ,
    autoclose: true,
    todayHighlight: true,
    startView: 2,
    minView: 2,
    format: 'yyyy-mm-dd',
    forceParse: true
}).val(now);


//（3）周视图，由于此插件没有周视图，这里为自己自定义的时间格式，前端采用h5的input type=”week”属性来设置，兼容性略差，同学们根据需求可做个参考


var weeks=year+"-W"+currentWeek;//当前周，这里是自己拼接的周类型
$('#week [data-time="start"]').val(weeks);
$('#week [data-time="end"]').val(weeks);

// （4）月视图

//月的开始时间
$('#month [data-time="start"]').datetimepicker({
    language:  'zh-CN',
    todayBtn:  false,
    autoclose: true,
    startView: 3,//默认视图为年视图
    minView: 3,
    format: 'yyyy-mm',
    forceParse: true,
    endDate:MonthTime
}).val(MonthTime);

//月的结束时间
$('#month [data-time="end"]').datetimepicker({
    language:  'zh-CN',
    todayBtn:  false,
    autoclose: true,
    startView: 3,
    minView: 3,
    format: 'yyyy-mm',
    forceParse: true,
    endDate:MonthTime
}).val(MonthTime);


// (5)年视图

//年份开始时间
$('#yearDate [data-time="start"]').datetimepicker({
    language:  'zh-CN',
    todayBtn: true ,//打开底部今天按钮,false为关闭
    autoclose: true,//选中日期后自动关闭选择器
    todayHighlight: true,//高亮显示当前日期
    startView: 4,
    minView: 4,
    format: 'yyyy',
    forceParse: true,
    endDate:YearDate
}).on("changeDate",function(e){
    var endTime = e.date;//设置时间可选择范围，最大选择不能超过当前年份
    $('#yearDate [data-time="end"]').datetimepicker("setStartDate",endTime);
}).val(YearDate);

//年份结束时间
$('#yearDate [data-time="end"]').datetimepicker({
    language:  'zh-CN',
    todayBtn: true ,
    autoclose: true,
    todayHighlight: true,
    startView: 4,
    minView: 4,
    format: 'yyyy',
    forceParse: true,
    endDate:YearDate
}).on("changeDate",function(e){
    var endTime = e.date;
    $('#yearDate [data-time="start"]').datetimepicker("setEndDate",endTime);
}).val(YearDate);


// 下拉后是当前时间
$("#user-info-check-performance-time").datetimepicker("setStartDate", DateFormat.format(new Date(), "yyyy-MM-dd"));