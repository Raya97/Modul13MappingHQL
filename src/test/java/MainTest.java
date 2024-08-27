import org.example.Main;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    void testMainMethod() {
        assertDoesNotThrow(() -> Main.main(new String[]{}));
    }
}
