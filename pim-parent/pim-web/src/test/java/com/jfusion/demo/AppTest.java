package com.jfusion.demo;

import com.sinux.modules.exception.entity.PimBusException;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

/**
 * Unit test for simple App.
 */
public class AppTest 
    extends TestCase
{
    /**
     * Create the test case
     *
     * @param testName name of the test case
     */
    public AppTest( String testName )
    {
        super( testName );
    }

    /**
     * @return the suite of tests being tested
     */
    public static Test suite()
    {
        return new TestSuite( AppTest.class );
    }

    /**
     * Rigourous Test :-)
     */
    public void testApp()
    {
        assertTrue( true );
    }
    
    @org.junit.Test
    public void test1() {
    	int len = 10000;
    	Long a1 = System.currentTimeMillis();
    	for(int i=0;i<len;i++) {
    		ResultMesg mesg  =check2("1","2");
        	//System.out.println(mesg);
    	}
    	
    	Long a2 = System.currentTimeMillis();
    	System.out.println("check方法用时:"+(a2-a1));
    	a1 = System.currentTimeMillis();
    	for(int i=0;i<len;i++) {
    		ResultMesg mesg1 = check3("1","2");
        	//System.out.println(mesg1);
    	}
    	
    	a2 = System.currentTimeMillis();
    	System.out.println();
     	System.out.println("check2方法用时:"+(a2-a1));
    }
    
    class ResultMesg {
    	private String code;
    	private String mesg;
		public String getCode() {
			return code;
		}
		public void setCode(String code) {
			this.code = code;
		}
		public String getMesg() {
			return mesg;
		}
		public void setMesg(String mesg) {
			this.mesg = mesg;
		}
		@Override
		public String toString() {
			return "ResultMesg [code=" + code + ", mesg=" + mesg + "]";
		}
    	
    	
    }
    
    private ResultMesg check(String name,String password) {
    	ResultMesg mesg = new ResultMesg();
    	if(name.equals(password)) {
    		mesg.setCode("1");
    		mesg.setMesg("验证成功.");
    	}else {
    		mesg.setCode("2");
    		mesg.setMesg("验证失败.");
    	}
    	return mesg;
    }
    
    private boolean check1(String name,String password) {
    	if(name.equals(password)) {
    		return true;
    	}else {
    		throw new PimBusException("验证失败");
    	}
   
    }
    
    private ResultMesg check2(String name,String password) {
    	try {
    		ResultMesg mesg = new ResultMesg();
    		check1(name, password);
    		mesg.setCode("1");
    		mesg.setMesg("验证成功.");
    		return mesg;
    	}catch (PimBusException e) {
			// TODO: handle exception
    		ResultMesg mesg = new ResultMesg();
    		mesg.setCode("2");
    		mesg.setMesg(e.getErrorMsg());
    		return mesg;
		}
    	
    }
    
    private ResultMesg check3(String name,String password) {
    	try {
    		ResultMesg mesg = new ResultMesg();
    		check(name, password);
    
    		return mesg;
    	}catch (Exception e) {
			// TODO: handle exception
    		ResultMesg mesg = new ResultMesg();
    		mesg.setCode("2");
    		mesg.setMesg(e.getMessage());
    		return mesg;
		}
    	
    	
    }
}
