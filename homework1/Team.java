package homework1;

import java.util.ArrayList;

public class Team {
    private String title;
    private Runner[] team;
    private int numberRunners;
    private ArrayList<String> finalyzed;

    Team()
    {
        title = "Default Team";
        finalyzed = new ArrayList<>();
        numberRunners = 2;
        team = new Runner[numberRunners];
        team[0] = new Runner("Best", 6);
        team[1] = new Runner("Midle", 3);
    }

    Team(String titleTeam, int numberRunners)
    {
        title = titleTeam;
        finalyzed = new ArrayList<>();
        this.numberRunners = numberRunners;
        team = new Runner[numberRunners];
        for (int i = 0; i < numberRunners; i++)
        {
            team[i] = new Runner();
        }
    }

    void showResults()
    {
        System.out.println("Финалисты");
        for (String item : finalyzed)
        {
            System.out.println(item + " прошел полосу препятствий");
        }
    }

    void showTeam()
    {
        System.out.println("Состав команды " + title);
        for (Runner runner : team)
        {
            System.out.println(runner.getName());
        }
    }

    public int getNumberRunners() {
        return numberRunners;
    }

    public String getTitle() {
        return title;
    }

    public Runner[] getTeam() {
        return team;
    }

    public void addFinalized(String name)
    {
        finalyzed.add(name);
    }
}
