package ticketmachine;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class TicketMachineTest {
	private static final int PRICE = 50; // Une constante

	private TicketMachine machine; // l'objet à tester

	@Before
	public void setUp() {
		machine = new TicketMachine(PRICE); // On initialise l'objet à tester
	}

	@Test
	// On vérifie que le prix affiché correspond au paramètre passé lors de l'initialisation
	// S1 : le prix affiché correspond à l’initialisation
	public void priceIsCorrectlyInitialized() {
		// Paramètres : message si erreur, valeur attendue, valeur réelle
		assertEquals("Initialisation incorrecte du prix", PRICE, machine.getPrice());
	}

	@Test
	// S2 : la balance change quand on insère de l’argent
	public void insertMoneyChangesBalance() {
		machine.insertMoney(10);
		machine.insertMoney(20);
		assertEquals("La balance n'est pas correctement mise à jour", 10 + 20, machine.getBalance()); // Les montants ont été correctement additionnés               
	}

	@Test
	// S3 : on n’imprime pas le ticket si le montant inséré est insuffisant
        public void montantInsuffisant(){
            machine.insertMoney(25);
            assertFalse("Le ticket s'est imprimer alors que le montant est insuffisant",machine.printTicket());
        }
        
        @Test
        //  S4 : on imprime le ticket si le montant inséré est suffisant
        public void montantSuffisant(){
            machine.insertMoney(PRICE);
            assertTrue("Le ticket s'imprime pas alors que le montant est suffisant",machine.printTicket());
            
        }
        @Test
        // S5 : Quand on imprime un ticket la balance est décrémentée du prix du ticket
        public void machineCasse(){
            machine.insertMoney(PRICE); 
            int effectif = machine.getBalance();
            machine.printTicket();
            assertEquals("la balance n'est pas bien affecter par le prix du ticket",machine.getBalance()+machine.getPrice(),effectif);
            
        }
        @Test 
        //S6 : le montant collecté est mis à jour quand on imprime un ticket (pas avant)
        public void cashIn(){
            machine.insertMoney(PRICE);
            int argent = machine.getTotal();
            machine.printTicket();
            assertEquals("le montant est pas bien mise a jour",argent+machine.getPrice(),machine.getTotal());
        }
        @Test
        // S7 : refund() rend correctement la monnaie
        public void pasDeArnaque(){
            machine.insertMoney(PRICE);
            assertEquals("La monnaie n'est pas correctement rendu",PRICE,machine.refund());
        }
       @Test
        //S8 : refund() remet la balance à zéro
        public void pasDeArnaqueDeLautreCote(){
            machine.insertMoney(PRICE);
            machine.refund();
            assertEquals("La balance s'initialise pas a 0",0,machine.getBalance());
        }
        @Test(expected=IllegalArgumentException.class )
        //S9 : on ne peut pas insérer un montant négatif
        public void pasDargentNegatif(){
            machine.insertMoney(-1);
        }
        @Test(expected=IllegalArgumentException.class)
        //S10 : on ne peut pas créer de machine qui délivre des tickets dont le prix est négatif
        public void givingMachine(){
            TicketMachine magicMachine = new TicketMachine(-50);
        }
        
        
        
}
