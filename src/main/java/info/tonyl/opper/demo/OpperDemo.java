package info.tonyl.opper.demo;

import info.tonyl.opper.Opper;

public class OpperDemo {
	private static String[] ops = { "a", "b", "c", "d", "e", "long" };
	private static String[] opValue = { "test", "one", "two", "three", "f" };

	public static void main(String args[]) {
		Opper.register(ops);

		for (String s : opValue) {
			Opper.register(s, true);
		}

		Opper.parse(args);

		for (String s : ops) {
			if (Opper.isSet(s)) {
				System.out.println(s + " is set.");
			}
		}

		for (String s : opValue) {
			if (Opper.isSet(s)) {
				System.out.println(s + " is set, and it's value is \"" + Opper.getValueOf(s) + "\"");
			}
		}
	}
}
