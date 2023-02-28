package com.web.lab.weblab3;



import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.UUID;

@ManagedBean
@ApplicationScoped
public class DotWorkerBean implements Serializable {

    UUID owner = UUID.randomUUID();

    private final DotDatabase db = new DotDatabase();

    public Dot getNewDot() {
        return newDot;
    }

    public void setNewDot(Dot newDot) {
        this.newDot = newDot;
    }

    private Dot newDot;

    public List<Dot> getPointsTable(){
        return db.getPoints(this.owner.toString());
    }

    public DotWorkerBean() {
        this.newDot = new Dot();
    }

    public void setLastR(){
        if(getPointsTable().size() != 0)
            newDot.setR(getPointsTable().get(0).getR());
    }

    public void clearTable(){
        db.clearDatabase();
    }

    public void doCode() {
        if (newDot.isError()) {
            float mainR = newDot.getR();
            newDot = new Dot();
            newDot.setR(mainR);
            newDot.setErrorString("error");
        } else {
            newDot.createValueX();
            Double[] parseX = newDot.getReadX();
            if (parseX != null) {
                for (int i = 0; i < parseX.length; ++i) {
                    String mainY = newDot.getY();
                    float mainR = newDot.getR();
                    if (parseX[i] != null) {
                        newDot.setY(mainY);
                        newDot.setR(mainR);
                        newDot.setyDouble(Double.parseDouble(newDot.getY()));
                        newDot.setX(parseX[i]);
                        newDot.setTimework(LocalTime.now().truncatedTo(ChronoUnit.SECONDS).format(DateTimeFormatter.ISO_LOCAL_TIME));
                        newDot.setOwner(owner.toString());
                        newDot.isHit();
                        db.addPointToTable(newDot);
                        newDot = new Dot();
                        newDot.setY(mainY);
                        newDot.setR(mainR);
                    }
                }
            }
        }
    }
}
