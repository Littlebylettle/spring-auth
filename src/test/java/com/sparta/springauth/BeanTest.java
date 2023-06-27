package com.sparta.springauth;


import com.sparta.springauth.food.Food;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest  //빈 주입받아오는걸 사용 가능하게 함

public class BeanTest {


//    //빈이 한개 이상이라 자동주입(autowired) 할 수 없다고 오류가 뜸 어떤 빈을 등록할지 모르겠다는 뜻 치킨과 피자중 -
//    //Autowired는 기본적으로 빈의 타입으로 먼저 찾고 그 다음에 이름으로 찾는다.
//    //1. 등록이 된 빈의 이름을 등록하여 해결한다 Food pizza Food Chicken
//    @Autowired
//    Food pizza;
//    @Autowired
//    Food chicken;
//    //2. 하나의 빈에 가서 @Primary 등록 -> 우선시 됨 현재 칰킨에 해놓음
//    @Autowired
//    Food food;
    //3. Qualifier 사용하기 빈에도 현재 테스트에도 달아줌-->현재 피자에다 달아놓음 피자를 먹습니다로 변경되어 출력이됨 ->Primary 보다 우선순위 높음
    // 범용적인 객체 -> Primary 지역적인 객체 -> Qualifier을 달아주는게 맞음 치킨이 95퍼 피자가 5퍼 매출이라 가정한 다면 이해가 하기 쉬움
    //스프링에서는 항상 좁은범위가 우선순위가 더 높음 -> 효율적이기 때문
@Autowired
@Qualifier("pizza")
Food food;

    @Test
    @DisplayName("Primary와 Qualifier 중에 누가 우선순위가 높은가")
    void test1() {
       food.eat();
    }

}
