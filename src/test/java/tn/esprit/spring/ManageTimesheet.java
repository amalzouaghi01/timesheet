package tn.esprit.spring;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

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


import tn.esprit.spring.entities.Mission;
import tn.esprit.spring.entities.MissionExterne;
import tn.esprit.spring.services.ITimesheetService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ManageTimesheet {

	private static final Logger l = LogManager.getLogger(ManageTimesheet.class);	
	@Autowired
	ITimesheetService timesheetControl;
	@Test
	public void contextLoads() throws ParseException  {
		

		MissionExterne miseEnPlace4G = new MissionExterne("Mise en place du 4G pour l’entreprise Orange", "", "facturation@orange.tn", 650f);
		MissionExterne devNouveauOutilVente = new MissionExterne("Dev d'un nouveau outil de vente", "", "facturation@orange.tn", 475f);
		Mission maintenanceSIRH = new Mission("Maintenance du SIRH interne", "");
		
		int miseEnPlace4GId = timesheetControl.ajouterMission(miseEnPlace4G);
		int devNouveauOutilVenteId = timesheetControl.ajouterMission(devNouveauOutilVente);
		int maintenanceSIRHId = timesheetControl.ajouterMission(maintenanceSIRH);
		
		int depRhId = 2;
		int depTelecomId = 1;
		
		
		timesheetControl.affecterMissionADepartement(miseEnPlace4GId, depTelecomId);
		timesheetControl.affecterMissionADepartement(devNouveauOutilVenteId, depTelecomId);
		timesheetControl.affecterMissionADepartement(maintenanceSIRHId, depRhId);
		
		
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
		Date dateDebutamalZouaghi4G = dateFormat.parse("01/01/2016");
		Date dateFinamalZouaghi4G = dateFormat.parse("31/12/2016");
		int amalZouaghiId = 3;
		timesheetControl.ajouterTimesheet(miseEnPlace4GId, amalZouaghiId, 
				dateDebutamalZouaghi4G, dateFinamalZouaghi4G);
		int yosraArbiId = 5; 
		timesheetControl.validerTimesheet(miseEnPlace4GId, amalZouaghiId, 
				dateDebutamalZouaghi4G, dateFinamalZouaghi4G, 
												yosraArbiId);
		
		int ghadaKlibiId = 1;
		Date dateDebutDevNouveauOutilghada = dateFormat.parse("01/01/2016");
		Date dateFinDevNouveauOutilghada = dateFormat.parse("15/06/2016");
		timesheetControl.ajouterTimesheet(devNouveauOutilVenteId, ghadaKlibiId, dateDebutDevNouveauOutilghada, dateFinDevNouveauOutilghada);

		Date dateDebutMaintenanceSIRHKhaled = dateFormat.parse("16/10/2016");
		Date dateFinMaintenanceSIRHKhaled = dateFormat.parse("31/12/2016");
		timesheetControl.ajouterTimesheet(maintenanceSIRHId, ghadaKlibiId, dateDebutMaintenanceSIRHKhaled, dateFinMaintenanceSIRHKhaled);

		
		int maherGharbiId = 2;
		Date dateDebut4GmaherGharbi = dateFormat.parse("01/01/2016");
		Date dateFin4GmaherGharbi = dateFormat.parse("16/03/2016");
		timesheetControl.ajouterTimesheet(miseEnPlace4GId, maherGharbiId, dateDebut4GmaherGharbi, dateFin4GmaherGharbi);

		Date dateDebutMaintenanceSIRHmaherGharbi = dateFormat.parse("17/03/2016");
		Date dateFinMaintenanceSIRHImaherGharbi = dateFormat.parse("31/12/2016");
		timesheetControl.ajouterTimesheet(maintenanceSIRHId, maherGharbiId, dateDebutMaintenanceSIRHmaherGharbi, dateFinMaintenanceSIRHImaherGharbi);
		
		
		List<Mission> missionNamesAymen = timesheetControl.findAllMissionByEmployeJPQL(amalZouaghiId);
		l.info("Mission de Amal :");
		for(Mission mission : missionNamesAymen){
			l.info(mission.getName());
		}
		
		
		List<Mission> missionNamesKhaled = timesheetControl.findAllMissionByEmployeJPQL(ghadaKlibiId);
		l.info("Mission de Khaled :");
		for(Mission mission : missionNamesKhaled){
			l.info(mission.getName());
		}
		List<Mission> missionNamesMohamed = timesheetControl.findAllMissionByEmployeJPQL(maherGharbiId);
		l.info("Mission de Mohamed :");
		for(Mission mission : missionNamesMohamed){
			l.info(mission.getName());
		}
		
		
	}

	}

