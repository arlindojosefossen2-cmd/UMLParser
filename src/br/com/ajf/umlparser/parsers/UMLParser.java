package br.com.ajf.umlparser.parsers;

import br.com.ajf.umlparser.utils.UMLParserInfo;

import java.util.LinkedList;
import java.util.List;

/**
 * The Class UMLParser.
 */
//Annotated: Load the package and class info for tooltips.
public final class UMLParser implements IUMLParser
{
    
    /** The pk. */
    private String pk;
    
    /** The name. */
    private String name;
    
    /** The fields. */
    private List<String> fields;
    
    /** The constructors. */
    private List<String> constructors;
    
    /** The methods. */
    private List<String> methods;
    
    
    /**
     * Instantiates a new UML parser.
     */
    public UMLParser()
	{
		
	}

	/**
     * Gets the name.
     *
     * @return the name
     */
    @Override
    public String getName()
    {
        return name;
    }
    
    /**
     * Gets the package.
     *
     * @return the package
     */
    @Override
    public String getPackage()
    {
        return pk;
    }
    
    /**
     * Fields.
     *
     * @return the list
     */
    @Override
    public List<String> fields()
    {
        return fields;
    }
    
    /**
     * Constructors.
     *
     * @return the list
     */
    @Override
    public List<String> constructors()
    {
        return constructors;
    }
    
    /**
     * Methods.
     *
     * @return the list
     */
    @Override
    public List<String> methods()
    {
        return methods;
    }
    
    /**
     * Parses the.
     *
     * @param cls the cls
     */
    @Override
    public void parse(Class<?> cls)
    {
        parse(cls,true,true,true);
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
    @Override
    public void parse(Class<?> cls, boolean privateFieldsToo,
                      boolean privateMethodsToo, boolean privateConstructorsToo,
                      boolean staticFieldsToo, boolean staticMethodsToo)
    {
        pk = IUMLParser.parsePackage(cls);
        name = IUMLParser.parseName(cls);
        final FieldsParser fieldsParser = new FieldsParser();
        final MethodsParser methodsParser = new MethodsParser();
        final ConstructorsParser constructorsParser = new ConstructorsParser();
        
        this.fields = new LinkedList<>(fieldsParser.parseAllFields(cls,privateFieldsToo,staticFieldsToo));
        this.methods = new LinkedList<>(methodsParser.parseAllMethods(cls,staticMethodsToo,privateMethodsToo));
        this.constructors = new LinkedList<>(constructorsParser.parse(cls,privateConstructorsToo));
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
    @Override
    public void parse(String classPackageName, boolean privateFieldsToo,
                      boolean privateMethodsToo, boolean privateConstructorsToo,
                      boolean staticFieldsToo, boolean staticMethodsToo)
    {
        try
        {
            parse(Class.forName(classPackageName),privateFieldsToo,privateMethodsToo,privateConstructorsToo,staticFieldsToo,staticMethodsToo);
        }
        catch (ClassNotFoundException e)
        {
            UMLParserInfo.show("ERROR", "ClassNotFoundException:\n"
                                        + e.getMessage() + "\nClass not exist int this package.\n");
        }
    }
}