package hello.core.scan.filter;

import java.lang.annotation.*;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyExcludeComponent {
    //MyIncludeComponent 어노테이션이 붙으면 컴포넌트 스캔에 제외할거야.
}
