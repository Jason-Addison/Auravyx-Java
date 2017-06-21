package engine.component.graphical;

import engine.component.Component;
import engine.game.Game;
import engine.graphics.model.Model;

/**
 * Created by Owner on 3/23/2017.
 */
public class ModelComponent extends Component
{
    Model model;

    public ModelComponent(String name)
    {
        this.model = Game.getModel(name);
    }
    public ModelComponent(Model model)
    {
        this.model = model;
    }

    public void addModel(String modelString, float xOff, float yOff, float zOff)
    {
        /*ModelSpace model = new ModelSpace(modelString, xOff, yOff, zOff);

        models.put(idCount, model);
        idCount++;*/
    }
    public Model getModel()
    {
        return model;
    }

    public void setModel(Model model)
    {
        this.model = model;
    }
    public void setTexture(int texture)
    {
        model.overrideTexture(texture);
    }
}
