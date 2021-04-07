package com.example.demo.cartms.domain.queries;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FindCartQuery {
    private String cartNumber;
}
