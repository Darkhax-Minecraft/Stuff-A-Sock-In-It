package net.darkhax.sasit.io;

import java.io.PrintStream;

import net.darkhax.sasit.SASIT;

public class PrintStreamFilterable extends PrintStream {

    public PrintStreamFilterable (PrintStream stream) {

        super(stream);
    }

    @Override
    public void println (String s) {

        if (!SASIT.CONFIG.shouldFilterMessage(s)) {
            super.println(s);
        }
    }

    @Override
    public void print (String s) {

        if (!SASIT.CONFIG.shouldFilterMessage(s)) {
            super.print(s);
        }
    }
}