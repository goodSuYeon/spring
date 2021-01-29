package kr.or.ddit.ioc;

import static org.junit.Assert.*;

import javax.annotation.Resource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/kr/or/ddit/ioc/ioc.xml")
public class CollectionBeanTest {

	// collectionBean 스프링 bean이 정상적으로 생성되었는지 확인하기 위한 테스트
	@Resource(name="collectionBean")
	private CollectionBean collectionBean;
	
	@Test
	public void collectionBeanTest() {
		assertNotNull(collectionBean);
		assertNotNull(collectionBean.getList());
		assertEquals(3, collectionBean.getList().size());
		assertEquals("brown", collectionBean.getList().get(1));
		
		assertEquals("김수연", collectionBean.getMap().get("usernm"));
	}

}
