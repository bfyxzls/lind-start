package com.lind.start.test.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.lind.start.test.CurrentThreadObj;
import com.lind.start.test.dto.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author lind
 * @date 2022/8/10 15:57
 * @since 1.0.0
 */
@Component("user")
public class UserServiceImpl implements UserService {

	static String USER_SET = "user-set";

	@Autowired
	RedisTemplate redisTemplate;

	@Autowired
	ObjectMapper objectMapper;

	@Override
	public void getDetail(int id) {
		System.out.println(CurrentThreadObj.get("hello"));
	}

	@Override
	public String getName(int id) {
		return "lind";
	}

	@Override
	public void save(UserDTO userDTO) throws JsonProcessingException {
		redisTemplate.opsForSet().add(USER_SET, objectMapper.writeValueAsString(userDTO));
	}

	@Override
	public void del(String id) {
		redisTemplate.opsForSet().remove(USER_SET, id);
	}

	@Override
	public List<UserDTO> getAll() {
		Set set = redisTemplate.opsForSet().members(USER_SET);
		List<UserDTO> userDTOS = new ArrayList<>();
		set.forEach(o -> {
			UserDTO userDTO = null;
			try {
				userDTO = objectMapper.readValue(o.toString(), UserDTO.class);
			}
			catch (JsonProcessingException e) {
				throw new RuntimeException(e);
			}
			userDTOS.add(userDTO);
		});
		return userDTOS;
	}

}
