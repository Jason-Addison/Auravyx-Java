package engine.component;

import java.util.HashMap;

/**
 * Created by Owner on 2/18/2017.
 */
public class TestComponent extends Component
{
    public TestComponent()
    {

    }
    public void update(HashMap<String, Component> map)
    {
        Component e = new TestComponent();

        TestComponent c = (TestComponent) e;
        c.meme();
    }
    public void meme()
    {
    }
}
