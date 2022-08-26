package hello.core.singleton;

public class SingletonService {

    //자기 자신을 내부에 static으로 가져서 딱 1개만 가진다. 클래스 레벨에 딱 한개!
    private static final SingletonService instance = new SingletonService();

    //jvm이 처음 생성할 때 new연산자로 만든 싱클톤서비스 인스턴스를 instance에 참조변수로 넣어주고 조회시에 주소를 반환.
    public static SingletonService getInstance(){
        return instance;
    }

    private SingletonService(){
    }

    public void login(){
        System.out.println("싱글톤 객체 로직 호출");
    }
}
