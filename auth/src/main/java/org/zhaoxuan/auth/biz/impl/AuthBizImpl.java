package org.zhaoxuan.user.auth.biz.impl;

import cn.hutool.core.lang.Snowflake;
import jakarta.annotation.Resource;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;
import org.zhaoxuan.user.auth.business.out.*;
import org.zhaoxuan.user.auth.feign.service.UserFeignService;
import org.zhaoxuan.user.Beans.HeaderBean;
import org.zhaoxuan.user.auth.biz.AuthBiz;
import org.zhaoxuan.user.auth.business.in.LoginRequest;
import org.zhaoxuan.auth.business.out.*;
import org.zhaoxuan.service.auth.business.out.*;
import org.zhaoxuan.user.exception.*;
import org.zhaoxuan.user.service.auth.business.out.*;
import org.zhaoxuan.user.auth.common.constants.RedisKeyPrefixConstants;
import org.zhaoxuan.user.business.user.business.out.UserDetailInfoResponse;
import org.zhaoxuan.user.constants.HeaderDefault;
import org.zhaoxuan.exception.*;
import org.zhaoxuan.service.exception.*;
import org.zhaoxuan.service.utils.*;
import org.zhaoxuan.user.service.exception.*;
import org.zhaoxuan.user.service.utils.*;
import org.zhaoxuan.user.utils.*;
import org.zhaoxuan.utils.*;

@Service
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class AuthBizImpl implements AuthBiz {

    @Resource
    private RedisAccessUtils redisAccessUtils;
    @Resource
    private UserFeignService userFeignService;
    private final Snowflake snowflake;
    private final HttpServletRequest httpServletRequest;

    @Override
    public VerifyCodeResponse verifyCode() {
        return null;
    }

    @Override
    public LoginResonse login(LoginRequest request) throws CustomException {
//
//        ExceptionDecider.ifTrue(!checkVerifyCode(request.getCode(), request.getAccount()),
//                ErrorResponseEnum.INVALID_VERIFY_CODE.getCode());
//
//        UserEntity user = userService.getUserByAccount(request.getAccount());
//        ExceptionDecider.ifTrue(!checkPassword(user, request.getPassword()),
//                ErrorResponseEnum.INVALID_PASSWORD.getCode());
//
//        long token = snowflake.nextId();
//
//        String infoIdxId = RedisKeyPrefixConstants.USER_INFO_IDX_UID + user.getId();
//        String userInfo = (String) redisUtil.get(infoIdxId);
//        if (ObjectUtils.isEmpty(userInfo)) {
//            redisUtil.set(infoIdxId, JsonUtils.objToStr(user));
//        }
//
//        HeaderBean headerBean = AnalyseRequestHeaderUtil.getHeaderBean(httpServletRequest);
//        redisUtil.set(getIdIdxTokenKey(String.valueOf(token), headerBean.getFrom()), infoIdxId,
//                TimeConstants.ONE_HOUR, TimeUnit.MILLISECONDS);

//        return LoginResonse.builder()
//                .token(token)
//                .build();
        return null;
    }

    private Boolean checkPassword(UserDetailInfoResponse user, String password) {

        // TODO 调用USER微服务获取用户数据

//        return !ObjectUtils.isEmpty(user.getPassword()) &&
//                !ObjectUtils.isEmpty(password) &&
//                user.getPassword().equals(password);
        return false;
    }

    private Boolean checkPassword(String account, String password) {

        // TODO 调用USER微服务获取用户数据
//        return !ObjectUtils.isEmpty(user.getPassword()) &&
//                !ObjectUtils.isEmpty(password) &&
//                user.getPassword().equals(password);
        return false;
    }

    private Boolean checkVerifyCode(String verifyCode, String account) {

        String key = RedisKeyPrefixConstants.VERIFY_CODE_KEY_PREFIX + account + verifyCode;
        String value = (String) redisAccessUtils.get(key);

        return !ObjectUtils.isEmpty(value) && verifyCode.equals(value);

    }

    @Override
    public void logout() {

        HeaderBean headerBean = AnalyseRequestHeaderUtil.getHeaderBean(httpServletRequest);
        String token = headerBean.getToken();
        String from = headerBean.getFrom();
        String key = getIdIdxTokenKey(token, from);

        redisAccessUtils.del(key);

    }

    @Override
    public LoginInfoResonse info() throws CustomException {

        HeaderBean headerBean = AnalyseRequestHeaderUtil.getHeaderBean(httpServletRequest);
        String token = headerBean.getToken();
        String from = headerBean.getFrom();
        String key = getIdIdxTokenKey(token, from);

        String value = (String) redisAccessUtils.get(key);
        ExceptionDecider.ifNull(value, ErrorResponseEnum.NOT_LOGIN.getCode());

        // TODO 调用USER微服务获取用户数据

        return LoginInfoResonse.builder().build();

    }

    private String getIdIdxTokenKey(String token, String from) {

        return RedisKeyPrefixConstants.USER_ID_IDX_TOKEN + token + ":" + (ObjectUtils.isEmpty(from) ? HeaderDefault.UNKNOW : from);

    }

}
