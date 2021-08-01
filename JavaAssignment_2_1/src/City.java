public class City implements Comparable<City> {
    private String name;
    private String countryCode;
    private Integer population;

    public City(String name, String countryCode, String population) {
        this.name = name;
        this.countryCode = countryCode;
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
