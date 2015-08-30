package provision.sapep

import java.text.SimpleDateFormat;

println (">>> [provision][sapep][validFrom] Called -----");

output = null

if (user.startDate) {
	output = new SimpleDateFormat("yyyy-MM-dd").format(user.startDate);
}


println (">>> [provision][sapep][validFrom] Output: "+output);

