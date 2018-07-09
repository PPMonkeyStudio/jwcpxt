package jwcpxt;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.pphgzs.service.ServiceService;
import com.pphgzs.service.UserService;
import com.pphgzs.util.TimeUtil;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:applicationContext*.xml" })
public class MyTest {
	@Resource
	private UserService userService;
	@Resource
	private ServiceService serviceService;

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

	}

	@Test
	public void tttt() {
		System.out.println(serviceService.list_serviceInstance_byNoJudgeAndRandomAndNumAndServiceDefinitionIDAndDate(3,
				"d0d75dfd-32b4-4fdd-b56e-d6af20107625", TimeUtil.getStringDay()));
	}

	@Test
	public void tttdf() {
		serviceService.distribution_serviceInstance_auto();
	}

}
