package com.vc.utils;

import java.util.Calendar;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.vc.models.UserVerificationCodeMappingBO;
import com.vc.services.UserVerificationCodeMappingServiceImpl;

@Component
@EnableScheduling
public class VerificationCodeExpiryJob {

	// if expiry of verification code is set true in config.
	// Better solution can be to put the mapping entry in Delayed Queue with the
	// ttl,
	// and when the msg expired it is send to Main queue and consumed by the
	// consumer.
	// where in db we can set flag as false or invalidate cache.

	@Value("${verification.code.expiry}")
	private Boolean isExpireCode;

	@Value("${verification.code.expiry.time}")
	private long expiryTime;

	@Autowired
	private UserVerificationCodeMappingServiceImpl userVerificationCodeMappingServiceImpl;

	private static final Logger LOGGER = LoggerFactory.getLogger(VerificationCodeExpiryJob.class);

	@Scheduled(cron = "0 */10 * * * ?") // running every 10 mins, not a good solution, might happen that code can live
	// for 40 mins
	public void expireVerificationCodes() {
		LOGGER.info("Expiring Verification codes based on time : " + isExpireCode + " - " + expiryTime);
		if (isExpireCode) {
			List<UserVerificationCodeMappingBO> activeMappings = userVerificationCodeMappingServiceImpl
					.getAllActiveMappings();
			if (null != activeMappings) {
				Calendar cal = Calendar.getInstance();
				LOGGER.info("date - " + cal.getTimeInMillis());
				List<UserVerificationCodeMappingBO> expiredMappings = activeMappings.stream()
						.filter(d -> ((cal.getTimeInMillis() - d.getUpdatedOn().getTime()) > (expiryTime * 1000)))
						.peek(m -> m.setActive(false)).collect(Collectors.toList());
				LOGGER.info("Expiry List : " + expiredMappings);
				userVerificationCodeMappingServiceImpl.updateUserVerificationCodeMappings(expiredMappings);
			} else {
				LOGGER.info("No Active User & Code Mapping");
			}
		}
	}

}
