package br.com.ajf.umlparser.parsers;

import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.LinkedList;
import java.util.List;

/**
 * The Class MethodsParser.
 */
public final class MethodsParser
{
    
    /**
     * Instantiates a new methods parser.
     */
    public MethodsParser()
    {
    
    }
    
    /**
     * Parses the all methods.
     *
     * @param cls the cls
     * @return the list
     */
    public List<String> parseAllMethods(Class<?> cls)
    {
        return parseAllMethods(cls,true);
    }
    
    /**
     * Parses the all methods.
     *
     * @param cls the cls
     * @param privateMethodsToo the private methods too
     * @return the list
     */
    public List<String> parseAllMethods(Class<?> cls,boolean privateMethodsToo)
    {
        return parseAllMethods(cls,true,privateMethodsToo);
    }
    
    /**
     * Parses the all methods.
     *
     * @param cls the cls
     * @param staticMethodsToo the static methods too
     * @param privateMethodsToo the private methods too
     * @return the list
     */
    public List<String> parseAllMethods(Class<?> cls,boolean staticMethodsToo,boolean privateMethodsToo)
    {
        return new LinkedList<>(parseObjectMethods(cls, privateMethodsToo, staticMethodsToo));
    }
    
    /**
     * Parses the object methods.
     *
     * @param cls the cls
     * @param privateMethodsToo the private methods too
     * @param staticMethodsToo the static methods too
     * @return the list
     */
    public List<String> parseObjectMethods(Class<?> cls,boolean privateMethodsToo,boolean staticMethodsToo)
    {
        final List<String> parsed = new LinkedList<>();
        
        final StringBuilder sb = new StringBuilder();
        
        final Method[] methods = cls.getDeclaredMethods();
        
        for (Method m : methods)
        {
            if(!Modifier.isStatic(m.getModifiers()))
            {
                if(Modifier.isPrivate(m.getModifiers()) && privateMethodsToo)
                {
                    m.setAccessible(true);
                    parseObjectMethods(parsed,sb,m,"-");
                }
                else if(Modifier.isPublic(m.getModifiers()))
                {
                    parseObjectMethods(parsed,sb,m,"+");
                }
                else if(Modifier.isProtected(m.getModifiers()))
                {
                    parseObjectMethods(parsed,sb,m,"*");
                }
            }
            else if(Modifier.isStatic(m.getModifiers()) && staticMethodsToo)
            {
                if(Modifier.isPrivate(m.getModifiers()) && privateMethodsToo)
                {
                    m.setAccessible(true);
                    parseStaticMethods(parsed,sb,m,"-");
                }
                else if(Modifier.isPublic(m.getModifiers()))
                {
                    parseStaticMethods(parsed,sb,m,"+");
                }
                else if(Modifier.isProtected(m.getModifiers()))
                {
                    parseStaticMethods(parsed,sb,m,"*");
                }
            }
        }
        
        return parsed;
    }
    
    /**
     * Parses the object methods.
     *
     * @param parsed the parsed
     * @param sb the sb
     * @param m the m
     * @param type the type
     */
    private void parseObjectMethods(List<String> parsed, StringBuilder sb, Method m, String type)
    {
        final String name = m.getName();
        final String[] array = m.getReturnType().getName().split("\\.");
        
        sb.append(" %s %s(".formatted(type,name));
        
        final Parameter[] parameters = m.getParameters();
        
        for (int i = 0; i < parameters.length; i++)
        {
            final Parameter p = parameters[i];
            final String[] n = p.getName().split("\\.");
            final String[] tp = p.getType().getName().split("\\.");
            
            if(i == parameters.length-1)
            {
                sb.append(" %s : %s ".formatted(n[n.length-1],tp[tp.length-1]));
            }
            else
            {
                sb.append(" %s : %s ,".formatted(n[n.length-1],tp[tp.length-1]));
            }
        }
        
        sb.append(") : ").append(array[array.length-1]).append(";");
        parsed.add(sb.toString());
        sb.delete(0,sb.length());
    }
    
    /**
     * Parses the static methods.
     *
     * @param parsed the parsed
     * @param sb the sb
     * @param m the m
     * @param type the type
     */
    private void parseStaticMethods(List<String> parsed, StringBuilder sb, Method m, String type)
    {
        final String name = m.getName();
        final String[] array = m.getReturnType().getName().split("\\.");
        
        sb.append(" %s static %s(".formatted(type,name));
        
        final Parameter[] parameters = m.getParameters();
        
        for (int i = 0; i < parameters.length; i++)
        {
            final Parameter p = parameters[i];
            final String[] n = p.getName().split("\\.");
            final String[] tp = p.getType().getName().split("\\.");
            
            if(i == parameters.length-1)
            {
                sb.append(" %s : %s ".formatted(n[n.length-1],tp[tp.length-1]));
            }
            else
            {
                sb.append(" %s : %s ,".formatted(n[n.length-1],tp[tp.length-1]));
            }
        }
        
        sb.append(") : ").append(array[array.length-1]).append(";");
        parsed.add(sb.toString());
        sb.delete(0,sb.length());
    }
}