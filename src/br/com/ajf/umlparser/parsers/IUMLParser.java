package br.com.ajf.umlparser.parsers;

import br.com.ajf.umlparser.utils.UMLParserInfo;

import java.lang.reflect.Modifier;
import java.util.List;

/**
 * The Interface IUMLParser.
 */
public interface IUMLParser
{
    
    /**
     * Gets the name.
     *
     * @return the name
     */
    String getName();
    
    /**
     * Gets the package.
     *
     * @return the package
     */
    String getPackage();
    
    /**
     * Fields.
     *
     * @return the list
     */
    List<String> fields();
    
    /**
     * Constructors.
     *
     * @return the list
     */
    List<String> constructors();
    
    /**
     * Methods.
     *
     * @return the list
     */
    List<String> methods();
    
    /**
     * Parses the.
     *
     * @param cls the cls
     */
    void parse(Class<?> cls);
    
    /**
     * Parses the.
     *
     * @param cls the cls
     * @param privateFieldsToo the private fields too
     * @param privateMethodsToo the private methods too
     * @param privateConstructorsToo the private constructors too
     */
    default void parse(Class<?> cls,
               boolean privateFieldsToo,
               boolean privateMethodsToo,
               boolean privateConstructorsToo)
    {
        parse(cls,privateFieldsToo,privateMethodsToo,privateConstructorsToo,false,false);
    }
    
   /**
    * Parses the.
    *
    * @param cls the cls
    * @param privateFieldsToo the private fields too
    * @param privateMethodsToo the private methods too
    * @param privateConstructorsToo the private constructors too
    * @param staticFieldsToo the static fields too
    * @param staticMethodsToo the static methods too
    */
   void parse(Class<?> cls,
               boolean privateFieldsToo,
               boolean privateMethodsToo,
               boolean privateConstructorsToo,
               boolean staticFieldsToo,
               boolean staticMethodsToo);
    
    /**
     * Parses the.
     *
     * @param classPackageName the class package name
     * @param privateFieldsToo the private fields too
     * @param privateMethodsToo the private methods too
     * @param privateConstructorsToo the private constructors too
     */
    default void parse(String classPackageName,
                       boolean privateFieldsToo,
                       boolean privateMethodsToo,
                       boolean privateConstructorsToo)
    {
        parse(classPackageName,privateFieldsToo,privateMethodsToo,privateConstructorsToo,false,false);
    }
    
    /**
     * Parses the.
     *
     * @param classPackageName the class package name
     * @param privateFieldsToo the private fields too
     * @param privateMethodsToo the private methods too
     * @param privateConstructorsToo the private constructors too
     * @param staticFieldsToo the static fields too
     * @param staticMethodsToo the static methods too
     */
    void parse(String classPackageName,
               boolean privateFieldsToo,
               boolean privateMethodsToo,
               boolean privateConstructorsToo,
               boolean staticFieldsToo,
               boolean staticMethodsToo);
    
    /**
     * Parses the.
     *
     * @param classForName the class for name
     */
    default void parse(String classForName)
    {
        try
        {
            parse(Class.forName(classForName));
        }
        catch (ClassNotFoundException e)
        {
            UMLParserInfo.show("ERROR", "ClassNotFoundException:\n"
                                        + e.getMessage() + "\nClass not exist int this package.\n");
        }
    }
    
    /**
     * Parses the name.
     *
     * @param cls the cls
     * @return the string
     */
    static String parseName(Class<?> cls)
    {
        final String[] array = cls.getName().split("\\.");
        final String name = array[array.length-1];
        final String type;
        
        if(cls.isInterface() && !cls.isAnnotation())
        {
            type = "Interface";
        }
        else if(cls.isAnnotation())
        {
            type = "Annotation";
        }
        else if(cls.isEnum())
        {
            type = "Enum";
        }
        else if(cls.isRecord())
        {
            type = "Record";
        }
        else
        {
            type = "Class";
        }
        
        if(Modifier.isStatic(cls.getModifiers()))
        {
            if(Modifier.isPublic(cls.getModifiers()))
            {
                return " + static %s : %s.".formatted(type,name);
            }
            else if(Modifier.isProtected(cls.getModifiers()))
            {
                return " * static %s : %s.".formatted(type,name);
            }
            else if (Modifier.isPrivate(cls.getModifiers()))
            {
                return " - static %s : %s.".formatted(type,name);
            }
        }
        else
        {
            if(Modifier.isPublic(cls.getModifiers()))
            {
                return " + %s : %s.".formatted(type,name);
            }
            else if(Modifier.isProtected(cls.getModifiers()))
            {
                return " * %s : %s.".formatted(type,name);
            }
            else if (Modifier.isPrivate(cls.getModifiers()))
            {
                return " - %s : %s.".formatted(type,name);
            }
        }
        
        return "";
    }
    
    /**
     * Parses the package.
     *
     * @param cls the cls
     * @return the string
     */
    static String parsePackage(Class<?> cls)
    {
        return " Package: %s.".formatted(cls.getPackageName());
    }
}