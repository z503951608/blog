package springcloud.club.blog.listenner;

import java.util.Arrays;

import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
/**
 * spring-boot启动之后的回调
 * @author admin
 *
 */
@Component
@Order(1)
@Slf4j
public class RunLoadCommandLineRunner implements CommandLineRunner{

    @Override
    public void run(String... args) throws Exception {
        log.info("-------------------------应用启动了------------------------" + Arrays.asList(args));
    }

}
