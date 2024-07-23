package blood.proverka;

import org.bukkit.boss.BossBar;
import org.bukkit.entity.Player;

public class ProverkaSession {
    private Player checker;
    private Player target;
    private BossBar bossBar;
    private boolean onHold;
    private boolean frozen;

    public ProverkaSession(Player checker, Player target, BossBar bossBar) {
        this.checker = checker;
        this.target = target;
        this.bossBar = bossBar;
        this.onHold = false;
        this.frozen = false;
    }

    public Player getChecker() {
        return checker;
    }

    public Player getTarget() {
        return target;
    }

    public BossBar getBossBar() {
        return bossBar;
    }

    public boolean isOnHold() {
        return onHold;
    }

    public void setOnHold(boolean onHold) {
        this.onHold = onHold;
    }

    public boolean isFrozen() {
        return frozen;
    }

    public void setFrozen(boolean frozen) {
        this.frozen = frozen;
    }
}