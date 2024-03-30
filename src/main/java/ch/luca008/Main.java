package ch.luca008;

import ch.luca008.SpigotApi.SpigotApi;
import ch.luca008.SpigotApi.Utils.Logger;
import org.bukkit.command.TabCompleter;
import org.bukkit.plugin.java.JavaPlugin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class Main extends JavaPlugin {

    private static Main instance;
    public static Main getInstance(){return instance;}

    private List<Warp> warpList;

    public void onEnable()
    {
        instance = this;
        if(!getDataFolder().exists())
            getDataFolder().mkdirs();
        loadWarps();
        getCommand("warp").setExecutor(new WarpCommand());
        getCommand("warp").setTabCompleter(getCompleter());
        getCommand("setwarp").setExecutor(new SetWarpCommand());
        getCommand("unsetwarp").setExecutor(new UnsetWarpCommand());
        getCommand("unsetwarp").setTabCompleter(getCompleter());
        getCommand("warps").setExecutor(new WarpsCommand());
    }

    private TabCompleter getCompleter()
    {
        return (a,b,c,d)->{
            if(d.length == 1)
                return getWarpList().stream().filter(Warp::isValid).map(Warp::getName).toList();
            else
                return List.of();
        };
    }

    private void loadWarps()
    {
        warpList = Arrays.stream(getDataFolder().listFiles())
                .map(SpigotApi.getJSONApi()::readerFromFile)
                .map(Warp::new)
                .filter(Warp::isValid)
                .peek(w-> Logger.info("Loaded warp " + w.getName() + "!", Main.class.getName()))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public List<Warp> getWarpList()
    {
        return new ArrayList<>(warpList);
    }

    public Optional<Warp> findWarp(String name)
    {
        return warpList.stream().filter(w->w.getName().equals(name)).findFirst();
    }

    public boolean deleteWarp(@Nonnull Warp warp)
    {
        if(warpList.remove(warp))
        {
            File f = new File(getDataFolder(), warp.getName()+".json");
            if(f.exists())
            {
                return f.delete();
            }
        }
        return false;
    }

    public boolean createWarp(@Nonnull Warp warp)
    {
        if(warpList.contains(warp))
        {
            return false;
        }
        warpList.add(warp);
        warp.toFile();
        return true;
    }

}