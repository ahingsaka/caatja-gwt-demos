package com.katspow.caatjagwtdemos.client.welcome;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.katspow.caatjagwtdemos.client.welcome.hypernumber.HyperNumber;
import com.katspow.caatjagwtdemos.client.welcome.showcase.Showcase;
import com.katspow.caatjagwtdemos.client.welcome.tutorials.Tutorials;

public class WelcomeView extends Composite {

    private static final String NOT_YET_IMPLEMENTED = "Not yet implemented !";
    private static final String HEIGHT = "500px";
    private static final String WIDTH = "680px";
    
    private static final String WELCOME_TEXT = "Welcome to the CAATJA-GWT demos !<br>"
            + "Choose one of the examples on the left<br> " + "by clicking on the icon";

    private enum Demo {
        SHOWCASE("Showcase"), DEMOS("Demos"), DEMOS_WITH_SOURCE("Demos with source"), HYPERNUMBER("Hypernumber");
        
        private String label;
        
        private Demo(String label) {
            this.label = label;
        }
        
        public String getLabel() {
            return label;
        }
    }

    public WelcomeView() {

        FlexTable grid = new FlexTable();
        grid.setSize(WIDTH, HEIGHT);

        grid.setText(0, 0, "CAATJA-GWT demos");
        grid.getFlexCellFormatter().setColSpan(0, 0, 3);
        grid.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

        HTML showcase = createHTMLEntry(Demo.SHOWCASE);
        HTML demos = createHTMLEntry(Demo.DEMOS);
        HTML demosWithSourceCode = createHTMLEntry(Demo.DEMOS_WITH_SOURCE);
        HTML hypernumber = createHTMLEntry(Demo.HYPERNUMBER);

        grid.setWidget(1, 0, showcase);
        grid.setWidget(2, 0, demos);
        grid.setWidget(3, 0, demosWithSourceCode);
        grid.setWidget(4, 0, hypernumber);

        grid.setWidget(1, 2, new HTML(WELCOME_TEXT));
        grid.getFlexCellFormatter().setColSpan(1, 2, 2);
        grid.getFlexCellFormatter().setRowSpan(1, 2, 4);
        grid.getFlexCellFormatter().setHorizontalAlignment(1, 2, HasHorizontalAlignment.ALIGN_CENTER);
        grid.getFlexCellFormatter().setVerticalAlignment(1, 2, HasVerticalAlignment.ALIGN_MIDDLE);

        initWidget(grid);

    }
    
    private HTML createHTMLEntry(final Demo demo) {
        HTML htmlEntry = new HTML(demo.getLabel());
        htmlEntry.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                loadDemo(demo);
            }
        });
        
        return htmlEntry;
    }
    

    private void loadDemo(Demo demo) {

        try {
            WelcomeView.this.setVisible(false);

            switch (demo) {
            case SHOWCASE:
                new Showcase().start();
                break;

            case HYPERNUMBER:
                new HyperNumber().start();
                break;

            case DEMOS_WITH_SOURCE:
                new Tutorials().start();
                break;
                
            case DEMOS:
                Window.alert(NOT_YET_IMPLEMENTED);
                break;

            default:
                break;
            }
        } catch (Exception e) {
            Window.alert("Could not load " + demo.name());
            e.printStackTrace();
        }
    }
}
