package com.example.sop_midterm_63070158;

import com.vaadin.flow.component.Key;
import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.combobox.ComboBox;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.NumberField;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import org.springframework.web.reactive.function.client.WebClient;

@Route("/index")
public class ProductView extends VerticalLayout {
    private ComboBox productList;
    private TextField productName;
    private NumberField cost, profit, price;
    private Button add, update, delete, clear;
    private HorizontalLayout h1;
    public ProductView() {
        productList = new ComboBox("Product List");
        productList.setWidth("600px");
        productName = new TextField();
        productName.setLabel("Product Name: ");
        productName.setWidth("600px");
        cost = new NumberField();
        cost.setLabel("Product Cost:");
        cost.setWidth("600px");
        cost.setValue(0.0);
        profit =  new NumberField();
        profit.setLabel("Product Profit");
        profit.setValue(0.0);
        profit.setWidth("600px");
        price = new NumberField();
        price.setLabel("Product Price:");
        price.setWidth("600px");
        price.setValue(0.0);
        price.setEnabled(false);
        add = new Button("Add Product");
        update = new Button("Update Product");
        delete = new Button("Delete Product");
        clear = new Button("Clear Product");
        h1 = new HorizontalLayout();
        h1.add(add, update, delete, clear);
        this.add(productList, productName, cost, profit, price, h1);

        clearProduct();
        clear.addClickListener(event -> {
           clearProduct();
        });

        cost.addKeyPressListener(Key.ENTER, e ->{
            callPrice();
        });

    }
    public void clearProduct() {
        this.productName.setValue("");
        this.cost.setValue(0.0);
        this.profit.setValue(0.0);
        this.profit.setValue(0.0);
    }
    public void callPrice() {
        double cost = this.cost.getValue();
        double profit = this.profit.getValue();
        double net = WebClient.create().get().uri("http://127.0.0.1:80/getPrice/" + cost + "/+" + profit).retrieve().bodyToMono(Double.class).block();
        price.setValue(net);
    }
}
