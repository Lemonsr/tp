package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GENDER_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_REMARK_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_PM;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TP;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.AMY;
import static seedu.address.testutil.TypicalPersons.DANIEL;
import static seedu.address.testutil.TypicalPersons.ELLE;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.StudentBuilder;

public class StudentTest {
    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Student student = new StudentBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> student.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(DANIEL.isSamePerson(DANIEL));

        // null -> returns false
        assertFalse(DANIEL.isSamePerson(null));

        // same phone, all other attributes different -> returns true
        Student editedDaniel = new StudentBuilder(DANIEL).withName(VALID_NAME_BOB).withGender(VALID_GENDER_AMY)
                .withTag(VALID_TAG_PM).build();
        assertTrue(DANIEL.isSamePerson(editedDaniel));

        // different phone, all other attributes same -> returns false
        editedDaniel = new StudentBuilder(DANIEL).withPhone(VALID_PHONE_AMY).build();
        assertFalse(DANIEL.isSamePerson(editedDaniel));
    }

    @Test
    public void isRemarkEmptyTest() {
        String remark = "";
        // empty remark
        Student editedDaniel = new StudentBuilder(DANIEL).withRemark(remark).build();
        assertTrue(editedDaniel.isRemarkEmpty());

        // valid remark
        remark = "remark";
        editedDaniel = new StudentBuilder(DANIEL).withRemark(remark).build();
        assertFalse(editedDaniel.isRemarkEmpty());
    }

    @Test
    public void equals() {
        // same values -> returns true
        Student elleCopy = new StudentBuilder(ELLE).build();
        assertEquals(ELLE, elleCopy);

        // same object -> returns true
        assertEquals(ELLE, ELLE);

        // null -> returns false
        assertNotEquals(null, ELLE);

        // different type -> returns false
        assertNotEquals(5, ELLE);

        // different student -> returns false
        assertNotEquals(ELLE, AMY);

        // different name -> returns false
        Student editedElle = new StudentBuilder(ELLE).withName(VALID_NAME_AMY).build();
        assertNotEquals(ELLE, editedElle);

        // different phone -> returns false
        editedElle = new StudentBuilder(ELLE).withPhone(VALID_PHONE_AMY).build();
        assertNotEquals(ELLE, editedElle);

        // different gender -> returns false
        editedElle = new StudentBuilder(ELLE).withGender(VALID_GENDER_BOB).build();
        assertNotEquals(ELLE, editedElle);

        // different tags -> returns false
        editedElle = new StudentBuilder(ELLE).withTag(VALID_TAG_TP).build();
        assertNotEquals(ELLE, editedElle);

        // different remarks -> returns false
        editedElle = new StudentBuilder(ELLE).withRemark(VALID_REMARK_AMY).build();
        assertNotEquals(ELLE, editedElle);
    }
}
