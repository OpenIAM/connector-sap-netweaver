package provision.sapep


import org.openiam.base.AttributeOperationEnum
import org.openiam.base.BaseAttribute
import org.openiam.base.BaseAttributeContainer
import org.openiam.idm.srvc.grp.dto.Group
import org.openiam.idm.srvc.grp.service.GroupDataService

output = null
def attributeContainer = new BaseAttributeContainer()
user.groups?.each{Group g->
    if (g.operation == AttributeOperationEnum.ADD || g.operation == AttributeOperationEnum.DELETE) {
        def dn = getGroupAttribute(g, "UNIQUE_ID")
        if (dn) {
            attributeContainer.attributeList.add(new BaseAttribute(dn, dn, g.operation))
        } else {
            println "Group ${g.name} provisioning will be skipped (UNIQUE_ID attribute can not be found)"
        }
    }
}

if (attributeContainer.attributeList) {
    output = attributeContainer
}

String getGroupAttribute(Group group, String attrName) {
    def value = group.attributes.find({ it.name.equalsIgnoreCase(attrName) })?.value
    if (!value) {
        def groupManager = context?.getBean("groupManager") as GroupDataService
        def grp = groupManager.getGroup(group.id)
        value = grp.attributes.find({ it.name.equalsIgnoreCase(attrName) })?.value
    }
    return value
}