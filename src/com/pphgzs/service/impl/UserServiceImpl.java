package com.pphgzs.service.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import com.pphgzs.dao.UserDao;
import com.pphgzs.domain.DO.jwcpxt_entry_exit;
import com.pphgzs.domain.DO.jwcpxt_user;
import com.pphgzs.domain.VO.UserVO;
import com.pphgzs.service.UnitService;
import com.pphgzs.service.UserService;
import com.pphgzs.util.MD5Util;
import com.pphgzs.util.TimeUtil;
import com.pphgzs.util.uuidUtil;

public class UserServiceImpl implements UserService {
	private UserDao userDao;
	private UnitService unitService;

	public UnitService getUnitService() {
		return unitService;
	}

	public void setUnitService(UnitService unitService) {
		this.unitService = unitService;
	}

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}

	/*
	 * 
	 * 
	 */

	@Override
	public jwcpxt_user get_userDO_byUserID(String userID) {
		return userDao.get_userDO_byUserID(userID);
	}

	@Override
	public boolean save_user(jwcpxt_user user) {
		// 名称不可重复
		if (userDao.get_user_byAccount(user.getUser_account()) == null) {
			user.setJwcpxt_user_id(uuidUtil.getUuid());
			//
			user.setUser_password(MD5Util.GetMD5Code(user.getUser_account()));
			//

			//
			String time = TimeUtil.getStringSecond();
			user.setUser_gmt_create(time);
			user.setUser_gmt_modified(time);
			userDao.save_user(user);
			return true;
		} else {
			return false;
		}
	}

	@Override
	public jwcpxt_user get_userDO_byRandomAndTypeCP() {
		return userDao.get_userDO_byRandomAndTypeCP();
	}

	@Override
	public UserVO get_userVO() {
		UserVO userVO = new UserVO();
		List<jwcpxt_user> userList = userDao.list_user_all();
		userVO.setUserList(userList);

		return userVO;
	}

	@Override
	public boolean reset_userPassword_byUserID(String userID) {
		jwcpxt_user user = userDao.get_userDO_byUserID(userID);

		if (user == null) {
			return false;
		}

		user.setUser_password(MD5Util.GetMD5Code(user.getUser_account()));
		userDao.update_user(user);
		return true;
	}

	@Override
	public boolean update_userPassword(jwcpxt_user newUser) {
		jwcpxt_user oldUser = userDao.get_userDO_byUserID(newUser.getJwcpxt_user_id());
		if (oldUser == null) {
			return false;
		}

		if (newUser.getUser_password() == null) {
			return false;
		}
		oldUser.setUser_password(MD5Util.GetMD5Code(newUser.getUser_password()));

		userDao.update_user(oldUser);
		return true;
	}

	@Override
	public boolean ban_user_byUserID(String userID) {
		jwcpxt_user user = userDao.get_userDO_byUserID(userID);

		if (user == null) {
			return false;
		}
		if (user.getUser_state() == 1) {
			user.setUser_state(2);
		} else {
			user.setUser_state(1);
		}

		userDao.update_user(user);
		return true;
	}

	@SuppressWarnings("resource")
	@Override
	public boolean uploadExcel(File file, String fileFileName, String fileContentType) throws Exception {
		FileInputStream inputStream = new FileInputStream(file);
		// 文件的后缀名
		System.out.println(fileFileName);
		String suffix = fileFileName.substring(fileFileName.lastIndexOf("."));
		List<jwcpxt_entry_exit> entryExitList = new LinkedList<jwcpxt_entry_exit>();
		Workbook workbook = null;
		if (".xls".equals(suffix)) {
			workbook = new HSSFWorkbook(inputStream); // 支持07版本以前的excel
			entryExitList = HssUpload(workbook);
		} else if (".xlsx".equals(suffix)) {
			workbook = new XSSFWorkbook(inputStream); // 支持07版本以后的excel
			entryExitList = XssUpload(workbook);
		} else {
			System.out.println("不支持的文件类型！");
			return false;
		}
		for (jwcpxt_entry_exit entry_exit : entryExitList) {
			if (!userDao.saveObject(entry_exit)) {
				System.out.println(entry_exit);
				throw new Exception();
			}
		}
		return true;
	}

	private String getUnitCode(String unitName) {
		if (unitName.indexOf("湘东") > -1) {
			return "360313120000";
		} else if (unitName.indexOf("上栗") > -1) {
			return "360322060000";
		} else if (unitName.indexOf("安源") > -1) {
			return "360302160000";
		} else if (unitName.indexOf("芦溪") > -1) {
			return "360323210000";
		} else if (unitName.indexOf("莲花") > -1) {
			return "360321060000";
		} else if (unitName.indexOf("萍乡市公安局") > -1) {
			return "360300240000";
		}
		return null;
	}

	private List<jwcpxt_entry_exit> HssUpload(Workbook workbook) throws ParseException, UnsupportedEncodingException {
		List<jwcpxt_entry_exit> entryExitList = new LinkedList<jwcpxt_entry_exit>();
		jwcpxt_entry_exit entry_exit;
		HSSFSheet sheet = (HSSFSheet) workbook.getSheetAt(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 1, rowNum = sheet.getLastRowNum(); i <= rowNum; i++) {
			HSSFRow row = sheet.getRow(i);
			entry_exit = new jwcpxt_entry_exit();
			for (int j = 0; j < 7; j++) {
				HSSFCell cell = row.getCell(j);
				switch (j) {
				case 0:
					entry_exit.setEntry_exit_client_name(cell.getStringCellValue());
					break;
				case 1:
					String Sex = cell.getStringCellValue();
					if ("男".equals(Sex)) {
						entry_exit.setEntry_exit_client_sex("1");
					} else if ("女".equals(Sex)) {
						entry_exit.setEntry_exit_client_sex("2");
					}
					break;
				case 2:
					entry_exit.setEntry_exit_client_type(cell.getStringCellValue());
					break;
				case 3:
					entry_exit.setEntry_exit_client_id_type(cell.getStringCellValue());
					break;
				case 4:
					String phone = cell.getStringCellValue().trim();
					if (phone.length() != 11) {
						continue;
					}
					entry_exit.setEntry_exit_client_phone(cell.getStringCellValue().trim());
					break;
				case 5:
					entry_exit.setEntry_exit_client_data(sdf.format(sdf.parse(cell.getStringCellValue())).toString());
					break;
				case 6:
					entry_exit.setEntry_exit_client_unit(getUnitCode(cell.getStringCellValue()));
					// entry_exit.setEntry_exit_client_unit(userDao.getUnitCode_ByUnitName(cell.getStringCellValue()));
					break;
				default:
					System.out.println("none");
					break;
				}
			}
			entry_exit.setEntry_exit_id(UUID.randomUUID().toString());
			entry_exit.setEntry_exit_client_is_distribute(2);
			entry_exit.setEntry_exit_gmt_create(TimeUtil.getStringSecond());
			entry_exit.setEntry_exit_gmt_modify(TimeUtil.getStringSecond());
			entryExitList.add(entry_exit);
		}
		return entryExitList;
	}

	private List<jwcpxt_entry_exit> XssUpload(Workbook workbook) throws ParseException, UnsupportedEncodingException {
		List<jwcpxt_entry_exit> entryExitList = new LinkedList<jwcpxt_entry_exit>();
		jwcpxt_entry_exit entry_exit;
		XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		for (int i = 1, rowNum = sheet.getLastRowNum(); i <= rowNum; i++) {
			XSSFRow row = sheet.getRow(i);
			entry_exit = new jwcpxt_entry_exit();
			String Sex;
			for (int j = 0; j < 7; j++) {
				XSSFCell cell = row.getCell(j);
				switch (j) {
				case 0:
					entry_exit.setEntry_exit_client_name(cell.getStringCellValue());
					break;
				case 1:
					Sex = cell.getStringCellValue();
					if ("男".equals(Sex)) {
						entry_exit.setEntry_exit_client_sex("1");
					} else if ("女".equals(Sex)) {
						entry_exit.setEntry_exit_client_sex("2");
					}
					break;
				case 2:
					entry_exit.setEntry_exit_client_type(cell.getStringCellValue());
					break;
				case 3:
					entry_exit.setEntry_exit_client_id_type(cell.getStringCellValue());
					break;
				case 4:
					String phone = cell.getStringCellValue().trim();
					if (phone.length() != 11) {
						continue;
					}
					entry_exit.setEntry_exit_client_phone(cell.getStringCellValue().trim());
					break;
				case 5:
					entry_exit.setEntry_exit_client_data(sdf.format(sdf.parse(cell.getStringCellValue())).toString());
					break;
				case 6:
					entry_exit.setEntry_exit_client_unit(getUnitCode(cell.getStringCellValue()));
					// entry_exit.setEntry_exit_client_unit(userDao.getUnitCode_ByUnitName(cell.getStringCellValue()));
					break;
				default:
					System.out.println("none");
					break;
				}
			}
			entry_exit.setEntry_exit_id(UUID.randomUUID().toString());
			entry_exit.setEntry_exit_client_is_distribute(2);
			entry_exit.setEntry_exit_gmt_create(TimeUtil.getStringSecond());
			entry_exit.setEntry_exit_gmt_modify(TimeUtil.getStringSecond());
			entryExitList.add(entry_exit);
		}
		return entryExitList;
	}
}
