package com.pphgzs.dao.impl;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.pphgzs.dao.StatisticsDao;
import com.pphgzs.domain.DO.jwcpxt_answer_choice;
import com.pphgzs.domain.DO.jwcpxt_answer_open;
import com.pphgzs.domain.DO.jwcpxt_option;
import com.pphgzs.domain.DO.jwcpxt_question;
import com.pphgzs.domain.DO.jwcpxt_service_definition;
import com.pphgzs.domain.DTO.DeductMarkFirstInfoDTO;
import com.pphgzs.domain.DTO.QuestionOptionAnswerDTO;
import com.pphgzs.domain.DTO.ReturnVisitDTO;
import com.pphgzs.domain.DTO.ServiceGradeDTO;
import com.pphgzs.domain.VO.DeductMarkInfoVO;
import com.pphgzs.domain.VO.MonthDayMountVO;
import com.pphgzs.domain.VO.ReturnVisitVO;
import com.pphgzs.domain.VO.StatisDissaQuestionDateVO;
import com.pphgzs.domain.VO.StatisDissaServiceDateVO;
import com.pphgzs.domain.VO.StatisDissatiDateVO;

public class StatisticsDaoImpl implements StatisticsDao {
	private SessionFactory sessionFactory;

	public Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	/**
	 * 获取分数大于0 的某问题选项
	 */
	@Override
	public List<jwcpxt_option> get_listOption_byQuestionId(String jwcpxt_question_id) {
		List<jwcpxt_option> listOption = new ArrayList<>();
		Session session = getSession();
		String hql = "from jwcpxt_option where option_question = :questionId and option_grade > 0";
		Query query = session.createQuery(hql);
		query.setParameter("questionId", jwcpxt_question_id);
		listOption = query.list();
		return listOption;
	}

	/**
	 * 
	 */
	@Override
	public jwcpxt_option get_answerChoice_byClietnAndQuestion(String jwcpxt_question_id,
			String jwcpxt_service_client_id) {
		jwcpxt_option secondOption = new jwcpxt_option();
		Session session = getSession();
		String hql = "select secondOption from jwcpxt_answer_choice answerChoice,jwcpxt_option secondOption where answerChoice.answer_choice_option = secondOption.jwcpxt_option_id"//
				+ " and answerChoice.answer_choice_client = :choiceClient and answer_choice_question = :choiceQuestion";
		Query query = session.createQuery(hql);
		query.setParameter("choiceClient", jwcpxt_service_client_id);
		query.setParameter("choiceQuestion", jwcpxt_question_id);
		secondOption = (jwcpxt_option) query.uniqueResult();
		return secondOption;
	}

	/**
	 * 
	 */
	@Override
	public jwcpxt_answer_open get_answerOpen_byClientAndQuestion(String jwcpxt_question_id,
			String jwcpxt_service_client_id) {
		jwcpxt_answer_open answerOpen = new jwcpxt_answer_open();
		Session session = getSession();
		String hql = "from jwcpxt_answer_open where answer_open_client = :clientId and answer_open_question = :questionId";
		Query query = session.createQuery(hql);
		query.setParameter("clientId", jwcpxt_service_client_id);
		query.setParameter("questionId", jwcpxt_question_id);
		answerOpen = (jwcpxt_answer_open) query.uniqueResult();
		return answerOpen;
	}

	/**
	 * 
	 */
	@Override
	public List<jwcpxt_question> get_listQuestion_byServiceDefinitionId(String optionId) {
		List<jwcpxt_question> listQuestion = new ArrayList<>();
		Session session = getSession();
		String hql = "from jwcpxt_question where question_service_definition = :optionId";
		Query query = session.createQuery(hql);
		query.setParameter("optionId", optionId);
		listQuestion = query.list();
		return listQuestion;
	}

	/**
	 * 
	 */
	@Override
	public List<DeductMarkFirstInfoDTO> get_DeductMarkFirstInfo(DeductMarkInfoVO deductMarkInfoVO) {
		List<DeductMarkFirstInfoDTO> listDeductMarkFirstInfoDTO = new ArrayList<>();
		Session session = getSession();
		String hql = "select"//
				+ " new com.pphgzs.domain.DTO.DeductMarkFirstInfoDTO(serviceClient,serviceDefinition,unit,_option,question)"//
				+ " from"//
				+ " jwcpxt_answer_choice answerChoice,"//
				+ " jwcpxt_option _option,"//
				+ " jwcpxt_service_client serviceClient,"//
				+ " jwcpxt_service_instance serviceInstance,"//
				+ " jwcpxt_unit unit,"//
				+ " jwcpxt_service_definition serviceDefinition,"//
				+ " jwcpxt_question question"//
				+ " WHERE"//
				+ " answerChoice.answer_choice_option = _option.jwcpxt_option_id"//
				+ " AND answerChoice.answer_choice_client = serviceClient.jwcpxt_service_client_id"//
				+ " AND serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id"//
				+ " AND serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id"//
				+ " AND serviceInstance.service_instance_service_definition = serviceDefinition.jwcpxt_service_definition_id"//
				+ " AND _option.option_question = question.jwcpxt_question_id"//
				+ " AND _option.option_grade > 0"//
				+ " AND serviceClient.service_client_gmt_modified >= :screenTimeStart"//
				+ " AND serviceClient.service_client_gmt_modified <= :screenTimeEnd"//
				+ " AND (unit.jwcpxt_unit_id LIKE :unitId or unit.unit_father like :unitId)"//
				+ " AND serviceInstance.service_instance_judge like :screenJudge"//
				+ " AND serviceDefinition.jwcpxt_service_definition_id LIKE :screenService"//
				+ " order by serviceDefinition,unit,serviceClient";//
		Query query = session.createQuery(hql);
		if (deductMarkInfoVO.getScreenTimeStart().equals("")) {
			query.setParameter("screenTimeStart", "0000-00-00 00:00:00");
		} else {
			query.setParameter("screenTimeStart", deductMarkInfoVO.getScreenTimeStart() + " 00:00:00");
		}
		if (deductMarkInfoVO.getScreenTimeEnd().equals("")) {
			query.setParameter("screenTimeEnd", "9999-99-99 23:59:59");
		} else {
			query.setParameter("screenTimeEnd", deductMarkInfoVO.getScreenTimeEnd() + " 23:59:59");
		}
		if (deductMarkInfoVO.getScreenUnit().equals("")) {
			query.setParameter("unitId", "%");
		} else {
			query.setParameter("unitId", deductMarkInfoVO.getScreenUnit());
		}
		if (deductMarkInfoVO.getScreenJudge().equals("")) {
			query.setParameter("screenJudge", "%");
		} else {
			query.setParameter("screenJudge", deductMarkInfoVO.getScreenJudge());
		}
		if (deductMarkInfoVO.getScreenDefinitionId().equals("")) {
			query.setParameter("screenService", "%");
		} else {
			query.setParameter("screenService", deductMarkInfoVO.getScreenDefinitionId());
		}
		listDeductMarkFirstInfoDTO = query.list();
		return listDeductMarkFirstInfoDTO;
	}

	/**
	 * 
	 */
	@Override
	public int get_dataMonthDayMount(MonthDayMountVO monthDayMountVO, int i) {
		Session session = getSession();
		String hql = "";
		if (i == 0 || i == 1 || i == 3 || i == 5 || i == 6) {
			hql = hql + "select"//
					+ " count(*)"//
					+ " from"//
					+ " jwcpxt_service_client serviceClient,"//
					+ " jwcpxt_service_instance serviceInstance,"//
					+ " jwcpxt_service_definition serviceDefinition"//
					+ " where"//
					+ " serviceClient.SERVICE_CLIENT_SERVICE_INSTANCE = serviceInstance.JWCPXT_SERVICE_INSTANCE_ID"
					+ " and serviceInstance.SERVICE_INSTANCE_SERVICE_DEFINITION = serviceDefinition.JWCPXT_SERVICE_DEFINITION_ID";
			if (i != 5 && i != 6) {
				hql = hql + " and serviceDefinition.JWCPXT_SERVICE_DEFINITION_ID != 'revisit'";//
			}
			if (i == 5 || i == 6) {
				hql = hql + " and serviceDefinition.JWCPXT_SERVICE_DEFINITION_ID = 'revisit'";//
			}
			hql = hql + " and serviceClient.service_client_gmt_modified >= :startTime"//
					+ " and serviceClient.service_client_gmt_modified <= :endTime"//
					+ " and serviceInstance.service_instance_judge like :userId";
			if (i == 3) {
				hql = hql
						+ " AND serviceDefinition.JWCPXT_SERVICE_DEFINITION_ID != '805bba8d-34f0-449c-bc8c-e868938e0f05'";
			}
			if (i == 1 || i == 3 || i == 6) {
				hql = hql + " AND serviceClient.service_client_visit = '1'";
			}
		}
		if (i == 2 || i == 4 || i == 7) {
			hql = hql + "select"//
					+ " count(*)"//
					+ " from"//
					+ " ("//
					+ " select"//
					+ " serviceClient.jwcpxt_service_client_id"//
					+ " from"//
					+ " jwcpxt_service_client serviceClient,"//
					+ " jwcpxt_service_instance serviceInstance,"//
					+ " jwcpxt_service_definition serviceDefinition"//
					+ " where"//
					+ " serviceClient.SERVICE_CLIENT_SERVICE_INSTANCE = serviceInstance.JWCPXT_SERVICE_INSTANCE_ID"
					+ " and serviceInstance.SERVICE_INSTANCE_SERVICE_DEFINITION = serviceDefinition.JWCPXT_SERVICE_DEFINITION_ID";//
			if (i != 7) {
				hql = hql + " and serviceDefinition.JWCPXT_SERVICE_DEFINITION_ID != 'revisit'";
			} else {
				hql = hql + " and serviceDefinition.JWCPXT_SERVICE_DEFINITION_ID = 'revisit'";
			}
			hql = hql + " and serviceClient.service_client_gmt_modified >= :startTime"//
					+ " and serviceClient.service_client_gmt_modified <= :endTime"
					+ " and serviceInstance.service_instance_judge like :userId"//
					+ " and serviceClient.service_client_visit='1'";//
			if (i == 2) {
				hql = hql
						+ " AND serviceDefinition.JWCPXT_SERVICE_DEFINITION_ID != '805bba8d-34f0-449c-bc8c-e868938e0f05'";
			}
			if (i == 4) {
				hql = hql
						+ " AND serviceDefinition.JWCPXT_SERVICE_DEFINITION_ID = '805bba8d-34f0-449c-bc8c-e868938e0f05'";

			}
			hql = hql + " ) t1"//
					+ " left join"//
					+ " ("//
					+ " select"//
					+ " client.jwcpxt_service_client_id"//
					+ " from"//
					+ " jwcpxt_answer_choice choice,"//
					+ " jwcpxt_option _option,"//
					+ " jwcpxt_service_client client"//
					+ " where"//
					+ " choice.answer_choice_client = client.jwcpxt_service_client_id"//
					+ " AND choice.answer_choice_option = _option.jwcpxt_option_id"//
					+ " AND (";//
			if (i == 2) {
				hql = hql + " _option.option_describe LIKE '不满意'"//
						+ " OR _option.option_describe LIKE '不太满意'";//
			}
			if (i == 4 || i == 7) {
				hql = hql + " _option.option_describe LIKE '满意'";//
			}
			hql = hql + " )"//
					+ " GROUP BY"//
					+ " client.JWCPXT_SERVICE_CLIENT_ID"//
					+ " ) t2 ON t1.JWCPXT_SERVICE_CLIENT_ID = t2.jwcpxt_service_client_id"//
					+ " WHERE"//
					+ " t2.jwcpxt_service_client_id IS NOT NULL";
		}
		Query query = session.createSQLQuery(hql);
		if ("".equals(monthDayMountVO.getUserId())) {
			query.setParameter("userId", "%");
		} else {
			query.setParameter("userId", monthDayMountVO.getUserId());
		}
		if ("".equals(monthDayMountVO.getStartTime())) {
			query.setParameter("startTime", "0000-00-00 00:00:00");
		} else {
			query.setParameter("startTime", monthDayMountVO.getStartTime() + " 00:00:00");
		}
		if ("".equals(monthDayMountVO.getEndTime())) {
			query.setParameter("endTime", "9999-99-99 23:59:59");
		} else {
			query.setParameter("endTime", monthDayMountVO.getEndTime() + " 23:59:59");
		}
		try {
			int count = ((Number) query.uniqueResult()).intValue();
			return count;
		} catch (ClassCastException e) {
			return 0;
		} finally {
			session.clear();
		}
	}

	/**
	 * 获取相对应的数量
	 */
	@Override
	public int getServiceOptionCount(String serviceName, String questionName, String optionName) {
		Session session = getSession();
		String hql = "select count(*)"//
				+ " from"//
				+ " jwcpxt_answer_choice choice,"//
				+ " jwcpxt_option _option,"//
				+ " jwcpxt_question question,"//
				+ " jwcpxt_service_definition definition"//
				+ " where"//
				+ " choice.answer_choice_option = _option.jwcpxt_option_id"//
				+ " and _option.option_question = question.jwcpxt_question_id"//
				+ " and question.question_service_definition = definition.jwcpxt_service_definition_id"//
				+ " and _option.option_describe = :optionName"//
				+ " and question.question_describe = :questionName"//
				+ " and definition.service_definition_describe = :serviceName";
		Query query = session.createQuery(hql);
		query.setParameter("serviceName", serviceName);
		query.setParameter("questionName", questionName);
		query.setParameter("optionName", optionName);
		try {
			int count = ((Number) query.uniqueResult()).intValue();
			return count;
		} catch (ClassCastException e) {
			return 0;
		} finally {
			session.clear();
		}
	}

	/**
	 * 
	 */
	@Override
	public List<ReturnVisitDTO> getUserCountVO(ReturnVisitVO returnVisitVO) {
		Session session = getSession();
		String hql = "select new com.pphgzs.domain.DTO.ReturnVisitDTO(serviceClient.service_client_visit,count(*))"//
				+ " from"//
				+ " jwcpxt_service_client serviceClient,"//
				+ " jwcpxt_service_instance serviceInstance"//
				+ " where"//
				+ " serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id"//
				+ " and serviceInstance.service_instance_date >= :startHTime"
				+ " and serviceInstance.service_instance_date <= :endHTime"//
				+ " and serviceClient.service_client_gmt_modified >= :beforeDate"//
				+ " and serviceClient.service_client_gmt_modified <= :afterDate"//
				+ " and serviceInstance.service_instance_judge like :userId"//
				+ " group by serviceClient.service_client_visit";
		Query query = session.createQuery(hql);
		// 昨天和今天
		if ("".equals(returnVisitVO.getStartTime())) {
			query.setParameter("startHTime", "0000-00-00");
		} else {
			query.setParameter("startHTime", returnVisitVO.getStartTime());
		}
		if ("".equals(returnVisitVO.getEndTime())) {
			query.setParameter("endHTime", "9999-99-99");
		} else {
			query.setParameter("endHTime", returnVisitVO.getEndTime());
		}
		if ("".equals(returnVisitVO.getStartHTime())) {
			query.setParameter("beforeDate", "0000-00-00");
		} else {
			query.setParameter("beforeDate", returnVisitVO.getStartHTime() + " 00:00:00");
		}
		if ("".equals(returnVisitVO.getEndHTime())) {
			query.setParameter("afterDate", "9999-99-99");
		} else {
			query.setParameter("afterDate", returnVisitVO.getEndHTime() + " 23:59:59");
		}
		if ("".equals(returnVisitVO.getUserId())) {
			query.setParameter("userId", "%%");
		} else {
			query.setParameter("userId", "%" + returnVisitVO.getUserId() + "%");
		}
		List<ReturnVisitDTO> listReturnVisitDTO = new ArrayList<>();
		listReturnVisitDTO = query.list();
		return listReturnVisitDTO;
	}

	/**
	 * 总数量
	 */
	@Override
	public int get_totalCountService_byTime(StatisDissaServiceDateVO statisDissaServiceDateVO, String startTime,
			String endTime, jwcpxt_service_definition serviceDefinition) {
		Session session = getSession();
		String hql = "select count(*)"//
				+ " from"//
				+ " jwcpxt_service_definition serviceDefinition,"//
				+ " jwcpxt_unit unit,"//
				+ " jwcpxt_answer_choice answerChoice,"//
				+ " jwcpxt_service_client serviceClient,"//
				+ " jwcpxt_service_instance serviceInstance"//
				+ " where"//
				+ " answerChoice.answer_choice_client = serviceClient.jwcpxt_service_client_id"//
				+ " and serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id"//
				+ " and serviceInstance.service_instance_service_definition = serviceDefinition.jwcpxt_service_definition_id"//
				+ " and serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id"//
				+ " and unit.jwcpxt_unit_id like :unitId"//
				+ "	and serviceDefinition.jwcpxt_service_definition_id like :serviceDefinitionId"//
				+ " and serviceInstance.service_instance_date >= :startTime "//
				+ " and serviceInstance.service_instance_date <= :endTime ";
		Query query = session.createQuery(hql);
		if (statisDissaServiceDateVO.getScreenUnit().equals("")) {
			query.setParameter("unitId", "%%");
		} else {
			query.setParameter("unitId", statisDissaServiceDateVO.getScreenUnit());
		}
		query.setParameter("startTime", startTime + " 00:00:00");
		query.setParameter("endTime", endTime + " 00:00:00");
		query.setParameter("serviceDefinitionId", serviceDefinition.getJwcpxt_service_definition_id());
		try {
			int count = ((Number) query.uniqueResult()).intValue();
			return count;
		} catch (ClassCastException e) {
			return 0;
		} finally {
			session.clear();
		}
	}

	/**
	 * 获取该单位所有的业务
	 */
	@Override
	public List<jwcpxt_service_definition> get_statisService_byTime(StatisDissaServiceDateVO statisDissaServiceDateVO) {
		Session session = getSession();
		String hql = "select distinct(serviceDefinition)"//
				+ " from"//
				+ " jwcpxt_service_definition serviceDefinition,"//
				+ " jwcpxt_unit unit,"//
				+ " jwcpxt_unit_service unitService"//
				+ " where unitService.unit_id = unit.jwcpxt_unit_id"//
				+ " and unitService.service_definition_id = serviceDefinition.jwcpxt_service_definition_id"
				+ " and unit.jwcpxt_unit_id like :unitId";//
		Query query = session.createQuery(hql);
		if (statisDissaServiceDateVO.getScreenUnit().equals("")) {
			query.setParameter("unitId", "%%");
		} else {
			query.setParameter("unitId", statisDissaServiceDateVO.getScreenUnit());
		}
		List<jwcpxt_service_definition> list = new ArrayList<>();
		list = query.list();
		session.clear();
		return list;
	}

	/**
	 * 根据VO获取改业务的所有不满意问题数量
	 */
	@Override
	public int get_countStatisDateDTO(StatisDissaQuestionDateVO statisDissaQuestionDateVO, String startTime,
			String endTime, QuestionOptionAnswerDTO questionOptionAnswerDTO) {
		Session session = getSession();
		String hql = "select count(*) from";//
		if ("1".equals(questionOptionAnswerDTO.getQuestion().getQuestion_type())
				|| "2".equals(questionOptionAnswerDTO.getQuestion().getQuestion_type())) {
			hql = hql + " jwcpxt_dissatisfied_feedback dissatisfiedFeedback,";//
		}
		hql = hql + " jwcpxt_answer_choice answerChoice,"//
				+ "	jwcpxt_option _option,"//
				+ "	jwcpxt_question question,"//
				+ " jwcpxt_service_client serviceClient,"//
				+ " jwcpxt_service_instance serviceInstance,"//
				+ " jwcpxt_unit unit,"//
				+ " jwcpxt_service_definition serviceDefinition"//
				+ " where"//
				+ " "//
				+ " answerChoice.answer_choice_option = _option.jwcpxt_option_id";//
		if ("1".equals(questionOptionAnswerDTO.getQuestion().getQuestion_type())
				|| "2".equals(questionOptionAnswerDTO.getQuestion().getQuestion_type())) {
			hql = hql
					+ " and dissatisfiedFeedback.dissatisfied_feedback_answer_choice = answerChoice.jwcpxt_answer_choice_id";//
		}
		hql = hql + "	and answerChoice.answer_choice_question = question.jwcpxt_question_id"//
				+ " and answerChoice.answer_choice_client = serviceClient.jwcpxt_service_client_id"//
				+ " and serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id"//
				+ " and serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id"//
				+ "	and serviceInstance.service_instance_service_definition = serviceDefinition.jwcpxt_service_definition_id"//
				+ " "//
				+ " and (unit.jwcpxt_unit_id like :unitId or unit.unit_father like :unitId)"//
				+ "	and serviceDefinition.jwcpxt_service_definition_id like :serviceDefinitionId"//
				+ " and serviceClient.service_client_gmt_modified >= :startTime "//
				+ " and serviceClient.service_client_gmt_modified < :endTime "
				+ " and question.jwcpxt_question_id like :questionId"//
				+ " and _option.jwcpxt_option_id like :optionId";//

		// System.out.println("hql:" + hql);
		// System.out.println("startTime：" + startTime + " 00:00:00");
		// System.out.println("endTime:" + endTime + " 23:59:59");
		// System.out.println("serviceDefinitionId:" +
		// statisDissaQuestionDateVO.getScreenService());
		// System.out.println("questionId:" +
		// questionOptionAnswerDTO.getQuestion().getJwcpxt_question_id());
		// System.out.println("optionId:" +
		// questionOptionAnswerDTO.getOption().getJwcpxt_option_id());

		Query query = session.createQuery(hql);
		if (statisDissaQuestionDateVO.getScreenUnit().equals("")) {
			query.setParameter("unitId", "%%");
		} else {
			query.setParameter("unitId", statisDissaQuestionDateVO.getScreenUnit());
		}
		query.setParameter("startTime", startTime + " 00:00:00");
		query.setParameter("endTime", endTime + " 00:00:00");
		query.setParameter("serviceDefinitionId", statisDissaQuestionDateVO.getScreenService());
		query.setParameter("questionId", questionOptionAnswerDTO.getQuestion().getJwcpxt_question_id());
		query.setParameter("optionId", questionOptionAnswerDTO.getOption().getJwcpxt_option_id());
		try {
			int count = ((Number) query.uniqueResult()).intValue();
			return count;
		} catch (ClassCastException e) {
			return 0;
		} finally {
			session.clear();
		}
	}

	/**
	 * 根据VO获取该业务的所有问题以及选项
	 */
	@Override
	public List<QuestionOptionAnswerDTO> get_pushQuestionOption(StatisDissaQuestionDateVO statisDissaQuestionDateVO) {
		Session session = getSession();
		String hql = "select new com.pphgzs.domain.DTO.QuestionOptionAnswerDTO(question,_option) from"//
				+ " jwcpxt_dissatisfied_feedback dissatisfiedFeedback,"//
				+ " jwcpxt_answer_choice answerChoice,"//
				+ "	jwcpxt_option _option,"//
				+ "	jwcpxt_question question,"//
				+ " jwcpxt_service_client serviceClient,"//
				+ " jwcpxt_service_instance serviceInstance,"//
				+ " jwcpxt_unit unit,"//
				+ " jwcpxt_service_definition serviceDefinition"//
				+ " where"//
				+ " "//
				+ " dissatisfiedFeedback.dissatisfied_feedback_answer_choice = answerChoice.jwcpxt_answer_choice_id"//
				+ " and answerChoice.answer_choice_option = _option.jwcpxt_option_id"//
				+ "	and answerChoice.answer_choice_question = question.jwcpxt_question_id"//
				+ " and answerChoice.answer_choice_client = serviceClient.jwcpxt_service_client_id"//
				+ " and serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id"//
				+ " and serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id"//
				+ "	and serviceInstance.service_instance_service_definition = serviceDefinition.jwcpxt_service_definition_id"//
				+ " "//
				+ " and (unit.jwcpxt_unit_id like :unitId or unit.unit_father like :unitId)"//
				+ "	and serviceDefinition.jwcpxt_service_definition_id like :serviceDefinitionId";//
		Query query = session.createQuery(hql);
		if (statisDissaQuestionDateVO.getScreenUnit().equals("")) {
			query.setParameter("unitId", "%%");
		} else {
			query.setParameter("unitId", statisDissaQuestionDateVO.getScreenUnit());
		}
		if (statisDissaQuestionDateVO.getScreenService().equals("")) {
			query.setParameter("serviceDefinitionId", "%%");
		} else {
			query.setParameter("serviceDefinitionId", statisDissaQuestionDateVO.getScreenService());
		}
		List<QuestionOptionAnswerDTO> list = new ArrayList<>();
		list = query.list();
		session.clear();
		return list;
	}

	/**
	 * 
	 */
	@Override
	public int statisticsDaoget_countService_byTime(StatisDissaServiceDateVO statisDissaServiceDateVO, String startTime,
			String endTime, jwcpxt_service_definition serviceDefinition) {
		Session session = getSession();
		String hql = "select count(*)"//
				+ " from "//
				+ " jwcpxt_dissatisfied_feedback dissatisfiedFeedback,"//
				+ " jwcpxt_answer_choice answerChoice,"//
				+ " jwcpxt_service_client serviceClient,"//
				+ " jwcpxt_service_instance serviceInstance,"//
				+ " jwcpxt_service_definition serviceDefinition,"//
				+ " jwcpxt_unit unit"//
				+ " where"//
				+ ""//
				+ " dissatisfiedFeedback.dissatisfied_feedback_answer_choice = answerChoice.jwcpxt_answer_choice_id"//
				+ " and answerChoice.answer_choice_client = serviceClient.jwcpxt_service_client_id"//
				+ " and serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id"//
				+ " and serviceInstance.service_instance_service_definition = serviceDefinition.jwcpxt_service_definition_id"//
				+ " and serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id"//
				+ ""//
				+ " and (unit.jwcpxt_unit_id like :unitId or unit.unit_father like :unitId) "//
				+ " and serviceClient.service_client_gmt_modified >= :startTime "//
				+ " and serviceClient.service_client_gmt_modified <= :endTime "//
				+ " and serviceDefinition.jwcpxt_service_definition_id = :serviceDefinitionId";
		// System.out.println("hql:" + hql);
		Query query = session.createQuery(hql);
		if (statisDissaServiceDateVO.getScreenUnit().equals("")) {
			query.setParameter("unitId", "%%");
		} else {
			query.setParameter("unitId", statisDissaServiceDateVO.getScreenUnit());
		}
		query.setParameter("startTime", startTime + " 00:00:00");
		query.setParameter("endTime", endTime + " 00:00:00");
		query.setParameter("serviceDefinitionId", serviceDefinition.getJwcpxt_service_definition_id());
		try {
			int count = ((Number) query.uniqueResult()).intValue();
			return count;
		} catch (ClassCastException e) {
			return 0;
		} finally {
			session.clear();
		}
	}

	/**
	 * 获取当前时间段里面所有的业务
	 */
	@Override
	public List<jwcpxt_service_definition> get_pushService_byTime(StatisDissaServiceDateVO statisDissaServiceDateVO) {
		Session session = getSession();
		String hql = "select distinct(serviceDefinition)"//
				+ " from "//
				+ " jwcpxt_dissatisfied_feedback dissatisfiedFeedback,"//
				+ " jwcpxt_answer_choice answerChoice,"//
				+ " jwcpxt_service_client serviceClient,"//
				+ " jwcpxt_service_instance serviceInstance,"//
				+ " jwcpxt_service_definition serviceDefinition,"//
				+ " jwcpxt_unit unit"//
				+ " where"//
				+ ""//
				+ " dissatisfiedFeedback.dissatisfied_feedback_answer_choice = answerChoice.jwcpxt_answer_choice_id"//
				+ " and answerChoice.answer_choice_client = serviceClient.jwcpxt_service_client_id"//
				+ " and serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id"//
				+ " and serviceInstance.service_instance_service_definition = serviceDefinition.jwcpxt_service_definition_id"//
				+ " and serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id"//
				+ ""//
				+ " and (unit.jwcpxt_unit_id like :unitId or unit.unit_father like :unitId) ";//
		Query query = session.createQuery(hql);
		if (statisDissaServiceDateVO.getScreenUnit().equals("")) {
			query.setParameter("unitId", "%%");
		} else {
			query.setParameter("unitId", statisDissaServiceDateVO.getScreenUnit());
		}
		List<jwcpxt_service_definition> list = new ArrayList<>();
		list = query.list();
		session.clear();
		return list;
	}

	/**
	 * 获取时间段的总数量
	 */
	@Override
	public int get_totaolCount(StatisDissatiDateVO statisDissatiDateVO, String startTime, String endTime) {
		Session session = getSession();
		String hql = "select count(*) "//
				+ " from"//
				+ " jwcpxt_answer_choice answerChoice,"//
				+ " jwcpxt_service_client serviceClient,"//
				+ " jwcpxt_service_instance serviceInstance,"//
				+ " jwcpxt_unit unit"//
				+ " where"//
				+ " answerChoice.answer_choice_client = serviceClient.jwcpxt_service_client_id"//
				+ " and serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id"//
				+ " and serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id"//
				+ ""//
				+ " and unit.jwcpxt_unit_id like :unitId"//
				+ " and serviceClient.service_client_gmt_modified >= :startTime "//
				+ " and serviceClient.service_client_gmt_modified <= :endTime ";//
		Query query = session.createQuery(hql);
		if (statisDissatiDateVO.getScreenUnit().equals("")) {
			query.setParameter("unitId", "%%");
		} else {
			query.setParameter("unitId", statisDissatiDateVO.getScreenUnit());
		}
		query.setParameter("startTime", startTime + " 00:00:00");
		query.setParameter("endTime", endTime + " 00:00:00");
		try {
			int count = ((Number) query.uniqueResult()).intValue();
			return count;
		} catch (ClassCastException e) {
			return 0;
		} finally {
			session.clear();
		}
	}

	/**
	 * 推送数量
	 */
	@Override
	public int get_countOption_byTime(StatisDissatiDateVO statisDissatiDateVO, String startTime, String endTime,
			String option) {
		Session session = getSession();
		String hql = "select count(*) from "//
				+ "jwcpxt_dissatisfied_feedback dissatisfiedFeedback,"//
				+ "jwcpxt_answer_choice answerChoice,"//
				+ "jwcpxt_option _option,"//
				+ "jwcpxt_service_client serviceClient,"//
				+ "jwcpxt_service_instance serviceInstance,"//
				+ "jwcpxt_unit unit "//
				+ "where "//
				+ "dissatisfiedFeedback.dissatisfied_feedback_answer_choice = answerChoice.jwcpxt_answer_choice_id "//
				+ "and answerChoice.answer_choice_option = _option.jwcpxt_option_id "//
				+ "and answerChoice.answer_choice_client = serviceClient.jwcpxt_service_client_id "//
				+ "and serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id "//
				+ "and serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id "//
				+ ""//
				+ "and (unit.jwcpxt_unit_id like :unitId or unit.unit_father like :unitId) "//
				+ " and serviceClient.service_client_gmt_modified >= :startTime "//
				+ " and serviceClient.service_client_gmt_modified <= :endTime "//
				+ "and _option.option_describe like :option";
		// System.out.println("hql:" + hql);
		// System.out.println("startTime:" + startTime + " 00:00:00");
		// System.out.println("endTime：" + endTime + " 23:59:59");
		// System.out.println("option:" + option);
		Query query = session.createQuery(hql);
		if (statisDissatiDateVO.getScreenUnit().equals("")) {
			query.setParameter("unitId", "%%");
		} else {
			query.setParameter("unitId", statisDissatiDateVO.getScreenUnit());
		}
		query.setParameter("startTime", startTime + " 00:00:00");
		query.setParameter("endTime", endTime + " 00:00:00");
		query.setParameter("option", option);
		try {
			int count = ((Number) query.uniqueResult()).intValue();
			return count;
		} catch (ClassCastException e) {
			return 0;
		} finally {
			session.clear();
		}
	}

	/**
	 * 获取对应的所有推送选项
	 */
	@Override
	public List<String> get_pushOption_byTime(StatisDissatiDateVO statisDissatiDateVO) {
		Session session = getSession();
		String hql = "select distinct(_option.option_describe) from "//
				+ "jwcpxt_dissatisfied_feedback dissatisfiedFeedback,"//
				+ "jwcpxt_answer_choice answerChoice,"//
				+ "jwcpxt_option _option,"//
				+ "jwcpxt_service_client serviceClient,"//
				+ "jwcpxt_service_instance serviceInstance,"//
				+ "jwcpxt_unit unit "//
				+ "where "//
				+ "dissatisfiedFeedback.dissatisfied_feedback_answer_choice = answerChoice.jwcpxt_answer_choice_id "//
				+ "and answerChoice.answer_choice_option = _option.jwcpxt_option_id "//
				+ "and answerChoice.answer_choice_client = serviceClient.jwcpxt_service_client_id "//
				+ "and serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id "//
				+ "and serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id "//
				+ ""//
				+ "and (unit.jwcpxt_unit_id like :unitId or unit.unit_father like :unitId) ";
		Query query = session.createQuery(hql);
		if (statisDissatiDateVO.getScreenUnit().equals("")) {
			query.setParameter("unitId", "%%");
		} else {
			query.setParameter("unitId", statisDissatiDateVO.getScreenUnit());
		}
		List<String> list = new ArrayList<>();
		list = query.list();
		session.clear();
		return list;
	}

	@Override
	public int get_StatisticsDissatisfiedDateCount_byFatherUnit(String jwcpxt_service_definition_id,
			String jwcpxt_unit_id, String startTime, String endTime) {
		Session session = getSession();
		String hql = "select "//
				+ " count(answerChoice) "//
				+ " from "//
				+ " jwcpxt_answer_choice answerChoice , "//
				+ " jwcpxt_service_client serviceClient , "//
				+ " jwcpxt_service_instance serviceInstance , "//
				+ " jwcpxt_option option , "//
				+ " jwcpxt_unit unit "//
				//
				+ " where "//
				+ " answerChoice.answer_choice_client=serviceClient.jwcpxt_service_client_id "//
				+ " and serviceClient.service_client_service_instance=serviceInstance.jwcpxt_service_instance_id "//
				+ " and answerChoice.answer_choice_option=option.jwcpxt_option_id "//
				//
				+ " and (unit.unit_father=:unitID or unit.jwcpxt_unit_id = :unitID) "//
				+ " and serviceInstance.service_instance_belong_unit=unit.jwcpxt_unit_id "//
				+ " and serviceInstance.service_instance_service_definition=:serviceDefinitionID "//
				+ " and serviceClient.service_client_gmt_modified >= :startTime "//
				+ " and serviceClient.service_client_gmt_modified <= :endTime "//
				+ " and option.option_push='1' "//
		;
		Query query = session.createQuery(hql);
		//
		query.setParameter("unitID", jwcpxt_unit_id);
		query.setParameter("serviceDefinitionID", jwcpxt_service_definition_id);
		query.setParameter("startTime", startTime + " 00:00:00");
		query.setParameter("endTime", endTime + " 23:59:59");
		//
		try {
			int count = ((Number) query.uniqueResult()).intValue();
			return count;
		} catch (ClassCastException e) {
			return 0;
		} finally {
			session.clear();
		}
	}

	@Override
	public int get_StatisticsDissatisfiedDateCount(String jwcpxt_service_definition_id, String jwcpxt_unit_id,
			String startTime, String endTime) {
		Session session = getSession();
		String hql = "select "//
				+ " count(answerChoice) "//
				+ " from "//
				+ " jwcpxt_answer_choice answerChoice , "//
				+ " jwcpxt_service_client serviceClient , "//
				+ " jwcpxt_service_instance serviceInstance , "//
				+ " jwcpxt_option option "//
				//
				+ " where "//
				+ " answerChoice.answer_choice_client=serviceClient.jwcpxt_service_client_id "//
				+ " and serviceClient.service_client_service_instance=serviceInstance.jwcpxt_service_instance_id "//
				+ " and answerChoice.answer_choice_option=option.jwcpxt_option_id "//
				//
				+ " and serviceInstance.service_instance_belong_unit=:unitID "//
				+ " and serviceInstance.service_instance_service_definition=:serviceDefinitionID "//
				+ " and serviceClient.service_client_gmt_modified >= :startTime "//
				+ " and serviceClient.service_client_gmt_modified <= :endTime "//
				+ " and option.option_push='1' "//
		;
		Query query = session.createQuery(hql);
		//
		query.setParameter("unitID", jwcpxt_unit_id);
		query.setParameter("serviceDefinitionID", jwcpxt_service_definition_id);
		query.setParameter("startTime", startTime + " 00:00:00");
		query.setParameter("endTime", endTime + " 23:59:59");
		//
		try {
			int count = ((Number) query.uniqueResult()).intValue();
			return count;
		} catch (ClassCastException e) {
			return 0;
		} finally {
			session.clear();
		}
	}

	@Override
	public int get_dayNum_byServiceDefinitionIDAndDate_byFatherUnit(String serviceDefinitionID, String unitID,
			String day) {
		Session session = getSession();
		String hql = "select "//
				+ " count(answerChoice) "//
				+ " from "//
				+ " jwcpxt_answer_choice answerChoice , "//
				+ " jwcpxt_service_client serviceClient , "//
				+ " jwcpxt_service_instance serviceInstance , "//
				+ " jwcpxt_option _option, "//
				+ " jwcpxt_unit unit "//
				//
				+ " where "//
				+ " answerChoice.answer_choice_client=serviceClient.jwcpxt_service_client_id "//
				+ " and serviceClient.service_client_service_instance=serviceInstance.jwcpxt_service_instance_id "//
				+ " and answerChoice.answer_choice_option=_option.jwcpxt_option_id "//
				+ " and serviceInstance.service_instance_belong_unit=unit.jwcpxt_unit_id "//
				//
				+ " and (unit.unit_father=:unitID or unit.jwcpxt_unit_id=:unitID) "//
				+ " and serviceInstance.service_instance_service_definition=:serviceDefinitionID "//
				+ " and serviceClient.service_client_gmt_modified like :day "//
				+ " and _option.option_push='1' "//
		;
		Query query = session.createQuery(hql);
		//
		query.setParameter("unitID", unitID);
		query.setParameter("serviceDefinitionID", serviceDefinitionID);
		query.setParameter("day", day + "%");

		//
		try {
			int count = ((Number) query.uniqueResult()).intValue();
			return count;
		} catch (ClassCastException e) {
			return 0;
		} finally {
			session.clear();
		}
	}

	@Override
	public int get_dayNum_byServiceDefinitionIDAndDate(String serviceDefinitionID, String unitID, String day) {

		Session session = getSession();
		String hql = "select "//
				+ " count(answerChoice) "//
				+ " from "//
				+ " jwcpxt_answer_choice answerChoice , "//
				+ " jwcpxt_service_client serviceClient , "//
				+ " jwcpxt_service_instance serviceInstance , "//
				+ " jwcpxt_option _option "//
				//
				+ " where "//
				+ " answerChoice.answer_choice_client=serviceClient.jwcpxt_service_client_id "//
				+ " and serviceClient.service_client_service_instance=serviceInstance.jwcpxt_service_instance_id "//
				+ " and answerChoice.answer_choice_option=_option.jwcpxt_option_id "//
				//
				+ " and serviceInstance.service_instance_belong_unit=:unitID "//
				+ " and serviceInstance.service_instance_service_definition=:serviceDefinitionID "//
				+ " and serviceClient.service_client_gmt_modified like :day "//
				//
				+ " and _option.option_push='1' "//
		;
		Query query = session.createQuery(hql);
		//
		query.setParameter("unitID", unitID);
		query.setParameter("serviceDefinitionID", serviceDefinitionID);
		query.setParameter("day", day + "%");
		//
		try {
			int count = ((Number) query.uniqueResult()).intValue();
			return count;
		} catch (ClassCastException e) {
			return 0;
		} finally {
			session.clear();
		}
	}

	@Override
	public double geteStatisticsGrade_byFatherUnit(ServiceGradeDTO serviceGradeDTO, String fatherUnitId,
			String searchTimeStart, String searchTimeEnd, int i) {
		Session session = getSession();
		String hql = "";
		if (i == 1) {
			hql = hql + " select "//
					+ " (((count(distinct serviceClient.JWCPXT_SERVICE_CLIENT_ID)*100)  -  sum(_option.option_grade))  /  count(distinct serviceClient.JWCPXT_SERVICE_CLIENT_ID))*100  "//
					+ " from "//
					+ " jwcpxt_unit unit , "//
					+ " jwcpxt_service_instance serviceInstance , "//
					+ " jwcpxt_service_client serviceClient , "//
					+ " jwcpxt_answer_choice answerChoice , "//
					+ " jwcpxt_option _option "//
					//
					+ " where "
					+ " serviceClient.service_client_service_instance=serviceInstance.jwcpxt_service_instance_id"//
					+ " and answerChoice.answer_choice_client=serviceClient.jwcpxt_service_client_id"//
					+ " and answerChoice.answer_choice_option=_option.jwcpxt_option_id"//
					+ " and serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id "// 业务实例关联到三级单位
					//
					+ " and (unit.unit_father = :fatherUnitId or unit.jwcpxt_unit_id= :fatherUnitId)" // 二级单位ID等于传过来的单位ID
					+ " and serviceInstance.service_instance_service_definition = :serviceDefinitionID "//
					+ " and serviceClient.service_client_gmt_modified >= :searchTimeStart"//
					+ " and serviceClient.service_client_gmt_modified <= :searchTimeEnd";//
			//
			// + " and serviceInstance.service_instance_date >= :searchTimeStart "//
			// + " and serviceInstance.service_instance_date <= :searchTimeEnd ";
		}
		if (i == 2) {
			hql = hql + "SELECT"//
					+ " (("//
					+ " (t1.total * 100) - sum(t1.option_grade)"//
					+ " ) / t1.total)*100"//
					+ " FROM"//
					+ " ("//
					+ " SELECT"//
					+ " serviceClient.JWCPXT_SERVICE_CLIENT_ID,"//
					+ " serviceInstance.SERVICE_INSTANCE_BELONG_FEEDBACK,"//
					+ " _option.option_grade,"//
					+ " serviceClient.SERVICE_CLIENT_PHONE,"//
					+ " coun.total"//
					+ " FROM"//
					+ " jwcpxt_unit unit,"//
					+ " jwcpxt_service_instance serviceInstance,"//
					+ " jwcpxt_service_client serviceClient,"//
					+ " jwcpxt_answer_choice answerChoice,"//
					+ " jwcpxt_option _option,"//
					+ "	("//
					+ " SELECT"//
					+ " count("//
					+ " DISTINCT serviceClient.JWCPXT_SERVICE_CLIENT_ID"//
					+ " ) AS total"//
					+ " FROM"//
					+ " jwcpxt_unit unit,"//
					+ " jwcpxt_service_instance serviceInstance,"//
					+ " jwcpxt_service_client serviceClient,"//
					+ " jwcpxt_answer_choice answerChoice,"//
					+ " jwcpxt_option _option"//
					+ " WHERE"//
					+ " serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id"//
					+ " AND answerChoice.answer_choice_client = serviceClient.jwcpxt_service_client_id"//
					+ " AND answerChoice.answer_choice_option = _option.jwcpxt_option_id"//
					+ " AND serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id"//
					+ " AND ("//
					+ " unit.unit_father = :fatherUnitId"//
					+ " OR unit.jwcpxt_unit_id = :fatherUnitId"//
					+ " )"//
					+ " AND serviceInstance.service_instance_service_definition = :serviceDefinitionID"//
					+ " and serviceClient.service_client_gmt_modified >= :searchTimeStart"//
					+ " and serviceClient.service_client_gmt_modified <= :searchTimeEnd" + " ) coun"//
					+ " WHERE"//
					+ " serviceClient.service_client_service_instance = serviceInstance.jwcpxt_service_instance_id"//
					+ " AND answerChoice.answer_choice_client = serviceClient.jwcpxt_service_client_id"//
					+ " AND answerChoice.answer_choice_option = _option.jwcpxt_option_id"//
					+ " AND serviceInstance.service_instance_belong_unit = unit.jwcpxt_unit_id"//
					+ " AND ("//
					+ " unit.unit_father = :fatherUnitId"//
					+ " OR unit.jwcpxt_unit_id = :fatherUnitId"//
					+ " )"//
					+ " AND serviceInstance.service_instance_service_definition = :serviceDefinitionID"//
					+ " and serviceClient.service_client_gmt_modified >= :searchTimeStart"//
					+ " and serviceClient.service_client_gmt_modified <= :searchTimeEnd" + " ) t1"//
					+ " LEFT JOIN ("//
					+ " SELECT"//
					+ " feedbackRectification.jwcpxt_feedback_rectification_id"//
					+ " FROM"//
					+ " jwcpxt_feedback_rectification feedbackRectification,"//
					+ " jwcpxt_dissatisfied_feedback dissatisfiedFeedback,"//
					+ " jwcpxt_answer_choice answerChoice,"//
					+ " jwcpxt_option _option,"//
					+ " jwcpxt_service_client serviceClient,"//
					+ " jwcpxt_service_instance serviceInstance,"//
					+ " jwcpxt_unit unit"//
					+ " WHERE"//
					+ " feedbackRectification.feedback_rectification_dissatisfied_feedback = dissatisfiedFeedback.jwcpxt_dissatisfied_feedback_id"//
					+ " AND dissatisfiedFeedback.dissatisfied_feedback_answer_choice = answerChoice.JWCPXT_ANSWER_CHOICE_ID"//
					+ " AND answerChoice.ANSWER_CHOICE_OPTION = _option.JWCPXT_OPTION_ID"//
					+ " AND answerChoice.ANSWER_CHOICE_CLIENT = serviceClient.JWCPXT_SERVICE_CLIENT_ID"//
					+ " AND serviceClient.SERVICE_CLIENT_SERVICE_INSTANCE = serviceInstance.JWCPXT_SERVICE_INSTANCE_ID"//
					+ " AND serviceInstance.SERVICE_INSTANCE_BELONG_UNIT = unit.JWCPXT_UNIT_ID"//
					+ " AND ("//
					+ " unit.unit_father = :fatherUnitId"//
					+ " OR unit.jwcpxt_unit_id = :fatherUnitId"//
					+ " )"//
					+ " AND _option.OPTION_GRADE > 0"//
					+ " and serviceClient.service_client_gmt_modified <= :searchTimeEnd"
					+ " ) t2 ON t2.jwcpxt_feedback_rectification_id = t1.SERVICE_INSTANCE_BELONG_FEEDBACK"//
					+ " WHERE"//
					+ " t2.jwcpxt_feedback_rectification_id IS NOT NULL";
		}

		System.out.println("---------------------------------------");
		System.out.println("hql:" + hql);
		System.out.println("fatherUnitId：" + fatherUnitId);
		System.out.println("serviceDefinitionID:" + serviceGradeDTO.getService_id());
		System.out.println("searchTimeStart:" + searchTimeStart + " 00:00:00");
		System.out.println("searchTimeEnd:" + searchTimeEnd + " 23:59:59");
		System.out.println("---------------------------------------");
		Query query = session.createSQLQuery(hql);
		query.setParameter("fatherUnitId", fatherUnitId);
		query.setParameter("serviceDefinitionID", serviceGradeDTO.getService_id());
		//
		if (searchTimeStart.equals("")) {
			query.setParameter("searchTimeStart", "0000-00-00");
		} else {
			query.setParameter("searchTimeStart", searchTimeStart + " 00:00:00");
		}
		if (searchTimeEnd.equals("")) {
			query.setParameter("searchTimeEnd", "9999-99-99");
		} else {
			query.setParameter("searchTimeEnd", searchTimeEnd + " 23:59:59");
		}
		//
		//
		try {
			double count = ((Number) query.uniqueResult()).intValue();
			return (count / 10000) * serviceGradeDTO.getGrade();
		} catch (ClassCastException e) {
			return serviceGradeDTO.getGrade();
		} catch (NullPointerException e) {
			return serviceGradeDTO.getGrade();
		} finally {
			session.clear();
		}
	}

	@Override
	public double geteStatisticsGrade(ServiceGradeDTO serviceGradeDTO, String unitId, String searchTimeStart,
			String searchTimeEnd) {
		Session session = getSession();
		String hql = " select "//
				+ " ((count(distinct serviceClient)*100)  -  sum(_option.option_grade))  /  count(distinct serviceClient) "//
				+ " from "//
				+ " jwcpxt_service_instance serviceInstance , "//
				+ " jwcpxt_service_client serviceClient , "//
				+ " jwcpxt_answer_choice answerChoice , "//
				+ " jwcpxt_option _option "//
				//
				+ " where "
				+ " serviceClient.service_client_service_instance=serviceInstance.jwcpxt_service_instance_id"//
				+ " and answerChoice.answer_choice_client=serviceClient.jwcpxt_service_client_id"//
				+ " and answerChoice.answer_choice_option=_option.jwcpxt_option_id"//
				//
				+ " and serviceInstance.service_instance_belong_unit = :unitId "//
				+ " and serviceInstance.service_instance_service_definition = :serviceDefinitionID "//
				//
				+ " and serviceInstance.service_instance_date >= :searchTimeStart "//
				+ " and serviceInstance.service_instance_date <= :searchTimeEnd "//
		;
		Query query = session.createQuery(hql);
		query.setParameter("unitId", unitId);
		query.setParameter("serviceDefinitionID", serviceGradeDTO.getService_id());
		//
		query.setParameter("searchTimeStart", searchTimeStart + " 00:00:00");
		query.setParameter("searchTimeEnd", searchTimeEnd + " 23:59:59");
		//
		//

		try {
			double count = ((Number) query.uniqueResult()).intValue();
			return (count / 100) * serviceGradeDTO.getGrade();
		} catch (ClassCastException e) {
			return serviceGradeDTO.getGrade();
		} catch (NullPointerException e) {
			return serviceGradeDTO.getGrade();
		} finally {
			session.clear();
		}

		//

	}

}