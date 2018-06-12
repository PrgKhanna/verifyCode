# pkhanna verifyCode

Spring, Hibernate , Mysql , Maven , Junit.

API : /vc/verification_code/{user_id}.{format} - generate code for user
      /vc/verification_code/{user_id}/{code}.{format} - verify code for user

There are 2 tables:
1. users - Stores User info
2. user_verification_code_mapping - user verification code mapping.

There is a scheduler which runs every 10 mins to expire the code which are 30 mins older if expiry flag is set to true.

few unit test written for VerificationCodeResponseGeneratorService.


