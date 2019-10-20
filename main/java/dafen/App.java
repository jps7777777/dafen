package dafen;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * Hello world!
 */
//@EnableAutoConfiguration(exclude = {DataSourceAutoConfiguration.class})
@SpringBootApplication(scanBasePackages = {"dafen"})
@MapperScan("dafen.dao")
@RestController
public class App {

    @RequestMapping("/hello")
    @ResponseBody
    public String method(){
        return "good idea";
    }


    public static void main(String[] args) {
        System.out.println("Hello World!");
        SpringApplication.run(App.class,args);
    }


}
