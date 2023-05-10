package yv.tils.dc.utils.language;

import org.bukkit.Bukkit;
import org.bukkit.configuration.file.YamlConfiguration;
import yv.tils.dc.DiscordPlugin;
import yv.tils.dc.utils.ConsoleLog;

import java.io.File;
import java.util.List;

public class LanguageFile {

    private static YamlConfiguration yamlConfiguration;

    public static void LanguageFileGet() {
        File file = new File(DiscordPlugin.getInstance().getDataFolder(),"language.yml");
        if (file.exists()) {
            yamlConfiguration = YamlConfiguration.loadConfiguration(file);
        }else {
            new ConsoleLog(file.getPath());
        }
    }

    public static String getMessage(LanguageMessage message) {
        return yamlConfiguration.getString(message.name().toUpperCase());
    }

    public String ListReplacer(String InPut, List<String> ToReplace, List<String> Insert) {
        new ConsoleLog(InPut);
        new ConsoleLog("StringReplacer1");
        for (int i = 0; i < ToReplace.size(); i++) {
            InPut = InPut.replaceAll(ToReplace.get(i), Insert.get(i));
        }
        new ConsoleLog("StringReplacer2");
        new ConsoleLog(InPut);
        return InPut;
    }

}
