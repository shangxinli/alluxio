/*
 * The Alluxio Open Foundation licenses this work under the Apache License, version 2.0
 * (the "License"). You may not use this work except in compliance with the License, which is
 * available at www.apache.org/licenses/LICENSE-2.0
 *
 * This software is distributed on an "AS IS" basis, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND,
 * either express or implied, as more fully set forth in the License.
 *
 * See the NOTICE file distributed with this work for information regarding copyright ownership.
 */

package alluxio.underfs.abfs;

import alluxio.ConfigurationTestUtils;
import alluxio.conf.AlluxioConfiguration;
import alluxio.underfs.UnderFileSystemFactory;
import alluxio.underfs.UnderFileSystemFactoryRegistry;

import org.junit.Assert;
import org.junit.Test;

/**
 * Unit tests for {@link AbfsUnderFileSystemFactory}.
 */
public class AbfsUnderFileSystemFactoryTest {

  /**
   * Tests the
   * {@link UnderFileSystemFactoryRegistry#find(String, alluxio.conf.AlluxioConfiguration)} method.
   */
  @Test
  public void factory() {
    AlluxioConfiguration conf = ConfigurationTestUtils.copyDefaults();
    UnderFileSystemFactory factory =
        UnderFileSystemFactoryRegistry.find("abfs://localhost/test/path", conf);
    Assert.assertNotNull(
        "A UnderFileSystemFactory should exist for abfs paths when using this module",
        factory);

    factory = UnderFileSystemFactoryRegistry.find("abfss://localhost/test/path", conf);
    Assert.assertNotNull(
        "A UnderFileSystemFactory should exist for abfss paths when using this module",
        factory);

    factory = UnderFileSystemFactoryRegistry.find("alluxio://localhost/test/path", conf);
    Assert.assertNull("A UnderFileSystemFactory should not exist for unsupported paths when using"
        + " this module.", factory);
  }
}
