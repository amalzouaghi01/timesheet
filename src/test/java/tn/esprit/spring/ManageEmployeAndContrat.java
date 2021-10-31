package tn.esprit.spring;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;


import tn.esprit.spring.controller.IControllerEmployeImpl;

import tn.esprit.spring.entities.Contrat;
import tn.esprit.spring.entities.Employe;
import tn.esprit.spring.entities.Entreprise;
import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.Role;

import java.text.ParseException;
import java.text.SimpleDateFormat;
//import java.text.SimpleDateFormat;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManageEmployeAndContrat {
	    private static final Logger l = LogManager.getLogger(ManageEmployeAndContrat.class);
		@Autowired
		IControllerEmployeImpl employeControl;
		@Test
		public void contextLoads() throws ParseException {
			
		
		Employe amalZouaghi = new Employe("zouaghi", "amal", "amal.zouaghi@ssiiconsulting.tn", true, Role.INGENIEUR);
		Employe mahergharbi = new Employe("gharbi", "maher", "maher.gharbi@ssiiconsulting.tn", true, Role.TECHNICIEN);
		Employe hanenebenhamouda = new Employe("benhamouda", "hanene", "hanene.benhamouda@ssiiconsulting.tn", true, Role.INGENIEUR);
		Employe oumayakhemir = new Employe("khemir", "oumaya", "oumaya.khemir@ssiiconsulting.tn", true, Role.CHEF_DEPARTEMENT);
		Employe hamzabenkhedher = new Employe("benkhedher", "hamza", "hamza.benkhedher@ssiiconsulting.tn", true, Role.CHEF_DEPARTEMENT);		
		
		int amalZouaghiId = employeControl.ajouterEmploye(amalZouaghi);
		int mahergharbiId = employeControl.ajouterEmploye(mahergharbi);
		int hanenebenhamoudaId = employeControl.ajouterEmploye(hanenebenhamouda);
		int oumayakhemirId = employeControl.ajouterEmploye(oumayakhemir);
		int hamzabenkhedherId = employeControl.ajouterEmploye(hamzabenkhedher);
		
		int depRhId = 2;
		int depTelecomId = 1;
				
		employeControl.affecterEmployeADepartement(amalZouaghiId, depRhId);
		employeControl.affecterEmployeADepartement(mahergharbiId, depTelecomId);
		
		employeControl.affecterEmployeADepartement(hanenebenhamoudaId, depRhId);
		employeControl.affecterEmployeADepartement(oumayakhemirId, depTelecomId);
		
		employeControl.affecterEmployeADepartement(hamzabenkhedherId, depTelecomId);

		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Contrat amalContrat = new Contrat(dateFormat.parse("01/02/2015"), "CDI", 1600); 
		Contrat maherContrat = new Contrat(dateFormat.parse("01/03/2010"), "CDI", 2600);
		Contrat haneneContrat = new Contrat(dateFormat.parse("15/05/2013"), "CDI", 900);
		Contrat oumayaContrat = new Contrat(dateFormat.parse("10/05/2014"), "CDI", 2000);
		Contrat hamzaContrat = new Contrat(dateFormat.parse("12/06/2010"), "CDI", 2700);
		
		int amalContratId = employeControl.ajouterContrat(amalContrat);
		int maherContratId = employeControl.ajouterContrat(maherContrat);
		int haneneContratContratId = employeControl.ajouterContrat(haneneContrat);
		int oumayaContratId = employeControl.ajouterContrat(oumayaContrat);
		int hamzaContratId = employeControl.ajouterContrat(hamzaContrat);

		employeControl.affecterContratAEmploye(amalContratId, amalZouaghiId);
		employeControl.affecterContratAEmploye(maherContratId, mahergharbiId);
		employeControl.affecterContratAEmploye(haneneContratContratId, hanenebenhamoudaId);
		employeControl.affecterContratAEmploye(oumayaContratId, oumayakhemirId);
		employeControl.affecterContratAEmploye(hamzaContratId, hamzabenkhedherId);
		
		l.info(employeControl.getEmployePrenomById(hamzabenkhedherId));
		employeControl.desaffecterEmployeDuDepartement(hamzabenkhedherId, depTelecomId);
		
	
		
		employeControl.mettreAjourEmailByEmployeId("newEmail@email.tn", amalZouaghiId);
		
		employeControl.mettreAjourEmailByEmployeIdJPQL("newEmail2@email.tn", amalZouaghiId);

		
		
		float salaire = employeControl.getSalaireByEmployeIdJPQL(mahergharbiId);
		l.info("Le salaire de l'employe dont l'id est : " + mahergharbiId + " est : " + salaire);
		
		Entreprise entreprise = new Entreprise();
		entreprise.setId(1);
		List<Employe> employes = employeControl.getAllEmployeByEntreprise(entreprise);
		
		for(Employe employe : employes){
			l.info("*****" + employe.getNom());
		}
		
		
		l.info("Salaire Moyen By Departement"+employeControl.getSalaireMoyenByDepartementId(1));
		
		Employe employe = new Employe();
		employe.setId(1);
		Mission mission = new Mission();
		mission.setId(2);
		
	}

}
