
    /**  
    * @Title: Request.java
    * @Package cza.MyRpc.server
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月8日下午3:45:54
    * @version V1.0  
    */
    
package cza.MyRpc.common;

import java.io.Serializable;

/**
    * @ClassName: Request
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月8日下午3:45:54
    *
    */

public class Request implements Serializable{
	
	    /**
	    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	    */
	    
	private static final long serialVersionUID = 1L;
	private Long requestId;
	private Object[] args;
	private String serviceName;
	private String method;
	private Class<?>[] argTypes;
	
	
	
	
	
	
	
	
	/**
	* @return argTypes
	*/
	
	public Class<?>[] getArgTypes() {
		return argTypes;
	}


	
	/**
	 * @param argTypes the argTypes to set
	 */
	
	public void setArgTypes(Class<?>[] argTypes) {
		this.argTypes = argTypes;
	}


	/**
	* @return requestId
	*/
	
	public Long getRequestId() {
		return requestId;
	}

	
	/**
	 * @param requestId the requestId to set
	 */
	
	public void setRequestId(Long requestId) {
		this.requestId = requestId;
	}

	/**
	* @return args
	*/
	
	public Object[] getArgs() {
		return args;
	}
	
	/**
	 * @param args the args to set
	 */
	
	public void setArgs(Object[] args) {
		this.args = args;
	}
	
	/**
	* @return serviceName
	*/
	
	public String getServiceName() {
		return serviceName;
	}
	
	/**
	 * @param serviceName the serviceName to set
	 */
	
	public void setServiceName(String serviceName) {
		this.serviceName = serviceName;
	}
	
	/**
	* @return method
	*/
	
	public String getMethod() {
		return method;
	}
	
	/**
	 * @param method the method to set
	 */
	
	public void setMethod(String method) {
		this.method = method;
	}
	
	
}
