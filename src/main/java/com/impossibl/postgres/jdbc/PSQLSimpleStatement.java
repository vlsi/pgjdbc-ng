package com.impossibl.postgres.jdbc;

import static com.impossibl.postgres.jdbc.PSQLErrorUtils.chainWarnings;
import static com.impossibl.postgres.jdbc.PSQLTextUtils.getProtocolSQLText;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLWarning;
import java.util.Collections;

import com.impossibl.postgres.protocol.PrepareCommand;
import com.impossibl.postgres.protocol.ResultField;
import com.impossibl.postgres.types.Type;

public class PSQLSimpleStatement extends PSQLStatement {

	public PSQLSimpleStatement(PSQLConnection connection) {
		super(connection, null, Collections.<ResultField>emptyList());
	}

	SQLWarning prepare(String sql) throws SQLException {
		
		sql = getProtocolSQLText(sql);

		PrepareCommand prep = connection.getProtocol().createPrepare(null, sql, Collections.<Type>emptyList());
		
		SQLWarning warningChain = connection.execute(prep);
		
		resultFields = prep.getDescribedResultFields();
		
		return warningChain;
	}
	
	@Override
	public boolean execute(String sql) throws SQLException {
		
		SQLWarning prepWarningChain = prepare(sql);
				
		boolean res = super.executeStatement(null, Collections.<Type>emptyList(), Collections.<Object>emptyList());
		
		warningChain = chainWarnings(prepWarningChain, warningChain);
		
		return res;
	}

	@Override
	public boolean execute(String sql, int autoGeneratedKeys) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean execute(String sql, int[] columnIndexes) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean execute(String sql, String[] columnNames) throws SQLException {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ResultSet executeQuery(String sql) throws SQLException {

		execute(sql);

		return getResultSet();
	}

	@Override
	public int executeUpdate(String sql, int autoGeneratedKeys) throws SQLException {

		execute(sql);

		return getUpdateCount();
	}

	@Override
	public int executeUpdate(String sql, int[] columnIndexes) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int executeUpdate(String sql, String[] columnNames) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int executeUpdate(String sql) throws SQLException {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void addBatch(String sql) throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public void clearBatch() throws SQLException {
		// TODO Auto-generated method stub

	}

	@Override
	public int[] executeBatch() throws SQLException {
		// TODO Auto-generated method stub
		return null;
	}

}