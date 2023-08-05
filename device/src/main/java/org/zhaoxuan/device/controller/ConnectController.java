package org.zhaoxuan.device.controller;

import io.swagger.annotations.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.zhaoxuan.device.business.in.ConnectRequest;

// @Api("连接管理")
@RestController
@RequestMapping("connect")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class ConnectController {

    @PostMapping()
    // @ApiOperation("激活连接")
    public void active(@RequestBody ConnectRequest request) {

    }

    @DeleteMapping()
    // @ApiOperation("激活连接")
    public void inactive(@RequestBody ConnectRequest request) {

    }

}
