package org.zhaoxuan.remote_call.feign.service.data.user;

import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.pojo.entity.user.OrgEntity;
import org.zhaoxuan.pojo.request.PageRequest;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.BatchAddRequest;
import org.zhaoxuan.pojo.request.user.IdsRequest;
import org.zhaoxuan.pojo.request.user.OrgRequest;
import org.zhaoxuan.remote_call.feign.interceptor.FeignInterceptor;

@RefreshScope
@RequestMapping("/data/org")
@SuppressWarnings("unused")
@FeignClient(name = "data-org", configuration = FeignInterceptor.class)
public interface DataOrgFeignService {
    @GetMapping(value = "/page",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    PageResponse<OrgEntity> orgPage(
            @RequestParam PageRequest page,
            @RequestParam OrgRequest orgParam);

    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void addOrg(@RequestBody BatchAddRequest<OrgEntity> request);

    @DeleteMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void removeOrg(@RequestBody IdsRequest request);

    @PutMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    void modifyOrg(@RequestBody BatchAddRequest<OrgEntity> request);

}
