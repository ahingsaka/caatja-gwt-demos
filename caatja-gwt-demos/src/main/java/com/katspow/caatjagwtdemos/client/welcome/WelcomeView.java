package com.katspow.caatjagwtdemos.client.welcome;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.LayoutPanel;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

public class WelcomeView extends Composite {

	private static final String WELCOME_TEXT = "Welcome to the CAATJA-GWT demos !<br>"
			+ "Choose one of the examples on the left<br> "
			+ "by clicking on the icon";

	public WelcomeView() {
		DockLayoutPanel mainPanel = new DockLayoutPanel(Unit.PCT);

		mainPanel.addNorth(new HTML("CAATJA-GWT demos"), 20);

		VerticalPanel vp = new VerticalPanel();
		LayoutPanel showCaseLine = new LayoutPanel();
		LayoutPanel hyperNumberLine = new LayoutPanel();
		LayoutPanel demosWithSourceLine = new LayoutPanel();
		
		vp.add(showCaseLine);
		vp.add(hyperNumberLine);
		vp.add(demosWithSourceLine);

		mainPanel.addWest(new ScrollPanel(vp), 50);
		mainPanel.add(new HTML(WELCOME_TEXT));

		initWidget(mainPanel);

	}

}
