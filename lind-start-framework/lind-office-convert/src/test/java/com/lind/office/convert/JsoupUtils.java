package com.lind.office.convert;

import org.jsoup.Jsoup;
import org.junit.Test;

public class JsoupUtils {

	@Test
	public void removeHtml() {

		String dirtyHTML = "<p><img alt=\"\" src=\"http://back.chinalawinfo.com/upFiles/images/7(16).png\" data-bd-imgshare-binded=\"1\" width=\"549\" height=\"299\">";
		String cleanHTML = Jsoup.parse(dirtyHTML).html();
		System.out.println(cleanHTML);
	}

}
