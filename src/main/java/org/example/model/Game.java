package org.example.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Game {
   private int id;
    private String name;
    private String type;
    private int rating;


}
