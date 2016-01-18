package it.ispw.efco.nottitranquille;

import it.ispw.efco.nottitranquille.model.Address;
import it.ispw.efco.nottitranquille.model.Location;
import it.ispw.efco.nottitranquille.model.Structure;


/**
 * @author Claudio Pastorini Omar Shalby Federico Vagnoni Emanuele Vannacci
 */
public class Demo {

    public static void main(String args[]) {

        Address address = new Address("Italia", "Zagarolo", "viale ungheria", "00039");

        String description = "L'albergo a conduzione familiare Tasos Villa offre " +
                "sistemazioni indipendenti a soli 3 minuti a piedi dalla" +
                " spiaggia sabbiosa di Acharavi. Vanta una piscina con terrazza " +
                "solarium e connessione Wi-Fi gratuita in tutta la struttura.\n" +
                "Tutti gli alloggi dispongono di angolo cottura con tostapane, bollitore, " +
                "frigorifero, cucina a due fuochi e piccolo forno." +
                " Includono inoltre un ventilatore e una TV a colori.\n";

        Structure structure = new Structure("Villa Tasos", address );
        structure.setDescription(description);

        Location location0 = new Location("Stanza 0", "Appartamento 0 di Villa Tasos");

        Location location1 = new Location("Stanza 1", "Appartamento 1 di Villa Tasos");

        structure.addLocation(location0);
        structure.addLocation(location1);




    }


}
