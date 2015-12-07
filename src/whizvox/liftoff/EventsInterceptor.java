package whizvox.liftoff;

import org.bukkit.ChatColor;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.block.Sign;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.block.BlockPlaceEvent;
import org.bukkit.event.block.SignChangeEvent;
import org.bukkit.event.player.PlayerInteractEvent;

public class EventsInterceptor implements Listener {

    @EventHandler
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (event.getAction() == Action.RIGHT_CLICK_BLOCK && event.getClickedBlock() != null && event.getClickedBlock().getType() == Material.STONE_BUTTON) {
            Location loc = event.getClickedBlock().getLocation().clone();
            // 0 = conditions not met
            // 1 = look for signs below
            // 2 = look for signs above
            byte status = 0;
            if (validElevatorSign(loc.add(0, 1, 0).getBlock())) {
                status = 1;
            } else if (validElevatorSign(loc.add(0, -2, 0).getBlock())) {
                status = 2;
            }
            if (status != 0) {
                Player player = event.getPlayer();
                if (player.hasPermission("liftoff.use")) {
                    Location destSign = null;
                    for (int i = 0; i < Config.getSignRange(); i++) {
                        Block block = loc.add(0, status == 1 ? -1 : 1, 0).getBlock();
                        if (validElevatorSign(block)) {
                            destSign = loc;
                            break;
                        }
                    }
                    if (destSign == null) {
                        player.sendMessage("No destination was found!");
                    } else {
                        boolean solidGroundFound = false;
                        for (int i = 0; i < Config.getSolidGroundRange(); i++) {
                            loc.add(0, -1, 0);
                            Block block = loc.getBlock();
                            if (validSolidBlock(block)) {
                                solidGroundFound = true;
                                break;
                            }
                        }
                        if (solidGroundFound) {
                            loc.setYaw(player.getLocation().getYaw());
                            loc.setPitch(player.getLocation().getPitch());
                            player.teleport(loc.add(0.5, 1, 0.5));
                        } else {
                            player.sendMessage("No solid ground has been found!");
                        }
                    }
                } else {
                    player.sendMessage(ChatColor.RED + "You don't have permission to use elevators.");
                }
            }
        }
    }

    @EventHandler
    public void onSignEdited(SignChangeEvent event) {
        if (Config.signColoringEnabled()) {
            if (event.getBlock().getType() == Material.WALL_SIGN && event.getLine(0).endsWith("[Elevator]")) {
                if (event.getPlayer().hasPermission("liftoff.place")) {
                    event.setLine(0, ChatColor.DARK_BLUE + "[Elevator]");
                } else {
                    event.getPlayer().sendMessage(ChatColor.RED + "You are not allowed to place elevator signs!");
                    event.setLine(0, "[ERROR]");
                }
            }
        }
    }

    private static boolean validElevatorSign(Block block) {
        if (block != null && block.getType() == Material.WALL_SIGN) {
            final String[] lines = ((Sign) block.getState()).getLines();
            return lines.length > 0 && lines[0].endsWith("[Elevator]");
        }
        return false;
    }

    private static boolean validSolidBlock(Block block) {
        Material mat = block == null ? null : block.getType();
        // For some reason, SIGN_POST and WALL_SIGN are considered "solid" blocks.
        return mat != null && mat.isSolid() && mat != Material.SIGN_POST && mat != Material.WALL_SIGN;
    }

}
