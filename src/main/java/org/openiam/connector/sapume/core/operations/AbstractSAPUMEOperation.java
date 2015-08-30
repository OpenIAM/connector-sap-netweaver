package org.openiam.connector.sapume.core.operations;

import org.openiam.connector.sapume.core.common.SAPUMEConfiguration;
import org.openiam.connector.sapume.core.connection.SAPUMEConnection;

public abstract class AbstractSAPUMEOperation
{
	protected SAPUMEConnection sapConnection;
	protected SAPUMEConfiguration sapConfiguration;

	public AbstractSAPUMEOperation()
	{
		
	}
	
	public AbstractSAPUMEOperation(SAPUMEConnection conn, SAPUMEConfiguration config)
	{
		this.sapConnection = conn;
		this.sapConfiguration = config;
	}

	public void setConfiguration(SAPUMEConfiguration config)
	{
		this.sapConfiguration = config;
	}
		
}
