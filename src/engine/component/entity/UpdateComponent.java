package engine.component.entity;

import engine.component.Component;
import engine.component.ComponentManager;
import engine.component.entity.items.UpdateInterface;

/**
 * Created by Owner on 6/17/2017.
 */
public class UpdateComponent extends Component
{
    UpdateInterface updater = new UpdateInterface()
    {
        @Override
        public void update(ComponentManager manager)
        {

        }
    };
    public UpdateComponent()
    {

    }
    public UpdateComponent(UpdateInterface u)
    {
        this.updater = u;
    }
    public void update(ComponentManager manager)
    {
        updater.update(manager);
    }

    public void setUpdater(UpdateInterface u)
    {
        this.updater = u;
    }

}
