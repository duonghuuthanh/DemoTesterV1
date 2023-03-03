
import com.dht.services.NguyenToService;
import com.sun.jdi.connect.Connector;
import java.util.stream.Stream;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvFileSource;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author admin
 */
public class NguyenToParamsTester {
    @ParameterizedTest
    @ValueSource(ints = {5, 7, 11, 23, 47})
    public void testOddNumber(int n) {
        boolean actual = NguyenToService.isNguyenTo(n);
        Assertions.assertTrue(actual);
    }
    
    @ParameterizedTest
    @CsvSource(value = {"6,false", "2,true", "1,false", "4,false", "11,true"})
    public void testCsv(int n, boolean expected) {
        boolean actual = NguyenToService.isNguyenTo(n);
        Assertions.assertEquals(expected, actual);
    }
    
    @ParameterizedTest
    @CsvFileSource(resources = "/data/data.csv", numLinesToSkip = 1)
    public void testCsvFile(int n, boolean expected) {
        boolean actual = NguyenToService.isNguyenTo(n);
        Assertions.assertEquals(expected, actual);
    }
    
    @ParameterizedTest
    @MethodSource(value = "ntData")
    public void testMethod(int n, boolean expected) {
        boolean actual = NguyenToService.isNguyenTo(n);
        Assertions.assertEquals(expected, actual);
    }
    
    static Stream<Arguments> ntData() {
        // ...
        return Stream.of(
                Arguments.arguments(3,true),
                Arguments.arguments(8,false)
        );
                
    }
}
