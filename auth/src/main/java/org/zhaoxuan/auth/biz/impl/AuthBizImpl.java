package org.zhaoxuan.auth.biz.impl;

import cn.hutool.core.lang.Snowflake;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.zhaoxuan.auth.biz.AuthBiz;
import org.zhaoxuan.common.constants.RedisKeyPrefixConstants;
import org.zhaoxuan.common.constants.TimeConstants;
import org.zhaoxuan.common.exception.*;
import org.zhaoxuan.common.utils.*;
import org.zhaoxuan.pojo.bean.HeaderBean;
import org.zhaoxuan.pojo.request.auth.LoginRequest;
import org.zhaoxuan.pojo.request.auth.LogoutRequest;
import org.zhaoxuan.pojo.response.auth.LoginResponse;
import org.zhaoxuan.pojo.response.auth.VerifyCodeResponse;
import org.zhaoxuan.pojo.response.user.UserOrgRoleInfo;
import org.zhaoxuan.remote_call.feign.service.data.user.*;

import java.util.concurrent.TimeUnit;

@Service
@ComponentScan(includeFilters = @ComponentScan.Filter(type = FilterType.ANNOTATION, classes = FeignClient.class))
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class AuthBizImpl implements AuthBiz {

    private final HttpServletRequest httpServletRequest;

    @Resource
    private RedisAccessUtils redisAccessUtils;
    @Resource
    private Snowflake snowflake;
    @Resource
    private DataOrgFeignService orgFeignService;
    @Resource
    private DataRoleFeignService roleFeignService;
    @Resource
    private DataUserFeignService userFeignService;

    @Override
    public VerifyCodeResponse verifyCode() {
        long id = snowflake.nextId();
        String randStr = ImageUtils.getRandomString(4);
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
    public LoginResponse login(@RequestBody @Validated LoginRequest request)
            throws CustomException, IllegalAccessException {

        String verifyCode = (String) redisAccessUtils.get(request.getCodeId());
        ExceptionDecider.ifTrue(ObjectUtils.isEmpty(request.getCode()) ||
                        !request.getCode().equals(verifyCode),
                ResponseCodeEnum.VERIFY_CODE_INVALID);

        long id = userFeignService.checkPasswd(request.getAccount(), request.getPassword());
        UserOrgRoleInfo user = userFeignService.getById(id);

        long token = snowflake.nextId();
        HeaderBean header = HeaderUtils.mapHeaderParam(httpServletRequest);
        redisAccessUtils.set(RedisKeyPrefixConstants.USER_ID_IDX_TOKEN + header.getFrom() + token,
                token,
                TimeConstants.ONE_HOUR,
                TimeUnit.SECONDS);

        return LoginResponse.builder().token(String.valueOf(token)).build();

    }

    @Override
    public void logout(@RequestBody @Validated LogoutRequest request)
            throws IllegalAccessException {
        HeaderBean header = HeaderUtils.mapHeaderParam(httpServletRequest);
        redisAccessUtils.del(RedisKeyPrefixConstants.USER_ID_IDX_TOKEN +
                header.getFrom() +
                header.getToken());
    }

    @Override
    public UserOrgRoleInfo info()
            throws IllegalAccessException, CustomException {
        HeaderBean header = HeaderUtils.mapHeaderParam(httpServletRequest);
        Long uid = (Long) redisAccessUtils.get(RedisKeyPrefixConstants.VERIFY_CODE_KEY_PREFIX +
                header.getFrom() +
                header.getToken());
        ExceptionDecider.ifNull(uid, ResponseCodeEnum.TOKEN_INVALID);
        return userFeignService.getById(uid);
    }

}