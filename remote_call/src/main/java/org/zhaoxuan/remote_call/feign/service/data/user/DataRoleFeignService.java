package org.zhaoxuan.remote_call.feign.service.data.user;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.pojo.entity.user.RoleEntity;
import org.zhaoxuan.pojo.request.PageRequest;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.BatchAddRequest;
import org.zhaoxuan.pojo.request.user.RoleRequest;
import org.zhaoxuan.remote_call.feign.interceptor.FeignInterceptor;

@Component
@RefreshScope
@SuppressWarnings("unused")
@FeignClient(name = "data-role", configuration = FeignInterceptor.class)
public interface DataRoleFeignService {

    @GetMapping(value = "/page",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    PageResponse<RoleEntity> rolePage(
            @RequestParam PageRequest page,
            @RequestParam RoleRequest roleParam);

    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void addRole(@RequestBody BatchAddRequest<RoleEntity> request);

    @DeleteMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void removeRole(@RequestBody BatchAddRequest<RoleEntity> request);

    @PutMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void modifyRole(@RequestBody BatchAddRequest<RoleEntity> request);

}
