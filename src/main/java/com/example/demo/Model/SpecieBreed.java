package com.example.demo.Model;

import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
@Data
@NoArgsConstructor
@AllArgsConstructor
public class SpecieBreed {
    private String specie;
    private String breed;

    @Override
    public String toString() {
        return "SpecieBreed{" +
                "specie='" + specie + '\'' +
                ", breed='" + breed + '\'' +
                '}';
    }
}
