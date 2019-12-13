package se.kth.homework_4.model;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
@Table(name = "currency")
public class Currency {

    @Id
    @Column(name = "id")
    @NotNull
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotBlank(message = "")
    @Size(min = 3, max = 3, message = "Has to be three ")
    @NotNull
    @Column(name = "code")
    private String code;

    public Currency(){

    }

    public Currency(String code){
        this.code = code.toUpperCase();
    }

    public int getId() {
        return id;
    }

    public String getCode() {
        return code;
    }

    @Override
    public String toString() {
        return code;
    }
}
