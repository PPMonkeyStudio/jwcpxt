//获取所有的单位
getAllUnit();
function getAllUnit() {
	$.post('/jwcpxt/Unit/list_unitDO_all', {}, response => {
		let option_str = '';
		response.forEach(function(elt, i) {
			option_str += `<option value="${elt.jwcpxt_unit_id}">${elt.unit_name}</option>`;
		})
		$('#jwcpxt_unit_id').html(option_str).selectpicker('refresh');
	}, 'json')
}

//let lineChart = echarts.init(document.getElementById('main'), 'light');
//var paiChart = echarts.init(document.getElementById('main2'), 'light');
var dissatisfactionChart = echarts.init(document.getElementById('allDissatisfaction'), 'light');
var dissatisfiedServiceChart = echarts.init(document.getElementById('dissatisfiedService'), 'light');
var dissatisfactionProblemChart = echarts.init(document.getElementById('dissatisfactionProblem'), 'light');
var definitionId = '';
function getDate() {
	let dateTime = new Date();
	let year = dateTime.getFullYear();
	let mounth = (dateTime.getMonth() + 1).toString().length > 1 ? (dateTime.getMonth() + 1) : "0" + (dateTime.getMonth() + 1);
	let day = (dateTime.getDate()).toString().length > 1 ? (dateTime.getDate()) : "0" + (dateTime.getDate());
	return year + "-" + mounth + "-" + day;
}
function getDate7() {
	let date7Time = new Date(new Date().getTime() - (6 * 24 * 60 * 60 * 1000));
	let year = date7Time.getFullYear();
	let mounth = (date7Time.getMonth() + 1).toString().length > 1 ? (date7Time.getMonth() + 1) : "0" + (date7Time.getMonth() + 1);
	let day = (date7Time.getDate()).toString().length > 1 ? (date7Time.getDate()) : "0" + (date7Time.getDate());
	return year + "-" + mounth + "-" + day;
}

let params = {
	'jwcpxt_unit_id' : '',
	'startTime' : getDate7(),
	'endTime' : getDate(),
	'timeType' : 1
}

//到页面时默认执行一次方法
pageInit();
function pageInit() {
	getChart(params);
	$('#startTime').val(params.startTime);
	$('#endTime').val(params.endTime);
}
//获取统计后的信息，渲染出统计图表
function getChart($params) {
	let flag = true;
	for (let item in params) {
		if (!params[item] && item != 'jwcpxt_unit_id') {
			flag = false;
			return false;
		}
	}
	if (flag) {
		//不满意分布
		$.post('/jwcpxt/Statistics/get_statisDissatiDateVO', {
			'statisDissatiDateVO.screenUnit' : params.jwcpxt_unit_id,
			'statisDissatiDateVO.startTime' : params.startTime,
			'statisDissatiDateVO.endTime' : params.endTime,
			'statisDissatiDateVO.timeType' : params.timeType
		}, response => {
			randerDissatisfactionChart(response); //所有不满意图表
		}, 'json')
		//业务分类
		$.post('/jwcpxt/Statistics/get_statisDissaServiceDateVO', {
			'statisDissaServiceDateVO.screenUnit' : params.jwcpxt_unit_id,
			'statisDissaServiceDateVO.startTime' : params.startTime,
			'statisDissaServiceDateVO.endTime' : params.endTime,
			'statisDissaServiceDateVO.timeType' : params.timeType
		}, response => {
			randerDissatisfiedServiceChart(response); //所有不满意图表
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

//查询
function search(select) {
	let value = $(select).val();
	if (!value) {
		return false;
	}
	params[$(this).attr('name')] = value;
	getChart(params);
}
function checkTimeType(btn) {
	$('.timeType').removeAttr('disabled');
	$(btn).attr('disabled', 'disabled');
	params["timeType"] = $(btn).attr('time-type');
	getChart(params);
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
			formatter : `{b}: {@${_source[0][1]}} ({d}%)`
		},
		encode : {
			itemName : 'time',
			value : _source[0][1],
			tooltip : _source[0][1]
		}
	});
	//数据
	setTimeout(function() {
		let option = {
			title : {
				text : '汇总统计各情况图',
				subtext : ''
			},
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
		dissatisfactionChart.clear();
		dissatisfactionChart.setOption(option);
		dissatisfactionChart.on("click", function(param) {
			if (param.componentSubType == "pie") {
				let index = param.dataIndex;
				let describe = option.dataset.source[index + 1][0];
				try {
					if (describe == "满意") {
						//业务分类
						$.post('/jwcpxt/Statistics/get_statisQuestionDataVO', {
							'statisDissaServiceDateVO.screenUnit' : params.jwcpxt_unit_id,
							'statisDissaServiceDateVO.startTime' : params.startTime,
							'statisDissaServiceDateVO.endTime' : params.endTime,
							'statisDissaServiceDateVO.timeType' : params.timeType
						}, response => {
							randerDissatisfiedServiceChart(response); //所有不满意图表
						}, 'json')
					} else if (describe == "不满意") {
						//业务分类
						$.post('/jwcpxt/Statistics/get_statisDissaServiceDateVO', {
							'statisDissaServiceDateVO.screenUnit' : params.jwcpxt_unit_id,
							'statisDissaServiceDateVO.startTime' : params.startTime,
							'statisDissaServiceDateVO.endTime' : params.endTime,
							'statisDissaServiceDateVO.timeType' : params.timeType
						}, response => {
							randerDissatisfiedServiceChart(response); //所有不满意图表
						}, 'json')
					}
				/*res.listStaDisDateDTO[0].listDissaOptionDTO.forEach(function(elt, i) {
					if (elt.option == describe) {
						console.log(definitionId);
						dissatisfactionProblemSendData();
						throw 'Jump';
					}
				})*/
				} catch (e) {
					console.log(e);
				}
			}
		});
	});
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
			formatter : `{b}: {@${_source[0][1]}} ({d}%)`
		},
		encode : {
			itemName : 'time',
			value : _source[0][1],
			tooltip : _source[0][1]
		}
	});
	//数据
	setTimeout(function() {
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
		dissatisfiedServiceChart.clear();
		dissatisfiedServiceChart.setOption(option);
		dissatisfiedServiceChart.on("click", function(param) {
			if (param.componentSubType == "pie") {
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
			}
		});
	});
}

function dissatisfactionProblemSendData() {
	$.post('/jwcpxt/Statistics/get_statisDissaQuestionDateVO', {
		'statisDissaQuestionDateVO.screenUnit' : params.jwcpxt_unit_id,
		'statisDissaQuestionDateVO.startTime' : params.startTime,
		'statisDissaQuestionDateVO.endTime' : params.endTime,
		'statisDissaQuestionDateVO.timeType' : params.timeType,
		'statisDissaQuestionDateVO.screenService' : definitionId
	}, response => {
		randerDissatisfactionProblem(response); //所有不满意问题
	}, 'json')
}
function randerDissatisfactionProblem(res) {
	let _source = [ [ 'time' ], ];
	res.listStatisQuestionDTO.forEach(function(elt, i) {
		_source[0].push(elt.dateScale);
		elt.listStatisQuestionDTO.forEach(function(elt1, j) {
			if (!_source[j + 1])
				_source[j + 1] = [];
			//(请问)?(您对)[\u4e00-\u9fa5]+(满意吗)[?|？]
			//(请问)[\u4e00-\u9fa5]+(有没有向您)[、\u4e00-\u9fa5]+[?|？]
			let question = elt1.questionOptionAnswerDTO.question.question_describe;
			let answer = elt1.questionOptionAnswerDTO.option.option_describe;
			let replaceQuestion;

			if (/(请问)?(您对)[\u4e00-\u9fa5]+(满意吗)[?|？]/.test(question)) {
				replaceQuestion = question.replace("请问", "").replace("您对", "对").replace("满意吗", answer).replace("？", "");
			} else if (/(请问)[\u4e00-\u9fa5]+(有没有向您)[、\u4e00-\u9fa5]+[?|？]/.test(question)) {
				replaceQuestion = question.replace("请问", "").replace("有没有向您", answer).replace("？", "");
			}

			_source[j + 1][0] = replaceQuestion;
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
			formatter : `{b}: {@${_source[0][1]}} ({d}%)`
		},
		encode : {
			itemName : 'time',
			value : _source[0][1],
			tooltip : _source[0][1]
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
	dissatisfactionProblemChart.clear();
	dissatisfactionProblemChart.setOption(option);
	dissatisfactionProblemChart.on("click", function(param) {
		if (param.componentSubType == "pie") {
			let index = param.dataIndex;
			//let describe = option.dataset.source[index + 1][0];
			let definitionId = res.listStatisQuestionDTO[0].listStatisQuestionDTO[index].questionOptionAnswerDTO.question.question_service_definition;
			let optiontext = $('#jwcpxt_unit_id option:selected').text();
			if (optiontext == '选择一个单位...')
				optiontext = '';
			//console.log('/Skip/skipClientInformationPage?definitionId=' + definitionId + '&unitId=' + optiontext);
			window.open('/jwcpxt/Skip/skipClientInformationPage?definitionId=' + definitionId + '&unitId=' + optiontext);
		/*try {
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
		}*/
		}
	});
}




































/*
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
}*/