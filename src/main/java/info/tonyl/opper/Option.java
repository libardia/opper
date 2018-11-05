package info.tonyl.opper;

import java.util.Arrays;

class Option {
	String[] names;
	boolean hasValue;
	String value;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[Option: names=");
		builder.append(Arrays.toString(names));
		builder.append(", hasValue=");
		builder.append(hasValue);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
}
