package abused_master.avaritiaadditions.render;

import net.minecraft.client.Minecraft;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.ResourceLocation;
import org.lwjgl.opengl.GL11;

public class InfinityWingsModel extends ModelBiped
{
    public static final InfinityWingsModel INSTANCE = new InfinityWingsModel();
    public static ResourceLocation wingTex = new ResourceLocation("avaritiaadditions","textures/models/armor/infinity_wings.png");
    public static ResourceLocation wingGlowTex = new ResourceLocation("avaritiaadditions","textures/models/armor/infinity_armor_wingglow.png");
    ModelRenderer wing1;
    ModelRenderer Shape1;

  public InfinityWingsModel()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      wing1 = new ModelRenderer(this, 0, 33);
      wing1.addBox(0F, 0F, 0F, 28, 30, 0);
      wing1.setRotationPoint(0F, -8F, 1F);
      wing1.setTextureSize(64, 64);
      wing1.mirror = true;
      setRotation(wing1, 0F, -0.418879F, 0F);
      Shape1 = new ModelRenderer(this, 0, 0);
      Shape1.addBox(0F, 0F, 0F, 28, 30, 0);
      Shape1.setRotationPoint(-27F, -8F, 13F);
      Shape1.setTextureSize(64, 64);
      Shape1.mirror = true;
      setRotation(Shape1, 0F, 0.418879F, 0F);
  }

  @Override
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    Minecraft mc = Minecraft.getMinecraft();
    super.render(entity, f, f1, f2, f3, f4, f5);

    mc.renderEngine.bindTexture(wingTex);
    setRotationAngles(f, f1, f2, f3, f4, f5, entity);
    wing1.render(f5);
    Shape1.render(f5);
    //mc.renderEngine.bindTexture(wingGlowTex);

    long time = mc.thePlayer.worldObj.getWorldTime();
    double pulse = Math.sin(time / 10.0)*0.5 + 0.5;

    GL11.glColor4d(0.84, 1, 0.95, pulse*pulse*pulse*pulse*pulse*pulse*0.5);

    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE);
    //super.render(entity, f, f1, f2, f3, f4, f5);
    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);

    GL11.glDepthMask(true);
    GL11.glDisable(GL11.GL_BLEND);
    GL11.glEnable(GL11.GL_ALPHA_TEST);
    GL11.glEnable(GL11.GL_LIGHTING);
    GL11.glColor4d(1,1,1,1);
  }

  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }

  @Override
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5, Entity entity)
  {
    super.setRotationAngles(f, f1, f2, f3, f4, f5, entity);
  }
}
