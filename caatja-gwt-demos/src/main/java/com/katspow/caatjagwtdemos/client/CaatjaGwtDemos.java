package com.katspow.caatjagwtdemos.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.user.client.ui.RootPanel;
import com.katspow.caatja.core.Caatja;
import com.katspow.caatjagwt.client.CAATGwt;
import com.katspow.caatjagwt.client.CaatjaGwtDate;
import com.katspow.caatjagwt.client.CaatjaGwtEventManager;
import com.katspow.caatjagwt.client.CaatjaGwtImageLoader;
import com.katspow.caatjagwt.client.CaatjaGwtNavigator;
import com.katspow.caatjagwt.client.CaatjaGwtPreloader;
import com.katspow.caatjagwt.client.CaatjaGwtRootPanel;
import com.katspow.caatjagwt.client.CaatjaGwtService;
import com.katspow.caatjagwt.client.CaatjaGwtWindow;
import com.katspow.caatjagwtdemos.client.welcome.HomeView;

public class CaatjaGwtDemos implements EntryPoint {

	public void onModuleLoad() {

	    // FIXME DI
		new Caatja(new CaatjaGwtDate(), new CaatjaGwtNavigator(), new CaatjaGwtWindow(), new CaatjaGwtRootPanel(),
				new CaatjaGwtService(), new CaatjaGwtEventManager(), new CaatjaGwtImageLoader(),
				new CaatjaGwtPreloader(), new CAATGwt());
		
		try {
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
