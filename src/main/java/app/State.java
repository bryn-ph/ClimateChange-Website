package app;


public class State {
   public String stateName;

   public float avgTemp;

   public float minTemp;

   public float maxTemp;

   public Integer year;


   public State() {
   }

   
   public State(String stateName, float avgTemp, float minTemp, float maxTemp, Integer year) {
      this.stateName = stateName;
      this.avgTemp = avgTemp;
      this.minTemp = minTemp;
      this.maxTemp = maxTemp;
      this.year = year;
   }
}