package net.darkhax.sasit;

import net.darkhax.sasit.libs.Constants;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod.Instance;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = Constants.MODID, name = Constants.MOD_NAME, version = Constants.VERSION_NUMBER)
public class SASIT {

    @Instance(Constants.MODID)
    public static SASIT instance;

    @EventHandler
    public void preInit (FMLPreInitializationEvent event) {

    }
}
