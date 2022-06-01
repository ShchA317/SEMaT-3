package webapp;

import javax.inject.Named;

@Named
public class ExampleBean {
    private String text = "Some text";

    public String getText(){
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
