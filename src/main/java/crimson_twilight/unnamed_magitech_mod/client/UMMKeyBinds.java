package crimson_twilight.unnamed_magitech_mod.client;


import net.minecraftforge.fml.client.registry.ClientRegistry;
import org.lwjgl.glfw.GLFW;
import net.minecraft.client.settings.KeyBinding;

public class UMMKeyBinds
{
    public static KeyBinding cultivate;

    public static void register()
    {
        cultivate = registerKeybinding(new KeyBinding("key.cultivate.desc", GLFW.GLFW_KEY_C, "key.umm.cultivate"));
    }

    private static KeyBinding registerKeybinding(KeyBinding key) {
        ClientRegistry.registerKeyBinding(key);
        return key;
    }
}
