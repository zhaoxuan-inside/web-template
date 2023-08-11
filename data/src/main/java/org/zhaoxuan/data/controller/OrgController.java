package org.zhaoxuan.data.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.pojo.entity.user.OrgEntity;
import org.zhaoxuan.pojo.request.PageRequest;
import org.zhaoxuan.pojo.request.PageResponse;
import org.zhaoxuan.pojo.request.user.BatchAddRequest;
import org.zhaoxuan.pojo.request.user.IdsRequest;
import org.zhaoxuan.pojo.request.user.OrgRequest;

// @Api(tags = "组织数据管理")
@RestController
@RequestMapping("/org")
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class OrgController {

    // @ApiOperation("分页获取org表数据")
    @GetMapping("/page")
    public PageResponse<OrgEntity> orgPage(
            @RequestParam PageRequest page,
            @RequestParam OrgRequest orgParam) {
        return null;
    }

    // @ApiOperation("添加组织")
    @PostMapping("")
    public void addOrg(@RequestBody BatchAddRequest<OrgEntity> request) {

    }

    // @ApiOperation("删除组织")
    @DeleteMapping("")
    public void removeOrg(@RequestBody IdsRequest request) {

    }

    // @ApiOperation("修改组织")
    @PutMapping("")
    public void modifyOrg(@RequestBody BatchAddRequest<OrgEntity> request) {

    }

}
