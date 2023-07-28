package org.zhaoxuan.controller;

import io.swagger.annotations.Api;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Api(tags = "组织管理")
@RestController
@RequestMapping("/organization")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class OrganizationController {



}
