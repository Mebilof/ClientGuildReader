package com.example.demo.Model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.springframework.stereotype.Component;

import java.sql.Time;


@Component
@Data
public class GuildMember {
    //private Long id;
    private String username;
    private String time;
    private String rank;
    private Payments payments;
    private String notes;
}
