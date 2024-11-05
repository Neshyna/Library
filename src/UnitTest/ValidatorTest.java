package UnitTest;

import Model.User;
import Utils.PersonValidator;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ValidatorTest {

    PersonValidator regularUser;
    String startEmail = "Neshyna@test.com";
    String startPassword = "Neshyna100%";

    @BeforeEach
    void setUp() {
        regularUser = new PersonValidator(startEmail,startPassword);
    }

    @Test
    void testValidEmailSet() {
        String validEmail = "Valid123@test.com";
        regularUser.setEmail(validEmail);
        assertEquals(validEmail, regularUser.getEmail());
    }

    @ParameterizedTest
    @MethodSource("invalidEmailData")
    void testInvalidEmailSet(String invalidEmail) {
        regularUser.setEmail(invalidEmail);
        assertNotEquals(invalidEmail, regularUser.getEmail());
        assertEquals(startEmail, regularUser.getEmail());
    }

    static Stream<String> invalidEmailData() {
        return Stream.of(
                "testmail.net",
                "test@@mail.net",
                "test@mai@l.net",
                "test@mailnet",
                "test@mail.ne.t",
                "test@mail.net.",
                "test@ mail.net",
                "test@ma!il.net"
        );
    }

    @Test
    void testValidPasswordSet() {
        String validPassword = "Test_123";
        regularUser.setPassword(validPassword);
        assertEquals(validPassword, regularUser.getPassword());
    }

    @ParameterizedTest
    @MethodSource("invalidPassword")
    void testInvalidPassword(String invalidPassword) {
        regularUser.setPassword(invalidPassword);
        assertEquals(startPassword, regularUser.getPassword());
        assertNotEquals(invalidPassword, regularUser.getPassword());
    }

    static Stream<String> invalidPassword() {
        return Stream.of(
                "Test_1",
                "Test_test",
                "TEST_123",
                "test_123",
                "Test123456"
        );
    }
}
