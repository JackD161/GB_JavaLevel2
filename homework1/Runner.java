package homework1;

import java.util.Random;

public class Runner {
    private String name;
    private int maxJump;
    private Random random = new Random();

    Runner()
    {
        name = "Player" + String.valueOf(random.nextInt(10));
        maxJump = random.nextInt(6);
    }

    Runner(String name, int maxJump)
    {
        this.name = name;
        this.maxJump = maxJump;
    }

    public String getName() {
        return name;
    }

    public int getMaxJump() {
        return maxJump;
    }
}
