package intertwinedbasicandoauth2ssso.util;

import static java.lang.annotation.ElementType.ANNOTATION_TYPE;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;

@Retention(RUNTIME)
@Target({ TYPE, METHOD, ANNOTATION_TYPE })
@Constraint(validatedBy = FieldsValueMatchValidator.class)
public @interface FieldsValueMatch {

	String message() default "Fields values don't match!";
    Class<?>[] groups() default {};
    String field();
    String fieldMatch();

    @Target({ ElementType.TYPE })
    @Retention(RetentionPolicy.RUNTIME)
    @interface List {
        FieldsValueMatch[] value();
    }
}
