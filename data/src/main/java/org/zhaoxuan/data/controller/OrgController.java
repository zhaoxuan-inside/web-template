package org.zhaoxuan.data.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.data.biz.OrgBiz;
import org.zhaoxuan.pojo.entity.user.OrgEntity;
import org.zhaoxuan.pojo.request.PageParamRequest;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.BatchRequest;
import org.zhaoxuan.pojo.request.user.OrgRequest;

import java.util.List;

@Tag(name = "组织数据管理")
@RestController
@RequestMapping("/org")
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class OrgController {

    private final OrgBiz orgBiz;

    @Operation(description = "添加组织")
    @PostMapping("")
    public void addOrg(@RequestBody BatchRequest<OrgEntity> request) {
        orgBiz.addOrg(request);
    }

    @Operation(description = "删除组织")
    @DeleteMapping("")
    public void removeOrg(@RequestBody BatchRequest<Long> request) {
        orgBiz.removeOrg(request);
    }

    @Operation(description = "修改组织")
    @PutMapping("")
    public void modifyOrg(@RequestBody BatchRequest<OrgEntity> request) {
        orgBiz.modifyOrg(request);
    }

    @Operation(description = "分页获取org表数据")
    @GetMapping("")
    public PageResponse<List<OrgEntity>> orgPage(
            @RequestParam(required = false) PageParamRequest page,
            @RequestParam(required = false) OrgRequest orgParam) {
        return orgBiz.orgPage(page, orgParam);
    }

}
