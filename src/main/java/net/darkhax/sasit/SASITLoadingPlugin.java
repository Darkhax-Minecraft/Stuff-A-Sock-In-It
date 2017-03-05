package net.darkhax.sasit;

import java.util.Map;

import net.minecraftforge.fml.relauncher.IFMLLoadingPlugin;

public class SASITLoadingPlugin implements IFMLLoadingPlugin {

    @Override
    public String[] getASMTransformerClass () {

        return null;
    }

    @Override
    public String getModContainerClass () {

        return "net.darkhax.sasit.SASITModContainer";
    }

    @Override
    public String getSetupClass () {

        return "net.darkhax.sasit.SASITCallHook";
    }

    @Override
    public void injectData (Map<String, Object> data) {

    }

    @Override
    public String getAccessTransformerClass () {

        return null;
    }
}