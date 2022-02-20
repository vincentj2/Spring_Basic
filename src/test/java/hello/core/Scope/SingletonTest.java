package hello.core.Scope;

import org.junit.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Scope;

import javax.annotation.PostConstruct;
import javax.annotation.PreDestroy;

import static org.assertj.core.api.Assertions.*;

public class SingletonTest {

    @Test
    public void singletonBeanFind(){
        AnnotationConfigApplicationContext ac = new AnnotationConfigApplicationContext(singletonBean.class);
        singletonBean singletonBean1 = ac.getBean(singletonBean.class);
        singletonBean singletonBean2 = ac.getBean(singletonBean.class);
        System.out.println("singletonBean1 = " + singletonBean1);
        System.out.println("singletonBean2 = " + singletonBean2);
        assertThat(singletonBean1).isSameAs(singletonBean2);

        ac.close();
    }

    @Scope("singleton")
    static class singletonBean {
        
        @PostConstruct
        public void init(){
            System.out.println("SingletonBean.init");
        }
        
        @PreDestroy
        public void destroy(){
            System.out.println("SingletonBean.destory");
        }
    }
}
