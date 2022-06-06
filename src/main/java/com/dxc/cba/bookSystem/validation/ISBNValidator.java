package com.dxc.cba.bookSystem.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * This class is to provide the custom validation for ISBN number.
 */
public class ISBNValidator  implements ConstraintValidator<ISBNValidation, Long>{

    @Override
    public boolean isValid(Long isbn, ConstraintValidatorContext context) {
       return String.valueOf(isbn).length() ==13?true:false;
    }
    
}
