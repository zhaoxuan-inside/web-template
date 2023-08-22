package org.zhaoxuan.auth.biz.impl;

import cn.hutool.core.lang.Snowflake;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.zhaoxuan.auth.biz.AuthBiz;
import org.zhaoxuan.common.constants.RedisKeyPrefixConstants;
import org.zhaoxuan.common.constants.TimeConstants;
import org.zhaoxuan.common.exception.*;
import org.zhaoxuan.common.utils.*;
import org.zhaoxuan.pojo.bean.HeaderBean;
import org.zhaoxuan.pojo.request.auth.LoginRequest;
import org.zhaoxuan.pojo.response.auth.LoginResponse;
import org.zhaoxuan.pojo.response.auth.VerifyCodeResponse;
import org.zhaoxuan.pojo.response.user.UserOrgRoleInfo;
import org.zhaoxuan.remote_call.feign.service.DataFeignService;

import java.util.concurrent.TimeUnit;

@Slf4j
@Service
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
@ComponentScan(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = FeignClient.class))
public class AuthBizImpl implements AuthBiz {

    @Value("${module.auth.verify-code-length}")
    private int verifyCodeLength;

    private final HttpServletRequest httpServletRequest;
    private final RedisAccessUtils redisAccessUtils;
    private final Snowflake snowflake;
    private final DataFeignService dataFeignService;

    @Override
    public VerifyCodeResponse verifyCode() {
        long id = snowflake.nextId();
        String randStr = ImageUtils.getRandomString(verifyCodeLength);
        log.info("random string : {}.", randStr);
        String randStrBase64 = ImageUtils.getVerifyCodeImage(randStr);
        redisAccessUtils.set(RedisKeyPrefixConstants.VERIFY_CODE_KEY_PREFIX + id,
                randStr,
                TimeConstants.ONE_MINUTE * 10,
                TimeUnit.SECONDS);
        return VerifyCodeResponse.builder()
                .id(id)
                .image(randStrBase64)
                .build();
    }

    @Override
    public LoginResponse login(LoginRequest request)
            throws CustomException, IllegalAccessException {

        String verifyCode = (String) redisAccessUtils.get(RedisKeyPrefixConstants.VERIFY_CODE_KEY_PREFIX +
                request.getCodeId());
        ExceptionDecider.ifTrue(ObjectUtils.isEmpty(verifyCode) ||
                        ObjectUtils.isEmpty(request.getCode()) ||
                        !request.getCode().equals(verifyCode),
                ResponseCodeEnum.VERIFY_CODE_INVALID);

        long uid = dataFeignService.checkPasswd(request.getAccount(), request.getPassword());
        UserOrgRoleInfo user = dataFeignService.getUserById(uid);

        long token = snowflake.nextId();
        HeaderBean headerBean = HeaderUtils.mapHeaderParam(httpServletRequest);

        redisAccessUtils.del(RedisKeyPrefixConstants.VERIFY_CODE_KEY_PREFIX + request.getCodeId());

        Long oldToken = (Long) redisAccessUtils.get(RedisKeyPrefixConstants.TOKEN_IDX_UID + headerBean.getFrom() + uid);
        redisAccessUtils.del(RedisKeyPrefixConstants.UID_IDX_TOKEN + headerBean.getFrom() + oldToken);
        redisAccessUtils.del(RedisKeyPrefixConstants.TOKEN_IDX_UID + headerBean.getFrom() + uid);

        redisAccessUtils.set(RedisKeyPrefixConstants.TOKEN_IDX_UID + headerBean.getFrom() + uid,
                token,
                TimeConstants.ONE_HOUR,
                TimeUnit.SECONDS);

        redisAccessUtils.set(RedisKeyPrefixConstants.UID_IDX_TOKEN + headerBean.getFrom() + token,
                uid,
                TimeConstants.ONE_HOUR,
                TimeUnit.SECONDS);

        return LoginResponse.builder().token(String.valueOf(token)).build();

    }

    @Override
    public void logout()
            throws IllegalAccessException {
        HeaderBean header = HeaderUtils.mapHeaderParam(httpServletRequest);
        redisAccessUtils.del(RedisKeyPrefixConstants.UID_IDX_TOKEN +
                header.getFrom() +
                header.getToken());
    }

    @Override
    public UserOrgRoleInfo info()
            throws IllegalAccessException, CustomException {
        HeaderBean header = HeaderUtils.mapHeaderParam(httpServletRequest);
        Long uid = (Long) redisAccessUtils.get(RedisKeyPrefixConstants.UID_IDX_TOKEN +
                header.getFrom() +
                header.getToken());
        ExceptionDecider.ifNull(uid, ResponseCodeEnum.TOKEN_INVALID);
        return dataFeignService.getUserById(uid);
    }

}