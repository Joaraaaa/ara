package org.ara.dbtest;

import javax.sql.DataSource;

import org.ara.mapper.TestMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("file:src/main/webapp/WEB-INF/spring/root-context.xml")

public class TestMapperTest {
	@Autowired
	// DataSource dataSource = new DataSource(); 자바문법
	private TestMapper testMapper;
	
	@Test
	public void testGetTime() {
		System.out.println(testMapper.getTime());
	}

}
