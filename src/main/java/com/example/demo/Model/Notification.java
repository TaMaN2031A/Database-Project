package com.example.demo.Model;

import jdk.jfr.Enabled;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;

import java.sql.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Notification {
    @Id
    private int ID;
    private String content;
    private Date date;
}
