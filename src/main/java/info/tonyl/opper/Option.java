package info.tonyl.opper;

class Option {
	boolean hasValue;
	String value;

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("[Option: hasValue=");
		builder.append(hasValue);
		builder.append(", value=");
		builder.append(value);
		builder.append("]");
		return builder.toString();
	}
}
