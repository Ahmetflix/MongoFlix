package dev.Ahmetflix.MongoFlix;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.FIELD)
public @interface FlixField {

	String fieldName();
	
	boolean isObjectId() default false; // If you have objectId on your fields make this true 
}
