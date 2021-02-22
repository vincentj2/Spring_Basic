package hello.core.order;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import hello.core.member.MemoryMemberRepository;

public class OrderServiceImpl implements OrderService {

    private final MemberRepository memberRepository = new MemoryMemberRepository();
    private final DiscountPolicy discountPolicy = new FixDiscountPolicy();
    /*만약 FixDiscountPolicy() -> RateDiscountPolicy()로 변경하게 된다면
    OrderServiceImpl 자체의 코드 변경이 필요해지기 때문에 DIP, OCP 원칙을 위반하게 된다.
    그런경우 누군가 private final DiscountPolicy discountPolicy 같은 형태로 의존성 주입을 해줘야한다.
    -> 지금 코드는 기능을 확장해서 변경하면 클라이언트 코드에 영향을 준다
    */
    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);
        int discountPrice = discountPolicy.discount(member,itemPrice);

        return new Order(memberId,itemName,itemPrice,discountPrice);
    }
}
