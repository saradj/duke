import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DukeTest {
    @Test
    public void dummyTest(){
        assertEquals(2, 2);
    }
    @Test
    public void testExitCommand() throws DukeException {
           Duke duke= new Duke("data/tasks.txt");
            Command c=Parser.parse("bye");
         assertTrue(c instanceof ExitCommand);
    }
}
