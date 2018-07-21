
//let lineChart = echarts.init(document.getElementById('main'), 'light');
//var paiChart = echarts.init(document.getElementById('main2'), 'light');
var dissatisfactionChart = echarts.init(document.getElementById('allDissatisfaction'), 'light');
var dissatisfiedServiceChart = echarts.init(document.getElementById('dissatisfiedService'), 'light');
var dissatisfactionProblemChart = echarts.init(document.getElementById('dissatisfactionProblem'), 'light');
var definitionId = '';
var date7 = new Date(new Date().getTime() - (6 * 24 * 60 * 60 * 1000));

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
	'statisticsDissatisfiedDayDataVO.endTime' : '',
	'statisDissatiDateVO.stimeType' : ''
}

$.post('/jwcpxt/Statistics/get_statisDissatiDateVO', {
	'statisDissatiDateVO.screenUnit' : '',
	'statisDissatiDateVO.startTime' : '2018-07-16',
	'statisDissatiDateVO.endTime' : '2018-07-22',
	'statisDissatiDateVO.timeType' : '1'
}, response => {
	randerDissatisfactionChart(response); //所有不满意图表
}, 'json')

$.post('/jwcpxt/Statistics/get_statisDissaServiceDateVO', {
	'statisDissaServiceDateVO.screenUnit' : '',
	'statisDissaServiceDateVO.startTime' : '2018-07-16',
	'statisDissaServiceDateVO.endTime' : '2018-07-22',
	'statisDissaServiceDateVO.timeType' : '1'
}, response => {
	randerDissatisfiedServiceChart(response); //所有不满意图表
}, 'json')


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
			randerDissatisfactionChart(response); //所有不满意图表
		}, 'json')
	/*$.post('/jwcpxt/Statistics/get_StatisticsDissatisfiedDayDataVO', $params, response => {
		randerLineChart(response); //折线图
	}, 'json')
	$.post('/jwcpxt/Statistics/get_StatisticsDissatisfiedDateCountVO', {
		'statisticsDissatisfiedDateCountVO.startTime' : $params['statisticsDissatisfiedDayDataVO.startTime'],
		'statisticsDissatisfiedDateCountVO.endTime' : $params['statisticsDissatisfiedDayDataVO.endTime'],
		'statisticsDissatisfiedDateCountVO.unit.jwcpxt_unit_id' : $params['statisticsDissatisfiedDayDataVO.unit.jwcpxt_unit_id']
	}, response => {
		randerPieChart(response); //饼图
	}, 'json')*/
	}
}


function searchUnit(select) {
	let unit = $(select).val();
	if (!unit) {
		return false;
	}
	params['statisticsDissatisfiedDayDataVO.unit.jwcpxt_unit_id'] = unit;
	getInfo(params);
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

/*[
[ 'product', '2012', '2013', '2014', '2015', '2016', '2017' ],
[ 'Matcha Latte', 41.1, 30.4, 65.1, 53.3, 83.8, 98.7 ],
[ 'Milk Tea', 86.5, 92.1, 85.7, 83.1, 73.4, 55.1 ],
[ 'Cheese Cocoa', 24.1, 67.2, 79.5, 86.4, 65.2, 82.5 ],
[ 'Walnut Brownie', 55.2, 67.1, 69.2, 72.4, 53.9, 39.1 ]
]*/
//绘制总体不满意分布
function randerDissatisfactionChart(res) {
	let _source = [ [ 'time' ], ];
	res.listStaDisDateDTO.forEach(function(elt, i) {
		_source[0].push(elt.dateScale);
		elt.listDissaOptionDTO.forEach(function(elt1, j) {
			if (!_source[j + 1])
				_source[j + 1] = [];
			_source[j + 1][0] = elt1.option;
			_source[j + 1].push(elt1.count);
		})
	})
	let _series = [];
	let length = _source.length - 1;
	for (var i = 0; i < length; i++) {
		_series.push({
			type : 'line',
			smooth : true,
			seriesLayoutBy : 'row'
		});
	}
	_series.push({
		type : 'pie',
		id : 'pie',
		radius : '30%',
		center : [ '50%', '25%' ],
		label : {
			formatter : '{b}: {@2012} ({d}%)'
		},
		encode : {
			itemName : 'time',
			value : '2012',
			tooltip : '2012'
		}
	});
	//数据
	let option = {
		legend : {},
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				type : 'cross',
				crossStyle : {
					color : '#999'
				}
			}
		},
		dataset : {
			source : _source
		},
		xAxis : {
			type : 'category'
		},
		yAxis : {
			gridIndex : 0
		},
		grid : {
			top : '55%'
		},
		series : _series
	};
	dissatisfactionChart.on('updateAxisPointer', function(event) {
		let xAxisInfo = event.axesInfo[0];
		if (xAxisInfo) {
			let dimension = xAxisInfo.value + 1;
			dissatisfactionChart.setOption({
				series : {
					id : 'pie',
					label : {
						formatter : '{b}: {@[' + dimension + ']} ({d}%)'
					},
					encode : {
						value : dimension,
						tooltip : dimension
					}
				}
			});
		}
	});
	dissatisfactionChart.setOption(option);
}

/*[
[ 'product', '2012', '2013', '2014', '2015', '2016', '2017' ],
[ 'Matcha Latte', 41.1, 30.4, 65.1, 53.3, 83.8, 98.7 ],
[ 'Milk Tea', 86.5, 92.1, 85.7, 83.1, 73.4, 55.1 ],
[ 'Cheese Cocoa', 24.1, 67.2, 79.5, 86.4, 65.2, 82.5 ],
[ 'Walnut Brownie', 55.2, 67.1, 69.2, 72.4, 53.9, 39.1 ]
]*/
//绘制不满意和业务关联的图
function randerDissatisfiedServiceChart(res) {
	let _source = [ [ 'time' ], ];
	res.listStatisDIssaServiceDateDTO.forEach(function(elt, i) {
		_source[0].push(elt.dateScale);
		elt.listStatisDIssaServiceDTO.forEach(function(elt1, j) {
			if (!_source[j + 1])
				_source[j + 1] = [];
			_source[j + 1][0] = elt1.serviceDefinition.service_definition_describe;
			_source[j + 1].push(elt1.count);
		})
	})
	let _series = [];
	let length = _source.length - 1;
	for (var i = 0; i < length; i++) {
		_series.push({
			type : 'line',
			smooth : true,
			seriesLayoutBy : 'row'
		});
	}
	_series.push({
		type : 'pie',
		id : 'pie',
		radius : '30%',
		center : [ '50%', '25%' ],
		label : {
			formatter : '{b}: {@2012} ({d}%)'
		},
		encode : {
			itemName : 'time',
			value : '2012',
			tooltip : '2012'
		}
	});
	//数据
	let option = {
		legend : {},
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				type : 'cross',
				crossStyle : {
					color : '#999'
				}
			}
		},
		dataset : {
			source : _source
		},
		xAxis : {
			type : 'category'
		},
		yAxis : {
			gridIndex : 0
		},
		grid : {
			top : '55%'
		},
		series : _series
	};
	dissatisfiedServiceChart.on('updateAxisPointer', function(event) {
		let xAxisInfo = event.axesInfo[0];
		if (xAxisInfo) {
			let dimension = xAxisInfo.value + 1;
			dissatisfiedServiceChart.setOption({
				series : {
					id : 'pie',
					label : {
						formatter : '{b}: {@[' + dimension + ']} ({d}%)'
					},
					encode : {
						value : dimension,
						tooltip : dimension
					}
				}
			});
		}
	});
	dissatisfiedServiceChart.setOption(option);
	dissatisfiedServiceChart.on("click", function(param) {
		let index = param.dataIndex;
		let describe = option.dataset.source[index + 1][0];
		try {
			res.listStatisDIssaServiceDateDTO[0].listStatisDIssaServiceDTO.forEach(function(elt, i) {
				if (elt.serviceDefinition.service_definition_describe == describe) {
					definitionId = elt.serviceDefinition.jwcpxt_service_definition_id;
					console.log(definitionId);
					dissatisfactionProblemSendData();
					throw 'Jump';
				}
			})
		} catch (e) {
			console.log(e);
		}
	});
}


function dissatisfactionProblemSendData() {
	$.post('/jwcpxt/Statistics/get_statisDissaQuestionDateVO', {
		'statisDissaQuestionDateVO.screenUnit' : '',
		'statisDissaQuestionDateVO.startTime' : '2018-07-16',
		'statisDissaQuestionDateVO.endTime' : '2018-07-22',
		'statisDissaQuestionDateVO.timeType' : '1',
		'statisDissaQuestionDateVO.screenService' : definitionId
	}, response => {
		randerDissatisfactionProblem(response); //所有不满意问题
	}, 'json')
}
function randerDissatisfactionProblem(res) {
	let _source = [ [ 'time2' ], ];
	res.listStatisDissaDTO.forEach(function(elt, i) {
		_source[0].push(elt.dateScale);
		if (!_source[i])
			_source[i] = [];
		_source[i][0] = res.listQuestionOptionDTO[i].question.question_describe + '--' + res.listQuestionOptionDTO[i].option.option_describe;
		_source[i].push(res.listStatisDissaDTO[i].count);
		res.listQuestionOptionDTO[i]
	})
	res.listStatisDIssaServiceDateDTO.forEach(function(elt, i) {
		_source[0].push(elt.dateScale);
		elt.listStatisDIssaServiceDTO.forEach(function(elt1, j) {
			if (!_source[j + 1])
				_source[j + 1] = [];
			_source[j + 1][0] = elt1.serviceDefinition.service_definition_describe;
			_source[j + 1].push(elt1.count);
		})
	})
	let _series = [];
	let length = _source.length - 1;
	for (var i = 0; i < length; i++) {
		_series.push({
			type : 'line',
			smooth : true,
			seriesLayoutBy : 'row'
		});
	}
	_series.push({
		type : 'pie',
		id : 'pie',
		radius : '30%',
		center : [ '50%', '25%' ],
		label : {
			formatter : '{b}: {@2012} ({d}%)'
		},
		encode : {
			itemName : 'time2',
			value : '2012',
			tooltip : '2012'
		}
	});
	//数据
	let option = {
		legend : {},
		tooltip : {
			trigger : 'axis',
			axisPointer : {
				type : 'cross',
				crossStyle : {
					color : '#999'
				}
			}
		},
		dataset : {
			source : _source
		},
		xAxis : {
			type : 'category'
		},
		yAxis : {
			gridIndex : 0
		},
		grid : {
			top : '55%'
		},
		series : _series
	};
	dissatisfactionProblemChart.on('updateAxisPointer', function(event) {
		let xAxisInfo = event.axesInfo[0];
		if (xAxisInfo) {
			let dimension = xAxisInfo.value + 1;
			dissatisfactionProblemChart.setOption({
				series : {
					id : 'pie',
					label : {
						formatter : '{b}: {@[' + dimension + ']} ({d}%)'
					},
					encode : {
						value : dimension,
						tooltip : dimension
					}
				}
			});
		}
	});
	dissatisfactionProblemChart.setOption(option);
}












































//绘制折线图
function randerLineChart(res) {
	//时间周期内每日
	let dayArr = TimeUtil.getDayAll(res.startTime, res.endTime);
	console.log(dayArr)
	let service = [];
	let chartData = [];
	res.statisticsDissatisfiedDayData.forEach(function(elt, i) {
		//业务名称
		service.push(elt.serviceDefinition.service_definition_describe);
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
			text : res.unit.unit_name + '不满意数量折线图堆叠'
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


//绘制饼图
function randerPieChart(res) {
	//legendData全部数据
	//selected 选中数据
	//seriesData 图表数据
	let service = [];
	let chartData = [];
	res.statisticsDissatisfiedDateCountDTO.forEach(function(elt, i) {
		//业务名称
		service.push(elt.serviceDefinition.service_definition_describe);
		//数据
		chartData.push({
			name : elt.serviceDefinition.service_definition_describe,
			value : elt.dayCount
		});
	})
	// 指定图表的配置项和数据
	let option2 = {
		title : {
			text : res.unit.unit_name + '不满意数量统计',
			subtext : '',
			x : 'center'
		},
		tooltip : {
			trigger : 'item',
			formatter : "{a} <br/>{b} : {c} ({d}%)"
		},
		legend : {
			type : 'scroll',
			orient : 'vertical',
			right : 10,
			top : 20,
			bottom : 20,
			data : service,
			selected : service
		},
		series : [
			{
				name : '业务',
				type : 'pie',
				radius : '55%',
				center : [ '40%', '50%' ],
				data : chartData,
				itemStyle : {
					emphasis : {
						shadowBlur : 10,
						shadowOffsetX : 0,
						shadowColor : 'rgba(0, 0, 0, 0.5)'
					}
				}
			}
		]
	};
	paiChart.setOption(option2);
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