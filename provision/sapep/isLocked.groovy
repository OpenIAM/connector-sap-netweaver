package provision.sapep

import org.openiam.idm.srvc.auth.dto.Login
import org.openiam.idm.srvc.auth.dto.LoginStatusEnum
import org.openiam.idm.srvc.user.dto.UserStatusEnum

def targetIdentity

def principalList = user.principalList as List
principalList?.each { Login l->
    if(binding.hasVariable("managedSysId") && (l.managedSysId == managedSysId)) {
        targetIdentity = l
    }
}
if (targetIdentity && (targetIdentity.status == LoginStatusEnum.INACTIVE || targetIdentity.isLocked == 1)) {
    output="False"
} else if (user.secondaryStatus == UserStatusEnum.DISABLED) {
    output="False"
} else if (user.status == UserStatusEnum.ACTIVE || user.status == UserStatusEnum.PENDING_INITIAL_LOGIN) {
    output="True"
} else {
    output="False"
}
