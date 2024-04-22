package app;


public class City {
   public String cityName;

   public float avgTemp;

   public float minTemp;

   public float maxTemp;

   public Integer year;


   public City() {
   }

   
   public City(String cityName, float avgTemp, float minTemp, float maxTemp, Integer year) {
      this.cityName = cityName;
      this.avgTemp = avgTemp;
      this.minTemp = minTemp;
      this.maxTemp = maxTemp;
      this.year = year;
   }
}