package info.tonyl.opper.demo;

import info.tonyl.opper.Opper;

public class OpperDemo {
	public static void main(String args[]) {
		Opper.registerWithValue("f", "file");
		Opper.registerMultiple("m", "s", "D");
		Opper.parse(args);

		for (String k : Opper.getAllSetOptions()) {
			System.out.print("Option " + k);
			if (!Opper.hasValue(k)) {
				System.out.println(" is set.");
			} else {
				System.out.println(" has value \"" + Opper.getValueOf(k) + "\"");
			}
		}

		System.out.println("\nAll nameless values:");
		for (String s : Opper.getNamelessValues()) {
			System.out.println("\"" + s + "\"");
		}
	}
}
