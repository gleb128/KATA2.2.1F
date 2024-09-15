package hiber.model;

import javax.persistence.*;

@Entity
public class Car {
    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "model")
    private String model;
    @Column (name = "series'")
    private int series;


    public Car() {}

    public String getModel() {
        return model;
    }

    public int getSeries() {
        return series;
    }

    public void setModel(String model) {}

    public void setSeries(int series) {}

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "Car{" +
                "id=" + id +
                ", model='" + model + '\'' +
                ", series=" + series +
                '}';
    }

    public Long getId() {
        return id;


    }
}
