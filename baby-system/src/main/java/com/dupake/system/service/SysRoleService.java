package com.dupake.system.service;

import com.dupake.common.message.CommonPage;
import com.dupake.common.message.CommonResult;
import com.dupake.common.pojo.dto.req.role.RoleAddRequest;
import com.dupake.common.pojo.dto.req.role.RoleQueryRequest;
import com.dupake.common.pojo.dto.req.role.RoleUpdateRequest;
import com.dupake.common.pojo.dto.res.system.MenuDTO;
import com.dupake.common.pojo.dto.res.system.RoleDTO;
import com.dupake.system.entity.SysRole;

import java.util.List;

/**
 * <p>
 * 角色表 服务类
 * </p>
 *
 * @author dupake
 * @since 2020-06-08
 */
public interface SysRoleService {

    SysRole selectByName(String roleName);

    List<SysRole> findUserRoles(Long id);

    List<MenuDTO> getMenuListByUserId(Long id);

    CommonResult<CommonPage<RoleDTO>> listByPage(RoleQueryRequest roleQueryRequest);

    CommonResult addRole(RoleAddRequest roleAddRequest);

    CommonResult updateRole(RoleUpdateRequest roleUpdateRequest);

    CommonResult deleteRole(List<Long> ids);

    CommonResult<List<RoleDTO>> listAll();

    CommonResult<List<RoleDTO>> listRoleByUserId(Long userId);

    CommonResult allocMenu(Long roleId, List<Long> menuIds);
}