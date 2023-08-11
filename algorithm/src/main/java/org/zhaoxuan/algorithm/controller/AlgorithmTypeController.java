package org.zhaoxuan.algorithm.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "算法 - 算法类型1")
@RestController()
@RequestMapping("/algorithm-type")
@SuppressWarnings("unused")
@RequiredArgsConstructor(onConstructor = @__({@Autowired}))
public class AlgorithmTypeController {

}
