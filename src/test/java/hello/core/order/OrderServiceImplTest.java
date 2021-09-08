package hello.core.order;

import hello.core.discount.FixDiscountPolicy;
import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemoryMemberRepository;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class OrderServiceImplTest {

    @Test
    void createOrder() {
        MemoryMemberRepository memberRepository = new MemoryMemberRepository();
        memberRepository.save(new Member(1L, "name", Grade.VIP));

        OrderServiceImpl orderService = new OrderServiceImpl(memberRepository, new FixDiscountPolicy());
        Order order = orderService.createOrder(1L, "itemA", 10000);

        assertThat(order.getDiscountPrice()).isEqualTo(1000);
    }

    // 프레임워크 없이 순수한 자바 코드로 테스트를 할 경우, 수정자 주입을 이용하면 의존관계 주입의 누락이 생길 수 있다.
    // 생성자 주입을 이용할 시에는 의존관계가 누락되면 컴파일 오류가 발생한다.
    // 생성자 주입을 이용할 시에는 final 키워드를 이용할 수 있다. 이를 통해 생성자에서 값 설정 실수를 한 경우 컴파일 시점에 쉽게 발견할 수 있다.
    // 생성자 주입 이외의 주입 방식은 모두 생성자 이후에 호출되므로 final 키워드를 사용할 수 없다.
}