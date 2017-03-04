package iss.nus.medipal.application.ice;

/**
 * Created by richard on 4/3/17.
 */

public class ICE {
    private int id;
    private String name;

    public ICE() {}

    public ICE(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "ICE{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }
}
