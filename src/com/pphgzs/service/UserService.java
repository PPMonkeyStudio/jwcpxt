package com.pphgzs.service;

import java.util.List;

import com.pphgzs.domain.DO.jwcpxt_user;
import com.pphgzs.domain.DTO.UserDTO;
import com.pphgzs.domain.VO.UserVO;

public interface UserService {

	public boolean save_user(jwcpxt_user user);

	public boolean delete_user(jwcpxt_user user);

	public boolean update_user(jwcpxt_user newUser);

	public List<jwcpxt_user> list_user_all();

	public jwcpxt_user get_user_byUserID(String userID);

	public List<UserDTO> list_userDTO_all();

	List<UserDTO> list_userDTOList_byUserList(List<jwcpxt_user> userList);

	UserDTO get_userDTO_byUserID(String userID);

	public UserVO get_userVO(UserVO userVO);

	public List<jwcpxt_user> list_user_byJurisdiction(jwcpxt_user user);

	/**
	 * 获取一个用户某天被分配了多少个
	 * 
	 * @param date
	 * @return
	 */
	public int get_userDistributionNum_byToday(String userID);

}
