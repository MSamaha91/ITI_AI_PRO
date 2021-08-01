
public class City implements Comparable<City> {
    private String name;
    private String countryCode;
    private Integer population;
    private boolean isCapital;

    public City(String name, String countryCode, String isCapital, String population) {
        this.name = name;
        this.countryCode = countryCode;
        this.isCapital = (isCapital.equalsIgnoreCase("primary"));
        try {
            this.population = Integer.parseInt(population.replace("\"", ""));
        } catch (Exception ex) {
            this.population = 0;
        }
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCountryCode() {
        return countryCode;
    }

    public void setCountryCode(String countryCode) {
        this.countryCode = countryCode;
    }

    public boolean isCapital() {
        return isCapital;
    }

    public void setCapital(boolean isCapital) {
        this.isCapital = isCapital;
    }

    public Integer getPopulation() {
        return population;
    }

    public void setPopulation(Integer population) {
        if (population > 0)
            this.population = population;
        else
            System.out.println("Invalid population. It must be more than 0");
    }

    @Override
    public String toString() {
        return "City{" +
                "name='" + name + '\'' +
                ", population=" + population +
                '}';
    }

    @Override
    public int compareTo(City otherCity) {
        return this.population.compareTo(otherCity.getPopulation());
    }
}
