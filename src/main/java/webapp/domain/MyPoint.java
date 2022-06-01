package webapp.domain;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.util.Date;
import java.util.Objects;

@MappedSuperclass
//@Table(name = "points")
public abstract class MyPoint implements Serializable {
    @SequenceGenerator(name = "seqGen", sequenceName = "gen_seq", allocationSize = 1)
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "gen_seq")
    @Column(name ="id")
    private int id;

    @Transient
    private static final long serialVersionUID = 4L;

    @Min(value = -3, message = "x must be > -3")
    @Max(value = 5, message = "x must be <= 5")
    private double x;

    @Min(value = -3, message = "y must be > -3")
    @Max(value = 5, message = "y must be <= 5")
    private double y;

    @Min(value = 1, message = "r must be > -3")
    @Max(value = 5, message = "r must be <= 5")
    private double r;
    private boolean coordsStatus;
    private Date bornDate;
    private String owner;

    public MyPoint(String owner, @NotNull double x, @NotNull double y, @NotNull double r) {
        this.owner = owner;
        this.x = x;
        this.y = y;
        this.r = r;
        coordsStatus = checkCoordinates(x, y, r);
        bornDate = new Date();
    }

    public MyPoint() {}

    private boolean checkCoordinates(@NotNull double x, @NotNull double y, @NotNull double r) {
        return (x >= 0 && y >= 0 && x <= r/2 && y <= r)  ||
                (x >= 0 && y <= 0 && x + (-y) <= r ) ||
                (x <= 0 && y <= 0 && x * x + y * y <= Math.pow(r/2, 2));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MyPoint)) return false;
        MyPoint point = (MyPoint) o;
        return Double.compare(point.x, x) == 0 &&
                Double.compare(point.y, y) == 0 &&
                Double.compare(point.r, r) == 0 &&
                coordsStatus == point.coordsStatus;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, x, y, r, coordsStatus, bornDate);
    }

    @Override
    public String toString() {
        return "<tr><td>" + x + "</td>" +
                "<td>" + y + "</td>" +
                "<td>" + r + "</td>" +
                "<td style='color: " + ((coordsStatus) ? "green" : "red") + "'>" + coordsStatus + "</td>" +
                "<td>" + bornDate + "</td></tr>";
    }

    public long getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getX() {
        return x;
    }

    public void setX(double x) {
        this.x = x;
    }

    public double getY() {
        return y;
    }

    public void setY(double y) {
        this.y = y;
    }

    public double getR() {
        return r;
    }

    public void setR(double r) {
        this.r = r;
    }

    public boolean isCoordsStatus() {
        return coordsStatus;
    }

    public void setCoordsStatus(boolean coordsStatus) {
        this.coordsStatus = coordsStatus;
    }

    public Date getBornDate() {
        return bornDate;
    }

    public void setBornDate(Date bornDate) {
        this.bornDate = bornDate;
    }
}