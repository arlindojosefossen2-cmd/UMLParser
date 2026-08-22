package br.com.ajf.umlparser.parsers;

import java.lang.reflect.Constructor;
import java.lang.reflect.Modifier;
import java.lang.reflect.Parameter;
import java.util.LinkedList;
import java.util.List;

/**
 * The Class ConstructorsParser.
 */
public final class ConstructorsParser
{
    
    /**
     * Instantiates a new constructors parser.
     */
    public ConstructorsParser()
    {
    
    }
    
    /**
     * Parses the.
     *
     * @param cls the cls
     * @param privateConstructorsToo the private constructors too
     * @return the list
     */
    public List<String> parse(Class<?> cls, boolean privateConstructorsToo)
    {
        final List<String> parsed = new LinkedList<>();
        
        final StringBuilder sb = new StringBuilder();
        
        final Constructor<?>[] constructors = cls.getDeclaredConstructors();
        
        for (Constructor<?> c : constructors)
        {
            if(Modifier.isPublic(c.getModifiers()))
            {
                parseConstructor(parsed,sb,c,"+");
            }
            else if(Modifier.isProtected(c.getModifiers()))
            {
                parseConstructor(parsed,sb,c,"*");
            }
            else if(Modifier.isPrivate(c.getModifiers()) && privateConstructorsToo)
            {
                parseConstructor(parsed,sb,c,"-");
            }
        }
        
        return parsed;
    }
    
    /**
     * Parses the constructor.
     *
     * @param parsed the parsed
     * @param sb the sb
     * @param c the c
     * @param type the type
     */
    private void parseConstructor(List<String> parsed, StringBuilder sb, Constructor<?> c, String type)
    {
        final String[] name = c.getName().split("\\.");
    
        sb.append(" %s ".formatted(type)).append(name[name.length-1]).append("(");
        
        final Parameter[] parameters = c.getParameters();
        
        for (int i = 0; i < parameters.length; i++)
        {
            final Parameter p = parameters[i];
            final String[] nn = p.getName().split("\\.");
            final String na = nn[nn.length-1];
            final String[] t = p.getType().getName().split("\\.");
            final String ty = t[t.length-1];
            
            if(i == parameters.length-1)
            {
                sb.append(na).append(" : ").append(ty);
            }
            else
            {
                sb.append(na).append(" : ").append(ty).append(" , ");
            }
        }
        
        sb.append(");");
        parsed.add(sb.toString());
        sb.delete(0,sb.length());
    }
}