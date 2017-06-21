package engine.graphics.gui;

import engine.graphics.gui.guicomponents.Event;
import engine.graphics.gui.guicomponents.GuiComponent;

/**
 * Created by Owner on 5/26/2017.
 */
public class Looper extends GuiComponent
{
    Event event = new Event()
    {
        @Override
        public void onEvent()
        {

        }
    };
    public Looper(Event event)
    {
        this.event = event;
    }
    public void update()
    {
        event.onEvent();
    }
}
