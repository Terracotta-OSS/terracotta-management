package org.terracotta.management;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;
import org.nustaq.serialization.FSTConfiguration;
import org.terracotta.management.call.ContextualReturn;
import org.terracotta.management.context.Context;
import org.terracotta.management.stats.NumberUnit;
import org.terracotta.management.stats.Sample;
import org.terracotta.management.stats.history.CounterHistory;
import org.terracotta.management.stats.history.RatioHistory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInput;
import java.io.ObjectOutput;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static java.util.Arrays.asList;
import static org.junit.Assert.assertEquals;

/**
 * @author Mathieu Carbou
 */
@RunWith(JUnit4.class)
public class FstSerializationTest {

  @Test
  public void testComplex() throws Exception {
    List<Object> objects = Arrays.asList(
        new CounterHistory("TestCount1", asList(new Sample<Long>(1L, 1L)), NumberUnit.COUNT),
        new RatioHistory("TestCount2", asList(new Sample<Double>(1L, 1.0)), NumberUnit.PERCENT),
        ContextualReturn.of(Context.create("k", "v"), 1L),
        ContextualReturn.of(Context.empty(), 1.0),
        ContextualReturn.empty(Context.create("k", "v")));

    ByteArrayOutputStream baos = new ByteArrayOutputStream();

    FSTConfiguration conf = FSTConfiguration.createFastBinaryConfiguration();
    conf.setForceSerializable(true);

    ObjectOutput objectOutput = conf.getObjectOutput(baos);

    for (Object o : objects) {
      objectOutput.writeObject(o);
    }

    objectOutput.flush();

    byte[] bytes = baos.toByteArray();

    ObjectInput input = conf.getObjectInput(new ByteArrayInputStream(bytes));

    List<Object> oo = new ArrayList<Object>();
    for (int i = 0; i < objects.size(); i++) {
      oo.add(input.readObject());
    }

    assertEquals(objects, oo);
  }

}
