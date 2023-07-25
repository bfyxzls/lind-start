package com.lind.start.test.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.lind.start.test.dto.UserDTO;

import java.util.List;

/**
 * @author lind
 * @date 2022/8/10 15:56
 * @since 1.0.0
 */
public interface UserService {

	void getDetail(int id);

	String getName(int id);

	void save(UserDTO userDTO) throws JsonProcessingException;

	void del(String id);

	List<UserDTO> getAll();

}
