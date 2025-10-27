package racingcar;

import camp.nextstep.edu.missionutils.test.NsTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import racingcar.constant.ErrorMessage;

import static camp.nextstep.edu.missionutils.test.Assertions.assertRandomNumberInRangeTest;
import static camp.nextstep.edu.missionutils.test.Assertions.assertSimpleTest;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ApplicationTest extends NsTest {
    private static final int MOVING_FORWARD = 4;
    private static final int STOP = 3;

    @Test
    void 기능_테스트() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni", "1");
                    assertThat(output()).contains("pobi : -", "woni : ", "최종 우승자 : pobi");
                },
                MOVING_FORWARD, STOP
        );
    }

    @Test
    void 기능_테스트_공동_우승() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi,woni,jun", "2");
                    assertThat(output()).contains(
                            "pobi : -",    // 1R: pobi(4), woni(3), jun(4)
                            "woni : ",
                            "jun : -",
                            "pobi : --",   // 2R: pobi(4), woni(3), jun(4)
                            "woni : ",
                            "jun : --",
                            "최종 우승자 : pobi, jun"
                    );
                },
                MOVING_FORWARD, STOP, MOVING_FORWARD,
                MOVING_FORWARD, STOP, MOVING_FORWARD
        );
    }

    @Test
    void 기능_테스트_단일_자동차() {
        assertRandomNumberInRangeTest(
                () -> {
                    run("pobi", "3");
                    assertThat(output()).contains(
                            "pobi : -",
                            "pobi : -",
                            "pobi : --",
                            "최종 우승자 : pobi"
                    );
                },
                MOVING_FORWARD,
                STOP,
                MOVING_FORWARD
        );
    }

    @Test
    void 예외_테스트() {
        assertSimpleTest(() -> {
            run("pobi,javaji", "1");

            assertThat(output()).contains(
                    ErrorMessage.INVALID_CAR_NAME_LENGTH.getMessage("javaji")
            );
        });
    }

    @Test
    void 예외_테스트_시도횟수_숫자아님() {
        assertSimpleTest(() -> {
            // given
            run("pobi,woni", "abc");

            // then
            assertThat(output()).contains(
                    ErrorMessage.INVALID_ROUND_COUNT_NUMBER.getMessage("abc")
            );
        });
    }

    @Test
    void 예외_테스트_시도횟수_0이하() {
        assertSimpleTest(() -> {
            run("pobi,woni", "0");
            assertThat(output()).contains(
                    ErrorMessage.INVALID_ROUND_COUNT_POSITIVE.getMessage("0")
            );
        });

        assertSimpleTest(() -> {
            run("pobi,woni", "-1");
            assertThat(output()).contains(
                    ErrorMessage.INVALID_ROUND_COUNT_POSITIVE.getMessage("-1")
            );
        });
    }

    @Override
    public void runMain() {
        Application.main(new String[]{});
    }
}
