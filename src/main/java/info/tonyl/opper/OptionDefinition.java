package info.tonyl.opper;

public class OptionDefinition {
	private String name;
	private boolean hasValue = false;

	public OptionDefinition(String name, boolean hasValue) {
		this.name = name;
		this.hasValue = hasValue;
	}

	public String getName() {
		return name;
	}

	public boolean hasValue() {
		return hasValue;
	}

	public Option make() {
		Option o = new Option();
		o.hasValue = hasValue;
		o.value = "";
		return o;
	}
}
