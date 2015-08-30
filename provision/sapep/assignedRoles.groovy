package provision.sapep

import java.util.ArrayList;
import java.util.List;
import org.openiam.idm.srvc.role.dto.Role;
import org.openiam.idm.srvc.user.dto.User;
import org.openiam.base.AttributeOperationEnum;

import org.openiam.base.BaseAttribute;
import org.openiam.base.BaseAttributeContainer;

output = null
BaseAttributeContainer attributeContainer = new BaseAttributeContainer();

user.roles?.each{Role r->
	if (r.operation == AttributeOperationEnum.ADD || r.operation == AttributeOperationEnum.DELETE) {
		attributeContainer.attributeList.add(new BaseAttribute(r.name, r.name, r.operation))
	}
}

if (attributeContainer.attributeList) {
	output = attributeContainer
}
