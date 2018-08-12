package com.pphgzs.action;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.opensymphony.xwork2.ActionContext;
import com.pphgzs.domain.DTO.MonthDayMountDTO;
import com.pphgzs.domain.DTO.ServiceGradeDTO;
import com.pphgzs.domain.DTO.UnitHaveServiceGradeDTO;
import com.pphgzs.domain.VO.ClientAttentionServiceVO;
import com.pphgzs.domain.VO.DissatisfiedVO;
import com.pphgzs.domain.VO.MonthDayMountVO;
import com.pphgzs.domain.VO.ReturnVisitVO;
import com.pphgzs.domain.VO.StatisDissaQuestionDateVO;
import com.pphgzs.domain.VO.StatisDissaServiceDateVO;
import com.pphgzs.domain.VO.StatisDissatiDateVO;
import com.pphgzs.domain.VO.StatisticsDissatisfiedDateCountVO;
import com.pphgzs.domain.VO.StatisticsDissatisfiedDayDataVO;
import com.pphgzs.domain.VO.StatisticsVO;
import com.pphgzs.service.StatisticsService;
import com.pphgzs.util.TimeUtil;

/**
 * 
 * @author 孙毅
 *
 */
public class StatisticsAction implements ServletRequestAware, ServletResponseAware {

	private HttpServletRequest request;
	private HttpServletResponse response;
	private StatisticsService statisticsService;
	private List<ServiceGradeDTO> serviceGradeDTOList;
	private String[] unitIds;
	private String searchTimeStart;
	private String searchTimeEnd;

	private StatisticsDissatisfiedDayDataVO statisticsDissatisfiedDayDataVO;
	private StatisticsDissatisfiedDateCountVO statisticsDissatisfiedDateCountVO;
	private StatisDissatiDateVO statisDissatiDateVO;
	private StatisDissaServiceDateVO statisDissaServiceDateVO;
	private StatisDissaQuestionDateVO statisDissaQuestionDateVO;
	private ReturnVisitVO returnVisitVO;
	private ClientAttentionServiceVO clientAttentionServiceVO;
	private DissatisfiedVO dissatisfiedVO;
	private MonthDayMountVO monthDayMountVO;
	private String startTime = "0000-01-01";
	private String endTime = "9999-12-31";
	/*
	 * 
	 */
	private InputStream inputStream; // 输出流变量
	private String excelFileName; // 下载文件名
	/*
	 * 
	 */

	/**
	 * 获取当月满意数量以及当月总数量以及当日的数量
	 * 
	 * @throws IOException
	 */
	public void downloadData() throws IOException {
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		response.setContentType("text/html;charset=utf-8");
		if (monthDayMountVO == null) {
			monthDayMountVO = new MonthDayMountVO();
		}
		if (startTime == null || "".equals(startTime)) {
			startTime = "0000-01-01";
		}
		if (endTime == null || "".equals(endTime)) {
			endTime = "9999-12-31";
		}
		if (!TimeUtil.validate(startTime, endTime)) {
			response.getWriter().write("参数格式错误!");
			return;
		}
		monthDayMountVO.setEndTime(endTime);
		monthDayMountVO.setStartTime(startTime);
		monthDayMountVO = statisticsService.get_dataMonthDayMount(monthDayMountVO);
		response.getWriter().write(gson.toJson(monthDayMountVO.getMonthDayMountDTO()));
	}

	/**
	 * 群众最不满意业务
	 * 
	 * @throws IOException
	 */
	public void get_clientDissatisfiedService() throws IOException {
		dissatisfiedVO = statisticsService.get_clientDissatisfiedService(dissatisfiedVO);
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(gson.toJson(dissatisfiedVO));
	}

	/**
	 * 获取群众最关注的业务（满意的）
	 * 
	 * @throws IOException
	 */
	public void get_clientAttentionService() throws IOException {
		clientAttentionServiceVO = statisticsService.get_clientAttentionService(clientAttentionServiceVO);
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(gson.toJson(clientAttentionServiceVO));
	}

	/**
	 * 获取测评员测评数量比
	 * 
	 * @throws IOException
	 */
	public void getUserCountVO() throws IOException {
		returnVisitVO = statisticsService.getUserCountVO(returnVisitVO);
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(gson.toJson(returnVisitVO));
	}

	/**
	 * @throws IOException
	 * 
	 */
	public void get_statisQuestionDataVO() throws IOException {
		statisDissaServiceDateVO = statisticsService.get_statisQuestionDataVO(statisDissaServiceDateVO);
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(gson.toJson(statisDissaServiceDateVO));
	}

	/**
	 * 问题分布
	 * 
	 * @throws IOException
	 */
	public void get_statisDissaQuestionDateVO() throws IOException {
		statisDissaQuestionDateVO = statisticsService.get_statisDissaQuestionDateVO(statisDissaQuestionDateVO);
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(gson.toJson(statisDissaQuestionDateVO));
	}

	/**
	 * 获取业务不满意分配
	 * 
	 * @throws IOException
	 */
	public void get_statisDissaServiceDateVO() throws IOException {
		statisDissaServiceDateVO = statisticsService.get_statisDissaServiceDateVO(statisDissaServiceDateVO);
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(gson.toJson(statisDissaServiceDateVO));
	}

	/**
	 * 获取时间结点里所有推送的选项描述，及其数量
	 * 
	 * @throws IOException
	 */
	public void get_statisDissatiDateVO() throws IOException {
		statisDissatiDateVO = statisticsService.get_statisDissatiDateVO(statisDissatiDateVO);
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(gson.toJson(statisDissatiDateVO));
	}

	public void get_StatisticsDissatisfiedDateCountVO() throws IOException {
		statisticsDissatisfiedDateCountVO = statisticsService
				.get_StatisticsDissatisfiedDateCountVO(statisticsDissatisfiedDateCountVO);
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(gson.toJson(statisticsDissatisfiedDateCountVO));
	}

	public void get_StatisticsDissatisfiedDayDataVO() throws IOException {
		statisticsDissatisfiedDayDataVO = statisticsService
				.get_StatisticsDissatisfiedDayDataVO(statisticsDissatisfiedDayDataVO);
		//
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.setPrettyPrinting();// 格式化json数据
		Gson gson = gsonBuilder.serializeNulls().create();
		//
		response.setContentType("text/html;charset=utf-8");
		response.getWriter().write(gson.toJson(statisticsDissatisfiedDayDataVO));
	}

	public void getGradeByCondition() {
		StatisticsVO statisticsVO = statisticsService.getGradeByCondition(unitIds, searchTimeStart, searchTimeEnd,
				serviceGradeDTOList);

		UnitHaveServiceGradeDTO unitHaveServiceGradeDTO = new UnitHaveServiceGradeDTO();

		for (int i = 0; i < statisticsVO.getUnitHaveServiceGradeDTOList().size() - 1; i++) {
			for (int j = 0; j < statisticsVO.getUnitHaveServiceGradeDTOList().size() - 1 - i; j++) {
				if (statisticsVO.getUnitHaveServiceGradeDTOList().get(j).getTotalGrade() < statisticsVO
						.getUnitHaveServiceGradeDTOList().get(j + 1).getTotalGrade()) {
					unitHaveServiceGradeDTO = statisticsVO.getUnitHaveServiceGradeDTOList().get(j);
					statisticsVO.getUnitHaveServiceGradeDTOList().set(j,
							statisticsVO.getUnitHaveServiceGradeDTOList().get(j + 1));
					statisticsVO.getUnitHaveServiceGradeDTOList().set(j + 1, unitHaveServiceGradeDTO);
				}
			}
		}
		Gson gson = new Gson();
		String result = gson.toJson(statisticsVO);
		try {
			response.setContentType("text/html;charset=utf-8");
			PrintWriter pw = response.getWriter();
			pw.write(result);
			pw.flush();
			pw.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public String getGradeByCondition_valueStack() {
		StatisticsVO statisticsVO = statisticsService.getGradeByCondition(unitIds, searchTimeStart, searchTimeEnd,
				serviceGradeDTOList);
		UnitHaveServiceGradeDTO unitHaveServiceGradeDTO = new UnitHaveServiceGradeDTO();
		for (int i = 0; i < statisticsVO.getUnitHaveServiceGradeDTOList().size() - 1; i++) {
			for (int j = 0; j < statisticsVO.getUnitHaveServiceGradeDTOList().size() - 1 - i; j++) {
				if (statisticsVO.getUnitHaveServiceGradeDTOList().get(j).getTotalGrade() < statisticsVO
						.getUnitHaveServiceGradeDTOList().get(j + 1).getTotalGrade()) {
					unitHaveServiceGradeDTO = statisticsVO.getUnitHaveServiceGradeDTOList().get(j);
					statisticsVO.getUnitHaveServiceGradeDTOList().set(j,
							statisticsVO.getUnitHaveServiceGradeDTOList().get(j + 1));
					statisticsVO.getUnitHaveServiceGradeDTOList().set(j + 1, unitHaveServiceGradeDTO);
				}
			}
		}
		Gson gson = new Gson();
		String result = gson.toJson(statisticsVO);
		ActionContext.getContext().getValueStack().set("statisticsVO", result);

		return "getGradeByCondition_valueStack";
	}

	public String exportStatisticsExcel() {
		StatisticsVO statisticsVO = statisticsService.getGradeByCondition(unitIds, searchTimeStart, searchTimeEnd,
				serviceGradeDTOList);
		UnitHaveServiceGradeDTO unitHaveServiceGradeDTO = new UnitHaveServiceGradeDTO();
		for (int i = 0; i < statisticsVO.getUnitHaveServiceGradeDTOList().size() - 1; i++) {
			for (int j = 0; j < statisticsVO.getUnitHaveServiceGradeDTOList().size() - 1 - i; j++) {
				if (statisticsVO.getUnitHaveServiceGradeDTOList().get(j).getTotalGrade() < statisticsVO
						.getUnitHaveServiceGradeDTOList().get(j + 1).getTotalGrade()) {
					unitHaveServiceGradeDTO = new UnitHaveServiceGradeDTO();
					unitHaveServiceGradeDTO = statisticsVO.getUnitHaveServiceGradeDTOList().get(j);
					statisticsVO.getUnitHaveServiceGradeDTOList().set(j,
							statisticsVO.getUnitHaveServiceGradeDTOList().get(j + 1));
					statisticsVO.getUnitHaveServiceGradeDTOList().set(j + 1, unitHaveServiceGradeDTO);
				}
			}
		}
		/**
		 * 写入文件
		 */

		try {
			HSSFWorkbook wb = new HSSFWorkbook();
			/**
			 * 写
			 */

			statisticsService.writeStatisticsExcel(statisticsVO, wb);

			/**
			 * 写完毕
			 */
			// 第七步，将文件存到流中
			ByteArrayOutputStream os = new ByteArrayOutputStream();
			wb.write(os);
			byte[] fileContent = os.toByteArray();
			ByteArrayInputStream is = new ByteArrayInputStream(fileContent);

			inputStream = is; // 文件流
			excelFileName = "" + TimeUtil.getStringSecond() + ".xls"; // 设置下载的文件名

		} catch (Exception e) {
			e.printStackTrace();
		}

		return "exportStatisticsExcel";
	}
	/*
	 * 
	 */

	public String[] getUnitIds() {
		return unitIds;
	}

	public StatisDissatiDateVO getStatisDissatiDateVO() {
		return statisDissatiDateVO;
	}

	public StatisDissaQuestionDateVO getStatisDissaQuestionDateVO() {
		return statisDissaQuestionDateVO;
	}

	public void setStatisDissaQuestionDateVO(StatisDissaQuestionDateVO statisDissaQuestionDateVO) {
		this.statisDissaQuestionDateVO = statisDissaQuestionDateVO;
	}

	public void setStatisDissatiDateVO(StatisDissatiDateVO statisDissatiDateVO) {
		this.statisDissatiDateVO = statisDissatiDateVO;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

	public String getExcelFileName() {
		return excelFileName;
	}

	public void setExcelFileName(String excelFileName) {
		this.excelFileName = excelFileName;
	}

	public void setUnitIds(String[] unitIds) {
		this.unitIds = unitIds;
	}

	public String getSearchTimeStart() {
		return searchTimeStart;
	}

	public void setSearchTimeStart(String searchTimeStart) {
		this.searchTimeStart = searchTimeStart;
	}

	public String getSearchTimeEnd() {
		return searchTimeEnd;
	}

	public void setSearchTimeEnd(String searchTimeEnd) {
		this.searchTimeEnd = searchTimeEnd;
	}

	public StatisDissaServiceDateVO getStatisDissaServiceDateVO() {
		return statisDissaServiceDateVO;
	}

	public void setStatisDissaServiceDateVO(StatisDissaServiceDateVO statisDissaServiceDateVO) {
		this.statisDissaServiceDateVO = statisDissaServiceDateVO;
	}

	public List<ServiceGradeDTO> getServiceGradeDTOList() {
		return serviceGradeDTOList;
	}

	public ClientAttentionServiceVO getClientAttentionServiceVO() {
		return clientAttentionServiceVO;
	}

	public void setClientAttentionServiceVO(ClientAttentionServiceVO clientAttentionServiceVO) {
		this.clientAttentionServiceVO = clientAttentionServiceVO;
	}

	public void setServiceGradeDTOList(List<ServiceGradeDTO> serviceGradeDTOList) {
		this.serviceGradeDTOList = serviceGradeDTOList;
	}

	public HttpServletRequest getRequest() {
		return request;
	}

	public void setRequest(HttpServletRequest request) {
		this.request = request;
	}

	public HttpServletResponse getResponse() {
		return response;
	}

	public void setResponse(HttpServletResponse response) {
		this.response = response;
	}

	public StatisticsService getStatisticsService() {
		return statisticsService;
	}

	public void setStatisticsService(StatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}

	@Override
	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
	}

	@Override
	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
	}

	public StatisticsDissatisfiedDayDataVO getStatisticsDissatisfiedDayDataVO() {
		return statisticsDissatisfiedDayDataVO;
	}

	public void setStatisticsDissatisfiedDayDataVO(StatisticsDissatisfiedDayDataVO statisticsDissatisfiedDayDataVO) {
		this.statisticsDissatisfiedDayDataVO = statisticsDissatisfiedDayDataVO;
	}

	public StatisticsDissatisfiedDateCountVO getStatisticsDissatisfiedDateCountVO() {
		return statisticsDissatisfiedDateCountVO;
	}

	public ReturnVisitVO getReturnVisitVO() {
		return returnVisitVO;
	}

	public void setReturnVisitVO(ReturnVisitVO returnVisitVO) {
		this.returnVisitVO = returnVisitVO;
	}

	public void setStatisticsDissatisfiedDateCountVO(
			StatisticsDissatisfiedDateCountVO statisticsDissatisfiedDateCountVO) {
		this.statisticsDissatisfiedDateCountVO = statisticsDissatisfiedDateCountVO;
	}

	public DissatisfiedVO getDissatisfiedVO() {
		return dissatisfiedVO;
	}

	public void setDissatisfiedVO(DissatisfiedVO dissatisfiedVO) {
		this.dissatisfiedVO = dissatisfiedVO;
	}

	public MonthDayMountVO getMonthDayMountVO() {
		return monthDayMountVO;
	}

	public void setMonthDayMountVO(MonthDayMountVO monthDayMountVO) {
		this.monthDayMountVO = monthDayMountVO;
	}

	public String getStartTime() {
		return startTime;
	}

	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}

	public String getEndTime() {
		return endTime;
	}

	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}

}
