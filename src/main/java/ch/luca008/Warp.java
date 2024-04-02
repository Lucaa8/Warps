package ch.luca008;

import ch.luca008.SpigotApi.Api.JSONApi.JSONReader;
import ch.luca008.SpigotApi.Api.JSONApi.JSONWriter;
import ch.luca008.SpigotApi.SpigotApi;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.OfflinePlayer;
import org.bukkit.World;
import org.bukkit.entity.Player;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.File;
import java.time.Instant;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.Objects;
import java.util.UUID;

public class Warp {

    private boolean isValid = false;
    private Location loc;
    private String name;
    private String description;
    private OfflinePlayer createdBy;
    private long createdAt;

    public Warp(@Nonnull JSONReader reader)
    {
        JSONReader location = reader.getJson("Location");
        World w = Bukkit.getWorld(location.getString("World"));
        if(w == null)
            return;
        this.loc = new Location(w,
                location.getDouble("X"),
                location.getDouble("Y"),
                location.getDouble("Z"),
                (float)location.getDouble("Yaw"),
                (float)location.getDouble("Pitch"));
        this.name = reader.getString("Name");
        this.description = reader.c("Description") ? reader.getString("Description") : "§cNone";
        this.createdBy = Bukkit.getOfflinePlayer(UUID.fromString(reader.getString("CreatedBy")));
        this.createdAt = reader.getLong("CreatedAt");
        this.isValid = true;
    }

    public Warp(@Nonnull Location loc, @Nonnull String name, @Nullable String description, @Nonnull OfflinePlayer createdBy) {
        this.loc = loc;
        this.name = name;
        this.description = description == null ? "§cNone" : description;
        this.createdBy = createdBy;
        this.createdAt = System.currentTimeMillis();
        this.isValid = true;
    }

    public void toFile()
    {
        if(!isValid())
            return;
        File f = new File(Main.getInstance().getDataFolder(), this.name+".json");
        if(f.exists())
            return;
        JSONWriter writer = SpigotApi.getJSONApi().getWriter(null);
        writer
                .write("Location.X", loc.getX())
                .write("Location.Y", loc.getY())
                .write("Location.Z", loc.getZ())
                .write("Location.Yaw", loc.getYaw())
                .write("Location.Pitch", loc.getPitch())
                .write("Location.World", loc.getWorld().getName())
                .write("Name", name)
                .write("Description", description)
                .write("CreatedBy", createdBy.getUniqueId())
                .write("CreatedAt", createdAt);
        writer.writeToFile(f, true);
    }

    public boolean isValid() {
        return isValid;
    }

    public Location getLoc() {
        return loc.clone();
    }

    public String getName() {
        return this.name;
    }

    public String getDescription() {
        return description;
    }

    public OfflinePlayer getCreatedBy() {
        return createdBy;
    }

    public long getCreatedAt() {
        return createdAt;
    }

    private String getCreatedTimeAgo() {
        Instant creation = Instant.ofEpochMilli(createdAt);
        ZonedDateTime now = ZonedDateTime.now();
        ZonedDateTime creationDateTime = ZonedDateTime.ofInstant(creation, ZoneId.of("Europe/Paris"));

        long years = ChronoUnit.YEARS.between(creationDateTime, now);
        long months = ChronoUnit.MONTHS.between(creationDateTime, now);
        long days = ChronoUnit.DAYS.between(creationDateTime, now);
        long hours = ChronoUnit.HOURS.between(creationDateTime, now);
        long minutes = ChronoUnit.MINUTES.between(creationDateTime, now);

        String ago = "Créé il y a §b";
        if (years > 0) {
            long monthsInYear = months - years * 12;
            return ago + years + " ans"+ (monthsInYear > 0 ? " et " + monthsInYear + " mois" : "");
        } else if (months > 0) {
            long daysInMonth = days - months * 30;
            return ago + months + " mois" + (daysInMonth > 0 ? " et " + daysInMonth + " jours" : "");
        } else if (days > 0) {
            return ago + days + " jours";
        } else if (hours > 0) {
            return ago + hours + " heures";
        } else if (minutes > 0) {
            return ago + minutes + " minutes";
        } else {
            return ago + "quelques secondes";
        }
    }

    public boolean teleport(@Nonnull Player player)
    {
        if(!isValid())
            return false;
        player.teleport(loc);
        return true;
    }

    @Override
    public String toString() {
        if(!isValid())
            return "Invalid Warp";
        return "§f- §6" + getName() + " §f("+getCreatedTimeAgo()+"§f par §b"+getCreatedBy().getName()+"§f)\nDescription: §7" + getDescription().replace('&', '§');
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Warp warp)) return false;
        return name.equals(warp.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
