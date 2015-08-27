package com.verizon.pc.rest.apps.om;


import java.util.logging.Logger;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;


import com.sun.jersey.api.view.Viewable;
import com.verizon.webkit.datacloud.DataCloud;
import com.verizon.webkit.datacloud.DataSourceCriteria;
import com.verizon.webkit.datacloud.DataSourceResult;
import com.verizon.webkit.datacloud.DataSourceResultColumnTypeEnum;
import com.verizon.webkit.datacloud.DataSourceResultSet;

@Path("/apps/ordermanager/HoldJeopardyHistory")
public class HoldJeopardyHistory extends OrderManagerPanel{
	private final static Logger logger = Logger.getLogger(HoldJeopardyHistory.class.getName());
	
	@GET
    @Produces(MediaType.TEXT_HTML)
    public Viewable doIndex() throws Exception {
    	init();
    
    	DataSourceCriteria criteria = new DataSourceCriteria();        
		logger.finer("order number "+omReq.getOrderNum());
		criteria.put("ORDER_NUMBER",omReq.getOrderNum());
        DataSourceResultSet ws = DataCloud.perform("getOrderDetails.HoldJeopardyHistory",criteria);
        
    	if(ws==null || ws.getRowCount()==0){
    		msgList.add("No Hold/Jeop History Data For Order : "+omReq.getOrderNum());
    	}else{
    		
			//kv.setTitle("Task Progress History (Hold/Jeopardy/Review)");
    		resultSet.addColumn("ORDER_NUMBER","Order Number",DataSourceResultColumnTypeEnum.STRING);
    		resultSet.addColumn("TASK_NAME","Task Name",DataSourceResultColumnTypeEnum.STRING);
    		resultSet.addColumn("TASK_PROGRESS_STATUS","Progress Status",DataSourceResultColumnTypeEnum.STRING);
    		resultSet.addColumn("REASON_CODE","Reason Code",DataSourceResultColumnTypeEnum.STRING);
    		resultSet.addColumn("REASON_CODE_DESC","Reason Code Description",DataSourceResultColumnTypeEnum.STRING);
    		resultSet.addColumn("TRXN_START_TIME","Transaction Start Time",null);
    		resultSet.addColumn("TRXN_END_TIME","Transaction End Time",null);
    		resultSet.addColumn("TRXN_START_USER_ID","Transaction Start User ID",DataSourceResultColumnTypeEnum.STRING);
    		resultSet.addColumn("TRXN_END_USER_ID","Transaction End User ID",DataSourceResultColumnTypeEnum.STRING);
    		resultSet.addColumn("DURATION","Duration (days:hours:minutes)",DataSourceResultColumnTypeEnum.STRING);
    		
    		for(DataSourceResult or: ws.getRows()){	
				DataSourceResult r = new DataSourceResult();
				r.put("ORDER_NUMBER", or.getString("ORDER_NUMBER"));
				r.put("TASK_NAME", or.getString("TASK_NAME"));
				r.put("TASK_PROGRESS_STATUS", or.getString("TASK_PROGRESS_STATUS"));
				r.put("REASON_CODE", or.getString("REASON_CODE"));
				r.put("REASON_CODE_DESC", or.getString("REASON_CODE_DESC"));
				r.put("TRXN_START_TIME", or.get("TRXN_START_TIME"));
				r.put("TRXN_END_TIME", or.get("TRXN_END_TIME"));
				r.put("TRXN_START_USER_ID", or.getString("TRXN_START_USER_ID"));
				r.put("TRXN_END_USER_ID", or.getString("TRXN_END_USER_ID"));
				r.put("DURATION", or.getString("DURATION"));
				resultSet.addRow(r);
			}
    	}	
    	return new Viewable("/apps/om/HoldJeopardyHistory.jsp");
	}
	
	private DataSourceResultSet resultSet = new DataSourceResultSet();
	public DataSourceResultSet getResultSet() {
		return resultSet;
	}
	
}
 
