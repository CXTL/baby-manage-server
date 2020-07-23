package com.dupake.system.controller;


import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.role.RoleAddRequest;
import com.dupake.common.pojo.dto.req.role.RoleQueryRequest;
import com.dupake.common.pojo.dto.req.role.RoleUpdateRequest;
import com.dupake.common.pojo.dto.req.role.RoleAddRequest;
import com.dupake.common.pojo.dto.req.role.RoleQueryRequest;
import com.dupake.common.pojo.dto.req.role.RoleUpdateRequest;
import com.dupake.common.pojo.dto.res.RoleDTO;
import com.dupake.system.service.SysRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.validation.Valid;
import java.util.List;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author dupake
 * @since 2020-06-08
 */
@Api(tags = "系统：角色管理")
@RestController
@RequestMapping("/api/roles")
public class SysRoleController {


    @Resource
    private SysRoleService sysRoleService;


    @ApiOperation("分页查询菜单列表")
    @PostMapping(value = "/listByPage")
    public CommonResult<CommonPage<RoleDTO>> listByPage(@Valid @RequestBody RoleQueryRequest roleQueryRequest) {
        return sysRoleService.listByPage(roleQueryRequest);
    }


    @ApiOperation("新增指定菜单信息")
    @PostMapping(value = "/addRole")
    public CommonResult addRole(@Valid @RequestBody RoleAddRequest roleAddRequest) {
        return sysRoleService.addRole(roleAddRequest);
    }



    @ApiOperation("修改指定菜单信息")
    @PostMapping(value = "/updateRole")
    public CommonResult updateRole(@Valid @RequestBody RoleUpdateRequest roleUpdateRequest) {
        return sysRoleService.updateRole(roleUpdateRequest);
    }


    @ApiOperation("删除指定菜单信息")
    @PostMapping(value = "/delete")
    public CommonResult delete(@RequestParam(value = "roleId") Long roleId) {
        return sysRoleService.delete(roleId);
    }


    @ApiOperation("批量删除菜单信息")
    @PostMapping(value = "/deleteBatch")
    public CommonResult deleteBatch(@RequestParam(value = "ids") List<Long> ids) {
        return sysRoleService.deleteBatch(ids);
    }


}
