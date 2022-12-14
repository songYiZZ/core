package hello.core;

import hello.core.discount.DiscountPolicy;
import hello.core.discount.FixDiscountPolicy;
import hello.core.discount.RateDiscountPolicy;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import hello.core.member.MemoryMemberRepository;
import hello.core.order.OrderService;
import hello.core.order.OrderServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration//설정정보에 적용하는 어노테이션
public class AppConfig {//공연 기획자

    //@Bean memberService -> MemoryMemberRepository()
    //@Bean orderService -> new MemoryMemberRepository()
    //2번 호출이니까 싱글톤 깨진는거 아녀요?

    //예상 호출
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.memberRepository
    //call AppConfig.orderService
    //call AppConfig.memberRepository

    //실제 호출
    //call AppConfig.memberService
    //call AppConfig.memberRepository
    //call AppConfig.orderService

    @Bean
    //생성자 주입 //1. MemoryMemberRepository 생성해서
    public MemberService memberService(){
        System.out.println("call AppConfig.memberService");
        //2. MemberServiceImpl에 구현체가 뭐가 들어갈지 정해준다
        return new MemberServiceImpl(memberRepository());
    }
    @Bean
    public MemoryMemberRepository memberRepository() {
        System.out.println("call AppConfig.memberRepository");
        return new MemoryMemberRepository();
    }

    @Bean
    //2. 주입~
    public OrderService orderService(){
        System.out.println("call AppConfig.orderService");
        //2. OrderServiceImpl의 구현체를 넣어준다.
        return new OrderServiceImpl(memberRepository(), discountPolicy());
//        return null;
    }


    @Bean
    public DiscountPolicy discountPolicy(){
        return new RateDiscountPolicy();
        //return new FixDiscountPolicy();
    }
}
