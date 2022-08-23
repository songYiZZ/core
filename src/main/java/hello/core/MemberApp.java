package hello.core;

import hello.core.member.Grade;
import hello.core.member.Member;
import hello.core.member.MemberService;
import hello.core.member.MemberServiceImpl;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class MemberApp {

    public static void main(String[] args) {
        //아래코드는 기존에 직접 해주던 것을
        //MemberService memberService = new MemberServiceImpl();
        //appConfig가 알아서 memberService의 구현체를 memberService에 넣어준다.

        //AppConfig appConfig = new AppConfig();
        //MemberService memberService = appConfig.memberService();

        //위의 두줄을 주석처리하고 스프링으로 전환
        //ApplicationContext는 스프링컨테이너로 보면 된다.
        //AppConfig의 정보를 읽고 빈들을 스프링컨테이너로 관리해준다.
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext(AppConfig.class);
        MemberService memberService = applicationContext.getBean("memberService", MemberService.class);

        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }

}
