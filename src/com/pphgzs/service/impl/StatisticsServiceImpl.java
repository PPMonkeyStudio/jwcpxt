package com.pphgzs.service.impl;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.pphgzs.dao.StatisticsDao;
import com.pphgzs.dao.UnitDao;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_unit;
import com.pphgzs.domain.DTO.ClientAttentionServiceDTO;
import com.pphgzs.domain.DTO.DissatisfiedDTO;
import com.pphgzs.domain.DTO.QuestionOptionAnswerDTO;
import com.pphgzs.domain.DTO.ReturnVisitDTO;
import com.pphgzs.domain.DTO.ServiceGradeBelongUnitDTO;
import com.pphgzs.domain.DTO.ServiceGradeDTO;
import com.pphgzs.domain.DTO.StatisDIssaServiceDTO;
import com.pphgzs.domain.DTO.StatisDIssaServiceDateDTO;
import com.pphgzs.domain.DTO.StatisDissaQuestionDateDTO;
import com.pphgzs.domain.DTO.StatisQuestionDTO;
import com.pphgzs.domain.DTO.StatisticsDissatisfiedDateCountDTO;
import com.pphgzs.domain.DTO.StatisticsDissatisfiedDateDTO;
import com.pphgzs.domain.DTO.StatisticsDissatisfiedDayDataDTO;
import com.pphgzs.domain.DTO.StatisticsDissatisfiedOptionDTO;
import com.pphgzs.domain.DTO.UnitHaveServiceGradeDTO;
import com.pphgzs.domain.VO.ClientAttentionServiceVO;
import com.pphgzs.domain.VO.DissatisfiedVO;
import com.pphgzs.domain.VO.ReturnVisitVO;
import com.pphgzs.domain.VO.StatisDissaQuestionDateVO;
import com.pphgzs.domain.VO.StatisDissaServiceDateVO;
import com.pphgzs.domain.VO.StatisDissatiDateVO;
import com.pphgzs.domain.VO.StatisticsDissatisfiedDateCountVO;
import com.pphgzs.domain.VO.StatisticsDissatisfiedDayDataVO;
import com.pphgzs.domain.VO.StatisticsVO;
import com.pphgzs.service.ServiceService;
import com.pphgzs.service.StatisticsService;
import com.pphgzs.util.TimeUtil;
import com.pphgzs.util.WeekDayUtil;

public class StatisticsServiceImpl implements StatisticsService {
	private StatisticsDao statisticsDao;
	private UnitDao unitDao;
	private ServiceService serviceService;

	/**
	 * 群众最不满意的项目
	 */
	@Override
	public DissatisfiedVO get_clientDissatisfiedService(DissatisfiedVO dissatisfiedVO) {
		List<DissatisfiedDTO> listAttentionDTO = new ArrayList<>();
		DissatisfiedDTO dissatisfiedDTO = null;
		// 根据业务描述、问题描述、选项描述获取数量
		dissatisfiedDTO = new DissatisfiedDTO();
		dissatisfiedDTO.setServiceName("交通事故民警处理不及时");
		dissatisfiedDTO.setServiceCount(statisticsDao.getServiceOptionCount("交通事故报警", "请问您报警后，民警是否及时受理？", "否"));
		listAttentionDTO.add(dissatisfiedDTO);
		//
		dissatisfiedDTO = new DissatisfiedDTO();
		dissatisfiedDTO.setServiceName("民警执法不文明不规范");
		dissatisfiedDTO.setServiceCount(statisticsDao.getServiceOptionCount("交通事故报警", "请问您对民警文明规范执法是否满意？", "否")
				+ statisticsDao.getServiceOptionCount("110报警", "请问您对民警文明规范执法是否满意？", "不满意")
				+ statisticsDao.getServiceOptionCount("110报警", "请问您对民警文明规范执法是否满意？", "不太满意")
				+ statisticsDao.getServiceOptionCount("刑事案件", "请问您对民警文明规范执法是否满意？", "不满意")
				+ statisticsDao.getServiceOptionCount("刑事案件", "请问您对民警文明规范执法是否满意？", "不太满意"));
		listAttentionDTO.add(dissatisfiedDTO);
		//
		dissatisfiedDTO = new DissatisfiedDTO();
		dissatisfiedDTO.setServiceName("民警到达现场迟缓不及时");
		dissatisfiedDTO.setServiceCount(statisticsDao.getServiceOptionCount("110报警", "请问您报警后，民警是否及时到现场？", "半个小时之后到现场"));
		listAttentionDTO.add(dissatisfiedDTO);
		//
		dissatisfiedDTO = new DissatisfiedDTO();
		dissatisfiedDTO.setServiceName("民警未出现场");
		dissatisfiedDTO.setServiceCount(statisticsDao.getServiceOptionCount("110报警", "请问您报警后，民警是否及时到现场？", "没有出现场"));
		listAttentionDTO.add(dissatisfiedDTO);
		//
		dissatisfiedDTO = new DissatisfiedDTO();
		dissatisfiedDTO.setServiceName("窗口工作人员的服务态度差");
		dissatisfiedDTO
				.setServiceCount(statisticsDao.getServiceOptionCount("出入境办证", "请问您能对公安机关出入境办证窗口工作人员的服务态度满意吗？", "不满意")
						+ statisticsDao.getServiceOptionCount("出入境办证", "请问您能对公安机关出入境办证窗口工作人员的服务态度满意吗？", "不太满意")
						+ (statisticsDao.getServiceOptionCount("户政办证", "请问您对公安机关户政窗口工作人员的服务态度满意吗？", "不满意"))
						+ (statisticsDao.getServiceOptionCount("户政办证", "请问您对公安机关户政窗口工作人员的服务态度满意吗？", "不太满意"))
						+ statisticsDao.getServiceOptionCount("车管办证", "请问您对交警车管窗口工作人员的服务态度满意吗？", "不满意")
						+ statisticsDao.getServiceOptionCount("车管办证", "请问您对交警车管窗口工作人员的服务态度满意吗？", "不太满意"));
		listAttentionDTO.add(dissatisfiedDTO);
		//
		dissatisfiedDTO = new DissatisfiedDTO();
		dissatisfiedDTO.setServiceName("窗口工作人员办理业务效率低");
		dissatisfiedDTO.setServiceCount(statisticsDao.getServiceOptionCount("户政办证", "您对办理业务的工作人员办事效率满意吗？", "不满意")
				+ statisticsDao.getServiceOptionCount("户政办证", "您对办理业务的工作人员办事效率满意吗？", "不太满意")
				+ statisticsDao.getServiceOptionCount("车管办证", "请问您到交警车管窗口办理业务材料齐全时是否一次办结？", "否"));
		listAttentionDTO.add(dissatisfiedDTO);
		//
		dissatisfiedDTO = new DissatisfiedDTO();
		dissatisfiedDTO.setServiceName("市民对居住的社会治安不满意");
		dissatisfiedDTO.setServiceCount(
				statisticsDao.getServiceOptionCount("安全感满意度", "请问您对居住的地方社会治安感觉是安全、比较安全、不太安全、不安全？", "不安全")
						+ statisticsDao.getServiceOptionCount("安全感满意度", "请问您对居住的地方社会治安感觉是安全、比较安全、不太安全、不太安全？", "不安全"));
		listAttentionDTO.add(dissatisfiedDTO);
		//
		dissatisfiedDTO = new DissatisfiedDTO();
		dissatisfiedDTO.setServiceName("市民对本地公安机关工作不满意");
		dissatisfiedDTO.setServiceCount(
				statisticsDao.getServiceOptionCount("安全感满意度", "请问您对本地公安局、派出所的工作是满意、比较满意、不太满意还是不满意？", "不满意")
						+ statisticsDao.getServiceOptionCount("安全感满意度", "请问您对本地公安局、派出所的工作是满意、比较满意、不太满意还是不满意？", "不太满意"));
		listAttentionDTO.add(dissatisfiedDTO);
		//
		/**
		 * 按点数量正序 hy
		 */
		Collections.sort(listAttentionDTO, new Comparator<DissatisfiedDTO>() {
			public int compare(DissatisfiedDTO arg0, DissatisfiedDTO arg1) {
				int hits0 = arg0.getServiceCount();
				int hits1 = arg1.getServiceCount();
				if (hits1 > hits0) {
					return 1;
				} else if (hits1 == hits0) {
					return 0;
				} else {
					return -1;
				}
			}
		});
		dissatisfiedVO.setListAttentionDTO(listAttentionDTO);
		return dissatisfiedVO;
	}

	/**
	 * 群众最关注的业务
	 */
	@Override
	public ClientAttentionServiceVO get_clientAttentionService(ClientAttentionServiceVO clientAttentionServiceVO) {
		//
		List<ClientAttentionServiceDTO> listClientAttentionServiceDTO = new ArrayList<>();
		ClientAttentionServiceDTO clientAttentionServiceDTO = new ClientAttentionServiceDTO();
		// 根据业务描述、问题描述、选项描述获取数量
		clientAttentionServiceDTO.setAttentionService("交通事故民警处理及时");
		clientAttentionServiceDTO.setCount(statisticsDao.getServiceOptionCount("交通事故报警", "请问您报警后，民警是否及时受理？", "是"));
		listClientAttentionServiceDTO.add(clientAttentionServiceDTO);
		//
		clientAttentionServiceDTO = new ClientAttentionServiceDTO();
		clientAttentionServiceDTO.setAttentionService("民警执法文明规范");
		clientAttentionServiceDTO.setCount(statisticsDao.getServiceOptionCount("交通事故报警", "请问您对民警文明规范执法是否满意？", "是")
				+ statisticsDao.getServiceOptionCount("110报警", "请问您对民警文明规范执法是否满意？", "满意")
				+ statisticsDao.getServiceOptionCount("110报警", "请问您对民警文明规范执法是否满意？", "比较满意")
				+ statisticsDao.getServiceOptionCount("刑事案件", "请问您对民警文明规范执法是否满意？", "满意")
				+ statisticsDao.getServiceOptionCount("刑事案件", "请问您对民警文明规范执法是否满意？", "比较满意"));
		listClientAttentionServiceDTO.add(clientAttentionServiceDTO);
		//
		clientAttentionServiceDTO = new ClientAttentionServiceDTO();
		clientAttentionServiceDTO.setAttentionService("民警到达现场及时");
		clientAttentionServiceDTO.setCount(statisticsDao.getServiceOptionCount("110报警", "请问您报警后，民警是否及时到现场？", "及时"));
		listClientAttentionServiceDTO.add(clientAttentionServiceDTO);
		//
		clientAttentionServiceDTO = new ClientAttentionServiceDTO();
		clientAttentionServiceDTO.setAttentionService("窗口工作人员的服务态度好");
		clientAttentionServiceDTO
				.setCount(statisticsDao.getServiceOptionCount("出入境办证", "请问您能对公安机关出入境办证窗口工作人员的服务态度满意吗？", "满意")
						+ statisticsDao.getServiceOptionCount("出入境办证", "请问您能对公安机关出入境办证窗口工作人员的服务态度满意吗？", "比较满意")
						+ statisticsDao.getServiceOptionCount("户政办证", "请问您对公安机关户政窗口工作人员的服务态度满意吗？", "满意")
						+ statisticsDao.getServiceOptionCount("户政办证", "请问您对公安机关户政窗口工作人员的服务态度满意吗？", "比较满意")
						+ statisticsDao.getServiceOptionCount("车管办证", "请问您对交警车管窗口工作人员的服务态度满意吗？", "满意")
						+ statisticsDao.getServiceOptionCount("车管办证", "请问您对交警车管窗口工作人员的服务态度满意吗？", "比较满意"));
		listClientAttentionServiceDTO.add(clientAttentionServiceDTO);
		//
		clientAttentionServiceDTO = new ClientAttentionServiceDTO();
		clientAttentionServiceDTO.setAttentionService("窗口工作人员办事效率高");
		clientAttentionServiceDTO.setCount(statisticsDao.getServiceOptionCount("户政办证", "您对办理业务的工作人员办事效率满意吗？", "满意")
				+ statisticsDao.getServiceOptionCount("户政办证", "您对办理业务的工作人员办事效率满意吗？", "比较满意")
				+ statisticsDao.getServiceOptionCount("车管办证", "请问您到交警车管窗口办理业务材料齐全时是否一次办结？", "是"));
		listClientAttentionServiceDTO.add(clientAttentionServiceDTO);
		//
		clientAttentionServiceDTO = new ClientAttentionServiceDTO();
		clientAttentionServiceDTO.setAttentionService("市民对居住的社会治安满意");
		clientAttentionServiceDTO
				.setCount(statisticsDao.getServiceOptionCount("安全感满意度", "请问您对居住的地方社会治安感觉是安全、比较安全、不太安全、不安全？", "安全")
						+ statisticsDao.getServiceOptionCount("安全感满意度", "请问您对居住的地方社会治安感觉是安全、比较安全、不太安全、不安全？", "比较安全"));
		listClientAttentionServiceDTO.add(clientAttentionServiceDTO);
		//
		clientAttentionServiceDTO = new ClientAttentionServiceDTO();
		clientAttentionServiceDTO.setAttentionService("市民对本地公安机关工作满意");
		clientAttentionServiceDTO
				.setCount(statisticsDao.getServiceOptionCount("安全感满意度", "请问您对本地公安局、派出所的工作是满意、比较满意、不太满意还是不满意？", "满意")
						+ statisticsDao.getServiceOptionCount("安全感满意度", "请问您对本地公安局、派出所的工作是满意、比较满意、不太满意还是不满意？", "比较满意"));
		listClientAttentionServiceDTO.add(clientAttentionServiceDTO);
		/**
		 * 按点数量正序 hy
		 */
		Collections.sort(listClientAttentionServiceDTO, new Comparator<ClientAttentionServiceDTO>() {
			public int compare(ClientAttentionServiceDTO arg0, ClientAttentionServiceDTO arg1) {
				int hits0 = arg0.getCount();
				int hits1 = arg1.getCount();
				if (hits1 > hits0) {
					return 1;
				} else if (hits1 == hits0) {
					return 0;
				} else {
					return -1;
				}
			}
		});
		clientAttentionServiceVO.setListClientAttentionServiceDTO(listClientAttentionServiceDTO);
		return clientAttentionServiceVO;
	}

	/**
	 * 获取VO
	 */
	@Override
	public ReturnVisitVO getUserCountVO(ReturnVisitVO returnVisitVO) {
		List<ReturnVisitDTO> listReturnVisitDTO = new ArrayList<>();
		listReturnVisitDTO = statisticsDao.getUserCountVO(returnVisitVO);
		returnVisitVO.setListReturnVisitDTO(listReturnVisitDTO);
		return returnVisitVO;
	}

	/**
	 * 满意业务分布
	 */
	@Override
	public StatisDissaServiceDateVO get_statisQuestionDataVO(StatisDissaServiceDateVO statisDissaServiceDateVO) {
		// 定义
		List<String> listDate = new ArrayList<>();
		List<StatisDIssaServiceDateDTO> listStatisDIssaServiceDateDTO = new ArrayList<>();
		StatisDIssaServiceDateDTO statisDIssaServiceDateDTO = null;
		List<StatisDIssaServiceDTO> listStatisDissaServiceDTO = new ArrayList<>();
		StatisDIssaServiceDTO statisDIssaServiceDTO = new StatisDIssaServiceDTO();
		List<jwcpxt_service_definition> listServiceDefinition = new ArrayList<>();
		//
		if (!"".equals(statisDissaServiceDateVO.getStartTime()) && !"".equals(statisDissaServiceDateVO.getEndTime())) {
			if (!"".equals(statisDissaServiceDateVO.getTimeType())) {
				switch (statisDissaServiceDateVO.getTimeType()) {
				case "1":
					// 按天
					try {
						listDate = TimeUtil.findDates(statisDissaServiceDateVO.getStartTime(),
								statisDissaServiceDateVO.getEndTime());
					} catch (ParseException e) {
					}
					break;
				case "2":
					// 按周
					listDate = WeekDayUtil.getDates(statisDissaServiceDateVO.getStartTime(),
							statisDissaServiceDateVO.getEndTime(), "星期日");
					break;
				case "3":
					// 按月
					try {
						listDate = WeekDayUtil.getMonthBetween(statisDissaServiceDateVO.getStartTime(),
								statisDissaServiceDateVO.getEndTime());
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
		if (listDate.size() == 0) {
			return statisDissaServiceDateVO;
		}
		// 该时间段所有的业务
		listServiceDefinition = statisticsDao.get_statisService_byTime(statisDissaServiceDateVO);
		// 遍历时间
		for (int i = 1; i < listDate.size(); i++) {
			statisDIssaServiceDateDTO = new StatisDIssaServiceDateDTO();
			listStatisDissaServiceDTO = new ArrayList<>();
			// 获取对应的数量
			for (jwcpxt_service_definition serviceDefinition : listServiceDefinition) {
				//
				statisDIssaServiceDTO = new StatisDIssaServiceDTO();
				// 不满意数量
				int disCount = statisticsDao.statisticsDaoget_countService_byTime(statisDissaServiceDateVO,
						listDate.get(i - 1), listDate.get(i), serviceDefinition);
				// 总数量
				int totalCount = statisticsDao.get_totalCountService_byTime(statisDissaServiceDateVO,
						listDate.get(i - 1), listDate.get(i), serviceDefinition);
				int staisCount = totalCount - disCount;
				statisDIssaServiceDTO.setCount(staisCount);
				statisDIssaServiceDTO.setServiceDefinition(serviceDefinition);
				listStatisDissaServiceDTO.add(statisDIssaServiceDTO);
			}
			statisDIssaServiceDateDTO.setDateScale(listDate.get(i));
			statisDIssaServiceDateDTO.setListStatisDIssaServiceDTO(listStatisDissaServiceDTO);
			listStatisDIssaServiceDateDTO.add(statisDIssaServiceDateDTO);
		}
		statisDissaServiceDateVO.setListStatisDIssaServiceDateDTO(listStatisDIssaServiceDateDTO);
		return statisDissaServiceDateVO;
	}

	/**
	 * 某业务不满意问题分布情况
	 */
	@Override
	public StatisDissaQuestionDateVO get_statisDissaQuestionDateVO(
			StatisDissaQuestionDateVO statisDissaQuestionDateVO) {
		// 定义
		List<String> listDate = new ArrayList<>();
		List<QuestionOptionAnswerDTO> listQuestionOption = new ArrayList<>();
		List<QuestionOptionAnswerDTO> listOldQuestionOption = new ArrayList<>();
		List<StatisDissaQuestionDateDTO> listStatisQuestionDateDTO = new ArrayList<>();
		StatisDissaQuestionDateDTO statisDissaQuestionDateDTO = new StatisDissaQuestionDateDTO();
		List<StatisQuestionDTO> listStatisQuestionDTO = new ArrayList<>();
		StatisQuestionDTO statisQuestionDTO = new StatisQuestionDTO();
		//
		if (!"".equals(statisDissaQuestionDateVO.getStartTime()) && !"".equals(statisDissaQuestionDateVO)) {
			if (!"".equals(statisDissaQuestionDateVO.getTimeType())) {
				switch (statisDissaQuestionDateVO.getTimeType()) {
				case "1":
					// 按天
					try {
						listDate = TimeUtil.findDates(statisDissaQuestionDateVO.getStartTime(),
								statisDissaQuestionDateVO.getEndTime());
					} catch (ParseException e) {
					}
					break;
				case "2":
					// 按周
					listDate = WeekDayUtil.getDates(statisDissaQuestionDateVO.getStartTime(),
							statisDissaQuestionDateVO.getEndTime(), "星期日");
					break;
				case "3":
					// 按月
					try {
						listDate = WeekDayUtil.getMonthBetween(statisDissaQuestionDateVO.getStartTime(),
								statisDissaQuestionDateVO.getEndTime());
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
		if (listDate.size() == 0) {
			return statisDissaQuestionDateVO;
		}
		// 获取该业务的所有推送的问题以及选项
		listQuestionOption = statisticsDao.get_pushQuestionOption(statisDissaQuestionDateVO);
		// 去重
		for (QuestionOptionAnswerDTO questionOptionAnswerDTO : listQuestionOption) {
			int flag = 0;
			if (listOldQuestionOption.size() == 0) {
				listOldQuestionOption.add(questionOptionAnswerDTO);
			} else {
				for (QuestionOptionAnswerDTO oldQuestion : listOldQuestionOption) {
					if (questionOptionAnswerDTO.getQuestion().getQuestion_describe()
							.equals(oldQuestion.getQuestion().getQuestion_describe())
							&& questionOptionAnswerDTO.getOption().getOption_describe()
									.equals(oldQuestion.getOption().getOption_describe())) {
						flag++;
					}
				}
				if (flag == 0) {
					listOldQuestionOption.add(questionOptionAnswerDTO);
				}
			}
		}
		// 遍历时间
		for (int i = 1; i < listDate.size(); i++) {
			statisDissaQuestionDateDTO = new StatisDissaQuestionDateDTO();
			listStatisQuestionDTO = new ArrayList<>();
			for (QuestionOptionAnswerDTO oldQuest : listOldQuestionOption) {
				statisQuestionDTO = new StatisQuestionDTO();
				int count = statisticsDao.get_countStatisDateDTO(statisDissaQuestionDateVO, listDate.get(i - 1),
						listDate.get(i), oldQuest);
				statisQuestionDTO.setCount(count);
				statisQuestionDTO.setQuestionOptionAnswerDTO(oldQuest);
				listStatisQuestionDTO.add(statisQuestionDTO);
			}
			statisDissaQuestionDateDTO.setListStatisQuestionDTO(listStatisQuestionDTO);
			statisDissaQuestionDateDTO.setDateScale(listDate.get(i));
			listStatisQuestionDateDTO.add(statisDissaQuestionDateDTO);
		}
		statisDissaQuestionDateVO.setListStatisQuestionDTO(listStatisQuestionDateDTO);
		return statisDissaQuestionDateVO;
	}

	/**
	 * 获得业务的不满意反馈分布
	 * 
	 * @param statisDissaServiceDateVO
	 * @return
	 */
	@Override
	public StatisDissaServiceDateVO get_statisDissaServiceDateVO(StatisDissaServiceDateVO statisDissaServiceDateVO) {
		// 定义
		List<String> listDate = new ArrayList<>();
		List<StatisDIssaServiceDateDTO> listStatisDIssaServiceDateDTO = new ArrayList<>();
		StatisDIssaServiceDateDTO statisDIssaServiceDateDTO = null;
		List<StatisDIssaServiceDTO> listStatisDissaServiceDTO = new ArrayList<>();
		StatisDIssaServiceDTO statisDIssaServiceDTO = new StatisDIssaServiceDTO();
		List<jwcpxt_service_definition> listServiceDefinition = new ArrayList<>();
		//
		if (!"".equals(statisDissaServiceDateVO.getStartTime()) && !"".equals(statisDissaServiceDateVO.getEndTime())) {
			if (!"".equals(statisDissaServiceDateVO.getTimeType())) {
				switch (statisDissaServiceDateVO.getTimeType()) {
				case "1":
					// 按天
					try {
						listDate = TimeUtil.findDates(statisDissaServiceDateVO.getStartTime(),
								statisDissaServiceDateVO.getEndTime());
					} catch (ParseException e) {
					}
					break;
				case "2":
					// 按周
					listDate = WeekDayUtil.getDates(statisDissaServiceDateVO.getStartTime(),
							statisDissaServiceDateVO.getEndTime(), "星期日");
					break;
				case "3":
					// 按月
					try {
						listDate = WeekDayUtil.getMonthBetween(statisDissaServiceDateVO.getStartTime(),
								statisDissaServiceDateVO.getEndTime());
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
		if (listDate.size() == 0) {
			return statisDissaServiceDateVO;
		}
		// 获取对应时间段里面的业务数量
		listServiceDefinition = statisticsDao.get_pushService_byTime(statisDissaServiceDateVO);
		// 遍历时间
		for (int i = 1; i < listDate.size(); i++) {
			statisDIssaServiceDateDTO = new StatisDIssaServiceDateDTO();
			listStatisDissaServiceDTO = new ArrayList<>();
			// 获取对应的数量
			for (jwcpxt_service_definition serviceDefinition : listServiceDefinition) {
				//
				statisDIssaServiceDTO = new StatisDIssaServiceDTO();
				//
				int count = statisticsDao.statisticsDaoget_countService_byTime(statisDissaServiceDateVO,
						listDate.get(i - 1), listDate.get(i), serviceDefinition);
				statisDIssaServiceDTO.setCount(count);
				statisDIssaServiceDTO.setServiceDefinition(serviceDefinition);
				listStatisDissaServiceDTO.add(statisDIssaServiceDTO);
			}
			statisDIssaServiceDateDTO.setDateScale(listDate.get(i));
			statisDIssaServiceDateDTO.setListStatisDIssaServiceDTO(listStatisDissaServiceDTO);
			listStatisDIssaServiceDateDTO.add(statisDIssaServiceDateDTO);
		}
		statisDissaServiceDateVO.setListStatisDIssaServiceDateDTO(listStatisDIssaServiceDateDTO);
		return statisDissaServiceDateVO;
	}

	/**
	 * 获取时间结点里所有推送的选项描述，及其数量
	 */
	@Override
	public StatisDissatiDateVO get_statisDissatiDateVO(StatisDissatiDateVO statisDissatiDateVO) {
		// 定义
		List<String> listDate = new ArrayList<>();
		List<StatisticsDissatisfiedDateDTO> listStatDissDateDTO = new ArrayList<>();
		StatisticsDissatisfiedDateDTO statisticsDissatisfiedDateDTO = new StatisticsDissatisfiedDateDTO();
		StatisticsDissatisfiedOptionDTO statisticsDissatisfiedOptionDTO = new StatisticsDissatisfiedOptionDTO();
		List<StatisticsDissatisfiedOptionDTO> listDissaOptionDTO = new ArrayList<>();
		List<String> listOption = new ArrayList<>();
		//
		if (!"".equals(statisDissatiDateVO.getStartTime()) && !"".equals(statisDissatiDateVO.getEndTime())) {
			if (!"".equals(statisDissatiDateVO.getTimeType())) {
				switch (statisDissatiDateVO.getTimeType()) {
				case "1":
					// 按天
					try {
						listDate = TimeUtil.findDates(statisDissatiDateVO.getStartTime(),
								statisDissatiDateVO.getEndTime());
					} catch (ParseException e) {
					}
					break;
				case "2":
					// 按周
					listDate = WeekDayUtil.getDates(statisDissatiDateVO.getStartTime(),
							statisDissatiDateVO.getEndTime(), "星期日");
					break;
				case "3":
					// 按月
					try {
						listDate = WeekDayUtil.getMonthBetween(statisDissatiDateVO.getStartTime(),
								statisDissatiDateVO.getEndTime());
					} catch (Exception e) {
						e.printStackTrace();
					}
					break;
				}
			}
		}
		if (listDate.size() == 0) {
			return statisDissatiDateVO;
		}
		// 获取所有的不满意推送选项----单位筛选
		listOption = statisticsDao.get_pushOption_byTime(statisDissatiDateVO);
		// 遍历时间
		for (int i = 1; i < listDate.size(); i++) {
			//
			statisticsDissatisfiedDateDTO = new StatisticsDissatisfiedDateDTO();
			listDissaOptionDTO = new ArrayList<>();
			// 获取当天业务回答的总数
			int totalAnswer = statisticsDao.get_totaolCount(statisDissatiDateVO, listDate.get(i - 1), listDate.get(i));
			int disCount = 0;
			// 获取对应的数量
			for (String option : listOption) {
				statisticsDissatisfiedOptionDTO = new StatisticsDissatisfiedOptionDTO();
				int count = statisticsDao.get_countOption_byTime(statisDissatiDateVO, listDate.get(i - 1),
						listDate.get(i), option);
				disCount = disCount + count;
				statisticsDissatisfiedOptionDTO.setCount(count);
				statisticsDissatisfiedOptionDTO.setOption(option);
				listDissaOptionDTO.add(statisticsDissatisfiedOptionDTO);
			}
			statisticsDissatisfiedOptionDTO = new StatisticsDissatisfiedOptionDTO();
			if ((totalAnswer - disCount) < 0) {
				statisticsDissatisfiedOptionDTO.setCount(0);
			} else {
				statisticsDissatisfiedOptionDTO.setCount(totalAnswer - disCount);
			}
			statisticsDissatisfiedOptionDTO.setOption("满意");
			listDissaOptionDTO.add(statisticsDissatisfiedOptionDTO);
			statisticsDissatisfiedDateDTO.setDateScale(listDate.get(i));
			statisticsDissatisfiedDateDTO.setListDissaOptionDTO(listDissaOptionDTO);
			listStatDissDateDTO.add(statisticsDissatisfiedDateDTO);
		}
		statisDissatiDateVO.setListStaDisDateDTO(listStatDissDateDTO);
		return statisDissatiDateVO;
	}

	@Override
	public StatisticsDissatisfiedDateCountVO get_StatisticsDissatisfiedDateCountVO(
			StatisticsDissatisfiedDateCountVO statisticsDissatisfiedDateCountVO) {
		/**
		 * 取出单位信息
		 */
		statisticsDissatisfiedDateCountVO
				.setUnit(unitDao.get_unitDO_byID(statisticsDissatisfiedDateCountVO.getUnit().getJwcpxt_unit_id()));
		/*
		 * 取出此单位所有的业务定义
		 */
		statisticsDissatisfiedDateCountVO
				.setUnit(unitDao.get_unitDO_byID(statisticsDissatisfiedDateCountVO.getUnit().getJwcpxt_unit_id()));
		List<jwcpxt_service_definition> serviceDefinitionList = serviceService
				.list_serviceDefinitionDOList_byUnitID(statisticsDissatisfiedDateCountVO.getUnit().getJwcpxt_unit_id());
		/*
		 * 遍历业务定义，以天为单位取得这个单位的这个业务在这一天的错误数量。
		 */
		List<StatisticsDissatisfiedDateCountDTO> statisticsDissatisfiedDateCountDTOList = new ArrayList<StatisticsDissatisfiedDateCountDTO>();
		for (jwcpxt_service_definition serviceDefinition : serviceDefinitionList) {
			StatisticsDissatisfiedDateCountDTO statisticsDissatisfiedDateCountDTO = new StatisticsDissatisfiedDateCountDTO();
			int dayCount = 0;
			if (statisticsDissatisfiedDateCountVO.getUnit().getUnit_grade() == 2) {
				dayCount = statisticsDao.get_StatisticsDissatisfiedDateCount_byFatherUnit(
						serviceDefinition.getJwcpxt_service_definition_id(),
						statisticsDissatisfiedDateCountVO.getUnit().getJwcpxt_unit_id(),
						statisticsDissatisfiedDateCountVO.getStartTime(),
						statisticsDissatisfiedDateCountVO.getEndTime());
			} else if (statisticsDissatisfiedDateCountVO.getUnit().getUnit_grade() == 3) {
				dayCount = statisticsDao.get_StatisticsDissatisfiedDateCount(
						serviceDefinition.getJwcpxt_service_definition_id(),
						statisticsDissatisfiedDateCountVO.getUnit().getJwcpxt_unit_id(),
						statisticsDissatisfiedDateCountVO.getStartTime(),
						statisticsDissatisfiedDateCountVO.getEndTime());
			}

			statisticsDissatisfiedDateCountDTO.setDayCount(dayCount);
			statisticsDissatisfiedDateCountDTO.setServiceDefinition(serviceDefinition);
			statisticsDissatisfiedDateCountDTOList.add(statisticsDissatisfiedDateCountDTO);
		}
		statisticsDissatisfiedDateCountVO.setStatisticsDissatisfiedDateCountDTO(statisticsDissatisfiedDateCountDTOList);
		return statisticsDissatisfiedDateCountVO;
	}

	@Override
	public StatisticsDissatisfiedDayDataVO get_StatisticsDissatisfiedDayDataVO(
			StatisticsDissatisfiedDayDataVO statisticsDissatisfiedDayDataVO) {
		/**
		 * 取出单位信息
		 */
		statisticsDissatisfiedDayDataVO
				.setUnit(unitDao.get_unitDO_byID(statisticsDissatisfiedDayDataVO.getUnit().getJwcpxt_unit_id()));
		/*
		 * 取出此单位所有的业务定义，
		 */
		statisticsDissatisfiedDayDataVO
				.setUnit(unitDao.get_unitDO_byID(statisticsDissatisfiedDayDataVO.getUnit().getJwcpxt_unit_id()));
		List<jwcpxt_service_definition> serviceDefinitionList = serviceService
				.list_serviceDefinitionDOList_byUnitID(statisticsDissatisfiedDayDataVO.getUnit().getJwcpxt_unit_id());
		/*
		 * 遍历业务定义，以天为单位取得这个单位的这个业务在这一天的错误数量。
		 */
		List<StatisticsDissatisfiedDayDataDTO> statisticsDissatisfiedDayDataDTOList = new ArrayList<StatisticsDissatisfiedDayDataDTO>();
		for (jwcpxt_service_definition serviceDefinition : serviceDefinitionList) {
			StatisticsDissatisfiedDayDataDTO statisticsDissatisfiedDayDataDTO = new StatisticsDissatisfiedDayDataDTO();
			List<Integer> dayNumList = new ArrayList<Integer>();

			String startDate = statisticsDissatisfiedDayDataVO.getStartTime();
			String endDate = statisticsDissatisfiedDayDataVO.getEndTime();
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// 24小时制
			long time1 = 0;
			long time2 = 0;
			try {
				time1 = simpleDateFormat.parse(startDate).getTime();
				time2 = simpleDateFormat.parse(endDate).getTime();
			} catch (ParseException e) {
				e.printStackTrace();
			}
			// 求每天
			for (long time = time1; time <= time2; time = time + 86400000) {

				int dayNum = 0;
				if (statisticsDissatisfiedDayDataVO.getUnit().getUnit_grade() == 2) {
					dayNum = statisticsDao.get_dayNum_byServiceDefinitionIDAndDate_byFatherUnit(
							serviceDefinition.getJwcpxt_service_definition_id(),
							statisticsDissatisfiedDayDataVO.getUnit().getJwcpxt_unit_id(),
							simpleDateFormat.format(new Date(time)));
				} else if (statisticsDissatisfiedDayDataVO.getUnit().getUnit_grade() == 3) {
					dayNum = statisticsDao.get_dayNum_byServiceDefinitionIDAndDate(
							serviceDefinition.getJwcpxt_service_definition_id(),
							statisticsDissatisfiedDayDataVO.getUnit().getJwcpxt_unit_id(),
							simpleDateFormat.format(new Date(time)));
				}

				dayNumList.add(dayNum);
			}
			statisticsDissatisfiedDayDataDTO.setDayNumList(dayNumList);
			statisticsDissatisfiedDayDataDTO.setServiceDefinition(serviceDefinition);
			statisticsDissatisfiedDayDataDTOList.add(statisticsDissatisfiedDayDataDTO);
		}
		statisticsDissatisfiedDayDataVO.setStatisticsDissatisfiedDayData(statisticsDissatisfiedDayDataDTOList);
		return statisticsDissatisfiedDayDataVO;
	}

	@Override
	public void writeStatisticsExcel(StatisticsVO statisticsVO, HSSFWorkbook wb) {
		// 第二步，在webbook中添加一个sheet，对应Excel文件中的 sheet
		HSSFSheet sheet = wb.createSheet("统计数据");
		// 第三步，在sheet中添加表头第0行，注意老版本poi对Excel的行数列数有限制
		HSSFRow row = sheet.createRow(0);

		// 第五步，创建表头单元格，并设置样式
		HSSFCell cell;

		// 表头数
		int sheetHead_num = 0;

		/**
		 * 设置表头
		 */
		cell = row.createCell(sheetHead_num++);
		cell.setCellValue("单位");
		//
		for (String sheetHeadName : statisticsVO.getSheetHeadNameList()) {
			cell = row.createCell(sheetHead_num++);
			cell.setCellValue(sheetHeadName);
		}
		//
		cell = row.createCell(sheetHead_num++);
		cell.setCellValue("总分");
		//
		cell = row.createCell(sheetHead_num++);
		cell.setCellValue("排名");

		/**
		 * 写入数据 遍历单位
		 */
		int listNum = 1;
		for (UnitHaveServiceGradeDTO unitHaveServiceGradeDTO : statisticsVO.getUnitHaveServiceGradeDTOList()) {
			/*
			 * 一行
			 */
			row = sheet.createRow(listNum++);
			/*
			 * 遍历一个单位的分
			 */
			sheetHead_num = 0;
			// 单位
			cell = row.createCell(sheetHead_num++);
			cell.setCellValue(unitHaveServiceGradeDTO.getUnit().getUnit_name());
			//
			for (ServiceGradeBelongUnitDTO serviceGradeBelongUnitDTO : unitHaveServiceGradeDTO
					.getServiceGradeBelongUnitDTOList()) {
				cell = row.createCell(sheetHead_num++);
				cell.setCellValue(serviceGradeBelongUnitDTO.getGrade());
			}
			// 总分
			cell = row.createCell(sheetHead_num++);
			cell.setCellValue(unitHaveServiceGradeDTO.getTotalGrade());
			// 排名
			cell = row.createCell(sheetHead_num++);
			cell.setCellValue(listNum - 1);

		}
	}

	@Override
	public StatisticsVO getGradeByCondition(String[] unitIds, String searchTimeStart, String searchTimeEnd,
			List<ServiceGradeDTO> serviceGradeDTOList) {
		StatisticsVO statisticsVO = new StatisticsVO();
		List<UnitHaveServiceGradeDTO> unitHaveServiceGradeDTOList = new ArrayList<UnitHaveServiceGradeDTO>();

		UnitHaveServiceGradeDTO unitHaveServiceGradeDTO;
		for (int i = 0; i < unitIds.length; i++) {
			double totalGrade = 0;
			// 创建一个单位
			unitHaveServiceGradeDTO = new UnitHaveServiceGradeDTO();
			jwcpxt_unit unit = unitDao.get_unitDO_byID(unitIds[i]);
			unitHaveServiceGradeDTO.setUnit(unit);
			// 创建一个业务分数list
			List<ServiceGradeBelongUnitDTO> serviceGradeBelongUnitDTOList = new ArrayList<ServiceGradeBelongUnitDTO>();
			// 遍历需要统计的业务，查询这项业务分数
			for (ServiceGradeDTO serviceGradeDTO : serviceGradeDTOList) {
				double statisticsGrade;
				// 创建一个业务分数DTO
				ServiceGradeBelongUnitDTO serviceGradeBelongUnitDTO = new ServiceGradeBelongUnitDTO();
				// 将业务DO放入到DTO中
				serviceGradeBelongUnitDTO.setServiceDefinition(
						serviceService.get_serviceDefinitionDO_byServiceDefinitionID(serviceGradeDTO.getService_id()));
				// 统计这个单位下这个业务所得分

				if (unit.getUnit_grade() == 2) {
					statisticsGrade = statisticsDao.geteStatisticsGrade_byFatherUnit(serviceGradeDTO, unitIds[i],
							searchTimeStart, searchTimeEnd);
				} else {
					statisticsGrade = statisticsDao.geteStatisticsGrade(serviceGradeDTO, unitIds[i], searchTimeStart,
							searchTimeEnd);
				}
				totalGrade = totalGrade + statisticsGrade;
				statisticsGrade = ((int) (statisticsGrade * 10000 + 0.5)) / 10000.0;
				totalGrade = ((int) (totalGrade * 10000 + 0.5)) / 10000.0;
				// 将所得分放入到DTO中
				serviceGradeBelongUnitDTO.setGrade(statisticsGrade);
				// 将一个业务及他的分数的DTO放入到DTO列表中
				serviceGradeBelongUnitDTOList.add(serviceGradeBelongUnitDTO);
			}
			// 把所有业务的分数放入到单位中
			unitHaveServiceGradeDTO.setServiceGradeBelongUnitDTOList(serviceGradeBelongUnitDTOList);
			unitHaveServiceGradeDTO.setTotalGrade(totalGrade);
			// 这个单位DTO放入到单位列表中
			unitHaveServiceGradeDTOList.add(unitHaveServiceGradeDTO);

		}
		// 将统计完的所有单位放到VO中
		statisticsVO.setUnitHaveServiceGradeDTOList(unitHaveServiceGradeDTOList);
		/*
		 * 表头列表
		 */
		List<String> sheetHeadNameList = new ArrayList<String>();
		for (ServiceGradeDTO _serviceGradeDTO : serviceGradeDTOList) {
			if (_serviceGradeDTO.getService_id().equals("revisit")) {
				sheetHeadNameList.add("整改情况");
			} else {
				jwcpxt_service_definition serviceDefinition = serviceService
						.get_serviceDefinitionDO_byServiceDefinitionID(_serviceGradeDTO.getService_id());
				if (serviceDefinition != null) {
					sheetHeadNameList.add(serviceDefinition.getService_definition_describe());
				}

			}

		}
		statisticsVO.setSheetHeadNameList(sheetHeadNameList);
		/*
		 * 
		 */
		return statisticsVO;
	}

	public StatisticsDao getStatisticsDao() {
		return statisticsDao;
	}

	public void setStatisticsDao(StatisticsDao statisticsDao) {
		this.statisticsDao = statisticsDao;
	}

	public UnitDao getUnitDao() {
		return unitDao;
	}

	public void setUnitDao(UnitDao unitDao) {
		this.unitDao = unitDao;
	}

	public ServiceService getServiceService() {
		return serviceService;
	}

	public void setServiceService(ServiceService serviceService) {
		this.serviceService = serviceService;
	}

}
