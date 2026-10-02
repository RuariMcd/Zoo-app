import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class AnimalTest {

    @Test
    void testGetStringEagle() {
        Eagle eagle = new Eagle("Sky", 3, "Brown", "5kg", "2m");

        String result = eagle.getString();

        assertEquals("Eagle,Sky,3,Brown,5kg,2m", result);
    }

    @Test
    void testValidLion() {
        Lion lion = new Lion("Simba", 5, "Golden", "190kg", "50cm");

        assertTrue(lion.isValid());
    }

    @Test
    void testInvalidAnimalEmptyName() {
        Lion lion = new Lion("", 5, "Golden", "190kg", "50cm");

        assertFalse(lion.isValid());
    }

    @Test
    void testInvalidEagleNoWingspan() {
        Eagle eagle = new Eagle("Sky", 3, "Brown", "5kg", "");

        assertFalse(eagle.isValid());
    }

}