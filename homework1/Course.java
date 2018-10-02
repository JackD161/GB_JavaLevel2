package homework1;

import java.util.Random;

public class Course {
    private String itemCourse;
    private int heightCourse;
    private Course[] courses;
    private Random random = new Random();
    private int count;

    Course()
    {
        count = 4;
        courses = new Course[count];
        for (int i = 0; i < count; i++)
        {
            courses[i] = new Course("Забор", random.nextInt(6));
        }
    }

    Course(int count)
    {
        this.count = count;
        courses = new Course[count];
        for (int i = 0; i < count; i++)
        {
            courses[i] = new Course("Преграда", random.nextInt(6));
        }
    }

    Course(String itemCourse, int heightCourse)
    {
        this.itemCourse = itemCourse;
        this.heightCourse = heightCourse;
    }

    void doIt(Team team)
    {
        Runner[] teamRuning = team.getTeam();
        for (Runner runner : teamRuning)
        {
            int score = 0;
            for (Course item : courses)
            {
                if (item.heightCourse <= runner.getMaxJump())
                {
                    score++;
                }
                else
                {
                    score--;
                }
            }
            if (score == count)
            {
                team.addFinalized(runner.getName());
            }
        }
    }
}
