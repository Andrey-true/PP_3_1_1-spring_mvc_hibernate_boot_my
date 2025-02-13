package web.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Positive;



import java.util.Objects;

@Entity
@Table(name = "user")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull(message = "Name may not be null")
    @Size(min = 2, max = 20, message = "Name must be between 2 and 20 characters")
    private String name;

    @NotNull(message = "Name may not be null")
    @Digits(integer = 2, message = "Age should be a number with up to 3 digits", fraction = 0)
    @Positive(message = "Price must be positive")
    private int age;

    @NotNull(message = "Name may not be null")
    @Size(min = 2, max = 15, message = "Name must be between 2 and 15 characters")
    private String city;


    public User() {
    }

    public User(String name, int age, String city) {
        this.name = name;
        this.age = age;
        this.city = city;
    }

    public String getName() {
        return name;
    }

    public void setName(String model) {
        this.name = model;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int series) {
        this.age = series;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String color) {
        this.city = color;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User user)) return false;
        return getAge() == user.getAge() && Objects.equals(getName(), user.getName()) && Objects.equals(getCity(), user.getCity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getAge(), getCity());
    }
}

