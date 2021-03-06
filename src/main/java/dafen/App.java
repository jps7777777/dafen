package dafen;

import dafen.utils.FuncUntils;
import io.swagger.annotations.ApiOperation;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

/**
 * Hello world!
 */
@SpringBootApplication(scanBasePackages = {"dafen"})
@MapperScan("dafen.dao")
@RestController
public class App extends SpringBootServletInitializer {

    @ApiOperation(value = "测试接口")
    @RequestMapping(value = "/hello",method = RequestMethod.GET)
    @ResponseBody
    public String method(){
        int times = FuncUntils.getTimesWithWeek();
        return "good idea:"+times;
    }

    /**
     * extends SpringBootServletInitializer
     * 目标定义：前端生产数据使用
     * 项目部署到服务器可以直接访问接口
     */
    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(App.class);
    }

    /**
     * 跨域过滤器
     * @return 返回结果
     */
    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.addAllowedOrigin("*");
        corsConfiguration.addAllowedHeader("*");
        corsConfiguration.addAllowedMethod("*");
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration); // 4
        return new CorsFilter(source);
    }

    public static void main(String[] args) {
        System.out.println("Hello World!");
        SpringApplication.run(App.class,args);
    }


}
