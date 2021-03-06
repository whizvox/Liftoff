package whizvox.liftoff;

import org.bukkit.configuration.Configuration;
import org.bukkit.plugin.Plugin;

public class Config {

    private static int signRange, solidGroundRange;
    private static boolean signColoring;

    public static void init(Plugin plugin) {
        plugin.saveDefaultConfig();
        final Configuration defaults = plugin.getConfig().getDefaults();
        signRange = plugin.getConfig().getInt("signRange");
        if (signRange > 255 || signRange < 3) {
            System.out.println("[Liftoff] Sign range can only be between 3 and 255: " + signRange);
            int signRange_default = defaults.getInt("signRange");
            plugin.getConfig().set("signRange", signRange_default);
            signRange = signRange_default;
        }
        solidGroundRange = plugin.getConfig().getInt("solidGroundRange");
        if (solidGroundRange > 255 || signRange < 1) {
            System.out.println("[Liftoff] Solid ground range can only be between 1 and 255: " + solidGroundRange);
            int solidGroundRange_default = defaults.getInt("solidGroundRange");
            plugin.getConfig().set("solidGroundRange", solidGroundRange_default);
            solidGroundRange = solidGroundRange_default;
        }
        signColoring = plugin.getConfig().getBoolean("signColoring");
        plugin.saveConfig();
    }

    public static int getSignRange() {
        return signRange;
    }

    public static int getSolidGroundRange() {
        return solidGroundRange;
    }

    public static boolean signColoringEnabled() {
        return signColoring;
    }

}
