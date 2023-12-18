package ru.sergsnic.springcourse.model;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
@Table(name = "Movie")
public class Movie {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "name")
    private String name;
    @Column(name = "year_of_prodaction")
    private int yearOfProdaction;
    @ManyToMany(mappedBy = "movies")
    private List<Actor> actors;

    public Movie() {
    }
    public Movie(String name, int yearOfProdaction) {
        this.name = name;
        this.yearOfProdaction = yearOfProdaction;
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

    public int getYearOfProdaction() {
        return yearOfProdaction;
    }

    public void setYearOfProdaction(int yearOfProdaction) {
        this.yearOfProdaction = yearOfProdaction;
    }

    public List<Actor> getActors() {
        return actors;
    }

    public void setActors(List<Actor> actors) {
        this.actors = actors;
    }

    @Override
    public String toString() {
        return "Movie{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", yearOfProdaction=" + yearOfProdaction +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Movie movie = (Movie) o;
        return id == movie.id && yearOfProdaction == movie.yearOfProdaction && Objects.equals(name, movie.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, yearOfProdaction);
    }
}
