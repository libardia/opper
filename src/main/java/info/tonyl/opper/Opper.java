package info.tonyl.opper;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Opper {
	private static List<OptionDefinition> definitions = new ArrayList<>();
	private static Map<String, Option> options = new HashMap<>();
	private static List<String> namelessValues = new ArrayList<>();

	public static void register(OptionDefinition... definitions) {
		for (OptionDefinition def : definitions) {
			Opper.definitions.add(def);
		}
	}

	public static void register(String name, boolean hasValue) {
		register(new OptionDefinition(name, hasValue));
	}

	public static void register(String name) {
		register(new OptionDefinition(name, false));
	}

	public static void register(String... names) {
		for (String name : names) {
			register(name, false);
		}
	}

	public static void registerMultiple(String all) {
		for (int i = 0; i < all.length(); i++) {
			register(all.substring(i, i + 1), false);
		}
	}

	public static void parse(String args[]) {
		// Last option is used to assign a value
		Option last = null;

		for (String arg : args) {
			// The list of things that could potentially be registered options
			List<String> potential = new ArrayList<>();

			if (arg.startsWith("--")) {
				potential.add(arg.substring(2));
			} else if (arg.startsWith("-")) {
				for (int i = 1; i < arg.length(); i++) {
					potential.add(arg.substring(i, i + 1));
				}
			} else {
				if (last != null && last.hasValue) {
					last.value = arg;
				} else {
					namelessValues.add(arg);
				}
			}

			for (String p : potential) {
				for (OptionDefinition def : definitions) {
					if (p.equals(def.getName())) {
						last = def.make();
						options.put(p, last);
						break;
					}
				}
			}
		}
	}

	public static boolean isSet(String name) {
		return options.containsKey(name);
	}

	public static String getValueOf(String name) {
		Option o = options.get(name);
		if (o == null) {
			return "";
		} else {
			return o.value;
		}
	}

	public static String[] getNamelessValues() {
		return (String[]) namelessValues.toArray();
	}
}
