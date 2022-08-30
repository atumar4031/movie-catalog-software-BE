package link.attech.moviecatalog;

import link.attech.moviecatalog.config.filter.JwtAuthenticationFilter;
import link.attech.moviecatalog.entity.Role;
import link.attech.moviecatalog.entity.User;
import link.attech.moviecatalog.repository.UserRepository;
import link.attech.moviecatalog.request.UserRequest;
import link.attech.moviecatalog.request.UserToRole;
import link.attech.moviecatalog.response.SecurityContextUser;
import link.attech.moviecatalog.service.RoleService;
import link.attech.moviecatalog.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableEurekaClient
public class MovieCatalogApplication {
    @Autowired
    private UserRepository userRepository;
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public JwtAuthenticationFilter getJwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    public static void main(String[] args) {
        SpringApplication.run(MovieCatalogApplication.class, args);
    }
    @Bean
    public WebMvcConfigurer conpigure(){
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry reg) {
                reg.addMapping("/**").allowedOrigins("*");
            }
        };
    }

//    @Bean
//    CommandLineRunner run(UserService userService, RoleService roleService) {
//        return args -> {
//            roleService.addRole(new Role(null, "ROLE_USER"));
//            roleService.addRole(new Role(null, "ROLE_ADMIN"));
//            roleService.addRole(new Role(null, "ROLE_MANAGER"));
//            roleService.addRole(new Role(null, "ROLE_SUPER_ADMIN"));
//
//            userService.userRegistration(new UserRequest("user@gmail.com", "user", "user"));
//            userService.userRegistration(new UserRequest("admin@gmail.com", "admin", "admin"));
//            userService.userRegistration(new UserRequest("manager@gmail.com", "manager", "manager"));
//
//            roleService.addRoleToUser(new UserToRole("user@gmail.com","ROLE_USER"));
//            roleService.addRoleToUser(new UserToRole("admin@gmail.com","ROLE_ADMIN"));
//            roleService.addRoleToUser(new UserToRole("manager@gmail.com","ROLE_MANAGER"));
//            roleService.addRoleToUser(new UserToRole("manager@gmail.com","ROLE_SUPER_ADMIN"));
//        };
//    }

}
