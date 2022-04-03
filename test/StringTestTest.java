import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.*;

class StringTestTest {

    private String str;

    @BeforeAll
    static void beforeAll() {
        System.out.println("Initialize connection to database");
    }

    @AfterAll
    static void afterAll() {
        System.out.println("Close connection to database");
    }

    @BeforeEach
    void beforeEach(TestInfo info) {
        System.out.println("Initialize Test Data for " + info.getDisplayName());
    }
    @AfterEach
    void AfterEach(TestInfo info) {
        System.out.println("clean up of test data for " + info.getDisplayName());
    }

    @Test
    @RepeatedTest(10)
    void length_basics() {
        int actualLength = "ABCD".length();
        int expectedLength = 4;
        assertEquals(expectedLength, actualLength);
    }

    @Test
    @Disabled
    void length_greater_than_zero() {
        assertTrue("ABCD".length()<0);
        assertTrue("ABC".length()<0);
        assertTrue("A".length()<0);
        assertTrue("DEF".length()<0);
    }

    @ParameterizedTest
    @ValueSource(strings= {"ABCD", "ABC", "A", "DEF"})
    void length_greater_than_zero_using_parameterized_test(String str) {
        assertTrue(str.length()>0);
    }

    @ParameterizedTest(name = "{0} toUpperCase is {1}")
    @CsvSource(value= {"abcd, ABCD","abc, ABC","'',''","abcdefg, ABCDEFG"})
    void uppercase(String word, String capitalizedWord) {
        assertEquals(capitalizedWord, word.toUpperCase());
    }

    @ParameterizedTest(name = "{0} length is {1}")
    @CsvSource(value= {"abcd, 4","abc, 3","'',0","abcdefg, 7"} )
    void length(String word, int expectedLength) {
        assertEquals(expectedLength, word.length());
    }


    @Test
    @DisplayName("When length is null, throw an exception")
    void length_exception() {

        String str = null;

        assertThrows(NullPointerException.class,

                () -> {
                    str.length();
                }

                );
    }

    @Test
    void performanceTest() {
        assertTimeout(Duration.ofSeconds(5), () -> {
            for (int i = 0; i <= 100000; i++) {
                int j = i;
                System.out.println(j);
            }
        }
        );
    }

    @Test
    void toUpperCase_basic() {
        String str = "abcd";
        String result = str.toUpperCase();
        assertEquals("ABCD", result);
    }

    @Test
    void contains_basic() {
        String str = "abcdefgh";
        assertEquals(false, str.contains("ijk"));
        assertFalse(str.contains("ijk"));
    }

    @Test
    void contains_basic2() {
        assertFalse("abcdefgh".contains("ijk"));
    }

    @Test
    void split_basic() {
        String str = "abc def ghi";
        String actualResult[] = str.split(" ");
        String[] expectedResult = new String[] { "abc", "def", "ghi"};

        assertArrayEquals(expectedResult, actualResult);
    }

    @Nested
    @DisplayName("For an empty string")
    class EmptyStringTests {

        @BeforeEach
        void setToEmpty() {
            str = "";
        }

        @Test
        @DisplayName("length should be zero")
        void lengthIsZero() {
            assertEquals(0,str.length());
        }

        @Test
        @DisplayName("uppercase should be empty")
        void uppercaseIsEmpty() {
            assertEquals("",str.toUpperCase());
        }
    }
}