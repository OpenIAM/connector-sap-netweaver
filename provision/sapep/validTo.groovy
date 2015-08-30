package provision.sapep

import java.text.SimpleDateFormat;

println (">>> [provision][sapep][validTo] Called -----");

output = null

if (user.lastDate) {
	output = new SimpleDateFormat("yyyy-MM-dd").format(user.lastDate);
}


println (">>> [provision][sapep][validTo] Output: "+output);