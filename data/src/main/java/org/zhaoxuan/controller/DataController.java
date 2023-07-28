package org.zhaoxuan.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "数据管理")
@RestController
@RequestMapping("/data")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class DataController {




}
