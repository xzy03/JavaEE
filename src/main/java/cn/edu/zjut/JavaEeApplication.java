package cn.edu.zjut;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("cn.edu.zjut.mapper")
public class JavaEeApplication {

    public static void main(String[] args) {
        SpringApplication.run(JavaEeApplication.class, args);
    }

}
