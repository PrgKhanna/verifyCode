package com.vc.caching;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.springframework.stereotype.Service;

import com.vc.models.UserBO;

@Service
public class UserServiceCacheImpl extends CachingService<UserBO> {

	@Override
	public void save(String key, UserBO userBO) {
		redisService.setValueWithTimeLimit(key, userBO, 12, TimeUnit.HOURS);

	}

	@Override
	public void saveAll(String key, List<UserBO> userBOs) {
		redisService.setValueWithTimeLimit(key, userBOs, 12, TimeUnit.HOURS);
	}

}
