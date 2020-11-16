package cn.gly.auth.security.service;

import cn.gly.auth.security.exception.SecurityAuthException;
import cn.gly.auth.security.model.dto.AuthenticationUser;
import cn.gly.auth.user.model.dto.MinimumRoleDTO;
import cn.gly.auth.user.model.dto.MinimumUserDTO;
import cn.gly.auth.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class AuthenticationUserService implements UserDetailsService {

    @Autowired
    private UserService userService;

    /**
     * 这里返回的用户最终会和{@link org.springframework.security.authentication.UsernamePasswordAuthenticationToken}
     * 这个里面的用户名密码进行比对，如果成功了就走 success handler 失败反之
     *
     * @param username 用户名
     * @return
     * @throws UsernameNotFoundException
     */
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        MinimumUserDTO user = userService.findMinimumUser(username)
                .orElseThrow(() -> new SecurityAuthException("User not found!"));

        List<MinimumRoleDTO> roles = userService.findUserMinimumRole(user.getUserId());


        ArrayList authorities = roles
                .stream()
                .map(per -> new SimpleGrantedAuthority(per.getEnName()))
                .collect(Collectors.toCollection(() -> new ArrayList(roles.size())));

        return AuthenticationUser.from(user).setAuthorities(authorities);
    }
}
