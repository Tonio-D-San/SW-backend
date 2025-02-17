package it.asansonne.storybe.util.generator;

import static it.cybsec.app.constants.JobPosition.*;

import it.cybsec.app.constants.JobPosition;
import java.util.Random;

public class JobPositionSelector{
	public static String getRandomJobPosition(){
		JobPosition[] jobPositions = {SOFTWARE_DEVELOPER,FRONTEND_DEVELOPER,BACKEND_DEVELOPER,FULLSTACK_DEVELOPER,DEVOPS_ENGINEER,DATA_SCIENTIST,DATA_ENGINEER,MACHINE_LEARNING_ENGINEER,DATABASE_ADMINISTRATOR,SYSTEM_ADMINISTRATOR,NETWORK_ENGINEER,CLOUD_ENGINEER,QA_ENGINEER,BUSINESS_ANALYST,PRODUCT_MANAGER,PROJECT_MANAGER,SCRUM_MASTER,SOFTWARE_ARCHITECT,SECURITY_ANALYST,MOBILE_DEVELOPER,UI_UX_DESIGNER,TECHNICAL_SUPPORT_SPECIALIST,IT_MANAGER,IT_DIRECTOR,CTO};
		return jobPositions[new Random().nextInt(jobPositions.length)].toString();
	}
}