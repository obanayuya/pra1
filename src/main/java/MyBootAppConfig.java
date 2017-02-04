import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import test.domain.model.entity.MyData;

@Configuration
public class MyBootAppConfig {

    @Bean
    MyDataBean myDataBean(){
        return new MyDataBean();
    }

}
