package engine.graphics.gui.guicomponents;

/**
 * Created by Owner on 3/8/2017.
 */
public class KeyboardListener extends GuiComponent
{

    KeyEvent keyEvent;
    public KeyboardListener()
    {
        initializeComponent();
    }
    public void update()
    {
        keyEvent.onType();
    }
    public void addKeyEvent(KeyEvent keyEvent)
    {
        this.keyEvent = keyEvent;
    }
}
