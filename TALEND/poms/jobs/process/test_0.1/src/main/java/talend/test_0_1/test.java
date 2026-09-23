
package talend.test_0_1;

import routines.Numeric;
import routines.DataOperation;
import routines.TalendDataGenerator;
import routines.TalendStringUtil;
import routines.TalendString;
import routines.MDM;
import routines.StringHandling;
import routines.Relational;
import routines.TalendDate;
import routines.Mathematical;
import routines.SQLike;
import routines.system.*;
import routines.system.api.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.math.BigDecimal;
import java.io.ByteArrayOutputStream;
import java.io.ByteArrayInputStream;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.ObjectOutputStream;
import java.io.ObjectInputStream;
import java.io.IOException;
import java.util.Comparator;

@SuppressWarnings("unused")

/**
 * Job: test Purpose: <br>
 * Description: <br>
 * 
 * @author BENICHOU, Amine
 * @version 8.0.1.20250218_0945-patch
 * @status DEV
 */
public class test implements TalendJob {
	static {
		System.setProperty("TalendJob.log", "test.log");
	}

	private static org.apache.logging.log4j.Logger log = org.apache.logging.log4j.LogManager.getLogger(test.class);

	static {
		if (isSLF4JBridgeHandlerPresent()) {
			useSLF4JBridgeHandler();
		}
	}

	protected static void useSLF4JBridgeHandler() {
		try {
			java.lang.StringBuilder config = new java.lang.StringBuilder();
			config.append("handlers = org.slf4j.bridge.SLF4JBridgeHandler\n"); // jul-to-slf4j
			config.append(".level = FINEST");

			try (java.io.ByteArrayInputStream is = new java.io.ByteArrayInputStream(config.toString().getBytes())) {
				java.util.logging.LogManager.getLogManager().readConfiguration(is);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	protected static boolean isSLF4JBridgeHandlerPresent() {
		boolean isSLF4JBridgeHandlerPresent = false;
		try {
			Class.forName("org.slf4j.bridge.SLF4JBridgeHandler");
			isSLF4JBridgeHandlerPresent = true;
		} catch (ClassNotFoundException e) {
		}
		return isSLF4JBridgeHandlerPresent;
	}

	protected static boolean isCBPClientPresent() {
		boolean isCBPClientPresent = false;
		try {
			Class.forName("org.talend.metrics.CBPClient");
			isCBPClientPresent = true;
		} catch (java.lang.ClassNotFoundException e) {
		}
		return isCBPClientPresent;
	}

	protected static void logIgnoredError(String message, Throwable cause) {
		log.error(message, cause);

	}

	public final Object obj = new Object();

	// for transmiting parameters purpose
	private Object valueObject = null;

	public Object getValueObject() {
		return this.valueObject;
	}

	public void setValueObject(Object valueObject) {
		this.valueObject = valueObject;
	}

	private final static String defaultCharset = java.nio.charset.Charset.defaultCharset().name();

	private final static String utf8Charset = "UTF-8";

	public static String taskExecutionId = null;

	public static String jobExecutionId = java.util.UUID.randomUUID().toString();;

	private final static boolean isCBPClientPresent = isCBPClientPresent();

	// contains type for every context property
	public class PropertiesWithType extends java.util.Properties {
		private static final long serialVersionUID = 1L;
		private java.util.Map<String, String> propertyTypes = new java.util.HashMap<>();

		public PropertiesWithType(java.util.Properties properties) {
			super(properties);
		}

		public PropertiesWithType() {
			super();
		}

		public void setContextType(String key, String type) {
			propertyTypes.put(key, type);
		}

		public String getContextType(String key) {
			return propertyTypes.get(key);
		}
	}

	// create and load default properties
	private java.util.Properties defaultProps = new java.util.Properties();

	// create application properties with default
	public class ContextProperties extends PropertiesWithType {

		private static final long serialVersionUID = 1L;

		public ContextProperties(java.util.Properties properties) {
			super(properties);
		}

		public ContextProperties() {
			super();
		}

		public void synchronizeContext() {

			if (connection_local_Login != null) {

				this.setProperty("connection_local_Login", connection_local_Login.toString());

			}

			if (connection_local_Database != null) {

				this.setProperty("connection_local_Database", connection_local_Database.toString());

			}

			if (connection_local_Port != null) {

				this.setProperty("connection_local_Port", connection_local_Port.toString());

			}

			if (connection_local_Password != null) {

				this.setProperty("connection_local_Password", connection_local_Password.toString());

			}

			if (connection_local_AdditionalParams != null) {

				this.setProperty("connection_local_AdditionalParams", connection_local_AdditionalParams.toString());

			}

			if (connection_local_Schema != null) {

				this.setProperty("connection_local_Schema", connection_local_Schema.toString());

			}

			if (connection_local_Server != null) {

				this.setProperty("connection_local_Server", connection_local_Server.toString());

			}

		}

		// if the stored or passed value is "<TALEND_NULL>" string, it mean null
		public String getStringValue(String key) {
			String origin_value = this.getProperty(key);
			if (NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY.equals(origin_value)) {
				return null;
			}
			return origin_value;
		}

		public String connection_local_Login;

		public String getConnection_local_Login() {
			return this.connection_local_Login;
		}

		public String connection_local_Database;

		public String getConnection_local_Database() {
			return this.connection_local_Database;
		}

		public String connection_local_Port;

		public String getConnection_local_Port() {
			return this.connection_local_Port;
		}

		public java.lang.String connection_local_Password;

		public java.lang.String getConnection_local_Password() {
			return this.connection_local_Password;
		}

		public String connection_local_AdditionalParams;

		public String getConnection_local_AdditionalParams() {
			return this.connection_local_AdditionalParams;
		}

		public String connection_local_Schema;

		public String getConnection_local_Schema() {
			return this.connection_local_Schema;
		}

		public String connection_local_Server;

		public String getConnection_local_Server() {
			return this.connection_local_Server;
		}
	}

	protected ContextProperties context = new ContextProperties(); // will be instanciated by MS.

	public ContextProperties getContext() {
		return this.context;
	}

	protected java.util.Map<String, String> defaultProperties = new java.util.HashMap<String, String>();
	protected java.util.Map<String, String> additionalProperties = new java.util.HashMap<String, String>();

	public java.util.Map<String, String> getDefaultProperties() {
		return this.defaultProperties;
	}

	public java.util.Map<String, String> getAdditionalProperties() {
		return this.additionalProperties;
	}

	private final String jobVersion = "0.1";
	private final String jobName = "test";
	private final String projectName = "TALEND";
	public Integer errorCode = null;
	private String currentComponent = "";
	public static boolean isStandaloneMS = Boolean.valueOf("false");

	private void s(final String component) {
		try {
			org.talend.metrics.DataReadTracker.setCurrentComponent(jobName, component);
		} catch (Exception | NoClassDefFoundError e) {
			// ignore
		}
	}

	private void mdc(final String subJobName, final String subJobPidPrefix) {
		mdcInfo.forEach(org.slf4j.MDC::put);
		org.slf4j.MDC.put("_subJobName", subJobName);
		org.slf4j.MDC.put("_subJobPid", subJobPidPrefix + subJobPidCounter.getAndIncrement());
	}

	private void sh(final String componentId) {
		ok_Hash.put(componentId, false);
		start_Hash.put(componentId, System.currentTimeMillis());
	}

	{
		s("none");
	}

	private String cLabel = null;

	private final java.util.Map<String, Object> globalMap = new java.util.HashMap<String, Object>();
	private final static java.util.Map<String, Object> junitGlobalMap = new java.util.HashMap<String, Object>();

	private final java.util.Map<String, Long> start_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Long> end_Hash = new java.util.HashMap<String, Long>();
	private final java.util.Map<String, Boolean> ok_Hash = new java.util.HashMap<String, Boolean>();
	public final java.util.List<String[]> globalBuffer = new java.util.ArrayList<String[]>();

	private final JobStructureCatcherUtils talendJobLog = new JobStructureCatcherUtils(jobName,
			"_RPNKQLbKEfGIZvUIz3EKyQ", "0.1");
	private org.talend.job.audit.JobAuditLogger auditLogger_talendJobLog = null;

	private RunStat runStat = new RunStat(talendJobLog, System.getProperty("audit.interval"));

	// OSGi DataSource
	private final static String KEY_DB_DATASOURCES = "KEY_DB_DATASOURCES";

	private final static String KEY_DB_DATASOURCES_RAW = "KEY_DB_DATASOURCES_RAW";

	public void setDataSources(java.util.Map<String, javax.sql.DataSource> dataSources) {
		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		for (java.util.Map.Entry<String, javax.sql.DataSource> dataSourceEntry : dataSources.entrySet()) {
			talendDataSources.put(dataSourceEntry.getKey(),
					new routines.system.TalendDataSource(dataSourceEntry.getValue()));
		}
		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	public void setDataSourceReferences(List serviceReferences) throws Exception {

		java.util.Map<String, routines.system.TalendDataSource> talendDataSources = new java.util.HashMap<String, routines.system.TalendDataSource>();
		java.util.Map<String, javax.sql.DataSource> dataSources = new java.util.HashMap<String, javax.sql.DataSource>();

		for (java.util.Map.Entry<String, javax.sql.DataSource> entry : BundleUtils
				.getServices(serviceReferences, javax.sql.DataSource.class).entrySet()) {
			dataSources.put(entry.getKey(), entry.getValue());
			talendDataSources.put(entry.getKey(), new routines.system.TalendDataSource(entry.getValue()));
		}

		globalMap.put(KEY_DB_DATASOURCES, talendDataSources);
		globalMap.put(KEY_DB_DATASOURCES_RAW, new java.util.HashMap<String, javax.sql.DataSource>(dataSources));
	}

	private final java.io.ByteArrayOutputStream baos = new java.io.ByteArrayOutputStream();
	private final java.io.PrintStream errorMessagePS = new java.io.PrintStream(new java.io.BufferedOutputStream(baos));

	public String getExceptionStackTrace() {
		if ("failure".equals(this.getStatus())) {
			errorMessagePS.flush();
			return baos.toString();
		}
		return null;
	}

	private Exception exception;

	public Exception getException() {
		if ("failure".equals(this.getStatus())) {
			return this.exception;
		}
		return null;
	}

	private class TalendException extends Exception {

		private static final long serialVersionUID = 1L;

		private java.util.Map<String, Object> globalMap = null;
		private Exception e = null;

		private String currentComponent = null;
		private String cLabel = null;

		private String virtualComponentName = null;

		public void setVirtualComponentName(String virtualComponentName) {
			this.virtualComponentName = virtualComponentName;
		}

		private TalendException(Exception e, String errorComponent, final java.util.Map<String, Object> globalMap) {
			this.currentComponent = errorComponent;
			this.globalMap = globalMap;
			this.e = e;
		}

		private TalendException(Exception e, String errorComponent, String errorComponentLabel,
				final java.util.Map<String, Object> globalMap) {
			this(e, errorComponent, globalMap);
			this.cLabel = errorComponentLabel;
		}

		public Exception getException() {
			return this.e;
		}

		public String getCurrentComponent() {
			return this.currentComponent;
		}

		public String getExceptionCauseMessage(Exception e) {
			Throwable cause = e;
			String message = null;
			int i = 10;
			while (null != cause && 0 < i--) {
				message = cause.getMessage();
				if (null == message) {
					cause = cause.getCause();
				} else {
					break;
				}
			}
			if (null == message) {
				message = e.getClass().getName();
			}
			return message;
		}

		@Override
		public void printStackTrace() {
			if (!(e instanceof TalendException || e instanceof TDieException)) {
				if (virtualComponentName != null && currentComponent.indexOf(virtualComponentName + "_") == 0) {
					globalMap.put(virtualComponentName + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				}
				globalMap.put(currentComponent + "_ERROR_MESSAGE", getExceptionCauseMessage(e));
				System.err.println("Exception in component " + currentComponent + " (" + jobName + ")");
			}
			if (!(e instanceof TDieException)) {
				if (e instanceof TalendException) {
					e.printStackTrace();
				} else {
					e.printStackTrace();
					e.printStackTrace(errorMessagePS);
					test.this.exception = e;
				}
			}
			if (!(e instanceof TalendException)) {
				try {
					for (java.lang.reflect.Method m : this.getClass().getEnclosingClass().getMethods()) {
						if (m.getName().compareTo(currentComponent + "_error") == 0) {
							m.invoke(test.this, new Object[] { e, currentComponent, globalMap });
							break;
						}
					}

					if (!(e instanceof TDieException)) {
						if (enableLogStash) {
							talendJobLog.addJobExceptionMessage(currentComponent, cLabel, null, e);
							talendJobLogProcess(globalMap);
						}
					}
				} catch (Exception e) {
					this.e.printStackTrace();
				}
			}
		}
	}

	public void tDBInput_2_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBOutput_1_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		tDBInput_2_onSubJobError(exception, errorComponent, globalMap);
	}

	public void talendJobLog_error(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		end_Hash.put(errorComponent, System.currentTimeMillis());

		status = "failure";

		talendJobLog_onSubJobError(exception, errorComponent, globalMap);
	}

	public void tDBInput_2_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public void talendJobLog_onSubJobError(Exception exception, String errorComponent,
			final java.util.Map<String, Object> globalMap) throws TalendException {

		resumeUtil.addLog("SYSTEM_LOG", "NODE:" + errorComponent, "", Thread.currentThread().getId() + "", "FATAL", "",
				exception.getMessage(), ResumeUtil.getExceptionStackTrace(exception), "");

	}

	public static class row1Struct implements routines.system.IPersistableRow<row1Struct> {
		final static byte[] commonByteArrayLock_TALEND_test = new byte[0];
		static byte[] commonByteArray_TALEND_test = new byte[0];

		public int SalesOrderID;

		public int getSalesOrderID() {
			return this.SalesOrderID;
		}

		public Boolean SalesOrderIDIsNullable() {
			return false;
		}

		public Boolean SalesOrderIDIsKey() {
			return false;
		}

		public Integer SalesOrderIDLength() {
			return 10;
		}

		public Integer SalesOrderIDPrecision() {
			return 0;
		}

		public String SalesOrderIDDefault() {

			return "";

		}

		public String SalesOrderIDComment() {

			return "";

		}

		public String SalesOrderIDPattern() {

			return "";

		}

		public String SalesOrderIDOriginalDbColumnName() {

			return "SalesOrderID";

		}

		public short RevisionNumber;

		public short getRevisionNumber() {
			return this.RevisionNumber;
		}

		public Boolean RevisionNumberIsNullable() {
			return false;
		}

		public Boolean RevisionNumberIsKey() {
			return false;
		}

		public Integer RevisionNumberLength() {
			return 3;
		}

		public Integer RevisionNumberPrecision() {
			return 0;
		}

		public String RevisionNumberDefault() {

			return "";

		}

		public String RevisionNumberComment() {

			return "";

		}

		public String RevisionNumberPattern() {

			return "";

		}

		public String RevisionNumberOriginalDbColumnName() {

			return "RevisionNumber";

		}

		public java.util.Date OrderDate;

		public java.util.Date getOrderDate() {
			return this.OrderDate;
		}

		public Boolean OrderDateIsNullable() {
			return false;
		}

		public Boolean OrderDateIsKey() {
			return false;
		}

		public Integer OrderDateLength() {
			return 23;
		}

		public Integer OrderDatePrecision() {
			return 3;
		}

		public String OrderDateDefault() {

			return null;

		}

		public String OrderDateComment() {

			return "";

		}

		public String OrderDatePattern() {

			return "dd-MM-yyyy";

		}

		public String OrderDateOriginalDbColumnName() {

			return "OrderDate";

		}

		public java.util.Date DueDate;

		public java.util.Date getDueDate() {
			return this.DueDate;
		}

		public Boolean DueDateIsNullable() {
			return false;
		}

		public Boolean DueDateIsKey() {
			return false;
		}

		public Integer DueDateLength() {
			return 23;
		}

		public Integer DueDatePrecision() {
			return 3;
		}

		public String DueDateDefault() {

			return null;

		}

		public String DueDateComment() {

			return "";

		}

		public String DueDatePattern() {

			return "dd-MM-yyyy";

		}

		public String DueDateOriginalDbColumnName() {

			return "DueDate";

		}

		public java.util.Date ShipDate;

		public java.util.Date getShipDate() {
			return this.ShipDate;
		}

		public Boolean ShipDateIsNullable() {
			return true;
		}

		public Boolean ShipDateIsKey() {
			return false;
		}

		public Integer ShipDateLength() {
			return 23;
		}

		public Integer ShipDatePrecision() {
			return 3;
		}

		public String ShipDateDefault() {

			return null;

		}

		public String ShipDateComment() {

			return "";

		}

		public String ShipDatePattern() {

			return "dd-MM-yyyy";

		}

		public String ShipDateOriginalDbColumnName() {

			return "ShipDate";

		}

		public short Status;

		public short getStatus() {
			return this.Status;
		}

		public Boolean StatusIsNullable() {
			return false;
		}

		public Boolean StatusIsKey() {
			return false;
		}

		public Integer StatusLength() {
			return 3;
		}

		public Integer StatusPrecision() {
			return 0;
		}

		public String StatusDefault() {

			return "";

		}

		public String StatusComment() {

			return "";

		}

		public String StatusPattern() {

			return "";

		}

		public String StatusOriginalDbColumnName() {

			return "Status";

		}

		public boolean OnlineOrderFlag;

		public boolean getOnlineOrderFlag() {
			return this.OnlineOrderFlag;
		}

		public Boolean OnlineOrderFlagIsNullable() {
			return false;
		}

		public Boolean OnlineOrderFlagIsKey() {
			return false;
		}

		public Integer OnlineOrderFlagLength() {
			return 1;
		}

		public Integer OnlineOrderFlagPrecision() {
			return 0;
		}

		public String OnlineOrderFlagDefault() {

			return "";

		}

		public String OnlineOrderFlagComment() {

			return "";

		}

		public String OnlineOrderFlagPattern() {

			return "";

		}

		public String OnlineOrderFlagOriginalDbColumnName() {

			return "OnlineOrderFlag";

		}

		public String SalesOrderNumber;

		public String getSalesOrderNumber() {
			return this.SalesOrderNumber;
		}

		public Boolean SalesOrderNumberIsNullable() {
			return false;
		}

		public Boolean SalesOrderNumberIsKey() {
			return false;
		}

		public Integer SalesOrderNumberLength() {
			return 25;
		}

		public Integer SalesOrderNumberPrecision() {
			return 0;
		}

		public String SalesOrderNumberDefault() {

			return null;

		}

		public String SalesOrderNumberComment() {

			return "";

		}

		public String SalesOrderNumberPattern() {

			return "";

		}

		public String SalesOrderNumberOriginalDbColumnName() {

			return "SalesOrderNumber";

		}

		public String PurchaseOrderNumber;

		public String getPurchaseOrderNumber() {
			return this.PurchaseOrderNumber;
		}

		public Boolean PurchaseOrderNumberIsNullable() {
			return true;
		}

		public Boolean PurchaseOrderNumberIsKey() {
			return false;
		}

		public Integer PurchaseOrderNumberLength() {
			return 25;
		}

		public Integer PurchaseOrderNumberPrecision() {
			return 0;
		}

		public String PurchaseOrderNumberDefault() {

			return null;

		}

		public String PurchaseOrderNumberComment() {

			return "";

		}

		public String PurchaseOrderNumberPattern() {

			return "";

		}

		public String PurchaseOrderNumberOriginalDbColumnName() {

			return "PurchaseOrderNumber";

		}

		public String AccountNumber;

		public String getAccountNumber() {
			return this.AccountNumber;
		}

		public Boolean AccountNumberIsNullable() {
			return true;
		}

		public Boolean AccountNumberIsKey() {
			return false;
		}

		public Integer AccountNumberLength() {
			return 15;
		}

		public Integer AccountNumberPrecision() {
			return 0;
		}

		public String AccountNumberDefault() {

			return null;

		}

		public String AccountNumberComment() {

			return "";

		}

		public String AccountNumberPattern() {

			return "";

		}

		public String AccountNumberOriginalDbColumnName() {

			return "AccountNumber";

		}

		public int CustomerID;

		public int getCustomerID() {
			return this.CustomerID;
		}

		public Boolean CustomerIDIsNullable() {
			return false;
		}

		public Boolean CustomerIDIsKey() {
			return false;
		}

		public Integer CustomerIDLength() {
			return 10;
		}

		public Integer CustomerIDPrecision() {
			return 0;
		}

		public String CustomerIDDefault() {

			return "";

		}

		public String CustomerIDComment() {

			return "";

		}

		public String CustomerIDPattern() {

			return "";

		}

		public String CustomerIDOriginalDbColumnName() {

			return "CustomerID";

		}

		public Integer SalesPersonID;

		public Integer getSalesPersonID() {
			return this.SalesPersonID;
		}

		public Boolean SalesPersonIDIsNullable() {
			return true;
		}

		public Boolean SalesPersonIDIsKey() {
			return false;
		}

		public Integer SalesPersonIDLength() {
			return 10;
		}

		public Integer SalesPersonIDPrecision() {
			return 0;
		}

		public String SalesPersonIDDefault() {

			return "";

		}

		public String SalesPersonIDComment() {

			return "";

		}

		public String SalesPersonIDPattern() {

			return "";

		}

		public String SalesPersonIDOriginalDbColumnName() {

			return "SalesPersonID";

		}

		public Integer TerritoryID;

		public Integer getTerritoryID() {
			return this.TerritoryID;
		}

		public Boolean TerritoryIDIsNullable() {
			return true;
		}

		public Boolean TerritoryIDIsKey() {
			return false;
		}

		public Integer TerritoryIDLength() {
			return 10;
		}

		public Integer TerritoryIDPrecision() {
			return 0;
		}

		public String TerritoryIDDefault() {

			return "";

		}

		public String TerritoryIDComment() {

			return "";

		}

		public String TerritoryIDPattern() {

			return "";

		}

		public String TerritoryIDOriginalDbColumnName() {

			return "TerritoryID";

		}

		public int BillToAddressID;

		public int getBillToAddressID() {
			return this.BillToAddressID;
		}

		public Boolean BillToAddressIDIsNullable() {
			return false;
		}

		public Boolean BillToAddressIDIsKey() {
			return false;
		}

		public Integer BillToAddressIDLength() {
			return 10;
		}

		public Integer BillToAddressIDPrecision() {
			return 0;
		}

		public String BillToAddressIDDefault() {

			return "";

		}

		public String BillToAddressIDComment() {

			return "";

		}

		public String BillToAddressIDPattern() {

			return "";

		}

		public String BillToAddressIDOriginalDbColumnName() {

			return "BillToAddressID";

		}

		public int ShipToAddressID;

		public int getShipToAddressID() {
			return this.ShipToAddressID;
		}

		public Boolean ShipToAddressIDIsNullable() {
			return false;
		}

		public Boolean ShipToAddressIDIsKey() {
			return false;
		}

		public Integer ShipToAddressIDLength() {
			return 10;
		}

		public Integer ShipToAddressIDPrecision() {
			return 0;
		}

		public String ShipToAddressIDDefault() {

			return "";

		}

		public String ShipToAddressIDComment() {

			return "";

		}

		public String ShipToAddressIDPattern() {

			return "";

		}

		public String ShipToAddressIDOriginalDbColumnName() {

			return "ShipToAddressID";

		}

		public int ShipMethodID;

		public int getShipMethodID() {
			return this.ShipMethodID;
		}

		public Boolean ShipMethodIDIsNullable() {
			return false;
		}

		public Boolean ShipMethodIDIsKey() {
			return false;
		}

		public Integer ShipMethodIDLength() {
			return 10;
		}

		public Integer ShipMethodIDPrecision() {
			return 0;
		}

		public String ShipMethodIDDefault() {

			return "";

		}

		public String ShipMethodIDComment() {

			return "";

		}

		public String ShipMethodIDPattern() {

			return "";

		}

		public String ShipMethodIDOriginalDbColumnName() {

			return "ShipMethodID";

		}

		public Integer CreditCardID;

		public Integer getCreditCardID() {
			return this.CreditCardID;
		}

		public Boolean CreditCardIDIsNullable() {
			return true;
		}

		public Boolean CreditCardIDIsKey() {
			return false;
		}

		public Integer CreditCardIDLength() {
			return 10;
		}

		public Integer CreditCardIDPrecision() {
			return 0;
		}

		public String CreditCardIDDefault() {

			return "";

		}

		public String CreditCardIDComment() {

			return "";

		}

		public String CreditCardIDPattern() {

			return "";

		}

		public String CreditCardIDOriginalDbColumnName() {

			return "CreditCardID";

		}

		public String CreditCardApprovalCode;

		public String getCreditCardApprovalCode() {
			return this.CreditCardApprovalCode;
		}

		public Boolean CreditCardApprovalCodeIsNullable() {
			return true;
		}

		public Boolean CreditCardApprovalCodeIsKey() {
			return false;
		}

		public Integer CreditCardApprovalCodeLength() {
			return 15;
		}

		public Integer CreditCardApprovalCodePrecision() {
			return 0;
		}

		public String CreditCardApprovalCodeDefault() {

			return null;

		}

		public String CreditCardApprovalCodeComment() {

			return "";

		}

		public String CreditCardApprovalCodePattern() {

			return "";

		}

		public String CreditCardApprovalCodeOriginalDbColumnName() {

			return "CreditCardApprovalCode";

		}

		public Integer CurrencyRateID;

		public Integer getCurrencyRateID() {
			return this.CurrencyRateID;
		}

		public Boolean CurrencyRateIDIsNullable() {
			return true;
		}

		public Boolean CurrencyRateIDIsKey() {
			return false;
		}

		public Integer CurrencyRateIDLength() {
			return 10;
		}

		public Integer CurrencyRateIDPrecision() {
			return 0;
		}

		public String CurrencyRateIDDefault() {

			return "";

		}

		public String CurrencyRateIDComment() {

			return "";

		}

		public String CurrencyRateIDPattern() {

			return "";

		}

		public String CurrencyRateIDOriginalDbColumnName() {

			return "CurrencyRateID";

		}

		public String SubTotal;

		public String getSubTotal() {
			return this.SubTotal;
		}

		public Boolean SubTotalIsNullable() {
			return false;
		}

		public Boolean SubTotalIsKey() {
			return false;
		}

		public Integer SubTotalLength() {
			return 19;
		}

		public Integer SubTotalPrecision() {
			return 4;
		}

		public String SubTotalDefault() {

			return null;

		}

		public String SubTotalComment() {

			return "";

		}

		public String SubTotalPattern() {

			return "";

		}

		public String SubTotalOriginalDbColumnName() {

			return "SubTotal";

		}

		public String TaxAmt;

		public String getTaxAmt() {
			return this.TaxAmt;
		}

		public Boolean TaxAmtIsNullable() {
			return false;
		}

		public Boolean TaxAmtIsKey() {
			return false;
		}

		public Integer TaxAmtLength() {
			return 19;
		}

		public Integer TaxAmtPrecision() {
			return 4;
		}

		public String TaxAmtDefault() {

			return null;

		}

		public String TaxAmtComment() {

			return "";

		}

		public String TaxAmtPattern() {

			return "";

		}

		public String TaxAmtOriginalDbColumnName() {

			return "TaxAmt";

		}

		public String Freight;

		public String getFreight() {
			return this.Freight;
		}

		public Boolean FreightIsNullable() {
			return false;
		}

		public Boolean FreightIsKey() {
			return false;
		}

		public Integer FreightLength() {
			return 19;
		}

		public Integer FreightPrecision() {
			return 4;
		}

		public String FreightDefault() {

			return null;

		}

		public String FreightComment() {

			return "";

		}

		public String FreightPattern() {

			return "";

		}

		public String FreightOriginalDbColumnName() {

			return "Freight";

		}

		public String TotalDue;

		public String getTotalDue() {
			return this.TotalDue;
		}

		public Boolean TotalDueIsNullable() {
			return false;
		}

		public Boolean TotalDueIsKey() {
			return false;
		}

		public Integer TotalDueLength() {
			return 19;
		}

		public Integer TotalDuePrecision() {
			return 4;
		}

		public String TotalDueDefault() {

			return null;

		}

		public String TotalDueComment() {

			return "";

		}

		public String TotalDuePattern() {

			return "";

		}

		public String TotalDueOriginalDbColumnName() {

			return "TotalDue";

		}

		public String Comment;

		public String getComment() {
			return this.Comment;
		}

		public Boolean CommentIsNullable() {
			return true;
		}

		public Boolean CommentIsKey() {
			return false;
		}

		public Integer CommentLength() {
			return 128;
		}

		public Integer CommentPrecision() {
			return 0;
		}

		public String CommentDefault() {

			return null;

		}

		public String CommentComment() {

			return "";

		}

		public String CommentPattern() {

			return "";

		}

		public String CommentOriginalDbColumnName() {

			return "Comment";

		}

		public Object rowguid;

		public Object getRowguid() {
			return this.rowguid;
		}

		public Boolean rowguidIsNullable() {
			return false;
		}

		public Boolean rowguidIsKey() {
			return false;
		}

		public Integer rowguidLength() {
			return 36;
		}

		public Integer rowguidPrecision() {
			return 0;
		}

		public String rowguidDefault() {

			return "";

		}

		public String rowguidComment() {

			return "";

		}

		public String rowguidPattern() {

			return "";

		}

		public String rowguidOriginalDbColumnName() {

			return "rowguid";

		}

		public java.util.Date ModifiedDate;

		public java.util.Date getModifiedDate() {
			return this.ModifiedDate;
		}

		public Boolean ModifiedDateIsNullable() {
			return false;
		}

		public Boolean ModifiedDateIsKey() {
			return false;
		}

		public Integer ModifiedDateLength() {
			return 23;
		}

		public Integer ModifiedDatePrecision() {
			return 3;
		}

		public String ModifiedDateDefault() {

			return null;

		}

		public String ModifiedDateComment() {

			return "";

		}

		public String ModifiedDatePattern() {

			return "dd-MM-yyyy";

		}

		public String ModifiedDateOriginalDbColumnName() {

			return "ModifiedDate";

		}

		private java.util.Date readDate(ObjectInputStream dis) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(dis.readLong());
			}
			return dateReturn;
		}

		private java.util.Date readDate(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			java.util.Date dateReturn = null;
			int length = 0;
			length = unmarshaller.readByte();
			if (length == -1) {
				dateReturn = null;
			} else {
				dateReturn = new Date(unmarshaller.readLong());
			}
			return dateReturn;
		}

		private void writeDate(java.util.Date date1, ObjectOutputStream dos) throws IOException {
			if (date1 == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeLong(date1.getTime());
			}
		}

		private void writeDate(java.util.Date date1, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (date1 == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeLong(date1.getTime());
			}
		}

		private String readString(ObjectInputStream dis) throws IOException {
			String strReturn = null;
			int length = 0;
			length = dis.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_test.length) {
					if (length < 1024 && commonByteArray_TALEND_test.length == 0) {
						commonByteArray_TALEND_test = new byte[1024];
					} else {
						commonByteArray_TALEND_test = new byte[2 * length];
					}
				}
				dis.readFully(commonByteArray_TALEND_test, 0, length);
				strReturn = new String(commonByteArray_TALEND_test, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private String readString(org.jboss.marshalling.Unmarshaller unmarshaller) throws IOException {
			String strReturn = null;
			int length = 0;
			length = unmarshaller.readInt();
			if (length == -1) {
				strReturn = null;
			} else {
				if (length > commonByteArray_TALEND_test.length) {
					if (length < 1024 && commonByteArray_TALEND_test.length == 0) {
						commonByteArray_TALEND_test = new byte[1024];
					} else {
						commonByteArray_TALEND_test = new byte[2 * length];
					}
				}
				unmarshaller.readFully(commonByteArray_TALEND_test, 0, length);
				strReturn = new String(commonByteArray_TALEND_test, 0, length, utf8Charset);
			}
			return strReturn;
		}

		private void writeString(String str, ObjectOutputStream dos) throws IOException {
			if (str == null) {
				dos.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				dos.writeInt(byteArray.length);
				dos.write(byteArray);
			}
		}

		private void writeString(String str, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (str == null) {
				marshaller.writeInt(-1);
			} else {
				byte[] byteArray = str.getBytes(utf8Charset);
				marshaller.writeInt(byteArray.length);
				marshaller.write(byteArray);
			}
		}

		private Integer readInteger(ObjectInputStream dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private Integer readInteger(org.jboss.marshalling.Unmarshaller dis) throws IOException {
			Integer intReturn;
			int length = 0;
			length = dis.readByte();
			if (length == -1) {
				intReturn = null;
			} else {
				intReturn = dis.readInt();
			}
			return intReturn;
		}

		private void writeInteger(Integer intNum, ObjectOutputStream dos) throws IOException {
			if (intNum == null) {
				dos.writeByte(-1);
			} else {
				dos.writeByte(0);
				dos.writeInt(intNum);
			}
		}

		private void writeInteger(Integer intNum, org.jboss.marshalling.Marshaller marshaller) throws IOException {
			if (intNum == null) {
				marshaller.writeByte(-1);
			} else {
				marshaller.writeByte(0);
				marshaller.writeInt(intNum);
			}
		}

		public void readData(ObjectInputStream dis) {

			synchronized (commonByteArrayLock_TALEND_test) {

				try {

					int length = 0;

					this.SalesOrderID = dis.readInt();

					this.RevisionNumber = dis.readShort();

					this.OrderDate = readDate(dis);

					this.DueDate = readDate(dis);

					this.ShipDate = readDate(dis);

					this.Status = dis.readShort();

					this.OnlineOrderFlag = dis.readBoolean();

					this.SalesOrderNumber = readString(dis);

					this.PurchaseOrderNumber = readString(dis);

					this.AccountNumber = readString(dis);

					this.CustomerID = dis.readInt();

					this.SalesPersonID = readInteger(dis);

					this.TerritoryID = readInteger(dis);

					this.BillToAddressID = dis.readInt();

					this.ShipToAddressID = dis.readInt();

					this.ShipMethodID = dis.readInt();

					this.CreditCardID = readInteger(dis);

					this.CreditCardApprovalCode = readString(dis);

					this.CurrencyRateID = readInteger(dis);

					this.SubTotal = readString(dis);

					this.TaxAmt = readString(dis);

					this.Freight = readString(dis);

					this.TotalDue = readString(dis);

					this.Comment = readString(dis);

					this.rowguid = (Object) dis.readObject();

					this.ModifiedDate = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void readData(org.jboss.marshalling.Unmarshaller dis) {

			synchronized (commonByteArrayLock_TALEND_test) {

				try {

					int length = 0;

					this.SalesOrderID = dis.readInt();

					this.RevisionNumber = dis.readShort();

					this.OrderDate = readDate(dis);

					this.DueDate = readDate(dis);

					this.ShipDate = readDate(dis);

					this.Status = dis.readShort();

					this.OnlineOrderFlag = dis.readBoolean();

					this.SalesOrderNumber = readString(dis);

					this.PurchaseOrderNumber = readString(dis);

					this.AccountNumber = readString(dis);

					this.CustomerID = dis.readInt();

					this.SalesPersonID = readInteger(dis);

					this.TerritoryID = readInteger(dis);

					this.BillToAddressID = dis.readInt();

					this.ShipToAddressID = dis.readInt();

					this.ShipMethodID = dis.readInt();

					this.CreditCardID = readInteger(dis);

					this.CreditCardApprovalCode = readString(dis);

					this.CurrencyRateID = readInteger(dis);

					this.SubTotal = readString(dis);

					this.TaxAmt = readString(dis);

					this.Freight = readString(dis);

					this.TotalDue = readString(dis);

					this.Comment = readString(dis);

					this.rowguid = (Object) dis.readObject();

					this.ModifiedDate = readDate(dis);

				} catch (IOException e) {
					throw new RuntimeException(e);

				} catch (ClassNotFoundException eCNFE) {
					throw new RuntimeException(eCNFE);

				}

			}

		}

		public void writeData(ObjectOutputStream dos) {
			try {

				// int

				dos.writeInt(this.SalesOrderID);

				// short

				dos.writeShort(this.RevisionNumber);

				// java.util.Date

				writeDate(this.OrderDate, dos);

				// java.util.Date

				writeDate(this.DueDate, dos);

				// java.util.Date

				writeDate(this.ShipDate, dos);

				// short

				dos.writeShort(this.Status);

				// boolean

				dos.writeBoolean(this.OnlineOrderFlag);

				// String

				writeString(this.SalesOrderNumber, dos);

				// String

				writeString(this.PurchaseOrderNumber, dos);

				// String

				writeString(this.AccountNumber, dos);

				// int

				dos.writeInt(this.CustomerID);

				// Integer

				writeInteger(this.SalesPersonID, dos);

				// Integer

				writeInteger(this.TerritoryID, dos);

				// int

				dos.writeInt(this.BillToAddressID);

				// int

				dos.writeInt(this.ShipToAddressID);

				// int

				dos.writeInt(this.ShipMethodID);

				// Integer

				writeInteger(this.CreditCardID, dos);

				// String

				writeString(this.CreditCardApprovalCode, dos);

				// Integer

				writeInteger(this.CurrencyRateID, dos);

				// String

				writeString(this.SubTotal, dos);

				// String

				writeString(this.TaxAmt, dos);

				// String

				writeString(this.Freight, dos);

				// String

				writeString(this.TotalDue, dos);

				// String

				writeString(this.Comment, dos);

				// Object

				dos.writeObject(this.rowguid);

				// java.util.Date

				writeDate(this.ModifiedDate, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public void writeData(org.jboss.marshalling.Marshaller dos) {
			try {

				// int

				dos.writeInt(this.SalesOrderID);

				// short

				dos.writeShort(this.RevisionNumber);

				// java.util.Date

				writeDate(this.OrderDate, dos);

				// java.util.Date

				writeDate(this.DueDate, dos);

				// java.util.Date

				writeDate(this.ShipDate, dos);

				// short

				dos.writeShort(this.Status);

				// boolean

				dos.writeBoolean(this.OnlineOrderFlag);

				// String

				writeString(this.SalesOrderNumber, dos);

				// String

				writeString(this.PurchaseOrderNumber, dos);

				// String

				writeString(this.AccountNumber, dos);

				// int

				dos.writeInt(this.CustomerID);

				// Integer

				writeInteger(this.SalesPersonID, dos);

				// Integer

				writeInteger(this.TerritoryID, dos);

				// int

				dos.writeInt(this.BillToAddressID);

				// int

				dos.writeInt(this.ShipToAddressID);

				// int

				dos.writeInt(this.ShipMethodID);

				// Integer

				writeInteger(this.CreditCardID, dos);

				// String

				writeString(this.CreditCardApprovalCode, dos);

				// Integer

				writeInteger(this.CurrencyRateID, dos);

				// String

				writeString(this.SubTotal, dos);

				// String

				writeString(this.TaxAmt, dos);

				// String

				writeString(this.Freight, dos);

				// String

				writeString(this.TotalDue, dos);

				// String

				writeString(this.Comment, dos);

				// Object

				dos.clearInstanceCache();
				dos.writeObject(this.rowguid);

				// java.util.Date

				writeDate(this.ModifiedDate, dos);

			} catch (IOException e) {
				throw new RuntimeException(e);
			}

		}

		public String toString() {

			StringBuilder sb = new StringBuilder();
			sb.append(super.toString());
			sb.append("[");
			sb.append("SalesOrderID=" + String.valueOf(SalesOrderID));
			sb.append(",RevisionNumber=" + String.valueOf(RevisionNumber));
			sb.append(",OrderDate=" + String.valueOf(OrderDate));
			sb.append(",DueDate=" + String.valueOf(DueDate));
			sb.append(",ShipDate=" + String.valueOf(ShipDate));
			sb.append(",Status=" + String.valueOf(Status));
			sb.append(",OnlineOrderFlag=" + String.valueOf(OnlineOrderFlag));
			sb.append(",SalesOrderNumber=" + SalesOrderNumber);
			sb.append(",PurchaseOrderNumber=" + PurchaseOrderNumber);
			sb.append(",AccountNumber=" + AccountNumber);
			sb.append(",CustomerID=" + String.valueOf(CustomerID));
			sb.append(",SalesPersonID=" + String.valueOf(SalesPersonID));
			sb.append(",TerritoryID=" + String.valueOf(TerritoryID));
			sb.append(",BillToAddressID=" + String.valueOf(BillToAddressID));
			sb.append(",ShipToAddressID=" + String.valueOf(ShipToAddressID));
			sb.append(",ShipMethodID=" + String.valueOf(ShipMethodID));
			sb.append(",CreditCardID=" + String.valueOf(CreditCardID));
			sb.append(",CreditCardApprovalCode=" + CreditCardApprovalCode);
			sb.append(",CurrencyRateID=" + String.valueOf(CurrencyRateID));
			sb.append(",SubTotal=" + SubTotal);
			sb.append(",TaxAmt=" + TaxAmt);
			sb.append(",Freight=" + Freight);
			sb.append(",TotalDue=" + TotalDue);
			sb.append(",Comment=" + Comment);
			sb.append(",rowguid=" + String.valueOf(rowguid));
			sb.append(",ModifiedDate=" + String.valueOf(ModifiedDate));
			sb.append("]");

			return sb.toString();
		}

		public String toLogString() {
			StringBuilder sb = new StringBuilder();

			sb.append(SalesOrderID);

			sb.append("|");

			sb.append(RevisionNumber);

			sb.append("|");

			if (OrderDate == null) {
				sb.append("<null>");
			} else {
				sb.append(OrderDate);
			}

			sb.append("|");

			if (DueDate == null) {
				sb.append("<null>");
			} else {
				sb.append(DueDate);
			}

			sb.append("|");

			if (ShipDate == null) {
				sb.append("<null>");
			} else {
				sb.append(ShipDate);
			}

			sb.append("|");

			sb.append(Status);

			sb.append("|");

			sb.append(OnlineOrderFlag);

			sb.append("|");

			if (SalesOrderNumber == null) {
				sb.append("<null>");
			} else {
				sb.append(SalesOrderNumber);
			}

			sb.append("|");

			if (PurchaseOrderNumber == null) {
				sb.append("<null>");
			} else {
				sb.append(PurchaseOrderNumber);
			}

			sb.append("|");

			if (AccountNumber == null) {
				sb.append("<null>");
			} else {
				sb.append(AccountNumber);
			}

			sb.append("|");

			sb.append(CustomerID);

			sb.append("|");

			if (SalesPersonID == null) {
				sb.append("<null>");
			} else {
				sb.append(SalesPersonID);
			}

			sb.append("|");

			if (TerritoryID == null) {
				sb.append("<null>");
			} else {
				sb.append(TerritoryID);
			}

			sb.append("|");

			sb.append(BillToAddressID);

			sb.append("|");

			sb.append(ShipToAddressID);

			sb.append("|");

			sb.append(ShipMethodID);

			sb.append("|");

			if (CreditCardID == null) {
				sb.append("<null>");
			} else {
				sb.append(CreditCardID);
			}

			sb.append("|");

			if (CreditCardApprovalCode == null) {
				sb.append("<null>");
			} else {
				sb.append(CreditCardApprovalCode);
			}

			sb.append("|");

			if (CurrencyRateID == null) {
				sb.append("<null>");
			} else {
				sb.append(CurrencyRateID);
			}

			sb.append("|");

			if (SubTotal == null) {
				sb.append("<null>");
			} else {
				sb.append(SubTotal);
			}

			sb.append("|");

			if (TaxAmt == null) {
				sb.append("<null>");
			} else {
				sb.append(TaxAmt);
			}

			sb.append("|");

			if (Freight == null) {
				sb.append("<null>");
			} else {
				sb.append(Freight);
			}

			sb.append("|");

			if (TotalDue == null) {
				sb.append("<null>");
			} else {
				sb.append(TotalDue);
			}

			sb.append("|");

			if (Comment == null) {
				sb.append("<null>");
			} else {
				sb.append(Comment);
			}

			sb.append("|");

			if (rowguid == null) {
				sb.append("<null>");
			} else {
				sb.append(rowguid);
			}

			sb.append("|");

			if (ModifiedDate == null) {
				sb.append("<null>");
			} else {
				sb.append(ModifiedDate);
			}

			sb.append("|");

			return sb.toString();
		}

		/**
		 * Compare keys
		 */
		public int compareTo(row1Struct other) {

			int returnValue = -1;

			return returnValue;
		}

		private int checkNullsAndCompare(Object object1, Object object2) {
			int returnValue = 0;
			if (object1 instanceof Comparable && object2 instanceof Comparable) {
				returnValue = ((Comparable) object1).compareTo(object2);
			} else if (object1 != null && object2 != null) {
				returnValue = compareStrings(object1.toString(), object2.toString());
			} else if (object1 == null && object2 != null) {
				returnValue = 1;
			} else if (object1 != null && object2 == null) {
				returnValue = -1;
			} else {
				returnValue = 0;
			}

			return returnValue;
		}

		private int compareStrings(String string1, String string2) {
			return string1.compareTo(string2);
		}

	}

	public void tDBInput_2Process(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("tDBInput_2_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		mdc("tDBInput_2", "IZdFPF_");

		String iterateId = "";

		String currentComponent = "";
		s("none");
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				row1Struct row1 = new row1Struct();

				/**
				 * [tDBOutput_1 begin ] start
				 */

				sh("tDBOutput_1");

				s(currentComponent = "tDBOutput_1");

				runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, 0, 0, "row1");

				int tos_count_tDBOutput_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tDBOutput_1 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tDBOutput_1 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tDBOutput_1 = new StringBuilder();
							log4jParamters_tDBOutput_1.append("Parameters:");
							log4jParamters_tDBOutput_1.append("USE_EXISTING_CONNECTION" + " = " + "false");
							log4jParamters_tDBOutput_1.append(" | ");
							log4jParamters_tDBOutput_1.append("DRIVER" + " = " + "MSSQL_PROP");
							log4jParamters_tDBOutput_1.append(" | ");
							log4jParamters_tDBOutput_1.append("HOST" + " = " + "context.connection_local_Server");
							log4jParamters_tDBOutput_1.append(" | ");
							log4jParamters_tDBOutput_1.append("PORT" + " = " + "context.connection_local_Port");
							log4jParamters_tDBOutput_1.append(" | ");
							log4jParamters_tDBOutput_1.append("DB_SCHEMA" + " = " + "context.connection_local_Schema");
							log4jParamters_tDBOutput_1.append(" | ");
							log4jParamters_tDBOutput_1.append("DBNAME" + " = " + "context.connection_local_Database");
							log4jParamters_tDBOutput_1.append(" | ");
							log4jParamters_tDBOutput_1.append("USER" + " = " + "context.connection_local_Login");
							log4jParamters_tDBOutput_1.append(" | ");
							log4jParamters_tDBOutput_1.append("PASS" + " = "
									+ String.valueOf(routines.system.PasswordEncryptUtil
											.encryptPassword(context.connection_local_Password)).substring(0, 4)
									+ "...");
							log4jParamters_tDBOutput_1.append(" | ");
							log4jParamters_tDBOutput_1.append("TABLE" + " = " + "\"sales_test\"");
							log4jParamters_tDBOutput_1.append(" | ");
							log4jParamters_tDBOutput_1.append("TABLE_ACTION" + " = " + "DROP_IF_EXISTS_AND_CREATE");
							log4jParamters_tDBOutput_1.append(" | ");
							log4jParamters_tDBOutput_1.append("IDENTITY_INSERT" + " = " + "false");
							log4jParamters_tDBOutput_1.append(" | ");
							log4jParamters_tDBOutput_1.append("DATA_ACTION" + " = " + "INSERT");
							log4jParamters_tDBOutput_1.append(" | ");
							log4jParamters_tDBOutput_1.append("SPECIFY_IDENTITY_FIELD" + " = " + "false");
							log4jParamters_tDBOutput_1.append(" | ");
							log4jParamters_tDBOutput_1.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
							log4jParamters_tDBOutput_1.append(" | ");
							log4jParamters_tDBOutput_1.append("DIE_ON_ERROR" + " = " + "false");
							log4jParamters_tDBOutput_1.append(" | ");
							log4jParamters_tDBOutput_1
									.append("PROPERTIES" + " = " + "context.connection_local_AdditionalParams");
							log4jParamters_tDBOutput_1.append(" | ");
							log4jParamters_tDBOutput_1.append("ACTIVE_DIR_AUTH" + " = " + "false");
							log4jParamters_tDBOutput_1.append(" | ");
							log4jParamters_tDBOutput_1.append("ENABLE_ALWAYS_ENCRYPTED" + " = " + "false");
							log4jParamters_tDBOutput_1.append(" | ");
							log4jParamters_tDBOutput_1.append("COMMIT_EVERY" + " = " + "10000");
							log4jParamters_tDBOutput_1.append(" | ");
							log4jParamters_tDBOutput_1.append("ADD_COLS" + " = " + "[]");
							log4jParamters_tDBOutput_1.append(" | ");
							log4jParamters_tDBOutput_1.append("USE_FIELD_OPTIONS" + " = " + "false");
							log4jParamters_tDBOutput_1.append(" | ");
							log4jParamters_tDBOutput_1.append("IGNORE_DATE_OUTOF_RANGE" + " = " + "false");
							log4jParamters_tDBOutput_1.append(" | ");
							log4jParamters_tDBOutput_1.append("ENABLE_DEBUG_MODE" + " = " + "false");
							log4jParamters_tDBOutput_1.append(" | ");
							log4jParamters_tDBOutput_1.append("SUPPORT_NULL_WHERE" + " = " + "false");
							log4jParamters_tDBOutput_1.append(" | ");
							log4jParamters_tDBOutput_1.append("USE_BATCH_SIZE" + " = " + "true");
							log4jParamters_tDBOutput_1.append(" | ");
							log4jParamters_tDBOutput_1.append("BATCH_SIZE" + " = " + "10000");
							log4jParamters_tDBOutput_1.append(" | ");
							log4jParamters_tDBOutput_1.append("SET_QUERY_TIMEOUT" + " = " + "false");
							log4jParamters_tDBOutput_1.append(" | ");
							log4jParamters_tDBOutput_1.append("UNIFIED_COMPONENTS" + " = " + "tMSSqlOutput");
							log4jParamters_tDBOutput_1.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tDBOutput_1 - " + (log4jParamters_tDBOutput_1));
						}
					}
					new BytesLimit65535_tDBOutput_1().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tDBOutput_1", "tDBOutput_1", "tMSSqlOutput");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				int nb_line_tDBOutput_1 = 0;
				int nb_line_update_tDBOutput_1 = 0;
				int nb_line_inserted_tDBOutput_1 = 0;
				int nb_line_deleted_tDBOutput_1 = 0;
				int nb_line_rejected_tDBOutput_1 = 0;

				int deletedCount_tDBOutput_1 = 0;
				int updatedCount_tDBOutput_1 = 0;
				int insertedCount_tDBOutput_1 = 0;
				int rowsToCommitCount_tDBOutput_1 = 0;
				int rejectedCount_tDBOutput_1 = 0;
				String dbschema_tDBOutput_1 = null;
				String tableName_tDBOutput_1 = null;
				boolean whetherReject_tDBOutput_1 = false;

				java.util.Calendar calendar_tDBOutput_1 = java.util.Calendar.getInstance();
				long year1_tDBOutput_1 = TalendDate.parseDate("yyyy-MM-dd", "0001-01-01").getTime();
				long year2_tDBOutput_1 = TalendDate.parseDate("yyyy-MM-dd", "1753-01-01").getTime();
				long year10000_tDBOutput_1 = TalendDate.parseDate("yyyy-MM-dd HH:mm:ss", "9999-12-31 24:00:00")
						.getTime();
				long date_tDBOutput_1;

				java.util.Calendar calendar_datetimeoffset_tDBOutput_1 = java.util.Calendar
						.getInstance(java.util.TimeZone.getTimeZone("UTC"));

				java.sql.Connection conn_tDBOutput_1 = null;
				String dbUser_tDBOutput_1 = null;
				dbschema_tDBOutput_1 = context.connection_local_Schema;
				String driverClass_tDBOutput_1 = "com.microsoft.sqlserver.jdbc.SQLServerDriver";

				if (log.isDebugEnabled())
					log.debug("tDBOutput_1 - " + ("Driver ClassName: ") + (driverClass_tDBOutput_1) + ("."));
				java.lang.Class.forName(driverClass_tDBOutput_1);
				String port_tDBOutput_1 = context.connection_local_Port;
				String dbname_tDBOutput_1 = context.connection_local_Database;
				String url_tDBOutput_1 = "jdbc:sqlserver://" + context.connection_local_Server;
				if (!"".equals(port_tDBOutput_1)) {
					url_tDBOutput_1 += ":" + context.connection_local_Port;
				}
				if (!"".equals(dbname_tDBOutput_1)) {
					url_tDBOutput_1 += ";databaseName=" + context.connection_local_Database;

				}
				url_tDBOutput_1 += ";appName=" + projectName + ";" + context.connection_local_AdditionalParams;
				dbUser_tDBOutput_1 = context.connection_local_Login;

				final String decryptedPassword_tDBOutput_1 = context.connection_local_Password;

				String dbPwd_tDBOutput_1 = decryptedPassword_tDBOutput_1;
				if (log.isDebugEnabled())
					log.debug("tDBOutput_1 - " + ("Connection attempts to '") + (url_tDBOutput_1)
							+ ("' with the username '") + (dbUser_tDBOutput_1) + ("'."));
				conn_tDBOutput_1 = java.sql.DriverManager.getConnection(url_tDBOutput_1, dbUser_tDBOutput_1,
						dbPwd_tDBOutput_1);
				if (log.isDebugEnabled())
					log.debug("tDBOutput_1 - " + ("Connection to '") + (url_tDBOutput_1) + ("' has succeeded."));

				resourceMap.put("conn_tDBOutput_1", conn_tDBOutput_1);

				conn_tDBOutput_1.setAutoCommit(false);
				int commitEvery_tDBOutput_1 = 10000;
				int commitCounter_tDBOutput_1 = 0;

				if (log.isDebugEnabled())
					log.debug("tDBOutput_1 - " + ("Connection is set auto commit to '")
							+ (conn_tDBOutput_1.getAutoCommit()) + ("'."));
				int batchSize_tDBOutput_1 = 10000;
				int batchSizeCounter_tDBOutput_1 = 0;

				if (dbschema_tDBOutput_1 == null || dbschema_tDBOutput_1.trim().length() == 0) {
					tableName_tDBOutput_1 = "sales_test";
				} else {
					tableName_tDBOutput_1 = dbschema_tDBOutput_1 + "].[" + "sales_test";
				}
				int count_tDBOutput_1 = 0;

				boolean whetherExist_tDBOutput_1 = false;
				try (java.sql.Statement isExistStmt_tDBOutput_1 = conn_tDBOutput_1.createStatement()) {
					try {

						isExistStmt_tDBOutput_1.execute("SELECT TOP 1 1 FROM [" + tableName_tDBOutput_1 + "]");
						whetherExist_tDBOutput_1 = true;
					} catch (java.lang.Exception e) {
						globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());
						whetherExist_tDBOutput_1 = false;
					}
				}
				if (whetherExist_tDBOutput_1) {
					try (java.sql.Statement stmtDrop_tDBOutput_1 = conn_tDBOutput_1.createStatement()) {
						if (log.isDebugEnabled())
							log.debug("tDBOutput_1 - " + ("Dropping") + (" table '")
									+ ("[" + tableName_tDBOutput_1 + "]") + ("'."));
						stmtDrop_tDBOutput_1.execute("DROP TABLE [" + tableName_tDBOutput_1 + "]");
						if (log.isDebugEnabled())
							log.debug("tDBOutput_1 - " + ("Drop") + (" table '") + ("[" + tableName_tDBOutput_1 + "]")
									+ ("' has succeeded."));
					}
				}
				try (java.sql.Statement stmtCreate_tDBOutput_1 = conn_tDBOutput_1.createStatement()) {
					if (log.isDebugEnabled())
						log.debug("tDBOutput_1 - " + ("Creating") + (" table '") + ("[" + tableName_tDBOutput_1 + "]")
								+ ("'."));
					stmtCreate_tDBOutput_1.execute("CREATE TABLE [" + tableName_tDBOutput_1
							+ "]([SalesOrderID] INT  not null ,[RevisionNumber] TINYINT  not null ,[OrderDate] DATETIME  not null ,[DueDate] DATETIME  not null ,[ShipDate] DATETIME ,[Status] TINYINT  not null ,[OnlineOrderFlag] BIT  not null ,[SalesOrderNumber] NVARCHAR(25)   not null ,[PurchaseOrderNumber] NVARCHAR(25)  ,[AccountNumber] NVARCHAR(15)  ,[CustomerID] INT  not null ,[SalesPersonID] INT ,[TerritoryID] INT ,[BillToAddressID] INT  not null ,[ShipToAddressID] INT  not null ,[ShipMethodID] INT  not null ,[CreditCardID] INT ,[CreditCardApprovalCode] VARCHAR(15)  ,[CurrencyRateID] INT ,[SubTotal] VARCHAR(19)   not null ,[TaxAmt] VARCHAR(19)   not null ,[Freight] VARCHAR(19)   not null ,[TotalDue] VARCHAR(19)   not null ,[Comment] NVARCHAR(128)  ,[rowguid] UNIQUEIDENTIFIER  not null ,[ModifiedDate] DATETIME  not null )");
					if (log.isDebugEnabled())
						log.debug("tDBOutput_1 - " + ("Create") + (" table '") + ("[" + tableName_tDBOutput_1 + "]")
								+ ("' has succeeded."));
				}
				java.sql.PreparedStatement pstmt_tDBOutput_1 = null;
				java.sql.PreparedStatement pstmtInsert_tDBOutput_1 = null;
				java.sql.PreparedStatement pstmtUpdate_tDBOutput_1 = null;
				String insert_tDBOutput_1 = "INSERT INTO [" + tableName_tDBOutput_1
						+ "] ([SalesOrderID],[RevisionNumber],[OrderDate],[DueDate],[ShipDate],[Status],[OnlineOrderFlag],[SalesOrderNumber],[PurchaseOrderNumber],[AccountNumber],[CustomerID],[SalesPersonID],[TerritoryID],[BillToAddressID],[ShipToAddressID],[ShipMethodID],[CreditCardID],[CreditCardApprovalCode],[CurrencyRateID],[SubTotal],[TaxAmt],[Freight],[TotalDue],[Comment],[rowguid],[ModifiedDate]) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
				pstmt_tDBOutput_1 = conn_tDBOutput_1.prepareStatement(insert_tDBOutput_1);
				resourceMap.put("pstmt_tDBOutput_1", pstmt_tDBOutput_1);

				/**
				 * [tDBOutput_1 begin ] stop
				 */

				/**
				 * [tDBInput_2 begin ] start
				 */

				sh("tDBInput_2");

				s(currentComponent = "tDBInput_2");

				cLabel = "\"SalesOrderHeader\"";

				int tos_count_tDBInput_2 = 0;

				if (log.isDebugEnabled())
					log.debug("tDBInput_2 - " + ("Start to work."));
				if (log.isDebugEnabled()) {
					class BytesLimit65535_tDBInput_2 {
						public void limitLog4jByte() throws Exception {
							StringBuilder log4jParamters_tDBInput_2 = new StringBuilder();
							log4jParamters_tDBInput_2.append("Parameters:");
							log4jParamters_tDBInput_2.append("USE_EXISTING_CONNECTION" + " = " + "false");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("HOST" + " = " + "context.connection_local_Server");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("DRIVER" + " = " + "MSSQL_PROP");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("PORT" + " = " + "context.connection_local_Port");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("DB_SCHEMA" + " = " + "context.connection_local_Schema");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("DBNAME" + " = " + "context.connection_local_Database");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("USER" + " = " + "context.connection_local_Login");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("PASS" + " = "
									+ String.valueOf(routines.system.PasswordEncryptUtil
											.encryptPassword(context.connection_local_Password)).substring(0, 4)
									+ "...");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("TABLE" + " = " + "\"SalesOrderHeader\"");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("QUERYSTORE" + " = " + "\"\"");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("QUERY" + " = "
									+ "\"SELECT \\\"\"+context.connection_local_Schema+\"\\\".SalesOrderHeader.SalesOrderID, 		\\\"\"+context.connection_local_Schema+\"\\\".SalesOrderHeader.RevisionNumber, 		\\\"\"+context.connection_local_Schema+\"\\\".SalesOrderHeader.OrderDate, 		\\\"\"+context.connection_local_Schema+\"\\\".SalesOrderHeader.DueDate, 		\\\"\"+context.connection_local_Schema+\"\\\".SalesOrderHeader.ShipDate, 		\\\"\"+context.connection_local_Schema+\"\\\".SalesOrderHeader.Status, 		\\\"\"+context.connection_local_Schema+\"\\\".SalesOrderHeader.OnlineOrderFlag, 		\\\"\"+context.connection_local_Schema+\"\\\".SalesOrderHeader.SalesOrderNumber, 		\\\"\"+context.connection_local_Schema+\"\\\".SalesOrderHeader.PurchaseOrderNumber, 		\\\"\"+context.connection_local_Schema+\"\\\".SalesOrderHeader.AccountNumber, 		\\\"\"+context.connection_local_Schema+\"\\\".SalesOrderHeader.CustomerID, 		\\\"\"+context.connection_local_Schema+\"\\\".SalesOrderHeader.SalesPersonID, 		\\\"\"+context.connection_local_Schema+\"\\\".SalesOrderHeader.TerritoryID, 		\\\"\"+context.connection_local_Schema+\"\\\".SalesOrderHeader.BillToAddressID, 		\\\"\"+context.connection_local_Schema+\"\\\".SalesOrderHeader.ShipToAddressID, 		\\\"\"+context.connection_local_Schema+\"\\\".SalesOrderHeader.ShipMethodID, 		\\\"\"+context.connection_local_Schema+\"\\\".SalesOrderHeader.CreditCardID, 		\\\"\"+context.connection_local_Schema+\"\\\".SalesOrderHeader.CreditCardApprovalCode, 		\\\"\"+context.connection_local_Schema+\"\\\".SalesOrderHeader.CurrencyRateID, 		\\\"\"+context.connection_local_Schema+\"\\\".SalesOrderHeader.SubTotal, 		\\\"\"+context.connection_local_Schema+\"\\\".SalesOrderHeader.TaxAmt, 		\\\"\"+context.connection_local_Schema+\"\\\".SalesOrderHeader.Freight, 		\\\"\"+context.connection_local_Schema+\"\\\".SalesOrderHeader.TotalDue, 		\\\"\"+context.connection_local_Schema+\"\\\".SalesOrderHeader.Comment, 		\\\"\"+context.connection_local_Schema+\"\\\".SalesOrderHeader.rowguid, 		\\\"\"+context.connection_local_Schema+\"\\\".SalesOrderHeader.ModifiedDate\" +\" FROM	\\\"\"+context.connection_local_Schema+\"\\\".SalesOrderHeader\"");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("SPECIFY_DATASOURCE_ALIAS" + " = " + "false");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2
									.append("PROPERTIES" + " = " + "context.connection_local_AdditionalParams");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("ACTIVE_DIR_AUTH" + " = " + "false");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("ENABLE_ALWAYS_ENCRYPTED" + " = " + "false");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("TRIM_ALL_COLUMN" + " = " + "false");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("TRIM_COLUMN" + " = " + "[{TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("SalesOrderID") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("RevisionNumber") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("OrderDate") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("DueDate") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("ShipDate")
									+ "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("Status") + "}, {TRIM="
									+ ("false") + ", SCHEMA_COLUMN=" + ("OnlineOrderFlag") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("SalesOrderNumber") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("PurchaseOrderNumber") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("AccountNumber") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("CustomerID") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("SalesPersonID") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("TerritoryID")
									+ "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("BillToAddressID") + "}, {TRIM="
									+ ("false") + ", SCHEMA_COLUMN=" + ("ShipToAddressID") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("ShipMethodID") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("CreditCardID") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("CreditCardApprovalCode") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("CurrencyRateID") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("SubTotal") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("TaxAmt") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("Freight")
									+ "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN=" + ("TotalDue") + "}, {TRIM="
									+ ("false") + ", SCHEMA_COLUMN=" + ("Comment") + "}, {TRIM=" + ("false")
									+ ", SCHEMA_COLUMN=" + ("rowguid") + "}, {TRIM=" + ("false") + ", SCHEMA_COLUMN="
									+ ("ModifiedDate") + "}]");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("SET_QUERY_TIMEOUT" + " = " + "false");
							log4jParamters_tDBInput_2.append(" | ");
							log4jParamters_tDBInput_2.append("UNIFIED_COMPONENTS" + " = " + "tMSSqlInput");
							log4jParamters_tDBInput_2.append(" | ");
							if (log.isDebugEnabled())
								log.debug("tDBInput_2 - " + (log4jParamters_tDBInput_2));
						}
					}
					new BytesLimit65535_tDBInput_2().limitLog4jByte();
				}
				if (enableLogStash) {
					talendJobLog.addCM("tDBInput_2", "\"SalesOrderHeader\"", "tMSSqlInput");
					talendJobLogProcess(globalMap);
					s(currentComponent);
				}

				org.talend.designer.components.util.mssql.MSSqlGenerateTimestampUtil mssqlGTU_tDBInput_2 = org.talend.designer.components.util.mssql.MSSqlUtilFactory
						.getMSSqlGenerateTimestampUtil();

				java.util.List<String> talendToDBList_tDBInput_2 = new java.util.ArrayList();
				String[] talendToDBArray_tDBInput_2 = new String[] { "FLOAT", "NUMERIC", "NUMERIC IDENTITY", "DECIMAL",
						"DECIMAL IDENTITY", "REAL" };
				java.util.Collections.addAll(talendToDBList_tDBInput_2, talendToDBArray_tDBInput_2);
				int nb_line_tDBInput_2 = 0;
				java.sql.Connection conn_tDBInput_2 = null;
				String driverClass_tDBInput_2 = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				java.lang.Class jdbcclazz_tDBInput_2 = java.lang.Class.forName(driverClass_tDBInput_2);
				String dbUser_tDBInput_2 = context.connection_local_Login;

				final String decryptedPassword_tDBInput_2 = context.connection_local_Password;

				String dbPwd_tDBInput_2 = decryptedPassword_tDBInput_2;

				String port_tDBInput_2 = context.connection_local_Port;
				String dbname_tDBInput_2 = context.connection_local_Database;
				String url_tDBInput_2 = "jdbc:sqlserver://" + context.connection_local_Server;
				if (!"".equals(port_tDBInput_2)) {
					url_tDBInput_2 += ":" + context.connection_local_Port;
				}
				if (!"".equals(dbname_tDBInput_2)) {
					url_tDBInput_2 += ";databaseName=" + context.connection_local_Database;
				}
				url_tDBInput_2 += ";appName=" + projectName + ";" + context.connection_local_AdditionalParams;
				String dbschema_tDBInput_2 = context.connection_local_Schema;

				log.debug("tDBInput_2 - Driver ClassName: " + driverClass_tDBInput_2 + ".");

				log.debug("tDBInput_2 - Connection attempt to '" + url_tDBInput_2 + "' with the username '"
						+ dbUser_tDBInput_2 + "'.");

				conn_tDBInput_2 = java.sql.DriverManager.getConnection(url_tDBInput_2, dbUser_tDBInput_2,
						dbPwd_tDBInput_2);
				log.debug("tDBInput_2 - Connection to '" + url_tDBInput_2 + "' has succeeded.");

				java.sql.Statement stmt_tDBInput_2 = conn_tDBInput_2.createStatement();

				String dbquery_tDBInput_2 = "SELECT \"" + context.connection_local_Schema
						+ "\".SalesOrderHeader.SalesOrderID,\n		\"" + context.connection_local_Schema
						+ "\".SalesOrderHeader.RevisionNumber,\n		\"" + context.connection_local_Schema
						+ "\".SalesOrderHeader.OrderDate,\n		\"" + context.connection_local_Schema
						+ "\".SalesOrderHeader.DueDate,\n		\"" + context.connection_local_Schema
						+ "\".SalesOrderHeader.ShipDate,\n		\"" + context.connection_local_Schema
						+ "\".SalesOrderHeader.Status,\n		\"" + context.connection_local_Schema
						+ "\".SalesOrderHeader.OnlineOrderFlag,\n		\"" + context.connection_local_Schema
						+ "\".SalesOrderHeader.SalesOrderNumber,\n		\"" + context.connection_local_Schema
						+ "\".SalesOrderHeader.PurchaseOrderNumber,\n		\"" + context.connection_local_Schema
						+ "\".SalesOrderHeader.AccountNumber,\n		\"" + context.connection_local_Schema
						+ "\".SalesOrderHeader.CustomerID,\n		\"" + context.connection_local_Schema
						+ "\".SalesOrderHeader.SalesPersonID,\n		\"" + context.connection_local_Schema
						+ "\".SalesOrderHeader.TerritoryID,\n		\"" + context.connection_local_Schema
						+ "\".SalesOrderHeader.BillToAddressID,\n		\"" + context.connection_local_Schema
						+ "\".SalesOrderHeader.ShipToAddressID,\n		\"" + context.connection_local_Schema
						+ "\".SalesOrderHeader.ShipMethodID,\n		\"" + context.connection_local_Schema
						+ "\".SalesOrderHeader.CreditCardID,\n		\"" + context.connection_local_Schema
						+ "\".SalesOrderHeader.CreditCardApprovalCode,\n		\"" + context.connection_local_Schema
						+ "\".SalesOrderHeader.CurrencyRateID,\n		\"" + context.connection_local_Schema
						+ "\".SalesOrderHeader.SubTotal,\n		\"" + context.connection_local_Schema
						+ "\".SalesOrderHeader.TaxAmt,\n		\"" + context.connection_local_Schema
						+ "\".SalesOrderHeader.Freight,\n		\"" + context.connection_local_Schema
						+ "\".SalesOrderHeader.TotalDue,\n		\"" + context.connection_local_Schema
						+ "\".SalesOrderHeader.Comment,\n		\"" + context.connection_local_Schema
						+ "\".SalesOrderHeader.rowguid,\n		\"" + context.connection_local_Schema
						+ "\".SalesOrderHeader.ModifiedDate" + "\nFROM	\"" + context.connection_local_Schema
						+ "\".SalesOrderHeader";

				log.debug("tDBInput_2 - Executing the query: '" + dbquery_tDBInput_2 + "'.");

				globalMap.put("tDBInput_2_QUERY", dbquery_tDBInput_2);

				java.sql.ResultSet rs_tDBInput_2 = null;

				try {
					rs_tDBInput_2 = stmt_tDBInput_2.executeQuery(dbquery_tDBInput_2);
					java.sql.ResultSetMetaData rsmd_tDBInput_2 = rs_tDBInput_2.getMetaData();
					int colQtyInRs_tDBInput_2 = rsmd_tDBInput_2.getColumnCount();

					String tmpContent_tDBInput_2 = null;

					log.debug("tDBInput_2 - Retrieving records from the database.");

					while (rs_tDBInput_2.next()) {
						nb_line_tDBInput_2++;

						if (colQtyInRs_tDBInput_2 < 1) {
							row1.SalesOrderID = 0;
						} else {

							row1.SalesOrderID = rs_tDBInput_2.getInt(1);
							if (rs_tDBInput_2.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_2 < 2) {
							row1.RevisionNumber = 0;
						} else {

							row1.RevisionNumber = rs_tDBInput_2.getShort(2);
							if (rs_tDBInput_2.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_2 < 3) {
							row1.OrderDate = null;
						} else {

							row1.OrderDate = mssqlGTU_tDBInput_2.getDate(rsmd_tDBInput_2, rs_tDBInput_2, 3);

						}
						if (colQtyInRs_tDBInput_2 < 4) {
							row1.DueDate = null;
						} else {

							row1.DueDate = mssqlGTU_tDBInput_2.getDate(rsmd_tDBInput_2, rs_tDBInput_2, 4);

						}
						if (colQtyInRs_tDBInput_2 < 5) {
							row1.ShipDate = null;
						} else {

							row1.ShipDate = mssqlGTU_tDBInput_2.getDate(rsmd_tDBInput_2, rs_tDBInput_2, 5);

						}
						if (colQtyInRs_tDBInput_2 < 6) {
							row1.Status = 0;
						} else {

							row1.Status = rs_tDBInput_2.getShort(6);
							if (rs_tDBInput_2.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_2 < 7) {
							row1.OnlineOrderFlag = false;
						} else {

							row1.OnlineOrderFlag = rs_tDBInput_2.getBoolean(7);
							if (rs_tDBInput_2.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_2 < 8) {
							row1.SalesOrderNumber = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(8);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(8).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.SalesOrderNumber = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									row1.SalesOrderNumber = tmpContent_tDBInput_2;
								}
							} else {
								row1.SalesOrderNumber = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 9) {
							row1.PurchaseOrderNumber = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(9);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(9).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.PurchaseOrderNumber = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									row1.PurchaseOrderNumber = tmpContent_tDBInput_2;
								}
							} else {
								row1.PurchaseOrderNumber = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 10) {
							row1.AccountNumber = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(10);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(10).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.AccountNumber = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									row1.AccountNumber = tmpContent_tDBInput_2;
								}
							} else {
								row1.AccountNumber = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 11) {
							row1.CustomerID = 0;
						} else {

							row1.CustomerID = rs_tDBInput_2.getInt(11);
							if (rs_tDBInput_2.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_2 < 12) {
							row1.SalesPersonID = null;
						} else {

							row1.SalesPersonID = rs_tDBInput_2.getInt(12);
							if (rs_tDBInput_2.wasNull()) {
								row1.SalesPersonID = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 13) {
							row1.TerritoryID = null;
						} else {

							row1.TerritoryID = rs_tDBInput_2.getInt(13);
							if (rs_tDBInput_2.wasNull()) {
								row1.TerritoryID = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 14) {
							row1.BillToAddressID = 0;
						} else {

							row1.BillToAddressID = rs_tDBInput_2.getInt(14);
							if (rs_tDBInput_2.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_2 < 15) {
							row1.ShipToAddressID = 0;
						} else {

							row1.ShipToAddressID = rs_tDBInput_2.getInt(15);
							if (rs_tDBInput_2.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_2 < 16) {
							row1.ShipMethodID = 0;
						} else {

							row1.ShipMethodID = rs_tDBInput_2.getInt(16);
							if (rs_tDBInput_2.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_2 < 17) {
							row1.CreditCardID = null;
						} else {

							row1.CreditCardID = rs_tDBInput_2.getInt(17);
							if (rs_tDBInput_2.wasNull()) {
								row1.CreditCardID = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 18) {
							row1.CreditCardApprovalCode = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(18);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(18).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.CreditCardApprovalCode = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									row1.CreditCardApprovalCode = tmpContent_tDBInput_2;
								}
							} else {
								row1.CreditCardApprovalCode = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 19) {
							row1.CurrencyRateID = null;
						} else {

							row1.CurrencyRateID = rs_tDBInput_2.getInt(19);
							if (rs_tDBInput_2.wasNull()) {
								row1.CurrencyRateID = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 20) {
							row1.SubTotal = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(20);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(20).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.SubTotal = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									row1.SubTotal = tmpContent_tDBInput_2;
								}
							} else {
								row1.SubTotal = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 21) {
							row1.TaxAmt = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(21);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(21).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.TaxAmt = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									row1.TaxAmt = tmpContent_tDBInput_2;
								}
							} else {
								row1.TaxAmt = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 22) {
							row1.Freight = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(22);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(22).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Freight = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									row1.Freight = tmpContent_tDBInput_2;
								}
							} else {
								row1.Freight = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 23) {
							row1.TotalDue = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(23);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(23).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.TotalDue = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									row1.TotalDue = tmpContent_tDBInput_2;
								}
							} else {
								row1.TotalDue = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 24) {
							row1.Comment = null;
						} else {

							tmpContent_tDBInput_2 = rs_tDBInput_2.getString(24);
							if (tmpContent_tDBInput_2 != null) {
								if (talendToDBList_tDBInput_2.contains(
										rsmd_tDBInput_2.getColumnTypeName(24).toUpperCase(java.util.Locale.ENGLISH))) {
									row1.Comment = FormatterUtils.formatUnwithE(tmpContent_tDBInput_2);
								} else {
									row1.Comment = tmpContent_tDBInput_2;
								}
							} else {
								row1.Comment = null;
							}
						}
						if (colQtyInRs_tDBInput_2 < 25) {
							row1.rowguid = null;
						} else {

							row1.rowguid = rs_tDBInput_2.getObject(25);
							if (rs_tDBInput_2.wasNull()) {
								throw new RuntimeException("Null value in non-Nullable column");
							}
						}
						if (colQtyInRs_tDBInput_2 < 26) {
							row1.ModifiedDate = null;
						} else {

							row1.ModifiedDate = mssqlGTU_tDBInput_2.getDate(rsmd_tDBInput_2, rs_tDBInput_2, 26);

						}

						log.debug("tDBInput_2 - Retrieving the record " + nb_line_tDBInput_2 + ".");

						/**
						 * [tDBInput_2 begin ] stop
						 */

						/**
						 * [tDBInput_2 main ] start
						 */

						s(currentComponent = "tDBInput_2");

						cLabel = "\"SalesOrderHeader\"";

						tos_count_tDBInput_2++;

						/**
						 * [tDBInput_2 main ] stop
						 */

						/**
						 * [tDBInput_2 process_data_begin ] start
						 */

						s(currentComponent = "tDBInput_2");

						cLabel = "\"SalesOrderHeader\"";

						/**
						 * [tDBInput_2 process_data_begin ] stop
						 */

						/**
						 * [tDBOutput_1 main ] start
						 */

						s(currentComponent = "tDBOutput_1");

						if (runStat.update(execStat, enableLogStash, iterateId, 1, 1

								, "row1", "tDBInput_2", "\"SalesOrderHeader\"", "tMSSqlInput", "tDBOutput_1",
								"tDBOutput_1", "tMSSqlOutput"

						)) {
							talendJobLogProcess(globalMap);
						}

						if (log.isTraceEnabled()) {
							log.trace("row1 - " + (row1 == null ? "" : row1.toLogString()));
						}

						whetherReject_tDBOutput_1 = false;
						pstmt_tDBOutput_1.setInt(1, row1.SalesOrderID);

						pstmt_tDBOutput_1.setShort(2, row1.RevisionNumber);

						if (row1.OrderDate != null) {
							pstmt_tDBOutput_1.setTimestamp(3, new java.sql.Timestamp(row1.OrderDate.getTime()));
						} else {
							pstmt_tDBOutput_1.setNull(3, java.sql.Types.TIMESTAMP);
						}

						if (row1.DueDate != null) {
							pstmt_tDBOutput_1.setTimestamp(4, new java.sql.Timestamp(row1.DueDate.getTime()));
						} else {
							pstmt_tDBOutput_1.setNull(4, java.sql.Types.TIMESTAMP);
						}

						if (row1.ShipDate != null) {
							pstmt_tDBOutput_1.setTimestamp(5, new java.sql.Timestamp(row1.ShipDate.getTime()));
						} else {
							pstmt_tDBOutput_1.setNull(5, java.sql.Types.TIMESTAMP);
						}

						pstmt_tDBOutput_1.setShort(6, row1.Status);

						pstmt_tDBOutput_1.setBoolean(7, row1.OnlineOrderFlag);

						if (row1.SalesOrderNumber == null) {
							pstmt_tDBOutput_1.setNull(8, java.sql.Types.VARCHAR);
						} else {
							String driver = null;
							driver = "MSSQL_PROP";
							if ("MSSQL_PROP".equals(driver)) {
								pstmt_tDBOutput_1.setNString(8, row1.SalesOrderNumber);
							} else {
								pstmt_tDBOutput_1.setString(8, row1.SalesOrderNumber);
							}
						}

						if (row1.PurchaseOrderNumber == null) {
							pstmt_tDBOutput_1.setNull(9, java.sql.Types.VARCHAR);
						} else {
							String driver = null;
							driver = "MSSQL_PROP";
							if ("MSSQL_PROP".equals(driver)) {
								pstmt_tDBOutput_1.setNString(9, row1.PurchaseOrderNumber);
							} else {
								pstmt_tDBOutput_1.setString(9, row1.PurchaseOrderNumber);
							}
						}

						if (row1.AccountNumber == null) {
							pstmt_tDBOutput_1.setNull(10, java.sql.Types.VARCHAR);
						} else {
							String driver = null;
							driver = "MSSQL_PROP";
							if ("MSSQL_PROP".equals(driver)) {
								pstmt_tDBOutput_1.setNString(10, row1.AccountNumber);
							} else {
								pstmt_tDBOutput_1.setString(10, row1.AccountNumber);
							}
						}

						pstmt_tDBOutput_1.setInt(11, row1.CustomerID);

						if (row1.SalesPersonID == null) {
							pstmt_tDBOutput_1.setNull(12, java.sql.Types.INTEGER);
						} else {
							pstmt_tDBOutput_1.setInt(12, row1.SalesPersonID);
						}

						if (row1.TerritoryID == null) {
							pstmt_tDBOutput_1.setNull(13, java.sql.Types.INTEGER);
						} else {
							pstmt_tDBOutput_1.setInt(13, row1.TerritoryID);
						}

						pstmt_tDBOutput_1.setInt(14, row1.BillToAddressID);

						pstmt_tDBOutput_1.setInt(15, row1.ShipToAddressID);

						pstmt_tDBOutput_1.setInt(16, row1.ShipMethodID);

						if (row1.CreditCardID == null) {
							pstmt_tDBOutput_1.setNull(17, java.sql.Types.INTEGER);
						} else {
							pstmt_tDBOutput_1.setInt(17, row1.CreditCardID);
						}

						if (row1.CreditCardApprovalCode == null) {
							pstmt_tDBOutput_1.setNull(18, java.sql.Types.VARCHAR);
						} else {
							pstmt_tDBOutput_1.setString(18, row1.CreditCardApprovalCode);
						}

						if (row1.CurrencyRateID == null) {
							pstmt_tDBOutput_1.setNull(19, java.sql.Types.INTEGER);
						} else {
							pstmt_tDBOutput_1.setInt(19, row1.CurrencyRateID);
						}

						if (row1.SubTotal == null) {
							pstmt_tDBOutput_1.setNull(20, java.sql.Types.VARCHAR);
						} else {
							pstmt_tDBOutput_1.setString(20, row1.SubTotal);
						}

						if (row1.TaxAmt == null) {
							pstmt_tDBOutput_1.setNull(21, java.sql.Types.VARCHAR);
						} else {
							pstmt_tDBOutput_1.setString(21, row1.TaxAmt);
						}

						if (row1.Freight == null) {
							pstmt_tDBOutput_1.setNull(22, java.sql.Types.VARCHAR);
						} else {
							pstmt_tDBOutput_1.setString(22, row1.Freight);
						}

						if (row1.TotalDue == null) {
							pstmt_tDBOutput_1.setNull(23, java.sql.Types.VARCHAR);
						} else {
							pstmt_tDBOutput_1.setString(23, row1.TotalDue);
						}

						if (row1.Comment == null) {
							pstmt_tDBOutput_1.setNull(24, java.sql.Types.VARCHAR);
						} else {
							String driver = null;
							driver = "MSSQL_PROP";
							if ("MSSQL_PROP".equals(driver)) {
								pstmt_tDBOutput_1.setNString(24, row1.Comment);
							} else {
								pstmt_tDBOutput_1.setString(24, row1.Comment);
							}
						}

						if (row1.rowguid == null) {
							pstmt_tDBOutput_1.setNull(25, java.sql.Types.OTHER);
						} else {
							pstmt_tDBOutput_1.setObject(25, row1.rowguid);
						}

						if (row1.ModifiedDate != null) {
							pstmt_tDBOutput_1.setTimestamp(26, new java.sql.Timestamp(row1.ModifiedDate.getTime()));
						} else {
							pstmt_tDBOutput_1.setNull(26, java.sql.Types.TIMESTAMP);
						}

						pstmt_tDBOutput_1.addBatch();
						nb_line_tDBOutput_1++;

						if (log.isDebugEnabled())
							log.debug("tDBOutput_1 - " + ("Adding the record ") + (nb_line_tDBOutput_1) + (" to the ")
									+ ("INSERT") + (" batch."));
						batchSizeCounter_tDBOutput_1++;

						////////// batch execute by batch size///////
						class LimitBytesHelper_tDBOutput_1 {
							public int limitBytePart1(int counter, java.sql.PreparedStatement pstmt_tDBOutput_1)
									throws Exception {
								try {

									if (log.isDebugEnabled())
										log.debug("tDBOutput_1 - " + ("Executing the ") + ("INSERT") + (" batch."));
									for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
										if (countEach_tDBOutput_1 == -2 || countEach_tDBOutput_1 == -3) {
											break;
										}
										counter += countEach_tDBOutput_1;
									}

									if (log.isDebugEnabled())
										log.debug("tDBOutput_1 - " + ("The ") + ("INSERT")
												+ (" batch execution has succeeded."));
								} catch (java.sql.BatchUpdateException e) {
									globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());

									int countSum_tDBOutput_1 = 0;
									for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
										counter += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
									}

									log.error("tDBOutput_1 - " + (e.getMessage()));
									System.err.println(e.getMessage());

								}
								return counter;
							}

							public int limitBytePart2(int counter, java.sql.PreparedStatement pstmt_tDBOutput_1)
									throws Exception {
								try {

									if (log.isDebugEnabled())
										log.debug("tDBOutput_1 - " + ("Executing the ") + ("INSERT") + (" batch."));
									for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
										if (countEach_tDBOutput_1 == -2 || countEach_tDBOutput_1 == -3) {
											break;
										}
										counter += countEach_tDBOutput_1;
									}

									if (log.isDebugEnabled())
										log.debug("tDBOutput_1 - " + ("The ") + ("INSERT")
												+ (" batch execution has succeeded."));
								} catch (java.sql.BatchUpdateException e) {
									globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());

									for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
										counter += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
									}

									log.error("tDBOutput_1 - " + (e.getMessage()));
									System.err.println(e.getMessage());

								}
								return counter;
							}
						}
						if ((batchSize_tDBOutput_1 > 0) && (batchSize_tDBOutput_1 <= batchSizeCounter_tDBOutput_1)) {

							insertedCount_tDBOutput_1 = new LimitBytesHelper_tDBOutput_1()
									.limitBytePart1(insertedCount_tDBOutput_1, pstmt_tDBOutput_1);
							rowsToCommitCount_tDBOutput_1 = insertedCount_tDBOutput_1;

							batchSizeCounter_tDBOutput_1 = 0;
						}

						//////////// commit every////////////

						commitCounter_tDBOutput_1++;
						if (commitEvery_tDBOutput_1 <= commitCounter_tDBOutput_1) {
							if ((batchSize_tDBOutput_1 > 0) && (batchSizeCounter_tDBOutput_1 > 0)) {

								insertedCount_tDBOutput_1 = new LimitBytesHelper_tDBOutput_1()
										.limitBytePart1(insertedCount_tDBOutput_1, pstmt_tDBOutput_1);

								batchSizeCounter_tDBOutput_1 = 0;
							}
							if (rowsToCommitCount_tDBOutput_1 != 0) {

								if (log.isDebugEnabled())
									log.debug("tDBOutput_1 - " + ("Connection starting to commit ")
											+ (rowsToCommitCount_tDBOutput_1) + (" record(s)."));
							}
							conn_tDBOutput_1.commit();
							if (rowsToCommitCount_tDBOutput_1 != 0) {

								if (log.isDebugEnabled())
									log.debug("tDBOutput_1 - " + ("Connection commit has succeeded."));
								rowsToCommitCount_tDBOutput_1 = 0;
							}
							commitCounter_tDBOutput_1 = 0;
						}

						tos_count_tDBOutput_1++;

						/**
						 * [tDBOutput_1 main ] stop
						 */

						/**
						 * [tDBOutput_1 process_data_begin ] start
						 */

						s(currentComponent = "tDBOutput_1");

						/**
						 * [tDBOutput_1 process_data_begin ] stop
						 */

						/**
						 * [tDBOutput_1 process_data_end ] start
						 */

						s(currentComponent = "tDBOutput_1");

						/**
						 * [tDBOutput_1 process_data_end ] stop
						 */

						/**
						 * [tDBInput_2 process_data_end ] start
						 */

						s(currentComponent = "tDBInput_2");

						cLabel = "\"SalesOrderHeader\"";

						/**
						 * [tDBInput_2 process_data_end ] stop
						 */

						/**
						 * [tDBInput_2 end ] start
						 */

						s(currentComponent = "tDBInput_2");

						cLabel = "\"SalesOrderHeader\"";

					}
				} finally {
					if (rs_tDBInput_2 != null) {
						rs_tDBInput_2.close();
					}
					if (stmt_tDBInput_2 != null) {
						stmt_tDBInput_2.close();
					}
					if (conn_tDBInput_2 != null && !conn_tDBInput_2.isClosed()) {

						log.debug("tDBInput_2 - Closing the connection to the database.");

						conn_tDBInput_2.close();

						if ("com.mysql.cj.jdbc.Driver".equals((String) globalMap.get("driverClass_"))
								&& routines.system.BundleUtils.inOSGi()) {
							Class.forName("com.mysql.cj.jdbc.AbandonedConnectionCleanupThread")
									.getMethod("checkedShutdown").invoke(null, (Object[]) null);
						}

						log.debug("tDBInput_2 - Connection to the database closed.");

					}
				}
				globalMap.put("tDBInput_2_NB_LINE", nb_line_tDBInput_2);
				log.debug("tDBInput_2 - Retrieved records count: " + nb_line_tDBInput_2 + " .");

				if (log.isDebugEnabled())
					log.debug("tDBInput_2 - " + ("Done."));

				ok_Hash.put("tDBInput_2", true);
				end_Hash.put("tDBInput_2", System.currentTimeMillis());

				/**
				 * [tDBInput_2 end ] stop
				 */

				/**
				 * [tDBOutput_1 end ] start
				 */

				s(currentComponent = "tDBOutput_1");

				try {
					int countSum_tDBOutput_1 = 0;
					if (pstmt_tDBOutput_1 != null && batchSizeCounter_tDBOutput_1 > 0) {

						if (log.isDebugEnabled())
							log.debug("tDBOutput_1 - " + ("Executing the ") + ("INSERT") + (" batch."));
						for (int countEach_tDBOutput_1 : pstmt_tDBOutput_1.executeBatch()) {
							if (countEach_tDBOutput_1 == -2 || countEach_tDBOutput_1 == -3) {
								break;
							}
							countSum_tDBOutput_1 += countEach_tDBOutput_1;
						}
						rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;

						if (log.isDebugEnabled())
							log.debug("tDBOutput_1 - " + ("The ") + ("INSERT") + (" batch execution has succeeded."));
					}

					insertedCount_tDBOutput_1 += countSum_tDBOutput_1;

				} catch (java.sql.BatchUpdateException e) {
					globalMap.put("tDBOutput_1_ERROR_MESSAGE", e.getMessage());

					int countSum_tDBOutput_1 = 0;
					for (int countEach_tDBOutput_1 : e.getUpdateCounts()) {
						countSum_tDBOutput_1 += (countEach_tDBOutput_1 < 0 ? 0 : countEach_tDBOutput_1);
					}
					rowsToCommitCount_tDBOutput_1 += countSum_tDBOutput_1;

					insertedCount_tDBOutput_1 += countSum_tDBOutput_1;

					log.error("tDBOutput_1 - " + (e.getMessage()));
					System.err.println(e.getMessage());

				}
				if (pstmt_tDBOutput_1 != null) {

					pstmt_tDBOutput_1.close();
					resourceMap.remove("pstmt_tDBOutput_1");

				}
				resourceMap.put("statementClosed_tDBOutput_1", true);
				if (rowsToCommitCount_tDBOutput_1 != 0) {

					if (log.isDebugEnabled())
						log.debug("tDBOutput_1 - " + ("Connection starting to commit ")
								+ (rowsToCommitCount_tDBOutput_1) + (" record(s)."));
				}
				conn_tDBOutput_1.commit();
				if (rowsToCommitCount_tDBOutput_1 != 0) {

					if (log.isDebugEnabled())
						log.debug("tDBOutput_1 - " + ("Connection commit has succeeded."));
					rowsToCommitCount_tDBOutput_1 = 0;
				}
				commitCounter_tDBOutput_1 = 0;
				if (log.isDebugEnabled())
					log.debug("tDBOutput_1 - " + ("Closing the connection to the database."));
				conn_tDBOutput_1.close();
				if (log.isDebugEnabled())
					log.debug("tDBOutput_1 - " + ("Connection to the database has closed."));
				resourceMap.put("finish_tDBOutput_1", true);

				nb_line_deleted_tDBOutput_1 = nb_line_deleted_tDBOutput_1 + deletedCount_tDBOutput_1;
				nb_line_update_tDBOutput_1 = nb_line_update_tDBOutput_1 + updatedCount_tDBOutput_1;
				nb_line_inserted_tDBOutput_1 = nb_line_inserted_tDBOutput_1 + insertedCount_tDBOutput_1;
				nb_line_rejected_tDBOutput_1 = nb_line_rejected_tDBOutput_1 + rejectedCount_tDBOutput_1;

				globalMap.put("tDBOutput_1_NB_LINE", nb_line_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_UPDATED", nb_line_update_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_INSERTED", nb_line_inserted_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_DELETED", nb_line_deleted_tDBOutput_1);
				globalMap.put("tDBOutput_1_NB_LINE_REJECTED", nb_line_rejected_tDBOutput_1);

				if (log.isDebugEnabled())
					log.debug("tDBOutput_1 - " + ("Has ") + ("inserted") + (" ") + (nb_line_inserted_tDBOutput_1)
							+ (" record(s)."));

				if (runStat.updateStatAndLog(execStat, enableLogStash, resourceMap, iterateId, "row1", 2, 0,
						"tDBInput_2", "\"SalesOrderHeader\"", "tMSSqlInput", "tDBOutput_1", "tDBOutput_1",
						"tMSSqlOutput", "output")) {
					talendJobLogProcess(globalMap);
				}

				if (log.isDebugEnabled())
					log.debug("tDBOutput_1 - " + ("Done."));

				ok_Hash.put("tDBOutput_1", true);
				end_Hash.put("tDBOutput_1", System.currentTimeMillis());

				/**
				 * [tDBOutput_1 end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [tDBInput_2 finally ] start
				 */

				s(currentComponent = "tDBInput_2");

				cLabel = "\"SalesOrderHeader\"";

				/**
				 * [tDBInput_2 finally ] stop
				 */

				/**
				 * [tDBOutput_1 finally ] start
				 */

				s(currentComponent = "tDBOutput_1");

				try {
					if (resourceMap.get("statementClosed_tDBOutput_1") == null) {
						java.sql.PreparedStatement pstmtToClose_tDBOutput_1 = null;
						if ((pstmtToClose_tDBOutput_1 = (java.sql.PreparedStatement) resourceMap
								.remove("pstmt_tDBOutput_1")) != null) {
							pstmtToClose_tDBOutput_1.close();
						}
					}
				} finally {
					if (resourceMap.get("finish_tDBOutput_1") == null) {
						java.sql.Connection ctn_tDBOutput_1 = null;
						if ((ctn_tDBOutput_1 = (java.sql.Connection) resourceMap.get("conn_tDBOutput_1")) != null) {
							try {
								if (log.isDebugEnabled())
									log.debug("tDBOutput_1 - " + ("Closing the connection to the database."));
								ctn_tDBOutput_1.close();
								if (log.isDebugEnabled())
									log.debug("tDBOutput_1 - " + ("Connection to the database has closed."));
							} catch (java.sql.SQLException sqlEx_tDBOutput_1) {
								String errorMessage_tDBOutput_1 = "failed to close the connection in tDBOutput_1 :"
										+ sqlEx_tDBOutput_1.getMessage();
								log.error("tDBOutput_1 - " + (errorMessage_tDBOutput_1));
								System.err.println(errorMessage_tDBOutput_1);
							}
						}
					}
				}

				/**
				 * [tDBOutput_1 finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("tDBInput_2_SUBPROCESS_STATE", 1);
	}

	public void talendJobLogProcess(final java.util.Map<String, Object> globalMap) throws TalendException {
		globalMap.put("talendJobLog_SUBPROCESS_STATE", 0);

		final boolean execStat = this.execStat;

		String iterateId = "";

		String currentComponent = "";
		s("none");
		String cLabel = null;
		java.util.Map<String, Object> resourceMap = new java.util.HashMap<String, Object>();

		try {
			// TDI-39566 avoid throwing an useless Exception
			boolean resumeIt = true;
			if (globalResumeTicket == false && resumeEntryMethodName != null) {
				String currentMethodName = new java.lang.Exception().getStackTrace()[0].getMethodName();
				resumeIt = resumeEntryMethodName.equals(currentMethodName);
			}
			if (resumeIt || globalResumeTicket) { // start the resume
				globalResumeTicket = true;

				/**
				 * [talendJobLog begin ] start
				 */

				sh("talendJobLog");

				s(currentComponent = "talendJobLog");

				int tos_count_talendJobLog = 0;

				for (JobStructureCatcherUtils.JobStructureCatcherMessage jcm : talendJobLog.getMessages()) {
					org.talend.job.audit.JobContextBuilder builder_talendJobLog = org.talend.job.audit.JobContextBuilder
							.create().jobName(jcm.job_name).jobId(jcm.job_id).jobVersion(jcm.job_version)
							.custom("process_id", jcm.pid).custom("thread_id", jcm.tid).custom("pid", pid)
							.custom("father_pid", fatherPid).custom("root_pid", rootPid);
					org.talend.logging.audit.Context log_context_talendJobLog = null;

					if (jcm.log_type == JobStructureCatcherUtils.LogType.PERFORMANCE) {
						long timeMS = jcm.end_time - jcm.start_time;
						String duration = String.valueOf(timeMS);

						log_context_talendJobLog = builder_talendJobLog.sourceId(jcm.sourceId)
								.sourceLabel(jcm.sourceLabel).sourceConnectorType(jcm.sourceComponentName)
								.targetId(jcm.targetId).targetLabel(jcm.targetLabel)
								.targetConnectorType(jcm.targetComponentName).connectionName(jcm.current_connector)
								.rows(jcm.row_count).duration(duration).build();
						auditLogger_talendJobLog.flowExecution(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.JOBSTART) {
						log_context_talendJobLog = builder_talendJobLog.timestamp(jcm.moment).build();
						auditLogger_talendJobLog.jobstart(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.JOBEND) {
						long timeMS = jcm.end_time - jcm.start_time;
						String duration = String.valueOf(timeMS);

						log_context_talendJobLog = builder_talendJobLog.timestamp(jcm.moment).duration(duration)
								.status(jcm.status).build();
						auditLogger_talendJobLog.jobstop(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.RUNCOMPONENT) {
						log_context_talendJobLog = builder_talendJobLog.timestamp(jcm.moment)
								.connectorType(jcm.component_name).connectorId(jcm.component_id)
								.connectorLabel(jcm.component_label).build();
						auditLogger_talendJobLog.runcomponent(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.FLOWINPUT) {// log current component
																							// input line
						long timeMS = jcm.end_time - jcm.start_time;
						String duration = String.valueOf(timeMS);

						log_context_talendJobLog = builder_talendJobLog.connectorType(jcm.component_name)
								.connectorId(jcm.component_id).connectorLabel(jcm.component_label)
								.connectionName(jcm.current_connector).connectionType(jcm.current_connector_type)
								.rows(jcm.total_row_number).duration(duration).build();
						auditLogger_talendJobLog.flowInput(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.FLOWOUTPUT) {// log current component
																								// output/reject line
						long timeMS = jcm.end_time - jcm.start_time;
						String duration = String.valueOf(timeMS);

						log_context_talendJobLog = builder_talendJobLog.connectorType(jcm.component_name)
								.connectorId(jcm.component_id).connectorLabel(jcm.component_label)
								.connectionName(jcm.current_connector).connectionType(jcm.current_connector_type)
								.rows(jcm.total_row_number).duration(duration).build();
						auditLogger_talendJobLog.flowOutput(log_context_talendJobLog);
					} else if (jcm.log_type == JobStructureCatcherUtils.LogType.JOBERROR) {
						java.lang.Exception e_talendJobLog = jcm.exception;
						if (e_talendJobLog != null) {
							try (java.io.StringWriter sw_talendJobLog = new java.io.StringWriter();
									java.io.PrintWriter pw_talendJobLog = new java.io.PrintWriter(sw_talendJobLog)) {
								e_talendJobLog.printStackTrace(pw_talendJobLog);
								builder_talendJobLog.custom("stacktrace", sw_talendJobLog.getBuffer().substring(0,
										java.lang.Math.min(sw_talendJobLog.getBuffer().length(), 512)));
							}
						}

						if (jcm.extra_info != null) {
							builder_talendJobLog.connectorId(jcm.component_id).custom("extra_info", jcm.extra_info);
						}

						log_context_talendJobLog = builder_talendJobLog
								.connectorType(jcm.component_id.substring(0, jcm.component_id.lastIndexOf('_')))
								.connectorId(jcm.component_id)
								.connectorLabel(jcm.component_label == null ? jcm.component_id : jcm.component_label)
								.build();

						auditLogger_talendJobLog.exception(log_context_talendJobLog);
					}

				}

				/**
				 * [talendJobLog begin ] stop
				 */

				/**
				 * [talendJobLog main ] start
				 */

				s(currentComponent = "talendJobLog");

				tos_count_talendJobLog++;

				/**
				 * [talendJobLog main ] stop
				 */

				/**
				 * [talendJobLog process_data_begin ] start
				 */

				s(currentComponent = "talendJobLog");

				/**
				 * [talendJobLog process_data_begin ] stop
				 */

				/**
				 * [talendJobLog process_data_end ] start
				 */

				s(currentComponent = "talendJobLog");

				/**
				 * [talendJobLog process_data_end ] stop
				 */

				/**
				 * [talendJobLog end ] start
				 */

				s(currentComponent = "talendJobLog");

				ok_Hash.put("talendJobLog", true);
				end_Hash.put("talendJobLog", System.currentTimeMillis());

				/**
				 * [talendJobLog end ] stop
				 */

			} // end the resume

		} catch (java.lang.Exception e) {

			if (!(e instanceof TalendException)) {
				log.fatal(currentComponent + " " + e.getMessage(), e);
			}

			TalendException te = new TalendException(e, currentComponent, cLabel, globalMap);

			throw te;
		} catch (java.lang.Error error) {

			runStat.stopThreadStat();

			throw error;
		} finally {

			try {

				/**
				 * [talendJobLog finally ] start
				 */

				s(currentComponent = "talendJobLog");

				/**
				 * [talendJobLog finally ] stop
				 */

			} catch (java.lang.Exception e) {
				// ignore
			} catch (java.lang.Error error) {
				// ignore
			}
			resourceMap = null;
		}

		globalMap.put("talendJobLog_SUBPROCESS_STATE", 1);
	}

	public String resuming_logs_dir_path = null;
	public String resuming_checkpoint_path = null;
	public String parent_part_launcher = null;
	private String resumeEntryMethodName = null;
	private boolean globalResumeTicket = false;

	public boolean watch = false;
	// portStats is null, it means don't execute the statistics
	public Integer portStats = null;
	public int portTraces = 4334;
	public String clientHost;
	public String defaultClientHost = "localhost";
	public String contextStr = "Default";
	public boolean isDefaultContext = true;
	public String pid = "0";
	public String rootPid = null;
	public String fatherPid = null;
	public String fatherNode = null;
	public long startTime = 0;
	public boolean isChildJob = false;
	public String log4jLevel = "";

	private boolean enableLogStash;

	private boolean execStat = true;

	private ThreadLocal<java.util.Map<String, String>> threadLocal = new ThreadLocal<java.util.Map<String, String>>() {
		protected java.util.Map<String, String> initialValue() {
			java.util.Map<String, String> threadRunResultMap = new java.util.HashMap<String, String>();
			threadRunResultMap.put("errorCode", null);
			threadRunResultMap.put("status", "");
			return threadRunResultMap;
		};
	};

	protected PropertiesWithType context_param = new PropertiesWithType();
	public java.util.Map<String, Object> parentContextMap = new java.util.HashMap<String, Object>();

	public String status = "";

	private final static java.util.Properties jobInfo = new java.util.Properties();
	private final static java.util.Map<String, String> mdcInfo = new java.util.HashMap<>();
	private final static java.util.concurrent.atomic.AtomicLong subJobPidCounter = new java.util.concurrent.atomic.AtomicLong();

	public static void main(String[] args) {
		final test testClass = new test();

		int exitCode = testClass.runJobInTOS(args);
		if (exitCode == 0) {
			log.info("TalendJob: 'test' - Done.");
		}

		System.exit(exitCode);
	}

	private void getjobInfo() {
		final String TEMPLATE_PATH = "src/main/templates/jobInfo_template.properties";
		final String BUILD_PATH = "../jobInfo.properties";
		final String path = this.getClass().getResource("").getPath();
		if (path.lastIndexOf("target") > 0) {
			final java.io.File templateFile = new java.io.File(
					path.substring(0, path.lastIndexOf("target")).concat(TEMPLATE_PATH));
			if (templateFile.exists()) {
				readJobInfo(templateFile);
				return;
			}
		}
		readJobInfo(new java.io.File(BUILD_PATH));
	}

	private void readJobInfo(java.io.File jobInfoFile) {

		if (jobInfoFile.exists()) {
			try (java.io.InputStream is = new java.io.FileInputStream(jobInfoFile)) {
				jobInfo.load(is);
			} catch (IOException e) {

				log.debug("Read jobInfo.properties file fail: " + e.getMessage());

			}
		}
		log.info(String.format("Project name: %s\tJob name: %s\tGIT Commit ID: %s\tTalend Version: %s", projectName,
				jobName, jobInfo.getProperty("gitCommitId"), "8.0.1.20250218_0945-patch"));

	}

	public String[][] runJob(String[] args) {

		int exitCode = runJobInTOS(args);
		String[][] bufferValue = new String[][] { { Integer.toString(exitCode) } };

		return bufferValue;
	}

	public boolean hastBufferOutputComponent() {
		boolean hastBufferOutput = false;

		return hastBufferOutput;
	}

	public int runJobInTOS(String[] args) {
		// reset status
		status = "";

		String lastStr = "";
		for (String arg : args) {
			if (arg.equalsIgnoreCase("--context_param")) {
				lastStr = arg;
			} else if (lastStr.equals("")) {
				evalParam(arg);
			} else {
				evalParam(lastStr + " " + arg);
				lastStr = "";
			}
		}

		final boolean enableCBP = false;
		boolean inOSGi = routines.system.BundleUtils.inOSGi();

		if (!inOSGi && isCBPClientPresent) {
			if (org.talend.metrics.CBPClient.getInstanceForCurrentVM() == null) {
				try {
					org.talend.metrics.CBPClient.startListenIfNotStarted(enableCBP, true);
				} catch (java.lang.Exception e) {
					errorCode = 1;
					status = "failure";
					e.printStackTrace();
					return 1;
				}
			}
		}

		enableLogStash = "true".equalsIgnoreCase(System.getProperty("audit.enabled"));

		if (!"".equals(log4jLevel)) {

			if ("trace".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.TRACE);
			} else if ("debug".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.DEBUG);
			} else if ("info".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.INFO);
			} else if ("warn".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.WARN);
			} else if ("error".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.ERROR);
			} else if ("fatal".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.FATAL);
			} else if ("off".equalsIgnoreCase(log4jLevel)) {
				org.apache.logging.log4j.core.config.Configurator.setLevel(log.getName(),
						org.apache.logging.log4j.Level.OFF);
			}
			org.apache.logging.log4j.core.config.Configurator
					.setLevel(org.apache.logging.log4j.LogManager.getRootLogger().getName(), log.getLevel());

		}

		getjobInfo();
		log.info("TalendJob: 'test' - Start.");

		java.util.Set<Object> jobInfoKeys = jobInfo.keySet();
		for (Object jobInfoKey : jobInfoKeys) {
			org.slf4j.MDC.put("_" + jobInfoKey.toString(), jobInfo.get(jobInfoKey).toString());
		}
		org.slf4j.MDC.put("_pid", pid);
		org.slf4j.MDC.put("_rootPid", rootPid);
		org.slf4j.MDC.put("_fatherPid", fatherPid);
		org.slf4j.MDC.put("_projectName", projectName);
		org.slf4j.MDC.put("_startTimestamp", java.time.ZonedDateTime.now(java.time.ZoneOffset.UTC)
				.format(java.time.format.DateTimeFormatter.ISO_INSTANT));
		org.slf4j.MDC.put("_jobRepositoryId", "_RPNKQLbKEfGIZvUIz3EKyQ");
		org.slf4j.MDC.put("_compiledAtTimestamp", "2026-09-23T07:56:49.153017900Z");

		java.lang.management.RuntimeMXBean mx = java.lang.management.ManagementFactory.getRuntimeMXBean();
		String[] mxNameTable = mx.getName().split("@"); //$NON-NLS-1$
		if (mxNameTable.length == 2) {
			org.slf4j.MDC.put("_systemPid", mxNameTable[0]);
		} else {
			org.slf4j.MDC.put("_systemPid", String.valueOf(java.lang.Thread.currentThread().getId()));
		}

		if (enableLogStash) {
			java.util.Properties properties_talendJobLog = new java.util.Properties();
			properties_talendJobLog.setProperty("root.logger", "audit");
			properties_talendJobLog.setProperty("encoding", "UTF-8");
			properties_talendJobLog.setProperty("application.name", "Talend Studio");
			properties_talendJobLog.setProperty("service.name", "Talend Studio Job");
			properties_talendJobLog.setProperty("instance.name", "Talend Studio Job Instance");
			properties_talendJobLog.setProperty("propagate.appender.exceptions", "none");
			properties_talendJobLog.setProperty("log.appender", "file");
			properties_talendJobLog.setProperty("appender.file.path", "audit.json");
			properties_talendJobLog.setProperty("appender.file.maxsize", "52428800");
			properties_talendJobLog.setProperty("appender.file.maxbackup", "20");
			properties_talendJobLog.setProperty("host", "false");

			System.getProperties().stringPropertyNames().stream().filter(it -> it.startsWith("audit.logger."))
					.forEach(key -> properties_talendJobLog.setProperty(key.substring("audit.logger.".length()),
							System.getProperty(key)));

			org.apache.logging.log4j.core.config.Configurator
					.setLevel(properties_talendJobLog.getProperty("root.logger"), org.apache.logging.log4j.Level.DEBUG);

			auditLogger_talendJobLog = org.talend.job.audit.JobEventAuditLoggerFactory
					.createJobAuditLogger(properties_talendJobLog);
		}

		if (clientHost == null) {
			clientHost = defaultClientHost;
		}

		if (pid == null || "0".equals(pid)) {
			pid = TalendString.getAsciiRandomString(6);
		}

		org.slf4j.MDC.put("_pid", pid);

		if (rootPid == null) {
			rootPid = pid;
		}

		org.slf4j.MDC.put("_rootPid", rootPid);

		if (fatherPid == null) {
			fatherPid = pid;
		} else {
			isChildJob = true;
		}
		org.slf4j.MDC.put("_fatherPid", fatherPid);

		if (portStats != null) {
			// portStats = -1; //for testing
			if (portStats < 0 || portStats > 65535) {
				// issue:10869, the portStats is invalid, so this client socket can't open
				System.err.println("The statistics socket port " + portStats + " is invalid.");
				execStat = false;
			}
		} else {
			execStat = false;
		}

		try {
			java.util.Dictionary<String, Object> jobProperties = null;
			if (inOSGi) {
				jobProperties = routines.system.BundleUtils.getJobProperties(jobName);

				if (jobProperties != null && jobProperties.get("context") != null) {
					contextStr = (String) jobProperties.get("context");
				}

				if (jobProperties != null && jobProperties.get("taskExecutionId") != null) {
					taskExecutionId = (String) jobProperties.get("taskExecutionId");
				}

				// extract ids from parent route
				if (null == taskExecutionId || taskExecutionId.isEmpty()) {
					for (String arg : args) {
						if (arg.startsWith("--context_param")
								&& (arg.contains("taskExecutionId") || arg.contains("jobExecutionId"))) {

							String keyValue = arg.replace("--context_param", "");
							String[] parts = keyValue.split("=");
							String[] cleanParts = java.util.Arrays.stream(parts).filter(s -> !s.isEmpty())
									.toArray(String[]::new);
							if (cleanParts.length == 2) {
								String key = cleanParts[0];
								String value = cleanParts[1];
								if ("taskExecutionId".equals(key.trim()) && null != value) {
									taskExecutionId = value.trim();
								} else if ("jobExecutionId".equals(key.trim()) && null != value) {
									jobExecutionId = value.trim();
								}
							}
						}
					}
				}
			}

			// first load default key-value pairs from application.properties
			if (isStandaloneMS) {
				context.putAll(this.getDefaultProperties());
			}
			// call job/subjob with an existing context, like: --context=production. if
			// without this parameter, there will use the default context instead.
			java.io.InputStream inContext = test.class.getClassLoader()
					.getResourceAsStream("talend/test_0_1/contexts/" + contextStr + ".properties");
			if (inContext == null) {
				inContext = test.class.getClassLoader()
						.getResourceAsStream("config/contexts/" + contextStr + ".properties");
			}
			if (inContext != null) {
				try {
					// defaultProps is in order to keep the original context value
					if (context != null && context.isEmpty()) {
						defaultProps.load(inContext);
						if (inOSGi && jobProperties != null) {
							java.util.Enumeration<String> keys = jobProperties.keys();
							while (keys.hasMoreElements()) {
								String propKey = keys.nextElement();
								if (defaultProps.containsKey(propKey)) {
									defaultProps.put(propKey, (String) jobProperties.get(propKey));
								}
							}
						}
						context = new ContextProperties(defaultProps);
					}
					if (isStandaloneMS) {
						// override context key-value pairs if provided using --context=contextName
						defaultProps.load(inContext);
						context.putAll(defaultProps);
					}
				} finally {
					inContext.close();
				}
			} else if (!isDefaultContext) {
				// print info and job continue to run, for case: context_param is not empty.
				System.err.println("Could not find the context " + contextStr);
			}
			// override key-value pairs if provided via --config.location=file1.file2 OR
			// --config.additional-location=file1,file2
			if (isStandaloneMS) {
				context.putAll(this.getAdditionalProperties());
			}

			// override key-value pairs if provide via command line like
			// --key1=value1,--key2=value2
			if (!context_param.isEmpty()) {
				context.putAll(context_param);
				// set types for params from parentJobs
				for (Object key : context_param.keySet()) {
					String context_key = key.toString();
					String context_type = context_param.getContextType(context_key);
					context.setContextType(context_key, context_type);

				}
			}
			class ContextProcessing {
				private void processContext_0() {
					context.setContextType("connection_local_Login", "id_String");
					if (context.getStringValue("connection_local_Login") == null) {
						context.connection_local_Login = null;
					} else {
						context.connection_local_Login = (String) context.getProperty("connection_local_Login");
					}
					context.setContextType("connection_local_Database", "id_String");
					if (context.getStringValue("connection_local_Database") == null) {
						context.connection_local_Database = null;
					} else {
						context.connection_local_Database = (String) context.getProperty("connection_local_Database");
					}
					context.setContextType("connection_local_Port", "id_String");
					if (context.getStringValue("connection_local_Port") == null) {
						context.connection_local_Port = null;
					} else {
						context.connection_local_Port = (String) context.getProperty("connection_local_Port");
					}
					context.setContextType("connection_local_Password", "id_Password");
					if (context.getStringValue("connection_local_Password") == null) {
						context.connection_local_Password = null;
					} else {
						String pwd_connection_local_Password_value = context.getProperty("connection_local_Password");
						context.connection_local_Password = null;
						if (pwd_connection_local_Password_value != null) {
							if (context_param.containsKey("connection_local_Password")) {// no need to decrypt if it
																							// come from program
																							// argument or parent job
																							// runtime
								context.connection_local_Password = pwd_connection_local_Password_value;
							} else if (!pwd_connection_local_Password_value.isEmpty()) {
								try {
									context.connection_local_Password = routines.system.PasswordEncryptUtil
											.decryptPassword(pwd_connection_local_Password_value);
									context.put("connection_local_Password", context.connection_local_Password);
								} catch (java.lang.RuntimeException e) {
									// do nothing
								}
							}
						}
					}
					context.setContextType("connection_local_AdditionalParams", "id_String");
					if (context.getStringValue("connection_local_AdditionalParams") == null) {
						context.connection_local_AdditionalParams = null;
					} else {
						context.connection_local_AdditionalParams = (String) context
								.getProperty("connection_local_AdditionalParams");
					}
					context.setContextType("connection_local_Schema", "id_String");
					if (context.getStringValue("connection_local_Schema") == null) {
						context.connection_local_Schema = null;
					} else {
						context.connection_local_Schema = (String) context.getProperty("connection_local_Schema");
					}
					context.setContextType("connection_local_Server", "id_String");
					if (context.getStringValue("connection_local_Server") == null) {
						context.connection_local_Server = null;
					} else {
						context.connection_local_Server = (String) context.getProperty("connection_local_Server");
					}
				}

				public void processAllContext() {
					processContext_0();
				}
			}

			new ContextProcessing().processAllContext();
		} catch (java.io.IOException ie) {
			System.err.println("Could not load context " + contextStr);
			ie.printStackTrace();
		}

		// get context value from parent directly
		if (parentContextMap != null && !parentContextMap.isEmpty()) {
			if (parentContextMap.containsKey("connection_local_Login")) {
				context.connection_local_Login = (String) parentContextMap.get("connection_local_Login");
			}
			if (parentContextMap.containsKey("connection_local_Database")) {
				context.connection_local_Database = (String) parentContextMap.get("connection_local_Database");
			}
			if (parentContextMap.containsKey("connection_local_Port")) {
				context.connection_local_Port = (String) parentContextMap.get("connection_local_Port");
			}
			if (parentContextMap.containsKey("connection_local_Password")) {
				context.connection_local_Password = (java.lang.String) parentContextMap
						.get("connection_local_Password");
			}
			if (parentContextMap.containsKey("connection_local_AdditionalParams")) {
				context.connection_local_AdditionalParams = (String) parentContextMap
						.get("connection_local_AdditionalParams");
			}
			if (parentContextMap.containsKey("connection_local_Schema")) {
				context.connection_local_Schema = (String) parentContextMap.get("connection_local_Schema");
			}
			if (parentContextMap.containsKey("connection_local_Server")) {
				context.connection_local_Server = (String) parentContextMap.get("connection_local_Server");
			}
		}

		// Resume: init the resumeUtil
		resumeEntryMethodName = ResumeUtil.getResumeEntryMethodName(resuming_checkpoint_path);
		resumeUtil = new ResumeUtil(resuming_logs_dir_path, isChildJob, rootPid);
		resumeUtil.initCommonInfo(pid, rootPid, fatherPid, projectName, jobName, contextStr, jobVersion);

		List<String> parametersToEncrypt = new java.util.ArrayList<String>();
		parametersToEncrypt.add("connection_local_Password");
		// Resume: jobStart
		resumeUtil.addLog("JOB_STARTED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "",
				"", "", "", "", resumeUtil.convertToJsonText(context, ContextProperties.class, parametersToEncrypt));

		org.slf4j.MDC.put("_context", contextStr);
		log.info("TalendJob: 'test' - Started.");
		java.util.Optional.ofNullable(org.slf4j.MDC.getCopyOfContextMap()).ifPresent(mdcInfo::putAll);

		if (execStat) {
			try {
				runStat.openSocket(!isChildJob);
				runStat.setAllPID(rootPid, fatherPid, pid, jobName);
				runStat.startThreadStat(clientHost, portStats);
				runStat.updateStatOnJob(RunStat.JOBSTART, fatherNode);
			} catch (java.io.IOException ioException) {
				ioException.printStackTrace();
			}
		}

		java.util.concurrent.ConcurrentHashMap<Object, Object> concurrentHashMap = new java.util.concurrent.ConcurrentHashMap<Object, Object>();
		globalMap.put("concurrentHashMap", concurrentHashMap);

		long startUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		long endUsedMemory = 0;
		long end = 0;

		startTime = System.currentTimeMillis();

		this.globalResumeTicket = true;// to run tPreJob

		if (enableLogStash) {
			talendJobLog.addJobStartMessage();
			try {
				talendJobLogProcess(globalMap);
			} catch (java.lang.Exception e) {
				e.printStackTrace();
			}
		}

		this.globalResumeTicket = false;// to run others jobs

		try {
			errorCode = null;
			tDBInput_2Process(globalMap);
			if (!"failure".equals(status)) {
				status = "end";
			}
		} catch (TalendException e_tDBInput_2) {
			globalMap.put("tDBInput_2_SUBPROCESS_STATE", -1);

			e_tDBInput_2.printStackTrace();

		}

		this.globalResumeTicket = true;// to run tPostJob

		end = System.currentTimeMillis();

		if (watch) {
			System.out.println((end - startTime) + " milliseconds");
		}

		endUsedMemory = Runtime.getRuntime().totalMemory() - Runtime.getRuntime().freeMemory();
		if (false) {
			System.out.println((endUsedMemory - startUsedMemory) + " bytes memory increase when running : test");
		}
		if (enableLogStash) {
			talendJobLog.addJobEndMessage(startTime, end, status);
			try {
				talendJobLogProcess(globalMap);
			} catch (java.lang.Exception e) {
				e.printStackTrace();
			}
		}

		if (execStat) {
			runStat.updateStatOnJob(RunStat.JOBEND, fatherNode);
			runStat.stopThreadStat();
		}
		if (!inOSGi && isCBPClientPresent) {
			if (org.talend.metrics.CBPClient.getInstanceForCurrentVM() != null) {
				s("none");
				org.talend.metrics.CBPClient.getInstanceForCurrentVM().sendData();
			}
		}

		int returnCode = 0;

		if (errorCode == null) {
			returnCode = status != null && status.equals("failure") ? 1 : 0;
		} else {
			returnCode = errorCode.intValue();
		}
		resumeUtil.addLog("JOB_ENDED", "JOB:" + jobName, parent_part_launcher, Thread.currentThread().getId() + "", "",
				"" + returnCode, "", "", "");
		resumeUtil.flush();

		org.slf4j.MDC.remove("_subJobName");
		org.slf4j.MDC.remove("_subJobPid");
		org.slf4j.MDC.remove("_systemPid");
		log.info("TalendJob: 'test' - Finished - status: " + status + " returnCode: " + returnCode);

		return returnCode;

	}

	// only for OSGi env
	public void destroy() {
		// add CBP code for OSGI Executions
		if (null != taskExecutionId && !taskExecutionId.isEmpty()) {
			try {
				org.talend.metrics.DataReadTracker.setExecutionId(taskExecutionId, jobExecutionId, false);
				org.talend.metrics.DataReadTracker.sealCounter();
				org.talend.metrics.DataReadTracker.reset();
			} catch (Exception | NoClassDefFoundError e) {
				// ignore
			}
		}

	}

	private java.util.Map<String, Object> getSharedConnections4REST() {
		java.util.Map<String, Object> connections = new java.util.HashMap<String, Object>();

		return connections;
	}

	private void evalParam(String arg) {
		if (arg.startsWith("--resuming_logs_dir_path")) {
			resuming_logs_dir_path = arg.substring(25);
		} else if (arg.startsWith("--resuming_checkpoint_path")) {
			resuming_checkpoint_path = arg.substring(27);
		} else if (arg.startsWith("--parent_part_launcher")) {
			parent_part_launcher = arg.substring(23);
		} else if (arg.startsWith("--watch")) {
			watch = true;
		} else if (arg.startsWith("--stat_port=")) {
			String portStatsStr = arg.substring(12);
			if (portStatsStr != null && !portStatsStr.equals("null")) {
				portStats = Integer.parseInt(portStatsStr);
			}
		} else if (arg.startsWith("--trace_port=")) {
			portTraces = Integer.parseInt(arg.substring(13));
		} else if (arg.startsWith("--client_host=")) {
			clientHost = arg.substring(14);
		} else if (arg.startsWith("--context=")) {
			contextStr = arg.substring(10);
			isDefaultContext = false;
		} else if (arg.startsWith("--father_pid=")) {
			fatherPid = arg.substring(13);
		} else if (arg.startsWith("--root_pid=")) {
			rootPid = arg.substring(11);
		} else if (arg.startsWith("--father_node=")) {
			fatherNode = arg.substring(14);
		} else if (arg.startsWith("--pid=")) {
			pid = arg.substring(6);
		} else if (arg.startsWith("--context_type")) {
			String keyValue = arg.substring(15);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.setContextType(keyValue.substring(0, index),
							replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.setContextType(keyValue.substring(0, index), keyValue.substring(index + 1));
				}

			}

		} else if (arg.startsWith("--context_param")) {
			String keyValue = arg.substring(16);
			int index = -1;
			if (keyValue != null && (index = keyValue.indexOf('=')) > -1) {
				if (fatherPid == null) {
					context_param.put(keyValue.substring(0, index), replaceEscapeChars(keyValue.substring(index + 1)));
				} else { // the subjob won't escape the especial chars
					context_param.put(keyValue.substring(0, index), keyValue.substring(index + 1));
				}
			}
		} else if (arg.startsWith("--context_file")) {
			String keyValue = arg.substring(15);
			String filePath = new String(java.util.Base64.getDecoder().decode(keyValue));
			java.nio.file.Path contextFile = java.nio.file.Paths.get(filePath);
			try (java.io.BufferedReader reader = java.nio.file.Files.newBufferedReader(contextFile)) {
				String line;
				while ((line = reader.readLine()) != null) {
					int index = -1;
					if ((index = line.indexOf('=')) > -1) {
						if (line.startsWith("--context_param")) {
							if ("id_Password".equals(context_param.getContextType(line.substring(16, index)))) {
								context_param.put(line.substring(16, index),
										routines.system.PasswordEncryptUtil.decryptPassword(line.substring(index + 1)));
							} else {
								context_param.put(line.substring(16, index), line.substring(index + 1));
							}
						} else {// --context_type
							context_param.setContextType(line.substring(15, index), line.substring(index + 1));
						}
					}
				}
			} catch (java.io.IOException e) {
				System.err.println("Could not load the context file: " + filePath);
				e.printStackTrace();
			}
		} else if (arg.startsWith("--log4jLevel=")) {
			log4jLevel = arg.substring(13);
		} else if (arg.startsWith("--audit.enabled") && arg.contains("=")) {// for trunjob call
			final int equal = arg.indexOf('=');
			final String key = arg.substring("--".length(), equal);
			System.setProperty(key, arg.substring(equal + 1));
		}
	}

	private static final String NULL_VALUE_EXPRESSION_IN_COMMAND_STRING_FOR_CHILD_JOB_ONLY = "<TALEND_NULL>";

	private final String[][] escapeChars = { { "\\\\", "\\" }, { "\\n", "\n" }, { "\\'", "\'" }, { "\\r", "\r" },
			{ "\\f", "\f" }, { "\\b", "\b" }, { "\\t", "\t" } };

	private String replaceEscapeChars(String keyValue) {

		if (keyValue == null || ("").equals(keyValue.trim())) {
			return keyValue;
		}

		StringBuilder result = new StringBuilder();
		int currIndex = 0;
		while (currIndex < keyValue.length()) {
			int index = -1;
			// judege if the left string includes escape chars
			for (String[] strArray : escapeChars) {
				index = keyValue.indexOf(strArray[0], currIndex);
				if (index >= 0) {

					result.append(keyValue.substring(currIndex, index + strArray[0].length()).replace(strArray[0],
							strArray[1]));
					currIndex = index + strArray[0].length();
					break;
				}
			}
			// if the left string doesn't include escape chars, append the left into the
			// result
			if (index < 0) {
				result.append(keyValue.substring(currIndex));
				currIndex = currIndex + keyValue.length();
			}
		}

		return result.toString();
	}

	public Integer getErrorCode() {
		return errorCode;
	}

	public String getStatus() {
		return status;
	}

	ResumeUtil resumeUtil = null;
}
/************************************************************************************************
 * 150349 characters generated by Qlik Talend Cloud Enterprise Edition on the 23
 * septembre 2026 à 09:56:49 CEST
 ************************************************************************************************/