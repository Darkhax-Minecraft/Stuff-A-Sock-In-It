package net.darkhax.sasit.io;

import java.io.PrintStream;

import net.darkhax.sasit.handler.ConfigurationHandler;

public class PrintStreamFilterable extends PrintStream {

    public PrintStreamFilterable (PrintStream stream) {

        super(stream);
    }

    @Override
    public void println (String x) {

        if (!ConfigurationHandler.requiresFiltering(x)) {
            super.println(x);
        }
    }

    @Override
    public void print (String s) {

        if (!ConfigurationHandler.requiresFiltering(s)) {
            super.print(s);
        }
    }
}