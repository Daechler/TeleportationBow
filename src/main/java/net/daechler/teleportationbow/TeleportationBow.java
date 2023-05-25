package net.daechler.teleportationbow;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.ProjectileHitEvent;
import org.bukkit.plugin.java.JavaPlugin;

public class TeleportationBow extends JavaPlugin implements Listener {

    @Override
    public void onEnable() {
        // Register the plugin's event listener
        getServer().getPluginManager().registerEvents(this, this);

        // Display a green message indicating the plugin has been enabled
        getLogger().info(ChatColor.GREEN + getName() + " has been enabled!");
    }

    @Override
    public void onDisable() {
        // Display a red message indicating the plugin has been disabled
        getLogger().info(ChatColor.RED + getName() + " has been disabled!");
    }

    @EventHandler
    public void onProjectileHit(ProjectileHitEvent event) {
        if (!(event.getEntity() instanceof Arrow)) {
            return; // Ignore if the entity is not an arrow
        }

        Arrow arrow = (Arrow) event.getEntity();
        if (!(arrow.getShooter() instanceof Player)) {
            return; // Ignore if the shooter is not a player
        }

        Player shooter = (Player) arrow.getShooter();
        if (!shooter.hasPermission("teleportationbow.use")) {
            return; // Ignore if the shooter does not have the required permission
        }

        Location hitLocation = arrow.getLocation();
        shooter.teleport(hitLocation); // Teleport the shooter to the arrow's hit location
    }
}
