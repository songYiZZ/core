package hello.core.scan.filter;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.FilterType;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.context.annotation.ComponentScan.*;

public class ComponentFilterAppConfigTest {

    @Test
    void filterSan(){
        ApplicationContext ac = new AnnotationConfigApplicationContext(ComponentFilterAppConfig.class);
        BeanA beanA = ac.getBean("beanA", BeanA.class);
        Assertions.assertThat(beanA).isNotNull();

        //BeanB beanB = ac.getBean("beanB", BeanB.class); //excludeFilter설정으로 제외시켜서 조회시 에러
        assertThrows(NoSuchBeanDefinitionException.class,
                () -> ac.getBean("beanB",BeanB.class));
    }

    @Configuration
    @ComponentScan(
            includeFilters = @Filter(type= FilterType.ANNOTATION,classes = MyIncludeComponent.class),
            excludeFilters = @Filter(type= FilterType.ANNOTATION,classes = MyExcludeComponent.class)
    )
    static class ComponentFilterAppConfig{

    }
}
