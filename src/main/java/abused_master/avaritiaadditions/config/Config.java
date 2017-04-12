package abused_master.avaritiaadditions.config;

import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

import java.io.File;

public class Config {

    private static final String AVARITIA_ADDITIONS = "AvaritiaAdditions";

    public static String configpath;
    public static Configuration config;

    public static int IronSingularity;
    public static int GoldSingularity;
    public static int LapisSingularity;
    public static int RedstoneSingularity;
    public static int QuartzSingularity;
    public static int CopperSingularity;
    public static int TinSingularity;
    public static int LeadSingularity;
    public static int SilverSingularity;
    public static int NickelSingularity;
    public static int NeutronCollector;

    public static void init(FMLPreInitializationEvent event) {

        configpath = event.getModConfigurationDirectory().getAbsolutePath() + File.separator;
        config = new Configuration(new File(configpath + "AvaritiaAdditions.cfg"));
        try {
            config.load();
            Config.configure(config);
        } catch (Exception e1) {
            System.out.println("Error Loading Config File: AvaritiaAdditions.cfg");
        } finally {
            if (config.hasChanged()) {
                config.save();
            }
        }
    }

    public static void configure(Configuration config) {

        config.addCustomCategoryComment(AVARITIA_ADDITIONS, "Set to amount of blocks required for a singularity");
        IronSingularity = config.getInt("IronSingularity", AVARITIA_ADDITIONS, 400, 1, 1000000, "");
        GoldSingularity = config.getInt("GoldSingularity", AVARITIA_ADDITIONS, 200, 1, 1000000, "");
        LapisSingularity = config.getInt("LapisSingularity", AVARITIA_ADDITIONS, 400, 1, 1000000, "");
        RedstoneSingularity = config.getInt("RedstoneSingularity", AVARITIA_ADDITIONS, 500, 1, 1000000, "");
        QuartzSingularity = config.getInt("QuartzSingularity", AVARITIA_ADDITIONS, 300, 1, 1000000, "");
        CopperSingularity = config.getInt("CopperSingularity", AVARITIA_ADDITIONS, 400, 1, 1000000, "");
        TinSingularity = config.getInt("TinSingularity", AVARITIA_ADDITIONS, 400, 1, 1000000, "");
        LeadSingularity = config.getInt("LeadSingularity", AVARITIA_ADDITIONS, 300, 1, 1000000, "");
        SilverSingularity = config.getInt("SilverSingularity", AVARITIA_ADDITIONS, 300, 1, 1000000, "");
        NeutronCollector = config.getInt("NeutronCollector", AVARITIA_ADDITIONS, 7111, 1, 1000000, "Set the amount of ticks before a pile of neutrons is created");
    }

}
