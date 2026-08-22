package br.com.ajf.umlparser.test;

import br.com.ajf.umlparser.utils.UMLParserToolTip;

/**
 * The Class Person.
 */
@UMLParserToolTip(Author = "AJF",
                  Date = "Today: 21/32/43",
                  Version = "1.0",
                  Since = "1.0",
                  Package = "br.com.ajf.umlparser.test",
                  Description = "Person class to test.")
public class Person
{
    
    /** The id. */
    public static int id = 0;
    
    /** The Other. */
    private static String Other = "Other";
    
    /** The gender. */
    protected String gender;
    
    /** The name. */
    private String name;
    
    /** The age. */
    private int age;
    
    /**
     * Instantiates a new person.
     */
    public Person()
    {
        this(Other);
    }
    
    /**
     * Instantiates a new person.
     *
     * @param name the name
     */
    protected Person(String name)
    {
        this.name = name;
    }
    
    /**
     * Instantiates a new person.
     *
     * @param name the name
     * @param age the age
     */
    public Person(String name, int age)
    {
        this("",name,age);
    }
    
    /**
     * Instantiates a new person.
     *
     * @param gender the gender
     * @param name the name
     * @param age the age
     */
    private Person(String gender, String name, int age)
    {
        this.gender = gender;
        this.name = name;
        this.age = age;
    }
    
    /**
     * Gets the id.
     *
     * @return the id
     */
    public static int getId()
    {
        return id;
    }
    
    /**
     * Sets the id.
     *
     * @param id the new id
     */
    public static void setId(int id)
    {
        Person.id = id;
    }
    
    /**
     * Gets the other.
     *
     * @return the other
     */
    public static String getOther()
    {
        return Other;
    }
    
    /**
     * Sets the other.
     *
     * @param other the new other
     */
    public static void setOther(String other)
    {
        Other = other;
    }
    
    /**
     * Gets the gender.
     *
     * @return the gender
     */
    public String getGender()
    {
        return gender;
    }
    
    /**
     * Sets the gender.
     *
     * @param gender the new gender
     */
    public void setGender(String gender)
    {
        this.gender = gender;
    }
    
    /**
     * Gets the name.
     *
     * @return the name
     */
    public String getName()
    {
        return name;
    }
    
    /**
     * Sets the name.
     *
     * @param name the new name
     */
    public void setName(String name)
    {
        this.name = name;
    }
    
    /**
     * Gets the age.
     *
     * @return the age
     */
    public int getAge()
    {
        return age;
    }
    
    /**
     * Sets the age.
     *
     * @param age the new age
     */
    public void setAge(int age)
    {
        this.age = age;
    }
    
    /**
     * Done.
     */
    private static void done()
    {
    
    }
    
    /**
     * Done.
     *
     * @param age the age
     */
    protected static void done(int age)
    {
        done();
        age--;
    }
    
    /**
     * For ward.
     */
    protected void forWard()
    {
        forWard(Other);
    }
    
    /**
     * For ward.
     *
     * @param other the other
     */
    private void forWard(String other)
    {
    
    }
}