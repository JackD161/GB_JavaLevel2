package homework7ver4;

public class User {
    private String name;
    private String pass;
    private String nick;

    public User(String name, String pass, String nick)
    {
        this.name = name;
        this.pass = pass;
        this.nick = nick;
    }

    public User()
    {
        this.name = "Defaut name";
        this.pass = "777";
        this.nick = "Simple User";
    }

    public String getName()
    {
        return name;
    }
    public String getNick()
    {
        return nick;
    }
}
