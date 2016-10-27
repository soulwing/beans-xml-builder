package org.soulwing.cdi.beans;

/**
 * An exception thrown when building a descriptor fails.
 *
 * @author Carl Harris
 */
public class DescriptorBuildException extends RuntimeException {

  public DescriptorBuildException(String message, Throwable cause) {
    super(message, cause);
  }

  public DescriptorBuildException(String message) {
    super(message);
  }

  public DescriptorBuildException(Throwable cause) {
    super(cause);
  }

}
