package org.zhaoxuan.device.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.pojo.request.device.ConnectRequest;

@Tag(name = "连接管理")
@RestController
@RequestMapping("/connect")
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ConnectController {

    @PostMapping()
    @Operation(description = "激活连接")
    public void active(@RequestBody ConnectRequest request) {

    }

    @DeleteMapping()
    @Operation(description = "激活连接")
    public void inactive(@RequestBody ConnectRequest request) {

    }

}
