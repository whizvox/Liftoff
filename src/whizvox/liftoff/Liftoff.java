package whizvox.liftoff;

import org.bukkit.plugin.java.JavaPlugin;

public class Liftoff extends JavaPlugin {

    public static final String VERSION = "1.0";

    @Override
    public void onEnable() {
        System.out.println("[Liftoff] Initializing ...");
        try {
            Config.init(this);
            getServer().getPluginManager().registerEvents(new EventsInterceptor(), this);
            getCommand("liftoff").setExecutor(new LiftoffCommand());
            System.out.println("[Liftoff] Successfully initialized.");
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("[Liftoff] Could not initialize due to an exception.");
        }
    }

    @Override
    public void onDisable() {

    }

}
