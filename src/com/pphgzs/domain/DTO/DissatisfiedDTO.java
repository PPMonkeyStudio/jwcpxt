package com.pphgzs.domain.DTO;

/**
 * 
 * @author JXX 群众最不满意的业务
 */
public class DissatisfiedDTO {
	private String serviceName;
	private int serviceCount;

	public String getServiceName() {
		return serviceName;
	}

	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}

	@Override
	public String toString() {
		return "AttentionDTO [serviceName=" + serviceName + ", serviceCount=" + serviceCount + "]";
	}

	public DissatisfiedDTO(String serviceName, int serviceCount) {
		super();
		this.serviceName = serviceName;
		this.serviceCount = serviceCount;
	}

	public int getServiceCount() {
		return serviceCount;
	}

	public void setServiceCount(int serviceCount) {
		this.serviceCount = serviceCount;
	}

	public DissatisfiedDTO() {
		super();
	}

}
