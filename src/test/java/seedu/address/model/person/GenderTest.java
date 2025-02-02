package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.CARL;

import org.junit.jupiter.api.Test;

public class GenderTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Gender(null));
    }

    @Test
    public void constructor_invalidGender_throwsIllegalArgumentException() {
        String invalidGender = "";
        assertThrows(IllegalArgumentException.class, () -> new Gender(invalidGender));
    }

    @Test
    public void isValidGender() {
        // null name
        assertThrows(NullPointerException.class, () -> Gender.isValidGender(null));

        // invalid gender
        assertFalse(Gender.isValidGender("")); // empty string
        assertFalse(Gender.isValidGender(" ")); // spaces only
        assertFalse(Gender.isValidGender("^")); // only non-alphanumeric characters
        assertFalse(Gender.isValidGender("peter*")); // contains non-alphanumeric characters
        assertFalse(Gender.isValidGender("t")); // invalid input

        // valid gender
        assertTrue(Gender.isValidGender("M"));
        assertTrue(Gender.isValidGender("m"));
        assertTrue(Gender.isValidGender("F"));
        assertTrue(Gender.isValidGender("f"));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Gender gender = CARL.getGender();
        Gender genderCopy = new Gender("M");
        assertEquals(gender, genderCopy);

        // same object -> returns true
        assertEquals(gender, gender);

        // null -> returns false
        assertNotEquals(null, gender);

        // different type -> returns false
        assertNotEquals(5, gender);

        // different gender -> returns false
        Gender genderDifferent = new Gender("F");
        assertNotEquals(gender, genderDifferent);
    }
}
