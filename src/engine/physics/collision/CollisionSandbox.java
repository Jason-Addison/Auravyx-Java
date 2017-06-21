package engine.physics.collision;

import engine.component.Component;
import engine.component.ComponentManager;
import engine.component.WorldComponent;
import engine.entity.Entity;
import engine.physics.movement.PhysicsComponent;
import engine.world.World;
import engine.world.voxel.Chunk;
import org.lwjgl.util.vector.Vector3f;
import tools.Maths;
import tools.vector.Vec;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by Owner on 6/7/2017.
 */
public class CollisionSandbox
{
    public static int SLEEPING_COLLIDERS = 0;
    public static void testForCollision(ComponentManager entity, HashMap<String, Entity> entities)
    {
        broadPhase(entity, entities);
    }
    private static void broadPhase(ComponentManager entity, HashMap<String, Entity> entities)
    {
        ArrayList<CollisionComponent> localColliders = new ArrayList<>();
        ArrayList<Component> localComponents = entity.getComponents(Component.COLLISION_COMPONENT);
        if(localComponents == null)
        {
            return;
        }
        for(int i = 0; i < localComponents.size(); i++)
        {
            localColliders.add((CollisionComponent) localComponents.get(i));
        }
        for(CollisionComponent collider : localColliders)
        {
            for(String e : entities.keySet())
            {
                ComponentManager collideWith = entities.get(e).getComponentManager();
                if(entity != collideWith)
                {
                    ArrayList<CollisionComponent> exteriorColliders = new ArrayList<>();
                    ArrayList<Component> exteriorComponents = collideWith.getComponents(Component.COLLISION_COMPONENT);
                    if(exteriorComponents != null)
                    {
                        for(int i = 0; i < exteriorComponents.size(); i++)
                        {
                            exteriorColliders.add((CollisionComponent) exteriorComponents.get(i));
                        }
                        for(CollisionComponent c : exteriorColliders)
                        {
                            if(Collision.broadPhaseAABB(collider.getBroadPhaseAABB(), c.getBroadPhaseAABB()))
                            {
                        /*

                            Possible collision, do narrow collision

                         */
                                CollisionResponse response = narrowPhase(collider, c);
                                if(response != null)
                                {
                                    Vector3f mtv = response.getMtv();
                                    float threshold = 0.000001f;
                                    if(Math.abs(mtv.x) < threshold)
                                    {
                                        mtv.x = 0;
                                    }
                                    if(Math.abs(mtv.y) < threshold)
                                    {
                                        mtv.y = 0;
                                    }
                                    if(Math.abs(mtv.z) < threshold)
                                    {
                                        mtv.z = 0;
                                    }
                                    ////////////////////////////Edit later to support multiple colliders per object
                                    PhysicsComponent physicsComponent = (PhysicsComponent) (entity.get(Component.PHYSICS_COMPONENT));
                                    physicsComponent.addImpulse(Maths.invert(mtv), entity);
                                    PhysicsComponent exterior = ((PhysicsComponent) collideWith.get(Component.PHYSICS_COMPONENT));
                                    if(exterior != null)
                                    {
                                        exterior.wakeAll(collideWith);
                                    }
                                }
                                if(response == null)
                                {
                                    //System.out.println("ohno");
                                }
                            }
                        }
                    }
                }
            }
        }
    }
    private static CollisionResponse narrowPhase(CollisionComponent a, CollisionComponent b)
    {
        CollisionResponse response = null;

        if(a instanceof BoundingBoxComponent && b instanceof BoundingBoxComponent)
        {
            response = Collision.OBBtoOBB(a.getXRot(), a.getYRot(), a.getZRot(), a.getAsMatrix(),
                    b.getXRot(), b.getYRot(), b.getZRot(), b.getAsMatrix());
        }
        else
        {
            System.err.println("Tried to collide unknown object! Type : " + a.getClass().getSimpleName() + " and " + b.getClass().getSimpleName());
        }
        return response;
    }

    public static void testForWorldCollision(ComponentManager entity)
    {
        ArrayList<Float> WORLD_MESH = new ArrayList<>();
        WorldComponent wc = (WorldComponent) entity.get(Component.WORLD_COMPONENT);
        //LinkedHashMap<String, Chunk> chunks = World.getChunks();
        //for(String id : chunks.keySet())
        int x = (int) wc.getX() / 16;
        int z = (int) wc.getZ() / 16;
        if(wc.getX() < 0)
        {
            x--;
        }
        if(wc.getZ() < 0)
        {
            z--;
        }
        Chunk chunk = World.getChunk(x, z);
        {
            if(chunk != null)
            {
                ArrayList<Float> collision = chunk.getTerrain().getCollisionMesh();
                WORLD_MESH.addAll(collision);
            }
        }

        for(int i = 0; i < WORLD_MESH.size() / 12; i++)
        {
            float aX = WORLD_MESH.get(i * 12 + 0);
            float aY = WORLD_MESH.get(i * 12 + 1);
            float aZ = WORLD_MESH.get(i * 12 + 2);
            if(Math.abs(aX - wc.getX()) < 3 && Math.abs(aY - wc.getY()) < 3 && Math.abs(aZ - wc.getZ()) < 3)
            {
                Vector3f a = new Vector3f(aX, aY, aZ);
                Vector3f b = new Vector3f(WORLD_MESH.get(i * 12 + 3), WORLD_MESH.get(i * 12 + 4), WORLD_MESH.get(i * 12 + 5));
                Vector3f c = new Vector3f(WORLD_MESH.get(i * 12 + 6), WORLD_MESH.get(i * 12 + 7), WORLD_MESH.get(i * 12 + 8));
                Vector3f d = new Vector3f(WORLD_MESH.get(i * 12 + 9), WORLD_MESH.get(i * 12 + 10), WORLD_MESH.get(i * 12 + 11));

                //System.out.println(a + "  " + b + " " + c + " " + d);
                BoundingBoxComponent obb = (BoundingBoxComponent) entity.get(Component.COLLISION_COMPONENT);
                obb.setXRot(0);
                obb.setYRot(0);
                obb.setZRot(0);
                CollisionResponse response = Collision.OBBtoQuad(obb.getXRot(), obb.getYRot(), obb.getZRot(), obb.getAsMatrix(), a, b, c, d);

                if(response != null)
                {
                    Vector3f mtv = response.getMtv();
                    float threshold = 0.000001f;
                    if(Math.abs(mtv.x) < threshold)
                    {
                        mtv.x = 0;
                    }
                    if(Math.abs(mtv.y) < threshold)
                    {
                        mtv.y = 0;
                    }
                    if(Math.abs(mtv.z) < threshold)
                    {
                        mtv.z = 0;
                    }
                    PhysicsComponent physicsComponent = (PhysicsComponent) (entity.get(Component.PHYSICS_COMPONENT));
                    if(!Vec.zero(mtv))
                    {
                        physicsComponent.addImpulse(Maths.invert(mtv), entity);
                    }
                }
            }
        }
    }
}
