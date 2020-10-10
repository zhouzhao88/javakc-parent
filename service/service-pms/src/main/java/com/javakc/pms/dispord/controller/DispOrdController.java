package com.javakc.pms.dispord.controller;

import com.javakc.commonutils.api.APICODE;
import com.javakc.pms.dispord.entity.DispOrd;
import com.javakc.pms.dispord.service.DispOrdService;
import com.javakc.pms.dispord.vo.DispOrdQuery;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Api(value = "调度指令库管理")
@RestController
@RequestMapping("/pms/dispord")
public class DispOrdController {

    @Autowired
    private DispOrdService dispOrdService;


    @ApiOperation(value = "查询所有指令")
    @GetMapping
    public APICODE findAll(){
        List<DispOrd> list = dispOrdService.findAll();
        return APICODE.OK().data("items",list);
    }



    @ApiOperation(value = "带条件的分页查询")
    @GetMapping("{pageNo}/{pageSize}")
    public APICODE findPageDispOrd(DispOrdQuery dispOrdQuery, @PathVariable(name = "pageNo") int pageNo,@PathVariable(name = "pageSize") int pageSize){

        Page<DispOrd> page = dispOrdService.findPageDispOrd(dispOrdQuery, pageNo, pageSize);

        //总条数
        long totalElements = page.getTotalElements();
        //所有的数据
        List<DispOrd> list = page.getContent();

        return APICODE.OK().data("total",totalElements).data("items",list);
    }


    @ApiOperation(value = "新增调度指令库")
    @PostMapping("saveDispOrd")
    public APICODE saveDispOrd(@RequestBody DispOrd dispOrd){
        dispOrdService.saveOrUpdate(dispOrd);
        return APICODE.OK();
    }


    @ApiOperation(value = "根据id查询数据")
    @GetMapping("{id}")
    public APICODE getDispOrdById(@PathVariable(name = "id") String id){
        DispOrd dispOrd = dispOrdService.getById(id);
        return APICODE.OK().data("dispOrd",dispOrd);
    }

    @ApiOperation(value = "根据id修改调度指令库数据")
    @PutMapping
    public APICODE updateDispOrd(@RequestBody DispOrd dispOrd){
        dispOrdService.saveOrUpdate(dispOrd);
        return APICODE.OK();
    }


    @ApiOperation(value = "根据id删除数据")
    @DeleteMapping("{id}")
    public APICODE daleteDispOrd(@PathVariable(name = "id") String id){
        dispOrdService.removeById(id);
        return APICODE.OK();
    }
}
