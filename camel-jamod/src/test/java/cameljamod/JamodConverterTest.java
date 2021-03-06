/*
 *  Copyright 2012 Steven Swor.
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */
package cameljamod;

import java.util.Random;
import net.wimpi.modbus.procimg.InputRegister;
import net.wimpi.modbus.procimg.Register;
import net.wimpi.modbus.util.BitVector;
import static org.junit.Assert.*;
import org.junit.Test;

/**
 *
 * @author Steven Swor
 */
public class JamodConverterTest {

    private static final Random random = new Random();

    private static byte[] generateRandomBytes(final int maxSize) {
        int size = 1 + random.nextInt(maxSize);
        byte[] randomBytes = new byte[size];
        random.nextBytes(randomBytes);
        return randomBytes;
    }

    private static byte[] generateRandomEvenNumberOfBytes(final int maxSize) {
        int size = 1 + random.nextInt(maxSize);
        size += size % 2;
        byte[] randomBytes = new byte[size];
        random.nextBytes(randomBytes);
        return randomBytes;

    }

    /**
     * Test of toByteArray method, of class JamodConverter.
     */
    @Test
    public void testToByteArrayAndToBitVector() {
        byte[] expected = generateRandomBytes(8);
        BitVector bitVector = JamodConverter.toBitVector(expected);
        assertArrayEquals(expected, JamodConverter.toByteArray(bitVector));
    }

    /**
     * Test of toString method, of class JamodConverter.
     */
    @Test
    public void testToStringAndToBitVector() {
        for (int capacity = 0; capacity < 32; capacity++) {
            StringBuilder sb = new StringBuilder(capacity);
            int eightCount = 0;
            while (sb.length() < capacity + eightCount) {
                if (sb.length() > 0 && (sb.length() - eightCount) % 8 == 0) {
                    eightCount++;
                    sb.append(" ");
                }
                if (random.nextBoolean()) {
                    sb.append('1');
                } else {
                    sb.append('0');
                }
            }
            String str = sb.toString();
            BitVector bv = JamodConverter.toBitVector(str);
            String after = JamodConverter.toString(bv);
            assertEquals(str, after);
        }
    }

    @Test
    public void testConvertingBetweenBytesAndInputRegisters() {
        byte[] bytes = generateRandomEvenNumberOfBytes(32);
        assertTrue(bytes.length % 2 == 0);
        InputRegister[] registers = JamodConverter.toInputRegisterArray(bytes);
        assertArrayEquals(bytes, JamodConverter.toByteArray(registers));
    }
    
    @Test
    public void testConvertingBetweenBytesAndRegisters() {
        byte[] bytes = generateRandomEvenNumberOfBytes(32);
        assertTrue(bytes.length % 2 == 0);
        Register[] registers = JamodConverter.toRegisterArray(bytes);
        assertArrayEquals(bytes, JamodConverter.toByteArray(registers));
    }
    
    @Test
    public void testConvertingFromSingleRegisterToBytes() {
        byte[] bytes = generateRandomEvenNumberOfBytes(2);
        assertTrue(bytes.length==2);
        Register register = JamodConverter.toRegisterArray(bytes)[0];
        assertArrayEquals(bytes, JamodConverter.toByteArray(register));
    }
    
    @Test
    public void testConvertingFromSingleInputRegisterToBytes() {
        byte[] bytes = generateRandomEvenNumberOfBytes(2);
        assertTrue(bytes.length==2);
        InputRegister register = JamodConverter.toInputRegisterArray(bytes)[0];
        assertArrayEquals(bytes, JamodConverter.toByteArray(register));
    }
}
