package info.tonyl.opper;

public class OptionDefinition {
	private String[] names;
	private boolean hasValue = false;

	public OptionDefinition(String[] names, boolean hasValue) {
		this.names = names;
		this.hasValue = hasValue;
	}

	public String[] getNames() {
		return names;
	}

	public boolean hasValue() {
		return hasValue;
	}

	public Option make() {
		Option o = new Option();
		o.names = names;
		o.hasValue = hasValue;
		o.value = null;
		return o;
	}
}
