package abused_master.avaritiaadditions.render;

import net.minecraft.client.Minecraft;
import net.minecraft.util.text.TextFormatting;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Text {

    private static final TextFormatting[] sanic = new TextFormatting[] {TextFormatting.BLUE, TextFormatting.BLUE, TextFormatting.BLUE, TextFormatting.BLUE, TextFormatting.WHITE, TextFormatting.BLUE, TextFormatting.WHITE, TextFormatting.WHITE, TextFormatting.BLUE, TextFormatting.WHITE, TextFormatting.WHITE, TextFormatting.BLUE, TextFormatting.RED, TextFormatting.WHITE, TextFormatting.GRAY, TextFormatting.GRAY, TextFormatting.GRAY, TextFormatting.GRAY, TextFormatting.GRAY, TextFormatting.GRAY, TextFormatting.GRAY, TextFormatting.GRAY, TextFormatting.GRAY, TextFormatting.GRAY, TextFormatting.GRAY, TextFormatting.GRAY, TextFormatting.GRAY, TextFormatting.GRAY, TextFormatting.GRAY, TextFormatting.GRAY};
    private static final TextFormatting[] fabulousness = new TextFormatting[] {TextFormatting.RED, TextFormatting.GOLD, TextFormatting.YELLOW, TextFormatting.GREEN, TextFormatting.AQUA, TextFormatting.BLUE, TextFormatting.LIGHT_PURPLE};
    public static String makeSANIC(String input) {
        return ludicrousFormatting(input, sanic, 50.0, 2,1);
    }

    public static String makeFabulous(String input) {
        return ludicrousFormatting(input, fabulousness, 80.0, 1, 1);
    }

    public static String ludicrousFormatting(String input, TextFormatting[] colours, double delay, int step, int posstep) {
        StringBuilder sb = new StringBuilder(input.length()*3);
        if (delay <= 0) {
            delay = 0.001;
        }

        int offset = (int) Math.floor(Minecraft.getSystemTime() / delay) % colours.length;

        for (int i=0; i<input.length(); i++) {
            char c = input.charAt(i);

            int col = ((i * posstep) + colours.length - offset) % colours.length;

            sb.append(colours[col].toString());
            sb.append(c);
        }

        return sb.toString();
    }

    public static final Logger logger = LogManager.getLogger("Avaritia-Additions");

    public static void log(Level level, Throwable e, Object message) {
        log(level, message);
        e.printStackTrace();
    }

    public static void log(Level level, Object message) {
        logger.log(level, message);
    }

    public static void info(Object message) {
        log(Level.INFO, message);
    }
}
