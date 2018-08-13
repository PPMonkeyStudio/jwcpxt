package jwcpxt;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pphgzs.dao.DissatisfiedFeedbackDao;
import com.pphgzs.domain.DO.jwcpxt_service_client;
import com.pphgzs.domain.VO.DissatisfiedQuestionVO;
import com.pphgzs.service.DissatisfiedFeedbackService;
import com.pphgzs.service.ServiceService;
import com.pphgzs.service.UnitService;
import com.pphgzs.service.UserService;
import com.pphgzs.util.TimeUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class MyTest {
	@Resource
	private UserService userService;
	@Resource
	private UnitService unitService;
	@Resource
	private ServiceService serviceService;
	@Resource
	private DissatisfiedFeedbackDao dissatisfiedFeedbackDao;
	@Resource
	private DissatisfiedFeedbackService dissatisfiedFeedbackService;

	public UnitService getUnitService() {
		return unitService;
	}

	public void setUnitService(UnitService unitService) {
		this.unitService = unitService;
	}

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
	public void ttttt1() {

		DissatisfiedQuestionVO dissatisfiedQuestionVO = new DissatisfiedQuestionVO();
		dissatisfiedQuestionVO.setScreenState("1");
		System.out.println(dissatisfiedFeedbackService.get_dissatisfiedQuestionVO(dissatisfiedQuestionVO));
	}

	@Test
	public void ttttt2() {
		String date = TimeUtil.getStringDay_before7();
		date = date.replaceAll("-", "");
		System.out.println(date);
	}

	@Test
	public void ttttt3() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");// 24小时制
		long time1 = 0;
		long time2 = 0;
		try {
			time1 = simpleDateFormat.parse("2018-06-28").getTime();
			time2 = simpleDateFormat.parse("2018-07-16").getTime();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		for (long time = time1; time <= time2; time = time + 86400000) {
			System.out.println(simpleDateFormat.format(new Date(time)));
		}
	}

	@Test
	public void tese3() {
		jwcpxt_service_client client = new jwcpxt_service_client();
		client.setJwcpxt_service_client_id("aac515ec-0a46-4f65-8580-7c07b35bda43");
		System.out.println(serviceService.get_AllInformation_ByClientId(client));
	}

}
