package GUI.Training;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.awt.*;
import java.time.DayOfWeek;
import java.time.LocalDate;

public class Weekly {

    private static final Logger logger = LoggerFactory.getLogger(Weekly.class);

    public void weekly(Container container){
        LocalDate dateDuJour = LocalDate.now();

        DayOfWeek jourDeLaSemaine = dateDuJour.getDayOfWeek();

        LocalDate debutSemaine = dateDuJour.minusDays(jourDeLaSemaine.getValue());

        logger.info(debutSemaine.toString());
        System.out.println(debutSemaine);
        //LocalDate finSemaine = debutSemaine.plusDays(6);
    }

}
