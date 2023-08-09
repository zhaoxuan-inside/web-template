package org.zhaoxuan.user.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Tag(name = "组织管理")
@RestController
@RequestMapping("/organization")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class OrganizationController {


}
