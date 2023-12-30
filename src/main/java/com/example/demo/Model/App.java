package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class App {
    private int id;
    private String status;
    private int petId;
    private String adopterUserName;

    @Override
    public String toString() {
        return "App{" +
                "id=" + id +
                ", status='" + status + '\'' +
                ", petId=" + petId +
                ", adopterUserName='" + adopterUserName + '\'' +
                '}';
    }
}
