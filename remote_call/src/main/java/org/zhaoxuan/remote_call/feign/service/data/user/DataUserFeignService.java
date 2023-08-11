package org.zhaoxuan.remote_call.feign.service.data.user;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.pojo.entity.user.UserEntity;
import org.zhaoxuan.pojo.request.PageRequest;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.*;
import org.zhaoxuan.remote_call.feign.interceptor.FeignInterceptor;

@RefreshScope
@RequestMapping("/data/user")
@SuppressWarnings("unused")
@FeignClient(name = "data-user", configuration = FeignInterceptor.class)
public interface DataUserFeignService {

    @GetMapping(value = "/page",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    PageResponse<UserEntity> userPage(
            @RequestParam PageRequest page,
            @RequestParam UserRequest userParam);

    @GetMapping(value = "/page",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    PageResponse<UserEntity> userRolePage(
            @RequestParam PageRequest page,
            @RequestParam UserRequest userParam,
            @RequestParam RoleRequest roleParam);

    @GetMapping(value = "/org/page",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    PageResponse<UserEntity> userOrgPage(
            @RequestParam PageRequest page,
            @RequestParam UserRequest userParam,
            @RequestParam OrgRequest orgParam);

    @GetMapping(value = "/org/role/page",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    PageResponse<UserEntity> userOrgRolePage(
            @RequestParam PageRequest page,
            @RequestParam UserRequest userParam,
            @RequestParam RoleRequest roleParam,
            @RequestParam OrgRequest orgParam);

    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void addUser(@RequestBody BatchAddRequest<UserEntity> request);

    @DeleteMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void removeUser(@RequestBody IdsRequest request);

    @PutMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void modifyUser(@RequestBody BatchAddRequest<UserEntity> request);

}
