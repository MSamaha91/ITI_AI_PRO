package Assignment1;

public class Pyramid {
    private String pharaoh;
    private String modern_name;
    private String site;
    private double height;



    public Pyramid(String pharaoh, String modern_name, String site, String height) {
        this.pharaoh = pharaoh;
        this.modern_name = modern_name;
        this.site = site;
        try {
            this.height = Double.parseDouble(height);
        } catch (Exception ex) {
            this.height = -1;
        }
    }

    public String getPharaoh() {
        return pharaoh;
    }

    public void setPharaoh(String pharaoh) {
        this.pharaoh = pharaoh;
    }

    public void setModern_name(String modern_name) {
        this.modern_name = modern_name;
    }

    public String getModern_name() {
        return modern_name;
    }

    public String getSite() {
        return site;
    }

    public void setSite(String site) {
        this.site = site;
    }

    public void setHeight(double height) {
        if (height > 0)
            this.height = height;
        else
            System.out.println("Invalid height. It must be more than 0");
    }

    public double getHeight() {
        return height;
    }

    @Override
    public String toString() {
        return "Pyramid{" +
                "pharaoh='" + pharaoh + '\'' +
                ", modern_name='" + modern_name + '\'' +
                ", site='" + site + '\'' +
                ", height=" + height +
                '}';
    }
}
