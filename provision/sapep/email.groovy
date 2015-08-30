package provision.sapep

import org.openiam.idm.util.Transliterator

println("email.groovy called.");

/*println("User objects: ");
println("User: email=" + user.emailAddresses);
*/
if (user.email != null && user.email.length() > 0) {
	output=user.email
} else {

	output=Transliterator.transliterate(user.firstName + "." + user.lastName, true) + "@openiam.org"
}
