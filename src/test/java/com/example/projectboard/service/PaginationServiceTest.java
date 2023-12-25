package com.example.projectboard.service;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.stream.Stream;

import static org.junit.jupiter.params.provider.Arguments.arguments;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.NONE, classes = PaginationService.class)
@DisplayName("비즈니스 로직 - 페이지네이션")
class PaginationServiceTest {

    @Autowired
    private PaginationService paginationService;

    @DisplayName("현재 페이지 번호와 총 페이지 수를 주면 페이징 바 리스트를 만들어준다.")
    @ParameterizedTest
    @MethodSource()
    public void getBarNumberTest(int currentPageNumber, int toalPage, List<Integer> expected){
        //when
        List<Integer> actual = paginationService.getPaginationBarNumbers(currentPageNumber, toalPage);

        //then
        Assertions.assertThat(actual).isEqualTo(expected);
    }

    static Stream<Arguments> getBarNumberTest() {
        return Stream.of(
                arguments(0, 13, List.of(0, 1, 2, 3 ,4)),
                arguments(1, 13, List.of(0, 1, 2, 3 ,4)),
                arguments(2, 13, List.of(0, 1, 2, 3 ,4)),
                arguments(3, 13, List.of(1, 2, 3 ,4, 5)),
                arguments(4, 13, List.of(2, 3 ,4, 5, 6))
        );
    }
}