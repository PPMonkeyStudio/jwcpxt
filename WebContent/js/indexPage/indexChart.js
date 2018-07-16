
let lineChart = echarts.init(document.getElementById('main'), 'light');

//给下方法提供使用
Date.prototype.format = function() {
	var s = '';
	s += this.getFullYear() + '-'; // 获取年份。
	s += (this.getMonth() + 1) + "-"; // 获取月份。
	s += this.getDate(); // 获取日。
	return (s); // 返回日期。
};
var TimeUtil = {
	//按日查询
	getDayAll : function(begin, end) {
		var dateAllArr = new Array();
		var ab = begin.split("-");
		var ae = end.split("-");
		var db = new Date();
		db.setUTCFullYear(ab[0], ab[1] - 1, ab[2]);
		var de = new Date();
		de.setUTCFullYear(ae[0], ae[1] - 1, ae[2]);
		var unixDb = db.getTime();
		var unixDe = de.getTime();
		for (var k = unixDb; k <= unixDe;) {
			dateAllArr.push((new Date(parseInt(k))).format().toString());
			k = k + 24 * 60 * 60 * 1000;
		}
		return dateAllArr;
	}
}

let params = {
	'statisticsDissatisfiedDayDataVO.unit.jwcpxt_unit_id' : '',
	'statisticsDissatisfiedDayDataVO.startTime' : '',
	'statisticsDissatisfiedDayDataVO.endTime' : ''
}

randerTimeUtil();
function randerTimeUtil() {
	$.datetimepicker.setLocale('ch');
	$('.mydate').datetimepicker({
		pickerPosition : "top-right",
		yearStart : 1900, // 设置最小年份
		yearEnd : 2050, // 设置最大年份
		yearOffset : 0, // 年偏差
		timepicker : false, // 关闭时间选项
		format : 'Y-m-d', // 格式化日期年-月-日
		minDate : '1900/01/01', // 设置最小日期
		maxDate : '2050/01/01', // 设置最大日期
	});
}

//获取所有的单位
getAllUnit();
function getAllUnit() {
	$.post('/jwcpxt/Unit/list_unitDO_all', {}, response => {
		let option_str = '';
		response.forEach(function(elt, i) {
			option_str += `<option value="${elt.jwcpxt_unit_id}">${elt.unit_name}</option>`;
		})
		$('#SearchUnit').html(option_str).selectpicker('refresh');
	}, 'json')
}

//获取统计后的信息，渲染出统计图表
function getInfo($params) {
	let flag = true;
	for (let item in params) {
		if (!params[item]) {
			flag = false;
			return false;
		}
	}
	if (flag) {
		$.post('/jwcpxt/Statistics/get_StatisticsDissatisfiedDayDataVO', $params, response => {
			randerLineChart(response); //折线图
		}, 'json')
	}
}


function searchBeginTime(input) {
	let begin = $(input).val();
	if (!begin) {
		return false;
	}
	params['statisticsDissatisfiedDayDataVO.startTime'] = begin;
	getInfo(params);
}
function searchEndTime(input) {
	let end = $(input).val();
	if (!end) {
		return false;
	}
	params['statisticsDissatisfiedDayDataVO.endTime'] = end;
	getInfo(params);
}

//绘制折线图
function randerLineChart(res) {
	//时间周期内每日
	let dayArr = TimeUtil.getDayAll(res.startTime, res.endTime);
	let service = [];
	let chartData = [];
	res.statisticsDissatisfiedDayData.forEach(function(elt, i) {
		//业务名称
		service.push(elt.serviceDefinition.service_definition_describe.substring(0, 4));
		//数据
		chartData.push({
			name : elt.serviceDefinition.service_definition_describe,
			type : 'line',
			stack : '总数',
			data : elt.dayNumList
		});
	})
	// 基于准备好的dom，初始化echarts实例
	let option1 = {
		title : {
			text : res.unit.unit_name + '折线图堆叠'
		},
		tooltip : {
			enterable : true,
			trigger : 'axis'
		},
		legend : {
			data : service
		},
		grid : {
			left : '3%',
			right : '4%',
			bottom : '3%',
			containLabel : true
		},
		toolbox : {
			feature : {
				saveAsImage : {}
			}
		},
		xAxis : {
			type : 'category',
			boundaryGap : false,
			data : dayArr
		},
		yAxis : {
			type : 'value'
		},
		series : chartData
	};
	// 使用刚指定的配置项和数据显示图表。
	lineChart.setOption(option1);
}
