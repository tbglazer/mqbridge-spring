package il.co.fibi.comm.mqbridge.data.decorators;

public final class DecoratorUtils {

	public static interface IGetName {
		public String getName();
	}

	public static enum Param implements IGetName {
		;
		private final String name;

		private Param(String name) {
			this.name = name;
		}

		@Override
		public String getName() {
			return name;
		}
	}

	public static int getAsUnsignedInt(String input, int defval) {
		return input.isEmpty() ? defval : Integer.parseUnsignedInt(input);
	}

	public static boolean getAsBoolean(String input, boolean defval) {
		return input.isEmpty() ? defval : Boolean.parseBoolean(input);
	}

}
