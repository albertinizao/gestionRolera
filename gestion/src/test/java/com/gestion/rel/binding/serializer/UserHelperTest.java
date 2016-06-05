package com.gestion.rel.binding.serializer;

import static org.mockito.Mockito.when;

import javax.servlet.http.HttpServletRequest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import com.gestion.rel.binding.serializer.helper.UserHelper;

public class UserHelperTest {
	private UserHelper userHelper;
	protected HttpServletRequest request;

	@Before
	public void init() {
		request = Mockito.mock(HttpServletRequest.class);
		userHelper = UserHelper.getInstance(request);
	}
	
	@Test
	public void getMap(){
		String userId = "1234";
		String prevUrl = "http://pepe.com";
		when(request.getContextPath()).thenReturn(prevUrl);
		userHelper.getMap(userId);
	}
}
