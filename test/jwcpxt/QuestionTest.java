package jwcpxt;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pphgzs.domain.DO.jwcpxt_option;
import com.pphgzs.domain.DO.jwcpxt_question;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DTO.OptionDTO;
import com.pphgzs.domain.DTO.ServiceDefinitionDTO;
import com.pphgzs.domain.VO.QuestionVO;
import com.pphgzs.service.QuestionService;
import com.pphgzs.service.ServiceService;
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
		option.setOption_describe("选项5");
		option.setOption_question("ef2a25bc-fd97-4dad-9e5a-715809198650");
		option.setOption_grade(2);
		System.out.println(questionService.save_option(option));
	}

	/**
	 * 选项列表
	 */
	@Test
	public void list_optionDTOTest() {
		jwcpxt_question question = new jwcpxt_question();
		question.setJwcpxt_question_id("ef2a25bc-fd97-4dad-9e5a-715809198650");
		List<OptionDTO> listOptionDTO = new ArrayList<>();
		listOptionDTO = questionService.list_optionDTO(question);
	}

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
	 * 
	 */
	@Test
	public void delete_question() {
		jwcpxt_question question = new jwcpxt_question();
		question.setJwcpxt_question_id("d56e37b7-c3ea-4ead-9561-11dfa10a8d1f");
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
		question.setQuestion_describe("我的追问选择题3");
		question.setQuestion_type("4");
		question.setQuestion_service_definition("a64f8153-1a75-47b3-95bb-f40e35367e71");
		questionService.save_question(question);
	}

	/**
	 * VO 测试
	 */
	@Test
	public void get_questionVOTest() {
		QuestionVO questionVO = new QuestionVO();
		ServiceDefinitionDTO serviceDefinitionDTO = new ServiceDefinitionDTO(null, null);
		jwcpxt_service_definition serviceDefinition = new jwcpxt_service_definition();
		serviceDefinition.setJwcpxt_service_definition_id("d0d75dfd-32b4-4fdd-b56e-d6af20107625");
		serviceDefinitionDTO.setServiceDefinition(serviceDefinition);
		questionVO.setServiceDefinitionDTO(serviceDefinitionDTO);
		questionVO = questionService.get_questionVO(questionVO);
		System.out.println("questionVO:" + questionVO);
	}

	/**
	 * 
	 */

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public ServiceService getServiceService() {
		return serviceService;
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
