package br.com.ajf.umlparser.parsers;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.LinkedList;
import java.util.List;

/**
 * The Class FieldsParser.
 */
public final class FieldsParser
{
    
    /**
     * Instantiates a new fields parser.
     */
    public FieldsParser()
    {
    
    }
    
    /**
     * Parses the all fields.
     *
     * @param cls the cls
     * @return the list
     */
    public List<String> parseAllFields(Class<?> cls)
    {
        return parseAllFields(cls,true);
    }
    
    /**
     * Parses the all fields.
     *
     * @param cls the cls
     * @param privateFieldsToo the private fields too
     * @return the list
     */
    public List<String> parseAllFields(Class<?> cls,boolean privateFieldsToo)
    {
        return parseAllFields(cls,true,privateFieldsToo);
    }
    
    /**
     * Parses the all fields.
     *
     * @param cls the cls
     * @param staticFieldsToo the static fields too
     * @param privateFieldsToo the private fields too
     * @return the list
     */
    public List<String> parseAllFields(Class<?> cls,boolean staticFieldsToo,boolean privateFieldsToo)
    {
        final List<String> parsed = new LinkedList<>(parseObjectFields(cls, privateFieldsToo));
        
        if(staticFieldsToo)
        {
            parsed.addAll(parseStaticFields(cls,privateFieldsToo));
        }
        
        return parsed;
    }
    
    /**
     * Parses the static fields.
     *
     * @param cls the cls
     * @param privateFieldsToo the private fields too
     * @return the list
     */
    public List<String> parseStaticFields(Class<?> cls,boolean privateFieldsToo)
    {
        final List<String> parsed = new LinkedList<>();
     
        final Field[] fields = cls.getDeclaredFields();
            
        for (Field f : fields)
        {
            if (Modifier.isStatic(f.getModifiers()) && Modifier.isPrivate(f.getModifiers()) && privateFieldsToo)
            {
                f.setAccessible(true);
                parseStaticFields(parsed,f,"-");
            }
            else if(Modifier.isPublic(f.getModifiers()) && Modifier.isStatic(f.getModifiers()))
            {
                parseStaticFields(parsed,f,"+");
            }
            else if(Modifier.isProtected(f.getModifiers()) && Modifier.isStatic(f.getModifiers()))
            {
                parseStaticFields(parsed,f,"*");
            }
        }
        
        return parsed;
    }
    
    /**
     * Parses the object fields.
     *
     * @param cls the cls
     * @param privateFieldsToo the private fields too
     * @return the list
     */
    public List<String> parseObjectFields(Class<?> cls,boolean privateFieldsToo)
    {
        final List<String> parsed = new LinkedList<>();
        
        final Field[] fields = cls.getDeclaredFields();
        
        for (Field f : fields)
        {
            if (!Modifier.isStatic(f.getModifiers()) && Modifier.isPrivate(f.getModifiers()) && privateFieldsToo)
            {
                f.setAccessible(true);
                parseObjectFields(parsed,f,"-");
            }
            else if(Modifier.isPublic(f.getModifiers()) && !Modifier.isStatic(f.getModifiers()))
            {
                parseObjectFields(parsed,f,"+");
            }
            else if(Modifier.isProtected(f.getModifiers()) && !Modifier.isStatic(f.getModifiers()))
            {
                parseObjectFields(parsed,f,"*");
            }
        }
        
        return parsed;
    }
    
    /**
     * Parses the object fields.
     *
     * @param parsed the parsed
     * @param f the f
     * @param type the type
     */
    private void parseObjectFields(List<String> parsed, Field f, String type)
    {
        final String name = f.getName();
        final String[] array = f.getType().getName().split("\\.");
        
        parsed.add(" %s %s : %s;".formatted(type,name,array[array.length-1]));
    }
    
    /**
     * Parses the static fields.
     *
     * @param parsed the parsed
     * @param f the f
     * @param type the type
     */
    private void parseStaticFields(List<String> parsed, Field f, String type)
    {
        final String name = f.getName();
        final String[] array = f.getType().getName().split("\\.");
        
        parsed.add(" %s static %s : %s;".formatted(type,name,array[array.length-1]));
    }
}