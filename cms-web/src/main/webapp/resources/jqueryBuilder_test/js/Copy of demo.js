 /**
 * Created by chen on 8/10/2015.
 */

var postQuerySql=function(sql){
	$.ajax({
        type: "POST",
        url: "postQuerySql.do",
        data: {
        	sql: sql
        },
        dataType: "json",
        success: function(obj){
        	
        }
    });
};
// get rules
$('.parse-json').on('click', function() {
    var target = $(this).data('target');
    var result = $('#builder-' + target).queryBuilder('getRules');
    
    var res = JSON.stringify(result, null, 2);
    var myData = JSON.parse(res, function (key, value) {
        return key.indexOf('date') >= 0 ? new Date(value) : value;
    });
    if (!$.isEmptyObject(result)) {
//        bootbox.alert({
//            title: $(this).text(),
//            message: '<pre class="code-popup">' + JSON.stringify(result, null, 2) + '</pre>'
//        });
    	var sql=JSON.stringify(result, null, 2);
        console.log(sql);
    	postQuerySql(sql);
    }
});
