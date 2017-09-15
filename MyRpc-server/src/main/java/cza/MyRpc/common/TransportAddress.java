
    /**  
    * @Title: TransportAddress.java
    * @Package cza.MyRpc.common
    * @Description: TODO(用一句话描述该文件做什么)
    * @author mufeng
    * @date 2017年9月15日上午11:02:29
    * @version V1.0  
    */
    
package cza.MyRpc.common;


    /**
    * @ClassName: TransportAddress
    * @Description: TODO(这里用一句话描述这个类的作用)
    * @author mufeng
    * @date 2017年9月15日上午11:02:29
    *
    */

public class TransportAddress {
	private String addr;
	private Integer port;
	
	
	
	
	/**
	 * 创建一个新的实例 TransportAddress.
	 *
	 */

	public TransportAddress() {
		// TODO Auto-generated constructor stub
	}
	
	    /**
	     * 创建一个新的实例 TransportAddress.
	     *
	     * @param addr
	     * @param port
	     */
	    
	public TransportAddress(String addr, Integer port) {
		super();
		this.addr = addr;
		this.port = port;
	}

	/**
	* @return addr
	*/
	
	public String getAddr() {
		return addr;
	}
	
	/**
	 * @param addr the addr to set
	 */
	
	public void setAddr(String addr) {
		this.addr = addr;
	}
	
	/**
	* @return port
	*/
	
	public Integer getPort() {
		return port;
	}
	
	/**
	 * @param port the port to set
	 */
	
	public void setPort(Integer port) {
		this.port = port;
	}
	
	
}
