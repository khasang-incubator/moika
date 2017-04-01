package io.khasang.moika.entity;


import javax.persistence.*;

@Entity(name = "test")
public class Test {
    @Id
    @Column(name = "id", columnDefinition = "serial")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "name1")
    private String name1;

    @Column(name = "name2")
    private String name2;

    public Test() {
    }

    public int getId() {
        return id;
    }

    public String getName1() {
        return name1;
    }

    public void setName1(String name1) {
        this.name1 = name1;
    }

    public String getName2() {
        return name2;
    }

    public void setName2(String name2) {
        this.name2 = name2;
    }

    @Override
    public String toString() {
        return "Test Entity" + id +
                ", name1='" + name1 + '\'' +
                ", name2='" + name2 + '\'' +
                '}';
    }
}
