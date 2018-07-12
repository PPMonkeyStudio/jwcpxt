package jwcpxt;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pphgzs.dao.DissatisfiedFeedbackDao;
import com.pphgzs.domain.VO.DissatisfiedQuestionVO;
import com.pphgzs.service.DissatisfiedFeedbackService;
import com.pphgzs.service.ServiceService;
import com.pphgzs.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class MyTest {
	@Resource
	private UserService userService;
	@Resource
	private ServiceService serviceService;
	@Resource
	private DissatisfiedFeedbackDao dissatisfiedFeedbackDao;
	@Resource
	private DissatisfiedFeedbackService dissatisfiedFeedbackService;

	public DissatisfiedFeedbackService getDissatisfiedFeedbackService() {
		return dissatisfiedFeedbackService;
	}

	public void setDissatisfiedFeedbackService(DissatisfiedFeedbackService dissatisfiedFeedbackService) {
		this.dissatisfiedFeedbackService = dissatisfiedFeedbackService;
	}

	public DissatisfiedFeedbackDao getDissatisfiedFeedbackDao() {
		return dissatisfiedFeedbackDao;
	}

	public void setDissatisfiedFeedbackDao(DissatisfiedFeedbackDao dissatisfiedFeedbackDao) {
		this.dissatisfiedFeedbackDao = dissatisfiedFeedbackDao;
	}

	public ServiceService getServiceService() {
		return serviceService;
	}

	public void setServiceService(ServiceService serviceService) {
		this.serviceService = serviceService;
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@Test
	public void ttt() {

		DissatisfiedQuestionVO dissatisfiedQuestionVO = new DissatisfiedQuestionVO();
		dissatisfiedQuestionVO.setScreenState("1");
		System.out.println(dissatisfiedFeedbackService.get_dissatisfiedQuestionVO(dissatisfiedQuestionVO));
	}

	@Test
	public void tttt() {

	}

	@Test
	public void tttdf() {
	}

}
