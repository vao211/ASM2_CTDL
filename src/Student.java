public class Student {
    private String name;
    private String code;
    private String rank;
    private double point;
    static int count = 0;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public double getPoint() {
        return point;
    }

    public void setPoint(double point) {
        this.point = point;
    }

    public String getRank() {
        if (point < 5) {
            rank = "F";
        } else if (point >= 5 && point < 8) {
            rank = "P";
        } else if (point >= 8 && point < 9) {
            rank = "M";
        } else {
            rank = "D";
        }
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

}
