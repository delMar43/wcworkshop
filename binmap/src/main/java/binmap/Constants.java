package binmap;

public class Constants {
  public static final int SIZE_DYNAMIC = -1;

  /**
   * get the offset from the address table
   */
  public static final int OFFSET_BLOCK = -1;
  /**
   * calculate the offset based on previous lengths
   */
  public static final int OFFSET_CALC = -2;

  /**
   * as often as block offsets are defined
   */
  public static final int TIMES_OFFSETS = -1;
  /**
   * as often as the current property value has bytes
   */
  public static final int TIMES_ALL = -2;
  /**
   * as often as is still needed to iterate to the last defined block offset
   */
  public static final int TIMES_TOTHEEND = -3;

  /**
   * the string is null-terminated
   */
  public static final int LENGTH_NULLTERMINATED = -1;
}
