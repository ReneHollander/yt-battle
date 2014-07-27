package at.er.ytbattle.plugin.timer.timeables;

import java.util.Random;

import org.bukkit.Bukkit;
import org.bukkit.Color;
import org.bukkit.FireworkEffect;
import org.bukkit.FireworkEffect.Type;
import org.bukkit.World;
import org.bukkit.entity.Firework;
import org.bukkit.entity.Player;
import org.bukkit.inventory.meta.FireworkMeta;

public class FireworkTimer implements Runnable {

    private int count = 0;

    private int id;

    public FireworkTimer() {
        this.count = 0;
    }

    public void setID(int id) {
        this.id = id;
    }

    @Override
    public void run() {
        count++;
        if (count > 15) {
            for (World w : Bukkit.getWorlds()) {
                w.setTime(6000);
                w.setStorm(false);
            }
            Bukkit.getScheduler().cancelTask(this.id);
        } else {
            for (World w : Bukkit.getWorlds()) {
                w.setTime(18000);
                w.setStorm(false);
            }
            shootFirework();
        }

    }

    public void shootFirework() {
        for (Player p : Bukkit.getOnlinePlayers()) {
            Firework f = p.getWorld().spawn(p.getLocation(), Firework.class);
            FireworkMeta fm = f.getFireworkMeta();
            Random r = new Random();
            int fType = r.nextInt(5) + 1;
            Type type = null;
            switch (fType) {
                default:
                case 1:
                    type = Type.BALL;
                    break;
                case 2:
                    type = Type.BALL_LARGE;
                    break;
                case 3:
                    type = Type.BURST;
                    break;
                case 4:
                    type = Type.CREEPER;
                    break;
                case 5:
                    type = Type.STAR;
                    break;
            }

            int c1i = r.nextInt(18) + 1;
            int c2i = r.nextInt(18) + 1;

            Color c1 = getColour(c1i);
            Color c2 = getColour(c2i);

            FireworkEffect effect = FireworkEffect.builder().flicker(r.nextBoolean()).withColor(c1).withFade(c2).with(type).trail(r.nextBoolean()).build();
            fm.addEffect(effect);
            int power = r.nextInt(2) + 1;
            fm.setPower(power);

            f.setFireworkMeta(fm);
        }
    }

    public Color getColour(int c) {
        switch (c) {
            default:
                return Color.AQUA;
            case 1:
                return Color.AQUA;
            case 2:
                return Color.BLACK;
            case 3:
                return Color.BLUE;
            case 4:
                return Color.FUCHSIA;
            case 5:
                return Color.GRAY;
            case 6:
                return Color.GREEN;
            case 7:
                return Color.LIME;
            case 8:
                return Color.MAROON;
            case 9:
                return Color.NAVY;
            case 10:
                return Color.OLIVE;
            case 11:
                return Color.ORANGE;
            case 12:
                return Color.PURPLE;
            case 13:
                return Color.PURPLE;
            case 14:
                return Color.RED;
            case 15:
                return Color.SILVER;
            case 16:
                return Color.TEAL;
            case 17:
                return Color.WHITE;
            case 18:
                return Color.YELLOW;
        }
    }
}
