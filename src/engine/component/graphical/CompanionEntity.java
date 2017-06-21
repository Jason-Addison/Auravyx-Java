package engine.component.graphical;

import engine.component.Component;
import engine.component.ComponentManager;
import engine.component.WorldComponent;
import engine.entity.Entity;
import engine.world.World;
import org.lwjgl.util.vector.Matrix4f;
import org.lwjgl.util.vector.Vector3f;
import tools.Maths;
import tools.matrix.Matrix;

/**
 * Created by Owner on 3/23/2017.
 */
public class CompanionEntity extends Component
{

    int id;

    float xOff, yOff, zOff;
    Entity entity;
    public CompanionEntity(Entity entity, float xOff, float yOff, float zOff)
    {
        World.addEntity(entity);
        id = entity.getID();
        this.entity = entity;
        this.xOff = xOff;
        this.yOff = yOff;
        this.zOff = zOff;
    }

    public void update(ComponentManager manager)
    {
        WorldComponent worldSpace = (WorldComponent) manager.get(Component.WORLD_COMPONENT);
        WorldComponent subWorldSpace = (WorldComponent) World.getEntity(id).getComponent(Component.WORLD_COMPONENT);
        /*subWorldSpace.set(worldSpace.getX() + xOff, worldSpace.getY() + yOff, worldSpace.getZ() + zOff,
                worldSpace.getXRot() + subWorldSpace.getXRot(), worldSpace.getYRot() + subWorldSpace.getYRot(), worldSpace.getZRot() + subWorldSpace.getZRot(),
                subWorldSpace.getXScale(), subWorldSpace.getYScale(), subWorldSpace.getZScale(), 0, 0, 0);*/

        Matrix4f localTransform = Maths.createTransformationMatrix(new Vector3f(xOff, yOff, zOff), new Vector3f(0, 0, 0), worldSpace.getXRot(), worldSpace.getYRot(), worldSpace.getZRot(), worldSpace.getXScale(),
                worldSpace.getYScale(), worldSpace.getZScale());

        Matrix4f transform = Matrix.createAttached(new Vector3f(xOff, yOff, zOff), new Vector3f(0, 0, 0), subWorldSpace.getXRot(), subWorldSpace.getYRot(),
                subWorldSpace.getZRot(), subWorldSpace.getXScale(), subWorldSpace.getYScale(), subWorldSpace.getZScale(),
        new Vector3f(worldSpace.getX(), worldSpace.getY(), worldSpace.getZ()), new Vector3f(xOff, yOff, zOff), worldSpace.getXRot(), worldSpace.getYRot(), worldSpace.getZRot(),
                worldSpace.getXScale(), worldSpace.getYScale(), worldSpace.getZScale());

        subWorldSpace.setMatrix(transform);
    }
    public Entity getEntity()
    {
        return entity;
    }
}
