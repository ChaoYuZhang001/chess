package cn.gly.auth.user.dao;

import cn.gly.auth.user.model.dto.MinimumPermissionDTO;
import cn.gly.auth.user.model.dto.MinimumRoleDTO;
import cn.gly.auth.user.model.dto.MinimumUserDTO;
import cn.gly.auth.user.model.dto.RolePermissionPO;
import cn.gly.auth.user.model.po.UserPO;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Optional;

public interface UserDao {

    Optional<UserPO> findUserByUserName(@Param("userName") String userName);

    Optional<UserPO> findUserByUserId(@Param("userId") Long userId);

    Optional<MinimumUserDTO> findMinimumUser(@Param("userName") String userName);

    List<MinimumPermissionDTO> findUserMinimumPermission(@Param("userId") Long userId);

    List<MinimumPermissionDTO> findAllMinimumPermission();

    List<MinimumRoleDTO> findAllMinimumRoleDTO();

    List<RolePermissionPO> findAllRolePermissionRelation();

    List<MinimumRoleDTO> findUserMinimumRole(Long userId);
}
