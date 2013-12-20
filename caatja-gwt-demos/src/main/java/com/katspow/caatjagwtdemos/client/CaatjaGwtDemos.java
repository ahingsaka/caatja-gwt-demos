package com.katspow.caatjagwtdemos.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.katspow.caatjagwt.client.CaatjaGwt;
import com.katspow.caatjagwtdemos.client.welcome.HomeView;

public class CaatjaGwtDemos implements EntryPoint {

	public void onModuleLoad() {

		try {
		    CaatjaGwt.init();
//			new Main().__CAAT_init();
//			new HyperNumber().init();
//			new Tut031().init();
		    
			//new Showcase().start();
			RootPanel.get().add(new HomeView());
			
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
