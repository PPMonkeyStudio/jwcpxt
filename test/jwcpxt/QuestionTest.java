package jwcpxt;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

//import com.pphgzs.domain.DO.jwcpxt_feedback_rectification;
import com.pphgzs.domain.DO.jwcpxt_option;
import com.pphgzs.domain.DO.jwcpxt_question;
import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DO.jwcpxt_user;
import com.pphgzs.domain.DTO.ClientInstanceDTO;
import com.pphgzs.domain.VO.ClientInfoVO;
import com.pphgzs.domain.VO.StatisDissatiDateVO;
//import com.pphgzs.domain.DTO.ServiceDefinitionDTO;
//import com.pphgzs.domain.VO.DissatisfiedFeedbackVO;
//import com.pphgzs.domain.VO.QuestionVO;
import com.pphgzs.service.DissatisfiedFeedbackService;
import com.pphgzs.service.QuestionService;
import com.pphgzs.service.ServiceService;
import com.pphgzs.service.StatisticsService;
import com.pphgzs.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class QuestionTest {
	@Resource
	private UserService userService;
	@Resource
	private ServiceService serviceService;
	@Resource
	private QuestionService questionService;
	@Resource
	private DissatisfiedFeedbackService dissatisfiedFeedbackService;
	@Resource
	private StatisticsService statisticsService;

	/**
	 * 
	 */
	@Test
	public void get_statisDissatiDateVOTest() {
		StatisDissatiDateVO statisDissatiDateVO = new StatisDissatiDateVO();
		statisDissatiDateVO.setStartTime("2018-07-10");
		statisDissatiDateVO.setEndTime("2018-07-21");
//		statisDissatiDateVO.setTimeType("3");
		System.out.println(statisticsService.get_statisDissatiDateVO(statisDissatiDateVO));
	}

	/**
	 * 
	 */
	@Test
	public void get_clientVO() {
		ClientInfoVO clientInfoVO = new ClientInfoVO();
		System.out.println(serviceService.get_clientInfoVO_byUserId(clientInfoVO));
	}

	/**
	 * 
	 */
	@Test
	public void get_fdfd() {
		jwcpxt_service_client ji = new jwcpxt_service_client();
		ji.setJwcpxt_service_client_id("1");
		System.out.println(serviceService.get_serviceClientDo_byId(ji));
	}

	/**
	 * 测试当事人
	 */
	@Test
	public void get_notServiceClient_byServiceClientId() {
		ClientInstanceDTO clientInstanceDTO = new ClientInstanceDTO();
		jwcpxt_user user = new jwcpxt_user();
		user.setJwcpxt_user_id("1");
		clientInstanceDTO = serviceService.get_notServiceClient_byServiceClientId(user);
		System.out.println(clientInstanceDTO);
	}

	/*
	 * @Test public void get_feedbackRectification_byRectificationId() {
	 * jwcpxt_feedback_rectification feedBackRectification = new
	 * jwcpxt_feedback_rectification();
	 * feedBackRectification.setJwcpxt_feedback_rectification_id("1");
	 * dissatisfiedFeedbackService.get_feedbackRectification_byRectificationId(
	 * feedBackRectification); }
	 * 
	 * @Test public void get_dissatisfiedFeedbackVOTest() { DissatisfiedFeedbackVO
	 * dissatisfiedFeedbackVO = new DissatisfiedFeedbackVO();
	 * dissatisfiedFeedbackService.get_dissatisfiedFeedbackVO(dissatisfiedFeedbackVO
	 * ); }
	 */

	/**
	 * 
	 */
	@Test
	public void list_questionDTO_byServiceDefinitionId() {
		jwcpxt_service_definition serviceDefinition = new jwcpxt_service_definition();
		serviceDefinition.setJwcpxt_service_definition_id("d0d75dfd-32b4-4fdd-b56e-d6af20107625");
		System.out.println(questionService.list_questionDTO_byServiceDefinition(serviceDefinition));
		System.out.println(questionService.list_questionDTO_byServiceDefinition(serviceDefinition).size());
	}

	/**
	 * 测试根据问题Id获取问题的其他内容
	 */
	@Test
	public void get_questionDTO_byQuestionId() {
		jwcpxt_question question = new jwcpxt_question();
		question.setJwcpxt_question_id("ef2a25bc-fd97-4dad-9e5a-715809198650");
		System.out.println("fd:" + questionService.get_questionDTO_byQuestionId(question));
	}

	/**
	 * 删除选项测试
	 */
	@Test
	public void delete_option() {
		jwcpxt_option option = new jwcpxt_option();
		option.setJwcpxt_option_id("a64f8153-1a75-47b3-95bb-f40e35367e71");
		System.out.println(questionService.delete_option(option));
	}

	/**
	 * 删除追问选择题
	 * 
	 */
	@Test
	public void delete_zhuiWen() {
		jwcpxt_question question = new jwcpxt_question();
		question.setJwcpxt_question_id("814f9663-6f8d-4455-94ca-33a809d087c0");
		System.out.println(questionService.delete_questionInquiries(question));
	}

	/**
	 * 上移
	 */
	@Test
	public void moveup_option() {
		jwcpxt_option option = new jwcpxt_option();
		option.setJwcpxt_option_id("e61e2cbd-32e2-4719-9f8f-f58429e6e15c");
		System.out.println(questionService.move_option(option, "1"));
	}

	/**
	 * 下移
	 */
	@Test
	public void movedown_option() {
		jwcpxt_option option = new jwcpxt_option();
		option.setJwcpxt_option_id("e61e2cbd-32e2-4719-9f8f-f58429e6e15c");
		System.out.println(questionService.move_option(option, "2"));
	}

	/**
	 * 添加选项
	 */
	@Test
	public void save_optionTest() {
		jwcpxt_option option = new jwcpxt_option();
		option.setOption_describe("追问选项2");
		option.setOption_question("be999b67-ce64-45b6-8c46-fa2a9d2e9e1a");
		option.setOption_grade(2);
		System.out.println(questionService.save_option(option));
	}

	/**
	 * 选项列表
	 */
	/*
	 * @Test public void list_optionDTOTest() { jwcpxt_question question = new
	 * jwcpxt_question();
	 * question.setJwcpxt_question_id("ef2a25bc-fd97-4dad-9e5a-715809198650");
	 * List<OptionDTO> listOptionDTO = new ArrayList<>(); listOptionDTO =
	 * questionService.list_optionDTO(question); }
	 */

	/**
	 * 下移问题测试
	 */
	@Test
	public void movedown_question() {
		jwcpxt_question question = new jwcpxt_question();
		question.setJwcpxt_question_id("be999b67-ce64-45b6-8c46-fa2a9d2e9e1a");
		System.out.println(questionService.move_question_sort(question, "2"));
	}

	/**
	 * 上移问题测试
	 */
	@Test
	public void moveup_question() {
		jwcpxt_question question = new jwcpxt_question();
		question.setJwcpxt_question_id("be999b67-ce64-45b6-8c46-fa2a9d2e9e1a");
		System.out.println(questionService.move_question_sort(question, "1"));
	}

	/**
	 * 删除问题
	 */
	@Test
	public void delete_question() {
		jwcpxt_question question = new jwcpxt_question();
		question.setJwcpxt_question_id("ef2a25bc-fd97-4dad-9e5a-715809198650");
		System.out.println(questionService.delete_question(question));
	}

	/**
	 * 更新问题
	 */
	@Test
	public void update_questionTest() {
		jwcpxt_question question = new jwcpxt_question();
		question.setQuestion_type("2");
		question.setQuestion_describe("我的问题3");
		question.setJwcpxt_question_id("eb3009af-be16-4a10-9b99-f555507da3f0");
		System.out.println(questionService.update_question(question));
	}

	/**
	 * 测试保存问题
	 */
	@Test
	public void save_questionTest() {
		jwcpxt_question question = new jwcpxt_question();
		question.setQuestion_describe("我的追问开放题1");
		question.setQuestion_type("3");
		question.setQuestion_service_definition("40391b04-333a-4d71-8d87-c45fe43b4b45");
		questionService.save_question(question);
	}

	/**
	 * 
	 */

	public UserService getUserService() {
		return userService;
	}

	public DissatisfiedFeedbackService getDissatisfiedFeedbackService() {
		return dissatisfiedFeedbackService;
	}

	public void setDissatisfiedFeedbackService(DissatisfiedFeedbackService dissatisfiedFeedbackService) {
		this.dissatisfiedFeedbackService = dissatisfiedFeedbackService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ServiceService getServiceService() {
		return serviceService;
	}

	public StatisticsService getStatisticsService() {
		return statisticsService;
	}

	public void setStatisticsService(StatisticsService statisticsService) {
		this.statisticsService = statisticsService;
	}

	public void setServiceService(ServiceService serviceService) {
		this.serviceService = serviceService;
	}

	public QuestionService getQuestionService() {
		return questionService;
	}

	public void setQuestionService(QuestionService questionService) {
		this.questionService = questionService;
	}

}
