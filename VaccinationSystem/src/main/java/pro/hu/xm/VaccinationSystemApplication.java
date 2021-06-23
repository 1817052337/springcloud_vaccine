package pro.hu.xm;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class VaccinationSystemApplication {

    public static void main(String[] args) {
        SpringApplication.run(VaccinationSystemApplication.class, args);
    }

}
