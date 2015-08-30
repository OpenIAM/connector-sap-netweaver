package provision.sapep

import org.springframework.context.support.ClassPathXmlApplicationContext
import org.openiam.idm.srvc.org.dto.Organization;
import java.util.List;

def orgManager = context.getBean("orgManager")

output = null
def List<Organization> orgList = orgManager.getOrganizationsForUserByTypeLocalized(user.id, null, "DEPARMENT", null);
if(orgList != null && orgList.size() > 0) {
   	def org = orgList.get(0);
	if (org) {
		output = org.name;
	}
}