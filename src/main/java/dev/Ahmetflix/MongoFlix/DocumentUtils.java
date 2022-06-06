package dev.Ahmetflix.MongoFlix;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.logging.Logger;

import org.bson.Document;

public class DocumentUtils {

	public static Document mapToDocument(Map<String, Object> map) {
		Document document = new Document();
		for (Entry<String, Object> entry : map.entrySet()) {
			document.put(entry.getKey(), entry.getValue());
		}
		return document;
	}
	
	public static Document objectToDocument(Object obj) {
		Document document = new Document();
		Class<?> clazz = obj.getClass();
		
		Field[] fields = clazz.getDeclaredFields();
		List<Field> flixFields = new ArrayList<>();
		for (Field field : fields) {
			boolean hasFlixAnnotation = false;
			Annotation[] annotations = field.getAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation.annotationType() == FlixField.class) {
					hasFlixAnnotation = true;
				}
			}
			if (hasFlixAnnotation) {
				flixFields.add(field);
			}
		}
		
		for (Field field : flixFields) {
			field.setAccessible(true);
			String dataName = field.getAnnotation(FlixField.class).isObjectId() ? "_id" : field.getAnnotation(FlixField.class).fieldName();
			try {
				document.put(dataName, field.get(obj));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				Logger.getLogger("MongoFlix").severe(e.getMessage());
			}
		}
		
		return document;
	}
	
	public static Object documentToObject(Document doc, Object emptyObject) {
		Object obj = emptyObject;
		Class<?> clazz = emptyObject.getClass();
		
		Field[] fields = clazz.getDeclaredFields();
		List<Field> flixFields = new ArrayList<>();
		for (Field field : fields) {
			boolean hasFlixAnnotation = false;
			Annotation[] annotations = field.getAnnotations();
			for (Annotation annotation : annotations) {
				if (annotation.annotationType() == FlixField.class) {
					hasFlixAnnotation = true;
				}
			}
			if (hasFlixAnnotation) {
				flixFields.add(field);
			}
		}
		
		for (Field field : flixFields) {
			field.setAccessible(true);
			String dataName = field.getAnnotation(FlixField.class).isObjectId() ? "_id" : field.getAnnotation(FlixField.class).fieldName();
			try {
				field.set(obj, doc.getOrDefault(dataName, null));
			} catch (IllegalArgumentException | IllegalAccessException e) {
				Logger.getLogger("MongoFlix").severe(e.getMessage());
			}
		}
		
		return obj;
	}
	
}
