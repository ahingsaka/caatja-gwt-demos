package com.katspow.caatjagwtdemos.client.welcome;

import com.google.gwt.dom.client.Style.Unit;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.DockLayoutPanel;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.ScrollPanel;
import com.google.gwt.user.client.ui.VerticalPanel;
import com.katspow.caatjagwtdemos.client.showcase.Showcase;

public class WelcomeView extends Composite {

    private static final String WELCOME_TEXT = "Welcome to the CAATJA-GWT demos !<br>"
            + "Choose one of the examples on the left<br> " + "by clicking on the icon";

    public WelcomeView() {
        DockLayoutPanel mainPanel = new DockLayoutPanel(Unit.PCT);

        mainPanel.addNorth(new HTML("CAATJA-GWT demos"), 20);

        VerticalPanel vp = new VerticalPanel();
        
        HTML showcase = new HTML("Showcase");
        showcase.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
               WelcomeView.this.setVisible(false);
                try {
                    new Showcase().start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        
        vp.add(showcase);
        
        HTML hypernumber = new HTML("Hypernumber");
        hypernumber.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                
            }
        });
        
        vp.add(hypernumber);

        HTML demosWithSourceCode = new HTML("Demos with source code");
        demosWithSourceCode.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                
            }
        });
        
        vp.add(demosWithSourceCode);
        

        mainPanel.addWest(new ScrollPanel(vp), 50);
        mainPanel.add(new HTML(WELCOME_TEXT));

        initWidget(mainPanel);

    }

}
