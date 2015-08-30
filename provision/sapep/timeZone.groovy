package provision.sapep

import org.openiam.idm.srvc.user.dto.UserAttribute
import org.openiam.idm.srvc.user.ws.UserDataWebService

def timeZone = getUserAttributeByName(user.id, "timeZone")?.value
output = timeZone?: null

private UserAttribute getUserAttributeByName(String userId, String attrName) {
    def userWS = context.getBean('userWS') as UserDataWebService
    def attributes = userWS.getUserAttributesInternationalized(userId, null)
    return attributes?.find { attr-> attr.name == attrName }
}
