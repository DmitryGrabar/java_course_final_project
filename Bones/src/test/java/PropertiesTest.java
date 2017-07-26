

import org.junit.Assert;
import org.junit.Test;

import java.util.ResourceBundle;

public class PropertiesTest {

    @Test
    public void databasePropertiesExist() {
        Assert.assertNotNull(ResourceBundle.getBundle("prop/config"));
    }

    @Test
    public void pageContentPropertiesExist() {
        Assert.assertNotNull(ResourceBundle.getBundle("prop/game_startup"));
    }
}