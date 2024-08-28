package com.geeke.sys.controller;

import com.alibaba.fastjson.JSONObject;

import com.geeke.common.controller.SearchParams;
import com.geeke.common.data.Page;
import com.geeke.sys.controller.BaseController;
import com.geeke.sys.entity.PropertySet;
import com.geeke.sys.service.PropertySetService;
import com.geeke.utils.ResultUtil;
import com.geeke.utils.StringUtils;
import java.util.List;

import io.swagger.annotations.*;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 属性集Controller
 * @author
 * @version
 */
@Api(value = "/sys/propertySet", tags = {"属性集Controller"})
@RestController

@RequestMapping(value = "/sys/propertySet")
public class PropertySetController extends BaseController {

    @Autowired
    private PropertySetService propertySetService;

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "path", dataType = "string", name = "id", value = "", required = true)
    })
    @ApiOperation(value = "通过id获取属性集", notes = "通过id获取属性集", httpMethod = "GET",response = PropertySet.class)
    @GetMapping("/{id}")
    public ResponseEntity<JSONObject> getById(@PathVariable("id") String id) {
        PropertySet entity = propertySetService.get(id);

        return ResponseEntity.ok(ResultUtil.successJson(entity));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "SearchParams", name = "searchParams", value = "", required = true)
    })
    @ApiOperation(value = "通过条件获取属性集（分页）", notes = "通过条件获取属性集（分页）", httpMethod = "POST",response = PropertySet.class)
    @PostMapping(value = { "list", "" })
    public ResponseEntity<JSONObject> listPage(@RequestBody SearchParams searchParams) {
        Page<PropertySet> result = propertySetService.listPage(
            searchParams.getParams(),
            searchParams.getOffset(),
            searchParams.getLimit(),
            searchParams.getOrderby()
        );

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "SearchParams", name = "searchParams", value = "", required = true)
    })
    @ApiOperation(value = "通过条件获取属性集", notes = "通过条件获取属性集", httpMethod = "POST",response = PropertySet.class,responseContainer = "List")
    @PostMapping(value = "listAll")
    public ResponseEntity<JSONObject> listAll(@RequestBody SearchParams searchParams) {
        List<PropertySet> result = propertySetService.listAll(searchParams.getParams(), searchParams.getOrderby());

        return ResponseEntity.ok(ResultUtil.successJson(result));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "PropertySet", name = "entity", value = "", required = true)
    })
    @ApiOperation(value = "保存属性集", notes = "保存属性集，并且返回id", httpMethod = "POST",response=String.class)
    @PostMapping(value = "save")
    public ResponseEntity<JSONObject> save(@RequestBody PropertySet entity) {
        String id = propertySetService.save(entity).getId();
        return ResponseEntity.ok(ResultUtil.successJson(id));
    }

    @ApiImplicitParams({
            @ApiImplicitParam(paramType = "body", dataType = "PropertySet", name = "entity", value = "", required = true)
    })
    @ApiOperation(value = "删除属性集", notes = "删除属性集，并且返回删除条数", httpMethod = "POST",response = Integer.class)
    @PostMapping(value = "delete")
    public ResponseEntity<JSONObject> delete(@RequestBody PropertySet entity) {
        int rows = propertySetService.delete(entity);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }


    @ApiOperation(value = "批量添加属性集", notes = "批量添加属性集，并且返回ids（list）", httpMethod = "POST",response=String.class,responseContainer = "List")
    @PostMapping(value = "bulkInsert")
    public ResponseEntity<JSONObject> bulkInsert(@RequestBody @ApiParam() List<PropertySet> entitys) {
        List<String> ids = propertySetService.bulkInsert(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }


    @ApiOperation(value = "批量更新属性集", notes = "批量更新属性集，并且返回ids（list）", httpMethod = "POST",response=String.class,responseContainer = "List")
    @PostMapping(value = "bulkUpdate")
    public ResponseEntity<JSONObject> bulkUpdate(@RequestBody @ApiParam() List<PropertySet> entitys) {
        List<String> ids = propertySetService.bulkUpdate(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(ids));
    }


    @ApiOperation(value = "批量删除属性集", notes = "批量删除属性集，并且返回删除条数", httpMethod = "POST",response= Integer.class)
    @PostMapping(value = "bulkDelete")
    public ResponseEntity<JSONObject> bulkDelete(@RequestBody @ApiParam() List<PropertySet> entitys) {
        int rows = propertySetService.bulkDelete(entitys);
        return ResponseEntity.ok(ResultUtil.successJson(rows));
    }
}