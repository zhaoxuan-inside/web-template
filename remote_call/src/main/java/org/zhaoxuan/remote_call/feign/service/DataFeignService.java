package org.zhaoxuan.remote_call.feign.service;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.pojo.entity.user.*;
import org.zhaoxuan.pojo.request.PageParamRequest;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.*;
import org.zhaoxuan.pojo.response.user.UserOrgRoleInfo;
import org.zhaoxuan.remote_call.feign.interceptor.FeignInterceptor;

import java.util.List;

@Component
@RefreshScope
@SuppressWarnings("unused")
@FeignClient(name = "discrete-industries-data", configuration = FeignInterceptor.class)
public interface DataFeignService {

    @Operation(description = "添加用户")
    @PostMapping("/data/user")
    void addUser(@RequestBody BatchRequest<UserEntity> request);

    @Operation(description = "删除用户")
    @DeleteMapping("/data/user")
    void removeUser(@RequestBody BatchRequest<Long> request);

    @Operation(description = "修改用户")
    @PutMapping("/data/user")
    void modifyUser(@RequestBody BatchRequest<UserEntity> request);

    @Operation(description = "检查密码")
    @GetMapping("/data/user/check/{account}")
    Long checkPasswd(
            @PathVariable String account,
            @RequestParam String passwd);

    @GetMapping(value = "/data/user/condition",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    PageResponse<List<UserOrgRoleInfo>> pageByConditions(@RequestParam(required = false) PageParamRequest page,
                                                         @RequestParam(required = false) UserRequest userParam,
                                                         @RequestParam(required = false) OrgRequest orgParam,
                                                         @RequestParam(required = false) RoleRequest roleParam);

    @GetMapping(value = "/data/user/{id}",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    UserOrgRoleInfo getUserById(@PathVariable long id);

    @PostMapping(value = "/data/role",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void addRole(@RequestBody BatchRequest<RoleEntity> request);

    @DeleteMapping(value = "/data/role",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void removeRole(@RequestBody BatchRequest<Long> request);

    @PutMapping(value = "/data/role",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void modifyRole(@RequestBody BatchRequest<RoleEntity> request);

    @Operation(description = "分页获取Role表数据")
    @GetMapping("/data/role")
    PageResponse<List<RoleEntity>> rolePage(
            @RequestParam(required = false) PageParamRequest page,
            @RequestParam(required = false) RoleRequest roleParam);

    @PostMapping(value = "/data/org",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void addOrg(@RequestBody BatchRequest<OrgEntity> request);

    @DeleteMapping(value = "/data/org",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void removeOrg(@RequestBody BatchRequest<Long> request);

    @PutMapping(value = "/data/org",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void modifyOrg(@RequestBody BatchRequest<OrgEntity> request);

    @GetMapping(value = "/data/org")
    PageResponse<List<OrgEntity>> orgPage(
            @RequestParam(required = false) PageParamRequest page,
            @RequestParam(required = false) OrgRequest orgParam);

}
