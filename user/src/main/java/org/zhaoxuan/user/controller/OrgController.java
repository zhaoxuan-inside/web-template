package org.zhaoxuan.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.pojo.entity.user.OrgEntity;
import org.zhaoxuan.pojo.request.PageParamRequest;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.BatchRequest;
import org.zhaoxuan.pojo.request.user.OrgRequest;
import org.zhaoxuan.user.biz.OrgBiz;

import java.util.List;

@Tag(name = "组织管理")
@RestController
@RequestMapping("/org")
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class OrgController {

    private final OrgBiz orgBiz;

    @PostMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void addOrg(@RequestBody BatchRequest<OrgEntity> request) {
        orgBiz.addOrg(request);
    }

    @DeleteMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void removeOrg(@RequestBody BatchRequest<Long> request) {
        orgBiz.removeOrg(request);
    }

    @PutMapping(value = "",
            consumes = MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public void modifyOrg(@RequestBody BatchRequest<OrgEntity> request) {
        orgBiz.modifyOrg(request);
    }

    @GetMapping(value = "")
    public PageResponse<List<OrgEntity>> orgPage(
            @RequestParam(required = false) PageParamRequest page,
            @RequestParam(required = false) OrgRequest orgParam) {
        return orgBiz.orgPage(page, orgParam);
    }

}
