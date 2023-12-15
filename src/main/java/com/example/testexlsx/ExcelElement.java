package com.example.testexlsx;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface ExcelElement {
  String nome() default "";
  Class<?> tipo() default void.class;
  String grupo() default "";

}
