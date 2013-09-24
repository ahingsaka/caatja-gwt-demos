package com.katspow.caatjagwtdemos.client.welcome;

import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.user.client.Window;
import com.google.gwt.user.client.ui.Composite;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.HTML;
import com.google.gwt.user.client.ui.HasHorizontalAlignment;
import com.google.gwt.user.client.ui.HasVerticalAlignment;
import com.katspow.caatjagwtdemos.client.hypernumber.HyperNumber;
import com.katspow.caatjagwtdemos.client.showcase.Showcase;
import com.katspow.caatjagwtdemos.client.tutorials.Tutorials;

public class WelcomeView extends Composite {

    private static final String WELCOME_TEXT = "Welcome to the CAATJA-GWT demos !<br>"
            + "Choose one of the examples on the left<br> " + "by clicking on the icon";

    private enum Demo {
        SHOWCASE, HYPERNUMBER, DEMOS
    }

    public WelcomeView() {

        FlexTable grid = new FlexTable();
        grid.setSize("680px", "500px");

        grid.setText(0, 0, "CAATJA-GWT demos");
        grid.getFlexCellFormatter().setColSpan(0, 0, 3);
        grid.getFlexCellFormatter().setHorizontalAlignment(0, 0, HasHorizontalAlignment.ALIGN_CENTER);

        HTML showcase = new HTML("Showcase");
        showcase.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                loadDemo(Demo.SHOWCASE);
            }
        });

        HTML hypernumber = new HTML("Hypernumber (beta)");
        hypernumber.addClickHandler(new ClickHandler() {
            public void onClick(ClickEvent event) {
                loadDemo(Demo.HYPERNUMBER);
            }
        });

        HTML demosWithSourceCode = new HTML("Demos with source code");
        demosWithSourceCode.addClickHandler(new ClickHandler() {
            @Override
            public void onClick(ClickEvent event) {
                loadDemo(Demo.DEMOS);
            }
        });

        grid.setWidget(1, 0, showcase);
        grid.setWidget(2, 0, hypernumber);
        grid.setWidget(3, 0, demosWithSourceCode);

        grid.setWidget(1, 2, new HTML(WELCOME_TEXT));
        grid.getFlexCellFormatter().setColSpan(1, 2, 2);
        grid.getFlexCellFormatter().setRowSpan(1, 2, 3);
        grid.getFlexCellFormatter().setHorizontalAlignment(1, 2, HasHorizontalAlignment.ALIGN_CENTER);
        grid.getFlexCellFormatter().setVerticalAlignment(1, 2, HasVerticalAlignment.ALIGN_MIDDLE);

        initWidget(grid);

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

            case DEMOS:
                new Tutorials().start();
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
