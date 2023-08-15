package org.zhaoxuan.device.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.pojo.request.device.*;

@Tag(name = "内容管理")
@RestController
@RequestMapping("/content")
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ContentController {

    @PostMapping()
    @Operation(description = "添加内容")
    public void addContent(@RequestBody AddContentRequest<String> request) {

    }

    @DeleteMapping()
    @Operation(description = "删除内容")
    public void addContent(@RequestBody DelContentRequest<Long> request) {

    }

    @DeleteMapping()
    @Operation(description = "修改内容")
    public void modifyContent(@RequestBody AddContentRequest<Long> request) {

    }

    @DeleteMapping()
    @Operation(description = "查询内容")
    public void addContent(@RequestBody GetContentRequest<Long> request) {

    }

}
