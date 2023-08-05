package org.zhaoxuan.remote_call.feign.service.data.user;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.common.Beans.feign.in.*;
import org.zhaoxuan.common.entity.user.RoleEntity;
import org.zhaoxuan.common.request.*;
import org.zhaoxuan.remote_call.feign.interceptor.FeignInterceptor;

@RefreshScope
@RequestMapping("/data/role")
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
