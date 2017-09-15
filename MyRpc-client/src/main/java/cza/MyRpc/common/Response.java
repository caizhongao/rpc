
    /**  
    * @Title: Response.java
    * @Package cza.MyRpc.server
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年2月8日下午4:23:32
    * @version V1.0  
    */
    
package cza.MyRpc.common;

import java.io.Serializable;

/**
    * @ClassName: Response
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年2月8日下午4:23:32
    *
    */

public class Response implements Serializable{
	
	    /**
	    * @Fields serialVersionUID : TODO(用一句话描述这个变量表示什么)
	    */
	    
	private static final long serialVersionUID = 1L;
	private Long requestId;
    private Throwable error;
    private Object result;
    
	
	
	    /**
	     * 创建一个新的实例 Response.
	     *
	     * @param requestId
	     * @param error
	     * @param result
	     */
	    
	public Response(Long requestId, Throwable error, Object result) {
		super();
		this.requestId = requestId;
		this.error = error;
		this.result = result;
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
	* @return error
	*/
	
	public Throwable getError() {
		return error;
	}
	
	/**
	 * @param error the error to set
	 */
	
	public void setError(Throwable error) {
		this.error = error;
	}
	
	/**
	* @return result
	*/
	
	public Object getResult() {
		return result;
	}
	
	/**
	 * @param result the result to set
	 */
	
	public void setResult(Object result) {
		this.result = result;
	}
}
