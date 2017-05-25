package net.darkhax.sasit;

import java.util.Arrays;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.google.common.eventbus.EventBus;

import net.minecraftforge.fml.common.DummyModContainer;
import net.minecraftforge.fml.common.LoadController;
import net.minecraftforge.fml.common.ModMetadata;

public class SASITModContainer extends DummyModContainer {

    public static final String MODID = "sasit";
    public static final String MOD_NAME = "StuffASockInIt";

    // How Ironic :p
    public static final Logger LOG = LogManager.getLogger(MOD_NAME);

    public SASITModContainer () {

        super(new ModMetadata());

        final ModMetadata meta = this.getMetadata();

        meta.modId = MODID;
        meta.name = MOD_NAME;
        meta.version = "@VERSION@";
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