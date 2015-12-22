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
package org.terracotta.management.stats;

/**
 * @author Ludovic Orban
 */
public abstract class AbstractStatistic<V, U> implements Statistic<V, U> {

  private final String name;
  private final V value;
  private final U unit;

  public AbstractStatistic(String name, V value, U unit) {
    this.name = name;
    this.value = value;
    this.unit = unit;
  }

  @Override
  public U getUnit() {
    return unit;
  }

  @Override
  public String getName() {
    return name;
  }

  @Override
  public V getValue() {
    return value;
  }

  @Override
  public String toString() {
    return "Statistic{" + "name='" + getName() + '\'' + ", value=" + getValue() + ", unit=" + getUnit() + '}';
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    AbstractStatistic<?, ?> that = (AbstractStatistic<?, ?>) o;
    return name.equals(that.name) && value.equals(that.value) && unit.equals(that.unit);
  }

  @Override
  public int hashCode() {
    int result = name.hashCode();
    result = 31 * result + value.hashCode();
    result = 31 * result + unit.hashCode();
    return result;
  }
}
