package demo.controller.bar;

import javax.validation.constraints.Size;

public class RegistBarForm {

	@Size(max = 2)
    private String _barName;

    public String getBarName() {
        return _barName;
    }

    public void setBarName(String barName) {
        this._barName = barName;
    }
}
