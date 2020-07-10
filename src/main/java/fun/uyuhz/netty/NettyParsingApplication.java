package fun.uyuhz.netty;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("fun.uyuhz")
public class NettyParsingApplication {

    public static void main(String[] args) {
        SpringApplication.run(NettyParsingApplication.class, args);
    }
}
