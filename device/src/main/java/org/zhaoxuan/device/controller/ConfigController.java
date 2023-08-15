package org.zhaoxuan.device.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.pojo.request.device.*;
import org.zhaoxuan.pojo.response.device.GetConfigResponse;

@Tag(name = "配置管理")
@RestController
@RequestMapping("/config")
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ConfigController {

    @GetMapping()
    @Operation(description = "获取配置")
    public GetConfigResponse getConfig(@RequestParam GetConfigRequest request) {
        return null;
    }

    @PostMapping()
    @Operation(description = "添加配置")
    public void addConfig(@RequestBody AddConfigRequest request) {

    }

    @PutMapping()
    @Operation(description = "修改配置")
    public void modifyConfig(@RequestBody ModifyConfigRequest request) {

    }

    @DeleteMapping()
    @Operation(description = "修改配置")
    public void removeConfig(@RequestBody RemoveConfigRequest request) {

    }

}
