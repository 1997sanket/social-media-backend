package com.kamble.user;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Past;
import javax.validation.constraints.Size;
import java.time.LocalDate;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
    private Integer id;

    @Size(min = 3, message = "User should have atleast 3 characters.")
    private String name;

    @Past(message = "User's date of birth should be in the past.")
    private LocalDate dob;
}
