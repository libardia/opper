package info.tonyl.opper.demo;

import info.tonyl.opper.Opper;

public class OpperDemo {

	public static void main(String args[]) {
		Opper.register(new String[] { "f", "file" }, true);
		Opper.parse(args);

		System.out.println(Opper.tempGetOptions().toString());
	}
}
