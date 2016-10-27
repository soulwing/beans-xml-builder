package org.soulwing.cdi.beans;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Stream;

/**
 * A simpler wrapper for {@link List}.
 *
 * @author Carl Harris
 */
class MutableList<T> {

  private final List<T> delegate = new ArrayList<>();

  void append(T element) {
    delegate.add(element);
  }

  void insertBefore(T existingElement, T newElement) {
    int index = delegate.indexOf(existingElement);
    if (index == -1) {
      index = 0;
    }
    delegate.add(index, newElement);
  }

  void insertAfter(T existingElement, T newElement) {
    int index = delegate.lastIndexOf(existingElement);
    if (index == -1) {
      index = delegate.size() - 1;
    }
    delegate.add(index + 1, newElement);
  }

  void remove(T element) {
    delegate.remove(element);
  }

  List<T> toList() {
    return Collections.unmodifiableList(delegate);
  }

  boolean isEmpty() {
    return delegate.isEmpty();
  }

}
