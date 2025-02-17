package it.asansonne.storybe.util.generator;

import static it.asansonne.storybe.constants.JobPosition.BACKEND_DEVELOPER;
import static it.asansonne.storybe.constants.JobPosition.BUSINESS_ANALYST;
import static it.asansonne.storybe.constants.JobPosition.CLOUD_ENGINEER;
import static it.asansonne.storybe.constants.JobPosition.CTO;
import static it.asansonne.storybe.constants.JobPosition.DATABASE_ADMINISTRATOR;
import static it.asansonne.storybe.constants.JobPosition.DATA_ENGINEER;
import static it.asansonne.storybe.constants.JobPosition.DATA_SCIENTIST;
import static it.asansonne.storybe.constants.JobPosition.DEVOPS_ENGINEER;
import static it.asansonne.storybe.constants.JobPosition.FRONTEND_DEVELOPER;
import static it.asansonne.storybe.constants.JobPosition.FULLSTACK_DEVELOPER;
import static it.asansonne.storybe.constants.JobPosition.IT_DIRECTOR;
import static it.asansonne.storybe.constants.JobPosition.IT_MANAGER;
import static it.asansonne.storybe.constants.JobPosition.MACHINE_LEARNING_ENGINEER;
import static it.asansonne.storybe.constants.JobPosition.MOBILE_DEVELOPER;
import static it.asansonne.storybe.constants.JobPosition.NETWORK_ENGINEER;
import static it.asansonne.storybe.constants.JobPosition.PRODUCT_MANAGER;
import static it.asansonne.storybe.constants.JobPosition.PROJECT_MANAGER;
import static it.asansonne.storybe.constants.JobPosition.QA_ENGINEER;
import static it.asansonne.storybe.constants.JobPosition.SCRUM_MASTER;
import static it.asansonne.storybe.constants.JobPosition.SECURITY_ANALYST;
import static it.asansonne.storybe.constants.JobPosition.SOFTWARE_ARCHITECT;
import static it.asansonne.storybe.constants.JobPosition.SOFTWARE_DEVELOPER;
import static it.asansonne.storybe.constants.JobPosition.SYSTEM_ADMINISTRATOR;
import static it.asansonne.storybe.constants.JobPosition.TECHNICAL_SUPPORT_SPECIALIST;
import static it.asansonne.storybe.constants.JobPosition.UI_UX_DESIGNER;

import it.asansonne.storybe.constants.JobPosition;
import java.util.Random;

public class JobPositionSelector {
  public static String getRandomJobPosition() {
    JobPosition[] jobPositions =
        {SOFTWARE_DEVELOPER, FRONTEND_DEVELOPER, BACKEND_DEVELOPER, FULLSTACK_DEVELOPER,
            DEVOPS_ENGINEER, DATA_SCIENTIST, DATA_ENGINEER, MACHINE_LEARNING_ENGINEER,
            DATABASE_ADMINISTRATOR, SYSTEM_ADMINISTRATOR, NETWORK_ENGINEER, CLOUD_ENGINEER,
            QA_ENGINEER, BUSINESS_ANALYST, PRODUCT_MANAGER, PROJECT_MANAGER, SCRUM_MASTER,
            SOFTWARE_ARCHITECT, SECURITY_ANALYST, MOBILE_DEVELOPER, UI_UX_DESIGNER,
            TECHNICAL_SUPPORT_SPECIALIST, IT_MANAGER, IT_DIRECTOR, CTO};
    return jobPositions[new Random().nextInt(jobPositions.length)].toString();
  }
}