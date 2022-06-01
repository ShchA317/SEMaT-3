import webapp.domain.Point;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.persistence.*;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@ManagedBean(name = "mainBean")
@SessionScoped
public class MainBean implements Serializable {

    private static final long serialVersionUID = 4L;
    private EntityManagerFactory managerFactory;

//    @PersistenceContext(unitName = "db_con")
    private EntityManager manager;
    private List<Point> points = new ArrayList<>();


//    @Transactional
    public void validate() {
        System.out.println("Пришли данные на валидацию");
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            managerFactory = Persistence.createEntityManagerFactory("db_con");
            manager = managerFactory.createEntityManager();
            EntityTransaction transaction = manager.getTransaction();
            Map<String, String> params = facesContext.getExternalContext().getRequestParameterMap();
            Point point;

            if((params.get("X-field") != null && params.get("Y-field") != null && params.get("R-field") != null)) {
                point = new Point(facesContext.getExternalContext().getSessionId(true), Double.parseDouble(params.get("X-field")),
                        Double.parseDouble(params.get("Y-field")), Double.parseDouble(params.get("R-field")));
                transaction.begin();
                manager.persist(point);
                points.add(point);
                transaction.commit();
                transaction.begin();
                TypedQuery<Point> query = manager.createQuery("SELECT p FROM Point p WHERE p.owner = :owner", Point.class);
                points = query.setParameter("owner", facesContext.getExternalContext().getSessionId(true)).getResultList();
            } else {
                System.out.println("Пришел null");
                if(params.get("X-field") == null) System.out.println("X-field = null");
                if(params.get("Y-field") == null) System.out.println("Y-field = null");
                if(params.get("R-field") == null) System.out.println("R-field = null");
            }


        } finally {
            manager.close();
            managerFactory.close();
        }
    }

    public List<Point> getPoints() {
        return points;
    }

    public void setPoints(List<Point> points) {
        this.points = points;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MainBean)) return false;
        MainBean that = (MainBean) o;
        return Objects.equals(managerFactory, that.managerFactory) &&
                Objects.equals(manager, that.manager) &&
                Objects.equals(points, that.points);
    }

    @Override
    public int hashCode() {
        return Objects.hash(managerFactory, manager, points);
    }

    @Override
    public String toString() {
        return "mainBean{" +
                "managerFactory=" + managerFactory +
                ", manager=" + manager +
                ", points=" + points +
                '}';
    }
}
