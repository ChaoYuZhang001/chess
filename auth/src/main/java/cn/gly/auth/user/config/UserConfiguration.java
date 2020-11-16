package cn.gly.auth.user.config;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Configuration;

@Configuration
@MapperScan("cn.gly.auth.user.dao")
public class UserConfiguration {
}
