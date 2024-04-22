package app;

public class Persona{
  
   private String name;

   private String description;

   private String attributes;
   
   private String image;

  
   public Persona(String name, String description, String attributes, String image) {
      this.name = name;
      this.description = description;
      this.attributes = attributes;
      this.image = image;
   }

   public String getName() {
      return name;
   }

   public String getDscrp() {
      return description;
   }

   public String getAttr() {
      return attributes;
   }

   public String getImage() {
      return image;
   }
}

