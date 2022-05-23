package com.root.affixes.controller.graph;

import com.root.affixes.service.entity.Developer;
import com.root.affixes.service.entity.Person;
import com.root.affixes.service.service.GraphDataService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author jc
 */
@RestController
@RequestMapping("/api/graph")
public class GraphDataController {

    private final GraphDataService graphDataService;

    @Autowired
    public GraphDataController(GraphDataService graphDataService) {
        this.graphDataService = graphDataService;
    }

    @ApiOperation(value = "创建person")
    @PostMapping(value = "/persion")
    public Person create(
            @ApiParam(value = "person", required = true) @RequestBody @Validated Person person) {
        return graphDataService.createData();
    }

    @ApiOperation(value = "创建dev")
    @PostMapping(value = "/dev")
    public Developer create(
            @ApiParam(value = "dev", required = true) @RequestBody @Validated Developer developer) {
        return graphDataService.createDev();
    }


}
