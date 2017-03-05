package net.darkhax.sasit;

import java.util.Arrays;

import com.google.common.eventbus.EventBus;

import net.darkhax.sasit.libs.Constants;
import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;

public class SASITModContainer extends DummyModContainer {

    public SASITModContainer () {

        super(new ModMetadata());

        final ModMetadata meta = this.getMetadata();

        meta.modId = Constants.MODID;
        meta.name = Constants.MOD_NAME;
        meta.version = Constants.VERSION_NUMBER;
        meta.credits = "All the mods that spam the console with data nobody cares about!";
        meta.authorList = Arrays.asList("Darkhax");
        meta.description = "Allows for log elements to be filtered!";
        meta.url = "https://github.com/Darkhax-Minecraft/Stuff-A-Sock-In-It";
        meta.screenshots = new String[0];
        meta.logoFile = "";
    }

    @Override
    public boolean registerBus (EventBus bus, LoadController controller) {

        return true;
    }
}