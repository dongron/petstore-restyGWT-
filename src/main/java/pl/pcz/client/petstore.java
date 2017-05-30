package pl.pcz.client;

import com.google.gwt.core.client.EntryPoint;
import com.google.gwt.core.client.GWT;
import com.google.gwt.event.dom.client.ClickHandler;
import com.google.gwt.event.dom.client.ClickEvent;
import com.google.gwt.core.client.Scheduler;
import com.google.gwt.user.client.ui.Anchor;
import com.google.gwt.user.client.ui.FlexTable;
import com.google.gwt.user.client.ui.FlowPanel;
import com.google.gwt.user.client.ui.HorizontalPanel;
import com.google.gwt.user.client.ui.Label;
import com.google.gwt.user.client.ui.RootPanel;
import com.google.gwt.user.client.ui.VerticalPanel;

import com.google.gwt.http.client.Request;
import com.google.gwt.http.client.RequestBuilder;
import com.google.gwt.http.client.RequestCallback;
import com.google.gwt.http.client.RequestException;
import com.google.gwt.http.client.Response;
import com.google.gwt.http.client.URL;

import org.fusesource.restygwt.client.Method;
import org.fusesource.restygwt.client.MethodCallback;
import org.fusesource.restygwt.client.Defaults;
    
public class petstore implements EntryPoint, Scheduler.RepeatingCommand {

    private Label header = new Label();
    private FlexTable table = new FlexTable();
    private Label id = new Label("123");
    private Label name = new Label("Spot");
    private Label category = new Label("reserved");
    private VerticalPanel footer = new VerticalPanel();
    private int i = 0;

    public boolean execute() {
	header.setText("Petstore: " + i);
	++i;
	return true;
    }
    
    private void addPet(final String id, final String name, final String category) {

	ClickHandler h = new ClickHandler() {
	        String m_id = id;
		String m_name = name;
		String m_category = category;

		public void onClick(ClickEvent event) {
		    petstore.this.id.setText(m_id);
		    petstore.this.name.setText(m_name);
		    petstore.this.category.setText(m_category);
		}
	    };
	    
	int row = table.getRowCount();
	Label l1 = new Label(id);
	Label l2 = new Label(name);
	l1.addClickHandler(h);
	l2.addClickHandler(h);
	table.setWidget(row, 0, l1);
	table.setWidget(row, 1, l2);
    }

    public void onModuleLoad() {

        Defaults.setAddXHttpMethodOverrideHeader(false);
            
	Scheduler.get().scheduleFixedPeriod(this, 1000);
	
	// Sample pet data.
	table.addStyleName("table");
	addPet("123", "Spot", "available");
	addPet("321", "Duff", "taken");

	header.addStyleName("header");

	VerticalPanel right = new VerticalPanel();
	FlowPanel f1 = new FlowPanel();
	f1.add(new Label("ID: "));
	f1.add(id);
	FlowPanel f2 = new FlowPanel();
	f2.add(new Label("Name: "));
	f2.add(name);
	FlowPanel f3 = new FlowPanel();
	f3.add(new Label("Category: "));
	f3.add(category);
	right.add(f1);
	right.add(f2);
	right.add(f3);

        HorizontalPanel middle = new HorizontalPanel();
	middle.addStyleName("middle");
	middle.add(table);
	middle.add(right);

	footer.addStyleName("footer");

        RequestBuilder builder = new RequestBuilder(RequestBuilder.GET,
                                                    URL.encode("http://petstore.swagger.io/v2/pet/1"));

        try {
            Request request = builder.sendRequest(null, new RequestCallback() {

                    public void onError(Request request, Throwable exception) {
                        footer.add(new Label("Exception caught"));
                    }

                    public void onResponseReceived(Request request, Response response) {
                        // footer.add(new Label(response.getText()));
                    }
                });
        } catch (RequestException e) {
            footer.add(new Label("Exception caught"));
        }
        
	VerticalPanel vp = new VerticalPanel();
	vp.addStyleName("outer");
	vp.add(header);
	vp.add(middle);
	vp.add(footer);

        WebAPI service = GWT.create(WebAPI.class);
        service.getPet(1, new MethodCallback<Pet>() {
    
                public void onSuccess(Method method, Pet pet) {
                    footer.add(new Label(pet.name));
                }
    
                public void onFailure(Method method, Throwable exception) {
                    footer.add(new Label(exception.getMessage()));
                }
            });

	RootPanel rp = RootPanel.get();
	rp.add(vp);
    }
}
