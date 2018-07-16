$(function() {

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

	//折线图
	randerLineChart();
	//饼图
	randerPieChart();
	//绘制折线图
	function randerLineChart() {
		// 基于准备好的dom，初始化echarts实例
		var myChart = echarts.init(document.getElementById('main'));
		let option1 = {
			title : {
				text : '折线图堆叠'
			},
			tooltip : {
				enterable : true,
				trigger : 'axis'
			},
			legend : {
				data : [ '邮件营销', '联盟广告', '视频广告', '直接访问', '搜索引擎' ]
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
				data : [ '周一', '周二', '周三', '周四', '周五', '周六', '周日' ]
			},
			yAxis : {
				type : 'value'
			},
			series : [
				{
					name : '邮件营销',
					type : 'line',
					stack : '总量',
					data : [ 120, 132, 101, 134, 90, 230, 210 ]
				},
				{
					name : '联盟广告',
					type : 'line',
					stack : '总量',
					data : [ 220, 182, 191, 234, 290, 330, 310 ]
				},
				{
					name : '视频广告',
					type : 'line',
					stack : '总量',
					data : [ 150, 232, 201, 154, 190, 330, 410 ]
				},
				{
					name : '直接访问',
					type : 'line',
					stack : '总量',
					data : [ 320, 332, 301, 334, 390, 330, 320 ]
				},
				{
					name : '搜索引擎',
					type : 'line',
					stack : '总量',
					data : [ 820, 932, 901, 934, 1290, 1330, 1320 ]
				}
			]
		};
		// 使用刚指定的配置项和数据显示图表。
		myChart.setOption(option1);
	}


	//绘制饼图
	function randerPieChart() {
		var myChart2 = echarts.init(document.getElementById('main2'));
		// 指定图表的配置项和数据
		var data = genData(50);
		let option2 = {
			title : {
				text : '同名数量统计',
				subtext : '纯属虚构',
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
				data : data.legendData,

				selected : data.selected
			},
			series : [
				{
					name : '姓名',
					type : 'pie',
					radius : '55%',
					center : [ '40%', '50%' ],
					data : data.seriesData,
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
		function genData(count) {
			var nameList = [
				'赵', '钱', '孙', '李', '周', '吴', '郑', '王', '冯', '陈', '褚', '卫', '蒋', '沈', '韩', '杨', '朱', '秦', '尤', '许', '何', '吕', '施', '张', '孔', '曹', '严', '华', '金', '魏', '陶', '姜', '戚', '谢', '邹', '喻', '柏', '水', '窦', '章', '云', '苏', '潘', '葛', '奚', '范', '彭', '郎', '鲁', '韦', '昌', '马', '苗', '凤', '花', '方', '俞', '任', '袁', '柳', '酆', '鲍', '史', '唐', '费', '廉', '岑', '薛', '雷', '贺', '倪', '汤', '滕', '殷', '罗', '毕', '郝', '邬', '安', '常', '乐', '于', '时', '傅', '皮', '卞', '齐', '康', '伍', '余', '元', '卜', '顾', '孟', '平', '黄', '和', '穆', '萧', '尹', '姚', '邵', '湛', '汪', '祁', '毛', '禹', '狄', '米', '贝', '明', '臧', '计', '伏', '成', '戴', '谈', '宋', '茅', '庞', '熊', '纪', '舒', '屈', '项', '祝', '董', '梁', '杜', '阮', '蓝', '闵', '席', '季', '麻', '强', '贾', '路', '娄', '危'
			];
			var legendData = [];
			var seriesData = [];
			var selected = {};
			for (var i = 0; i < 50; i++) {
				name = Math.random() > 0.65
					? makeWord(4, 1) + '·' + makeWord(3, 0)
					: makeWord(2, 1);
				legendData.push(name);
				seriesData.push({
					name : name,
					value : Math.round(Math.random() * 100000)
				});
				selected[name] = i < 6;
			}

			return {
				legendData : legendData,
				seriesData : seriesData,
				selected : selected
			};

			function makeWord(max, min) {
				var nameLen = Math.ceil(Math.random() * max + min);
				var name = [];
				for (var i = 0; i < nameLen; i++) {
					name.push(nameList[Math.round(Math.random() * nameList.length - 1)]);
				}
				return name.join('');
			}
		}
		myChart2.setOption(option2);
	}
})