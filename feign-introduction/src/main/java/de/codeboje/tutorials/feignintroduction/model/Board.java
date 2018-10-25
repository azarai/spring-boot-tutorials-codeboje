package de.codeboje.tutorials.feignintroduction.model;

import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
public class Board {

	private Long id;
	
    private String name;
}
