/*
 * Copyright Terracotta, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.terracotta.management.call;

/**
 * Represents a method parameter
 *
 * @author Mathieu Carbou
 */
public final class Parameter {

  private final Object value;
  private final String className;

  public Parameter(Object value) {
    this(value, value.getClass().getName());
  }

  public Parameter(Object value, String className) {
    if (className == null) {
      throw new NullPointerException();
    }
    this.value = value;
    this.className = className;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Parameter parameter = (Parameter) o;
    return value != null ? value.equals(parameter.value) : parameter.value == null && className.equals(parameter.className);
  }

  @Override
  public int hashCode() {
    int result = value != null ? value.hashCode() : 0;
    result = 31 * result + className.hashCode();
    return result;
  }

  @Override
  public String toString() {
    final StringBuilder sb = new StringBuilder("Parameter{");
    sb.append("className='").append(className).append('\'');
    sb.append(", value=").append(value);
    sb.append('}');
    return sb.toString();
  }

  public Object getValue() {
    return value;
  }

  public String getClassName() {
    return className;
  }
}
