import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.testfx.api.FxRobot;
import org.testfx.api.FxToolkit;
import org.testfx.framework.junit5.ApplicationTest;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ArticleRevisionCheckerTest extends ApplicationTest {

    @Override
    public void start(Stage stage) throws Exception {
        ArticleRevisionChecker app = new ArticleRevisionChecker();
        app.start(stage);
    }

    @Test
    public void testInputField() {
        clickOn("#articleTitleField");
        write("Sample Article");
        assertEquals("Sample Article", lookup("#articleTitleField").queryTextInputControl().getText());
    }

    @Test
    public void testCheckButton() {
        clickOn("#articleTitleField");
        write("Sample Article");
        clickOn("#checkButton");
        Scene outputScene = FxToolkit.setupScene(() -> new ArticleRevisionChecker().createOutputLayout());
        assertTrue(outputScene.getRoot().lookup("#outputLabel").isVisible());
    }

    @Test
    public void testErrorButton() {
        clickOn("#errorButton");
        assertTrue(lookup("Invalid input").tryQuery().isPresent());
    }

    @Override
    public void stop() throws Exception {
        FxToolkit.hideStage();
    }
}
