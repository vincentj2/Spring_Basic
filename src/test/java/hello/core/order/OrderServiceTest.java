package hello.core.order;


import hello.core.AppConfig_old;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class OrderServiceTest {

    MemberService memberService;
    OrderService orderService;

    @BeforeEach
    public void beforeEach(){
        AppConfig_old appConfig = new AppConfig_old();
        memberService = appConfig.memberService();
        orderService = appConfig.orderService();
    }


    @Test
    public void createOrder() throws Exception{
            //given
        Long memberId = 1L;
        Member member = new Member(memberId,"memberA", Grade.VIP);

            //when
        memberService.join(member);
        Order order = orderService.createOrder(memberId,"itemA",10000);
            //then
        Assertions.assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }
}
