function formatNumber(n) {
    n = n.toString();
    return n[1] ? n : '0' + n
}

/*定义全局过滤器，必须先于Vue对象创建前定义*/
Vue.filter('formatTime',function (number,format) {
    var formateArr  = ['Y','M','D','h','m','s'];
    var returnArr   = [];

    var date = new Date(number);
    returnArr.push(date.getFullYear())//在字符数组的末尾插入一个元素
    returnArr.push(formatNumber(date.getMonth() + 1));
    returnArr.push(formatNumber(date.getDate()));

    returnArr.push(formatNumber(date.getHours()));
    returnArr.push(formatNumber(date.getMinutes()));
    returnArr.push(formatNumber(date.getSeconds()));

    for (var i in returnArr)
    {
        format = format.replace(formateArr[i], returnArr[i]);
    }
    return format;
})