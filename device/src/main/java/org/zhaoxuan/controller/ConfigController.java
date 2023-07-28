package org.zhaoxuan.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.business.in.*;
import org.zhaoxuan.business.out.GetConfigResponse;

@Api("配置管理")
@RestController
@RequestMapping("config")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ConfigController {

    @GetMapping()
    @ApiOperation("获取配置")
    public GetConfigResponse getConfig(@RequestParam GetConfigRequest request) {
        return null;
    }

    @PostMapping()
    @ApiOperation("添加配置")
    public void addConfig(@RequestBody AddConfigRequest request) {

    }

    @PutMapping()
    @ApiOperation("修改配置")
    public void modifyConfig(@RequestBody ModifyConfigRequest request) {

    }

    @DeleteMapping()
    @ApiOperation("修改配置")
    public void removeConfig(@RequestBody RemoveConfigRequest request) {

    }

}
